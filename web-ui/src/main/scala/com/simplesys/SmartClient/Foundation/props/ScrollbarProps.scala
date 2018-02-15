package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Foundation.{StretchImg, Canvas, StretchItem}
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js._

class ScrollbarProps extends StretchImgProps {
    var allowThumbDownState: ScOption[Boolean] = ScNone
    var allowThumbOverState: ScOption[Boolean] = ScNone
    var autoEnable: ScOption[Boolean] = ScNone
    var btnSize: ScOption[Int] = ScNone
    var cornerImg: ScOption[StretchItem] = ScNone
    var cornerSize: ScOption[Int] = ScNone
    var cornerSrc: ScOption[SCImgURL] = ScNone
    var endImg: ScOption[StretchImg] = ScNone
    var endThumbOverlap: ScOption[Int] = ScNone
    var scrollTarget: ScOption[Canvas] = ScNone
    var showCorner: ScOption[Boolean] = ScNone
    var showTrackButtons: ScOption[Boolean] = ScNone
    var showTrackEnds: ScOption[Boolean] = ScNone
    var startImg: ScOption[StretchItem] = ScNone
    var startThumbOverlap: ScOption[Int] = ScNone
    var thumbInset: ScOption[Int] = ScNone
    var thumbMinSize: ScOption[Int] = ScNone
    var thumbOverlap: ScOption[Int] = ScNone
    var trackEndHeight: ScOption[Int] = ScNone
    var trackEndImg: ScOption[StretchItem] = ScNone
    var trackEndWidth: ScOption[Int] = ScNone
    var trackImg: ScOption[StretchItem] = ScNone
    var trackStartImg: ScOption[StretchItem] = ScNone
}
