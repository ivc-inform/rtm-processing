package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Forms.{DynamicForm, ValuesManager}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.JSObject
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js._

class ValuesManagerProps extends ClassProps {
    var addOperation: ScOption[String] = ScNone
    var autoSynchronize: ScOption[Boolean] = ScNone
    var dataSource: ScOption[DataSource | String] = ScNone
    var deepCloneOnEdit: ScOption[Boolean] = ScNone
    var disableValidation: ScOption[Boolean] = ScNone
    var handleHiddenValidationErrors: ScOption[js.Function1[JSObject, Boolean]] = ScNone
    var members: ScOption[IscArray[DynamicForm]] = ScNone
    var operator: ScOption[OperatorId] = ScNone
    var removeOperation: ScOption[String] = ScNone
    var saveOperationType: ScOption[DSOperationType] = ScNone
    var submitValues: ScOption[js.Function2[JSObject, ValuesManager, _]] = ScNone
    var suppressValidationErrorCallback: ScOption[Boolean] = ScNone
    var updateOperation: ScOption[String] = ScNone
}
