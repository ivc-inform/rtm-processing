package com.simplesys.components.validators

import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.Forms.Validator
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.props.ValidatorProps
import com.simplesys.SmartClient.System.{Validator, isc}
import com.simplesys.System.Types.{ValidatorType, _}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._

object Validators {
    def noneValidator = Validator(new ValidatorProps {})

    def floatValidator = Validator(
        new ValidatorProps {
            `type` = ValidatorType.custom.opt
            errorMessage = "Должно быть десятичным либо целым числом.".opt
            condition = {
                (item: FormItem, validator: Validator, value: JSUndefined[JSAny], record: Record) ⇒
                    try {
                        val dbl = value.toString.toDouble
                        true
                    }
                    catch {
                        case e: Throwable ⇒
                            false
                    }
            }.toFunc.opt
        })

    def intValidator = Validator(
        new ValidatorProps {
            `type` = ValidatorType.custom.opt
            errorMessage = "Должно быть целым числом.".opt
            condition = {
                (item: FormItem, validator: Validator, value: JSUndefined[JSAny], record: Record) ⇒
                    try {
                        val int = value.toString.toInt
                        true
                    }
                    catch {
                        case e: Throwable ⇒
                            false
                    }
            }.toFunc.opt
        })
}
