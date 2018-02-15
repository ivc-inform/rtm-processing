package com.simplesys.rtm.common

import com.simplesys.advisa.{SimilarTerminalCache, SimilarTerminalFindResult}
import enumeratum.EnumEntry.Uppercase
import org.joda.time.{DateTime, LocalDate, LocalDateTime, LocalTime}
import enumeratum._
import ru.simplesys.dmprocess.templates.{
  CategoryParamRefWValue,
  FixedTemplate,
  ValueHolder
}
import ru.simplesys.dmprocess.text.Money
import ru.simplesys.dmprocess.text.currency.Currency.RUB

sealed abstract class TransactionType(override val entryName: String,
                                      val isIn: Boolean)
    extends EnumEntry
    with Uppercase

object TransactionType extends Enum[TransactionType] {
  val values = findValues

  case object Purchase extends TransactionType("P", isIn = false)
  case object Withdrawal extends TransactionType("W", isIn = false)
  case object CashIn extends TransactionType("T", isIn = true)
}

case class Transaction(isSimulatedEvent: Boolean,
                       isSendResponse: Boolean,
                       address: String,
                       text: String,
                       timestamp: LocalDateTime,
                       acnCode: String,
                       cnrCode: String,
                       rootType: TransactionType,
                       operationTimestamp: Option[LocalDateTime],
                       amount: Option[Money],
                       balance: Option[Money],
                       pos: Option[String],
                       posLookup: Option[SimilarTerminalFindResult])
    extends ParsedMessage

// todo change it!! change it!!!
object Transaction {
  def unapply(orig: MessageMFMD,
              tmpl: FixedTemplate,
              extractedValues: List[CategoryParamRefWValue],
              advisaPOSCache: SimilarTerminalCache): Option[Transaction] = {
    val transactionParams = (tmpl.defaultValues ++ extractedValues)
      .filter(_.categoryName == "Транзакционные данные")
    val paramVal = transactionParams
      .find(_.paramName == "Тип транзакции")
      .map(_.paramValue.asInstanceOf[ValueHolder[String]].value)
    val tType = for (pv <- paramVal;
                     ttype <- TransactionType.namesToValuesMap.get(pv))
      yield ttype

    tType map { tt =>
      val operationTSVal: Option[LocalDateTime] = transactionParams
        .find(_.paramName == "Дата операции из текста")
        .flatMap {
          _.paramValue.value match {
            case x: LocalDateTime => Some(x)
            case x: DateTime => Some(x.toLocalDateTime)
            case x: LocalDate =>
              Some(x.toLocalDateTime(new LocalTime(0, 0, 0, 0)))
            //case x: ValueHolder[Double] => Some(Money(x.value, RUB))
            case _ => None
          }
        }

      val amountVal =
        transactionParams.find(_.paramName == "Сумма транзакции").flatMap {
          _.paramValue.value match {
            case x: Money => Some(x)
            case x: Long => Some(Money(x, RUB))
            case x: Double => Some(Money(x, RUB))
            case _ => None
          }
        }

      val balanceVal =
        transactionParams.find(_.paramName == "Остаток по счету").flatMap {
          _.paramValue.value match {
            case x: Money => Some(x)
            case x: Long => Some(Money(x, RUB))
            case x: Double => Some(Money(x, RUB))
            case _ => None
          }
        }

      val posVal = transactionParams.find(_.paramName == "POS").flatMap {
        _.paramValue.value match {
          case x: String => Some(x)
          case _ => None
        }
      }

      val posLookupVal: Option[SimilarTerminalFindResult] =
        posVal.flatMap(advisaPOSCache.getTerminalByName _)

      Transaction(
        orig.isSimulatedMessage,
        orig.isSendSMS,
        orig.address,
        orig.text,
        orig.startTime.getOrElse(orig.timestamp),
        orig.accountCode,
        orig.connectorCode,
        tt,
        operationTSVal,
        amountVal,
        balanceVal,
        posVal,
        posLookupVal
      )
    }
  }
}
