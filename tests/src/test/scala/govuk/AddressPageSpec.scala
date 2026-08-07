package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class AddressPageSpec extends AnyFlatSpec {

  private def reachAddressPage(): Unit = {
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
  }

  "The address page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.setup()
    TestHelpers.goTo("/address")

    assert(TestHelpers.driver.getCurrentUrl.endsWith("/"))

    TestHelpers.teardown()
  }

  it should "show an error for an invalid postcode" in {
    TestHelpers.setup()
    reachAddressPage()

    TestHelpers.typeText("#addressLine1", "221B Baker Street")
    TestHelpers.typeText("#townOrCity", "London")
    TestHelpers.typeText("#postcode", "!!!")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    val errorSummaryVisible = TestHelpers.driver.findElements(
      org.openqa.selenium.By.cssSelector(".govuk-error-summary")
    ).size() > 0
    assert(errorSummaryVisible)

    TestHelpers.teardown()
  }

  it should "accept a valid address, with line 2 left blank, and move on" in {
    TestHelpers.setup()
    reachAddressPage()

    TestHelpers.typeText("#addressLine1", "221B Baker Street")
    TestHelpers.typeText("#townOrCity", "London")
    TestHelpers.typeText("#postcode", "NW1 6XE")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("phone-number"))

    TestHelpers.teardown()
  }
}
