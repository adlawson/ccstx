package io.otrl.ccstx.rsps3018

trait Format {

  def id: FormatId
  def stock: Stock
  def magstripe: Magstripe

  def boxes: List[Box]
  def fields: List[Field]
  def literals: List[Literal]

  def elements: List[Element] = boxes ++ fields ++ literals
}
