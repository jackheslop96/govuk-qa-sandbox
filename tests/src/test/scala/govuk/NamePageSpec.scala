package govuk

import govuk.utils.TestHelpers
import org.openqa.selenium.By
import org.scalatest.flatspec.AnyFlatSpec

class NamePageSpec extends AnyFlatSpec {

  "The name page" should "load successfully" in {
    TestHelpers.setup()
    TestHelpers.goTo("/")

    assert(TestHelpers.driver.getTitle.contains("What is your name?"))

    TestHelpers.teardown()
  }

  it should "show a validation error when no name is submitted" in {
    TestHelpers.setup()
    TestHelpers.goTo("/")

    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    // relies on the exact wording of Play's default "required" message
    val errorText = TestHelpers.driver
      .findElement(By.cssSelector(".govuk-error-summary__list li:nth-child(1) a"))
      .getText
    assert(errorText == "This field is required")

    TestHelpers.teardown()
  }

  it should "move on to the date of birth page when a name is given" in {
    TestHelpers.setup()
    TestHelpers.goTo("/")

    TestHelpers.typeText("#fullName", "Jamie Smith")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("date-of-birth"))

    TestHelpers.teardown()
  }
}
