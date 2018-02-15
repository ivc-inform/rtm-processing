package com.simplesys.app

import com.simplesys.rtm.common.MessageMFMD
import com.telinform.comfmdi.interobj.ComfmdiMfmdOutMessage
import org.joda.time.LocalDateTime

object ComDIConversion {
  implicit def ComdiOutMessage2MessageMFMD(
      message: ComfmdiMfmdOutMessage): MessageMFMD =
    MessageMFMD(
      connectorOutMessageId = message.getConnectorOutMessageId,
      timestamp = new LocalDateTime(message.getTimestamp.getTime),
      accountCode = message.getAccountCode,
      connectorCode = message.getConnectorCode,
      subject = message.getSubject,
      operatorUnitRegionCode = message.getOperatorUnitRegionCode,
      address = message.getAddress,
      priority = message.getPriority,
      text = message.getText,
      startTime = Option(message.getStartTime).map(new LocalDateTime(_)),
      stopTime = Option(message.getStopTime).map(new LocalDateTime(_)),
      comment = Option(message.getComment).filter(_.length > 0),
      tot = Option(message.getTot),
      opt = Option(message.getOpt),
      `type` = message.getType
    )

  implicit def MessageMFMD2ComdiOutMessage(
      message: MessageMFMD): ComfmdiMfmdOutMessage = {
    val res = new ComfmdiMfmdOutMessage()

    res setConnectorOutMessageId message.connectorOutMessageId
    res setTimestamp new java.util.Date(message.timestamp.toDateTime.getMillis)
    res setAccountCode message.accountCode
    res setConnectorCode message.connectorCode
    res setSubject message.subject
    res setOperatorUnitRegionCode message.operatorUnitRegionCode
    res setAddress message.address
    res setPriority message.priority
    res setText message.text
    res setStartTime message.startTime.map(ts => new java.util.Date(ts.toDateTime.getMillis)).orNull
    res setStopTime message.stopTime.map(ts => new java.util.Date(ts.toDateTime.getMillis)).orNull
    res setComment message.comment.orNull
    res setTot message.tot.orNull
    res setOpt message.opt.map(o => s"$o;").getOrElse("") + "mfmdTest=(true)" // todo: key mfmdTest can be already set
    res setType message.`type`
    res
  }
}
