package com.simplesys.actors

import akka.actor.ActorRef
import akka.persistence.PersistentActor
import com.simplesys.actors.SeqGenerator.{GetNewLimit, TakeNewLimit}
import com.simplesys.log.Logging

sealed trait SeqGeneratorMsg

object SeqGenerator {

  case class GetNewLimit(count: Int, replyTo: ActorRef) extends SeqGeneratorMsg

  case class TakeNewLimit(lower: BigInt, upper: BigInt)
}

trait SeqGenerator extends Logging { selfi: PersistentActor =>

  protected var seq: BigInt = 0

  def seqUpdateState(msg: GetNewLimit): Unit =
    seq += msg.count

  def seqReceiveRecover: Receive = {
    case evt: GetNewLimit => seqUpdateState(evt)
  }

  def getState: BigInt = seq
  def setState(s: BigInt): Unit = seq = s

  def seqReceiveCommand: Receive = {
    case m @ GetNewLimit(cnt, who) =>
      persist(m) { ev =>
        seqUpdateState(ev)
        val respond = TakeNewLimit(seq - cnt + 1, seq)
        who ! respond
        logger.debug(
          s"${self.path}: ${who.path} requested $cnt ids, responded with $respond")
      }
  }
}
