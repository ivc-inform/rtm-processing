package com.simplesys.SmartClient.Foundation

import com.simplesys.System.Types.{SCImgURL, URL, void, HTMLString}
import com.simplesys.System.Types.ImageStyle.ImageStyle

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Img extends StatefulCanvas {
    var activeAreaHTML: String
    var altText: String
    var imageHeight: Int
    var imageType: ImageStyle
    var imageWidth: Int
    var name: String
    def resetSrc(): void
    def setImageType(imageType: ImageStyle): void
    def setSrc(url: URL): void
    var size: Int
    var src: SCImgURL
    var usePNGFix: Boolean
}



