package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class HobbiesPageSpec extends PageSpec {

  private def reachHobbiesPage(): Unit = {
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

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
    maritalStatusPage.select("single")
    maritalStatusPage.submit()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.assertions()
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.assertions()
    phoneNumberPage.submit()
  }

  "The hobbies page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/hobbies")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error if Other is selected without specifying a hobby" in {
    reachHobbiesPage()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.select("Other")
    hobbiesPage.submit()
    hobbiesPage.errorAssertions()
  }

  it should "accept multiple selections and move on" in {
    reachHobbiesPage()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.select("Reading")
    hobbiesPage.select("Music")
    hobbiesPage.submit()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
  }
}
