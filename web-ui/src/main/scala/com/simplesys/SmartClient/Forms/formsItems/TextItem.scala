package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.CharacterCasing.CharacterCasing
import com.simplesys.System.Types._

import scala.scalajs.js

@js.native
trait TextItem extends FormItem {
    val browserAutoCapitalize: Boolean
    val browserAutoCorrect: Boolean
    val browserInputType: String
    var characterCasing: CharacterCasing
    def deselectValue(start: Boolean): void
    val editProxyConstructor: SCClassName
    var emptyStringValue: JSAny
    var enforceLength: Boolean
    var fetchMissingValues: Boolean
    var formatOnBlur: Boolean
    var formatOnFocusChange: Boolean
    def getEnteredValue(): String
    def getHint(): HTMLString
    def getSelectionRange(): IscArray[Int]
    var keyPressFilter: String
    var length: Int
    var mask: String
    var maskOverwriteMode: Boolean
    var maskPadChar: String
    var maskPromptChar: String
    var maskSaveLiterals: Boolean
    var printFullText: Boolean
    def selectValue(): void
    def setKeyPressFilter(filter: String): void
    def setSelectionRange(start: Int, end: Int): void
    var showHintInField: Boolean
    var usePlaceholderForHint: Boolean
}

@js.native
abstract class AbstractTextItemCompanion extends AbstractFormItemCompanion



