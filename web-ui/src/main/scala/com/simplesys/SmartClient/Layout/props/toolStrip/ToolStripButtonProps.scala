package com.simplesys.SmartClient.Layout.props.toolStrip

import com.simplesys.SmartClient.Control.props.ButtonProps
import com.simplesys.SmartClient.Layout.toolStrip.ToolStripButton
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js._

class ToolStripButtonProps extends ButtonProps {
    type classHandler <: ToolStripButton
    showDisabledIcon = false.opt

    var  enableIf: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    autoDraw = false.opt
}
