package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.Types.ColorPickerMode.ColorPickerMode
import com.simplesys.System.Types.{CSSStyleName, HTMLString, SCImgURL, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ColorPickerProps extends WindowProps {
    var allowComplexMode: ScOption[Boolean] = ScNone
    var autoCenterOnShow: ScOption[Boolean] = ScNone
    var autoPosition: ScOption[Boolean] = ScNone
    var basicColorLabel: ScOption[HTMLString] = ScNone
    var blueFieldPrompt: ScOption[HTMLString] = ScNone
    var blueFieldTitle: ScOption[HTMLString] = ScNone
    var cancelButton: ScOption[IButton] = ScNone
    var cancelButtonConstructor: ScOption[Class] = ScNone
    var cancelButtonTitle: ScOption[HTMLString] = ScNone
    var colorArray: ScOption[IscArray[String]] = ScNone
    var colorButtonBaseStyle: ScOption[CSSStyleName] = ScNone
    var colorButtonSize: ScOption[Int] = ScNone
    var colorChanged: ScOption[js.Function1[void, _]] = ScNone
    var colorSelected: ScOption[js.Function2[String, Int, _]] = ScNone
    var crosshairImageURL: ScOption[SCImgURL] = ScNone
    var defaultColor: ScOption[String] = ScNone
    var defaultOpacity: ScOption[Int] = ScNone
    var defaultPickMode: ScOption[ColorPickerMode] = ScNone
    var greenFieldPrompt: ScOption[HTMLString] = ScNone
    var greenFieldTitle: ScOption[HTMLString] = ScNone
    var htmlFieldPrompt: ScOption[HTMLString] = ScNone
    var htmlFieldTitle: ScOption[HTMLString] = ScNone
    var hueFieldPrompt: ScOption[HTMLString] = ScNone
    var hueFieldTitle: ScOption[HTMLString] = ScNone
    var lessButtonTitle: ScOption[HTMLString] = ScNone
    var lumFieldPrompt: ScOption[HTMLString] = ScNone
    var lumFieldTitle: ScOption[HTMLString] = ScNone
    var lumWidth: ScOption[Int] = ScNone
    var modeToggleButton: ScOption[IButton] = ScNone
    var modeToggleButtonConstructor: ScOption[Class] = ScNone
    var moreButtonTitle: ScOption[HTMLString] = ScNone
    var okButton: ScOption[IButton] = ScNone
    var okButtonConstructor: ScOption[Class] = ScNone
    var okButtonTitle: ScOption[HTMLString] = ScNone
    var opacitySliderLabel: ScOption[HTMLString] = ScNone
    var opacityText: ScOption[HTMLString] = ScNone
    var redFieldPrompt: ScOption[HTMLString] = ScNone
    var redFieldTitle: ScOption[HTMLString] = ScNone
    var satFieldPrompt: ScOption[HTMLString] = ScNone
    var satFieldTitle: ScOption[HTMLString] = ScNone
    var selectedColorLabel: ScOption[HTMLString] = ScNone
    var supportsTransparency: ScOption[Boolean] = ScNone
    var swatchImageURL: ScOption[SCImgURL] = ScNone
    var swatchWidth: ScOption[Int] = ScNone
}
