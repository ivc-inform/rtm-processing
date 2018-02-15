package com.simplesys.SmartClient.Forms

import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, DSRequest, DataSource}
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.Grid
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class, IscArray}
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.{Criteria, Record, void}
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ValuesManager extends Class {
    def addFieldErrors(fieldName: String, errors: IscArray[String], showErrors: Boolean): void
    def addMember(member: DynamicForm | String): void
    def addMembers(members: IscArray[DynamicForm] | IscArray[String]): void
    var addOperation: String
    var autoSynchronize: Boolean
    def cancel(requestProperties: DSRequest = js.native): void
    def clearErrors(showErrors: Boolean): void
    def clearFieldErrors(fieldName: String, show: Boolean): void
    def clearValue(fieldName: String): void
    def clearValues(): void
    var dataSource: DataSource | String
    var deepCloneOnEdit: Boolean
    var disableValidation: Boolean
    def editNewRecord(initialValues: JSObject | Record = js.native): void
    def editRecord(record: Record): void
    def editSelectedData(selectionComponent: Grid[ListGridField, ListGridRecord] | String): void
    def fetchData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def filterData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def getChangedValues(): JSObject
    def getDataSource(): DataSource
    def getErrors(): JSObject
    def getFieldErrors(fieldName: String): IscArray[String]
    def getItem(itemID: String, retrieveAll: Boolean = js.native): FormItem
    def getMember(ID: String): Canvas
    def getMemberForField(fieldName: String): Canvas
    def getMembers(): IscArray[Canvas]
    def getMemberValues(ID: String): JSObject
    def getOldValues(): JSObject
    def getSaveOperationType(requestProperties: DSRequest = js.native): DSOperationType
    def getValidatedValues(): JSObject
    def getValue(fieldName: String, component: Canvas = js.native): JSAny
    def getValues(): JSObject
    def getValuesAsAdvancedCriteria(textMatchStyle: TextMatchStyle = js.native): AdvancedCriteria
    def getValuesAsCriteria(advanced: Boolean, textMatchStyle: TextMatchStyle = js.native): Criteria | AdvancedCriteria
    var handleHiddenValidationErrors: js.Function1[JSObject, Boolean]
    def hasErrors(): Boolean
    def hasFieldErrors(fieldName: String): Boolean
    def isNewRecord(): Boolean
    var members: IscArray[DynamicForm]
    val operator: OperatorId
    def rememberValues(): JSObject
    def removeMember(member: DynamicForm | String): void
    def removeMembers(members: IscArray[DynamicForm] | IscArray[String]): void
    var removeOperation: String
    def resetValues(): void
    def saveData(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var saveOperationType: DSOperationType
    def setDataSource(dataSource: DataSource): void
    def setErrors(errors: JSObject, showErrors: Boolean): void
    def setFieldErrors(fieldName: String, errors: IscArray[String], showErrors: Boolean): void
    def setMemberValues(ID: String, values: JSObject): void
    def setValue(fieldName: String, newValue: JSAny): void
    def setValues(values: JSObject): void
    def showErrors(): void
    def showFieldErrors(): void
    def submit(callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    var submitValues: js.Function2[JSObject, ValuesManager, _]
    var suppressValidationErrorCallback: Boolean
    def synchronizeMember(member: Canvas): void
    def synchronizeMembers(): void
    def synchronizeMembersOnDataPath(dataPath: String): void
    var updateOperation: String
    def validate(): Boolean
    def valuesHaveChanged(): Boolean
}

@js.native
abstract trait AbstractValuesManagerCompanion extends AbstractClassCompanion {
    def getById(ID: String): ValuesManager = js.native
}

@js.native
   object ValuesManager extends AbstractValuesManagerCompanion

