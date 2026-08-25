package govuk.pages

import org.openqa.selenium.WebDriver

class NationalityPage(override protected val driver: WebDriver) extends ChoicePage {

  override val url: String = NationalityPage.url

  override val heading: String = NationalityPage.heading

  def fill(value: String): Unit = {
    val input = findElementById("otherNationality")
    fill(input, value)
  }
}

object NationalityPage {
  val url = "/nationality"
  val heading = "What is your nationality?"
}
