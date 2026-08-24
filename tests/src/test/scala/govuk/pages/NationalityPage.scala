package govuk.pages

import govuk.utils.TextInputHelpers
import org.openqa.selenium.WebElement

class NationalityPage extends ChoicePage with TextInputHelpers {

  override val url: String = NationalityPage.url

  override val heading: String = NationalityPage.heading

  val otherNationalityInput: WebElement = findElementById("otherNationality")

  def fill(value: String): Unit = fill(otherNationalityInput, value)
}

object NationalityPage {
  val url = "/nationality"
  val heading = "What is your nationality?"
}
