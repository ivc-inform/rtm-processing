package com.simplesys.SmartClient.Forms.props.validator

import com.simplesys.SmartClient.DataBinding.DataBoundComponent
import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.Validator
import com.simplesys.System.Types.Record
import com.simplesys.System.{JSObject, JSAny}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js
import scala.scalajs.js._

class validatorDefinitionProps extends AbstractClassProps {
    var action: ScOption[js.Function5[Boolean, DataSourceField | FormItem, Validator, Record, DataBoundComponent, _]] = ScNone
    var condition: ScOption[js.Function4[DataSourceField | FormItem, Validator, JSAny, JSObject, Boolean]] = ScNone
    var defaultErrorMessage: ScOption[String] = ScNone
    var requiresServer: ScOption[Boolean] = ScNone
}

