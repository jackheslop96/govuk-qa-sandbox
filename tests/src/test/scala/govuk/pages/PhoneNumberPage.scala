package govuk.pages

class PhoneNumberPage extends StringPage("phoneNumber") {

  override val url: String = "/phone-number"

  override val heading: String = "What is your phone number?"
}
