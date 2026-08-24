package govuk.utils

import org.openqa.selenium.WebElement

trait TextInputHelpers {

  protected def fill(input: WebElement, value: String): Unit = {
    input.clear()
    input.sendKeys(value)
  }
}
