package io.otrl.ccstx.rsps3018

import org.scalatest.{FlatSpecLike, Matchers}

class FieldsSpec extends FlatSpecLike with Matchers {

  it should "define a Fields object of fields" in {
    Fields shouldBe a [Fields]
  }

  it should "return a valid PartialField" in {
    val font = Font(FontSize(10), Alignment.Left, Emphasis.Regular)
    val bounds = Bounds(Coords(0, 0), Coords(99, 99))
    val partial = Fields.field1("foo")
    val field = partial(font, bounds)
    partial shouldBe a[PartialField]
    field shouldBe a[Field]
    field.id shouldBe FieldId("00001")
    field.value shouldBe "foo"
    field.font shouldBe font
    field.bounds shouldBe bounds
  }
}
