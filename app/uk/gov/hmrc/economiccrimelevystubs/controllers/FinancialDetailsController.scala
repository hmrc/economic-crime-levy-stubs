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
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework._
import uk.gov.hmrc.economiccrimelevystubs.services.ReadFileService
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.{Inject, Singleton}

@Singleton
class FinancialDetailsController @Inject() (
  cc: ControllerComponents,
  readFileService: ReadFileService
) extends BackendController(cc) {

  def getFinancialDetails(idType: String, idNumber: String, regimeType: String): Action[AnyContent] = Action { _ =>
    idNumber.takeRight(3) match {
      case "003" => Ok(Json.toJson(FinancialStubData.financialDataDueObligation()))
      case "004" => Ok(Json.toJson(FinancialStubData.financialDataOverdueObligationResponse()))
      case "005" => Ok(Json.toJson(FinancialStubData.FinancialDataPaidObligationResponse()))
      case "006" => Ok(Json.toJson(FinancialStubData.FinancialDataPartiallyPaidResponse()))
      case "007" => Ok(readFileService.readFile("FinancialDataPaidPartiallyPaidOverdueResponse"))
      case "008" => Ok(readFileService.readFile("FinancialDataOverpaidObligationSinglePayment"))
      case "009" => Ok(readFileService.readFile("FinancialDataOverpaidObligationMultiplePayments"))
      case "010" => Ok(readFileService.readFile("FinancialDataPaidObligationPartialPaidInterestResponse"))
      case "011" => Ok(readFileService.readFile("FinancialDataPaidObligationPaidInterestResponse"))
      case "012" => Ok(readFileService.readFile("FinancialDataOverdueObligationWithInterestResponse"))
      case "013" => Ok(readFileService.readFile("FinancialDataRefundForOverpayment"))
      case "014" => Ok(readFileService.readFile("FinancialDataOverdueObligationWithoutInterestDocumentFormed"))
      case "015" => Ok(readFileService.readFile("FinancialDataUnexpectedDocumentType"))
      case "016" => Ok(readFileService.readFile("FinancialDataPaidObligationWithReversalLineItemResponse"))
      case "017" => Ok(readFileService.readFile("FinancialDataPaidChargeWithInterestAndReversalResponse"))
      case "400" =>
        BadRequest(
          Json.obj(
            "failures" -> Seq(
              FinancialDataErrorResponse(
                "INVALID_REGIME_TYPE",
                "Submission has not passed validation. Invalid parameter taxRegime."
              )
            )
          )
        )
      case "500" =>
        InternalServerError(
          Json.obj(
            "failures" -> Seq(
              FinancialDataErrorResponse(
                "SERVER_ERROR",
                "IF is currently experiencing problems that require live service intervention."
              )
            )
          )
        )
      case _     =>
        NotFound(
          Json.obj(
            "failures" -> Seq(
              FinancialDataErrorResponse(
                "NO_DATA_FOUND",
                "The remote endpoint has indicated that no data can be found."
              )
            )
          )
        )
    }
  }

}
