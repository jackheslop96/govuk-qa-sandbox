package govuk.pages

import org.openqa.selenium.WebDriver

class MaritalStatusPage(override protected val driver: WebDriver) extends DropdownPage("maritalStatus") {

  override val url: String = MaritalStatusPage.url

  override val heading: String = MaritalStatusPage.heading
}

object MaritalStatusPage {

  def apply(driver: WebDriver): MaritalStatusPage = {
    val page = new MaritalStatusPage(driver)
    page.assertions()
    page
  }

  val url = "/marital-status"
  val heading = "What is your marital status?"
}
