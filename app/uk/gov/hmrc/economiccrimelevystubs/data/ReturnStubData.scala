/*
 * Copyright 2024 HM Revenue & Customs
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

import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{Band, GetEclReturnChargeDetails, GetEclReturnDeclarationDetails, GetEclReturnDetails, GetEclReturnSubmissionResponse}

import java.time.LocalDate

object ReturnStubData {

  private val accountingPeriodLength = 365
  private val eclReference = "XMECL0000000001"
  private val emailAddress = "a@b.com"
  private val name = "Joe John"
  private val periodKey = "22XY"
  private val positionInCompany = "Manager"
  private val processingDateTime = "2022-06-07T13:49:51Z"
  private val receiptDate = LocalDate.parse("2022-04-01")
  private val returnDate = "2022-04-01"
  private val returnType = "01"
  private val submissionId = "789124231021"
  private val telephoneNumber = "9876543218"

  private def validBase(): GetEclReturnSubmissionResponse =
    GetEclReturnSubmissionResponse(
      chargeDetails = GetEclReturnChargeDetails(
        chargeReference = None,
        periodKey = periodKey,
        receiptDate = receiptDate,
        returnType = returnType),
      declarationDetails = GetEclReturnDeclarationDetails(
        emailAddress = emailAddress,
        name = name,
        positionInCompany = positionInCompany,
        telephoneNumber = telephoneNumber),
      eclReference = eclReference,
      processingDateTime = processingDateTime,
      returnDetails = GetEclReturnDetails(
        accountingPeriodLength = accountingPeriodLength,
        accountingPeriodRevenue = 10_200_000,
        amountOfEclDutyLiable = 10_000,
        numberOfDaysRegulatedActivityTookPlace = Some(365),
        returnDate = returnDate,
        revenueBand = Band.Medium),
      submissionId = Some(submissionId)
    )

  def validReturnMedium(periodKey: String, eclRegistrationReference: String, chargeReference: Option[String] = None): GetEclReturnSubmissionResponse = {
    val base = validBase()
    base.copy(
      chargeDetails = base.chargeDetails.copy(
        chargeReference = chargeReference,
        periodKey = periodKey
      ),
      eclReference = eclRegistrationReference
    )
  }

  def validReturnLarge(periodKey: String, eclRegistrationReference: String, chargeReference: Option[String] = None): GetEclReturnSubmissionResponse = {
    val base = validBase()
    base.copy(
      chargeDetails = base.chargeDetails.copy(
        chargeReference = chargeReference,
        periodKey = periodKey
      ),
      eclReference = eclRegistrationReference,
      returnDetails = base.returnDetails.copy(
        amountOfEclDutyLiable = 36_000,
        accountingPeriodRevenue = 36_000_000,
        revenueBand = Band.Large
      )
    )
  }

  def validReturnVeryLarge(periodKey: String, eclRegistrationReference: String, chargeReference: Option[String] = None): GetEclReturnSubmissionResponse = {
    val base = validBase()
    base.copy(
      chargeDetails = base.chargeDetails.copy(
        chargeReference = chargeReference,
        periodKey = periodKey
      ),
      eclReference = eclRegistrationReference,
      returnDetails = base.returnDetails.copy(
        amountOfEclDutyLiable = 250_000,
        accountingPeriodRevenue = 1_000_000_000,
        revenueBand = Band.VeryLarge
      )
    )
  }

}
