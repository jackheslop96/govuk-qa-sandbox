package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class EmailAddressSpec extends PageSpec {

  private def reachEmailAddressPage(): Unit = {
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

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
    emailChoicePage.selectYes()
    emailChoicePage.submit()
  }

  "The email address page" should "show an error for an invalid email" in {
    reachEmailAddressPage()

    val emailAddressPage = new EmailAddressPage()
    emailAddressPage.assertions()
    emailAddressPage.fill("not-an-email")
    emailAddressPage.submit()
    emailAddressPage.errorAssertions()
  }

  it should "reach the confirmation page showing the submitted address" in {
    reachEmailAddressPage()

    val emailAddressPage = new EmailAddressPage()
    emailAddressPage.assertions()
    emailAddressPage.fill("jamie.smith@example.com")
    emailAddressPage.submit()

    val confirmationPage = new ConfirmationPage()
    confirmationPage.assertions()
    confirmationPage.rowAssertion("summary-email", "jamie.smith@example.com")
  }
}
