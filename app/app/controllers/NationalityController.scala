package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class NationalityFormData(nationality: String, otherNationality: Option[String])

@Singleton
class NationalityController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[NationalityFormData] = Form(
    mapping(
      "nationality"      -> nonEmptyText,
      "otherNationality" -> optional(text)
    )(NationalityFormData.apply)(NationalityFormData.unapply)
      .verifying(
        "nationality.error.other-required",
        data => data.nationality != "other" || data.otherNationality.exists(_.trim.nonEmpty)
      )
  )

  def showNationality(): Action[AnyContent] = Action { implicit request =>
    request.session.get("dob") match {
      case None => Redirect(routes.DateOfBirthController.showDateOfBirth())
      case Some(_) =>
        val preFilled = request.session.get("nationality") match {
          case Some(value) => form.fill(NationalityFormData(value, request.session.get("otherNationality")))
          case None         => form
        }
        Ok(views.html.nationality(preFilled))
    }
  }

  def submitNationality(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.nationality(formWithErrors)),
        data =>
          Redirect(routes.MaritalStatusController.showMaritalStatus())
            .withSession(
              request.session +
                ("nationality" -> data.nationality) +
                ("otherNationality" -> data.otherNationality.getOrElse(""))
            )
      )
  }
}
