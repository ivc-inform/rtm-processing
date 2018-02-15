package com.simplesys.components.formItems

import com.simplesys.SmartClient.Forms.formsItems.{AbstractFormItemWithButtonsCompanion, DurationValue, FormItemWithButtons}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js

trait GroupValue extends JSObject {
    val silenceDuration: JSUndefined[DurationValue]
    val testSilenceDuration: JSUndefined[DurationValue]
    val groupName: JSUndefined[String]
}

@js.native
trait GroupItem extends FormItemWithButtons {
    var silenceDuration: JSUndefined[DurationValue]
    var testSilenceDuration: JSUndefined[DurationValue]
    var groupName: JSUndefined[String]
    var nameValueMap: JSUndefined[IscArray[String]]
}

@js.native
abstract trait AbstractGroupItemCompanion extends AbstractFormItemWithButtonsCompanion {
}

