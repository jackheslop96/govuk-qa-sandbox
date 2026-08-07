package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class EmailAddressSpec extends AnyFlatSpec {

  private def reachEmailAddressPage(): Unit = {
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
    TestHelpers.click(".govuk-button") // skip phone number
    Thread.sleep(1000)
    TestHelpers.click(".govuk-button") // skip hobbies
    Thread.sleep(1000)
    TestHelpers.click(".govuk-button") // skip about you
    Thread.sleep(1000)
    TestHelpers.click("#wantsEmail")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
  }

  "The email address page" should "show an error for an invalid email" in {
    TestHelpers.setup()
    reachEmailAddressPage()

    TestHelpers.typeText("#email", "not-an-email")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    val errorSummaryVisible = TestHelpers.driver.findElements(
      org.openqa.selenium.By.cssSelector(".govuk-error-summary")
    ).size() > 0
    assert(errorSummaryVisible)

    TestHelpers.teardown()
  }

  it should "reach the confirmation page showing the submitted address" in {
    TestHelpers.setup()
    reachEmailAddressPage()

    TestHelpers.typeText("#email", "jamie.smith@example.com")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("confirmation"))
    assert(TestHelpers.getText("#summary-email") == "jamie.smith@example.com")

    TestHelpers.teardown()
  }
}
