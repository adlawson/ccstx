package io.otrl.ccstx.rsps3018

case class FontSize(value: Int) extends AnyVal
case class FieldId(value: String) extends AnyVal
case class FormatId(value: String) extends AnyVal
case class LiteralId(value: String) extends AnyVal
case class Magstripe(value: String) extends AnyVal

case class Coords(x: Int, y: Int)
case class Bounds(topLeft: Coords, bottomRight: Coords)
case class Font(size: FontSize, align: Alignment, emphasis: Emphasis) {
  def withEmphasis(emphasis: Emphasis): Font = copy(emphasis = emphasis)
}
