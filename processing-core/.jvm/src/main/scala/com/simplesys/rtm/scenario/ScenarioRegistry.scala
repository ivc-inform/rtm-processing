package com.simplesys.rtm.scenario

import java.time.LocalDateTime

import akka.routing.ConsistentHashingRouter.ConsistentHashable
import com.simplesys.log.Logging

import scala.Seq
import scala.collection.immutable._
import scala.concurrent.duration.Duration

trait ScenarioGroup {

    val name: String
    protected val silenceDuration: Duration

    def getSilenceEnd(started: LocalDateTime, ended: LocalDateTime) = ended.plusNanos(silenceDuration.toNanos)

}

trait ScenarioRegistry extends Logging {

    private[this] val scenarioCompanions = scala.collection.mutable.Set.empty[ScenarioCompanion]
    protected val testMode: Boolean

    def addScenarioCompanion(scenario: ScenarioCompanion, comment: String = ""): Unit = {
        if (findScenarioCompanion(scenario.scenarioData.scenarioVersion.idScenario).isEmpty) {
            scenarioCompanions += scenario
            logger debug s"Added ${scenario.scenarioData.toString + comment}"
        }
    }

    def findScenarioCompanion(idScenario: Long): Option[ScenarioCompanion] = scenarioCompanions.find(_.scenarioData.scenarioVersion.idScenario == idScenario)

    def removeScenarioCompanion(scenario: ScenarioCompanion): Unit = {
        scenarioCompanions -= scenario
        logger debug s"Removed ${scenario.scenarioData.toString}"
    }

    def removeScenarioCompanion(idScenario: Long): Boolean = {
        findScenarioCompanion(idScenario).map {
            scenario ⇒
                scenarioCompanions -= scenario
                logger debug s"Removed ${scenario.scenarioData.toString}"
                scenario
        }.isDefined
    }

    def scenarioCompanionMap: Map[String, Seq[ScenarioCompanion]] = scenarioCompanions.groupBy(_.scenarioGroup).map {
        case (key, value) ⇒ key.name -> value.toSeq
    }

    def scenarioGroupMap: Map[String, ScenarioGroup] = scenarioCompanions.map(scenario => (scenario.scenarioGroup.name, scenario.scenarioGroup))(collection.breakOut)
}

case class ScenarioRunEndDates(ended: LocalDateTime, silenceEnd: LocalDateTime)
case class ScenarioRunProps(seq: BigInt,
                            phone: String,
                            groupName: String,
                            scenarioData: ScenarioData,
                            started: LocalDateTime,
                            endDates: Option[ScenarioRunEndDates]) extends ConsistentHashable {

    override def consistentHashKey: Any = phone

    def getPersistenceId: String = s"$phone-$groupName-${scenarioData.scenarioName}-$seq"
    def getActorName: String = s"$phone-$groupName-${scenarioData.scenarioName}"

    def isEnded: Boolean = endDates.exists(_.ended.isBefore(LocalDateTime.now()))
    def isInSilence: Boolean = endDates.exists(endDates => endDates.ended.isBefore(LocalDateTime.now()) && !endDates.silenceEnd.isBefore(LocalDateTime.now()))
}
