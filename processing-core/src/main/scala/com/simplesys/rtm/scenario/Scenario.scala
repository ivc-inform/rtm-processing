package com.simplesys.rtm.scenario

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{PoisonPill, Props}
import akka.persistence.fsm.PersistentFSM
import com.simplesys.actors.Reaper.{ShutdownIt, ShutdownScenario, ShuttedDown}
import com.simplesys.actors.{AppConfig, SMSBodyHttp}
import com.simplesys.app.Human
import com.simplesys.common.Strings._
import com.simplesys.log.Logging
import com.simplesys.logging.LoggingScenario
import com.simplesys.rtm.common.PreProcessedMessage
import org.joda.time.DateTime

import scala.reflect._

object ScenarioData {
    def apply(name: String) = new ScenarioData(name, ScenarioVersion.default)
    def unapply(name: String): Option[ScenarioData] = {

        val pies0 = name.split("_")

        try {
            pies0 match {
                case Array(scenarioName, major, minor, build) =>
                    Some(
                        ScenarioData(
                            scenarioName,
                            ScenarioVersion(major.toUpperCase.replace("V", strEmpty).toInt,
                                minor.toInt,
                                build.toInt)))

                case Array(scenarioName, major, minor) =>
                    Some(
                        ScenarioData(
                            scenarioName,
                            ScenarioVersion(major.toUpperCase.replace("V", strEmpty).toInt, minor.toInt, -1)))

                case Array(scenarioName, major) =>
                    Some(
                        ScenarioData(
                            scenarioName,
                            ScenarioVersion(major.toUpperCase.replace("V", strEmpty).toInt, 0, -1)))

                case _ => None

            }
        } catch {
            case e: NumberFormatException =>
                None
        }
    }
}

case class PhoneData(address: String)

case class ScenarioData(scenarioName: String, scenarioVersion: ScenarioVersion) extends Ordering[ScenarioData] {
    override def compare(x: ScenarioData, y: ScenarioData): Int = (x.scenarioName + x.scenarioVersion.toString) compare (y.scenarioName + y.scenarioVersion.toString)
    override def toString: String = s"Scenario: $scenarioName ${scenarioVersion.toString}"
}

object ScenarioVersion {
    def apply(major: Int, minor: Int, build: Int) = new ScenarioVersion(major = major, minor = minor, build = Some(build), idScenario = -1)
    def default = new ScenarioVersion(major = 1, minor = 0, idScenario = -1)
}

case class ScenarioVersion(major: Int, minor: Int, build: Option[Int] = None, idScenario: Long) {
    override def toString: String = s"v${major}_${minor}${if (build.isDefined) "_" + build.get else strEmpty}"
}

abstract class Scenario[S <: BaseState, D, E](implicit ev: ClassTag[E]) extends PersistentFSM[S, D, E] with LoggingScenario {

    implicit val scenarioData: ScenarioData

    def scenarioRun: ScenarioRunProps

    def companion: ScenarioCompanion

    val prodMode: Boolean

    val persistenceId = scenarioRun.getPersistenceId

    /*override val journalPluginId: String = ""
    override val snapshotPluginId: String = ""*/

    def calcGroup( counter: AtomicInteger, scaleLine: String, offset: Int): Int = {
        def transformScaleLine(in: String): String = {
            var res = ""
            var sum = -1
            var step = 0
            in.split(":").foreach {
                item ⇒
                    val _item = item.toInt
                    sum += _item
                    if (step == 0)
                        res += s"$sum"
                    else
                        res += s":$sum"
                    step += 1
            }
            res
        }

        var res = 0
        var break = false
        val scaleSeq: Array[(Int, Int)] = transformScaleLine(scaleLine).split(":").map(_.toInt).zipWithIndex.map(item ⇒ (item._1, item._2 + offset))
        val scaleIterator: Iterator[(Int, Int)] = scaleSeq.iterator

        while (scaleIterator.hasNext && !break) {
            val item: (Int, Int) = scaleIterator.next()
            val counterItem = counter.get()

            if (counterItem <= item._1) {
                break = true
                res = item._2
                counter.incrementAndGet()
            }

            if (res == 0 && !scaleIterator.hasNext) {
                res = scaleSeq(0)._2
                counter.set(offset)
                counter.incrementAndGet()
            }
        }

        //println(res)
        res

    }

    def sendMessage(message: String, data: DataUnified)(implicit phoneNumber: PhoneData): String = {
        AppConfig.actorSelSmsSender ! SMSBodyHttp(phoneNumber, message, data.asBoolean("isSendResponse").getOrElse(false))
        message
    }

    override implicit def domainEventClassTag: ClassTag[E] = ev

    def applyEvent(domainEvent: E, currentData: D): D =
        domainEvent match {
            case ev: AbstractPersistedTimer =>
                (ev.cancelIt, ev.isAlarmInFuture) match {
                    case (false, true) =>
                        ev.log()
                        setTimer(name = ev.timerName,
                            msg = ev.getAlarmEvent,
                            timeout = ev.timeout)
                    case (false, false) =>
                        if (ev.startOverdueTimer) {
                            ev.log()
                            self ! ev.getAlarmEvent
                        }
                    case (true, true) =>
                        cancelTimer(ev.timerName)
                    case (true, false) =>
                }

                currentData

            case ev: AbstractInnerEvent =>
                if (recoveryFinished) self ! InnerEvent

                currentData

            case _ =>
                currentData
        }


    whenUnhandled {
        case Event(ShutdownIt, _) =>
            context stop self
            stay

        case Event(ShutdownScenario(idScenario, _), _) =>
            if (idScenario == scenarioData.scenarioVersion.idScenario) {
                self ! PoisonPill
                stop
            } else
                stay

        case Event(evx, data) =>
            logger.warning(s"Unhandled: Event: $evx, StateData: $data, state: $stateName")
            stay
    }

    onTermination {
        case x@StopEvent(PersistentFSM.Normal, _, _) =>
            val end = DateTime.now()

            val silenceEnd = companion.scenarioGroup.getSilenceEnd(scenarioRun.started, end)
            val endDates = ScenarioRunEndDates(end, silenceEnd)

            logger debug s"endDates: $endDates"

            context.parent ! ShuttedDown(scenarioRun.copy(endDates = Some(endDates)))

            //todo if we need to send also to ScenarioMap
            logger.debug(s"${self.path.name} stopped with ${x.toString}")

        case msg =>
            logger.debug(s"${self.path.name} stopped with ${msg.toString}")
    }


    /*override def receiveCommand: Receive = {
        case ShutdownScenario(idScenario) ⇒
            if (idScenario == scenarioData.scenarioVersion.idScenario)
                context stop self
    }*/

    override protected def onRecoveryFailure(cause: Throwable, event: Option[Any]): Unit = {
        super.onRecoveryFailure(cause, event)
        log error(cause, s"RecoveryFailure: ${cause.getStackTrace().mkString("", newLine, newLine)} on ${event.toString}")
    }
}

trait ScenarioCompanion extends Logging {
    val punct = """[!"\#$%&'()*+,\-./:;<=>?@\[\\\]^_`{|}~]"""

    def scenarioData: ScenarioData

    def scenarioGroup: ScenarioGroup

    def entryCheck(msg: PreProcessedMessage): Boolean
    def props(scenarioRun: ScenarioRunProps, human: Option[Human]): Props

    @inline
    def toRegExp(str: String): String = str.split("%").filter(_ != "") mkString (".*")
}
