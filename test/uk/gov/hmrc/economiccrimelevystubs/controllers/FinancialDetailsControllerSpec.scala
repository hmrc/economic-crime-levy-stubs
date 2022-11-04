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

  }
}
