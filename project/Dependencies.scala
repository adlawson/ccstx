package io.otrl.ccstx.project

import sbt._

object Dependencies {

  import Category._
  lazy val frtBindings = { soap ++ test }
  lazy val rsps3018 = { test }

  object Version {
    val parserCombinators = "1.0.1"
    val dispatch = "0.11.3"
    val scalatest = "3.0.1"
  }

  object Category {

    val soap = Seq(
      "net.databinder.dispatch" %% "dispatch-core" % Version.dispatch,
      "org.scala-lang.modules" %% "scala-parser-combinators" % Version.parserCombinators
    )

    val test = Seq(
      "org.scalatest" %% "scalatest" % Version.scalatest
    ).map(_ % Test)
  }
}
