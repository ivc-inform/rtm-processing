package com.simplesys.js.components.drawing.drawItems

import com.simplesys.System.JSUndefined

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait CounterVariableValue extends VariableValue {
    var startValue: JSUndefined[Int]
}

object CounterVariable extends BaseCompanionDrawItem

@js.native
trait CounterVariable extends Variable {
}

@js.native
abstract trait AbstractCounterVariableCompanion extends AbstractVariableItemCompanion {
}

