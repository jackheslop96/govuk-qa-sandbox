package govuk.pages

import govuk.utils.TextInputHelpers
import org.openqa.selenium.WebElement

abstract class StringPage(val id: String) extends QuestionPage with TextInputHelpers {

  val input: WebElement = findElementById(id)

  def fill(value: String): Unit = fill(input, value)
}
