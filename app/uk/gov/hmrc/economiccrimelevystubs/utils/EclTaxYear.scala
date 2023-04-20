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

import java.time.LocalDate
import scala.annotation.tailrec

object EclTaxYear {

  def currentYear: Int        = LocalDate.now().getYear
  private val MonthDue        = 9
  private val DayDue          = 30
  private val EclFyEndMonth   = 3
  private val EclFyStartMonth = 4
  private val EclFyEndDay     = 31
  private val EclFyStartDay   = 1

  def dueDate(yearDue: Int = currentYear): LocalDate =
    LocalDate.of(calculateYearDue(yearDue), MonthDue, DayDue)

  private def yearDue: Int = calculateYearDue()

  def currentFyStartYear: Int = yearDue - 1

  @tailrec
  private def calculateYearDue(yearDue: Int = currentYear, currentDate: LocalDate = LocalDate.now()): Int =
    if (currentDate.isAfter(LocalDate.of(yearDue, MonthDue, DayDue))) {
      calculateYearDue(yearDue + 1, currentDate)
    } else {
      yearDue
    }

  def eclPeriodFrom(startYear: Int = currentFyStartYear): LocalDate =
    LocalDate.of(startYear, EclFyStartMonth, EclFyStartDay)

  def eclPeriodTo(startYear: Int = currentFyStartYear): LocalDate =
    LocalDate.of(startYear + 1, EclFyEndMonth, EclFyEndDay)

}
