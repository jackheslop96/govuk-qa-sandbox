package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class AboutYouPageSpec extends AnyFlatSpec {

  private def reachAboutYouPage(): Unit = {
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
  }

  "The about you page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.setup()
    TestHelpers.goTo("/about-you")

    assert(TestHelpers.driver.getCurrentUrl.endsWith("/"))

    TestHelpers.teardown()
  }

  it should "accept free text and move on to the email choice page" in {
    TestHelpers.setup()
    reachAboutYouPage()

    TestHelpers.typeText("#aboutYou", "I enjoy long walks and short unit tests.")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("email-choice"))

    TestHelpers.teardown()
  }

  it should "allow the field to be left blank, since it's optional" in {
    TestHelpers.setup()
    reachAboutYouPage()

    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("email-choice"))

    TestHelpers.teardown()
  }
}
