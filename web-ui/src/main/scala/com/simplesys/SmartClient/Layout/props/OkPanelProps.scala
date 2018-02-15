package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.OkPanel
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class OkPanelProps extends HPanelSSProps {

    import scala.scalajs.js.{ThisFunction0, ThisFunction1}

    type classHandler <: OkPanel
    var okFunction: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var okCaption: ScOption[String] = ScNone
    var okIcon: ScOption[SCImgURL] = ScNone
    var ownerDestroy: ScOption[Boolean] = ScNone
    var ownerHide: ScOption[Boolean] = ScNone
    var timeoutClose: ScOption[Int] = ScNone
}
