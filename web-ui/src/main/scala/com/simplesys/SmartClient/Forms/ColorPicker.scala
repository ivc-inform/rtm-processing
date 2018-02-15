package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Layout.Window
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.Types.ColorPickerMode.ColorPickerMode
import com.simplesys.System.Types.{void, _}

import scala.scalajs.js


@js.native
trait ColorPicker extends Window {
    var allowComplexMode: Boolean
    var autoCenterOnShow: Boolean
    var autoPosition: Boolean
    val basicColorLabel: HTMLString
    val blueFieldPrompt: HTMLString
    val blueFieldTitle: HTMLString
    val cancelButton: IButton with AutoChild
    var cancelButtonConstructor: Class
    var cancelButtonTitle: HTMLString
    val colorArray: IscArray[String]
    val colorButtonBaseStyle: CSSStyleName
    var colorButtonSize: Int
    var colorChanged: js.Function1[void, _]
    var colorSelected: js.Function2[String, Int, _]
    val crosshairImageURL: SCImgURL
    val defaultColor: String
    val defaultOpacity: Int
    val defaultPickMode: ColorPickerMode
    def getBlue(): Int
    def getGreen(): Int
    def getHtmlColor(): String
    def getHue(): Int
    def getLuminosity(): Int
    def getOpacity(): Int
    def getRed(): Int
    def getSaturation(): Int
    val greenFieldPrompt: HTMLString
    val greenFieldTitle: HTMLString
    val htmlFieldPrompt: HTMLString
    val htmlFieldTitle: HTMLString
    val hueFieldPrompt: HTMLString
    val hueFieldTitle: HTMLString
    val lessButtonTitle: HTMLString
    val lumFieldPrompt: HTMLString
    val lumFieldTitle: HTMLString
    val lumWidth: Int
    val modeToggleButton: IButton with AutoChild
    val modeToggleButtonConstructor: Class
    val moreButtonTitle: HTMLString
    val okButton: IButton with AutoChild
    val okButtonConstructor: Class
    val okButtonTitle: HTMLString
    val opacitySliderLabel: HTMLString
    val opacityText: HTMLString
    val redFieldPrompt: HTMLString
    val redFieldTitle: HTMLString
    val satFieldPrompt: HTMLString
    val satFieldTitle: HTMLString
    val selectedColorLabel: HTMLString
    def setBlue(newValue: Int): void
    def setCurrentPickMode(pickMode: ColorPickerMode): void
    def setGreen(newValue: Int): void
    def setHtmlColor(newValue: String): void
    def setHue(newValue: Int): void
    def setLuminosity(newValue: Int): void
    def setRed(newValue: Int): void
    def setSupportsTransparency(transparencyFlag: Boolean): void
    val supportsTransparency: Boolean
    val swatchImageURL: SCImgURL
    val swatchWidth: Int
}



