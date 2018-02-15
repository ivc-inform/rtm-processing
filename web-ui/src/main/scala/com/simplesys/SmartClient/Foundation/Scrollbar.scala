package com.simplesys.SmartClient.Foundation

import com.simplesys.System.Types.{URL, void, SCImgURL}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Scrollbar extends StretchImg {
    val allowThumbDownState: Boolean
    val allowThumbOverState:Boolean
    var autoEnable:Boolean
    var btnSize:Int
    val cornerImg: StretchItem
    val cornerSize:Int
    val cornerSrc: SCImgURL
    val endImg:StretchImg
    val endThumbOverlap:Int
    val scrollTarget:Canvas
    def setScrollTarget (newTarget:Canvas = js.native):void
    val showCorner:Boolean
    val showTrackButtons:Boolean
    val showTrackEnds:Boolean
    val startImg:StretchItem
    val startThumbOverlap:Int
    val thumbInset:Int
    val thumbMinSize:Int
    val thumbOverlap:Int
    val trackEndHeight:Int
    val trackEndImg:StretchItem
    val trackEndWidth:Int
    val trackImg: StretchItem
    val trackStartImg: StretchItem
}



