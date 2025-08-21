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
import play.api.mvc._
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework._
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.{Inject, Singleton}

@Singleton
class FinancialDetailsController @Inject() (
  cc: ControllerComponents
) extends BackendController(cc) {

  def getFinancialDetails(idType: String, idNumber: String, regimeType: String): Action[AnyContent] = Action { _ =>
    idNumber.takeRight(3) match {
      case "003" => Ok(Json.toJson(FinancialStubData.financialDataDueObligation()))
      case "004" => Ok(Json.toJson(FinancialStubData.financialDataOverdueObligationResponse()))
      case "005" => Ok(Json.toJson(FinancialStubData.financialDataPaidObligationResponse()))
      case "006" => Ok(Json.toJson(FinancialStubData.financialDataPartiallyPaidResponse()))
      case "007" => Ok(Json.toJson(FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()))
      case "008" => Ok(Json.toJson(FinancialStubData.financialDataOverpaidObligationSinglePayment()))
      case "009" => Ok(Json.toJson(FinancialStubData.financialDataOverpaidObligationMultiplePayments()))
      case "010" => Ok(Json.toJson(FinancialStubData.financialDataPaidObligationPartialPaidInterestResponse()))
      case "011" => Ok(Json.toJson(FinancialStubData.financialDataPaidObligationPaidInterestResponse()))
      case "012" => Ok(Json.toJson(FinancialStubData.financialDataOverdueObligationWithInterestResponse()))
      case "013" => Ok(Json.toJson(FinancialStubData.financialDataRefundForOverpayment()))
      case "014" => Ok(Json.toJson(FinancialStubData.financialDataOverdueObligationWithoutInterestDocumentFormed()))
      case "015" => Ok(Json.toJson(FinancialStubData.financialDataUnexpectedDocumentType()))
      case "016" => Ok(Json.toJson(FinancialStubData.financialDataPaidObligationWithReversalLineItemResponse()))
      case "017" => Ok(Json.toJson(FinancialStubData.financialDataPaidChargeWithInterestAndReversalResponse()))
      case "018" => Ok(Json.toJson(FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()))
      case "019" => Ok(Json.toJson(FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()))
      case "020" => Ok(Json.toJson(FinancialStubData.financialDataClearingDocument()))
      case "022" => Ok(Json.toJson(FinancialStubData.financialDataPaidObligationResponse()))
      case "023" => Ok(Json.toJson(FinancialStubData.financialDataDueObligation()))
      case "024" => Ok(Json.toJson(FinancialStubData.financialDataPaymentOnAccount()))
      case "025" => Ok(Json.toJson(FinancialStubData.financialDataThirdLatePaymentPenalty()))
      case "026" => Ok(Json.toJson(FinancialStubData.financialDataSecondLateFilingPenalty()))
      case "027" => Ok(Json.toJson(FinancialStubData.financialDataFirstLatePaymentPenalty()))
      case "028" => Ok(Json.toJson(FinancialStubData.financialDataFirstLateFilingPenalty()))
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
      case "404" =>
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
      case "422" =>
        UnprocessableEntity(
          Json.obj(
            "failures" -> Seq(
              FinancialDataErrorResponse(
                "INVALID_ID",
                "The remote endpoint has indicated that reference id is invalid."
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
