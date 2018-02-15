package com.simplesys.components.formItems

import com.simplesys.System.JSUndefined
import com.simplesys.components.drawing.drawItems.AbstractVariableItemCompanion

import scala.scalajs.js

@js.native
trait CounterAccumItem extends VariableItem {
    var startValue: JSUndefined[Int]
}

@js.native
abstract trait AbstractCounterAccumItemCompanion extends AbstractVariableItemCompanion {
}


