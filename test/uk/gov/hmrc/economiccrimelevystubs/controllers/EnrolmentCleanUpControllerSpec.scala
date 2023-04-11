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

import play.api.libs.json.{Json, OFormat}
import play.api.mvc.Result
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.services.EclRegistrationReferenceService

import scala.concurrent.Future

class EnrolmentCleanUpControllerSpec extends SpecBase {

  val mockEclRegistrationReferenceService: EclRegistrationReferenceService = mock[EclRegistrationReferenceService]

  val controller = new EnrolmentCleanUpController(cc, mockEclRegistrationReferenceService)

  "referencesForCleanup" should {
    "return 200 OK with references for removal JSON containing list of references that will be removed" in {
      val eclReference = "XMECL0000000001"
      when(mockEclRegistrationReferenceService.getPreviousReferences).thenReturn(Future.successful(List(eclReference)))

      val result: Future[Result] =
        controller.referencesForCleanup()(
          fakeRequest
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        List(eclReference)
      )
    }

    "return 200 OK with references for removal JSON containing empty list" in {
      when(mockEclRegistrationReferenceService.getPreviousReferences).thenReturn(Future.successful(List()))

      val result: Future[Result] =
        controller.referencesForCleanup()(
          fakeRequest
        )

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(List.empty[String])
    }
  }

}
