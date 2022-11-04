/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.economiccrimelevystubs.models

import uk.gov.hmrc.economiccrimelevystubs.models.des._

import java.time.{Duration, Instant}
import java.util.Date

object EclStubData {
  val fulfilledObligationData: ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = Date.from(Instant.parse("1920-02-29T00:00:00.00Z")),
            inboundCorrespondenceToDate = Date.from(Instant.parse("1920-02-29T00:00:00.00Z")),
            inboundCorrespondenceDateReceived = Some(Date.from(Instant.now().minus(Duration.ofDays(1)))),
            inboundCorrespondenceDueDate = Date.from(Instant.now()),
            periodKey = "#001"
          )
        )
      )
    )
  )

  def openObligationData(dueDate: Instant): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = Date.from(Instant.parse("1920-02-29T00:00:00.00Z")),
            inboundCorrespondenceToDate = Date.from(Instant.parse("1920-02-29T00:00:00.00Z")),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = Date.from(dueDate),
            periodKey = "#001"
          )
        )
      )
    )
  )

}
