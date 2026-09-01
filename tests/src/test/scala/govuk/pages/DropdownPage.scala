package govuk.pages

import org.openqa.selenium.support.ui.Select

abstract class DropdownPage(id: String) extends QuestionPage {

  def select(value: String): Unit = {
    val select = new Select(findElementById(id))
    select.selectByValue(value)
  }
}
