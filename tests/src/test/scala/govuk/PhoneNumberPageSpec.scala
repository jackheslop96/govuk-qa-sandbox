package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class PhoneNumberPageSpec extends AnyFlatSpec {

  private def reachPhoneNumberPage(): Unit = {
    TestHelpers.goTo("/")
    TestHelpers.typeText("#fullName", "Jamie Smith")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
    TestHelpers.typeText("#day", "17")
    TestHelpers.typeText("#month", "3")
    TestHelpers.typeText("#year", "1990")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
    TestHelpers.click("#nationality")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
    TestHelpers.selectDropdown("#maritalStatus", "single")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
    TestHelpers.typeText("#addressLine1", "221B Baker Street")
    TestHelpers.typeText("#townOrCity", "London")
    TestHelpers.typeText("#postcode", "NW1 6XE")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
  }

  "The phone number page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.setup()
    TestHelpers.goTo("/phone-number")

    assert(TestHelpers.driver.getCurrentUrl.endsWith("/"))

    TestHelpers.teardown()
  }

  it should "show an error for an invalid phone number" in {
    TestHelpers.setup()
    reachPhoneNumberPage()

    TestHelpers.typeText("#phoneNumber", "not a phone number")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    val errorSummaryVisible = TestHelpers.driver.findElements(
      org.openqa.selenium.By.cssSelector(".govuk-error-summary")
    ).size() > 0
    assert(errorSummaryVisible)

    TestHelpers.teardown()
  }

  it should "allow the field to be left blank, since it's optional" in {
    TestHelpers.setup()
    reachPhoneNumberPage()

    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("hobbies"))

    TestHelpers.teardown()
  }
}
