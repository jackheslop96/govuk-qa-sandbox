package govuk.pages

import org.openqa.selenium.WebDriver

class NamePage(override protected val driver: WebDriver) extends StringPage("fullName") {

  override val url: String = NamePage.url

  override val heading: String = NamePage.heading
}

object NamePage {

  def apply(driver: WebDriver): NamePage = {
    val page = new NamePage(driver)
    page.assertions()
    page
  }

  val url = "/"
  val heading = "What is your name?"
}
