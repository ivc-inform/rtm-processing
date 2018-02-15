package com.simplesys.components.drawing.drawItems

import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object SendMessage extends BaseCompanionDrawItem

trait SendMessageValue extends VariableValue {
    val textMessage: JSUndefined[String]
}

@js.native
trait SendMessage extends Variable{

}

@js.native
abstract trait AbstractSendMessageCompanion extends AbstractVariableItemCompanion {
}

