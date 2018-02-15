package com.simplesys.components.formItems

import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait SendMessageItem extends VariableItem {
    var textMessage: JSUndefined[String]
}

@js.native
abstract trait AbstractSendMessageItemCompanion extends AbstractSubProgramItemCompanion


