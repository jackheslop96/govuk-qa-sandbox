package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class AboutYouPageSpec extends PageSpec {

  private def reachAboutYouPage(): Unit = {
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

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.submit()
  }

  "The about you page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo("/about-you")

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "accept free text and move on to the email choice page" in {
    reachAboutYouPage()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
    aboutYouPage.fill("I enjoy long walks and short unit tests.")
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
  }

  it should "allow the field to be left blank, since it's optional" in {
    reachAboutYouPage()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
  }
}
