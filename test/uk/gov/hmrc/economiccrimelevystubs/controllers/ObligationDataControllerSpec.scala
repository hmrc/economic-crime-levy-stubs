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

class ObligationDataControllerSpec extends SpecBase {

  val controller = new ObligationDataController(
    cc
  )

  "getObligationData" should {

    "return 200 OK with obligation data serialized as JSON containing a fulfilled obligation when passed an idNumber of 'fo-id'" in {
      val result: Future[Result] =
        controller.getObligationData("zecl", "fo-id", "ECL")(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(EclStubData.fulfilledObligationData)
    }

    "return 200 OK with obligation data serialized as JSON containing an open obligation when passed an idNumber other than 'fo-id'" in {
      val result: Future[Result] =
        controller.getObligationData("zecl", "ecl-id", "ECL")(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(EclStubData.openObligationData)
    }

  }
}
