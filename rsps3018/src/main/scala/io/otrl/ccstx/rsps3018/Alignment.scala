package io.otrl.ccstx.rsps3018

sealed trait Alignment

object Alignment {
  case object Left extends Alignment
  case object Right extends Alignment
}
