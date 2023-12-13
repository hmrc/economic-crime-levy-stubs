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

package uk.gov.hmrc.economiccrimelevystubs.models.integrationframework

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json._

final case class FinancialData(totalisation: Option[Totalisation], documentDetails: Option[Seq[DocumentDetails]])

object FinancialData {
  implicit val reads: Reads[FinancialData] = Json.reads[FinancialData]

  implicit val writes: Writes[FinancialData] = (
    (JsPath \ "getFinancialData" \ "financialDetails" \ "totalisation").writeNullable[Totalisation] and
      (JsPath \ "getFinancialData" \ "financialDetails" \ "documentDetails").writeNullable[Seq[DocumentDetails]]
  )(unlift(FinancialData.unapply))
}

final case class Totalisation(
  totalAccountBalance: Option[BigDecimal],
  totalAccountOverdue: Option[BigDecimal],
  totalOverdue: Option[BigDecimal],
  totalNotYetDue: Option[BigDecimal],
  totalBalance: Option[BigDecimal],
  totalCredit: Option[BigDecimal],
  totalCleared: Option[BigDecimal]
)

object Totalisation {
  implicit val reads: Reads[Totalisation] = Json.reads[Totalisation]

  implicit val writes: Writes[Totalisation] = (
    (JsPath \ "regimeTotalisation" \ "totalAccountBalance").writeNullable[BigDecimal] and
      (JsPath \ "regimeTotalisation" \ "totalAccountOverdue").writeNullable[BigDecimal] and
      (JsPath \ "targetedSearch_SelectionCriteriaTotalisation" \ "totalOverdue").writeNullable[BigDecimal] and
      (JsPath \ "targetedSearch_SelectionCriteriaTotalisation" \ "totalNotYetDue").writeNullable[BigDecimal] and
      (JsPath \ "targetedSearch_SelectionCriteriaTotalisation" \ "totalBalance").writeNullable[BigDecimal] and
      (JsPath \ "targetedSearch_SelectionCriteriaTotalisation" \ "totalCredit").writeNullable[BigDecimal] and
      (JsPath \ "targetedSearch_SelectionCriteriaTotalisation" \ "totalCleared").writeNullable[BigDecimal]
  )(unlift(Totalisation.unapply))
}

final case class DocumentDetails(
  documentType: Option[DocumentType],
  chargeReferenceNumber: Option[String],
  postingDate: Option[String],
  issueDate: Option[String],
  documentTotalAmount: Option[BigDecimal],
  documentClearedAmount: Option[BigDecimal],
  documentOutstandingAmount: Option[BigDecimal],
  lineItemDetails: Option[Seq[LineItemDetails]],
  interestPostedAmount: Option[BigDecimal],
  interestAccruingAmount: Option[BigDecimal],
  interestPostedChargeRef: Option[String],
  penaltyTotals: Option[Seq[PenaltyTotals]],
  contractObjectNumber: Option[String],
  contractObjectType: Option[String]
)

object DocumentDetails {
  implicit var reads: Reads[DocumentDetails] = Json.reads[DocumentDetails]

  implicit val writes: Writes[DocumentDetails] = (
    (JsPath \ "documentType").writeNullable[DocumentType] and
      (JsPath \ "chargeReferenceNumber").writeNullable[String] and
      (JsPath \ "postingDate").writeNullable[String] and
      (JsPath \ "issueDate").writeNullable[String] and
      (JsPath \ "documentTotalAmount").writeNullable[BigDecimal] and
      (JsPath \ "documentClearedAmount").writeNullable[BigDecimal] and
      (JsPath \ "documentOutstandingAmount").writeNullable[BigDecimal] and
      (JsPath \ "lineItemDetails").writeNullable[Seq[LineItemDetails]] and
      (JsPath \ "documentInterestTotals" \ "interestPostedAmount").writeNullable[BigDecimal] and
      (JsPath \ "documentInterestTotals" \ "interestAccruingAmount").writeNullable[BigDecimal] and
      (JsPath \ "documentInterestTotals" \ "interestPostedChargeRef").writeNullable[String] and
      (JsPath \ "documentPenaltyTotals").writeNullable[Seq[PenaltyTotals]] and
      (JsPath \ "contractObjectNumber").writeNullable[String] and
      (JsPath \ "contractObjectType").writeNullable[String]
  )(unlift(DocumentDetails.unapply))
}

final case class LineItemDetails(
  chargeDescription: Option[String],
  periodFromDate: Option[String],
  periodToDate: Option[String],
  periodKey: Option[String],
  netDueDate: Option[String],
  amount: Option[BigDecimal],
  clearingDate: Option[String],
  clearingReason: Option[String],
  clearingDocument: Option[String]
)

object LineItemDetails {
  implicit val format: OFormat[LineItemDetails] = Json.format[LineItemDetails]
}

final case class PenaltyTotals(
  penaltyType: Option[String],
  penaltyStatus: Option[String],
  penaltyAmount: Option[BigDecimal],
  postedChargeReference: Option[String]
)

object PenaltyTotals {
  implicit val format: OFormat[PenaltyTotals] = Json.format[PenaltyTotals]
}
