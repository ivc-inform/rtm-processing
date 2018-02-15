package com.simplesys.js.components.drawing.drawItems

import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait CalcGroupValue extends VariableValue {
    val scaleLine: JSUndefined[String]
    val counter: JSUndefined[CounterVariableValue]
}

object CalcGroup extends BaseCompanionDrawItem

@js.native
trait CalcGroup extends Variable {
    val scaleLine: JSUndefined[String]
}

@js.native
abstract trait AbstractCalcGroupCompanion extends AbstractVariableItemCompanion {
}

