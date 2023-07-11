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
import uk.gov.hmrc.economiccrimelevystubs.data.ObligationStubData

import scala.concurrent.Future

class ObligationDataControllerSpec extends SpecBase {

  val controller = new ObligationDataController(
    cc
  )

  private val idType     = "zecl"
  private val regimeType = "ECL"

  "getObligationData" should {

    "return 200 OK with obligation data JSON containing an open due obligation when the idNumber ends in '001'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000001", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        ObligationStubData.openDueObligation()
      )
    }

    "return 200 OK with obligation data JSON containing an open due obligation when the idNumber ends in '007'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000007", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        ObligationStubData.multipleFulfilledObligations()
      )
    }
    "return 200 OK with obligation data JSON containing open overdue and due obligations when the idNumber ends in '002'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000002", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        ObligationStubData.openOverdueAndDueObligations()
      )
    }

    "return 200 OK with obligation data JSON containing a fulfilled on time obligation when the idNumber ends in '003'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000003", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(ObligationStubData.fulfilledOnTimeObligation)
    }

    "return 200 OK with obligation data JSON containing fulfilled on time and open due obligations when the idNumber ends in '004'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000004", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(ObligationStubData.fulfilledOnTimeAndOpenDueObligations())
    }

    "return 200 OK with obligation data JSON containing fulfilled on time, open due obligation and overdue obligation when the idNumber ends in '005'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000005", regimeType)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(ObligationStubData.overdueSubmittedDueObligations())
    }

    "return 400 BAD_REQUEST with an INVALID_IDTYPE code when the idNumber ends in '400'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000400", regimeType)(fakeRequest)

      status(result)        shouldBe BAD_REQUEST
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "INVALID_IDTYPE",
        "reason" -> "Submission has not passed validation. Invalid parameter idType."
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with a SERVER_ERROR code when the idNumber ends in '500'" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000500", regimeType)(fakeRequest)

      status(result)        shouldBe INTERNAL_SERVER_ERROR
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "SERVER_ERROR",
        "reason" -> "DES is currently experiencing problems that require live service intervention."
      )
    }

    "return 404 NOT_FOUND with a NOT_FOUND code then idNumber ends in any other value" in {
      val result: Future[Result] =
        controller.getObligationData(idType, "XMECL0000000404", regimeType)(fakeRequest)

      status(result)        shouldBe NOT_FOUND
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "NOT_FOUND",
        "reason" -> "The remote endpoint has indicated that no associated data found."
      )
    }

  }
}
