package com.simplesys.rtm.common


import java.time.LocalDateTime

import akka.routing.ConsistentHashingRouter.ConsistentHashable

final case class MessageMFMD(
                              connectorOutMessageId: Long,
                              timestamp: LocalDateTime,
                              accountCode: String,
                              connectorCode: String,
                              subject: String,
                              operatorUnitRegionCode: String,
                              address: String,
                              priority: Int,
                              text: String,
                              startTime: Option[LocalDateTime],
                              stopTime: Option[LocalDateTime],
                              comment: Option[String],
                              tot: Option[String],
                              opt: Option[String],
                              idGroupTest: Long = 0,
                              `type`: String
) extends ConsistentHashable {
  override def consistentHashKey: Any = address

  def isSimulatedMessage: Boolean =
    opt.exists(_.indexOf("mfmdTest=(true)") > -1)

  def isSendSMSDuringSimulation: Boolean =
    opt.exists(_.indexOf("mfmdSend=(true)") > -1)

  def isSendSMS =
    !isSimulatedMessage || isSimulatedMessage && isSendSMSDuringSimulation
}
