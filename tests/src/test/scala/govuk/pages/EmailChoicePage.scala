package govuk.pages

class EmailChoicePage extends YesNoPage {

  override val url: String = EmailChoicePage.url

  override val heading: String = EmailChoicePage.heading
}

object EmailChoicePage {
  val url = "/email-choice"
  val heading = "Would you like to provide an email address?"
}
