package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.DataBinding.dataSource._
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.detailViewer.DetailViewerField
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Layout.WindowProgressDialog
import com.simplesys.SmartClient.RPC.ServerObject
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.System.Types.CriteriaCombineOperator.CriteriaCombineOperator
import com.simplesys.System.Types.CriteriaPolicy.CriteriaPolicy
import com.simplesys.System.Types.DSDataFormat.DSDataFormat
import com.simplesys.System.Types.DSInheritanceMode.DSInheritanceMode
import com.simplesys.System.Types.DSProtocol.DSProtocol
import com.simplesys.System.Types.DSServerType.DSServerType
import com.simplesys.System.Types.EnumTranslateStrategy.EnumTranslateStrategy
import com.simplesys.System.Types.FieldType.FieldType
import com.simplesys.System.Types.FormItemComponentType.FormItemComponentType
import com.simplesys.System.Types.MultiUpdatePolicy.MultiUpdatePolicy
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.OperatorValueType.OperatorValueType
import com.simplesys.System.Types.RPCTransport.RPCTransport
import com.simplesys.System.Types.SQLPagingStrategy.SQLPagingStrategy
import com.simplesys.System.Types.SequenceMode.SequenceMode
import com.simplesys.System.Types.SummaryFunction.SummaryFunction
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types._
import com.simplesys.System.{JSAny, JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait DataSource extends Class {

    def addData(newRecord: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def addDatas(newRecords: IscArray[Record], callback: RPCQueueCallback = js.native, requestProperties: DSRequest = js.native): void
    def performDSOperation(operationType: String, newRecord: Record | IscArray[Record], callback: JSUndefined[DSCallback] = js.native, requestProperties: DSRequest = js.native): void
    val addGlobalId: Boolean
    def addSearchOperator(operator: Operator, types: IscArray[FieldType] = js.native): void
    var allowAdvancedCriteria: Boolean
    var allowClientRequestedSummaries: Boolean
    def applyFilter(data: IscArray[Record], criteria: Criteria, requestProperties: DSRequest = js.native): IscArray[Record]
    val audit: Boolean
    val auditDataSourceID: String
    val auditDSConstructor: String
    val auditRevisionFieldName: String
    val auditTimeStampFieldName: String
    val auditTypeFieldName: String
    val auditUserFieldName: String
    val autoCacheAllData: Boolean
    val autoConvertRelativeDates: Boolean
    val autoCreateAuditTable: Boolean
    val autoDeriveSchema: Boolean
    val autoDeriveTitles: Boolean
    val autoJoinTransactions: Boolean
    val beanClassName: String
    val break: Boolean
    val cacheAcrossOperationIds: Boolean
    val cacheAllData: Boolean
    val cacheAllOperationId: String
    val cacheData: IscArray[Record]
    val cacheData1: IscArray[Record]
    val cacheMaxAge: Int
    val callbackParam: String
    val canMultiSort: Boolean
    val childrenField: String
    val clientOnly: Boolean
    def cloneDSRequest(dsRequest: DSRequest): DSRequest
    def cloneDSResponse(dsResponse: DSResponse): DSResponse
    def compareCriteria(newCriteria: Criteria, oldCriteria: Criteria, requestProperties: DSRequest = js.native, policy: CriteriaPolicy = js.native): Int
    def compareDates(date1: Date, date2: Date, fieldName: String): Int
    val configBean: String
    def contactsServer(): Boolean
    def convertDataSourceCriteria(criteria: Criteria, textMatchStyle: TextMatchStyle = js.native): AdvancedCriteria
    def convertRelativeDates(criteria: Criteria, timezoneOffset: String = js.native, firstDayOfWeek: Int = js.native, baseDate: Date = js.native): Criteria
    def copyRecord(record: Record): Record
    def copyRecords(records: IscArray[Record]): IscArray[Record]
    val creatorOverrides: Boolean
    val criteriaPolicy: CriteriaPolicy
    val dataField: String
    val dataFormat: DSDataFormat
    val dataProtocol: DSProtocol
    val dataTransport: RPCTransport
    val dataURL: URL
    val dbName: String
    val deepCloneOnEdit: Boolean
    val defaultBooleanStorageStrategy: String
    val defaultMultiUpdatePolicy: MultiUpdatePolicy
    val defaultTextMatchStyle: TextMatchStyle
    val descriptionField: String
    val dbImportFileName: String
    def downloadFile(data: Record, fieldName: String = js.native, requestProperties: DSRequest = js.native): void
    val dropExtraFields: Boolean
    val dropUnknownCriteria: Boolean
    val endGap: Int
    val enumConstantProperty: String
    val enumOrdinalProperty: String
    val enumTranslateStrategy: EnumTranslateStrategy
    def evaluateCriterion(record: Record, criterion: Criterion): Boolean
    def execute(dsRequest: DSRequest): void
    def exportClientData(data: IscArray[Record], requestProperties: DSRequest, callback: DSCallback): void
    def exportData(criteria: Criteria = js.native, requestProperties: DSRequest = js.native, callback: DSCallback = js.native): void
    def fetchData(criteria: Criteria = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    def fetchRecord(pkValue: JSAny, requestProperties: DSRequest = js.native, callback: DSCallback = js.native): void
    def fieldMatchesFilter(fieldValue: JSAny, filterValue: JSAny, requestProperties: DSRequest = js.native): Boolean
    val fields: IscArray[DataSourceField]
    val fileContentsField: String
    val fileFormatField: String
    val fileLastModifiedField: String
    val fileNameField: String
    val fileTypeField: String
    val fileVersionField: String
    def filterData(criteria: Criteria = js.native, requestProperties: DSRequest = js.native, callback: DSCallback = js.native): void
    def getAutoTitle(identifier: ID): String
    def getCacheData(): IscArray[Record]
    def getCacheData1(): IscArray[Record]
    def getClientOnlyDataSource(criteria: Criteria, callback: ClientOnlyDataSourceCallback, requestProperties: DSRequest = js.native, dataSourceProperties: DataSource = js.native): void
    def getClientOnlyResponse(request: DSRequest, serverData: IscArray[Record]): DSResponse
    def getDataProtocol(dsRequest: DSRequest): DSProtocol
    def getDisplayValue(fieldName: String, value: JSAny): JSAny
    def getFetchDataURL(criteria: Criteria, requestProperties: DSRequest = js.native): String
    def getField(fieldName: String): JSUndefined[DataSourceField]
    def getFieldCriterion(criterion: Criteria, fieldName: String): Criteria
    def getFieldForDataPath(dataPath: DataPath): DataSourceField
    def getFieldNames(excludeHidden: Boolean = js.native): IscArray[String]
    def getFieldOperatorMap(field: String | DataSourceField, includeHidden: Boolean = js.native, valueType: OperatorValueType = js.native, omitValueType: Boolean = js.native): ValueMap
    def getFieldOperators(field: String | DataSourceField): IscArray[OperatorId]
    def getFile(fileSpec: FileSpec | String, callback: GetFileCallback)
    def getFileURL(data: Record, fieldName: String = js.native, requestProperties: DSRequest = js.native): String
    def getFileVersion(fileSpec: FileSpec | String, version: Date, callback: GetFileVersionCallback): void
    def getLegalChildTags(): void
    def getPrimaryKeyField(): DataSourceField
    def getPrimaryKeyFieldName(): String
    def getPrimaryKeyFieldNames(): IscArray[String]
    def getPrimaryKeyFields(): Record
    def getSearchOperator(operatorId: OperatorId): Operator
    def getTypeOperatorMap(`type`: FieldType, includeHidden: Boolean, valueType: OperatorValueType, omitValueType: Boolean): ValueMap
    def getTypeOperators(typeName: FieldType | String): IscArray[OperatorId]
    def getUpdatedData(dsRequest: DSRequest, dsResponse: DSResponse, useDataFromRequest: Boolean): IscArray[DataSourceRecord]
    def getXMLRequestBody(dsRequest: DSRequest): String
    var globalNamespaces: JSObject
    val guestUserId: String
    var handleError: js.Function2[DSResponse, DSRequest, Boolean]
    def hasAllData(): Boolean
    def hasFile(fileSpec: FileSpec | String, callback: HasFileCallback)
    def hasFileVersion(fileSpec: FileSpec | String, version: Date, callback: HasFileCallback)
    val iconField: String
    val ID: ID
    val idClassName: String
    val ignoreTextMatchStyleCaseSensitive: Boolean
    val infoField: String
    val inheritanceMode: DSInheritanceMode
    val inheritsFrom: DataSource | ID
    def invalidateCache(): void
    val jsonPrefix: String
    val jsonSuffix: String
    def listFiles(criteria: Criteria, callback: DSCallback): void
    def listFileVersions(fileSpec: FileSpec | String, callback: DSCallback): void
    val lookAhead: Int
    val maxFileVersions: Int
    val noNullUpdates: Boolean
    val nullBooleanValue: Boolean
    val nullDateValue: Date
    val nullFloatValue: Float
    val nullIntegerValue: Int
    val nullStringValue: String
    val omitNullDefaultsOnAdd: String
    val operationBindings: IscArray[OperationBinding]
    val ownerIdField: String
    val patternEscapeChar: String
    val patternMultiWildcard: String | IscArray[String]
    val patternSingleWildcard: String | IscArray[String]
    def performCustomOperation(operationId: String, data: Record = js.native, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    val pluralTitle: String
    val preventHTTPCaching: Boolean
    def processResponse(requestId: String, dsResponse: DSResponse): void
    val progressiveLoading: Boolean
    val progressiveLoadingThreshold: Int
    val projectFileKey: String
    val projectFileLocations: IscArray[String]
    val qualifyColumnNames: Boolean
    val quoteColumnNames: Boolean
    val quoteTableName: Boolean
    val recordName: String
    def recordsAreEqual(record1: Record, record2: Record): Boolean
    def recordsAsText(records: IscArray[Record], settings: TextExportSettings = js.native): String
    def recordsFromText(text: String, settings: TextExportSettings = js.native): IscArray[Record]
    def recordsFromXML(elements: IscArray[XMLElement], callback: Callback): IscArray[Record]
    val recordXPath: XPathExpression
    def removeFile(fileSpec: FileSpec | String, callback: DSCallback = js.native): void
    def removeFileVersion(fileSpec: FileSpec | String, version: Date, callback: DSCallback = js.native): void
    def renameFile(oldFileSpec: FileSpec | String, newFileSpec: FileSpec | String, callback: DSCallback = js.native): void
    def removeSearchOperator(operator: Operator): void
    val requestProperties: DSRequest
    val requiredMessage: String
    val requires: VelocityExpression
    val requiresAuthentication: Boolean
    val requiresRole: String
    val resultBatchSize: Int
    val resultSetClass: JSObject
    val resultTreeClass: JSObject
    def saveFile(fileSpec: FileSpec | String, contents: String, callback: DSCallback = js.native): void
    val schema: String
    val schemaBean: String
    val schemaNamespace: URN
    val script: String
    val sendExtraFields: Boolean
    val sendParentNode: Boolean
    val sequenceMode: SequenceMode
    val serverConstructor: String
    val serverObject: ServerObject
    val serverType: DSServerType
    val serviceNamespace: URN
    def setCacheAllData(shouldCache: Boolean): void
    def setCacheData(data: IscArray[Record]): void
    def setClientOnly()
    def setTypeOperators(typeName: FieldType | String, operators: IscArray[OperatorId]): void
    val showLocalFieldsOnly: Boolean
    val showPrompt: Boolean
    val sparseUpdates: Boolean
    def splitCriteria(criteria: Criteria, fields: IscArray[String]): Criteria
    val sqlPaging: SQLPagingStrategy
    val sqlUsePagingHint: Boolean
    val strictSQLFiltering: Boolean
    def supportsAdvancedCriteria(): Boolean
    def supportsTextMatchStyle(textMatchStyle: TextMatchStyle): Boolean
    val tableCode: String
    val tableName: String
    val tagName: String
    val testFileName: String
    val title: String
    val titleField: String
    val transformMultipleFields: Boolean
    var transformRequest: js.Function1[DSRequest, _]
    var transformResponse: js.Function3[DSResponse, DSRequest, JSON, DSResponse]
    val translatePatternOperators: Boolean
    val trimMilliseconds: Boolean
    def updateCaches(dsResponse: DSResponse, dsRequest: DSRequest = js.native): void
    def updateData(updatedRecord: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    val useAnsiJoins: Boolean
    val useFlatFields: Boolean
    val useHttpProxy: Boolean
    val useLocalValidators: Boolean
    def useOfflineResponse(dsResponse: DSResponse, dsRequest: DSRequest): Boolean
    val useOfflineStorage: Boolean
    val useParentFieldOrder: Boolean
    val useSpringTransaction: Boolean
    val useStrictJSON: Boolean
    val useSubselectForRowCount: Boolean
    val useTestDataFetch: Boolean
    def validateData(values: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    val validateRelatedRecords: Boolean
    def viewFile(data: Record, callback: DSCallback = js.native, requestProperties: DSRequest = js.native): void
    val xmlNamespaces: JSObject
    def xmlSerialize(data: JSAny, flags: SerializationContext)
}

@js.native
abstract trait AbstractDataSourceCompanion extends AbstractClassCompanion {
    var _operators: JSObject = js.native
    def addSearchOperator(operator: Operator): void = js.native
    def applyRecordSummaryFunction(summaryFunction: SummaryFunction, record: DataSourceRecord, fields: IscArray[DataSourceField], summaryField: DataSourceField): JSAny = js.native
    def canFlattenCriteria(criteria: AdvancedCriteria): Boolean = js.native
    def combineCriteria(criteria1: Criteria, criteria2: Criteria, outerOperator: CriteriaCombineOperator = js.native, textMatchStyle: TextMatchStyle = js.native): Criteria = js.native
    def convertCriteria(criteria: Criteria, textMatchStyle: TextMatchStyle = js.native): AdvancedCriteria = js.native
    def copyCriteria(criteria: Criteria): Criteria = js.native
    var deepCloneOnEdit: Boolean = js.native
    def exportClientData(data: IscArray[Record], requestProperties: DSRequest, callback: DSCallback): void = js.native
    def flattenCriteria(criteria: AdvancedCriteria): AdvancedCriteria = js.native
    def get(id: ID): DataSource = js.native
    def getAdvancedCriteriaDescription(criteria: AdvancedCriteria | Criterion, dataSource: DataSource): String = js.native
    def getAutoTitle(identifier: ID): String = js.native
    def getDataSource(id: ID): DataSource = js.native
    def getFieldValue(field: DataSourceField | ListGridField | DetailViewerField | FormItem, record: Record | DataPath | Canvas | String): JSAny = js.native
    def getLoaderURL(): String = js.native
    def getSimpleErrors(dsResponse: DSResponse): JSObject = js.native
    def getSortBy(sortSpecifiers: IscArray[SortSpecifier]): IscArray[String] = js.native
    def getSortSpecifiers(sortBy: IscArray[String]): IscArray[String] = js.native
    def isFlatCriteria(criteria: AdvancedCriteria): Boolean = js.native
    def load(dsID: ID | IscArray[ID], callback: Callback, forceReload: Boolean = js.native): void = js.native
    var loaderURL: URL = js.native
    def loadWithParents(dsID: ID | IscArray[ID], callback: Callback, forceReload: Boolean = js.native): void = js.native
    var maxFileSizeExceededMessage: String = js.native
    var offlineMessage: String = js.native
    def registerRecordSummaryFunction(methodName: String, summaryFunction: SummaryFunction): void = js.native
    def saveValueViaDataPath(field: DataSourceField | ListGridField | DetailViewerField | FormItem, dataPath: DataPath, value: JSAny, values: Record, reason: String): void = js.native
    var serializeTimeAsDatetime: Boolean = js.native
    def setLoaderURL(url: URL): void = js.native
    def setTypeOperators(typeName: String | FieldType | FormItemComponentType, operators: IscArray[OperatorId] | IscArray[String]): void = js.native
    def removeData(data: Record, fieldName: String = js.native, requestProperties: DSRequest = js.native): void = js.native
}

@js.native
object DataSourceStatic extends AbstractDataSourceCompanion

