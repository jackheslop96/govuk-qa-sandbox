package govuk

import govuk.pages._

class AboutYouPageSpec extends PageSpec {

  "The about you page" should "redirect back to start of journey if visited directly" in {
    goTo(AboutYouPage.url)

    val namePage = new NamePage(driver)
    namePage.assertions()
  }

  it should "accept free text and move on to the email choice page" in {
    reachAboutYouPage()

    val aboutYouPage = new AboutYouPage(driver)
    aboutYouPage.assertions()
    aboutYouPage.fill("I enjoy long walks and short unit tests.")
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage(driver)
    emailChoicePage.assertions()
  }

  it should "allow the field to be left blank, since it's optional" in {
    reachAboutYouPage()

    val aboutYouPage = new AboutYouPage(driver)
    aboutYouPage.assertions()
    aboutYouPage.submit()

    val emailChoicePage = new EmailChoicePage(driver)
    emailChoicePage.assertions()
  }
}
