package com.simplesys.SmartClient.Control

import com.simplesys.SmartClient.Drawing.DrawPane
import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, Canvas}
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.ImageStyle.ImageStyle
import com.simplesys.System.Types.{CSSStyleName, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Slider extends Canvas {
    var drawPane: JSUndefined[DrawPane]
    var animateThumb: Boolean
    var animateThumbInit: Boolean
    var animateThumbTime: Int
    var flipValues: Boolean
    def getValue(): Double
    val hThumbStyle: CSSStyleName
    val hTrackStyle: CSSStyleName
    var labelHeight: Int
    var labelSpacing: Int
    var labelWidth: Int
    var length: Int
    var maxValue: Double
    var maxValueLabel: String
    var minValue: Double
    var minValueLabel: String
    var numValues: Int
    var roundPrecision: Int
    var roundValues: Boolean
    def setFlipValues(flipValues: Boolean): void
    def setLabelHeight(newHeight: Int): void
    def setLabelSpacing(labelWidth: Int): void
    def setLabelWidth(labelWidth: Int): void
    def setMaxValue(newValue: Int): void
    def setMaxValueLabel(labelText: String): void
    def setMinValue(newValue: Int): void
    def setNumValues(newNumValues: Double): void
    def setRoundPrecision(roundPrecision: Int): void
    def setRoundValues(roundValues: Boolean): void
    def setShowRange(showRange: Boolean): void
    def setShowTitle(showTitle: Boolean): void
    def setShowValue(showValue: Boolean): void
    def setStepPercent(stepPercent: Int): void
    def setThumbSrc(newSrc: String): void
    def setThumbThickWidth(newWidth: Int): void
    def setThumbThinWidth(newWidth: Int): void
    def setTitle(newTitle: String): void
    def setTrackCapSize(newSize: Int): void
    def setTrackImageType(newType: String): void
    def setTrackSrc(newSrc: String): void
    def setTrackWidth(newWidth: Int): void
    def setValue(newValue: Double, noAnimation: Boolean = js.native): void
    def setVertical(isVertical: Boolean): void
    var showRange: Boolean
    var showTitle: Boolean
    var showValue: Boolean
    var sliderTarget: Canvas
    var stepPercent: Double
    var thumbSrc: String
    var thumbThickWidth: Int
    var thumbThinWidth: Int
    var title: String
    var trackCapSize: Int
    var trackImageType: ImageStyle
    var trackSrc: String
    var trackWidth: Int
    var value: Double
    var valueChanged: js.Function1[Double, _]
    var valueIsChanging: js.Function1[void, Boolean]
    var vertical: Boolean
    val vThumbStyle: CSSStyleName
    val vTrackStyle: CSSStyleName
}




