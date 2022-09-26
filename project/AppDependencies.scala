import sbt._

object AppDependencies {

  val compile: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "bootstrap-backend-play-28" % "7.4.0"
  )

  val test: Seq[ModuleID]    = Seq(
    "uk.gov.hmrc"         %% "bootstrap-test-play-28" % "7.4.0",
    "org.mockito"         %% "mockito-scala"          % "1.16.42",
    "org.scalacheck"      %% "scalacheck"             % "1.15.4"
  ).map(_ % "test, it")

  def apply(): Seq[ModuleID] = compile ++ test

}
