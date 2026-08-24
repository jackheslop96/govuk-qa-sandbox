package govuk.pages

class EmailAddressPage extends StringPage("email") {

  override val url: String = EmailAddressPage.url

  override val heading: String = EmailAddressPage.heading
}

object EmailAddressPage {
  val url = "/email-address"
  val heading = "What is your email address?"
}
