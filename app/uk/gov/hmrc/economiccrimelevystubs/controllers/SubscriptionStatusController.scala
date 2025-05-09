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
import uk.gov.hmrc.economiccrimelevystubs.data.SubscriptionStatusStubData
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.Inject

class SubscriptionStatusController @Inject() (
  cc: ControllerComponents
) extends BackendController(cc) {
  def getSubscriptionStatus(regime: String, idType: String, idValue: String): Action[AnyContent] = Action { _ =>
    idValue.takeRight(3) match {
      case "001" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "002" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "003" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "004" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "005" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "006" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "007" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "008" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "009" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "010" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "011" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "012" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "013" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "014" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "015" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "016" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "017" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "018" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "019" => Ok(Json.toJson(SubscriptionStatusStubData.eclDeregisteredData(idType, idValue)))
      case "020" => Ok(Json.toJson(SubscriptionStatusStubData.eclSubscribedData(idType, idValue)))
      case "021" => Ok(Json.toJson(SubscriptionStatusStubData.eclDeregisteredData(idType, idValue)))
      case "022" => Ok(Json.toJson(SubscriptionStatusStubData.eclDeregisteredData(idType, idValue)))
      case "023" => Ok(Json.toJson(SubscriptionStatusStubData.eclDeregisteredData(idType, idValue)))
      case "400" =>
        BadRequest(
          Json.obj(
            "code"   -> "INVALID_IDTYPE",
            "reason" -> "Submission has not passed validation. Invalid parameter idType."
          )
        )
      case "500" =>
        InternalServerError(
          Json.obj(
            "code"   -> "SERVER_ERROR",
            "reason" -> "IF is currently experiencing problems that require live service intervention."
          )
        )
      case "404" =>
        NotFound(
          Json.obj(
            "code"   -> "NO_DATA_FOUND",
            "reason" -> "The remote endpoint has indicated that the requested resource could  not be found."
          )
        )
      case _     => Ok(Json.toJson(SubscriptionStatusStubData.eclNotSubscribedData))
    }
  }
}
