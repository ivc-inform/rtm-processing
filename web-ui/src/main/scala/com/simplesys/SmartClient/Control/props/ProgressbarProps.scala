package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Foundation.props.StretchImgProps
import com.simplesys.System.Types.void
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ProgressbarProps extends StretchImgProps {

    import com.simplesys.SmartClient.Control.Progressbar

    type classHandler <:  Progressbar

    var breadth: ScOption[Int] = ScNone
    var length: ScOption[Int] = ScNone
    var percentChanged: ScOption[js.Function1[void, _]] = ScNone
    var percentDone: ScOption[Int] = ScNone
}
