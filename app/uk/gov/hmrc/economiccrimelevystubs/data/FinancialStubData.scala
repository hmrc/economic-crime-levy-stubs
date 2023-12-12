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

  private val contractObjectNumber    = "104920928302302"
  private val contractObjectType      = "ECL"
  private val eclReturn               = "ECL Return"
  private val eclInterest             = "ECL Interest"
  private val incomingPayment         = "Incoming Payment"
  private val interestPostedChargeRef = "XB001286323438"

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
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(0),
          documentOutstandingAmount = Some(10000),
          documentTotalAmount = Some(10000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(interestPostedChargeRef),
          issueDate = Some(startYearStarOfYear(currentTaxYear).toString),
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
          postingDate = Some(startYearStarOfYear(currentTaxYear).toString)
        )
      )
    )
  )

  def financialDataOverdueObligationResponse(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(1250),
        totalAccountOverdue = Some(1000),
        totalBalance = Some(100),
        totalCleared = Some(0),
        totalCredit = Some(0),
        totalNotYetDue = Some(0),
        totalOverdue = Some(100)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000004"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(8000),
          documentOutstandingAmount = Some(28000),
          documentTotalAmount = Some(36000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(interestPostedChargeRef),
          issueDate = Some(startYearStarOfYear(previousTaxYear).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(8000),
                chargeDescription = Some("ECL Return"),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStarOfYear(previousTaxYear).toString)
        )
      )
    )
  )

  def FinancialDataPaidObligationResponse(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(1250),
        totalAccountOverdue = Some(1000),
        totalBalance = Some(100),
        totalCleared = Some(0),
        totalCredit = Some(0),
        totalNotYetDue = Some(0),
        totalOverdue = Some(100)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000005"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(12000),
          documentOutstandingAmount = Some(4000),
          documentTotalAmount = Some(16000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = Some(114.84),
          interestPostedChargeRef = Some(interestPostedChargeRef),
          issueDate = Some(startYearStarOfYear(previousTaxYear.back(1)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(4000),
                chargeDescription = Some(eclReturn),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(12000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(netDueDate(previousTaxYear.back(1).startYear).plusDays(1).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.back(1).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStarOfYear(previousTaxYear.back(1)).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some(interestPostedChargeRef),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(100),
          documentOutstandingAmount = Some(4.84),
          documentTotalAmount = Some(114.84),
          documentType = Some(DocumentType.InterestCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(startYearStarOfYear(previousTaxYear.back(1)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(4.84),
                chargeDescription = Some(eclInterest),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(100),
                chargeDescription = Some(eclInterest),
                clearingDate = Some(netDueDate(previousTaxYear.back(1).startYear).plusDays(1).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.back(1).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStarOfYear(previousTaxYear.back(1)).toString)
        )
      )
    )
  )
}
