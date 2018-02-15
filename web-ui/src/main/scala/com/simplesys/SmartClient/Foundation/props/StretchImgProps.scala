package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Foundation.StretchItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.ImageStyle.ImageStyle
import com.simplesys.System.Types.{CSSStyleName, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

class StretchImgProps extends StatefulCanvasProps {

    import com.simplesys.SmartClient.Foundation.StretchImg

    type classHandler <: StretchImg

    var gripImgSuffix: ScOption[String] = ScNone
    var hSrc: ScOption[SCImgURL] = ScNone
    var imageType: ScOption[ImageStyle] = ScNone
    var itemBaseStyle: ScOption[CSSStyleName] = ScNone
    var items: ScOption[IscArray[StretchItem]] = ScNone
    var showDownGrip: ScOption[Boolean] = ScNone
    var showGrip: ScOption[Boolean] = ScNone
    var showRollOverGrip: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
    var src: ScOption[SCImgURL] = ScNone
    var vertical: ScOption[Boolean] = ScNone
    var vSrc: ScOption[SCImgURL] = ScNone
}
