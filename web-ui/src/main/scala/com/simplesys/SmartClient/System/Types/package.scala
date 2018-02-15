package com.simplesys.System

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.xml.Elem

import scala.scalajs.js
import scala.scalajs.js.|

package object Types {

    type Field = JSObject

    type Criteria = JSObject

    trait CallbackObject {
        val target: JSObject
        val methodName: String
    }

    type Properties = JSObject
    type EdgeSizes = JSObject
    type Record = JSObject
    type DataSourceRecord = JSObject
    type CellRecord = JSObject
    type ListGridSelectedState = JSObject
    type ListGridSortState = JSObject
    type ListGridViewState = JSObject
    type ListGridGroupState = JSObject

    type TreeGridOpenState = JSObject
    type TreeGridViewState = JSObject
    type TreeGridSelectedState = JSObject

    type DetailViewerViewState = JSObject

    type Callback = js.Function | js.ThisFunction | CallbackObject
    type ValueMap = JSAny
    type XMLDocument = JSObject
    type XMLElement = JSObject
    type XMLNode = JSObject
    type VelocityExpression = JSObject
    type XPathExpression = String
    type ListGridFieldState = JSObject
    type XPath = String
    type FormatString = String
    type SCClassName = String
    type FormItemClassName = String
    type FormItemBaseStyle = SCClassName
    type CSSStyleName = String
    type CSSColor = String
    type CSSText = String
    type CSSClassName = String
    type Color = String
    type HTMLString = String
    type URL = String
    type URN = String
    type SCImgURL = String
    type RelativeDateString = String
    type KeyName = String
    type PageHtml = Elem
    type DataPath = String
    type AnimateShowEffectId = String
    type DateInputFormat = String
    type void = Unit
    type ID = String
}
