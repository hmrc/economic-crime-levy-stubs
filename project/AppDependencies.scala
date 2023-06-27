import sbt._

object AppDependencies {

  private val hmrcBootstrapVersion = "7.19.0"
  private val hmrcMongoVersion     = "1.3.0"

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-backend-play-28" % hmrcBootstrapVersion,
    "uk.gov.hmrc"       %% "tax-year"                  % "3.2.0",
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-play-28"        % hmrcMongoVersion
  )

  val test: Seq[ModuleID]    = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-28"   % hmrcBootstrapVersion,
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-28"  % hmrcMongoVersion,
    "org.mockito"       %% "mockito-scala"            % "1.17.12",
    "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2"
  ).map(_ % "test, it")

  def apply(): Seq[ModuleID] = compile ++ test

}
