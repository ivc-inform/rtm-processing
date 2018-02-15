package com.simplesys.logging

import java.sql.{SQLException, SQLRecoverableException}
import java.util.concurrent.Executors

import com.simplesys.actors.AppConfig._
import com.simplesys.config.Config
import com.simplesys.log.Logging
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext
import scala.util.control.Breaks._
import scalaz.{-\/, \/-}

class TestSuit extends FunSuite with Config with Logging {

  test("LogScenarioTrace1") {
    val writersCount = getInt(s"rtm-processing.logger.writersCount")
    val localExecContext = ExecutionContext.fromExecutorService(
      Executors.newFixedThreadPool(writersCount))

    val s = ScenarioTraceItem(
      sAddress = Some("address"),
      sScenario = "scenario",
      sStage = "state",
      sParentStage = Some("parent"),
      fBonusBase = Some(1.2666),
      idBonusMessage = Some(1000L),
      sBonusMessage = Some("Bonus"),
      idMarketingMessage = Some(55555L),
      sMarketingMessage = Some("message"),
      sEvent = None,
      sActionType = None
    )

    val cmd = LogSession.getScenarioTraceNewSession(s)
    scalaz.concurrent.Task
      .fork(dsLoggerTransactor
        .trans(cmd)
        .attempt
        .map(res => {
          res match {
            case -\/(failure) =>
              failure match {
                case e: SQLRecoverableException =>
                  logger.error(e, e.getMessage)

                case e: SQLException =>
                  logger.error(e, e.getMessage)

                case e: Throwable =>
                  logger.error(e, e.getMessage)

                  break

              }
            case \/-(success) =>
              logger.debug(
                s" logged to rtm_ScenarioTrace, cmd: ${cmd}, result: ${res}")
              break
          }
        }))(localExecContext)
      .run
  }

  test("2") {
    import LoggerAppActor._

    val res: Option[LevelLoggingA] = getTraceLevelFetchSession("qqqqqq.qqqq")

    logger debug s"${res.getOrElse(-1)}"
  }

  test("3") {
    val major = 1
    val minor = 2
    val build = 30
    val date = DateTime.now()

    def toString = {
      val fmt = DateTimeFormat.forPattern("YMd h:m:s")
      s"$major.$minor.$build-v${fmt print date}"
      //s"$major.$minor.$build-v${date}"
    }

    println(toString)
  }
}
