package govuk

import govuk.pages._

class EmailAddressSpec extends PageSpec {

  "The email address page" should "show an error for an invalid email" in {
    reachEmailAddressPage()

    val emailAddressPage = new EmailAddressPage(driver)
    emailAddressPage.assertions()
    emailAddressPage.fill("not-an-email")
    emailAddressPage.submit()
    emailAddressPage.errorAssertions()
  }

  it should "reach the confirmation page showing the submitted address" in {
    reachEmailAddressPage()

    val emailAddressPage = new EmailAddressPage(driver)
    emailAddressPage.assertions()
    emailAddressPage.fill("jamie.smith@example.com")
    emailAddressPage.submit()

    val confirmationPage = new ConfirmationPage(driver)
    confirmationPage.assertions()
    confirmationPage.rowAssertion("summary-email", "jamie.smith@example.com")
  }
}
