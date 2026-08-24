package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class FullJourneySpec extends PageSpec {

  "A user completing the whole journey" should "see all their answers on the confirmation page" in {
    TestHelpers.goTo(NamePage.url)

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
    nationalityPage.select("Other")
    nationalityPage.fill("French")
    nationalityPage.submit()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.assertions()
    maritalStatusPage.select("married")
    maritalStatusPage.submit()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.assertions()
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.assertions()
    phoneNumberPage.fill("01234 567890")
    phoneNumberPage.submit()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.select("Reading")
    hobbiesPage.select("Music")
    hobbiesPage.submit()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
    aboutYouPage.fill("I enjoy long walks and short unit tests.")
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.assertions()
    emailChoicePage.selectYes()
    emailChoicePage.submit()

    val emailAddressPage = new EmailAddressPage()
    emailAddressPage.assertions()
    emailAddressPage.fill("jamie.smith@example.com")
    emailAddressPage.submit()

    val confirmationPage = new ConfirmationPage()
    confirmationPage.assertions()
    confirmationPage.rowAssertion("summary-name", "Jamie Smith")
    confirmationPage.rowAssertion("summary-dob", "17 March 1990")
    confirmationPage.rowAssertion("summary-nationality", "French")
    confirmationPage.rowAssertion("summary-marital-status", "Married")
    confirmationPage.rowAssertion("summary-address", "221B Baker Street, London, NW1 6XE")
    confirmationPage.rowAssertion("summary-phone-number", "01234 567890")
    confirmationPage.rowAssertion("summary-hobbies", "Reading, Music")
    confirmationPage.rowAssertion("summary-about-you", "I enjoy long walks and short unit tests.")
    confirmationPage.rowAssertion("summary-email", "jamie.smith@example.com")
  }

  it should "let the user start again from the confirmation page" in {
    TestHelpers.goTo(NamePage.url)

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
    emailChoicePage.selectNo()
    emailChoicePage.submit()

    val confirmationPage = new ConfirmationPage()
    confirmationPage.assertions()
    confirmationPage.startAgain()

    namePage.assertions()
  }
}
