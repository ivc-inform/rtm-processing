package com.simplesys.actors.test

import akka.actor.{Actor, ActorSystem, Props}
import akka.persistence.fsm.PersistentFSM
import akka.persistence.fsm.PersistentFSM.FSMState

import scala.reflect._
import scala.util.Random

final case class SetNumber(num: Integer)

sealed trait State extends FSMState
case object Idle extends State {
  override def identifier: String = "Idle"
}
case object Active extends State {
  override def identifier: String = "Active"
}

sealed trait GenData {
  def add(number: Integer): GenData
}

case object EmptyData extends GenData {
  def add(number: Integer) = AddDataNumbers(Vector(number))
}

final case class AddDataNumbers(queue: Seq[Integer]) extends GenData {
  def add(number: Integer) = AddDataNumbers(queue :+ number)
}

sealed trait DomainEvt
case class SetNumberEvt(num: Integer) extends DomainEvt

class Generator extends Actor with PersistentFSM[State, GenData, DomainEvt] {

  override def applyEvent(domainEvent: DomainEvt,
                          currentData: GenData): GenData = {
    domainEvent match {
      case SetNumberEvt(num) => currentData.add(num)
    }
  }

  override def persistenceId: String = "generator"

  override def domainEventClassTag: ClassTag[DomainEvt] = classTag[DomainEvt]

  startWith(Idle, EmptyData)

  when(Idle) {
    case Event(SetNumber(num), EmptyData) =>
      goto(Active) applying SetNumberEvt(num)
  }

  when(Active) {
    case Event(SetNumber(num), numbers: GenData) =>
      println(numbers)
      stay applying SetNumberEvt(num)
  }
}

/*object TestFSM extends App {

    val system = ActorSystem()

    val actor = system.actorOf(Props[Generator])

    actor ! SetNumber(Random.nextInt())
    actor ! SetNumber(Random.nextInt())
    actor ! SetNumber(Random.nextInt())
    actor ! SetNumber(Random.nextInt())
    actor ! SetNumber(Random.nextInt())

    Thread.sleep(1000)
    system.terminate()

}*/
