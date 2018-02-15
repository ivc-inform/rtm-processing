package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.Control.{IButton, ImgButton}
import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, Criterion, DataSource}
import com.simplesys.SmartClient.Forms.formsItems.{FormItem, PickList, SelectItem}
import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.Layout.{AbstractLayoutCompanion, HLayout, Layout, VStack}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.FieldType.FieldType
import com.simplesys.System.Types.LogicalOperator.LogicalOperator
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.TopOperatorAppearance.TopOperatorAppearance
import com.simplesys.System.Types.ValueItemType.ValueItemType
import com.simplesys.System.Types.{AutoChild, MultiAutoChild, SCClassName, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait FilterBuilder extends Layout {
    val addButton: ImgButton with AutoChild
    val addButtonPrompt: String
    def addClause(filterClause: FilterClause): Boolean
    def addCriterion(criterion: Criterion): Boolean
    val allowEmpty: Boolean
    val bracket: Canvas with AutoChild
    val clauseStack: VStack with AutoChild
    def clearCriteria(): void
    val criteria: AdvancedCriteria
    val dataSource: DataSource
    val defaultSubClauseOperator: LogicalOperator
    val fieldDataSource: DataSource
    val fieldPicker: PickList with AutoChild
    val fieldPickerProperties: FormItem
    val fieldPickerTitle: String
    var filterChanged: js.Function1[void, _]
    def getChildFilters(): IscArray[FilterBuilder]
    def getCriteria(includeEmptyValues: Boolean = js.native): AdvancedCriteria
    def getEditorType(field: DataSourceField, operatorId: OperatorId): SCClassName
    def getFieldOperators(fieldName: String): IscArray[OperatorId]
    def getFilterDescription(): String
    def getSelectedClauses(): IscArray[FilterClause]
    var getValueFieldProperties: js.Function4[FieldType, String, OperatorId, ValueItemType, FormItem]
    val inlineAndNotTitle: String
    val inlineAndTitle: String
    val inlineOrTitle: String
    val matchAllTitle: String
    val matchAnyTitle: String
    val matchNoneTitle: String
    val missingFieldPrompt: String
    val modeSwitcher: Label with AutoChild
    val modeSwitcherAdvancedMessage: String
    val modeSwitcherFlattenWarningMessage: String
    val modeSwitcherSimpleMessage: String
    val operatorPicker: SelectItem with MultiAutoChild
    val operatorPickerProperties: FormItem
    val operatorPickerTitle: String
    val operatorPickerWidth: String | Int
    val radioOperatorForm: DynamicForm with AutoChild
    val radioOperatorLayout: HLayout with AutoChild
    val radioOperatorTitle: String
    val rangeSeparator: String
    val removeButton: ImgButton with AutoChild
    val removeButtonPrompt: String
    def removeClause(clause: FilterClause): void
    var retainValuesAcrossFields: Boolean
    var saveOnEnter: Boolean
    var search: js.Function1[AdvancedCriteria, _]
    def setCriteria(criteria: AdvancedCriteria): void
    def setTopOperator(operator: OperatorId): void
    def setTopOperatorAppearance(value: TopOperatorAppearance): void
    val showAddButton: Boolean
    val showFieldTitles: Boolean
    val showModeSwitcher: Boolean
    val showRemoveButton: Boolean
    val showSelectionCheckbox: Boolean
    val showSubClauseButton: Boolean
    val sortFields: Boolean
    val subClauseButton: IButton with AutoChild
    val subClauseButtonPrompt: String
    val subClauseButtonTitle: String
    var topOperator: LogicalOperator
    var topOperatorAppearance: TopOperatorAppearance
    val topOperatorForm: DynamicForm with AutoChild
    val topOperatorItem: SelectItem with AutoChild
    val topOperatorItemWidth: String | Int
    val topOperatorOptions: IscArray[OperatorId]
    val topOperatorTitle: String
    def validate(): Boolean
    val validateOnChange: Boolean
    val valueItemWidth: Int | String
}

@js.native
abstract trait AbstractFilterBuilderCompanion extends AbstractLayoutCompanion {
}


