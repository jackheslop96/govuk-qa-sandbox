package govuk

import govuk.pages._

class PhoneNumberPageSpec extends PageSpec {

  "The phone number page" should "redirect back to start of journey if visited directly" in {
    goTo(PhoneNumberPage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
  }

  it should "show an error for an invalid phone number" in {
    reachPhoneNumberPage()

    val phoneNumberPage = new PhoneNumberPage(driver)
    phoneNumberPage.assertions()
    phoneNumberPage.fill("not a phone number")
    phoneNumberPage.submit()
    phoneNumberPage.errorAssertions()
  }

  it should "allow the field to be left blank, since it's optional" in {
    reachPhoneNumberPage()

    val phoneNumberPage = new PhoneNumberPage(driver)
    phoneNumberPage.assertions()
    phoneNumberPage.submit()

    val hobbiesPage = new HobbiesPage(driver)
    hobbiesPage.assertions()
  }
}
