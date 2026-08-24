package govuk.pages

class HobbiesPage extends ChoicePage {

  override val url: String = HobbiesPage.url

  override val heading: String = HobbiesPage.heading
}

object HobbiesPage {
  val url = "/hobbies"
  val heading = "What are your hobbies?"
}
