package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.StretchImgProps
import com.simplesys.System.Types.Cursor.Cursor
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js._

class SplitbarProps extends StretchImgProps {
    var canCollapse: ScOption[Boolean] = ScNone
    var canCollapseOnTap: ScOption[Boolean] = ScNone
    var capSize: ScOption[Int] = ScNone
    var hResizeCursor: ScOption[Cursor] = ScNone
    var invertClosedGripIfTargetAfter: ScOption[Boolean] = ScNone
    var showClosedGrip: ScOption[Boolean] = ScNone
    var target: ScOption[Canvas] = ScNone
    var targetAfter: ScOption[Boolean] = ScNone
    var vResizeCursor: ScOption[Cursor] = ScNone
}
