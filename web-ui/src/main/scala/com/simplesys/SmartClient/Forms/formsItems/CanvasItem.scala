package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.Forms.{DynamicForm, DynamicFormSS}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.FormItemComponentType._
import com.simplesys.System.Types.FormItemType._
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.ReadOnlyDisplayAppearance.ReadOnlyDisplayAppearance
import com.simplesys.System.Types.void
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait CanvasItem extends FormItem {
    var _constructor: JSUndefined[FormItemType | FormItemComponentType]
    var innerForm: JSUndefined[DynamicFormSS]
    var applyPromptToCanvas: Boolean
    var autoDestroy: Boolean
    var canEditChanged: js.Function1[Boolean, Boolean]
    var canvas: Canvas
    var canvasConstructor: String
    var canvasDefaults: JSObject
    var canvasProperties: JSObject
    var createCanvas: js.Function2[DynamicForm, CanvasItem, Canvas]
    val editCriteriaInInnerForm: Boolean
    def getCriterion(): Criterion
    var multiple: Boolean
    var overflow: Overflow
    def readOnlyDisplayChanged(appearance: ReadOnlyDisplayAppearance): Boolean
    def setCanvas(canvas: Canvas): void
    def shouldDisableCanvas(): void
    var showValue: js.Function3[JSAny, JSAny, DynamicForm, CanvasItem]
}

@js.native
abstract class AbstractCanvasItemCompanion extends AbstractFormItemCompanion


