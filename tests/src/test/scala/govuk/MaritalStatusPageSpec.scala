package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class MaritalStatusPageSpec extends PageSpec {

  private def reachMaritalStatusPage(): Unit = {
    TestHelpers.goTo("/")

    val namePage = new NamePage()
    namePage.assertions()
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.assertions()
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()

    val nationalityPage = new NationalityPage()
    nationalityPage.assertions()
    nationalityPage.select("British")
    nationalityPage.submit()
  }

  "The marital status page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/marital-status")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error when no option is chosen" in {
    reachMaritalStatusPage()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
    maritalStatusPage.submit()
    maritalStatusPage.errorAssertions()
  }

  it should "accept a chosen option and move on to the address page" in {
    reachMaritalStatusPage()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
    maritalStatusPage.select("single")
    maritalStatusPage.submit()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.assertions()
  }
}
