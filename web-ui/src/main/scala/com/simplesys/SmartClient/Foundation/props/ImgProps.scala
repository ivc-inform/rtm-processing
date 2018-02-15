package com.simplesys.SmartClient.Foundation.props

import com.simplesys.System.Types.ImageStyle.ImageStyle
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScNone, ScOption}

class ImgProps extends StatefulCanvasProps {
    var activeAreaHTML: ScOption[String] = ScNone
    var altText: ScOption[String] = ScNone
    var imageHeight: ScOption[Int] = ScNone
    var imageType: ScOption[ImageStyle] = ScNone
    var imageWidth: ScOption[Int] = ScNone
    var name: ScOption[String] = ScNone
    var size: ScOption[Int] = ScNone
    var src: ScOption[SCImgURL] = ScNone
    var usePNGFix: ScOption[Boolean] = ScNone
}
