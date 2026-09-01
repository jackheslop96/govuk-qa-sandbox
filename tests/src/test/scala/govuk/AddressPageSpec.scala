package govuk

import govuk.pages._

class AddressPageSpec extends PageSpec {

  "The address page" should "redirect back to start of journey if visited directly" in {
    goTo(HomeAddressPage.url)

    NamePage(driver)
  }

  it should "show an error for an invalid postcode" in {
    reachAddressPage()

    val homeAddressPage = HomeAddressPage(driver)
    homeAddressPage.fill("221B Baker Street", "London", "!!!")
    homeAddressPage.submit()
    homeAddressPage.errorAssertions()
  }

  it should "accept a valid address, with line 2 left blank, and move on" in {
    reachAddressPage()

    val homeAddressPage = HomeAddressPage(driver)
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    PhoneNumberPage(driver)
  }
}
