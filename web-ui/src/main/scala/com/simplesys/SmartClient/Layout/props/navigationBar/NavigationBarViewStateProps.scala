package com.simplesys.SmartClient.Layout.props.navigationBar

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.Types.HTMLString
import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js._

class NavigationBarViewStateProps extends AbstractClassProps {
    var alwaysShowLeftButtonTitle: ScOption[Boolean] = ScNone
    var controls: ScOption[Seq[Canvas]] = ScNone
    var leftButtonTitle: ScOption[HTMLString] = ScNone
    var shortLeftButtonTitle: ScOption[HTMLString] = ScNone
    var showLeftButton: ScOption[Boolean] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
