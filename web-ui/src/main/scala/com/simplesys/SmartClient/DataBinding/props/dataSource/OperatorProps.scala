package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.SmartClient.DataBinding.Criterion
import com.simplesys.SmartClient.DataBinding.dataSource.Operator
import com.simplesys.System.JSAny
import com.simplesys.System.Types.FieldType.FieldType
import com.simplesys.System.Types.FormItemComponentType._
import com.simplesys.System.Types.FormItemType._
import com.simplesys.System.Types.OperatorValueType.OperatorValueType
import com.simplesys.System.Types.{ID, Record}
import com.simplesys.option.{FormItemType_FormItemComponentType, ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js.Function2

class OperatorProps extends AbstractClassProps {
    var condition: ScOption[js.Function5[JSAny, Record, JSAny, Criterion, Operator, Boolean]] = ScNone
    var compareCriteria: ScOption[Function2[Criterion, Criterion, Int]] = ScNone
    var editorType: ScOption[FormItemType_FormItemComponentType[FormItemType, FormItemComponentType]] = ScNone
    var fieldTypes: ScOption[Seq[FieldType]] = ScNone
    var hidden: ScOption[Boolean] = ScNone
    var ID: ScOption[ID] = ScNone
    var requiresServer: ScOption[Boolean] = ScNone
    var symbol: ScOption[String] = ScNone
    var title: ScOption[String] = ScNone
    var titleProperty: ScOption[ID] = ScNone
    var valueType: ScOption[OperatorValueType] = ScNone
}
