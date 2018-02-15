package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.{DynamicForm, DynamicFormSS}
import com.simplesys.SmartClient.Forms.formsItems.{CanvasItem, FormItem}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.FormItemType.FormItemType
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.{JSAny, JSObject}
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction2

class CanvasItemProps extends FormItemProps {
    type classHandler <: CanvasItem

    var applyPromptToCanvas: ScOption[Boolean] = ScNone
    var autoDestroy: ScOption[Boolean] = ScNone
    var canEditChanged: ScOption[js.Function1[Boolean, Boolean]] = ScNone
    var canvas: ScOption[Canvas] = ScNone
    var canvasConstructor: ScOption[String] = ScNone
    var canvasDefaults: ScOption[JSObject] = ScNone
    var canvasProperties: ScOption[JSObject] = ScNone
    var innerForm: ScOption[DynamicFormSS] = ScNone
    var createCanvas: ScOption[ThisFunction2[classHandler, _ <: DynamicForm, _ <: CanvasItem, Canvas]] = ScNone
    var _constructor: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = ScNone
    var editCriteriaInInnerForm: ScOption[Boolean] = ScNone
    var multiple: ScOption[Boolean] = ScNone
    var overflow: ScOption[Overflow] = ScNone
    var showValue: ScOption[js.Function3[JSAny, JSAny, DynamicForm, CanvasItem]] = ScNone
    `type` = FormItemComponentType.CanvasItem
}
