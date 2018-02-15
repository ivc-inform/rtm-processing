package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Foundation.props.StretchImgProps
import com.simplesys.System.Types.{CSSStyleName, URL, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class StretchImgButtonProps extends StretchImgProps {
    var action: ScOption[js.Function1[void, _]] = ScNone
    var capSize: ScOption[Int] = ScNone
    var hiliteAccessKey: ScOption[Boolean] = ScNone
    var iconAlign: ScOption[String] = ScNone
    var iconClick: ScOption[js.Function1[void, Boolean]] = ScNone
    var labelBreadthPad: ScOption[Int] = ScNone
    var labelHPad: ScOption[Int] = ScNone
    var labelLengthPad: ScOption[Int] = ScNone
    var labelSkinImgDir: ScOption[URL] = ScNone
    var labelVPad: ScOption[Int] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var showDisabledIcon: ScOption[Boolean] = ScNone
    var titleClipped: ScOption[js.Function1[void, Boolean]] = ScNone
    var titleHover: ScOption[js.Function1[void, Boolean]] = ScNone
    var titleStyle: ScOption[CSSStyleName] = ScNone
    var wrap: ScOption[Boolean] = ScNone
}
