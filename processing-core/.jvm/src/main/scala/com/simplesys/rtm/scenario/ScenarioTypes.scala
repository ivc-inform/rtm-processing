package com.simplesys.rtm.scenario

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

import akka.persistence.fsm.PersistentFSM.FSMState
import com.simplesys.common.Strings._
import com.simplesys.log.{Logger, Logging}
import com.simplesys.rtm.ScenarioCompanion.DT

import scala.concurrent.duration.FiniteDuration

sealed trait BaseState extends FSMState {
    val identifier: String = getClass.getSimpleName.replace("$", strEmpty).unCapitalize
}

trait StateUnified extends BaseState

sealed trait BaseData {
    val data: Map[String, Option[DataValue]]
}

case class DataUnified(data: Map[String, Option[DataValue]] = Map.empty[String, Option[DataValue]]) extends BaseData {
    def apply(name: String, value: DataValue): DataUnified = {
        //if (data.keys.exists(_ == name)) throw new RuntimeException(s"Element already $name exists.")
        this.copy(data = data ++ Map(name → Some(value)))
    }

    def apply(name: String, value: Option[DataValue]): DataUnified = {
        //if (data.keys.exists(_ == name)) throw new RuntimeException(s"Element already $name exists.")
        this.copy(data = data ++ Map(name → value))
    }


    def asString(name: String): Option[String] = data.get(name).flatMap {
        case Some(StringValue(value)) ⇒ Some(value)
        case _ ⇒ None
    }

    def asDouble(name: String): Option[Double] = data.get(name).flatMap {
        case Some(DoubleValue(value)) ⇒ Some(value)
        case Some(IntValue(value)) ⇒ Some(value.toDouble)
        case Some(LongValue(value)) ⇒ Some(value.toDouble)
        case _ ⇒ None
    }

    def asLong(name: String): Option[Long] = data.get(name).flatMap {
        case Some(LongValue(value)) ⇒ Some(value)
        case Some(DoubleValue(value)) ⇒ Some(value.toLong)
        case Some(IntValue(value)) ⇒ Some(value.toLong)
        case _ ⇒ None
    }

    def asInt(name: String): Option[Int] = data.get(name).flatMap {
        case Some(IntValue(value)) ⇒ Some(value)
        case _ ⇒ None
    }

    def asBoolean(name: String): Option[Boolean] = data.get(name).flatMap {
        case Some(BooleanValue(value)) ⇒ Some(value)
        case _ ⇒ None
    }

    def asLocalDateTime(name: String): Option[LocalDateTime] = data.get(name).flatMap {
        case Some(LocalDateTimeValue(value)) ⇒ Some(value)
        case _ ⇒ None
    }
}

sealed trait AbstractEvent

case class SetDataUnified(data: DataUnified) extends AbstractEvent


abstract trait AbstractPersistedTimer extends AbstractEvent with Logging {
    val name: String

    val scenario: ScenarioData

    val startedAt: LocalDateTime

    val startOverdueTimer: Boolean

    val cancelIt: Boolean

    def timerName = getClass.getSimpleName.replace("Timer", strEmpty).unCapitalize

    def duration: FiniteDuration

    def alarmAt: LocalDateTime = startedAt.plusNanos(duration.toNanos)

    def isAlarmInFuture = alarmAt.isAfter(LocalDateTime.now())

    def timeout: FiniteDuration = FiniteDuration(alarmAt.getMillis - LocalDateTime.now().getMillis, TimeUnit.MILLISECONDS)

    def getAlarmEvent = TimerAlarm(this)

    def log() {

        val logger: Logger = Logger(this getClass)
        val _timeout = timeout.toMillis
        def timerString =
            if (cancelIt) "timer cancelled"
            else s"timer ${if (_timeout > 0) "set" else "fired"} "

        logger debug (s"${newLine.newLine} /////////////////// Initialization Timer: $timerName $timerString /////////////////////////////////////////////////")
        logger debug (s"Scenario: $scenario")

        logger debug s"Timeout value: ${DT(timeout.toMillis)}, timer object is ${this.toString}"

        logger debug (s"$newLine /////////////////// End Initialization Timer: $timerName /////////////////////////////////////////////////".newLine)
    }
}

case class TimerUnified(name: String, cancelIt: Boolean, startedAt: LocalDateTime, startOverdueTimer: Boolean, duration: FiniteDuration, scenario: ScenarioData) extends AbstractPersistedTimer

case class TimerAlarm(timer: AbstractPersistedTimer)

trait AbstractInnerEvent

case class InnerEventUnified() extends AbstractInnerEvent with AbstractEvent

case object InnerEvent

