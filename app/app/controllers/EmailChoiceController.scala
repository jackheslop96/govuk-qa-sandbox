package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class EmailChoiceFormData(wantsEmail: Boolean)

@Singleton
class EmailChoiceController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[EmailChoiceFormData] = Form(
    mapping(
      "wantsEmail" -> boolean
    )(EmailChoiceFormData.apply)(EmailChoiceFormData.unapply)
  )

  def showEmailChoice(): Action[AnyContent] = Action { implicit request =>
    request.session.get("aboutYou") match {
      case None => Redirect(routes.AboutYouController.showAboutYou())
      case Some(_) =>
        val preFilled = request.session.get("wantsEmail") match {
          case Some(value) => form.fill(EmailChoiceFormData(value.toBoolean))
          case None        => form
        }
        Ok(views.html.emailChoice(preFilled))
    }
  }

  def submitEmailChoice(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.emailChoice(formWithErrors)),
        data => {
          val updatedSession = request.session + ("wantsEmail" -> data.wantsEmail.toString)
          if (data.wantsEmail) {
            Redirect(routes.EmailAddressController.showEmailAddress()).withSession(updatedSession)
          } else {
            Redirect(routes.ConfirmationController.showConfirmation())
              .withSession(updatedSession - "emailAddress")
          }
        }
      )
  }
}
