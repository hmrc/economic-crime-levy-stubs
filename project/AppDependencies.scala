import sbt._

object AppDependencies {

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "bootstrap-backend-play-28" % "7.15.0",
    "uk.gov.hmrc"       %% "tax-year"                  % "3.0.0",
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-play-28"        % "1.1.0"
  )

  val test: Seq[ModuleID]    = Seq(
    "uk.gov.hmrc"       %% "bootstrap-test-play-28"   % "7.15.0",
    "uk.gov.hmrc.mongo" %% "hmrc-mongo-test-play-28"  % "1.1.0",
    "org.mockito"       %% "mockito-scala"            % "1.17.12",
    "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2"
  ).map(_ % "test, it")

  def apply(): Seq[ModuleID] = compile ++ test

}
