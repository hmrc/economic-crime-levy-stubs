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

import java.time.LocalDate

object FinancialStubData {

  private val automaticClearing    = "Automatic Clearing"
  private val chargeReference      = "XB001286323438"
  private val contractObjectNumber = "104920928302302"
  private val contractObjectType   = "ECL"
  private val eclReturn            = "ECL Return"
  private val eclInterest          = "ECL Interest"
  private val incomingPayment      = "Incoming Payment"
  private val outgoingPayment      = "Outgoing Payment"
  private val paymentOnAccount     = "Payment on account"
  private val reversal             = "Reversal"

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
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear).toString),
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
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear).toString)
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
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(previousTaxYear).toString),
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
          postingDate = Some(startYearStartOfTaxYear(previousTaxYear).toString)
        )
      )
    )
  )

  def financialDataPaidObligationResponse(): FinancialData = FinancialData(
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
          documentClearedAmount = Some(14000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(14000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(previousTaxYear.back(1)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(14000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(previousTaxYear.back(1).startYear, 2, 9).toString),
                clearingDocument = Some(chargeReference),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.back(1).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(previousTaxYear.back(1)).toString)
        )
      )
    )
  )

  def financialDataPartiallyPaidResponse(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000006"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(4900),
          documentOutstandingAmount = Some(23100),
          documentTotalAmount = Some(28000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(3600),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 7, 21).toString),
                clearingDocument = Some(""),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.startYear).toString)
              ),
              LineItemDetails(
                amount = Some(1300),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 7, 20).toString),
                clearingDocument = Some(""),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear).toString)
        )
      )
    )
  )

  def financialDataPaidPartiallyPaidOverdueResponse(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000006"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(2400),
          documentOutstandingAmount = Some(14600),
          documentTotalAmount = Some(17000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(2400),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 6, 8).toString),
                clearingDocument = Some(""),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000007"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(1500),
          documentOutstandingAmount = Some(20500),
          documentTotalAmount = Some(22000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(1)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(1500),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(1).startYear, 4, 28).toString),
                clearingDocument = Some(""),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(1).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(1)).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000008"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(18000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(18000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(18000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 3, 12).toString),
                clearingDocument = Some(""),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(2).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(2))),
                periodToDate = Some(periodTo(previousTaxYear.back(2).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def financialDataOverpaidObligationSinglePayment(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000008"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(20000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(18000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(20000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 3, 12).toString),
                clearingDocument = Some("01"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(2).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(2))),
                periodToDate = Some(periodTo(previousTaxYear.back(2).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def financialDataOverpaidObligationMultiplePayments(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(0),
        totalAccountOverdue = Some(0),
        totalBalance = Some(0),
        totalCleared = Some(0),
        totalCredit = Some(0),
        totalNotYetDue = Some(0),
        totalOverdue = Some(0)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000009"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(20000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(18000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(10000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 3, 12).toString),
                clearingDocument = Some("01"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(2).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(2))),
                periodToDate = Some(periodTo(previousTaxYear.back(2).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(10000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 3, 12).toString),
                clearingDocument = Some("02"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(2).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(2))),
                periodToDate = Some(periodTo(previousTaxYear.back(2).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def FinancialDataPaidObligationPartialPaidInterestResponse(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000010"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(14000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(14000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = Some(114.84),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(0),
                chargeDescription = Some(eclReturn),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(14000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some(chargeReference),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(100),
          documentOutstandingAmount = Some(4.84),
          documentTotalAmount = Some(114.84),
          documentType = Some(DocumentType.InterestCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
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
                netDueDate = Some(netDueDate(currentTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(100),
                chargeDescription = Some(eclInterest),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def financialDataOverdueObligationWithInterestResponse(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000012"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(12000),
          documentOutstandingAmount = Some(4000),
          documentTotalAmount = Some(16000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = Some(114.84),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
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
                netDueDate = Some(netDueDate(currentTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(12000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some(chargeReference),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(100),
          documentOutstandingAmount = Some(4.84),
          documentTotalAmount = Some(114.84),
          documentType = Some(DocumentType.InterestCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
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
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(100),
                chargeDescription = Some(eclInterest),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def financialDataPaidObligationPaidInterestResponse(): FinancialData = FinancialData(
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
          chargeReferenceNumber = Some("XMECL0000000011"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(14000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(14000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = Some(114.84),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(0),
                chargeDescription = Some(eclReturn),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(1).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(14000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some(chargeReference),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(114.84),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(114.84),
          documentType = Some(DocumentType.InterestCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(0),
                chargeDescription = Some(eclInterest),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              ),
              LineItemDetails(
                amount = Some(114.84),
                chargeDescription = Some(eclInterest),
                clearingDate = Some(LocalDate.of(currentTaxYear.back(2).startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.back(1).startYear).toString),
                periodKey = Some(periodKey(previousTaxYear.back(1))),
                periodToDate = Some(periodTo(previousTaxYear.back(1).startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.back(2).startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(startYearStartOfTaxYear(currentTaxYear.back(2)).toString)
        )
      )
    )
  )

  def financialDataRefundForOverpayment(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = None,
        totalAccountOverdue = None,
        totalBalance = None,
        totalCleared = Some(1000),
        totalCredit = None,
        totalNotYetDue = None,
        totalOverdue = None
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000013"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(10000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(10000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = None,
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(10000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 8, 1).toString),
                clearingDocument = Some("201000000070"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 7, 31).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = None,
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(-1000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(-1000),
          documentType = Some(DocumentType.Payment),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = None,
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(-1000),
                chargeDescription = Some(paymentOnAccount),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 8, 9).toString),
                clearingDocument = Some("310000000069"),
                clearingReason = Some(outgoingPayment),
                periodFromDate = None,
                periodKey = None,
                periodToDate = None,
                netDueDate = Some(LocalDate.of(currentTaxYear.startYear, 7, 31).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 8, 1).toString)
        )
      )
    )
  )

  def financialDataOverdueObligationWithoutInterestDocumentFormed(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(30000),
        totalAccountOverdue = Some(30000),
        totalBalance = Some(30000),
        totalCleared = Some(6000),
        totalCredit = None,
        totalNotYetDue = None,
        totalOverdue = Some(30000)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000014"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(30000),
          documentOutstandingAmount = Some(6000),
          documentTotalAmount = Some(36000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(12.73),
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = None,
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(30000),
                chargeDescription = Some(eclReturn),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(LocalDate.of(currentTaxYear.startYear, 9, 30).toString)
              ),
              LineItemDetails(
                amount = Some(6000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 8, 1).toString),
                clearingDocument = Some("201000000069"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(LocalDate.of(currentTaxYear.startYear, 9, 30).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 7, 31).toString)
        )
      )
    )
  )

  def financialDataUnexpectedDocumentType(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(1250),
        totalAccountOverdue = Some(1000),
        totalBalance = Some(100),
        totalCleared = None,
        totalCredit = None,
        totalNotYetDue = None,
        totalOverdue = Some(100)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000015"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(0),
          documentOutstandingAmount = Some(10000),
          documentTotalAmount = Some(10000),
          documentType = Some(DocumentType.Other("Unexpected Document Type")),
          interestAccruingAmount = Some(12.1),
          interestPostedAmount = Some(13.12),
          interestPostedChargeRef = Some("XB001286323438"),
          issueDate = Some(LocalDate.of(currentTaxYear.startYear, 1, 1).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(0),
                chargeDescription = Some(eclReturn),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 1, 1).toString)
        )
      )
    )
  )

  def financialDataPaidObligationWithReversalLineItemResponse(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(1250),
        totalAccountOverdue = Some(1000),
        totalBalance = Some(100),
        totalCleared = None,
        totalCredit = None,
        totalNotYetDue = None,
        totalOverdue = Some(100)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000016"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(0),
          documentOutstandingAmount = Some(10000),
          documentTotalAmount = Some(10000),
          documentType = Some(DocumentType.AmendedCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(LocalDate.of(previousTaxYear.startYear, 1, 1).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(10000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(previousTaxYear.startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(automaticClearing),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.startYear).plusDays(1).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(previousTaxYear.startYear, 1, 1).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some("XK107657692914"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(36000),
          documentOutstandingAmount = Some(0),
          documentTotalAmount = Some(36000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(LocalDate.of(previousTaxYear.startYear, 1, 1).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(36000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(previousTaxYear.startYear, 2, 9).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(reversal),
                periodFromDate = Some(periodFrom(previousTaxYear.startYear).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(previousTaxYear.startYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(previousTaxYear.startYear, 1, 1).toString)
        )
      )
    )
  )

  def financialDataPaidChargeWithInterestAndReversalResponse(): FinancialData = FinancialData(
    Some(
      Totalisation(
        totalAccountBalance = Some(796.23),
        totalAccountOverdue = Some(796.23),
        totalBalance = Some(796.23),
        totalCleared = Some(286000),
        totalCredit = None,
        totalNotYetDue = None,
        totalOverdue = Some(796.23)
      )
    ),
    Some(
      Seq(
        DocumentDetails(
          chargeReferenceNumber = Some("XMECL0000000017"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = None,
          documentOutstandingAmount = Some(796.23),
          documentTotalAmount = Some(796.23),
          documentType = Some(DocumentType.InterestCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 18).toString),
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(796.23),
                chargeDescription = Some("Economic Crime Levy Interest"),
                clearingDate = None,
                clearingDocument = None,
                clearingReason = None,
                periodFromDate = Some(LocalDate.of(previousTaxYear.startYear, 10, 3).toString),
                periodKey = None,
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some("XL002610192892"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(250000),
          documentOutstandingAmount = None,
          documentTotalAmount = Some(250000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = Some(796.23),
          interestPostedAmount = Some(796.23),
          interestPostedChargeRef = Some(chargeReference),
          issueDate = None,
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(250000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(incomingPayment),
                periodFromDate = Some(LocalDate.of(previousTaxYear.startYear, 10, 3).toString),
                periodKey = Some(periodKey(previousTaxYear)),
                periodToDate = Some(periodTo(previousTaxYear.startYear).toString),
                netDueDate = Some(LocalDate.of(currentTaxYear.startYear, 9, 30).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString)
        ),
        DocumentDetails(
          chargeReferenceNumber = Some("003390018797"),
          contractObjectNumber = Some(contractObjectNumber),
          contractObjectType = Some(contractObjectType),
          documentClearedAmount = Some(36000),
          documentOutstandingAmount = None,
          documentTotalAmount = Some(36000),
          documentType = Some(DocumentType.NewCharge),
          interestAccruingAmount = None,
          interestPostedAmount = None,
          interestPostedChargeRef = None,
          issueDate = None,
          lineItemDetails = Some(
            Seq(
              LineItemDetails(
                amount = Some(36000),
                chargeDescription = Some(eclReturn),
                clearingDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString),
                clearingDocument = Some("719283701921"),
                clearingReason = Some(reversal),
                periodFromDate = Some(periodFrom(currentTaxYear.startYear).toString),
                periodKey = Some(periodKey(currentTaxYear)),
                periodToDate = Some(periodTo(currentTaxYear.startYear).toString),
                netDueDate = Some(netDueDate(currentTaxYear.finishYear).toString)
              )
            )
          ),
          penaltyTotals = None,
          postingDate = Some(LocalDate.of(currentTaxYear.startYear, 10, 16).toString)
        )
      )
    )
  )
}
