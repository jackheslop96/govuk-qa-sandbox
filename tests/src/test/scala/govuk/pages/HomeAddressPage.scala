package govuk.pages

import org.openqa.selenium.WebDriver

class HomeAddressPage(override protected val driver: WebDriver) extends AddressPage {

  override val url: String = HomeAddressPage.url

  override val heading: String = HomeAddressPage.heading
}

object HomeAddressPage {

  def apply(driver: WebDriver): HomeAddressPage = {
    val page = new HomeAddressPage(driver)
    page.assertions()
    page
  }

  val url = "/address"
  val heading = "What is your address?"
}
