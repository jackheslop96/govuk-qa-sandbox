package govuk

import govuk.pages._

class NationalityPageSpec extends PageSpec {

  "The nationality page" should "redirect back to start of journey if visited directly" in {
    goTo(NationalityPage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
  }

  it should "show an error if Other is selected without specifying a nationality" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage(driver)
    nationalityPage.assertions()
    nationalityPage.select("Other")
    nationalityPage.submit()
    nationalityPage.errorAssertions()
  }

  it should "accept Other with a specified nationality and move on" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage(driver)
    nationalityPage.assertions()
    nationalityPage.select("Other")
    nationalityPage.fill("French")
    nationalityPage.submit()

    val maritalStatusPage = new MaritalStatusPage(driver)
    maritalStatusPage.assertions()
  }

  it should "accept British and move straight on" in {
    reachNationalityPage()

    val nationalityPage = new NationalityPage(driver)
    nationalityPage.assertions()
    nationalityPage.select("British")
    nationalityPage.submit()

    val maritalStatusPage = new MaritalStatusPage(driver)
    maritalStatusPage.assertions()
  }
}
