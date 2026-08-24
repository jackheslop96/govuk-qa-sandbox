package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class EmailChoiceSpec extends PageSpec {

  private def reachEmailChoicePage(): Unit = {
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
  }

  "Choosing No" should "skip straight to the confirmation page" in {
    reachEmailChoicePage()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
    emailChoicePage.selectNo()
    emailChoicePage.submit()

    val confirmationPage = new ConfirmationPage()
    confirmationPage.assertions()
    confirmationPage.rowAssertion("summary-email", "Not provided")
  }

  "Choosing Yes" should "go on to the email address page" in {
    reachEmailChoicePage()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
    emailChoicePage.selectYes()
    emailChoicePage.submit()

    val emailAddressPage = new EmailAddressPage()
    emailAddressPage.assertions()
  }
}
