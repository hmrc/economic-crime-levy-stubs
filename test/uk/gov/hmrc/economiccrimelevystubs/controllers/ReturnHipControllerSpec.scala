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

import org.mockito.Mockito.when
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.Result
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.data.ReturnStubData
import uk.gov.hmrc.economiccrimelevystubs.models.hip.{HipSubmitEclReturnResponse, HipSuccessWrapper}
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{GetEclReturnSubmissionResponse, SubmitEclReturnResponse}
import uk.gov.hmrc.economiccrimelevystubs.services.ChargeReferenceService

import java.time.{Clock, Instant, ZoneId}
import scala.concurrent.Future

class ReturnHipControllerSpec extends SpecBase {

  val mockChargeReferenceService: ChargeReferenceService = mock[ChargeReferenceService]

  private val now = Instant.now
  private val stubClock: Clock = Clock.fixed(now, ZoneId.systemDefault)

  val controller = new ReturnHipController(
    cc,
    mockChargeReferenceService,
    stubClock
  )

  private val logID = "D82EBAB67AC6D7565C0682CA91BDC577"

  /** bodies are written out literally so the wire format is asserted, not the stub's own models. */
  private val hipErrorCases: Seq[(String, Int, JsValue)] = Seq(
    (
      "400",
      BAD_REQUEST,
      Json.obj(
        "origin"   -> "HoD",
        "response" -> Json.obj(
          "error" -> Json.obj("code" -> "400", "logID" -> logID, "message" -> "Submission has not passed validation.")
        )
      )
    ),
    (
      "422",
      UNPROCESSABLE_ENTITY,
      Json.obj(
        "error" -> Json.obj(
          "errorId"        -> "005",
          "processingDate" -> "2022-01-31T09:26:17Z",
          "text"           -> "No Form Bundle found"
        )
      )
    ),
    (
      "500",
      INTERNAL_SERVER_ERROR,
      Json.obj(
        "origin"   -> "HoD",
        "response" -> Json.obj(
          "error" -> Json.obj("code" -> "500", "logID" -> logID, "message" -> "Internal Server Error")
        )
      )
    ),
    (
      "503",
      SERVICE_UNAVAILABLE,
      Json.obj(
        "origin"   -> "HIP",
        "response" -> Json.obj(
          "failures" -> Json.arr(Json.obj("type" -> "Type of Failure", "reason" -> "Reason for Failure"))
        )
      )
    )
  )

  "submitReturn" should {
    "return 201 CREATED with the full HIP body when it is not a nil return" in {
      val eclRegistrationReference = "XMECL0000000001"
      val chargeReference = "XY000000000001"

      when(mockChargeReferenceService.getNextChargeReference).thenReturn(Future.successful(chargeReference))

      val returnJson = Json.obj("returnDetails" -> Json.obj("amountOfEclDutyLiable" -> 10000))

      val result: Future[Result] =
        controller.submitReturn(eclRegistrationReference)(
          fakeRequestWithJsonBody(returnJson)
        )

      status(result) shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        HipSuccessWrapper[HipSubmitEclReturnResponse](
          HipSubmitEclReturnResponse(
            processingDate = now,
            eclReference = "XMECL1114808092",
            submissionId = Some("789124231021"),
            chargeReference = Some(chargeReference),
            amount = 10000.0,
            dueDate = "2022-04-01"
          )
        )
      )
    }

    "return 201 CREATED with all fields besides charge reference when it is a nil return" in {
      val eclRegistrationReference = "XMECL0000000001"

      val returnJson = Json.obj("returnDetails" -> Json.obj("amountOfEclDutyLiable" -> 0))

      val result: Future[Result] =
        controller.submitReturn(eclRegistrationReference)(
          fakeRequestWithJsonBody(returnJson)
        )

      status(result) shouldBe CREATED
      contentAsJson(result) shouldBe Json.toJson(
        HipSuccessWrapper[HipSubmitEclReturnResponse](
          HipSubmitEclReturnResponse(
            processingDate = now,
            eclReference = "XMECL1114808092",
            submissionId = Some("789124231021"),
            chargeReference = None,
            amount = 10000.0,
            dueDate = "2022-04-01"
          )
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

      status(result) shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        HipSuccessWrapper[GetEclReturnSubmissionResponse](
          ReturnStubData.validReturnMedium(periodKey, eclReference)
        )
      )
    }

    "return 200 OK when eclReference ends in '018' with band 'Medium'" in {
      val eclReference = "XMECL0000000018"

      val result: Future[Result] =
        controller.getReturn(periodKey, eclReference)(fakeRequest)

      status(result) shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        HipSuccessWrapper[GetEclReturnSubmissionResponse](
          ReturnStubData.validReturnMedium(periodKey, eclReference)
        )
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with no body when the eclReference has no matching trigger" in {
      val result: Future[Result] =
        controller.getReturn(periodKey, "XMECL0000000123")(fakeRequest)

      status(result) shouldBe INTERNAL_SERVER_ERROR
    }

    hipErrorCases.foreach { case (suffix, expectedStatus, expectedBody) =>
      s"return $expectedStatus with the error body when the eclReference ends in '$suffix'" in {
        val result: Future[Result] =
          controller.getReturn(periodKey, s"XMECL0000000$suffix")(fakeRequest)

        status(result)        shouldBe expectedStatus
        contentAsJson(result) shouldBe expectedBody
      }
    }
  }

  "submitReturn errors" should {
    hipErrorCases.foreach { case (suffix, expectedStatus, expectedBody) =>
      s"return $expectedStatus with the error body when the eclReference ends in '$suffix'" in {
        val result: Future[Result] =
          controller.submitReturn(s"XMECL0000000$suffix")(
            fakeRequestWithJsonBody(Json.obj("returnDetails" -> Json.obj("amountOfEclDutyLiable" -> 10000)))
          )

        status(result)        shouldBe expectedStatus
        contentAsJson(result) shouldBe expectedBody
      }
    }
  }
}
