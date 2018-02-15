package com.simplesys.rtm.common

import java.time.LocalDateTime

import ru.mfms.mfmd.integration.category.CategoryParamRefWValue
import ru.mfms.mfmd.integration.valueholder.ValueHolder
import ru.simplesys.dmprocess.templates.FixedTemplate

case class OnlineBankEntry(isSimulatedEvent: Boolean,
                           isSendResponse: Boolean,
                           address: String,
                           text: String,
                           timestamp: LocalDateTime,
                           acnCode: String,
                           cnrCode: String)
    extends ParsedMessage

object OnlineBankEntry {
  def unapply(orig: MessageMFMD,
              tmpl: FixedTemplate,
              extractedValues: List[CategoryParamRefWValue])
    : Option[OnlineBankEntry] = {
    val transactionParams = (tmpl.defaultValues ++ extractedValues)
      .filter(_.categoryName == "Интернет-банк")
    val paramVal = transactionParams
      .find(_.paramName == "Тип операции в интернет-банке")
      .map(_.paramValue.asInstanceOf[ValueHolder[String]].value)

    val isOnline = paramVal.contains("E")
    if (isOnline)
      Some(
        OnlineBankEntry(
          orig.isSimulatedMessage,
          orig.isSendSMS,
          orig.address,
          orig.text,
          orig.startTime.getOrElse(orig.timestamp),
          orig.accountCode,
          orig.connectorCode
        )
      )
    else None
  }
}
