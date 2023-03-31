package uk.gov.hmrc.economiccrimelevystubs.models.des

import play.api.libs.json.{Json, OFormat}
final case class ReferencesForRemoval(references: List[String])

object ReferencesForRemoval {
  implicit val format: OFormat[ReferencesForRemoval] = Json.format[ReferencesForRemoval]
}
