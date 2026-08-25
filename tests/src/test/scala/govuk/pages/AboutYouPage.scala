package govuk.pages

import org.openqa.selenium.WebDriver

class AboutYouPage(override protected val driver: WebDriver) extends StringPage("aboutYou") {

  override val url: String = AboutYouPage.url

  override val heading: String = AboutYouPage.heading
}

object AboutYouPage {
  val url = "/about-you"
  val heading = "Tell us a bit about yourself"
}
