package com.simplesys.SmartClient.Layout.props.tabSet

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.{TabSet, TabSetSS}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js._

class TabProps extends AbstractClassProps {
    var afterRemoveTabs: ScOption[Function0[_]] = ScNone
    var canClose: ScOption[Boolean] = ScNone
    var canEditTitle: ScOption[Boolean] = ScNone
    var canReorder: ScOption[Boolean] = ScNone
    var closeIcon: ScOption[SCImgURL] = ScNone
    var closeIconSize: ScOption[Int] = ScNone
    var disabled: ScOption[Boolean] = ScNone
    var funcMenu: ScOption[MenuSS] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var ID: ScOption[String] = ScNone
    var name: ScOption[String] = ScNone
    var pane: ScOption[Canvas] = ScNone
    var paneMargin: ScOption[Int] = ScNone
    var pickerTitle: ScOption[HTMLString] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var tabDeselected: ScOption[js.Function7[TabSetSS, Int, Canvas, JSUndefined[ID], Tab, Tab, JSUndefined[String], Boolean]] = ScNone
    var tabSelected: ScOption[js.Function6[TabSetSS, Int, Canvas, JSUndefined[ID], Tab, JSUndefined[String], _]] = ScNone
    var title: ScOption[HTMLString] = ScNone
    var width: ScOption[Int] = ScNone
}
