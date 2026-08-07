package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

case class AddressFormData(
  addressLine1: String,
  addressLine2: Option[String],
  townOrCity: String,
  postcode: String
)

@Singleton
class AddressController @Inject() (mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  // Deliberately loose — not a full UK postcode regex, just enough to
  // reject obviously-wrong input for this sandbox.
  private val postcodePattern = "^[A-Za-z0-9 ]{5,8}$".r

  val form: Form[AddressFormData] = Form(
    mapping(
      "addressLine1" -> nonEmptyText,
      "addressLine2" -> optional(text),
      "townOrCity"   -> nonEmptyText,
      "postcode" -> nonEmptyText.verifying(
        "address.error.postcode-invalid",
        postcode => postcodePattern.matches(postcode)
      )
    )(AddressFormData.apply)(AddressFormData.unapply)
  )

  def showAddress(): Action[AnyContent] = Action { implicit request =>
    request.session.get("maritalStatus") match {
      case None => Redirect(routes.MaritalStatusController.showMaritalStatus())
      case Some(_) =>
        val preFilled = request.session.get("postcode") match {
          case Some(_) =>
            form.fill(
              AddressFormData(
                request.session.get("addressLine1").getOrElse(""),
                request.session.get("addressLine2").filter(_.nonEmpty),
                request.session.get("townOrCity").getOrElse(""),
                request.session.get("postcode").getOrElse("")
              )
            )
          case None => form
        }
        Ok(views.html.address(preFilled))
    }
  }

  def submitAddress(): Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.address(formWithErrors)),
        data =>
          Redirect(routes.PhoneNumberController.showPhoneNumber())
            .withSession(
              request.session +
                ("addressLine1" -> data.addressLine1) +
                ("addressLine2" -> data.addressLine2.getOrElse("")) +
                ("townOrCity"   -> data.townOrCity) +
                ("postcode"     -> data.postcode)
            )
      )
  }
}
