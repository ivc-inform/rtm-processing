package com.simplesys.SmartClient.Layout.props.sectionStack

import com.simplesys.SmartClient.Foundation.{Canvas, StretchImg}
import com.simplesys.SmartClient.Layout.Layout
import com.simplesys.SmartClient.Layout.props.HLayoutProps
import com.simplesys.System.Types.{HTMLString, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ImgSectionHeaderProps extends HLayoutProps {
    var background: ScOption[StretchImg] = ScNone
    var clipTitle: ScOption[Boolean] = ScNone
    var controls: ScOption[Seq[Canvas]] = ScNone
    var controlsLayout: ScOption[Layout] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var iconAlign: ScOption[String] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconOrientation: ScOption[String] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var title: ScOption[HTMLString] = ScNone
    var titleClipped: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var titleHover: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
}
