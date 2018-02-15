package com.simplesys.app

import com.simplesys.rtm.common.MessageMFMD
import com.telinform.comfmdi.interobj.ComfmdiMfmdOutMessage
import com.simplesys.common.Common._

object ComDIConversion {
  implicit def ComdiOutMessage2MessageMFMD(
      message: ComfmdiMfmdOutMessage): MessageMFMD =
    new MessageMFMD(
      connectorOutMessageId = message.getConnectorOutMessageId,
      timestamp = message.getTimestamp,
      accountCode = message.getAccountCode,
      connectorCode = message.getConnectorCode,
      subject = message.getSubject,
      operatorUnitRegionCode = message.getOperatorUnitRegionCode,
      address = message.getAddress,
      priority = message.getPriority,
      text = message.getText,
      startTime = message.getStartTime,
      stopTime = message.getStopTime,
      comment = message.getComment,
      tot = message.getTot,
      opt = message.getOpt,
      `type` = message.getType
    )

  implicit def MessageMFMD2ComdiOutMessage(
      message: MessageMFMD): ComfmdiMfmdOutMessage = {
    val res = new ComfmdiMfmdOutMessage()

    res setConnectorOutMessageId message.connectorOutMessageId
    res setTimestamp message.timestamp
    res setAccountCode message.accountCode
    res setConnectorCode message.connectorCode
    res setSubject message.subject
    res setOperatorUnitRegionCode message.operatorUnitRegionCode
    res setAddress message.address
    res setPriority message.priority
    res setText message.text
    res setStartTime message.startTime
    res setStopTime message.stopTime
    res setComment message.comment.orNull
    res setTot message.tot.orNull
    res setOpt message.opt.map(o => s"$o;").getOrElse("") + "mfmdTest=(true)" // todo: key mfmdTest can be already set
    res setType message.`type`
    res
  }
}
