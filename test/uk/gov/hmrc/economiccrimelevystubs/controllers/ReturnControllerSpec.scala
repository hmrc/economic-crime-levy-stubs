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
import uk.gov.hmrc.economiccrimelevystubs.data.ReturnStubData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.SubmitEclReturnResponse
import uk.gov.hmrc.economiccrimelevystubs.services.ChargeReferenceService

import java.time.{Clock, Instant, ZoneId}
import scala.concurrent.Future

class ReturnControllerSpec extends SpecBase {

  val mockChargeReferenceService: ChargeReferenceService = mock[ChargeReferenceService]

  private val now              = Instant.now
  private val stubClock: Clock = Clock.fixed(now, ZoneId.systemDefault)

  val controller = new ReturnController(
    cc,
    mockChargeReferenceService,
    stubClock
  )

  "submitReturn" should {
    "return 200 OK with the processing date and charge reference when it is not a nil return" in {
      val eclRegistrationReference = "XMECL0000000001"
      val chargeReference          = "XY000000000001"

      when(mockChargeReferenceService.getNextChargeReference).thenReturn(Future.successful(chargeReference))

      val returnJson = Json.obj("returnDetails" -> Json.obj("amountOfEclDutyLiable" -> 10000))

      val result: Future[Result] =
        controller.submitReturn(eclRegistrationReference)(
          fakeRequestWithJsonBody(returnJson)
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubmitEclReturnResponse(
          processingDate = now,
          chargeReference = Some(chargeReference)
        )
      )
    }

    "return 200 OK with the processing date and no charge reference when it is a nil return" in {
      val eclRegistrationReference = "XMECL0000000001"

      val returnJson = Json.obj("returnDetails" -> Json.obj("amountOfEclDutyLiable" -> 0))

      val result: Future[Result] =
        controller.submitReturn(eclRegistrationReference)(
          fakeRequestWithJsonBody(returnJson)
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubmitEclReturnResponse(
          processingDate = now,
          chargeReference = None
        )
      )
    }
  }

  "getReturn" should {

    val periodKey = "22XY"

    "return 200 OK when eclReference ends in '007' with band 'Medium'" in {
      val eclReference = "XMECL0000000007"

      val result: Future[Result] =
        controller.getReturn(periodKey, eclReference)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        ReturnStubData.validReturnMedium(periodKey, eclReference)
      )
    }

    "return 200 OK when eclReference ends in '018' with band 'Medium'" in {
      val eclReference = "XMECL0000000018"

      val result: Future[Result] =
        controller.getReturn(periodKey, eclReference)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        ReturnStubData.validReturnMedium(periodKey, eclReference)
      )
    }

    "return 400 BAD_REQUEST with an INVALID_ECLREFERENCE code when the eclReference ends in '400'" in {
      val result: Future[Result] =
        controller.getReturn(periodKey, "XMECL0000000400")(fakeRequest)

      status(result)        shouldBe BAD_REQUEST
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "INVALID_ECLREFERENCE",
        "reason" -> "Submission has not passed validation. Invalid parameter eclReference."
      )
    }

    "return 422 UNPROCESSABLE_ENTITY with an NOT_FOUND_FORM code when the eclReference ends in '422'" in {
      val result: Future[Result] =
        controller.getReturn(periodKey, "XMECL0000000422")(fakeRequest)

      status(result)        shouldBe UNPROCESSABLE_ENTITY
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "NOT_FOUND_FORM",
        "reason" -> "The remote endpoint has indicated that no successfully processed forms can be found."
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with an SERVER_ERROR code when the eclReference ends in '500'" in {
      val result: Future[Result] =
        controller.getReturn(periodKey, "XMECL0000000500")(fakeRequest)

      status(result)        shouldBe INTERNAL_SERVER_ERROR
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "SERVER_ERROR",
        "reason" -> "IF is currently experiencing problems that require live service intervention."
      )
    }

    "return 503 SERVICE_UNAVAILABLE with an SERVICE_UNAVAILABLE code when the eclReference ends in '503'" in {
      val result: Future[Result] =
        controller.getReturn(periodKey, "XMECL0000000503")(fakeRequest)

      status(result)        shouldBe SERVICE_UNAVAILABLE
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "SERVICE_UNAVAILABLE",
        "reason" -> "Dependent systems are currently not responding."
      )
    }
  }
}
