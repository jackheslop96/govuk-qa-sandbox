package govuk.pages

import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebDriver, WebElement}

import java.time.Duration

trait Page {

  val url: String

  val heading: String

  protected val driver: WebDriver

  private lazy val waitNow = new WebDriverWait(driver, Duration.ofSeconds(5))

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
    waitNow.until(ExpectedConditions.urlToBe(Page.baseUrl + url))

    waitNow.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), heading))
  }
}

object Page {
  val baseUrl = "http://localhost:9000"
}
