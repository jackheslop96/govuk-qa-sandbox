package govuk.pages

import org.openqa.selenium.WebDriver

class EmailAddressPage(override protected val driver: WebDriver) extends StringPage("email") {

  override val url: String = EmailAddressPage.url

  override val heading: String = EmailAddressPage.heading
}

object EmailAddressPage {

  def apply(driver: WebDriver): EmailAddressPage = {
    val page = new EmailAddressPage(driver)
    page.assertions()
    page
  }

  val url = "/email-address"
  val heading = "What is your email address?"
}
