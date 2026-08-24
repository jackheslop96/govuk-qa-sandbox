package govuk.pages

import org.openqa.selenium.{By, WebElement}

class ConfirmationPage extends Page {

  override val url: String = ConfirmationPage.url

  override val heading: String = ConfirmationPage.heading

  def rowAssertion(id: String, expectedText: String): Unit = {
    val row = findElementById(id)
    assert(row.getText.equals(expectedText))
  }

  private val startAgainLink: WebElement = findElementBy(By.xpath(s"//a[normalize-space()='Start again']"))

  def startAgain(): Unit = startAgainLink.click()
}

object ConfirmationPage {
  val url = "/confirmation"
  val heading = "Details submitted"
}
