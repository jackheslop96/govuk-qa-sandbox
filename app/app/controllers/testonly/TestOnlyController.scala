package controllers.testonly

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class TestOnlyController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def seed(): Action[AnyContent] = Action { implicit request =>
    val redirect = request.getQueryString("redirect").getOrElse("/")

    val session = redirect match {
      case "/date-of-birth" =>
        request.session + ("fullName" -> "Jamie Smith")

      case "/nationality" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17"
        )

      case "/marital-status" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> ""
        )

      case "/address" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single"
        )

      case "/phone-number" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single",
          "addressLine1" -> "221B Baker Street",
          "addressLine2" -> "",
          "townOrCity" -> "London",
          "postcode" -> "NW1 6XE"
        )

      case "/hobbies" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single",
          "addressLine1" -> "221B Baker Street",
          "addressLine2" -> "",
          "townOrCity" -> "London",
          "postcode" -> "NW1 6XE",
          "phoneNumber" -> ""
        )

      case "/about-you" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single",
          "addressLine1" -> "221B Baker Street",
          "addressLine2" -> "",
          "townOrCity" -> "London",
          "postcode" -> "NW1 6XE",
          "phoneNumber" -> "",
          "hobbies" -> "",
          "otherHobby" -> ""
        )

      case "/email-choice" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single",
          "addressLine1" -> "221B Baker Street",
          "addressLine2" -> "",
          "townOrCity" -> "London",
          "postcode" -> "NW1 6XE",
          "phoneNumber" -> "",
          "hobbies" -> "",
          "otherHobby" -> "",
          "aboutYou" -> ""
        )

      case "/email-address" =>
        request.session ++ Map(
          "fullName" -> "Jamie Smith",
          "dob" -> "1990-03-17",
          "nationality" -> "british",
          "otherNationality" -> "",
          "maritalStatus" -> "single",
          "addressLine1" -> "221B Baker Street",
          "addressLine2" -> "",
          "townOrCity" -> "London",
          "postcode" -> "NW1 6XE",
          "phoneNumber" -> "",
          "hobbies" -> "",
          "otherHobby" -> "",
          "aboutYou" -> "",
          "wantsEmail" -> "true"
        )

      case _ =>
        request.session
    }

    Redirect(redirect).withSession(session)
  }
}