package com.simplesys.components

import com.simplesys.System.{JSDictionary, JSObject, JSUndefined}
import com.simplesys.components.drawing.drawItems.{SubProgramValue, VariableValue}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait MultiElementsValue extends JSObject{
  val subPrograms : JSUndefined[JSDictionary[SubProgramValue]]
  val variables : JSUndefined[JSDictionary[VariableValue]]
}
