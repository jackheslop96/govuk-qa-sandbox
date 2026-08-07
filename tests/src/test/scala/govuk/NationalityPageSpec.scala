package govuk

import govuk.utils.TestHelpers
import org.scalatest.flatspec.AnyFlatSpec

class NationalityPageSpec extends AnyFlatSpec {

  private def fillNameAndDob(): Unit = {
    TestHelpers.goTo("/")
    TestHelpers.typeText("#fullName", "Jamie Smith")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
    TestHelpers.typeText("#day", "17")
    TestHelpers.typeText("#month", "3")
    TestHelpers.typeText("#year", "1990")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)
  }

  "The nationality page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.setup()
    TestHelpers.goTo("/nationality")

    assert(TestHelpers.driver.getCurrentUrl.endsWith("/"))

    TestHelpers.teardown()
  }

  it should "show an error if Other is selected without specifying a nationality" in {
    TestHelpers.setup()
    fillNameAndDob()

    TestHelpers.click("#nationality-3") // Other
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    val errorSummaryVisible = TestHelpers.driver.findElements(
      org.openqa.selenium.By.cssSelector(".govuk-error-summary")
    ).size() > 0
    assert(errorSummaryVisible)

    TestHelpers.teardown()
  }

  it should "accept Other with a specified nationality and move on" in {
    TestHelpers.setup()
    fillNameAndDob()

    TestHelpers.click("#nationality-3") // Other
    TestHelpers.typeText("#otherNationality", "French")
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("marital-status"))

    TestHelpers.teardown()
  }

  it should "accept British and move straight on" in {
    TestHelpers.setup()
    fillNameAndDob()

    TestHelpers.click("#nationality") // British
    TestHelpers.click(".govuk-button")
    Thread.sleep(1000)

    assert(TestHelpers.driver.getCurrentUrl.contains("marital-status"))

    TestHelpers.teardown()
  }
}
