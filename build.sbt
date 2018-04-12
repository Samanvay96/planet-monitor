import com.typesafe.sbt.packager.docker._
import sbt.Keys.{scalaVersion, _}
import sbt._

lazy val commonSettings = Seq(
  organization := "Samanvay",
  version := "0.1",
  scalaVersion := "2.12.3"

)

lazy val ITest = config("itest") extend Test

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .configs(ITest)
  .settings( commonSettings: _* )
  .settings( inConfig(ITest)(Defaults.testSettings) : _*)
  .settings(
    name := "planet-monitor",

    libraryDependencies ++= Seq[ModuleID](
      "org.json4s" %% "json4s-jackson" % "3.5.3",
      "com.github.scopt" %% "scopt" % "3.7.0",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
      "com.typesafe.akka" %% "akka-actor" % "2.5.9",
      "ch.qos.logback" % "logback-classic" % "1.1.6",
      "org.scalaj" %% "scalaj-http" % "2.3.0",

      "junit" % "junit" % "4.12" % "test,itest",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test,itest",
      "org.apache.directory.studio" % "org.apache.commons.io" % "2.4" % "test,itest",
      "org.mockito" % "mockito-core" % "2.7.22" % "test, itest",
      "com.typesafe.akka" %% "akka-testkit" % "2.5.9",
    ),

    parallelExecution in Test := false

  )

javaOptions in Universal := Seq("-J-Xmx8g")
scalacOptions := Seq("-unchecked", "-deprecation")
