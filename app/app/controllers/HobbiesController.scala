package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class HobbiesController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  val validHobbies = Set("reading", "sport", "music", "travel", "other")

  def showHobbies(): Action[AnyContent] = Action { implicit request =>
    request.session.get("phoneNumber") match {
      case None => Redirect(routes.PhoneNumberController.showPhoneNumber())
      case Some(_) =>
        val selected = request.session.get("hobbies").map(_.split(",").toList.filter(_.nonEmpty)).getOrElse(Nil)
        val otherHobby = request.session.get("otherHobby").getOrElse("")
        Ok(views.html.hobbies(selected, otherHobby, hasOtherError = false))
    }
  }

  def submitHobbies(): Action[AnyContent] = Action { implicit request =>
    // Bound manually rather than via a Play Form mapping: Play's list()/seq()
    // combinators expect indexed keys like hobbies[0], hobbies[1], but real
    // HTML checkboxes all sharing one name submit as repeated hobbies=x pairs.
    val submittedHobbies: List[String] =
      request.body.asFormUrlEncoded
        .flatMap(_.get("hobbies"))
        .map(_.toList.filter(validHobbies.contains))
        .getOrElse(Nil)

    val otherHobby: String =
      request.body.asFormUrlEncoded
        .flatMap(_.get("otherHobby"))
        .flatMap(_.headOption)
        .map(_.trim)
        .getOrElse("")

    if (submittedHobbies.contains("other") && otherHobby.isEmpty) {
      BadRequest(views.html.hobbies(submittedHobbies, otherHobby, hasOtherError = true))
    } else {
      Redirect(routes.AboutYouController.showAboutYou())
        .withSession(
          request.session +
            ("hobbies"    -> submittedHobbies.mkString(",")) +
            ("otherHobby" -> otherHobby)
        )
    }
  }
}
