package govuk.pages

import org.openqa.selenium.{By, WebElement}

trait QuestionPage extends Page {

  protected def fill(input: WebElement, value: String): Unit = {
    input.clear()
    input.sendKeys(value)
  }

  def submit(): Unit = {
    val button: WebElement = findClickableElementBy(By.cssSelector("[type='submit']"))
    button.submit()
  }

  def errorAssertions(): Unit = {
    findVisibleElementBy(By.cssSelector(".govuk-error-summary"))
  }
}
