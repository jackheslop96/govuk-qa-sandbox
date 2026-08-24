package govuk.pages

class PhoneNumberPage extends StringPage("phoneNumber") {

  override val url: String = PhoneNumberPage.url

  override val heading: String = PhoneNumberPage.heading
}

object PhoneNumberPage {
  val url = "/phone-number"
  val heading = "What is your phone number?"
}
