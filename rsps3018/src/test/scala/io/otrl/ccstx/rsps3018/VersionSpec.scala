package io.otrl.ccstx.rsps3018

import org.scalatest.{FlatSpecLike, Matchers}

class VersionSpec extends FlatSpecLike with Matchers {

  "value" should "return the current version we're accredited for" in {
     Version.value shouldBe "03-01"
  }
}
