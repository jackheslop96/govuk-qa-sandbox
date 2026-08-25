package govuk

import govuk.pages._

class AboutYouPageSpec extends PageSpec {

  "The about you page" should "redirect back to start of journey if visited directly" in {
    goTo(AboutYouPage.url)

    NamePage(driver)
  }

  it should "accept free text and move on to the email choice page" in {
    reachAboutYouPage()

    val aboutYouPage = AboutYouPage(driver)
    aboutYouPage.fill("I enjoy long walks and short unit tests.")
    aboutYouPage.submit()

    EmailChoicePage(driver)
  }

  it should "allow the field to be left blank, since it's optional" in {
    reachAboutYouPage()

    val aboutYouPage = AboutYouPage(driver)
    aboutYouPage.submit()

    EmailChoicePage(driver)
  }
}
