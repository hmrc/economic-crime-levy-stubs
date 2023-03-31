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
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class EclRegistrationReferenceService @Inject() (sequenceRepository: SequenceRepository)(implicit
  ec: ExecutionContext
) {

  private val eclRegistrationReferenceKey = "eclRegistrationReference"
  private val prefix                      = "XMECL"

  def getNextEclReference: Future[String] =
    sequenceRepository.getNextSequenceId(eclRegistrationReferenceKey).map { id =>
      s"$prefix${"%010d".format(id)}"
    }

  def getPreviousReferences: Future[List[String]] =
    getNextEclReference.map { reference => // TODO: This will not work as getNextEclReference returns a string in the format 'XMECLnnnnnnnnnn' which cannot be converted to an Int
      val referenceRange = List.range(1, reference.toInt)
      val references = new ListBuffer[String]()
      for (elem <- referenceRange)
        references += s"$prefix${"%010d".format(elem)}"
      references.toList
    }
}
