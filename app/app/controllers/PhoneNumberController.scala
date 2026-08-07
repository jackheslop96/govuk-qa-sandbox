package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class PhoneNumberFormData(phoneNumber: Option[String])

@Singleton
class PhoneNumberController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  private val phonePattern = "^[0-9 +()-]{7,20}$".r

  val form: Form[PhoneNumberFormData] = Form(
    mapping(
      "phoneNumber" -> optional(text)
        .verifying(
          "phone-number.error.invalid",
          _.forall(number => phonePattern.matches(number))
        )
    )(PhoneNumberFormData.apply)(PhoneNumberFormData.unapply)
  )

  def showPhoneNumber(): Action[AnyContent] = Action { implicit request =>
    request.session.get("postcode") match {
      case None => Redirect(routes.AddressController.showAddress())
      case Some(_) =>
        val preFilled = request.session.get("phoneNumber") match {
          case Some(number) if number.nonEmpty => form.fill(PhoneNumberFormData(Some(number)))
          case _                                => form
        }
        Ok(views.html.phoneNumber(preFilled))
    }
  }

  def submitPhoneNumber(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.phoneNumber(formWithErrors)),
        data =>
          Redirect(routes.HobbiesController.showHobbies())
            .withSession(request.session + ("phoneNumber" -> data.phoneNumber.getOrElse("")))
      )
  }
}
