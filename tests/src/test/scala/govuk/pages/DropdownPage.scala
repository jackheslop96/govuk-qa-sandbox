package govuk.pages

import govuk.utils.TestHelpers.driver
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select

abstract class DropdownPage(val id: String) extends QuestionPage {

  def select(value: String): Unit = {
    val select = new Select(driver.findElement(By.cssSelector(s"#$id")))
    select.selectByValue(value)
  }
}
