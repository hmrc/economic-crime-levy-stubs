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

import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework._
import uk.gov.hmrc.economiccrimelevystubs.utils.EclTaxYear._

object FinancialStubData {

  private val contractObjectType = "ECL"
  private val issueDate          = "2023-01-01"

  def financialDataDueObligation(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(1250),
        totalAccountOverdue = Some(1000),
        totalBalance = Some(100),
        totalCleared = Some(0),
        totalCredit = Some(0),
        totalNotYetDue = Some(250),
        totalOverdue = Some(100)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000003"),
          contractObjectNumber = Some("104920928302302"),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(0),
          documentOutstandingAmount = Some(10000),
          documentTotalAmount = Some(10000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some("XB001286323438"),
          issueDate = Some(issueDate),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(0),
                chargeDescription = None,
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate().toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some("2023-01-01")
        )
      )
    )
  )
}
