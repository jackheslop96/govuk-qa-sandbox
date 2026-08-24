package govuk

import govuk.utils.TestHelpers
import org.openqa.selenium.WebDriver
import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec

trait PageSpec extends AnyFlatSpec with BeforeAndAfterEach {

  override def beforeEach(): Unit = {
    TestHelpers.setup()
  }

  override def afterEach(): Unit = {
    TestHelpers.teardown()
  }

  protected def driver: WebDriver =
    TestHelpers.driver
}
