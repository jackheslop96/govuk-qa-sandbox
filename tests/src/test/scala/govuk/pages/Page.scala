package govuk.pages

import govuk.utils.TestHelpers
import govuk.utils.TestHelpers.driver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebElement}

import java.time.Duration

trait Page {

  val url: String

  val heading: String

  private val waitNow = new WebDriverWait(driver, Duration.ofSeconds(5))

  protected def findElementById(id: String): WebElement = findElementBy(By.cssSelector(s"#$id"))

  protected def findElementBy(by: By): WebElement = {
    waitNow.until {
      ExpectedConditions.presenceOfElementLocated(by)
    }
  }

  protected def findClickableElementBy(by: By): WebElement = waitNow.until {
    ExpectedConditions.elementToBeClickable(by)
  }

  protected def findVisibleElementBy(by: By): WebElement = waitNow.until {
    ExpectedConditions.visibilityOfElementLocated(by)
  }

  def assertions(): Unit = {
    val currentUrl = TestHelpers.driver.getCurrentUrl
    val h1 = findElementBy(By.tagName("h1"))

    assert(currentUrl.equals(TestHelpers.baseUrl + url))
    assert(h1.getText.equals(heading))
  }
}
