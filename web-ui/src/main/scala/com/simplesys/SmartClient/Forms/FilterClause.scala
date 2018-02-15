package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Control.ImgButton
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, PickList, SelectItem}
import com.simplesys.SmartClient.Layout.{AbstractLayoutCompanion, Layout}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.FieldType.FieldType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.ValueItemType.ValueItemType
import com.simplesys.System.Types.{AutoChild, Criteria, void}

import scala.scalajs.js

@js.native
trait FilterClause extends Layout {
    val clause: SearchForm with AutoChild
    var criterion: Criteria
    val fieldPicker: PickList with AutoChild
    val fieldPickerProperties: FormItem
    val fieldPickerTitle: String
    def getCriterion(): Criteria
    def getFieldOperators(fieldName: String): IscArray[OperatorId]
    def getFilterBuilder(): FilterBuilder
    def getValueFieldProperties(`type`: FieldType, fieldName: String, operatorId: OperatorId, itemType: ValueItemType): FormItem
    val operatorPicker: SelectItem with AutoChild
    val operatorPickerProperties: FormItem
    val operatorPickerTitle: String
    var remove: js.Function1[void, _]
    val removeButton: ImgButton with AutoChild
    val removeButtonPrompt: String
    val showFieldTitles: Boolean
    val showRemoveButton: Boolean
    def validate(): Boolean
    val validateOnChange: Boolean
    val valueItemTitle: String
}

@js.native
abstract trait AbstractFilterClauseCompanion extends AbstractLayoutCompanion {
}

@js.native
object FilterClause extends AbstractFilterClauseCompanion

