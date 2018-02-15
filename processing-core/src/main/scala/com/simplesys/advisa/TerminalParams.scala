package com.simplesys.advisa

import com.simplesys.misc.Helper._
import com.simplesys.common.Strings._

object TerminalParams {
  def apply(terminal: Terminal) = new TerminalParams(terminal)
}

class TerminalParams(terminal: Terminal) {

  val sTerminal: String = terminal.sTerminal
  val sCategoryCode: Option[String] = terminal.sCategoryCode
  val sCategoryName: Option[String] = terminal.sCategoryName
  val merchantName: Option[String] = terminal.merchantName

  val countSlashes = sTerminal countMatches "/"

  val merchantNameTokens: Set[String] = merchantName match {
    case None => Set.empty[String]
    case Some(string) => string.simplify.tokenize
  }

  val terminalNameTokens: Set[String] = sTerminal.simplify.tokenize

  override def toString =
    s"sTerminal: $sTerminal, sCategoryCode: ${sCategoryCode.getOrElse(strEmpty)}, sCategoryName: ${sCategoryName
      .getOrElse(strEmpty)}, merchantName: ${merchantName.getOrElse(strEmpty)}, countSlashes: $countSlashes, merchantNameTokens: ${merchantNameTokens
      .mkString("[".space, ",".space, space + "]")}, terminalNameTokens: ${terminalNameTokens
      .mkString("[".space, ",".space, space + "]")}"
}
