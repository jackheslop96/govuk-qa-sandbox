package govuk.pages

class EmailAddressPage extends StringPage("email") {

  override val url: String = "/email-address"

  override val heading: String = "What is your email address?"
}
