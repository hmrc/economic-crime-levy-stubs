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

import uk.gov.hmrc.economiccrimelevystubs.models.integrationframework.{FinancialDetail, FinancialDetails}
import uk.gov.hmrc.economiccrimelevystubs.utils.EclTaxYear

object FinancialDetailsStubData {

  val financialDetailsWithPaymentDue: FinancialDetails = FinancialDetails(
    Some(
      Seq(
        FinancialDetail(
          taxYear = EclTaxYear.currentFyStartYear.toString,
          chargeType = Some("ECL"),
          chargeReference = Some("XM002610011594"),
          periodKey = Some("#001"),
          originalAmount = Some(10000),
          outstandingAmount = Some(10000),
          clearedAmount = Some(0),
          accruedInterest = Some(0),
          items = Seq.empty
        )
      )
    )
  )

}
