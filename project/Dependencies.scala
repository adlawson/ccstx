package io.otrl.ccstx.project

import sbt._

object Dependencies {

  import Category._
  lazy val rsps3018 = { test }

  object Version {
    val scalatest = "3.0.1"
  }

  object Category {

    val test = Seq(
      "org.scalatest" %% "scalatest" % Version.scalatest
    ).map(_ % Test)
  }
}
