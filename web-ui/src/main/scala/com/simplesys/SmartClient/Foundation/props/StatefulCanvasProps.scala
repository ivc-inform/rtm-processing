package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Foundation.{Canvas, StatefulCanvas}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.IconOrientation.IconOrientation
import com.simplesys.System.Types.SelectionType.SelectionType
import com.simplesys.System.Types.State._
import com.simplesys.System.Types.VerticalAlignment._
import com.simplesys.System.Types._

class StatefulCanvasProps extends CanvasProps {
    type classHandler <: StatefulCanvas

    var actionType: ScOption[SelectionType] = ScNone
    var align: ScOption[Alignment] = ScNone
    var autoFit: ScOption[Boolean] = ScNone
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var iconHeight: ScOption[Int] = ScNone
    var iconOrientation: ScOption[IconOrientation] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var iconSpacing: ScOption[Int] = ScNone
    var iconStyle: ScOption[CSSStyleName] = ScNone
    var iconWidth: ScOption[Int] = ScNone
    var ignoreRTL: ScOption[Boolean] = ScNone
    var overCanvas: ScOption[Canvas] = ScNone
    var overCanvasConstructor: ScOption[String] = ScNone
    var overCanvasDefaults: ScOption[Canvas] = ScNone
    var radioGroup: ScOption[String] = ScNone
    var redrawOnStateChange: ScOption[Boolean] = ScNone
    var selected: ScOption[Boolean] = ScNone
    var showDisabled: ScOption[Boolean] = ScNone
    var showDown: ScOption[Boolean] = ScNone
    var showDownIcon: ScOption[Boolean] = ScNone
    var showFocused: ScOption[Boolean] = ScNone
    var showFocusedAsOver: ScOption[Boolean] = ScNone
    var showFocusedIcon: ScOption[Boolean] = ScNone
    var showOverCanvas: ScOption[Boolean] = ScNone
    var showRollOver: ScOption[Boolean] = ScNone
    var showRollOverIcon: ScOption[Boolean] = ScNone
    var showRTLIcon: ScOption[Boolean] = ScNone
    var showSelectedIcon: ScOption[Boolean] = ScNone
    var state: ScOption[State] = ScNone
    var title: ScOption[HTMLString] = ScNone
    var valign: ScOption[VerticalAlignment] = ScNone
}
