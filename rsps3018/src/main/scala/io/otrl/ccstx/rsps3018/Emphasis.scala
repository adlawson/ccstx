package io.otrl.ccstx.rsps3018

sealed trait Emphasis extends AnyRef {
  def value: Int
}

object Emphasis {

  sealed abstract class EmphasisConst(val value: Int) extends Emphasis

  case object Regular extends EmphasisConst(0)
  case object RegularUnderline extends EmphasisConst(1)
  case object RegularItalic extends EmphasisConst(2)
  case object RegularUnderlineItalic extends EmphasisConst(3)
  case object RegularBold extends EmphasisConst(4)
  case object RegularBoldUnderline extends EmphasisConst(5)
  case object RegularBoldItalic extends EmphasisConst(6)
  case object RegularBoldUnderlineItalic extends EmphasisConst(7)
  case object Inverted extends EmphasisConst(8)
  case object InvertedUnderline extends EmphasisConst(9)
  case object InvertedItalic extends EmphasisConst(10)
  case object InvertedUnderlineItalic extends EmphasisConst(11)
  case object InvertedBold extends EmphasisConst(12)
  case object InvertedBoldUnderline extends EmphasisConst(13)
  case object InvertedBoldItalic extends EmphasisConst(14)
  case object InvertedBoldUnderlineItalic extends EmphasisConst(15)
}
