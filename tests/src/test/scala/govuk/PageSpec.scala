package govuk

import govuk.pages._
import govuk.utils.TestHelpers
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec

trait PageSpec extends AnyFlatSpec with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    TestHelpers.setup()
  }

  override def afterEach(): Unit = {
    TestHelpers.teardown()
  }

  protected def reachNamePage(): Unit = {
    TestHelpers.goTo(NamePage.url)
  }

  protected def reachDateOfBirthPage(): Unit = {
    reachNamePage()

    val namePage = new NamePage()
    namePage.fill("Jamie Smith")
    namePage.submit()
  }

  protected def reachNationalityPage(): Unit = {
    reachDateOfBirthPage()

    val dateOfBirthPage = new DateOfBirthPage()
    dateOfBirthPage.fill(17, 3, 1990)
    dateOfBirthPage.submit()
  }

  protected def reachMaritalStatusPage(): Unit = {
    reachNationalityPage()

    val nationalityPage = new NationalityPage()
    nationalityPage.select("British")
    nationalityPage.submit()
  }

  protected def reachAddressPage(): Unit = {
    reachMaritalStatusPage()

    val maritalStatusPage = new MaritalStatusPage()
    maritalStatusPage.select("single")
    maritalStatusPage.submit()
  }

  protected def reachPhoneNumberPage(): Unit = {
    reachAddressPage()

    val homeAddressPage = new HomeAddressPage()
    homeAddressPage.fill("221B Baker Street", "London", "NW1 6XE")
    homeAddressPage.submit()
  }

  protected def reachHobbiesPage(): Unit = {
    reachPhoneNumberPage()

    val phoneNumberPage = new PhoneNumberPage()
    phoneNumberPage.submit()
  }

  protected def reachAboutYouPage(): Unit = {
    reachHobbiesPage()

    val hobbiesPage = new HobbiesPage()
    hobbiesPage.submit()
  }

  protected def reachEmailChoicePage(): Unit = {
    reachAboutYouPage()

    val aboutYouPage = new AboutYouPage()
    aboutYouPage.submit()
  }

  protected def reachEmailAddressPage(): Unit = {
    reachEmailChoicePage()

    val emailChoicePage = new EmailChoicePage()
    emailChoicePage.selectYes()
    emailChoicePage.submit()
  }
}
