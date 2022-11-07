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
import play.api.mvc.Result
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.models.EclStubData

import scala.concurrent.Future

class FinancialDetailsControllerSpec extends SpecBase {

  val controller = new FinancialDetailsController(
    cc
  )

  private val idType     = "zecl"
  private val regimeType = "ECL"

  "getFinancialDetails" should {

    "return 200 OK with no financial details JSON when the idNumber ends in '001' or '002'" in {
      Seq("XMECL0000000001", "XMECL0000000002").foreach { idNumber =>
        val result: Future[Result] =
          controller.getFinancialDetails(idType, idNumber, regimeType)(fakeRequest)

        status(result)        shouldBe OK
        contentAsJson(result) shouldBe Json.parse("{}")
      }
    }

    "return 200 OK with financial details JSON containing a payment that is due when the idNumber ends in '003'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000003", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(EclStubData.financialDetailsWithPaymentDue)
    }

    "return 400 BAD_REQUEST with an INVALID_IDTYPE code when the idNumber ends in '400'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000400", regimeType)(fakeRequest)

      status(result) shouldBe BAD_REQUEST
      contentAsJson(result) shouldBe Json.obj(
        "code" -> "INVALID_IDTYPE",
        "reason" -> "Submission has not passed validation. Invalid parameter idType."
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with a SERVER_ERROR code when the idNumber ends in '500'" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000500", regimeType)(fakeRequest)

      status(result) shouldBe INTERNAL_SERVER_ERROR
      contentAsJson(result) shouldBe Json.obj(
        "code" -> "SERVER_ERROR",
        "reason" -> "IF is currently experiencing problems that require live service intervention."
      )
    }

    "return 404 NOT_FOUND with a NOT_FOUND code then idNumber ends in any other value" in {
      val result: Future[Result] =
        controller.getFinancialDetails(idType, "XMECL0000000404", regimeType)(fakeRequest)

      status(result) shouldBe NOT_FOUND
      contentAsJson(result) shouldBe Json.obj(
        "code" -> "NO_DATA_FOUND",
        "reason" -> "The remote endpoint has indicated that no data can be found."
      )
    }

  }
}
