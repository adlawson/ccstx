package io.otrl.ccstx.rsps3018

sealed trait Stock

object Stock {
  case object BlueSeason extends Stock
  case object GoldRecord extends Stock
  case object GoldSeason extends Stock
  case object GoldTravelcard extends Stock
  case object NtrainStandard extends Stock
  case object OrangeStandard extends Stock
}
