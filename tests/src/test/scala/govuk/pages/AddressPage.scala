package govuk.pages

trait AddressPage extends QuestionPage {

  def fill(line1Value: String, townOrCityValue: String, postcodeValue: String): Unit = {
    val line1Input = findElementById("addressLine1")
    fill(line1Input, line1Value)

    val townOrCityInput = findElementById("townOrCity")
    fill(townOrCityInput, townOrCityValue)

    val postcodeInput = findElementById("postcode")
    fill(postcodeInput, postcodeValue)
  }
}
