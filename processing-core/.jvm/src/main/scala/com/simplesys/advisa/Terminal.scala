package com.simplesys.advisa

case class Terminal(
    _sTerminal: String,
    sCategoryCode: Option[String],
    sCategoryName: Option[String],
    merchantName: Option[String]
) {
  val sTerminal = _sTerminal.toUpperCase()
}
