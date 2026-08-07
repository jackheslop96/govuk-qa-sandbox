package controllers

import java.time.LocalDate
import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import scala.util.Try

case class DateOfBirthFormData(day: Int, month: Int, year: Int)

@Singleton
class DateOfBirthController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[DateOfBirthFormData] = Form(
    mapping(
      "day"   -> number(min = 1, max = 31),
      "month" -> number(min = 1, max = 12),
      "year"  -> number(min = 1900, max = 2100)
    )(DateOfBirthFormData.apply)(DateOfBirthFormData.unapply)
      .verifying(
        "date-of-birth.error.invalid",
        data => Try(LocalDate.of(data.year, data.month, data.day)).isSuccess
      )
  )

  def showDateOfBirth(): Action[AnyContent] = Action { implicit request =>
    request.session.get("fullName") match {
      case None => Redirect(routes.NameController.showName())
      case Some(_) =>
        val preFilled = request.session.get("dob") match {
          case Some(iso) =>
            val date = LocalDate.parse(iso)
            form.fill(DateOfBirthFormData(date.getDayOfMonth, date.getMonthValue, date.getYear))
          case None => form
        }
        Ok(views.html.dateOfBirth(preFilled))
    }
  }

  def submitDateOfBirth(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.dateOfBirth(formWithErrors)),
        data => {
          val iso = LocalDate.of(data.year, data.month, data.day).toString
          Redirect(routes.EmailChoiceController.showEmailChoice())
            .withSession(request.session + ("dob" -> iso))
        }
      )
  }
}
