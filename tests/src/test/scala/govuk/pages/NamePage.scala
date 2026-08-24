package govuk.pages

class NamePage extends StringPage("fullName") {

  override val url: String = "/"

  override val heading: String = "What is your name?"
}
