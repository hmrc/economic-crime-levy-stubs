/*
 * Copyright 2026 HM Revenue & Customs
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

package uk.gov.hmrc.economiccrimelevystubs.models.hip

import play.api.libs.json.{JsValue, Json}

object ErrorResponses {

  val badRequestJson: JsValue = Json.parse(
    """
      |{
      |  "origin": "HIP",
      |  "response": {
      |    "failures": [
      |      {
      |        "type": "Type of Failure",
      |        "reason": "Reason for Failure"
      |      }
      |    ]
      |  }
      |}""".stripMargin
  )

  val unprocessableEntityJson: JsValue = Json.parse(
    """
      |{
      |  "error": {
      |    "errorId": "006",
      |    "processingDate": "2022-01-31T09:26:17Z",
      |    "text": "There are no successfully processed forms for this customer"
      |  }
      |}""".stripMargin
  )

  val serviceUnavailableJson: JsValue = Json.parse(
    """
      |{
      |  "origin": "HIP",
      |  "response": {
      |    "failures": [
      |      {
      |        "type": "string",
      |        "reason": "string"
      |      }
      |    ]
      |  }
      |}""".stripMargin
  )

  val internalServerErrorJson: JsValue = Json.parse(
    """
      |{
      |  "origin": "HoD",
      |  "response": {
      |    "error": {
      |      "code": "500",
      |      "logID": "D82EBAB67AC6D7565C0682CA91BDC577",
      |      "message": "Internal Server Error"
      |    }
      |  }
      |}""".stripMargin
  )

}
