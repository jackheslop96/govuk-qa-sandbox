package govuk.pages

import org.openqa.selenium.WebDriver

class HobbiesPage(override protected val driver: WebDriver) extends ChoicePage {

  override val url: String = HobbiesPage.url

  override val heading: String = HobbiesPage.heading
}

object HobbiesPage {
  val url = "/hobbies"
  val heading = "What are your hobbies?"
}
