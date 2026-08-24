package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class NationalityPageSpec extends PageSpec {

  private def reachNationalityPage(): Unit = {
    TestHelpers.goTo("/")

    val namePage = new NamePage()
    namePage.assertions()
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.assertions()
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()
  }

  "The nationality page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/nationality")

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
