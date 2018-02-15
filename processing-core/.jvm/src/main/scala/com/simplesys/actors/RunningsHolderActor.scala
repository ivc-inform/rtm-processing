package com.simplesys.actors

import akka.actor.{ActorRef, Props}
import akka.persistence.{PersistentActor, RecoveryCompleted, SnapshotOffer}
import akka.routing.Broadcast
import com.simplesys.actors.Reaper._
import com.simplesys.common.Strings._
import com.simplesys.config.Config
import com.simplesys.rtm.scenario.{ScenarioRunProps, ScenarioRunningsRegistry}

import scala.collection._

case class RunningsRef(who: ActorRef)

case object FinishRecoverAndRun

case class RunningState(state: immutable.Seq[ScenarioRunProps])

object RunningsHolderActor {
    def props(phoneHolder: ActorRef, testMode: Boolean)(implicit controlBus: ControlBusImpl): Props = Props(new RunningsHolderActor(phoneHolder, testMode))
}

class RunningsHolderActor(phoneHolder: ActorRef, testMode: Boolean)(implicit val controlBus: ControlBusImpl) extends PersistentActor with SeqGenerator with Config {

    override val journalPluginId: String = if (testMode) getString("akka.persistence.journal-test.plugin") else strEmpty

    override val snapshotPluginId: String = if (testMode) getString("akka.persistence.snapshot-store-test.plugin") else strEmpty

    override def preStart(): Unit = {
        super.preStart()
        logger.info(s"${self.path} started")
    }

    override def postStop(): Unit = {
        super.postStop()
        logger.info(s"${self.path} stopped")
    }

    private var runningsRegistry = new ScenarioRunningsRegistry(immutable.Seq.empty)

    def updateRunnings(x: RunningsLifecycle): Unit = x match {
        case ShuttedDown(scenarioRun) =>
            logger.debug(s"${self.path.name}: received ShuttedDown message for ${scenarioRun.toString}. Processing")
            runningsRegistry.shutdownScenarioRun(scenarioRun, doRecalc = true)

        case StartedUp(scenarioRun) =>
            logger.debug(s"${self.path.name}: received StartedUp message for ${scenarioRun.toString}. Processing")
            runningsRegistry.addScenarioRun(scenarioRun, doRecalc = true)
    }

    def receiveRecoverCmn: Receive = {
        case RecoveryCompleted =>
            logger.debug(s"${self.path.name}: recovering completed. There are ${runningsRegistry.getPhonesCount} phones in map. Sending to phoneHolders")

            phoneHolder ! Broadcast(RunningsRef(self))

            runningsRegistry.getScenarioStream.foreach(phoneHolder ! _)

            phoneHolder ! Broadcast(FinishRecoverAndRun)

            // todo right now it's here. But it's better to have actor for watching all init sequences of all actors
            AppConfig.isUp.set(true)
            logger.debug(s"${self.path.name}: setting isUP to true")

        case SnapshotOffer(_, (_seq: BigInt, _runnings: RunningState)) =>
            runningsRegistry = new ScenarioRunningsRegistry(_runnings.state)

            super.setState(_seq)

        case msg =>
            logger.debug(
                s"${self.path.name}: unknown message during recovery, ${msg.toString}")

    }

    def runningsReceiveRecover: Receive = {
        case x: RunningsLifecycle => updateRunnings(x)
    }

    def runningsReceiveCommand: Receive = {
        case x: RunningsLifecycle =>
            persist(x)(updateRunnings)
    }

    def receiveCommandCmn: Receive = {
        case Request2ScenarioInUse(idScenario) ⇒
            runningsRegistry.getScenarioList.find(_.scenarioData.scenarioVersion.idScenario == idScenario) match {
                case None ⇒ sender() ! ScenarioNotInUse
                case Some(_) ⇒ sender() ! ScenarioInUse
            }

        case SnapshotIt =>
            saveSnapshot(
                (super[SeqGenerator].getState, runningsRegistry.getScenarioList))

        case Reaper.ShutdownIt =>
            logger.debug(s"${self.path.name}: shutting down")
            context stop self

        case msg =>
            logger.debug(s"${self.path.name}: unknown message ${msg.toString}")
    }

    override def receiveRecover: Receive = runningsReceiveRecover orElse seqReceiveRecover orElse receiveRecoverCmn

    override def receiveCommand: Receive = runningsReceiveCommand orElse seqReceiveCommand orElse receiveCommandCmn

    override def persistenceId: String = self.path.name
}
