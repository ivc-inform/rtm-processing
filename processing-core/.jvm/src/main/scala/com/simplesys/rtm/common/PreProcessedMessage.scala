package com.simplesys.rtm.common

final case class PreProcessedMessage(msg: MessageMFMD,
                                     parsedMsg: Option[ParsedMessage],
                                     address: String)
