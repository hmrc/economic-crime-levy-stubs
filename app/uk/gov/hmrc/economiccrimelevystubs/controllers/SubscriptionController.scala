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
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.economiccrimelevystubs.data.GetSubscriptionData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{CreateSubscriptionResponse, CreateSubscriptionResponsePayload}
import uk.gov.hmrc.economiccrimelevystubs.services.EclRegistrationReferenceService
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.{Clock, Instant}
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class SubscriptionController @Inject() (
  cc: ControllerComponents,
  eclRegistrationReferenceService: EclRegistrationReferenceService,
  clock: Clock
)(implicit ec: ExecutionContext)
    extends BackendController(cc)
    with Logging {

  def createSubscription(safeId: String): Action[JsValue] =
    Action.async(parse.json) { implicit request =>
      logger.info(
        s"Received create subscription request\n\n" +
          s"SAFE ID: $safeId\n\n" +
          s"Request body:\n${Json.prettyPrint(request.body)}\n\n" +
          s"Request headers:\n${request.headers}"
      )

      eclRegistrationReferenceService.getNextEclReference.map(registrationReference =>
        Ok(
          Json.toJson(
            CreateSubscriptionResponse(success =
              CreateSubscriptionResponsePayload(
                processingDate = Instant.now(clock),
                eclReference = registrationReference
              )
            )
          )
        )
      )
    }

  def getSubscription(eclRegistrationReference: String): Action[AnyContent] = Action { _ =>
    eclRegistrationReference.takeRight(3) match {
      case "001" => Ok(Json.toJson(GetSubscriptionData.validIndividualSubscription(eclRegistrationReference)))
      case "007" => Ok(Json.toJson(GetSubscriptionData.validIndividualSubscription(eclRegistrationReference)))
      case "002" => Ok(Json.toJson(GetSubscriptionData.validOrganisationSubscription(eclRegistrationReference)))
      case "018" => Ok(Json.toJson(GetSubscriptionData.validOrganisationSubscription(eclRegistrationReference)))
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
            "code"   -> "006",
            "reason" -> "There are no successfully processed forms for this customer"
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
}
