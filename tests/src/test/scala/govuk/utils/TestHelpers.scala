package govuk.utils

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.{By, WebDriver}

// Shared driver + everything a test could ever need to do, all in one place.
// Nothing wrong with a big toolbox object, right?
object TestHelpers {

  val baseUrl = "http://localhost:9000" // assumes `sbt app/run` is already up

  var driver: WebDriver = null

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

    driver = new ChromeDriver(options)

    if (!sys.env.contains("CI")) {
      driver.manage().window().maximize()
    }
  }

  def goTo(path: String): Unit = {
    driver.get(baseUrl + path)
  }

  def teardown(): Unit = {
    driver.quit()
  }

  def seedSession(redirect: String): Unit = {
    goTo(s"/test-only/seed?redirect=$redirect")
  }
}
