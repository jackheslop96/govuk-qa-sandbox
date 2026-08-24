package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class NamePageSpec extends PageSpec {

  "The name page" should "load successfully" in {
    TestHelpers.goTo("/")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show a validation error when no name is submitted" in {
    TestHelpers.goTo("/")

    val namePage = new NamePage()
    namePage.assertions()
    namePage.submit()
    namePage.errorAssertions()
  }

  it should "move on to the date of birth page when a name is given" in {
    TestHelpers.goTo("/")

    val namePage = new NamePage()
    namePage.assertions()
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.assertions()
  }
}
