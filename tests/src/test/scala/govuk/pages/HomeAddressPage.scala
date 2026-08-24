package govuk.pages

class HomeAddressPage extends AddressPage {

  override val url: String = HomeAddressPage.url

  override val heading: String = HomeAddressPage.heading
}

object HomeAddressPage {
  val url = "/address"
  val heading = "What is your address?"
}
