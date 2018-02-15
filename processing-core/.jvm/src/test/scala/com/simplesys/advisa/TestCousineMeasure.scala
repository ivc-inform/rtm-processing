package com.simplesys.advisa

import com.simplesys.actors.AppConfig
import com.simplesys.log.Logging
import org.scalatest.{FunSuite, Matchers}

import scala.collection._

class TestCousineMeasure extends FunSuite with Matchers with Logging {
  def loadTerminals(sql: String): IndexedSeq[String] = {
    import doobie.imports._

    //fetchsize from advisa cause of long list
    val proc = HC.process[String](
      sql,
      HPS.setFetchSize(AppConfig.dsAdvisa.Config.FetchSize))

    val _listTerminal: IndexedSeq[String] =
      proc.transact(AppConfig.dsLoggerTransactor).runLog.run

    _listTerminal
  }

  test("loadAdvisaCache") {
    logger.info("loading advisa POS cache")
    val advisaTerminalCache = new SimilarTerminalCache()

    logger.info("loading terminal lists")
    val allTerminals = loadTerminals("select splace from pos_terminal")
    val noMatchTerminals =
      loadTerminals("select splace from pos_terminal_nomatch")

    var matched: Int = 0
    var noMatched: Int = 0

    logger.info("starting test")
    val startTime = System.nanoTime()
    noMatchTerminals.foreach { t =>
      val optLookup = advisaTerminalCache.findSimilarTerminalSingleThreaded(t)
      optLookup match {
        case Some(l) => matched += 1
        case None => noMatched += 1
      }
    }
    val endTime = System.nanoTime()

    val duration = endTime - startTime
    logger.info(
      s"total terminals: ${noMatchTerminals.size}, matched with cousine: $matched, not matched: $noMatched, timing: ${duration / 1000000}ms")
  }
}
