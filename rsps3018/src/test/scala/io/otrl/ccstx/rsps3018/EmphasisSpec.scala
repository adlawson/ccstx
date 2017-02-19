package io.otrl.ccstx.rsps3018

import org.scalatest.{FlatSpecLike, Matchers}

class EmphasisSpec extends FlatSpecLike with Matchers {

  import Emphasis._

  it should "return the value 0 for Regular" in {
     Regular.value shouldBe 0
  }

  it should "return the value 1 for RegularUnderline" in {
     RegularUnderline.value shouldBe 1
  }

  it should "return the value 2 for RegularItalic" in {
     RegularItalic.value shouldBe 2
  }

  it should "return the value 3 for RegularUnderlineItalic" in {
     RegularUnderlineItalic.value shouldBe 3
  }

  it should "return the value 4 for RegularBold" in {
     RegularBold.value shouldBe 4
  }

  it should "return the value 5 for RegularBoldUnderline" in {
     RegularBoldUnderline.value shouldBe 5
  }

  it should "return the value 6 for RegularBoldItalic" in {
     RegularBoldItalic.value shouldBe 6
  }

  it should "return the value 7 for RegularBoldUnderlineItalic" in {
     RegularBoldUnderlineItalic.value shouldBe 7
  }

  it should "return the value 8 for Inverted" in {
     Inverted.value shouldBe 8
  }

  it should "return the value 9 for InvertedUnderline" in {
     InvertedUnderline.value shouldBe 9
  }

  it should "return the value 10 for InvertedItalic" in {
     InvertedItalic.value shouldBe 10
  }

  it should "return the value 11 for InvertedUnderlineItalic" in {
     InvertedUnderlineItalic.value shouldBe 11
  }

  it should "return the value 12 for InvertedBold" in {
     InvertedBold.value shouldBe 12
  }

  it should "return the value 13 for InvertedBoldUnderline" in {
     InvertedBoldUnderline.value shouldBe 13
  }

  it should "return the value 14 for InvertedBoldItalic" in {
     InvertedBoldItalic.value shouldBe 14
  }

  it should "return the value 15 for InvertedBoldUnderlineItalic" in {
     InvertedBoldUnderlineItalic.value shouldBe 15
  }
}
