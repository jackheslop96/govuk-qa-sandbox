package govuk.pages

import org.openqa.selenium.By

trait ChoicePage extends QuestionPage {

  def select(label: String): Unit = {
    val choice = findClickableElementBy(By.xpath(s"//label[normalize-space()='$label']"))
    choice.click()
  }
}
