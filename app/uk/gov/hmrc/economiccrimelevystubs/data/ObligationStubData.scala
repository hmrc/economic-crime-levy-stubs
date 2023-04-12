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

package uk.gov.hmrc.economiccrimelevystubs.data

import uk.gov.hmrc.economiccrimelevystubs.models.des._
import uk.gov.hmrc.time.TaxYear

import java.time._

object ObligationStubData {

  private val startOfPreviousTaxYear = TaxYear.current.previous.starts
  private val endOfPreviousTaxYear   = TaxYear.current.previous.finishes
  private val eclDueDate             = MonthDay.of(9, 30).atYear(TaxYear.current.previous.finishes.getYear)

  val fulfilledObligationData: ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = startOfPreviousTaxYear,
            inboundCorrespondenceToDate = endOfPreviousTaxYear,
            inboundCorrespondenceDateReceived =
              Some(LocalDate.ofInstant(Instant.now().minus(Duration.ofDays(1)), ZoneOffset.UTC)),
            inboundCorrespondenceDueDate = eclDueDate,
            periodKey = startOfPreviousTaxYear.getYear.toString
          )
        )
      )
    )
  )

  def openObligationData(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = startOfPreviousTaxYear,
            inboundCorrespondenceToDate = endOfPreviousTaxYear,
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = eclDueDate,
            periodKey = startOfPreviousTaxYear.getYear.toString
          )
        )
      )
    )
  )

}
