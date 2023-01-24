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
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.CreateSubscriptionResponse
import uk.gov.hmrc.economiccrimelevystubs.services.EclRegistrationReferenceService

import java.time.{Clock, Instant, ZoneId}
import scala.concurrent.Future

class SubscriptionControllerSpec extends SpecBase {

  val mockEclRegistrationReferenceService: EclRegistrationReferenceService = mock[EclRegistrationReferenceService]

  private val now              = Instant.now
  private val stubClock: Clock = Clock.fixed(now, ZoneId.systemDefault)

  val controller = new SubscriptionController(
    cc,
    mockEclRegistrationReferenceService,
    stubClock
  )

  private val idType = "SAFE"
  private val regime = "ECL"

  "createSubscription" should {
    "return 200 OK with the processing date and ECL registration reference" in {
      val eclReference = "XMECL0000000001"

      when(mockEclRegistrationReferenceService.getNextEclReference).thenReturn(Future.successful(eclReference))

      val result: Future[Result] =
        controller.createSubscription(regime, idType, "X00000000000001")(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        CreateSubscriptionResponse(
          processingDate = now,
          eclReference = eclReference
        )
      )
    }
  }

}
