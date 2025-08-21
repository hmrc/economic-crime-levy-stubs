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

import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AnyContentAsJson, Result}
import play.api.test.FakeRequest
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.data.FinancialStubDataHip
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.FinancialDataErrorResponse

import scala.concurrent.Future

class FinancialDetailsHipControllerSpec extends SpecBase {

  val controller = new FinancialDetailsHipController(
    cc
  )

  val validHeaders = Map(
    "correlationid"         -> "f0bd1f32-de51-45cc-9b18-0520d6e3ab1a",
    "X-Originating-System"  -> "MDTP",
    "X-Receipt-Date"        -> "2024-10-28T05:30:00Z",
    "X-Transmitting-System" -> "HIP"
  )

  def requestWithHeadersAndBody(headers: Map[String, String], body: JsValue): FakeRequest[AnyContentAsJson] =
    FakeRequest("POST", "/etmp/RESTAdapter/cross-regime/taxpayer/financial-data/query")
      .withHeaders(headers.toSeq: _*)
      .withJsonBody(body)

  "getFinancialDetailsHip" should {

    "return 201 CREATED when idNumber ends in '003'" in {

      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                 |{
                                                 | "taxRegime": "ECL",
                                                 | "taxpayerInformation": {
                                                 |   "idType": "ZECL",
                                                 |   "idNumber": "003"
                                                 | }
                                                 |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result) shouldBe CREATED

      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataDueObligation()
      )
    }

    "return 201 CREATED when idNumber ends in '004'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                    |{
                                                                    | "taxRegime": "ECL",
                                                                    | "taxpayerInformation": {
                                                                    |   "idType": "ZECL",
                                                                    |   "idNumber": "004"
                                                                    | }
                                                                    |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataOverdueObligationResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '005'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "005"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidObligationResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '006'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "006"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPartiallyPaidResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '007'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "007"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '008'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "008"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataOverpaidObligationSinglePayment()
      )
    }

    "return 201 CREATED when idNumber ends in '009'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "009"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataOverpaidObligationMultiplePayments()
      )
    }

    "return 201 CREATED when idNumber ends in '010'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "010"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidObligationPartialPaidInterestResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '011'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "011"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidObligationPaidInterestResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '012'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "012"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataOverdueObligationWithInterestResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '013'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "013"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataRefundForOverpayment()
      )
    }

    "return 201 CREATED when idNumber ends in '014'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "014"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataOverdueObligationWithoutInterestDocumentFormed()
      )
    }

    "return 201 CREATED when idNumber ends in '015'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "015"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataUnexpectedDocumentType()
      )
    }

    "return 201 CREATED when idNumber ends in '016'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "016"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result) shouldBe CREATED

      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidObligationWithReversalLineItemResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '017'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "017"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result) shouldBe CREATED

      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidChargeWithInterestAndReversalResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '018'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "018"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '019'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "019"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaidPartiallyPaidOverdueResponse()
      )
    }

    "return 201 CREATED when idNumber ends in '020'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "020"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataClearingDocument()
      )
    }

    "return 201 CREATED when idNumber ends in '024'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "024"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataPaymentOnAccount()
      )
    }

    "return 201 CREATED when idNumber ends in '025'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "025"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataThirdLatePaymentPenalty()
      )
    }

    "return 201 CREATED when idNumber ends in '026'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "026"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataSecondLateFilingPenalty()
      )
    }

    "return 201 CREATED when idNumber ends in '027'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "027"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataFirstLatePaymentPenalty()
      )
    }

    "return 201 CREATED when idNumber ends in '028'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "028"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

      status(result)        shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        FinancialStubDataHip.financialDataFirstLateFilingPenalty()
      )
    }

    "return 400 BAD_REQUEST with an INVALID_IDTYPE code when the idNumber ends in '400'" in {
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "400"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

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
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "404"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

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
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "422"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

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
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "500"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

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
      val jsonBodyToSubmit: JsValue                     = Json.parse("""
                                                                       |{
                                                                       | "taxRegime": "ECL",
                                                                       | "taxpayerInformation": {
                                                                       |   "idType": "ZECL",
                                                                       |   "idNumber": "404"
                                                                       | }
                                                                       |}""".stripMargin)
      val fakeHipRequest: FakeRequest[AnyContentAsJson] = requestWithHeadersAndBody(validHeaders, jsonBodyToSubmit)

      val result: Future[Result] =
        controller.getFinancialDetailsHip(fakeHipRequest)

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
