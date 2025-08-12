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
import play.api.mvc.Result
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubData
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubDataHip
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.FinancialDataErrorResponse

import scala.concurrent.Future

class FinancialDetailsControllerSpec extends SpecBase {

  val controller = new FinancialDetailsController(
    cc,
    appConfig
  )

  private val idType     = "zecl"
  private val regimeType = "ECL"

  "getFinancialDetails" should {

    "return 200 OK when idNumber ends in '003'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000003", regimeType)(fakeRequest)

      status(result) shouldBe OK

      if (appConfig.enable1811HipCall) {
        contentAsJson(result) shouldBe Json.toJson(
          FinancialStubDataHip.financialDataDueObligation()
        )
      } else {
        contentAsJson(result) shouldBe Json.toJson(
          FinancialStubData.financialDataDueObligation()
        )
      }
    }

    "return 200 OK when idNumber ends in '004'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000004", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataOverdueObligationResponse()
      )
    }

    "return 200 OK when idNumber ends in '005'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000005", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidObligationResponse()
      )
    }

    "return 200 OK when idNumber ends in '006'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000006", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPartiallyPaidResponse()
      )
    }

    "return 200 OK when idNumber ends in '007'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000007", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 200 OK when idNumber ends in '008'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000008", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataOverpaidObligationSinglePayment()
      )
    }

    "return 200 OK when idNumber ends in '009'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000009", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataOverpaidObligationMultiplePayments()
      )
    }

    "return 200 OK when idNumber ends in '010'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000010", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidObligationPartialPaidInterestResponse()
      )
    }

    "return 200 OK when idNumber ends in '011'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000011", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidObligationPaidInterestResponse()
      )
    }

    "return 200 OK when idNumber ends in '012'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000012", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataOverdueObligationWithInterestResponse()
      )
    }

    "return 200 OK when idNumber ends in '013'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000013", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataRefundForOverpayment()
      )
    }

    "return 200 OK when idNumber ends in '014'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000014", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataOverdueObligationWithoutInterestDocumentFormed()
      )
    }

    "return 200 OK when idNumber ends in '015'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000015", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataUnexpectedDocumentType()
      )
    }

    "return 200 OK when idNumber ends in '016'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000016", regimeType)(fakeRequest)

      status(result) shouldBe OK
    }

    "return 200 OK when idNumber ends in '017'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000017", regimeType)(fakeRequest)

      status(result) shouldBe OK
    }

    "return 200 OK when idNumber ends in '018'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000018", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 200 OK when idNumber ends in '019'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000019", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 200 OK when idNumber ends in '020'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000020", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataClearingDocument()
      )
    }

    "return 200 OK when idNumber ends in '024'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000024", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataPaymentOnAccount()
      )
    }

    "return 200 OK when idNumber ends in '025'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000025", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataThirdLatePaymentPenalty()
      )
    }

    "return 200 OK when idNumber ends in '026'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000026", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataSecondLateFilingPenalty()
      )
    }

    "return 200 OK when idNumber ends in '027'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000027", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataFirstLatePaymentPenalty()
      )
    }

    "return 200 OK when idNumber ends in '028'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000028", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubData.financialDataFirstLateFilingPenalty()
      )
    }

    "return 400 BAD_REQUEST with an INVALID_IDTYPE code when the idNumber ends in '400'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000400", regimeType)(fakeRequest)

      status(result)        shouldBe BAD_REQUEST
      contentAsJson(result) shouldBe Json.obj(
        "failures" -> Seq(
          FinancialDataErrorResponse(
            "INVALID_REGIME_TYPE",
            "Submission has not passed validation. Invalid parameter taxRegime."
          )
        )
      )
    }

    "return 404 NOT_FOUND with a NOT_FOUND code then idNumber ends in any other value" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000999", regimeType)(fakeRequest)

      status(result)        shouldBe NOT_FOUND
      contentAsJson(result) shouldBe Json.obj(
        "failures" -> Seq(
          FinancialDataErrorResponse(
            "NO_DATA_FOUND",
            "The remote endpoint has indicated that no data can be found."
          )
        )
      )
    }

    "return 422 UNPROCESSABLE_ENTITY with a UNPROCESSABLE_ENTITY code then idNumber ends in '422'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000422", regimeType)(fakeRequest)

      status(result)        shouldBe UNPROCESSABLE_ENTITY
      contentAsJson(result) shouldBe Json.obj(
        "failures" -> Seq(
          FinancialDataErrorResponse(
            "INVALID_ID",
            "The remote endpoint has indicated that reference id is invalid."
          )
        )
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with a SERVER_ERROR code when the idNumber ends in '500'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000500", regimeType)(fakeRequest)

      status(result)        shouldBe INTERNAL_SERVER_ERROR
      contentAsJson(result) shouldBe Json.obj(
        "failures" -> Seq(
          FinancialDataErrorResponse(
            "SERVER_ERROR",
            "IF is currently experiencing problems that require live service intervention."
          )
        )
      )
    }

    "return 404 NOT_FOUND with a NOT_FOUND code then idNumber ends in '404'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000404", regimeType)(fakeRequest)

      status(result)        shouldBe NOT_FOUND
      contentAsJson(result) shouldBe Json.obj(
        "failures" -> Seq(
          FinancialDataErrorResponse(
            "NO_DATA_FOUND",
            "The remote endpoint has indicated that no data can be found."
          )
        )
      )
    }
  }
}
