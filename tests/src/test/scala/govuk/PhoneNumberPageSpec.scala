package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class PhoneNumberPageSpec extends PageSpec {

  "The phone number page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo(PhoneNumberPage.url)

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
