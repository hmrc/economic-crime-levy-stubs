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

import uk.gov.hmrc.economiccrimelevystubs.repositories.SequenceRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class EclReturnReferenceService @Inject() (sequenceRepository: SequenceRepository)(implicit
  ec: ExecutionContext
) {

  private val eclReturnReferenceKey = "eclReturnReference"

  def getNextEclReference(periodKey: String): Future[String] =
    sequenceRepository.getNextSequenceId(eclReturnReferenceKey).map { id =>
      s"$periodKey${"%010d".format(id)}"
    }

}
