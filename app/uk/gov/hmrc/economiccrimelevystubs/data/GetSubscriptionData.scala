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

import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework._

object GetSubscriptionData {

  def validIndividualSubscription(eclReference: String): GetSubscriptionResponse =
    GetSubscriptionResponse(
      processingDateTime = "2024-01-22T13:49:51Z",
      legalEntityDetails = getLegalEntityDetailsForIndividual,
      correspondenceAddressDetails = getCorrespondenceAddressDetails,
      primaryContactDetails = getPrimaryContactDetails,
      additionalDetails = getAdditionalDetails(eclReference)
    )

  def validOrganisationSubscription(eclReference: String): GetSubscriptionResponse =
    GetSubscriptionResponse(
      processingDateTime = "2024-01-23T13:49:51Z",
      legalEntityDetails = getLegalEntityDetailsForOrganisations,
      correspondenceAddressDetails = getCorrespondenceAddressDetails,
      primaryContactDetails = getPrimaryContactDetails,
      additionalDetails = getAdditionalDetails(eclReference)
    )

  private val getLegalEntityDetailsForIndividual = GetLegalEntityDetails(
    customerIdentification1 = "887182726272637",
    customerIdentification2 = Some("584523698751254"),
    customerType = "01",
    firstName = Some("John"),
    lastName = Some("Smith")
  )

  private val getLegalEntityDetailsForOrganisations = GetLegalEntityDetails(
    customerIdentification1 = "887182726272637",
    customerIdentification2 = Some("584523698751254"),
    customerType = "02",
    organisationName = Some("ECL LTD")
  )

  private val getCorrespondenceAddressDetails = GetCorrespondenceAddressDetails(
    addressLine1 = "400",
    addressLine2 = Some("ECL Street"),
    addressLine3 = Some("ECL Avenue"),
    addressLine4 = Some("ECL Town"),
    postCode = Some("PL11 2PL"),
    countryCode = Some("GB")
  )

  private val getPrimaryContactDetails =
    GetPrimaryContactDetails(
      name = "John Smith",
      positionInCompany = "Compliance officer",
      telephone = "01232 473743",
      emailAddress = "sandw@yahoo.com"
    )

  private def getAdditionalDetails(eclReference: String) = GetAdditionalDetails(
    registrationDate = "2023-05-21",
    liabilityStartDate = "2023-05-01",
    eclReference = eclReference,
    amlSupervisor = "HMRC",
    businessSector = "Business sector"
  )
}
