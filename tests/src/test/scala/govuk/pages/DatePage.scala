package govuk.pages

import org.openqa.selenium.WebElement

trait DatePage extends QuestionPage {

  def fill(dayValue: Int, monthValue: Int, yearValue: Int): Unit = {
    val dayInput: WebElement = findElementById("day")
    fill(dayInput, String.valueOf(dayValue))

    val monthInput: WebElement = findElementById("month")
    fill(monthInput, String.valueOf(monthValue))

    val yearInput: WebElement = findElementById("year")
    fill(yearInput, String.valueOf(yearValue))
  }
}
