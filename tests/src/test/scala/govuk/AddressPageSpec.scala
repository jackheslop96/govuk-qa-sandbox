package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class AddressPageSpec extends PageSpec {

  "The address page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo(HomeAddressPage.url)

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
