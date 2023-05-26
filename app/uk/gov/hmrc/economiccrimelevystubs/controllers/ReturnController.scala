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
import play.api.mvc.{Action, ControllerComponents}
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.SubmitEclReturnResponse
import uk.gov.hmrc.economiccrimelevystubs.services.ChargeReferenceService
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.{Clock, Instant}
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ReturnController @Inject() (
  cc: ControllerComponents,
  eclReturnReferenceService: ChargeReferenceService,
  clock: Clock
)(implicit ec: ExecutionContext)
    extends BackendController(cc)
    with Logging {

  def submitReturn(eclRegistrationReference: String, periodKey: String): Action[JsValue] =
    Action.async(parse.json) { implicit request =>
      logger.info(
        s"Received submit return request\n\n" +
          s"ECL Registration reference: $eclRegistrationReference\n\n" +
          s"Period key: $periodKey\n\n" +
          s"Request body:\n${Json.prettyPrint(request.body)}\n\n" +
          s"Request headers:\n${request.headers}"
      )

      eclReturnReferenceService.getNextChargeReference
        .map(chargeReference =>
          Ok(
            Json.toJson(
              SubmitEclReturnResponse(processingDate = Instant.now(clock), chargeReference = chargeReference)
            )
          )
        )
    }

}
