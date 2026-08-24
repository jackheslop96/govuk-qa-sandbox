package govuk

import govuk.pages._

class EmailChoiceSpec extends PageSpec {

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
