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
    TestHelpers.seedSession(DateOfBirthPage.url)
  }

  protected def reachNationalityPage(): Unit = {
    TestHelpers.seedSession(NationalityPage.url)
  }

  protected def reachMaritalStatusPage(): Unit = {
    TestHelpers.seedSession(MaritalStatusPage.url)
  }

  protected def reachAddressPage(): Unit = {
    TestHelpers.seedSession(HomeAddressPage.url)
  }

  protected def reachPhoneNumberPage(): Unit = {
    TestHelpers.seedSession(PhoneNumberPage.url)
  }

  protected def reachHobbiesPage(): Unit = {
    TestHelpers.seedSession(HobbiesPage.url)
  }

  protected def reachAboutYouPage(): Unit = {
    TestHelpers.seedSession(AboutYouPage.url)
  }

  protected def reachEmailChoicePage(): Unit = {
    TestHelpers.seedSession(EmailChoicePage.url)
  }

  protected def reachEmailAddressPage(): Unit = {
    TestHelpers.seedSession(EmailAddressPage.url)
  }
}
