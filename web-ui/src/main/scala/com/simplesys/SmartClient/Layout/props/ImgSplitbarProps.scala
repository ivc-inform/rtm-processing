package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.ImgProps
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScNone, ScOption}

class ImgSplitbarProps extends ImgProps {
    var canCollapse: ScOption[Boolean] = ScNone
    var hSrc: ScOption[SCImgURL] = ScNone
    var target: ScOption[Canvas] = ScNone
    var vertical: ScOption[Boolean] = ScNone
    var vSrc: ScOption[SCImgURL] = ScNone
}
