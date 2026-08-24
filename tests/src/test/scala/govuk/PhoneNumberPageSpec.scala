package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class PhoneNumberPageSpec extends PageSpec {

  private def reachPhoneNumberPage(): Unit = {
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
  }

  "The phone number page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/phone-number")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error for an invalid phone number" in {
    reachPhoneNumberPage()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.assertions()
    phoneNumberPage.fill("not a phone number")
    phoneNumberPage.submit()
    phoneNumberPage.errorAssertions()
  }

  it should "allow the field to be left blank, since it's optional" in {
    reachPhoneNumberPage()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.assertions()
    phoneNumberPage.submit()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
  }
}
