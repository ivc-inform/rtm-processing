package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.ButtonItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.Class
import com.simplesys.System.JSObject
import com.simplesys.System.Types.{CSSStyleName, FormItemComponentType, SCImgURL}
import com.simplesys.option._

class ButtonItemProps extends CanvasItemProps {
    type classHandler <: ButtonItem

    var autoFit: ScOption[Boolean] = ScNone
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var button: ScOption[Canvas] = ScNone
    var buttonConstructor: ScOption[Class] = ScNone
    var buttonProperties: ScOption[JSObject] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var showFocusedAsOver: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.ButtonItem
}
