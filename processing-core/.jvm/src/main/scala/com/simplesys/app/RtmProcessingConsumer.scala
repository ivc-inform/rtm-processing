package com.simplesys.app

import java.util

import akka.actor.ActorRef
import com.simplesys.actors.AppConfig
import com.simplesys.app.ComDIConversion._
import com.simplesys.rtm.common.MessageMFMD
import com.telinform.comfmdi.interobj.ComfmdiMfmdOutMessage
import com.telinform.comfmdi.mfmd.impl.outmessage.ComfmdiMfmdOutMessageConsumer
import scala.collection.JavaConverters._

object RtmProcessingConsumer {
    def apply(processorRef: ActorRef): RtmProcessingConsumer = new RtmProcessingConsumer(processorRef)
}

class RtmProcessingConsumer(val processorRef: ActorRef) extends ComfmdiMfmdOutMessageConsumer {
    def processMessage(m: ComfmdiMfmdOutMessage): Unit = {
        if (m.getText != null && m.getText.length > 0) {
            val mfmd: MessageMFMD = m
            processorRef ! mfmd
        }
    }

    override def consumeComfmdiMfmdOutMessage(comfmdiMfmdOutMessage: ComfmdiMfmdOutMessage): Unit = processMessage(comfmdiMfmdOutMessage)


    override def consumeComfmdiMfmdOutMessageList(comfmdiMfmdOutMessageList: util.List[_]): Unit = {

        comfmdiMfmdOutMessageList.asScala.foreach {
            case comfmdiMfmdOutMessage: ComfmdiMfmdOutMessage =>
                processMessage(comfmdiMfmdOutMessage)
            case _ =>
        }
    }

    override def isUp: Boolean = AppConfig.isUp.get()
}
