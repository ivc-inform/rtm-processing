package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Layout.{Layout, Splitbar}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.LayoutPolicy.LayoutPolicy
import com.simplesys.System.Types.LayoutResizeBarPolicy.LayoutResizeBarPolicy
import com.simplesys.System.Types.LocatorStrategy.LocatorStrategy
import com.simplesys.System.Types.LocatorTypeStrategy.LocatorTypeStrategy
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types.{MultiAutoChild, void}
import com.simplesys.option.{DoubleAlignment, ScNone, ScOption}

import scala.scalajs.js

class LayoutProps extends CanvasProps {
    type classHandler <: Layout

    var align: ScOption[DoubleAlignment[Alignment, VerticalAlignment]] = ScNone
    var animateMembers: ScOption[Boolean] = ScNone
    var animateMemberTime: ScOption[Int] = ScNone
    var canDropComponents: ScOption[Boolean] = ScNone
    var defaultLayoutAlign: ScOption[DoubleAlignment[Alignment, VerticalAlignment]] = ScNone
    var defaultResizeBars: ScOption[LayoutResizeBarPolicy] = ScNone
    var dropLine: ScOption[Canvas] = ScNone
    var dropLineThickness: ScOption[Int] = ScNone
    var enforcePolicy: ScOption[Boolean] = ScNone
    var hPolicy: ScOption[LayoutPolicy] = ScNone
    var layoutBottomMargin: ScOption[Int] = ScNone
    var layoutLeftMargin: ScOption[Int] = ScNone
    var layoutMargin: ScOption[Int] = ScNone
    var layoutRightMargin: ScOption[Int] = ScNone
    var layoutTopMargin: ScOption[Int] = ScNone
    var leaveScrollbarGap: ScOption[LocatorStrategy] = ScNone
    var locateMembersType: ScOption[LocatorTypeStrategy] = ScNone
    var managePercentBreadth: ScOption[Boolean] = ScNone
    var memberOverlap: ScOption[Int] = ScNone
    var members: ScOption[Seq[Canvas]] = ScNone
    var membersChanged: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var membersMargin: ScOption[Int] = ScNone
    var minMemberSize: ScOption[Int] = ScNone
    var paddingAsLayoutMargin: ScOption[Boolean] = ScNone
    var placeHolderDefaults: ScOption[Canvas] = ScNone
    var placeHolderProperties: ScOption[Canvas] = ScNone
    var resizeBar: ScOption[Splitbar with MultiAutoChild] = ScNone
    var resizeBarClass: ScOption[String] = ScNone
    var resizeBarSize: ScOption[Int] = ScNone
    var reverseOrder: ScOption[Boolean] = ScNone
    var showDragPlaceHolder: ScOption[Boolean] = ScNone
    var showDropLines: ScOption[Boolean] = ScNone
    var stackZIndex: ScOption[String] = ScNone
    var vertical: ScOption[Boolean] = ScNone
    var vPolicy: ScOption[LayoutPolicy] = ScNone
}
