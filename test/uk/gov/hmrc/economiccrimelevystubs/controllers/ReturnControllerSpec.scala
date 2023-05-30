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
    "return 200 OK with the processing date and charge reference" in {
      val eclRegistrationReference = "XMECL0000000001"
      val chargeReference          = "XY000000000001"

      when(mockChargeReferenceService.getNextChargeReference).thenReturn(Future.successful(chargeReference))

      val result: Future[Result] =
        controller.submitReturn(eclRegistrationReference)(
          fakeRequestWithJsonBody(Json.obj("foo" -> "bar"))
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubmitEclReturnResponse(
          processingDate = now,
          chargeReference = chargeReference
        )
      )
    }
  }

}
