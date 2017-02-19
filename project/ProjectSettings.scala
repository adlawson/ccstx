package io.otrl.ccstx.project

import sbt._
import sbt.Keys._

object ProjectSettings {

  lazy val commonScalacOptions = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-value-discard"
  )

  lazy val ignoreArtifact = Seq(
    publishArtifact := false
  )

  lazy val ignoreDocumentation = Seq(
    publishArtifact in (Compile, packageDoc) := false,
    sources in (Compile, doc) := Nil
  )

  lazy val publishRemote = Seq(
    publishTo := Some("Artifactory Realm" at "https://otrl.artifactoryonline.com/otrl/maven"),
    credentials += Credentials(
      "Artifactory Realm",
      "otrl.artifactoryonline.com",
      System.getenv("ARTIFACTORY_USER"),
      System.getenv("ARTIFACTORY_PASS")
    )
  )

  lazy val warnUnusedImport = Seq(
    scalacOptions += "-Ywarn-unused-import",
    scalacOptions in (Compile, console) ~= (_.filterNot("-Ywarn-unused-import" == _)),
    scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value
  )
}
