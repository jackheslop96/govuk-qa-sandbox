package govuk.pages

class MaritalStatusPage extends DropdownPage("maritalStatus") {

  override val url: String = "/marital-status"

  override val heading: String = "What is your marital status?"
}
