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

package uk.gov.hmrc.economiccrimelevystubs.repositories

import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.inc
import org.mongodb.scala.model.{FindOneAndUpdateOptions, ReturnDocument}
import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.mongo.MongoComponent
import uk.gov.hmrc.mongo.play.json.PlayMongoRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SequenceRepository @Inject() (
  mongoComponent: MongoComponent
)(implicit ec: ExecutionContext)
    extends PlayMongoRepository[MongoSequence](
      collectionName = "sequences",
      mongoComponent = mongoComponent,
      domainFormat = MongoSequence.format,
      indexes = Seq.empty
    ) {

  def getNextSequenceId(key: String): Future[Long] =
    collection
      .findOneAndUpdate(
        filter = equal("_id", key),
        update = inc("sequence", 1),
        FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
      )
      .toSingle()
      .toFuture()
      .map(_.sequence)

}

case class MongoSequence(_id: String, sequence: Long)

object MongoSequence {
  val format: OFormat[MongoSequence] = Json.format
}
