package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.Class
import com.simplesys.System.Types.{void, AutoChild}
import com.simplesys.System.{JSDictionaryAny, JSAny}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait SliderItem extends CanvasItem {
    var changeOnDrag:Boolean
    var maxValue:Double
    var minValue:Double
    var numValues:Int
    val roundPrecision:Int
    val roundValues:Boolean
    def setMaxValue (newValue:Double):void
    def setMinValue (newValue:Double):void
    def setNumValues (newNumValues:Double):void
    val slider:Canvas with AutoChild
    val sliderConstructor:Class
    val sliderProperties:JSDictionaryAny
    val vertical:Boolean
}


