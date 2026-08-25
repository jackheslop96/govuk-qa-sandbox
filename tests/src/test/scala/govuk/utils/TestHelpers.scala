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
    Thread.sleep(1000) // give the page a moment to "settle"
  }

  def click(cssSelector: String): Unit = {
    driver.findElement(By.cssSelector(cssSelector)).click()
    Thread.sleep(500)
  }

  def typeText(cssSelector: String, text: String): Unit = {
    val el = driver.findElement(By.cssSelector(cssSelector))
    el.clear()
    el.sendKeys(text)
  }

  def selectDropdown(cssSelector: String, value: String): Unit = {
    val select = new Select(driver.findElement(By.cssSelector(cssSelector)))
    select.selectByValue(value)
  }

  def getText(cssSelector: String): String = {
    try {
      driver.findElement(By.cssSelector(cssSelector)).getText
    } catch {
      case e: Exception => "" // if anything goes wrong, just pretend nothing was there
    }
  }

  def teardown(): Unit = {
    driver.quit()
  }
}
