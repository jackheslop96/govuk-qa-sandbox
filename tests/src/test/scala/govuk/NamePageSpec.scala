package govuk

import govuk.pages._

class NamePageSpec extends PageSpec {

  "The name page" should "load successfully" in {
    goTo(NamePage.url)

    NamePage(driver)
  }

  it should "show a validation error when no name is submitted" in {
    goTo(NamePage.url)

    val namePage = NamePage(driver)
    namePage.submit()
    namePage.errorAssertions()
  }

  it should "move on to the date of birth page when a name is given" in {
    goTo(NamePage.url)

    val namePage = NamePage(driver)
    namePage.fill("Jamie Smith")
    namePage.submit()

    DateOfBirthPage(driver)
  }
}
