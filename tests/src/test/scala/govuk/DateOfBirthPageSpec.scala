package govuk

import govuk.pages.{DateOfBirthPage, NamePage, NationalityPage}
import govuk.utils.TestHelpers

class DateOfBirthPageSpec extends PageSpec {

  "The date of birth page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo(DateOfBirthPage.url)

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error for a date that doesn't exist" in {
    reachDateOfBirthPage()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.assertions()
    dateOfBirthPage.fill(31, 2, 2020)
    dateOfBirthPage.submit()
    dateOfBirthPage.errorAssertions()
  }

  it should "move on to the nationality page for a valid date" in {
    reachDateOfBirthPage()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.assertions()
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()

    val nationalityPage = new NationalityPage()
    nationalityPage.assertions()
  }
}
