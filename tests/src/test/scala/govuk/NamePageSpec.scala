package govuk

import govuk.pages._

class NamePageSpec extends PageSpec {

  "The name page" should "load successfully" in {
    goTo(NamePage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
  }

  it should "show a validation error when no name is submitted" in {
    goTo(NamePage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
    namePage.submit()
    namePage.errorAssertions()
  }

  it should "move on to the date of birth page when a name is given" in {
    goTo(NamePage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = new DateOfBirthPage(driver)
    dateOfBirthPage.assertions()
  }
}
