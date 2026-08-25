package govuk.pages

import org.openqa.selenium.WebElement

abstract class StringPage(id: String) extends QuestionPage {

  def fill(value: String): Unit = {
    val input: WebElement = findElementById(id)
    fill(input, value)
  }
}
