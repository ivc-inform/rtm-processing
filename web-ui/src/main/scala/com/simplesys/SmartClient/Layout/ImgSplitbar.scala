package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Foundation.{Canvas, AbstractCanvasCompanion, Img}
import com.simplesys.System.Types.SCImgURL

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ImgSplitbar extends Img {
    var canCollapse:Boolean
    val hSrc:SCImgURL
    var target:Canvas
    val vertical:Boolean
    val vSrc:SCImgURL
}



