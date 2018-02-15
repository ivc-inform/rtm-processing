package com.simplesys.actors

import akka.actor.ActorRef
import akka.event.{LookupClassification, EventBus}
//import com.simplesys.actors.CommonActorAddons.LoadCommand

sealed trait ControlBusMsgTopic

case object ControlBusMsgTopicInitialization extends ControlBusMsgTopic

case class ControlBusMsg(topic: ControlBusMsgTopic, payload: Any)

case object ControlBusMsgTopicLoadCommands extends ControlBusMsgTopic
case object ControlBusMsgTopicSaveCommands extends ControlBusMsgTopic

object ControlBusMsg {
//    def apply(payload: LoadCommand): ControlBusMsg = ControlBusMsg(ControlBusMsgTopicLoadCommands, payload)
//    def apply(payload: SaveCommand): ControlBusMsg = ControlBusMsg(ControlBusMsgTopicSaveCommands, payload)
}

class ControlBusImpl extends EventBus with LookupClassification {
  type Event = ControlBusMsg
  type Classifier = ControlBusMsgTopic
  type Subscriber = ActorRef

  // is used for extracting the classifier from the incoming events
  override protected def classify(event: Event): Classifier = event.topic

  // will be invoked for each event for all subscribers which registered themselves
  // for the event’s classifier
  override protected def publish(event: Event, subscriber: Subscriber): Unit = {
    subscriber ! event.payload
  }

  // must define a full order over the subscribers, expressed as expected from
  // `java.lang.Comparable.compare`
  override protected def compareSubscribers(a: Subscriber,
                                            b: Subscriber): Int =
    a.compareTo(b)

  // determines the initial size of the index data structure
  // used internally (i.e. the expected number of different classifiers)
  override protected def mapSize: Int = 128

}
