package com.simplesys.advisa

import org.joda.time.DateTime

case class Terminal(
    _sTerminal: String,
    sCategoryCode: Option[String],
    sCategoryName: Option[String],
    merchantName: Option[String]
) {
  val sTerminal = _sTerminal.toUpperCase()
}
