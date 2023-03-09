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

import org.mockito.ArgumentMatchers.any
import play.api.test.Helpers.await
import uk.gov.hmrc.economiccrimelevystubs.base.SpecBase
import uk.gov.hmrc.economiccrimelevystubs.repositories.SequenceRepository

import scala.concurrent.Future

class ChargeReferenceServiceSpec extends SpecBase {

  val mockSequenceRepository: SequenceRepository = mock[SequenceRepository]

  val service = new ChargeReferenceService(mockSequenceRepository)

  "getNextChargeReference" should {
    "return a correctly formatted charge reference" in {
      when(mockSequenceRepository.getNextSequenceId(any())).thenReturn(Future.successful(1L))

      val prefix = "XY"

      val result: String = await(service.getNextChargeReference)

      result shouldBe s"${prefix}000000000001"
    }
  }

}
