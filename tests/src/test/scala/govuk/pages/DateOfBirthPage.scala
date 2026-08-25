package govuk.pages

import org.openqa.selenium.WebDriver

class DateOfBirthPage(override protected val driver: WebDriver) extends DatePage {

  override val url: String = DateOfBirthPage.url

  override val heading: String = DateOfBirthPage.heading
}

object DateOfBirthPage {
  val url = "/date-of-birth"
  val heading = "What is your date of birth?"
}
