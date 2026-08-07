package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class AboutYouFormData(aboutYou: Option[String])

@Singleton
class AboutYouController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val form: Form[AboutYouFormData] = Form(
    mapping(
      "aboutYou" -> optional(text)
        .verifying("about-you.error.too-long", _.forall(_.length <= 500))
    )(AboutYouFormData.apply)(AboutYouFormData.unapply)
  )

  def showAboutYou(): Action[AnyContent] = Action { implicit request =>
    request.session.get("hobbies") match {
      case None => Redirect(routes.HobbiesController.showHobbies())
      case Some(_) =>
        val preFilled = request.session.get("aboutYou") match {
          case Some(text) if text.nonEmpty => form.fill(AboutYouFormData(Some(text)))
          case _                            => form
        }
        Ok(views.html.aboutYou(preFilled))
    }
  }

  def submitAboutYou(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.aboutYou(formWithErrors)),
        data =>
          Redirect(routes.EmailChoiceController.showEmailChoice())
            .withSession(request.session + ("aboutYou" -> data.aboutYou.getOrElse("")))
      )
  }
}
