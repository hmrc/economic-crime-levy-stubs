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

package uk.gov.hmrc.economiccrimelevystubs.services

import play.api.libs.json.{JsValue, Json}
import javax.inject.Singleton
import scala.io.Source.fromFile

@Singleton
class ReadFileService {

  private val BASE_PATH = "resources"

  def readFile(fileName: String): JsValue = {
    val stream = fromFile(s"$BASE_PATH/$fileName.json")
    val lines  =
      try stream.mkString
      finally stream.close()
    Json.parse(lines)
  }

}
