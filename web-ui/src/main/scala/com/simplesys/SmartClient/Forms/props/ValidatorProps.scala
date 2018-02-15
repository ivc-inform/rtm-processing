package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.DataBinding.AdvancedCriteria
import com.simplesys.SmartClient.Forms.Validator
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.RPC.ServerObject
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.Types.Record
import com.simplesys.System.Types.ValidatorType._
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class ValidatorProps extends ClassProps {

    import com.simplesys.System.JSAny

    var applyWhen: ScOption[AdvancedCriteria] = ScNone
    var condition: ScOption[Function4[FormItem, Validator, _ <:  JSUndefined[JSAny], _ <: Record, Boolean]] = ScNone
    var dependentFields: ScOption[IscArray[String]] = ScNone
    var serverOnly: ScOption[Boolean] = ScNone
    var stopOnError: ScOption[Boolean] = ScNone
    var validateOnChange: ScOption[Boolean] = ScNone
    var caseSensitive: ScOption[Boolean] = ScNone
    var precision: ScOption[JSAny] = ScNone
    var min: ScOption[Int] = ScNone
    var max: ScOption[Int] = ScNone
    var stopIfFalse: ScOption[Boolean] = ScNone
    var clientOnly: ScOption[Boolean] = ScNone
    var resultingValue: ScOption[JSObject] = ScNone
    var serverObject: ScOption[ServerObject] = ScNone
    var errorMessage: ScOption[String] = ScNone
    var serverCondition: ScOption[String] = ScNone
    var `type`: ScOption[ValidatorType] = ScNone
}
