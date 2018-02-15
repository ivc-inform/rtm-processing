package com.simplesys.logging

import java.sql.{SQLException, SQLRecoverableException}
import java.util.concurrent.Executors

import akka.actor.{Actor, ActorSystem, Props}
import com.simplesys.actors.AppConfig._
import com.simplesys.actors.Reaper
import com.simplesys.akka.event.Logging
import com.simplesys.common.Strings._
import doobie.imports._
import org.joda.time.DateTime

import scala.concurrent.ExecutionContext
import scalaz.Scalaz._
import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

//import com.simplesys.doobie._
import com.simplesys.doobie._

case class LoggerAppItem(message: String, source: String)
case class LoggerAppItemIO(cmd: ConnectionIO[Long], level: LevelLoggingA)

case class LoggerLevelAppItem(source: String, level: LevelLoggingA)
case class LoggerLevelAppItemIO(cmd: ConnectionIO[Long])

object LoggerAppActor {
    def getApplicationTraceNewSession(item: LoggerAppItem): ConnectionIO[Long] = {
        val ts = new java.sql.Timestamp(DateTime.now().getMillis)

        sql"""insert into rtm_ApplicationTrace (
                      idId,
                      dDate,
                      sMessage,
                      sSource
                  ) values (
                      Seq_Log.nextval,
                      $ts,
                      ${item.message},
                      ${item.source}
                  )""".update.withUniqueGeneratedKeys[Long]("idId")
    }

    def getTraveLevelNewSession(item: LoggerLevelAppItem): ConnectionIO[String] = {
        sql"""insert into rtm_Trace_Level (
                          sSource,
                          iLevel
                      ) values (
                          ${item.source},
                          ${item.level}
                      )""".update.withUniqueGeneratedKeys[String]("ssource")
    }

    def getTraceLevelFetchSession(source: String): Option[LevelLoggingA] = {
        def proc(source: String) = HC.process[Int](s"SELECT ILEVEL FROM RTM_TRACE_LEVEL TL WHERE TL.SSOURCE = ${source.quoted}",().point[PreparedStatementIO], dsLogger.Config.FetchSize)

        proc(source).transact(dsLoggerTransactor).runLog.run.headOption match {
            case None => None
            case Some(level) => Some(LevelLoggingA.getObject(level))
        }
    }

    def props(source: String) = Props(new LoggerAppActor(source))
}

class LoggerAppActor(val source: String) extends Actor with Logging {

    val writersCount = getInt(s"$configPrefix.logger.writersCount")
    val retryPeriod = getInt(s"$configPrefix.logger.retryPeriod") * 1000

    val localExecContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(writersCount))

    import LoggerAppActor._

    private var level: LevelLoggingA = DebugLevel
    def getLevel = level

    getTraceLevelFetchSession(source) match {
        case None =>
            val cmd = getTraveLevelNewSession(LoggerLevelAppItem(source, DebugLevel))

            Task
              .fork(dsLoggerTransactor
                .trans(cmd)
                .attempt
                .map(res =>
                    logger.debug(
                        s"${self.path.name} logged to rtm_trace_level, cmd: ${cmd}, result: ${res}")))(
                  localExecContext)
              .run

            level = DebugLevel

        case Some(_level) =>
            level = _level
    }

    override def receive: Receive = {
        case Reaper.ShutdownIt =>
            context stop self

        case LoggerAppItemIO(cmd, level) =>
            if (level.value > getLevel.value)
                Task.fork {
                    Task.delay {
                        import scala.util.control.Breaks._
                        breakable {
                            while (true) {
                                dsLoggerTransactor
                                  .trans(cmd)
                                  .attempt
                                  .map(res => {
                                      res match {
                                          case -\/(failure) =>
                                              failure match {
                                                  case e: SQLRecoverableException =>
                                                      logger error e.getMessage
                                                      Thread sleep retryPeriod

                                                  case e: SQLException =>
                                                      logger error e.getMessage
                                                      Thread sleep retryPeriod

                                                  case e: Throwable =>
                                                      logger error e.getMessage
                                                      break

                                              }
                                          case \/-(success) =>
                                              logger.info(
                                                  s"${self.path.name} logged to rtm_ApplicationTrace, cmd: ${cmd}, Inserted record ID: ${res}")
                                              break
                                      }
                                  })
                                  .run
                            }
                        }
                    }

                }(localExecContext).run
    }
}

class LoggingAppClass(val source: String, val system: ActorSystem) {

    import LoggerAppActor._

    private val actor = system.actorOf(LoggerAppActor props source)

    def trace(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            TraceLevel)
    }

    def debug(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            DebugLevel)
    }

    def info(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            InfoLevel)
    }

    def warning(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            WarningLevel)
    }

    def error(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            ErrorLevel)
    }

    def fatalError(message: String) = {
        actor ! LoggerAppItemIO(
            getApplicationTraceNewSession(LoggerAppItem(message, source)),
            FatalErrorLevel)
    }
}

trait LoggingApp {
    this: Actor =>

    def loggerApp = new LoggingAppClass(getClass.getName, context.system)

}
