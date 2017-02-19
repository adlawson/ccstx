package io.otrl.ccstx.rsps3018

sealed trait Element {
  def bounds: Bounds
}

sealed trait Textarea extends Element {
  def value: String
  def font: Font
}

case class Box(bounds: Bounds) extends Element
case class Field(id: FieldId, value: String, font: Font, bounds: Bounds) extends Textarea
case class Literal(id: LiteralId, value: String, font: Font, bounds: Bounds) extends Textarea
