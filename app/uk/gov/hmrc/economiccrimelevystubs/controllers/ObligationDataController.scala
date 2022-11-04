/*
 * Copyright 2022 HM Revenue & Customs
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

import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.economiccrimelevystubs.models.EclStubData
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.{Duration, Instant}
import javax.inject.{Inject, Singleton}

@Singleton
class ObligationDataController @Inject() (
  cc: ControllerComponents
) extends BackendController(cc) {

  def getObligationData(idType: String, idNumber: String, regimeType: String): Action[AnyContent] = Action { _ =>
    idNumber.takeRight(3) match {
      case "001" => Ok(Json.toJson(EclStubData.openObligationData(Instant.now().plus(Duration.ofDays(1)))))
      case "002" => Ok(Json.toJson(EclStubData.openObligationData(Instant.now().minus(Duration.ofDays(1)))))
      case "003" => Ok(Json.toJson(EclStubData.fulfilledObligationData))
      case "400" =>
        BadRequest(
          Json.obj(
            "code"   -> "INVALID_IDTYPE",
            "reason" -> "Submission has not passed validation. Invalid parameter idNumber."
          )
        )
      case "500" =>
        InternalServerError(
          Json.obj(
            "code"   -> "SERVER_ERROR",
            "reason" -> "DES is currently experiencing problems that require live service intervention."
          )
        )
      case _     =>
        NotFound(
          Json.obj(
            "code"   -> "NOT_FOUND",
            "reason" -> "The remote endpoint has indicated that no associated data found."
          )
        )
    }
  }
}
