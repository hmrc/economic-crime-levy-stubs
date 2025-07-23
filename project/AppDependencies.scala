import sbt.*

object AppDependencies {

  private val hmrcBootstrapVersion = "9.18.0"
  private val hmrcMongoVersion     = "2.6.0"

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-backend-play-30" % hmrcBootstrapVersion,
    "uk.gov.hmrc"       %% "tax-year"                  % "6.0.0",
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-play-30"        % hmrcMongoVersion
  )

  val test: Seq[ModuleID]    = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-30"   % hmrcBootstrapVersion,
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-30"  % hmrcMongoVersion,
    "org.mockito"       %% "mockito-scala"            % "2.0.0",
    "org.scalatestplus" %% "scalacheck-1-17"          % "3.2.18.0"
  ).map(_ % "test, it")

  def apply(): Seq[ModuleID] = compile ++ test

}
