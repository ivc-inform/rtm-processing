package com.simplesys.SmartClient.Layout.navigationBar

import com.simplesys.SmartClient.Control.StretchImgButton
import com.simplesys.System.Types.{SCImgURL, void}

import scala.scalajs.js

@js.native
trait MiniNavControl extends StretchImgButton {
    val downButtonSrc: SCImgURL
    var downClick: js.ThisFunction0[classHandler, void]
    val upButtonSrc: SCImgURL
    var upClick: js.ThisFunction0[classHandler, void]
}


