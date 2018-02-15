package com.simplesys.components.drawing.drawItems.props

import com.simplesys.SmartClient.Drawing.drawItem.props.DrawOvalProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.drawing.drawItems.StopState
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js._

class StopStateProps extends DrawOvalProps {
    type classHandler <: StopState

    override val `type`: ScOption[String] = "StopState".opt
}
