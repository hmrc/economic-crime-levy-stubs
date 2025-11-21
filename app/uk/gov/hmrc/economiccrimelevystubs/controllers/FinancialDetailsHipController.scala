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
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubDataHip
import uk.gov.hmrc.economiccrimelevystubs.models.hip._
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework._
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
          case "003" => Created(Json.toJson(FinancialStubDataHip.financialDataDueObligation()))
          case "004" => Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationResponse()))
          case "005" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationResponse()))
          case "006" => Created(Json.toJson(FinancialStubDataHip.financialDataPartiallyPaidResponse()))
          case "007" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
          case "008" => Created(Json.toJson(FinancialStubDataHip.financialDataOverpaidObligationSinglePayment()))
          case "009" => Created(Json.toJson(FinancialStubDataHip.financialDataOverpaidObligationMultiplePayments()))
          case "010" =>
            Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationPartialPaidInterestResponse()))
          case "011" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationPaidInterestResponse()))
          case "012" => Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationWithInterestResponse()))
          case "013" => Created(Json.toJson(FinancialStubDataHip.financialDataRefundForOverpayment()))
          case "014" =>
            Created(Json.toJson(FinancialStubDataHip.financialDataOverdueObligationWithoutInterestDocumentFormed()))
          case "015" => Created(Json.toJson(FinancialStubDataHip.financialDataUnexpectedDocumentType()))
          case "016" =>
            Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationWithReversalLineItemResponse()))
          case "017" =>
            Created(Json.toJson(FinancialStubDataHip.financialDataPaidChargeWithInterestAndReversalResponse()))
          case "018" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
          case "019" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()))
          case "020" => Created(Json.toJson(FinancialStubDataHip.financialDataClearingDocument()))
          case "022" => Created(Json.toJson(FinancialStubDataHip.financialDataPaidObligationResponse()))
          case "023" => Created(Json.toJson(FinancialStubDataHip.financialDataDueObligation()))
          case "024" => Created(Json.toJson(FinancialStubDataHip.financialDataPaymentOnAccount()))
          case "025" => Created(Json.toJson(FinancialStubDataHip.financialDataThirdLatePaymentPenalty()))
          case "026" => Created(Json.toJson(FinancialStubDataHip.financialDataSecondLateFilingPenalty()))
          case "027" => Created(Json.toJson(FinancialStubDataHip.financialDataFirstLatePaymentPenalty()))
          case "028" => Created(Json.toJson(FinancialStubDataHip.financialDataFirstLateFilingPenalty()))
          case "029" => Created(Json.toJson(FinancialStubDataHip.financialDataDueObligationWithPenalties()))
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
