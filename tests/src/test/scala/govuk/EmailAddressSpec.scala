package govuk

import govuk.pages._

class EmailAddressSpec extends PageSpec {

  "The email address page" should "show an error for an invalid email" in {
    reachEmailAddressPage()

    val emailAddressPage = EmailAddressPage(driver)
    emailAddressPage.fill("not-an-email")
    emailAddressPage.submit()
    emailAddressPage.errorAssertions()
  }

  it should "reach the confirmation page showing the submitted address" in {
    reachEmailAddressPage()

    val emailAddressPage = EmailAddressPage(driver)
    emailAddressPage.fill("jamie.smith@example.com")
    emailAddressPage.submit()

    val confirmationPage = ConfirmationPage(driver)
    confirmationPage.rowAssertion("summary-email", "jamie.smith@example.com")
  }
}
