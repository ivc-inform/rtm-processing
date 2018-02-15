package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.ColorItem
import com.simplesys.System.{JSAny, JSUndefined}
import com.simplesys.System.Types.ColorPickerMode.ColorPickerMode
import com.simplesys.System.Types.{CSSColor, FormItemComponentType}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{Function2, Function5}

class ColorItemProps extends TextItemProps {
    type classHandler <: ColorItem

    var allowComplexMode: ScOption[Boolean] = ScNone
    var defaultPickerMode: ScOption[ColorPickerMode] = ScNone
    var pickerColorSelected: ScOption[js.ThisFunction2[classHandler, String, JSUndefined[Int], _]] = ScNone
    var supportsTransparency: ScOption[Boolean] = ScNone
    var setValuePicker: ScOption[js.Function2[CSSColor, Int, _]] = ScNone
    `type` = FormItemComponentType.ColorItem
}
