package govuk.pages

class NamePage extends StringPage("fullName") {

  override val url: String = NamePage.url

  override val heading: String = NamePage.heading
}

object NamePage {
  val url = "/"
  val heading = "What is your name?"
}
