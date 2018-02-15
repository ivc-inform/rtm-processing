package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.drawing.drawItems.CounterVariable
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class CounterVariableProps extends VariableProps {
    type classHandler <: CounterVariable

    override val `type`: ScOption[String] = "CounterVariable".opt
}
