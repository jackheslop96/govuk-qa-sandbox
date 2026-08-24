package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class NationalityPageSpec extends PageSpec {

  "The nationality page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo(NationalityPage.url)

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error if Other is selected without specifying a nationality" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage()
    nationalityPage.assertions()
    nationalityPage.select("Other")
    nationalityPage.submit()
    nationalityPage.errorAssertions()
  }

  it should "accept Other with a specified nationality and move on" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage()
    nationalityPage.assertions()
    nationalityPage.select("Other")
    nationalityPage.fill("French")
    nationalityPage.submit()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
  }

  it should "accept British and move straight on" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage()
    nationalityPage.assertions()
    nationalityPage.select("British")
    nationalityPage.submit()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
  }
}
