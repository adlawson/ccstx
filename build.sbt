import sbt.Keys._
import io.otrl.ccstx.project._
import io.otrl.ccstx.project.ProjectSettings._

lazy val rootSettings = Seq(
  organization := "io.otrl",
  scalaVersion := "2.11.8",
  scalacOptions := commonScalacOptions
) ++ ignoreDocumentation ++ warnUnusedImport ++ publishRemote

lazy val ccstx = (project in file("."))
  .settings(rootSettings: _*)
  .settings(ignoreArtifact: _*)
  .aggregate(rsps3018)

lazy val rsps3018 = (project in file("rsps3018"))
  .settings(rootSettings: _*)
  .settings(name := "ccstx-rsps3018")
  .settings(libraryDependencies ++= Dependencies.rsps3018)
