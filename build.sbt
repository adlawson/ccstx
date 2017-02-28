import sbt.Keys._
import io.otrl.ccstx.project._
import io.otrl.ccstx.project.ProjectSettings._
import scala.xml.XML

lazy val rootSettings = Seq(
  organization := "io.otrl",
  scalaVersion := "2.11.8",
  scalacOptions := commonScalacOptions
) ++ ignoreDocumentation ++ warnUnusedImport ++ publishRemote

lazy val ccstx = (project in file("."))
  .settings(rootSettings: _*)
  .settings(ignoreArtifact: _*)
  .aggregate(frtBindings, rsps3018)

lazy val frtBindings = (project in file("frt-bindings"))
  .settings(rootSettings: _*)
  .settings(name := "frt-bindings")
  .settings(libraryDependencies ++= Dependencies.frtBindings)
  .settings(scalaxbPackageName in (Compile, scalaxb) := "com.fastrailticketing.ccstprinting")
  .enablePlugins(ScalaxbPlugin)

lazy val rsps3018 = (project in file("rsps3018"))
  .settings(rootSettings: _*)
  .settings(name := "ccstx-rsps3018")
  .settings(libraryDependencies ++= Dependencies.rsps3018)
  .settings(sourceGenerators in Compile += (sourceManaged in Compile).map(
    CcstXmlGenerator.gen(_, XML.loadFile("project/ccst.xml"), "io.otrl.ccstx.rsps3018")
  ).taskValue)
