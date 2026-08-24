package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class AddressPageSpec extends PageSpec {

  private def reachAddressPage(): Unit = {
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
  }

  "The address page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/address")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error for an invalid postcode" in {
    reachAddressPage()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.assertions()
    homeAddressPage.fill("221B Baker Street", "London", "!!!")
    homeAddressPage.submit()
    homeAddressPage.errorAssertions()
  }

  it should "accept a valid address, with line 2 left blank, and move on" in {
    reachAddressPage()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.assertions()
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.assertions()
  }
}
