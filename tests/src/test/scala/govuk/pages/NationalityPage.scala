package govuk.pages

import govuk.utils.TextInputHelpers
import org.openqa.selenium.WebElement

class NationalityPage extends ChoicePage with TextInputHelpers {

  override val url: String = "/nationality"

  override val heading: String = "What is your nationality?"

  val otherNationalityInput: WebElement = findElementById("otherNationality")

  def fill(value: String): Unit = fill(otherNationalityInput, value)
}
