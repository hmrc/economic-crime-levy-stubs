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

import play.api.mvc.Result
import play.api.test.FakeRequest
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase

import scala.concurrent.Future

class EchoControllerSpec extends SpecBase {

  val controller = new EchoController(cc)

  "echo" should {
    "print out request parameters" in {
      val paramName  = "name"
      val paramValue = "value"

      val result: Future[Result] =
        controller.echo()(
          FakeRequest("", s"/?$paramName=$paramValue")
        )

      status(result) shouldBe OK

      val content = contentAsString(result)
      content.contains(paramName)  shouldBe true
      content.contains(paramValue) shouldBe true
    }
  }

}
