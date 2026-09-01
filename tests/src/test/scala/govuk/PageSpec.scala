package govuk

import govuk.pages._
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec

trait PageSpec extends AnyFlatSpec with BeforeAndAfterEach {

  protected var driver: WebDriver = _

  override def beforeEach(): Unit = {
    WebDriverManager.chromedriver().setup()

    val options = new ChromeOptions()

    if (sys.env.contains("CI")) {
      options.addArguments(
        "--headless=new",
        "--no-sandbox",
        "--disable-dev-shm-usage"
      )
    } else {
      options.addArguments("--start-maximized")
    }

    driver = new ChromeDriver(options)
  }

  override def afterEach(): Unit = {
    if (driver != null) {
      driver.quit()
      driver = null
    }
  }

  protected def goTo(path: String): Unit =
    driver.get(s"${Page.baseUrl}$path")

  protected def seedSession(redirect: String): Unit =
    goTo(s"/test-only/seed?redirect=$redirect")

  protected def reachNamePage(): Unit =
    goTo(NamePage.url)

  protected def reachDateOfBirthPage(): Unit =
    seedSession(DateOfBirthPage.url)

  protected def reachNationalityPage(): Unit =
    seedSession(NationalityPage.url)

  protected def reachMaritalStatusPage(): Unit =
    seedSession(MaritalStatusPage.url)

  protected def reachAddressPage(): Unit =
    seedSession(HomeAddressPage.url)

  protected def reachPhoneNumberPage(): Unit =
    seedSession(PhoneNumberPage.url)

  protected def reachHobbiesPage(): Unit =
    seedSession(HobbiesPage.url)

  protected def reachAboutYouPage(): Unit =
    seedSession(AboutYouPage.url)

  protected def reachEmailChoicePage(): Unit =
    seedSession(EmailChoicePage.url)

  protected def reachEmailAddressPage(): Unit =
    seedSession(EmailAddressPage.url)
}