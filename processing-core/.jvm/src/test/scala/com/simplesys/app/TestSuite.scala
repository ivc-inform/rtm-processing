package com.simplesys.app

import java.util.UUID

import com.simplesys.actors.AppConfig._
import com.simplesys.advisa.{SimilarTerminalCache, Terminal, TerminalParams}
import com.simplesys.log.Logging
import com.simplesys.misc.Helper._
import doobie.imports._
import org.joda.time.LocalDateTime
import org.scalatest.FunSuite

import scala.collection.mutable
import scalaz.Scalaz._

class TestSuite extends FunSuite with Logging {

  test("make phone nambers list") {

    case class Message(phoneNumber: String)

    //import com.simplesys.doobie._

    val proc = HC.process[Message]("SELECT SADDRESS FROM DT_COM_DATA_DM",
                                   ().point[PreparedStatementIO])

    proc
      .transact(dsLoggerTransactor)
      .take(1000)
      .runLog
      .run
      .distinct
      .zipWithIndex
      .foreach {
        case (Message(phoneNumber), index) =>
          println(s"#${index}: ${phoneNumber}")
        case _ =>
      }
  }

  test("select with Option") {
    case class Message(phoneNumber: String, startTime: Option[LocalDateTime])

    //import com.simplesys.doobie._
    import com.simplesys.doobie._
    import scalaz.Scalaz._

    val proc =
      HC.process[Message]("SELECT SADDRESS, DTSTART_TIME FROM DT_COM_DATA_DM",
                          ().point[PreparedStatementIO])

    proc.transact(dsLoggerTransactor).take(1000).runLog.run.distinct.foreach {
      case Message(phoneNumber, None) => logger.debug(s"${phoneNumber}")
      case Message(phoneNumber, Some(startTime)) =>
        logger.debug(s"${phoneNumber} ${startTime}")
      case _ =>
    }
  }

  test("object SimilarTerminalCashe") {
    val a = new SimilarTerminalCache
    /*logger debug s"${a.findSimilarTerminal("SMARTFOOD-TERMINAL 7")}"
        logger debug s"${a.findSimilarTerminal("RESTAURANT COFFEEMANIA")}"
        logger debug s"${a.findSimilarTerminal("GASTRONOM")}"*/
    logger debug s"${a.findSimilarTerminal("RU/Moscow/WWW.SPEEDYLINE.RU")}"
  }

  test("qweeee") {
    //import com.simplesys.doobie._
    import scalaz.Scalaz._

    val proc = HC.process[Terminal]("""SELECT
              |    ID,
              |    CREATED,
              |    LAST_UPDATED,
              |    VERSION,
              |    LOCATION_LAT,
              |    LOCATION_LONG,
              |    MERCH_CITY,
              |    MERCH_COUNTRY,
              |    MERCH_NAME,
              |    BANK_ID,
              |    MERCH_UNIT_ID,
              |    TRANS_CAT_ID,
              |    BASED_ON_TERMINAL_ID,
              |    REGION_NAME,
              |    REGION_DESCRIPTION,
              |    CREATED_BY_LOADER
              |FROM
              |    TERMINALS""".stripMargin,
                                    ().point[PreparedStatementIO])

    val listTetminal = proc
      .transact(dsAdvisaTransactor)
      .take(2)
      .runLog
      .run
      .map(item => TerminalParams(item))
  }

  test("isWeirdNameForTerminal") {
    val str = "RU/Moscow/WWW.SPEEDYLINE.RU"
    logger debug s"${str.isWeirdNameForTerminal}"
  }

  test("simplify") {
    val str = "RU/Moscow/WWW.SPEEDYLINE.RU"
    logger debug s"${str.simplify}"
  }

  test("HumanMap") {
    ReaderPhoneDataFile.humanMap("sbrtm_phones.csv").foreach(println)
    ReaderPhoneDataFile.readPhones("control_phones.csv").foreach(println)
  }

  test("cont") {
    import scala.util.continuations._

    val sessions = new mutable.HashMap[UUID, Int => Unit]

    def ask(prompt: String): Int @cps[Unit] =
      shift { k: (Int => Unit) =>
        {
          val id = UUID.randomUUID()
          printf("%s\nrespond with: submit(0x%x, ...)\n", prompt, id)
          sessions += id -> k
        }
      }

    def go =
      reset {
        println("Welcome!")
        val first = ask("Please give me a number")
        val second = ask("Please enter another number")
        printf("The sum of your numbers is: %d\n", first + second)
      }

    go
  }

  test("123456") {}
}
