package com.simplesys.js.components.formItems

import com.simplesys.System.JSUndefined
import com.simplesys.js.components.drawing.drawItems.CounterVariableValue

import scala.scalajs.js

@js.native
trait CalcGroupItem extends VariableItem {
    var scaleLine: JSUndefined[String]
    var counter: JSUndefined[CounterVariableValue]

    var counterVariableName: String
    var calcGroupVariableName: String
}

@js.native
abstract trait AbstractCalgGroupItemCompanion extends AbstractVarriableCompanion {
}

