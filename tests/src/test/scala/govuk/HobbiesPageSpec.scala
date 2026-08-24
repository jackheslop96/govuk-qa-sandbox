package govuk

import govuk.pages._
import govuk.utils.TestHelpers

class HobbiesPageSpec extends PageSpec {

  "The hobbies page" should "redirect back to start of journey if visited directly" in {
    TestHelpers.goTo(HobbiesPage.url)

    val namePage = new NamePage()
    namePage.assertions()
  }

  it should "show an error if Other is selected without specifying a hobby" in {
    reachHobbiesPage()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.select("Other")
    hobbiesPage.submit()
    hobbiesPage.errorAssertions()
  }

  it should "accept multiple selections and move on" in {
    reachHobbiesPage()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.assertions()
    hobbiesPage.select("Reading")
    hobbiesPage.select("Music")
    hobbiesPage.submit()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.assertions()
  }
}
