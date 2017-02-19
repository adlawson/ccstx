package io.otrl.ccstx.rsps3018

import org.scalatest.{FlatSpecLike, Matchers}

class LiteralsSpec extends FlatSpecLike with Matchers {

  it should "define a Literals object of literals" in {
    Literals shouldBe a [Literals]
  }

  it should "return a valid PartialLiteral" in {
    val font = Font(FontSize(10), Alignment.Left, Emphasis.Regular)
    val bounds = Bounds(Coords(0, 0), Coords(99, 99))
    val partial = Literals.literal2
    val literal = partial(font, bounds)
    partial shouldBe a[PartialLiteral]
    literal shouldBe a[Literal]
    literal.id shouldBe LiteralId("00002")
    literal.value shouldBe "2 OF 2"
    literal.font shouldBe font
    literal.bounds shouldBe bounds
  }
}
