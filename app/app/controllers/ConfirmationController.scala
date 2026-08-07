package controllers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class ConfirmationController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  private val dobFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

  def showConfirmation(): Action[AnyContent] = Action { implicit request =>
    request.session.get("fullName") match {
      case None => Redirect(routes.NameController.showName())
      case Some(fullName) =>
        val session = request.session

        val formattedDob = session.get("dob") match {
          case Some(iso) => LocalDate.parse(iso).format(dobFormatter)
          case None      => "Not provided"
        }

        val nationality = session.get("nationality") match {
          case Some("other") => session.get("otherNationality").filter(_.nonEmpty).getOrElse("Other")
          case Some(value)   => value.capitalize
          case None          => "Not provided"
        }

        val maritalStatus = session.get("maritalStatus").map(_.replace("-", " ").capitalize).getOrElse("Not provided")

        val addressLines = List(
          session.get("addressLine1"),
          session.get("addressLine2").filter(_.nonEmpty),
          session.get("townOrCity"),
          session.get("postcode")
        ).flatten
        val address = if (addressLines.isEmpty) "Not provided" else addressLines.mkString(", ")

        val phoneNumber = session.get("phoneNumber").filter(_.nonEmpty).getOrElse("Not provided")

        val hobbies = session.get("hobbies").map(_.split(",").toList.filter(_.nonEmpty)).getOrElse(Nil)
        val otherHobby = session.get("otherHobby").filter(_.nonEmpty)
        val hobbiesDisplay =
          if (hobbies.isEmpty) "Not provided"
          else hobbies.map(h => if (h == "other") otherHobby.getOrElse("other") else h.capitalize).mkString(", ")

        val aboutYou = session.get("aboutYou").filter(_.nonEmpty).getOrElse("Not provided")
        val emailAddress = session.get("emailAddress")

        Ok(
          views.html.confirmation(
            fullName = fullName,
            formattedDob = formattedDob,
            nationality = nationality,
            maritalStatus = maritalStatus,
            address = address,
            phoneNumber = phoneNumber,
            hobbies = hobbiesDisplay,
            aboutYou = aboutYou,
            emailAddress = emailAddress
          )
        )
    }
  }

  def startAgain(): Action[AnyContent] = Action {
    Redirect(routes.NameController.showName()).withNewSession
  }
}
