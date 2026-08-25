package govuk.pages

import org.openqa.selenium.WebDriver

class EmailChoicePage(override protected val driver: WebDriver) extends YesNoPage {

  override val url: String = EmailChoicePage.url

  override val heading: String = EmailChoicePage.heading
}

object EmailChoicePage {
  val url = "/email-choice"
  val heading = "Would you like to provide an email address?"
}
