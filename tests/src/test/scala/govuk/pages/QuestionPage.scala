package govuk.pages

import org.openqa.selenium.{By, WebElement}

trait QuestionPage extends Page {

  def submit(): Unit = {
    val button: WebElement = findClickableElementBy(By.cssSelector("[type='submit']"))
    button.submit()
  }

  def errorAssertions(): Unit = {
    findVisibleElementBy(By.cssSelector(".govuk-error-summary"))
  }
}
