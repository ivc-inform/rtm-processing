package com.simplesys.regex

import com.simplesys.advisa.{
  TerminalParams,
  Terminal,
  SimilarTerminalFindResult
}
import com.simplesys.log.Logging
import com.simplesys.misc.Helper._
import org.scalatest.FunSuite

object compareAdvisa {

  val listTerminal: IndexedSeq[TerminalParams] = Vector(
    TerminalParams(
      Terminal("NL/HELP.UBER.COM/UBER BV",
               Some("taxi"),
               Some("taxi"),
               Some("UBER"))))
  val terminalMap: Map[String, TerminalParams] =
    listTerminal.map(tp => (tp.sTerminal, tp))(collection.breakOut)

  def getTerminalByName(
      merchantName: String): Option[SimilarTerminalFindResult] = {
    val searchStr = merchantName.toUpperCase

    terminalMap
      .get(searchStr)
      .map(
        tp =>
          SimilarTerminalFindResult(tp.sTerminal,
                                    tp.sCategoryCode,
                                    tp.sCategoryName,
                                    tp.merchantName,
                                    1)) orElse findSimilarTerminal(searchStr)
  }

  //.filter(_.similarity >= 0.9)

  def findSimilarTerminal(
      merchantName: String): Option[SimilarTerminalFindResult] = {

    if (isNotNul(merchantName) && merchantName.length >= minimumTerminalNameLength && !merchantName.isWeirdNameForTerminal) {
      val countSlashes: Int = merchantName countMatches "/"
      val tokenSet = merchantName.simplify.tokenize

      var merchantUnitMaxValue = minimumSimilarityMerchantUnitMetricValue
      var categoryMaxValue = minimumSimilarityCategoryMetricValue

      var merchantUnitDescriptor: TerminalParams = null
      var categoryDescriptor: TerminalParams = null

      var break = false
      listTerminal.foreach { terminalParam =>
        if (!break) {
          val value = tokenSet compare terminalParam.terminalNameTokens

          if (value >= merchantUnitMaxValue && countSlashes > 0 && countSlashes == terminalParam.countSlashes) {
            merchantUnitMaxValue = value
            merchantUnitDescriptor = terminalParam

            break = value >= 1.0
          } else if (value >= categoryMaxValue) {
            categoryMaxValue = value
            categoryDescriptor = terminalParam
          } else if (terminalParam.merchantNameTokens.nonEmpty) {
            val value = tokenSet compare terminalParam.merchantNameTokens
            if (value >= categoryMaxValue) {
              categoryMaxValue = value
              categoryDescriptor = terminalParam
            }
          }
        }
      }

      if (merchantUnitDescriptor != null && countSlashes > 0 && countSlashes == merchantUnitDescriptor.countSlashes) {
        Some(
          SimilarTerminalFindResult(merchantUnitDescriptor.sTerminal,
                                    merchantUnitDescriptor.sCategoryCode,
                                    merchantUnitDescriptor.sCategoryName,
                                    merchantUnitDescriptor.merchantName,
                                    merchantUnitMaxValue))
      } else if (categoryDescriptor != null) {
        Some(
          SimilarTerminalFindResult(categoryDescriptor.sTerminal,
                                    categoryDescriptor.sCategoryCode,
                                    categoryDescriptor.sCategoryName,
                                    categoryDescriptor.merchantName,
                                    categoryMaxValue))
      } else
        None
    } else
      None
  }

}

class TestSuite extends FunSuite with Logging {
  test("0") {
    val found = compareAdvisa.getTerminalByName("RU/Moscow/UBER BV")
    println(found)
  }

  test("1") {
    "\\s+".r.split("1111 dkfjifdi dcfdufudifudi").foreach(println)

  }
}
