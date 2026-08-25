package govuk.pages

import org.openqa.selenium.{By, WebDriver}

class ConfirmationPage(override protected val driver: WebDriver) extends Page {

  override val url: String = ConfirmationPage.url

  override val heading: String = ConfirmationPage.heading

  def rowAssertion(id: String, expectedText: String): Unit = {
    val row = findElementById(id)
    assert(row.getText.equals(expectedText))
  }

  def startAgain(): Unit = {
    val link = findElementBy(By.xpath(s"//a[normalize-space()='Start again']"))
    link.click()
  }
}

object ConfirmationPage {
  val url = "/confirmation"
  val heading = "Details submitted"
}
