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

package uk.gov.hmrc.economiccrimelevystubs

import play.api.test.FakeRequest
import uk.gov.hmrc.economiccrimelevystubs.base.ISpecBase
import uk.gov.hmrc.economiccrimelevystubs.controllers.routes

class HelloWorldISpec extends ISpecBase {

  s"GET /$contextPath/hello-world" should {
    "return 200 OK with Hello World" in {
      val result = callRoute(
        FakeRequest(routes.HelloWorldController.helloWorld())
      )

      status(result) shouldBe OK
      contentAsString(result) shouldBe "Hello World"
    }
  }

}
