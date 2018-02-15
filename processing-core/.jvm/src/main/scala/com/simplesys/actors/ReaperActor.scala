package com.simplesys.actors

import akka.actor.ActorRef
import com.simplesys.rtm.scenario.ScenarioRunProps

object Reaper {
    case object ShutdownIt
    sealed trait RunningsLifecycle

    case class ShuttedDown(scenarioRun: ScenarioRunProps) extends RunningsLifecycle
    case class StartedUp(scenarioRun: ScenarioRunProps) extends RunningsLifecycle

    // Used by others to register an Actor for watching
    case class WatchMe(ref: ActorRef)

    case class ShutdownScenario(scenarioId: Long, status: Int)

    sealed trait ScenarioProps
    case class Request2ScenarioInUse(scenarioId: Long)
    case object ScenarioInUse extends ScenarioProps
    case object ScenarioNotInUse extends ScenarioProps
}
