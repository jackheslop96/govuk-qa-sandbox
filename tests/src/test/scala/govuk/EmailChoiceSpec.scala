package govuk

import govuk.pages._

class EmailChoiceSpec extends PageSpec {

  "Choosing No" should "skip straight to the confirmation page" in {
    reachEmailChoicePage()

    val emailChoicePage = EmailChoicePage(driver)
    emailChoicePage.selectNo()
    emailChoicePage.submit()

    val confirmationPage = ConfirmationPage(driver)
    confirmationPage.rowAssertion("summary-email", "Not provided")
  }

  "Choosing Yes" should "go on to the email address page" in {
    reachEmailChoicePage()

    val emailChoicePage = EmailChoicePage(driver)
    emailChoicePage.selectYes()
    emailChoicePage.submit()

    EmailAddressPage(driver)
  }
}
