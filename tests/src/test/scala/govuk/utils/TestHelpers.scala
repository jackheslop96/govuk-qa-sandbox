package govuk.utils

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

// Shared driver + everything a test could ever need to do, all in one place.
// Nothing wrong with a big toolbox object, right?
object TestHelpers {

  val baseUrl = "http://localhost:9000" // assumes `sbt app/run` is already up

  private val driver: ThreadLocal[WebDriver] =
    new ThreadLocal[WebDriver]()

  def setup(): Unit = {
    WebDriverManager.chromedriver().setup()

    val options = new ChromeOptions()

    if (sys.env.contains("CI")) {
      options.addArguments(
        "--headless=new",
        "--no-sandbox",
        "--disable-dev-shm-usage"
      )
    }

    val newDriver = new ChromeDriver(options)

    if (!sys.env.contains("CI")) {
      newDriver.manage().window().maximize()
    }

    driver.set(newDriver)
  }

  def getDriver: WebDriver = {
    val currentDriver = driver.get()

    if (currentDriver == null) {
      throw new IllegalStateException("WebDriver has not been initialised")
    }

    currentDriver
  }

  def goTo(path: String): Unit = {
    getDriver.get(baseUrl + path)
  }

  def teardown(): Unit = {
    val currentDriver = driver.get()

    if (currentDriver != null) {
      currentDriver.quit()
      driver.remove()
    }
  }

  def seedSession(redirect: String): Unit = {
    goTo(s"/test-only/seed?redirect=$redirect")
  }
}
