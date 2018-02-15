package com.simplesys.advisa

import com.simplesys.actors.AppConfig
import com.simplesys.log.Logging
import org.scalatest.{FunSuite, Matchers}

import scala.collection._

class TestTerminalVocabulary extends FunSuite with Matchers with Logging {
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
    val tokenSet = mutable.Set.empty[String]
    advisaTerminalCache._listTerminal.foreach { tp =>
      tokenSet ++= tp.terminalNameTokens
    }

    logger.info(s"token count: ${tokenSet.size}")
  }
}
