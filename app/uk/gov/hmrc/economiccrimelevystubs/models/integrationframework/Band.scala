/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.economiccrimelevystubs.models.integrationframework

import play.api.libs.json.{Format, JsError, JsResult, JsString, JsSuccess, JsValue}

sealed trait Band

object Band {
  case object Small extends Band
  case object Medium extends Band
  case object Large extends Band
  case object VeryLarge extends Band

  implicit val format: Format[Band] = new Format[Band] {
    override def reads(json: JsValue): JsResult[Band] = json.validate[String] match {
      case JsSuccess(value, _) =>
        value match {
          case "Small"     => JsSuccess(Small)
          case "Medium"    => JsSuccess(Medium)
          case "Large"     => JsSuccess(Large)
          case "VeryLarge" => JsSuccess(VeryLarge)
          case s           => JsError(s"$s is not a valid Band")
        }
      case e: JsError          => e
    }

    override def writes(o: Band): JsValue = JsString(o.toString)
  }
}
