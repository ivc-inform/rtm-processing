package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.ImageStyle.ImageStyle
import com.simplesys.System.Types.{CSSStyleName, SCImgURL, URL, void}

import scala.scalajs.js

@js.native
trait StretchImg extends StatefulCanvas {
    var gripImgSuffix: String
    var hSrc: SCImgURL
    val imageType: ImageStyle
    var itemBaseStyle: CSSStyleName
    val items: IscArray[StretchItem]
    def setItems (items: IscArray[StretchItem]):void
    def setSrc (src:URL):void
    def setState (newState:String, whichPart:String = js.native):void
    var showDownGrip:Boolean
    var showGrip:Boolean
    var showRollOverGrip:Boolean
    var showTitle: Boolean
    var src:SCImgURL
    var vertical:Boolean
    var vSrc:SCImgURL
}


