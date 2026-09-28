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
import play.api.mvc.*
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubDataHip
import uk.gov.hmrc.economiccrimelevystubs.models.hip.*
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.*
import uk.gov.hmrc.economiccrimelevystubs.utils.Logger.logger
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import java.time.LocalDate
import javax.inject.{Inject, Singleton}

@Singleton
class FinancialDetailsHipController @Inject() (
  cc: ControllerComponents
) extends BackendController(cc) {

  def getFinancialDetailsHip: Action[AnyContent] = Action { implicit request =>
    (validateRequestJsonBody(request), validateRequestHeaders(request)) match {
      case (Left(errorResult), _)         =>
        errorResult
      case (_, Left(errorResult))         =>
        errorResult
      case (Right(requestBody), Right(_)) =>
        requestBody.idNumber.takeRight(3) match {
          case "003" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataDueObligation()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "004" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "005" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "006" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPartiallyPaidResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "007" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "008" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataOverpaidObligationSinglePayment()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "009" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataOverpaidObligationMultiplePayments()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "010" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationPartialPaidInterestResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "011" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationPaidInterestResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "012" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationWithInterestResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "013" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataRefundForOverpayment()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "014" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationWithoutInterestDocumentFormed()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "015" => Created(Json.toJson(FinancialStubDataHip.financialDataUnexpectedDocumentType()))
          case "016" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationWithReversalLineItemResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "017" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidChargeWithInterestAndReversalResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "018" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "019" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "020" => Created(Json.toJson(FinancialStubDataHip.financialDataClearingDocument()))
          case "022" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationResponse()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "023" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataDueObligation()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "024" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataPaymentOnAccount()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "025" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataThirdLatePaymentPenalty()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "026" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataSecondLateFilingPenalty()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "027" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataFirstLatePaymentPenalty()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "028" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataFirstLateFilingPenalty()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "029" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataDueObligationWithPenalties()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          // batch call
          case "030" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatch1()))
            } else {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatch2()))
            }
          case "031" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatchSuccess()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "032" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            } else {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatchSuccess()))
            }
          case "033" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-09-16T10:00:00Z",
                    "code"           -> "003",
                    "text"           -> "Request could not be processed"
                  )
                )
              )
            } else {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatchSuccess()))
            }
          case "034" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              Created(Json.toJson(FinancialStubDataHip.financialDataHipBatchSuccess()))
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-09-16T10:00:00Z",
                    "code"           -> "003",
                    "text"           -> "Request could not be processed"
                  )
                )
              )
            }
          case "035" =>
            if (requestBody.selectionCriteria.flatMap(_.dateRange).map(_.dateTo).contains(LocalDate.now.toString)) {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            } else {
              UnprocessableEntity(
                Json.obj(
                  "errors" -> Json.obj(
                    "processingDate" -> "2025-10-16T10:00:00Z",
                    "code"           -> "018",
                    "text"           -> "No Data Identified"
                  )
                )
              )
            }
          case "036" => Created(Json.toJson(FinancialStubDataHip.financialDataReversalNotShown()))
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
          case "433" =>
            UnprocessableEntity(
              Json.obj(
                "errors" -> Json.obj(
                  "processingDate" -> "2025-09-16T10:00:00Z",
                  "code"           -> "018",
                  "text"           -> "No Data Identified"
                )
              )
            )
          case "434" =>
            UnprocessableEntity(
              Json.obj(
                "errors" -> Json.obj(
                  "processingDate" -> "2025-09-16T10:00:00Z",
                  "code"           -> "003",
                  "text"           -> "Request could not be processed"
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

  private def validateRequestJsonBody(request: Request[AnyContent]): Either[Result, FinancialDetailsRequest] =
    request.body.asJson
      .toRight {
        logger.error("[GetFinancialDetailsHipController][getFinancialDetails] - No Json body received")
        BadRequest("No Json body received")
      }
      .flatMap(_.asOpt[FinancialDetailsRequest].toRight {
        logger.error("[GetFinancialDetailsHipController][getFinancialDetails] - Invalid Json body format")
        BadRequest("Invalid Json body format")
      })

  private def validateRequestHeaders(request: Request[AnyContent]): Either[Result, Unit] = {
    val requiredHeaders = List(
      "correlationid",
      "X-Originating-System",
      "X-Receipt-Date",
      "X-Transmitting-System"
    )
    val missingHeaders  = requiredHeaders.filterNot(request.headers.get(_).isDefined)

    missingHeaders.headOption match {
      case Some(missing) =>
        val error = s"Missing required header: $missing"
        logger.error(s"[GetFinancialDetailsHipController][getFinancialDetails] - Header validation failed: $error")
        Left(BadRequest(error))
      case None          =>
        Right(())
    }
  }
}
