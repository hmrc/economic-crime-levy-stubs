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

package uk.gov.hmrc.economiccrimelevystubs.utils

import uk.gov.hmrc.time.TaxYear

import java.time.LocalDate
import scala.annotation.tailrec

object EclTaxYear {

  private val MonthDue        = 9
  private val DayDue          = 30
  private val EclFyEndMonth   = 3
  private val EclFyStartMonth = 4
  private val EclFyEndDay     = 31
  private val EclFyStartDay   = 1
  private val netDueDateMonth = 2
  private val netDueDateDay   = 8
  private val yearStartDay    = 1
  private val yearStartMonth  = 1

  def currentTaxYear: TaxYear = TaxYear.current

  def currentYear: Int = LocalDate.now().getYear

  def dueDate(yearDue: Int = currentYear): LocalDate =
    LocalDate.of(yearDue, MonthDue, DayDue)

  def currentFyStartYear: Int = yearDue - 1

  def netDueDate(endYear: Int = yearDue): LocalDate =
    LocalDate.of(endYear, netDueDateMonth, netDueDateDay)

  def periodFrom(startYear: Int = currentFyStartYear): LocalDate =
    LocalDate.of(startYear, EclFyStartMonth, EclFyStartDay)

  def periodKey(taxYear: TaxYear): String = s"${taxYear.startYear.toString.takeRight(2)}XY"

  def periodTo(startYear: Int = currentFyStartYear): LocalDate =
    LocalDate.of(startYear + 1, EclFyEndMonth, EclFyEndDay)

  def previousTaxYear: TaxYear = TaxYear.current.previous

  def startYearStartOfTaxYear(taxYear: TaxYear): LocalDate =
    LocalDate.of(taxYear.startYear, yearStartMonth, yearStartDay)

  @tailrec
  private def calculateYearDue(yearDue: Int = currentYear, currentDate: LocalDate = LocalDate.now()): Int =
    if (currentDate.isAfter(LocalDate.of(yearDue, MonthDue, DayDue))) {
      calculateYearDue(yearDue + 1, currentDate)
    } else {
      yearDue
    }

  private def yearDue: Int = calculateYearDue()
}
