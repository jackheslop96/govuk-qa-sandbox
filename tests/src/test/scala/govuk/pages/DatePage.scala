package govuk.pages

import govuk.utils.TextInputHelpers
import org.openqa.selenium.WebElement

trait DatePage extends QuestionPage with TextInputHelpers {

  val dayInput: WebElement = findElementById("day")

  val monthInput: WebElement = findElementById("month")

  val yearInput: WebElement = findElementById("year")

  def fill(dayValue: Int, monthValue: Int, yearValue: Int): Unit = {
    fill(dayInput, String.valueOf(dayValue))
    fill(monthInput, String.valueOf(monthValue))
    fill(yearInput, String.valueOf(yearValue))
  }
}
