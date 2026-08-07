package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class MaritalStatusFormData(maritalStatus: String)

@Singleton
class MaritalStatusController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val validOptions = Set("single", "married", "divorced", "widowed", "prefer-not-to-say")

  val form: Form[MaritalStatusFormData] = Form(
    mapping(
      "maritalStatus" -> nonEmptyText.verifying("marital-status.error.required", option => validOptions.contains(option))
    )(MaritalStatusFormData.apply)(MaritalStatusFormData.unapply)
  )

  def showMaritalStatus(): Action[AnyContent] = Action { implicit request =>
    request.session.get("nationality") match {
      case None => Redirect(routes.NationalityController.showNationality())
      case Some(_) =>
        val preFilled = request.session.get("maritalStatus") match {
          case Some(value) => form.fill(MaritalStatusFormData(value))
          case None         => form
        }
        Ok(views.html.maritalStatus(preFilled))
    }
  }

  def submitMaritalStatus(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.maritalStatus(formWithErrors)),
        data =>
          Redirect(routes.AddressController.showAddress())
            .withSession(request.session + ("maritalStatus" -> data.maritalStatus))
      )
  }
}
