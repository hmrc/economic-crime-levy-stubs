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
import uk.gov.hmrc.economiccrimelevystubs.utils.EclTaxYear._

import java.time._

object ObligationStubData {

  private def periodKey(periodFrom: LocalDate): String = s"${periodFrom.getYear.toString.takeRight(2)}XY"

  def fulfilledOnTimeObligation: ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(),
            inboundCorrespondenceToDate = eclPeriodTo(),
            inboundCorrespondenceDateReceived = Some(eclPeriodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(),
            periodKey = periodKey(eclPeriodFrom())
          )
        )
      )
    )
  )

  def openDueObligation(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(),
            inboundCorrespondenceToDate = eclPeriodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(),
            periodKey = periodKey(eclPeriodFrom())
          )
        )
      )
    )
  )

  def fulfilledOnTimeAndOpenDueObligations(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentFyStartYear - 1),
            inboundCorrespondenceToDate = eclPeriodTo(currentFyStartYear - 1),
            inboundCorrespondenceDateReceived = Some(eclPeriodTo(currentFyStartYear - 1).plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(eclPeriodFrom(currentFyStartYear - 1))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(),
            inboundCorrespondenceToDate = eclPeriodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(),
            periodKey = periodKey(eclPeriodFrom())
          )
        )
      )
    )
  )

  def openOverdueAndDueObligations(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentFyStartYear - 1),
            inboundCorrespondenceToDate = eclPeriodTo(currentFyStartYear - 1),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(eclPeriodFrom(currentFyStartYear - 1))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(),
            inboundCorrespondenceToDate = eclPeriodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(),
            periodKey = periodKey(eclPeriodFrom())
          )
        )
      )
    )
  )

  def overdueSubmittedDueObligations(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 2),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(eclPeriodFrom(currentYear - 1))
          ),
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 3),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 3),
            inboundCorrespondenceDateReceived = Some(dueDate(yearDue = 2021)),
            inboundCorrespondenceDueDate = dueDate(currentYear - 2),
            periodKey = periodKey(eclPeriodFrom(currentYear - 2))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 1),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 1),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear),
            periodKey = periodKey(eclPeriodFrom(currentYear))
          )
        )
      )
    )
  )

  def fulfilledObligation(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 2),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(eclPeriodFrom(currentYear - 1))
          )
        )
      )
    )
  )

  def multipleFulfilledObligations(): ObligationData = ObligationData(
    obligations = Seq(
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 3),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 3),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear - 2),
            periodKey = periodKey(eclPeriodFrom(currentYear - 3))
          )
        )
      ),
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 2),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(eclPeriodFrom(currentYear - 2))
          )
        )
      ),
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = eclPeriodFrom(currentYear - 1),
            inboundCorrespondenceToDate = eclPeriodTo(currentYear - 1),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(currentYear),
            periodKey = periodKey(eclPeriodFrom(currentYear - 1))
          )
        )
      )
    )
  )
}
