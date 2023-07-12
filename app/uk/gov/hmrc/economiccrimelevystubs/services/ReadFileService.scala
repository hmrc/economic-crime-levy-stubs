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

import java.io.InputStream
import javax.inject.Singleton
import scala.io.Source
import scala.io.Source.fromFile

@Singleton
class ReadFileService {
  private val BASE_PATH                                   = "/resources"
  def readFile(fileName: String): JsValue = {
    val json = findResource(s"$BASE_PATH/$fileName.json")
    Json.parse(json)
  }
  private def findResource(path: String): String = {
    val resource = getClass.getResourceAsStream(path)
    readStreamToString(resource)
  }
  private def readStreamToString(is: InputStream): String =
    try Source.fromInputStream(is).mkString
    finally is.close()
}
