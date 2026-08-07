package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class EmailChoiceSpec extends AnyFlatSpec {

  private def reachEmailChoicePage(): Unit = {
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
  }

  "Choosing No" should "skip straight to the confirmation page" in {
    TestHelpers.setup()
    reachEmailChoicePage()

    TestHelpers.click("#wantsEmail-2")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("confirmation"))
    assert(TestHelpers.getText("#summary-email") == "Not provided")

    TestHelpers.teardown()
  }

  "Choosing Yes" should "go on to the email address page" in {
    TestHelpers.setup()
    reachEmailChoicePage()

    TestHelpers.click("#wantsEmail")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("email-address"))

    TestHelpers.teardown()
  }
}
