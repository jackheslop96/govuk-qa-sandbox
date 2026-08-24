package govuk.pages

import govuk.utils.TextInputHelpers
import org.openqa.selenium.WebElement

trait AddressPage extends QuestionPage with TextInputHelpers {

  val line1Input: WebElement = findElementById("addressLine1")

  val townOrCityInput: WebElement = findElementById("townOrCity")

  val postcodeInput: WebElement = findElementById("postcode")

  def fill(line1Value: String, townOrCityValue: String, postcodeValue: String): Unit = {
    fill(line1Input, line1Value)
    fill(townOrCityInput, townOrCityValue)
    fill(postcodeInput, postcodeValue)
  }
}
