package govuk

import govuk.pages._

class HobbiesPageSpec extends PageSpec {

  "The hobbies page" should "redirect back to start of journey if visited directly" in {
    goTo(HobbiesPage.url)

    NamePage(driver)
  }

  it should "show an error if Other is selected without specifying a hobby" in {
    reachHobbiesPage()

    val hobbiesPage = HobbiesPage(driver)
    hobbiesPage.select("Other")
    hobbiesPage.submit()
    hobbiesPage.errorAssertions()
  }

  it should "accept multiple selections and move on" in {
    reachHobbiesPage()

    val hobbiesPage = HobbiesPage(driver)
    hobbiesPage.select("Reading")
    hobbiesPage.select("Music")
    hobbiesPage.submit()

    AboutYouPage(driver)
  }
}
