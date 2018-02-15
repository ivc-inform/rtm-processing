package com.simplesys.SmartClient.Foundation

import com.simplesys.System.Types.{CSSStyleName, SCImgURL, CSSColor}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait EdgedCanvas extends Canvas {
    var addEdgeStyleSuffix: Boolean
    var centerBackgroundColor: CSSColor
    var edgeBottom: Int
    val edgeColor: CSSColor
    val edgeLeft: Int
    val edgeOffsetBottom: Int
    val edgeOffsetLeft: Int
    val edgeOffsetRight: Int
    val edgeOffsetTop: Int
    val edgeRight: Int
    val edgeStyleName: CSSStyleName
    var edgeTop: Int
    var showCenter: Int
}


