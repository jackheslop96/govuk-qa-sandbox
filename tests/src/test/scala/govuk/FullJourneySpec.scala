package govuk

import govuk.pages._

class FullJourneySpec extends PageSpec {

  "A user completing the whole journey" should "see all their answers on the confirmation page" in {
    goTo(NamePage.url)

    val namePage = NamePage(driver)
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = DateOfBirthPage(driver)
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()

    val nationalityPage = NationalityPage(driver)
    nationalityPage.select("Other")
    nationalityPage.fill("French")
    nationalityPage.submit()

    val maritalStatusPage = MaritalStatusPage(driver)
    maritalStatusPage.select("married")
    maritalStatusPage.submit()

    val homeAddressPage = HomeAddressPage(driver)
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    val phoneNumberPage = PhoneNumberPage(driver)
    phoneNumberPage.fill("01234 567890")
    phoneNumberPage.submit()

    val hobbiesPage = HobbiesPage(driver)
    hobbiesPage.select("Reading")
    hobbiesPage.select("Music")
    hobbiesPage.submit()

    val aboutYouPage = AboutYouPage(driver)
    aboutYouPage.fill("I enjoy long walks and short unit tests.")
    aboutYouPage.submit()

    val emailChoicePage = EmailChoicePage(driver)
    emailChoicePage.selectYes()
    emailChoicePage.submit()

    val emailAddressPage = EmailAddressPage(driver)
    emailAddressPage.fill("jamie.smith@example.com")
    emailAddressPage.submit()

    val confirmationPage = ConfirmationPage(driver)
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
    goTo(NamePage.url)

    val namePage = NamePage(driver)
    namePage.fill("Jamie Smith")
    namePage.submit()

    val dateOfBirthPage = DateOfBirthPage(driver)
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()

    val nationalityPage = NationalityPage(driver)
    nationalityPage.select("British")
    nationalityPage.submit()

    val maritalStatusPage = MaritalStatusPage(driver)
    maritalStatusPage.select("single")
    maritalStatusPage.submit()

    val homeAddressPage = HomeAddressPage(driver)
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()

    val phoneNumberPage = PhoneNumberPage(driver)
    phoneNumberPage.submit()

    val hobbiesPage = HobbiesPage(driver)
    hobbiesPage.submit()

    val aboutYouPage = AboutYouPage(driver)
    aboutYouPage.submit()

    val emailChoicePage = EmailChoicePage(driver)
    emailChoicePage.selectNo()
    emailChoicePage.submit()

    val confirmationPage = ConfirmationPage(driver)
    confirmationPage.startAgain()

    NamePage(driver)
  }
}
