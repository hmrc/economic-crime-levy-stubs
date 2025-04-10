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

import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, ControllerComponents}
import uk.gov.hmrc.economiccrimelevystubs.data.ObligationStubData
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.{Inject, Singleton}

@Singleton
class ObligationDataController @Inject() (
  cc: ControllerComponents
) extends BackendController(cc) {

  def getObligationData(idType: String, idNumber: String, regimeType: String): Action[AnyContent] = Action { _ =>
    idNumber.takeRight(3) match {
      case "001" => Ok(Json.toJson(ObligationStubData.openDueObligation()))
      case "002" => Ok(Json.toJson(ObligationStubData.openOverdueAndDueObligations()))
      case "003" => Ok(Json.toJson(ObligationStubData.fulfilledOnTimeObligation))
      case "004" => Ok(Json.toJson(ObligationStubData.fulfilledOnTimeAndOpenDueObligations()))
      case "005" => Ok(Json.toJson(ObligationStubData.overdueSubmittedDueObligations()))
      case "006" => Ok(Json.toJson(ObligationStubData.fulfilledObligation()))
      case "007" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "008" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "010" => Ok(Json.toJson(ObligationStubData.fulfilledOnTimeAndOpenDueObligations()))
      case "012" => Ok(Json.toJson(ObligationStubData.fulfilledOnTimeAndOpenDueObligations()))
      case "010" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "011" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "012" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "013" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "014" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "015" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "016" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "017" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "018" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "019" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "020" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "021" => Ok(Json.toJson(ObligationStubData.openDueObligation()))
      case "022" => Ok(Json.toJson(ObligationStubData.multipleFulfilledObligations()))
      case "023" => Ok(Json.toJson(ObligationStubData.overdueSubmittedDueObligations()))
      case "400" =>
        BadRequest(
          Json.obj(
            "code"   -> "INVALID_IDTYPE",
            "reason" -> "Submission has not passed validation. Invalid parameter idType."
          )
        )
      case "404" =>
        NotFound(
          Json.obj(
            "code"   -> "NOT_FOUND",
            "reason" -> "The remote endpoint has indicated that no associated data found."
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
