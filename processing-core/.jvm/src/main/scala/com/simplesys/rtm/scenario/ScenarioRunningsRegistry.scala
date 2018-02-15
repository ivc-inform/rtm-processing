package com.simplesys.rtm.scenario

import com.simplesys.log.Logging
import com.simplesys.rtm.common.PreProcessedMessage

import scala.Seq
import scala.collection._

case class RunningStateForGroup(var currentlyRunning: Option[ScenarioRunProps], inSilence: mutable.Map[Long, ScenarioRunProps]) {
    def recalcForNow: Option[RunningStateForGroup] = {
        currentlyRunning match {
            case Some(scr) if scr.isEnded =>
                if (scr.isInSilence)
                    inSilence.put(scr.scenarioData.scenarioVersion.idScenario, scr)
                currentlyRunning = None
            case _ =>
        }

        inSilence.retain { case (_, scenarioRunProps) => scenarioRunProps.isInSilence }

        if (isEmpty)
            None
        else
            Some(this)
    }

    def isEmpty = currentlyRunning.isEmpty && inSilence.isEmpty
}

object RunningStateForGroup {
    def apply(scenarioRunProps: ScenarioRunProps): RunningStateForGroup =
        if (!scenarioRunProps.isEnded)
            RunningStateForGroup(Some(scenarioRunProps), mutable.Map.empty)
        else
            RunningStateForGroup(None, mutable.Map(scenarioRunProps.scenarioData.scenarioVersion.idScenario -> scenarioRunProps))
}

class ScenarioRunningsRegistry(_lst: immutable.Seq[ScenarioRunProps]) extends Logging {
    val index = mutable.Map.empty[String, mutable.Map[String, RunningStateForGroup]]

    // constructing index
    _lst.foreach(addScenarioRun(_))

    def addScenarioRun(scenarioRun: ScenarioRunProps, doRecalc: Boolean = false): Unit = {

        logger.debug(s"before add scenarioRun: ${index.get(scenarioRun.phone).toString}")

        if (doRecalc)
            getCleanedRunnings(scenarioRun.phone)

        index.get(scenarioRun.phone) match {
            case Some(mapRunningStateForGroup) =>
                mapRunningStateForGroup.get(scenarioRun.groupName) match {
                    case Some(state) =>
                        if (!scenarioRun.isEnded) {
                            state.currentlyRunning match {
                                case Some(alreadySC) =>
                                    logger.debug(
                                        s"scenarioRun ${scenarioRun.toString} is running but ${alreadySC.toString} already registered as running. Replacing.")
                                    state.currentlyRunning = Some(scenarioRun)
                                case None =>
                                    state.currentlyRunning = Some(scenarioRun)
                            }
                        } else {
                            if (scenarioRun.isInSilence)
                                state.inSilence.put(scenarioRun.scenarioData.scenarioVersion.idScenario, scenarioRun)
                            else
                                logger.debug(s"scenarioRun ${scenarioRun.toString} is out of silence, but we trying to add it. Ignoring")
                        }
                    case None =>
                        mapRunningStateForGroup.put(scenarioRun.groupName, RunningStateForGroup(scenarioRun))
                }
            case None =>
                index.put(
                    scenarioRun.phone,
                    mutable.Map(scenarioRun.groupName -> RunningStateForGroup(scenarioRun)))
        }

        logger.debug(
            s"after add scenarioRun: ${index.get(scenarioRun.phone).toString}")

    }

    def shutdownScenarioRun(scenarioRun: ScenarioRunProps, doRecalc: Boolean): Unit = {

        logger.debug(s"before shutdown scenarioRun: ${index.get(scenarioRun.phone).toString}")

        if (doRecalc)
            getCleanedRunnings(scenarioRun.phone)

        if (scenarioRun.endDates.isDefined) {
            index.get(scenarioRun.phone) match {
                case Some(mapRunningStateForGroup) =>
                    mapRunningStateForGroup.get(scenarioRun.groupName) match {
                        case Some(state) =>
                            if (state.currentlyRunning.exists(_.getPersistenceId == scenarioRun.getPersistenceId)) {
                                state.currentlyRunning = None
                                if (scenarioRun.isInSilence)
                                    state.inSilence.put(scenarioRun.scenarioData.scenarioVersion.idScenario, scenarioRun)

                                if (state.isEmpty) {
                                    mapRunningStateForGroup remove scenarioRun.groupName

                                    if (mapRunningStateForGroup.isEmpty)
                                        index.remove(scenarioRun.phone)
                                }
                            } else
                                logger.debug(s"received strange shutdown message ${scenarioRun.toString}, but found scenario ${state.currentlyRunning.toString}")
                        case None =>
                            logger.debug(s"received strange shutdown message ${scenarioRun.toString}, but no such scenario group for this phone")
                    }
                case None =>
                    logger.debug(s"received strange shutdown message ${scenarioRun.toString}, but no such phone")
            }
        } else
            logger.debug(s"received strange shutdown message ${scenarioRun.toString}, but there is no end dates there!")

        logger.debug(s"after shutdown scenarioRun: ${index.get(scenarioRun.phone).toString}")
    }

    // refactor it to more efficient way!
    def getScenarioStream: immutable.Stream[ScenarioRunProps] = getScenarioList.toStream

    def getScenarioList: immutable.Seq[ScenarioRunProps] = {
        val builder = immutable.Seq.newBuilder[ScenarioRunProps]
        for ((p, mp) <- index;
             (g, state) <- mp) yield {
            state.currentlyRunning.foreach(builder += _)
            state.inSilence.values.foreach(builder += _)
        }
        builder.result()
    }

    def getCleanedRunnings(phone: String): Option[mutable.Map[String, RunningStateForGroup]] = {
        // fast cleanup of old scenario runnings
        val scMapOpt = index.get(phone)
        scMapOpt match {
            case Some(mp) =>
                mp.retain { case (_, scenarioRun) => scenarioRun.recalcForNow.nonEmpty }
                if (mp.isEmpty) {
                    index remove phone
                    None
                } else
                    Some(mp)
            case None =>
                None
        }
    }

    def getCurrentlyRunning(phone: String, doRecalc: Boolean = false): immutable.Seq[ScenarioRunProps] = {

        if (doRecalc)
            getCleanedRunnings(phone)

        val running: immutable.Seq[ScenarioRunProps] =
            index
              .get(phone)
              .map(_.values.flatMap(_.currentlyRunning)(collection.breakOut))
              .getOrElse(immutable.Seq.empty)

        logger.debug(s"currently runnings scenarioRun: ${running.toString}")

        running
    }

    def getScenariosPossible2Run(phone: String,
                                 registry: ScenarioRegistry,
                                 evObj: PreProcessedMessage,
                                 doRecalc: Boolean = false): immutable.Set[ScenarioCompanion] = {
        if (doRecalc)
            getCleanedRunnings(phone)

        val phoneScenarios = index.get(phone)
        logger.debug(s"phoneScenarios seq is: ${phoneScenarios.toString()}")

        // set of group names
        val runningGroups: immutable.Set[String] = phoneScenarios match {
            case Some(mp) =>
                mp.values.flatMap(_.currentlyRunning.map(_.groupName))(collection.breakOut)
            case None => immutable.Set.empty
        }
        logger.debug(s"runningGroups seq is: ${runningGroups.toString()}")

        //map of group -> scenarioRun names
        val silenced: immutable.Map[String, Set[String]] = phoneScenarios match {
            case Some(mp) =>
                mp.values
                  .flatMap(_.inSilence.values.map(scenarioRun => (scenarioRun.groupName, scenarioRun.scenarioData.scenarioName)))
                  .groupBy { case (grN, scN) => grN }
                  .mapValues { v => v.map { case (_, scN) => scN }(collection.breakOut)}
            case None => immutable.Map.empty
        }
        logger.debug(s"Silenced seq is: ${silenced.toString()}")

        val groupToCheck = registry.scenarioGroupMap.keySet diff runningGroups
        logger.debug(s"GroupToCheck seq is: ${groupToCheck.toString()}")

        val scenariosPossible2Run: scala.collection.immutable.Set[ScenarioCompanion] = groupToCheck.flatMap {
            groupName =>
                val key: ScenarioGroup = registry.scenarioGroupMap(groupName)
                logger.debug(s"ScenarioGroup is: ${key.name}")

                val scSeq = registry.scenarioCompanionMap.getOrElse(key.name, Seq.empty)
                logger.debug(s"scSeq seq is: ${groupToCheck.toString}")

                val preFiltered: Iterable[ScenarioCompanion] = scSeq.filter(scc =>
                    silenced.get(scc.scenarioGroup.name) match {
                        case Some(scName) => !scName.contains(scc.scenarioData.scenarioName)
                        case None => true
                    })

                logger.debug(s"prefiltered scenarios to run: $preFiltered")

                preFiltered.find(_ entryCheck evObj)
        }

        logger.debug(s"found new scenarios to run: ${scenariosPossible2Run.toString}")
        logger.debug(s"state is : ${index.get(evObj.msg.address).toString}")
        scenariosPossible2Run
    }

    def getPhonesCount: Int = index.keySet.size

}
