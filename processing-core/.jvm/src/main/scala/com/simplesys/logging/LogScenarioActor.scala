package com.simplesys.logging

import java.sql.{SQLException, SQLRecoverableException}
import java.time.LocalDateTime
import java.util.concurrent.Executors

import akka.actor._
import com.simplesys.actors.AppConfig._
import com.simplesys.actors.{AppConfig, ControlBusImpl, Reaper}
import com.simplesys.akka.event.Logging
import com.simplesys.common.Strings._
import com.simplesys.rtm.scenario.{PhoneData, ScenarioData}
import doobie.imports._

import scala.concurrent.ExecutionContext
import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

object ScenarioTraceItem {
    def apply(sStage: String = strEmpty,
              sParentStage: Option[String] = None,
              fBonusBase: Option[Double] = None,
              idBonusMessage: Option[Long] = None,
              sBonusMessage: Option[String] = None,
              idMarketingMessage: Option[Long] = None,
              sMarketingMessage: Option[String] = None,
              sEvent: Option[String] = None,
              sActionType: Option[String] = None)(implicit sAddress: PhoneData, sScenario: ScenarioData): ScenarioTraceItem = new ScenarioTraceItem(
        sAddress = Some(sAddress.address),
        sScenario = sScenario.scenarioName,
        sStage = if (sStage.length > 255) sStage.substring(0, 254) else sStage,
        sParentStage = sParentStage,
        fBonusBase = fBonusBase,
        idBonusMessage = idBonusMessage,
        sBonusMessage = sBonusMessage,
        idMarketingMessage = idMarketingMessage,
        sMarketingMessage = sMarketingMessage,
        sEvent = sEvent,
        sActionType = sActionType
    )
}

case class ScenarioTraceItem(
                              sAddress: Option[String],
                              sScenario: String,
                              sStage: String,
                              sParentStage: Option[String],
                              fBonusBase: Option[Double],
                              idBonusMessage: Option[Long],
                              sBonusMessage: Option[String],
                              idMarketingMessage: Option[Long],
                              sMarketingMessage: Option[String],
                              sActionType: Option[String],
                              sEvent: Option[String]
                            )

object LogSession {
    def getScenarioTraceNewSession(item: ScenarioTraceItem, testMode: Boolean): ConnectionIO[Long] = {
        val ts = new java.sql.Timestamp(LocalDateTime.now().getMillis)
        if (testMode)
            sql"""insert into rtm_ScenarioTrace_debug (
                          id,
                          dtTimestamp,
                          sAddress,
                          sScenario,
                          sStage,
                          sParentStage,
                          fBonusBase ,
                          ididBonusMessage,
                          sBonusMessage,
                          ididMarketingMessage,
                          sMarketingMessage,
                          sEvent,
                          sActionType
                      ) values (
                          Seq_Log.nextval,
                          $ts,
                          ${item.sAddress},
                          ${item.sScenario},
                          ${item.sStage},
                          ${item.sParentStage},
                          ${item.fBonusBase},
                          ${item.idBonusMessage},
                          ${item.sBonusMessage},
                          ${item.idMarketingMessage},
                          ${item.sMarketingMessage},
                          ${item.sEvent},
                          ${item.sActionType}
                      )""".update.withUniqueGeneratedKeys[Long]("id")
        else
            sql"""insert into rtm_ScenarioTrace (
                                      id,
                                      dtTimestamp,
                                      sAddress,
                                      sScenario,
                                      sStage,
                                      sParentStage,
                                      fBonusBase ,
                                      ididBonusMessage,
                                      sBonusMessage,
                                      ididMarketingMessage,
                                      sMarketingMessage,
                                      sEvent,
                                      sActionType
                                  ) values (
                                      Seq_Log.nextval,
                                      $ts,
                                      ${item.sAddress},
                                      ${item.sScenario},
                                      ${item.sStage},
                                      ${item.sParentStage},
                                      ${item.fBonusBase},
                                      ${item.idBonusMessage},
                                      ${item.sBonusMessage},
                                      ${item.idMarketingMessage},
                                      ${item.sMarketingMessage},
                                      ${item.sEvent},
                                      ${item.sActionType}
                                  )""".update.withUniqueGeneratedKeys[Long]("id")
    }
}

case class LogScenarioTrace(cmd: ConnectionIO[Long])

object LogScenarioActor {
    def props(implicit controlBus: ControlBusImpl): Props = Props(new LogScenarioActor)
}

class LogScenarioActor(implicit val controlBus: ControlBusImpl) extends Actor with Logging {

    val writersCount = getInt(s"$configPrefix.logger.writersCount")
    val retryPeriod = getInt(s"$configPrefix.logger.retryPeriod") * 1000
    val localExecContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(writersCount))

    override def preStart(): Unit = {
        super.preStart()
        logger.info(s"${self.path} started")
    }

    override def postStop(): Unit = {
        super.postStop()
        logger.info(s"${self.path} stopped")
    }

    override def receive: Receive = {
        case Reaper.ShutdownIt =>
            context stop self

        case LogScenarioTrace(cmd) =>
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
                                          //logger.debug(s"${self.path.name} logged to rtm_ScenarioTrace, cmd: ${cmd}, inserted record ID : ${success}")
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

class LoggingScenarioClass(val sytem: ActorSystem, val testMode: Boolean) {
    private val loggerScenario = system.actorSelection(AppConfig.actorPathScenarioTrace)

    def write(item: ScenarioTraceItem, recoveryRunning: Boolean): Unit = {
        if (!recoveryRunning)
            loggerScenario ! LogScenarioTrace(LogSession.getScenarioTraceNewSession(item, testMode))
    }
}

trait LoggingScenario extends Logging {
    self: Actor =>

    val testMode: Boolean

    val system = context.system
    val loggerScenario = new LoggingScenarioClass(system, testMode)
}
