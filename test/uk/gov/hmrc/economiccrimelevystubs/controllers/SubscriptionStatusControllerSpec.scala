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

import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import play.api.libs.json.Json
import play.api.mvc.Result
import org.scalatest.prop.Tables._
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.data.SubscriptionStatusStubData

import scala.concurrent.Future

class SubscriptionStatusControllerSpec extends SpecBase {

  val controller = new SubscriptionStatusController(
    cc
  )

  private val idType = "SAFE"
  private val regime = "ECL"

  "getSubscriptionStatus" should {

    "return 200 OK with subscription status JSON containing a form bundle not found subscription status when the idValue ends in any other value" in {
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, "XA0000000000099")(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubscriptionStatusStubData.eclNotSubscribedData
      )
    }

    "return 200 OK with subscription status JSON containing a successful subscription status with known idValue" in forAll(
      Table(
        "eclReferenceNumber",
        "XA0000000000001",
        "XA0000000000002",
        "XA0000000000003",
        "XA0000000000004",
        "XA0000000000005",
        "XA0000000000006",
        "XA0000000000007",
        "XA0000000000008",
        "XA0000000000009",
        "XA0000000000010",
        "XA0000000000011",
        "XA0000000000012",
        "XA0000000000013",
        "XA0000000000014",
        "XA0000000000015",
        "XA0000000000016",
        "XA0000000000017",
        "XA0000000000018",
        "XA0000000000020"
      )
    ) { (eclReferenceNumber: String) =>
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, eclReferenceNumber)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubscriptionStatusStubData.eclSubscribedData(idType, eclReferenceNumber)
      )
    }

    "return 200 OK with subscription status JSON containing a deregistered subscription status with known idValue" in forAll(
      Table(
        "eclReferenceNumber",
        "XA0000000000019",
        "XA0000000000021"
      )
    ) { (eclReferenceNumber: String) =>
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, eclReferenceNumber)(fakeRequest)

      status(result)        shouldBe OK
      contentAsJson(result) shouldBe Json.toJson(
        SubscriptionStatusStubData.eclDeregisteredData(idType, eclReferenceNumber)
      )
    }

    "return 400 BAD_REQUEST with an INVALID_IDTYPE code when the idValue ends in '400'" in {
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, "XA0000000000400")(fakeRequest)

      status(result)        shouldBe BAD_REQUEST
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "INVALID_IDTYPE",
        "reason" -> "Submission has not passed validation. Invalid parameter idType."
      )
    }

    "return 500 INTERNAL_SERVER_ERROR with a SERVER_ERROR code when the idValue ends in '500'" in {
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, "XA0000000000500")(fakeRequest)

      status(result)        shouldBe INTERNAL_SERVER_ERROR
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "SERVER_ERROR",
        "reason" -> "IF is currently experiencing problems that require live service intervention."
      )
    }

    "return 404 NOT_FOUND with a NO_DATA_FOUND code then idValue ends in '404'" in {
      val result: Future[Result] =
        controller.getSubscriptionStatus(regime, idType, "XA0000000000404")(fakeRequest)

      status(result)        shouldBe NOT_FOUND
      contentAsJson(result) shouldBe Json.obj(
        "code"   -> "NO_DATA_FOUND",
        "reason" -> "The remote endpoint has indicated that the requested resource could  not be found."
      )
    }

  }
}
