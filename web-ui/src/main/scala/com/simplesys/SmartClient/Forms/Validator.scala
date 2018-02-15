package com.simplesys.SmartClient.Forms


import com.simplesys.SmartClient.DataBinding.AdvancedCriteria
import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.RPC.ServerObject
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.Types.ValidatorType.ValidatorType
import com.simplesys.System.Types.void
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Validator extends Class {
    def condition(item: DataSourceField | FormItem, validator: Validator, value: JSAny, record: JSObject): Boolean
    val applyWhen: AdvancedCriteria
    val dependentFields: IscArray[String]
    val serverOnly: Boolean
    val precision : JSAny
    val min : JSAny
    val stopOnError: Boolean
    val validateOnChange: Boolean
    val caseSensitive: Boolean
    val stopIfFalse: Boolean
    val clientOnly: Boolean
    val resultingValue: JSObject
    val serverObject: ServerObject
    val errorMessage: String
    val serverCondition: String
    val `type`: ValidatorType | String
}

@js.native
abstract trait AbstractValidatorCompanion extends AbstractClassCompanion {
    def addValidator(`type`: String, condition: js.Function): void = js.native
    def addValidatorDefinition(`type`: String, definition: JSObject): void = js.native
    def addValidators(newValidators: JSObject): void = js.native
    def create(): void = js.native
    val mustBeEarlierThan: String = js.native
    val mustBeEarlierThanTime: String = js.native
    val mustBeExactLength: String = js.native
    val mustBeGreaterThan: String = js.native
    val mustBeLaterThan: String = js.native
    val mustBeLaterThanTime: String = js.native
    val mustBeLessThan: String = js.native
    val mustBeLongerThan: String = js.native
    val mustBeShorterThan: String = js.native
    val notABoolean: String = js.native
    val notADate: String = js.native
    val notADecimal: String = js.native
    val notAnInteger: String = js.native
    val notAString: String = js.native
    val notOneOf: String = js.native
    val requiredField: String = js.native
}


