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

import org.mongodb.scala.MongoCollection
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

  /** A TTL index is not desirable as there is only ever 1 record per key (currently 2)
   * that tracks the next available sequence number, hence we do not want the record to expire
   * so that we don't reset and recycle sequence numbers.
   */
  override protected lazy val requiresTtlIndex: Boolean = false

  def getCurrentReference(key: String): Future[Option[Long]] =
    collection
      .find(filter = equal("_id", key))
      .map(_.sequence)
      .headOption()

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
