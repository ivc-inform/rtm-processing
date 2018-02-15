package com.simplesys.components

import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.System._
import com.simplesys.components.drawing.drawItems.{SubProgramValue, VariableValue}
import com.simplesys.components.formItems.{GroupValue, LoggingValue}

import scala.scalajs.js.annotation.ScalaJSDefined

trait PropEditorLiveObject extends JSObject {

    var ID: JSUndefined[String]
    var _constructor: JSUndefined[String]
    //var serializeID: JSUndefined[String]
    var canDrag: JSUndefined[Boolean]
    var cursor: JSUndefined[String]
    var endArrow: JSUndefined[String]
    var fillColor: JSUndefined[String]
    var fillGradient: JSUndefined[JSObject]
    var fillOpacity: JSUndefined[Double]
    val lineCap: JSUndefined[String]
    val lineColor: JSUndefined[String]
    val lineOpacity: JSUndefined[Double]
    val linePattern: JSUndefined[String]
    val lineWidth: JSUndefined[Int]
    val shadow: JSUndefined[JSObject]
    val criteria: JSUndefined[JSObject]
    val startArrow: JSUndefined[String]
    val titleRotationMode: JSUndefined[String]
    val title: JSUndefined[String]
    val description: JSUndefined[String]
    val rounding: JSUndefined[Double]
    val keepInParentRect: JSUndefined[Boolean]
    val listRefs: JSUndefined[JSObject]
    val titleLabelProperties: JSUndefined[DrawLabel]
    val messageSMS: JSUndefined[String]
    val timerProps: JSUndefined[JSObject]
    val groupProps: JSUndefined[GroupValue]
    val variableProps: JSUndefined[VariableValue]
    val subProgramProps: JSUndefined[SubProgramValue]
    val loggingProps: JSUndefined[LoggingValue]
    val multiElementsProps: JSUndefined[MultiElementsValue]
}

