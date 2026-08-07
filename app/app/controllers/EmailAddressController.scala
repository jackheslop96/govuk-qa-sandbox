package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class EmailAddressFormData(email: String)

@Singleton
class EmailAddressController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[EmailAddressFormData] = Form(
    mapping(
      "email" -> email
    )(EmailAddressFormData.apply)(EmailAddressFormData.unapply)
  )

  def showEmailAddress(): Action[AnyContent] = Action { implicit request =>
    request.session.get("wantsEmail") match {
      case Some("true") =>
        val preFilled = request.session.get("emailAddress") match {
          case Some(address) => form.fill(EmailAddressFormData(address))
          case None           => form
        }
        Ok(views.html.emailAddress(preFilled))
      case _ => Redirect(routes.EmailChoiceController.showEmailChoice())
    }
  }

  def submitEmailAddress(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.emailAddress(formWithErrors)),
        data =>
          Redirect(routes.ConfirmationController.showConfirmation())
            .withSession(request.session + ("emailAddress" -> data.email))
      )
  }
}
