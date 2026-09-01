package govuk.pages

import org.openqa.selenium.WebDriver

class PhoneNumberPage(override protected val driver: WebDriver) extends StringPage("phoneNumber") {

  override val url: String = PhoneNumberPage.url

  override val heading: String = PhoneNumberPage.heading
}

object PhoneNumberPage {

  def apply(driver: WebDriver): PhoneNumberPage = {
    val page = new PhoneNumberPage(driver)
    page.assertions()
    page
  }

  val url = "/phone-number"
  val heading = "What is your phone number?"
}
