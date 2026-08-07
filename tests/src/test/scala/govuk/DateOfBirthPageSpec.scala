package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class DateOfBirthPageSpec extends AnyFlatSpec {

  "The date of birth page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.setup()
    TestHelpers.goTo("/date-of-birth")

    assert(TestHelpers.driver.getCurrentUrl.endsWith("/"))

    TestHelpers.teardown()
  }

  it should "show an error for a date that doesn't exist" in {
    TestHelpers.setup()
    TestHelpers.goTo("/")
    TestHelpers.typeText("#fullName", "Jamie Smith")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#day", "31")
    TestHelpers.typeText("#month", "2")
    TestHelpers.typeText("#year", "2020")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    val errorSummaryVisible = TestHelpers.driver.findElements(
      org.openqa.selenium.By.cssSelector(".govuk-error-summary")
    ).size() > 0
    assert(errorSummaryVisible)

    TestHelpers.teardown()
  }

  it should "move on to the nationality page for a valid date" in {
    TestHelpers.setup()
    TestHelpers.goTo("/")
    TestHelpers.typeText("#fullName", "Jamie Smith")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#day", "17")
    TestHelpers.typeText("#month", "3")
    TestHelpers.typeText("#year", "1990")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("nationality"))

    TestHelpers.teardown()
  }
}
