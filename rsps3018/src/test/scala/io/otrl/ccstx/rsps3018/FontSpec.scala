package io.otrl.ccstx.rsps3018

import org.scalatest.{FlatSpecLike, Matchers}

class FontSpec extends FlatSpecLike with Matchers {

  "withEmphasis" should "return the font with the given Emphasis" in {
    val font = Font(FontSize(10), Alignment.Left, Emphasis.Regular)
    val updated = font.withEmphasis(Emphasis.Inverted)
    updated.emphasis shouldBe Emphasis.Inverted
  }
}
