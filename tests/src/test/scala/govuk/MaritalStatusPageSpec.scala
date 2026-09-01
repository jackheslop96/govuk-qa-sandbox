package govuk

import govuk.pages._

class MaritalStatusPageSpec extends PageSpec {

  "The marital status page" should "redirect back to start of journey if visited directly" in {
    goTo(MaritalStatusPage.url)

    NamePage(driver)
  }

  it should "show an error when no option is chosen" in {
    reachMaritalStatusPage()

    val maritalStatusPage = MaritalStatusPage(driver)
    maritalStatusPage.submit()
    maritalStatusPage.errorAssertions()
  }

  it should "accept a chosen option and move on to the address page" in {
    reachMaritalStatusPage()

    val maritalStatusPage = MaritalStatusPage(driver)
    maritalStatusPage.select("single")
    maritalStatusPage.submit()

    HomeAddressPage(driver)
  }
}
