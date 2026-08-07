package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class NameFormData(fullName: String)

@Singleton
class NameController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[NameFormData] = Form(
    mapping(
      "fullName" -> nonEmptyText
    )(NameFormData.apply)(NameFormData.unapply)
  )

  def showName(): Action[AnyContent] = Action { implicit request =>
    val preFilled = request.session.get("fullName") match {
      case Some(name) => form.fill(NameFormData(name))
      case None       => form
    }
    Ok(views.html.name(preFilled))
  }

  def submitName(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.name(formWithErrors)),
        data =>
          Redirect(routes.DateOfBirthController.showDateOfBirth())
            .withSession(request.session + ("fullName" -> data.fullName))
      )
  }
}
