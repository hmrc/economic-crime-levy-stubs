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
import uk.gov.hmrc.economiccrimelevystubs.data.GetSubscriptionData
import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{CreateSubscriptionResponse, CreateSubscriptionResponsePayload}
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

  "createSubscription" should {
    "return 200 OK with the processing date and ECL registration reference" in {
      val eclReference = "XMECL0000000001"

      when(mockEclRegistrationReferenceService.getNextEclReference).thenReturn(Future.successful(eclReference))

      val result: Future[Result] =
        controller.createSubscription("XA0000000000001")(
          fakeRequestWithJsonBody(Json.obj("foo" -> "bar"))
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        CreateSubscriptionResponse(success =
          CreateSubscriptionResponsePayload(
            processingDate = now,
            eclReference = eclReference
          )
        )
      )
    }
  }

  "getSubscription" should {
    "return 200 OK with response for individuals if reference ends with '001' " in {
      val eclReference = "XMECL0000000001"

      val result: Future[Result] = controller.getSubscription(eclReference)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(GetSubscriptionData.validIndividualSubscription(eclReference))
    }

    "return 200 OK with response for organizations if reference ends with '002'" in {
      val eclReference = "XMECL0000000002"

      val result: Future[Result] = controller.getSubscription(eclReference)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(GetSubscriptionData.validOrganisationSubscription(eclReference))
    }

    "return 422 UnprocessableEntity with code and message if reference ends with '422'" in {
      val eclReference = "XMECL000000422"

      val result: Future[Result] = controller.getSubscription(eclReference)(fakeRequest)

      status(result)        shouldBe UNPROCESSABLE_ENTITY
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "006",
        "reason" -> "There are no successfully processed forms for this customer"
      )
    }
  }
}
