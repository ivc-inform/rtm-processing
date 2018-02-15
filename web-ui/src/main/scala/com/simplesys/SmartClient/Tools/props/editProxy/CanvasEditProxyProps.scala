package com.simplesys.SmartClient.Tools.props.editProxy

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.editProxy.CanvasEditProxy
import com.simplesys.SmartClient.Tools.props.EditProxyProps
import com.simplesys.System._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.{ThisFunction0, ThisFunction1}

class CanvasEditProxyProps extends EditProxyProps {
    type classHandler <: CanvasEditProxy

    var dropOut: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var drop: ScOption[ThisFunction1[classHandler,IscArray[JSAny], Boolean]] = ScNone
    var willAcceptDrop: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseDown: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseUp: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
}
