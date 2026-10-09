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
import uk.gov.hmrc.economiccrimelevystubs.models.hip.HipPlatformErrors.*
import uk.gov.hmrc.economiccrimelevystubs.models.hip.{Hip422Error, HipInner422Err, HipSubmitEclReturnResponse, HipSuccessWrapper}
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{GetEclReturnSubmissionResponse, SubmitEclReturnResponse}
import uk.gov.hmrc.economiccrimelevystubs.services.ChargeReferenceService
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.{Clock, Instant}
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReturnHipController @Inject() (
  cc: ControllerComponents,
  eclReturnReferenceService: ChargeReferenceService,
  clock: Clock
)(implicit ec: ExecutionContext)
    extends BackendController(cc)
    with Logging {

  private val logID = "D82EBAB67AC6D7565C0682CA91BDC577"

  private val hipError: PartialFunction[String, Result] = {
    case "400" =>
      BadRequest(
        Json.toJson(
          HipErrorWrapper(
            Origin.HoD,
            HipSystemErrorObject(HipError("400", logID, "Submission has not passed validation."))
          )
        )
      )
    case "422" =>
      UnprocessableEntity(
        Json.toJson(Hip422Error(HipInner422Err("005", "2022-01-31T09:26:17Z", "No Form Bundle found")))
      )
    case "500" =>
      InternalServerError(
        Json.toJson(HipErrorWrapper(Origin.HoD, HipSystemErrorObject(HipError("500", logID, "Internal Server Error"))))
      )
    case "503" =>
      ServiceUnavailable(
        Json.toJson(
          HipErrorWrapper(Origin.HIP, HipFailuresErrorArray(Array(HipFailure("Type of Failure", "Reason for Failure"))))
        )
      )
  }

  def getReturn(periodKey: String, eclRegistrationReference: String): Action[AnyContent] = Action { _ =>
    logger.info("Received GET return request")

    val success: PartialFunction[String, Result] = { case "007" | "018" | "019" =>
      getEclSuccessRequestBuilder(periodKey, eclRegistrationReference)
    }
    success.orElse(hipError).applyOrElse(eclRegistrationReference.takeRight(3), _ => InternalServerError)
  }

  def getEclSuccessRequestBuilder(periodKey: String, eclRegistrationReference: String): Result =
    Ok(
      Json.toJson(
        HipSuccessWrapper[GetEclReturnSubmissionResponse](
          ReturnStubData.validReturnMedium(periodKey, eclRegistrationReference)
        )
      )
    )

  def submitReturn(eclRegistrationReference: String): Action[JsValue] =
    Action.async(parse.json) { implicit request =>
      logger.info("Received POST return request")

      hipError
        .lift(eclRegistrationReference.takeRight(3))
        .fold(submitSuccess(request.body))(Future.successful)
    }

  private def submitSuccess(body: JsValue): Future[Result] = {
    val amountDue: BigDecimal = (body \ "returnDetails" \ "amountOfEclDutyLiable").get.as[BigDecimal]

    val chargeReference: Option[Future[String]] =
      if (amountDue == 0) None else Some(eclReturnReferenceService.getNextChargeReference)

    val result: Option[String] => Result = c =>
      Created(
        Json.toJson(
          HipSuccessWrapper[HipSubmitEclReturnResponse](
            HipSubmitEclReturnResponse(
              processingDate = Instant.now(clock),
              eclReference = "XMECL1114808092",
              submissionId = Some("789124231021"),
              chargeReference = c,
              amount = 10000.0,
              dueDate = "2022-04-01"
            )
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
