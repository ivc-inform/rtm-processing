package com.simplesys.components.formItems

import com.simplesys.SmartClient.Forms.formsItems.{AbstractFormItemWithButtonsCompanion, FormItemWithButtons}
import com.simplesys.System.Types.ValueMap
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

trait LoggingValue extends JSObject {
    val sStage: JSUndefined[String]
    val sParentStage: JSUndefined[String]
    val fBonusBase: JSUndefined[String]
    val idBonusMessage: JSUndefined[String]
    val sBonusMessage: JSUndefined[String]
    val idMarketingMessage: JSUndefined[String]
    val sMarketingMessage: JSUndefined[String]
    val sEvent: JSUndefined[String]
    val sActionType: JSUndefined[String]
}

@js.native
trait LoggingItem extends FormItemWithButtons {
    var sStage: JSUndefined[String]
    var sParentStage: JSUndefined[String]
    var fBonusBase: JSUndefined[String]
    var idBonusMessage: JSUndefined[String]
    var sBonusMessage: JSUndefined[String]
    var idMarketingMessage: JSUndefined[String]
    var sMarketingMessage: JSUndefined[String]
    var sEvent: JSUndefined[String]
    var sActionType: JSUndefined[String]
    var loggingValueMap4String: JSUndefined[ValueMap]
    var loggingValueMap4Double: JSUndefined[ValueMap]
    var loggingValueMap4Long: JSUndefined[ValueMap]
}

@js.native
abstract trait AbstractLoggingItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

