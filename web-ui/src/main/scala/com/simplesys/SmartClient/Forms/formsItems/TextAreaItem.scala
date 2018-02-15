package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.TEXTAREA_WRAP.TEXTAREA_WRAP
import com.simplesys.System.Types.{SCClassName, void}

import scala.scalajs.js

@js.native
trait TextAreaItem extends FormItem {

    import com.simplesys.System.JSAny

    var browserAutoCapitalize: Boolean
    var browserAutoCorrect: Boolean
    def deselectValue(start: Boolean = js.native): void
    var editProxyConstructor: SCClassName
    var emptyStringValue: JSAny
    var enforceLength: Boolean
    var formatOnBlur: Boolean
    def getEnteredValue(): String
    def getSelectionRange(): IscArray[Int]
    var length: Int
    var minHeight: Int
    var printFullText: Boolean
    def selectValue(): void
    def setSelectionRange(start: Int, end: Int): void
    var showHintInField: Boolean
    var usePlaceholderForHint: Boolean
    var wrap: TEXTAREA_WRAP
}

@js.native
abstract class AbstractTextAreaItemCompanion extends AbstractFormItemCompanion



