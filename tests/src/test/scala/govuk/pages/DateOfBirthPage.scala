package govuk.pages

class DateOfBirthPage extends DatePage {

  override val url: String = DateOfBirthPage.url

  override val heading: String = DateOfBirthPage.heading
}

object DateOfBirthPage {
  val url = "/date-of-birth"
  val heading = "What is your date of birth?"
}
