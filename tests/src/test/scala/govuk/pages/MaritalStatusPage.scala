package govuk.pages

class MaritalStatusPage extends DropdownPage("maritalStatus") {

  override val url: String = MaritalStatusPage.url

  override val heading: String = MaritalStatusPage.heading
}

object MaritalStatusPage {
  val url = "/marital-status"
  val heading = "What is your marital status?"
}
