package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Foundation.Canvas

import scala.scalajs.js

@js.native
trait OkCancelPanel extends HPanelSS {
    var okFunction: js.Function1[classHandler, _]
    var cancelFunction: js.Function1[classHandler, _]
    var okBtn: IButton
    var cancelButton: IButton
    def setDisabledOk(value: Boolean): this.type
    def setOwner(owner: Canvas): this.type
    var okCaption: String
    var cancelCaption: String
    var ownerDestroy: Boolean
    var ownerHide: Boolean
    var timeoutClose: Int
}


