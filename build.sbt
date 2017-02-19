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
