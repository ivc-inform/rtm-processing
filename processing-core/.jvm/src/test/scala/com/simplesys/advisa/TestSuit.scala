package com.simplesys.advisa

import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.config.Config
import com.simplesys.connectionStack.BoneCPStack
import com.simplesys.log.Logging
import com.simplesys.misc.Helper._
import doobie.imports._
import org.scalatest.FunSuite

class TestSuit extends FunSuite with Logging with Config {

  case class StopSymbol(symbol: Char, symbolOffset: Int)

  val cpStack: BoneCPStack = BoneCPStack()

  val dsAdvisa: BoneCPDataSource = getString("dbPool.defaultAdvisa") match {
    case x @ "oracleMFMSAdvisa" => cpStack OracleDataSource x
    case any => throw new RuntimeException(s"Bad: ${any}")
  }

  val dsLogger: BoneCPDataSource = getString("dbPool.defaultLogging") match {
    case x @ "oracleMFMSLogging" => cpStack OracleDataSource x
    case any => throw new RuntimeException(s"Bad: ${any}")
  }

  test("algoritm Bioler - Mure") {

    val inString = "in book we trust"
    val templ = "book"

    val str = inString.replaceAll(
      getString(
        "rtm-processing.similarTerminalCache.replaceToWhitespaceRegexp"),
      "#")
    //println(str)

    val tokens = inString.tokenize
    tokens foreach println

  }

  test("read TERMINALS_WCATS4MFMD_V") {

    val sqlText =
      "SELECT STERMINAL, SCATEGORYCODE, SCATEGORYNAME, MERCHANT_NAME FROM TERMINALS_WCATS4MFMD_V"
    val proc = HC
      .process[Terminal](sqlText, HPS.setFetchSize(dsAdvisa.Config.FetchSize))

    /*val res: ArrayDyn[TerminalParamsDyn] = ArrayDyn(proc
          .transact(dsAdvisa.DoobieDataSourceTransactor)
          .runLog
          .unsafePerformSync
          .sortWith(_.sTerminal < _.sTerminal).map(TerminalParams(_): TerminalParamsDyn).toSeq:_*)

        println(res.toPrettyString)*/

    val res = proc
      .transact(dsAdvisa.DoobieDataSourceTransactor)
      .runLog
      .run
      .sortWith(_.sTerminal < _.sTerminal)
      .foreach(println)
  }

  test("read TERMINALS_WCATS4MFMD_V1") {
    val cache = new SimilarTerminalCache

    val sqlText1 = "SELECT splace FROM tstv_TErminal"
    val proc1 =
      HC.process[String](sqlText1, HPS.setFetchSize(dsLogger.Config.FetchSize))
    proc1
      .transact(dsLogger.DoobieDataSourceTransactor)
      .runLog
      .run
      .take(2)
      .sortWith(_ < _)
      .foreach { item =>
        cache.findSimilarTerminal(item) match {
          case None =>
          //println(s"Not Exact match: ${item}")
          case Some(res) =>
            println(s"Not Exact match: From tstv_TErminal: $item,   ${res}")
        }
      }
  }

  test("read TERMINALS_WCATS4MFMD_V2") {
    val cache = new SimilarTerminalCache

    val item = "510000000456"
    cache.findSimilarTerminal(item) match {
      case None =>
      //println(s"Not Exact match: ${item}")
      case Some(res) =>
        println(s"Not Exact match: From tstv_TErminal: $item,   ${res}")
    }
  }
}
