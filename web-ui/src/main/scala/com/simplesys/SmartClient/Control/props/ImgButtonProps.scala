package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Foundation.props.ImgProps
import com.simplesys.System.Types.void
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ImgButtonProps extends ImgProps {
    var action: ScOption[js.Function1[void, _]] = ScNone
    var hiliteAccessKey: ScOption[Boolean] = ScNone
    var iconAlign: ScOption[String] = ScNone
    var labelHPad: ScOption[Int] = ScNone
    var labelVPad: ScOption[Int] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var showDisabledIcon: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
    var titleClipped: ScOption[js.Function1[void, Boolean]] = ScNone
    var titleHover: ScOption[js.Function1[void, Boolean]] = ScNone
}
