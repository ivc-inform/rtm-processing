package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.HTMLString

import scala.scalajs.js

@js.native
trait SelectOtherItem extends SelectItem {
    var dialogWidth: Int
    var otherTitle: String
    var otherValue: String
    var selectOtherPrompt: HTMLString
    var separatorTitle: String
    var separatorValue: String
}




