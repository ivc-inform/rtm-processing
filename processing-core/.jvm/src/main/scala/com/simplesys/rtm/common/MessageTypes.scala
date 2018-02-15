package com.simplesys.rtm.common

import java.time.LocalDateTime

import com.simplesys.advisa.SimilarTerminalCache
import ru.mfms.mfmd.integration.category.CategoryParamRefWValue
import ru.simplesys.dmprocess.templates.FixedTemplate

trait ParsedMessage {
  def address: String
  def text: String
  def timestamp: LocalDateTime
  def acnCode: String
  def cnrCode: String
  def isSimulatedEvent: Boolean
  def isSendResponse: Boolean
}

object ParsedMessage {
  def unapply(orig: MessageMFMD,
              tmpl: FixedTemplate,
              extractedValues: List[CategoryParamRefWValue],
              advisaPOSCache: SimilarTerminalCache): Option[ParsedMessage] = {
    Transaction.unapply(orig, tmpl, extractedValues, advisaPOSCache) orElse
      OnlineBankEntry.unapply(orig, tmpl, extractedValues)
  }
}
