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
            inboundCorrespondenceFromDate = periodFrom(),
            inboundCorrespondenceToDate = periodTo(),
            inboundCorrespondenceDateReceived = Some(periodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(periodTo().getYear),
            periodKey = periodKey(periodFrom())
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
            inboundCorrespondenceFromDate = periodFrom(),
            inboundCorrespondenceToDate = periodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo().getYear),
            periodKey = periodKey(periodFrom())
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
            inboundCorrespondenceFromDate = periodFrom(currentFyStartYear - 1),
            inboundCorrespondenceToDate = periodTo(currentFyStartYear - 1),
            inboundCorrespondenceDateReceived = Some(periodTo(currentFyStartYear - 1).plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(periodTo(currentFyStartYear - 1).getYear),
            periodKey = periodKey(periodFrom(currentFyStartYear - 1))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = periodFrom(),
            inboundCorrespondenceToDate = periodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo().getYear),
            periodKey = periodKey(periodFrom())
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
            inboundCorrespondenceFromDate = periodFrom(currentFyStartYear - 1),
            inboundCorrespondenceToDate = periodTo(currentFyStartYear - 1),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo(currentFyStartYear - 1).getYear),
            periodKey = periodKey(periodFrom(currentFyStartYear - 1))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = periodFrom(),
            inboundCorrespondenceToDate = periodTo(),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo().getYear),
            periodKey = periodKey(periodFrom())
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
            inboundCorrespondenceFromDate = periodFrom(currentYear - 2),
            inboundCorrespondenceToDate = periodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo(currentYear - 2).getYear),
            periodKey = periodKey(periodFrom(currentYear - 1))
          ),
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = periodFrom(currentYear - 3),
            inboundCorrespondenceToDate = periodTo(currentYear - 3),
            inboundCorrespondenceDateReceived = Some(dueDate(yearDue = 2021)),
            inboundCorrespondenceDueDate = dueDate(periodTo(currentYear - 3).getYear),
            periodKey = periodKey(periodFrom(currentYear - 2))
          ),
          ObligationDetails(
            status = Open,
            inboundCorrespondenceFromDate = periodFrom(currentYear - 1),
            inboundCorrespondenceToDate = periodTo(currentYear - 1),
            inboundCorrespondenceDateReceived = None,
            inboundCorrespondenceDueDate = dueDate(periodTo(currentYear).getYear),
            periodKey = periodKey(periodFrom(currentYear))
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
            inboundCorrespondenceFromDate = periodFrom(currentYear - 2),
            inboundCorrespondenceToDate = periodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = Some(periodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(periodFrom(currentYear - 1))
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
            inboundCorrespondenceFromDate = periodFrom(currentYear - 3),
            inboundCorrespondenceToDate = periodTo(currentYear - 3),
            inboundCorrespondenceDateReceived = Some(periodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(currentYear - 2),
            periodKey = periodKey(periodFrom(currentYear - 3))
          )
        )
      ),
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = periodFrom(currentYear - 2),
            inboundCorrespondenceToDate = periodTo(currentYear - 2),
            inboundCorrespondenceDateReceived = Some(periodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(currentYear - 1),
            periodKey = periodKey(periodFrom(currentYear - 2))
          )
        )
      ),
      Obligation(
        identification = None,
        obligationDetails = Seq(
          ObligationDetails(
            status = Fulfilled,
            inboundCorrespondenceFromDate = periodFrom(currentYear - 1),
            inboundCorrespondenceToDate = periodTo(currentYear - 1),
            inboundCorrespondenceDateReceived = Some(periodTo().plus(Period.ofDays(1))),
            inboundCorrespondenceDueDate = dueDate(currentYear),
            periodKey = periodKey(periodFrom(currentYear - 1))
          )
        )
      )
    )
  )
}
