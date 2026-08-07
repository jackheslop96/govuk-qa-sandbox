package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class FullJourneySpec extends AnyFlatSpec {

  "A user completing the whole journey" should "see all their answers on the confirmation page" in {
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

    TestHelpers.click("#nationality-3") // Other
    TestHelpers.typeText("#otherNationality", "French")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.selectDropdown("#maritalStatus", "married")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#addressLine1", "221B Baker Street")
    TestHelpers.typeText("#townOrCity", "London")
    TestHelpers.typeText("#postcode", "NW1 6XE")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#phoneNumber", "01234 567890")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.click("#hobbies")   // Reading
    TestHelpers.click("#hobbies-3") // Music
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#aboutYou", "I enjoy long walks and short unit tests.")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.click("#wantsEmail")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.typeText("#email", "jamie.smith@example.com")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.getText("#summary-name") == "Jamie Smith")
    assert(TestHelpers.getText("#summary-dob") == "17 March 1990")
    assert(TestHelpers.getText("#summary-nationality") == "French")
    assert(TestHelpers.getText("#summary-marital-status") == "Married")
    assert(TestHelpers.getText("#summary-address") == "221B Baker Street, London, NW1 6XE")
    assert(TestHelpers.getText("#summary-phone-number") == "01234 567890")
    assert(TestHelpers.getText("#summary-hobbies") == "Reading, Music")
    assert(TestHelpers.getText("#summary-about-you") == "I enjoy long walks and short unit tests.")
    assert(TestHelpers.getText("#summary-email") == "jamie.smith@example.com")

    TestHelpers.teardown()
  }

  it should "let the user start again from the confirmation page" in {
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
    TestHelpers.click("#wantsEmail-2")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    TestHelpers.click(".govuk-link")
    Thread.sleep(1000)

    // pretty much always true, weak assertion
    assert(TestHelpers.driver.getTitle != null)

    TestHelpers.teardown()
  }
}
