/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.economiccrimelevystubs.controllers

import play.api.Logging
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, ControllerComponents, Result}
import uk.gov.hmrc.economiccrimelevystubs.data.ReturnStubData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.SubmitEclReturnResponse
import uk.gov.hmrc.economiccrimelevystubs.services.ChargeReferenceService
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.{Clock, Instant}
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReturnController @Inject() (
  cc: ControllerComponents,
  eclReturnReferenceService: ChargeReferenceService,
  clock: Clock
)(implicit ec: ExecutionContext)
    extends BackendController(cc)
    with Logging {

  def getReturn(periodKey: String, eclRegistrationReference: String): Action[AnyContent] = Action { _ =>
    eclRegistrationReference.takeRight(3) match {
      case "001" => Ok(Json.toJson(ReturnStubData.validReturnMedium(periodKey, eclRegistrationReference)))
      case "002" => Ok(Json.toJson(ReturnStubData.validReturnLarge(periodKey, eclRegistrationReference)))
      case "003" => Ok(Json.toJson(ReturnStubData.validReturnVeryLarge(periodKey, eclRegistrationReference)))
      case "400" =>
        BadRequest(
          Json.obj(
            "code"   -> "INVALID_ECLREFERENCE",
            "reason" -> "Submission has not passed validation. Invalid parameter eclReference."
          )
        )
      case "422" =>
        UnprocessableEntity(
          Json.obj(
            "code"   -> "NOT_FOUND_FORM",
            "reason" -> "The remote endpoint has indicated that no successfully processed forms can be found."
          )
        )
      case "503" =>
        ServiceUnavailable(
          Json.obj(
            "code"   -> "SERVICE_UNAVAILABLE",
            "reason" -> "Dependent systems are currently not responding."
          )
        )
      case _     =>
        InternalServerError(
          Json.obj(
            "code"   -> "SERVER_ERROR",
            "reason" -> "IF is currently experiencing problems that require live service intervention."
          )
        )
    }
  }

  def submitReturn(eclRegistrationReference: String): Action[JsValue] =
    Action.async(parse.json) { implicit request =>
      logger.info(
        s"Received submit return request\n\n" +
          s"ECL Registration reference: $eclRegistrationReference\n\n" +
          s"Request body:\n${Json.prettyPrint(request.body)}\n\n" +
          s"Request headers:\n${request.headers}"
      )

      val amountDue: BigDecimal = (request.body \ "returnDetails" \ "amountOfEclDutyLiable").get.as[BigDecimal]

      val chargeReference: Option[Future[String]] =
        if (amountDue == 0) None else Some(eclReturnReferenceService.getNextChargeReference)

      val result: Option[String] => Result = c =>
        Ok(
          Json.toJson(
            SubmitEclReturnResponse(
              processingDate = Instant.now(clock),
              chargeReference = c
            )
          )
        )

      chargeReference match {
        case Some(fChargeReference) =>
          fChargeReference.map(ref => result(Some(ref)))
        case None                   =>
          Future.successful(result(None))
      }
    }

}
