package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.SmartClient.DataBinding.dataSource.{DSRequestModifier, Mail}
import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.RPC.ServerObject
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.DSDataFormat.DSDataFormat
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.DSProtocol.DSProtocol
import com.simplesys.System.Types.ExportFormat.ExportFormat
import com.simplesys.System.Types.RPCTransport.RPCTransport
import com.simplesys.System.Types.SQLPagingStrategy.SQLPagingStrategy
import com.simplesys.System.Types.SQLType.SQLType
import com.simplesys.System.Types.{URL, VelocityExpression, XPathExpression}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class OperationBindingProps extends AbstractClassProps {
    var allowAdvancedCriteria: ScOption[Boolean] = ScNone
    var allowMultiUpdate: ScOption[Boolean] = ScNone
    var autoJoinTransactions: ScOption[Boolean] = ScNone
    var cacheSyncOperation: ScOption[String] = ScNone
    var callbackParam: ScOption[String] = ScNone
    var canSyncCache: ScOption[Boolean] = ScNone
    var creatorOverrides: ScOption[Boolean] = ScNone
    var criteria: ScOption[IscArray[DSRequestModifier]] = ScNone
    var customCriteriaFields: ScOption[IscArray[String]] = ScNone
    var customFields: ScOption[IscArray[String]] = ScNone
    var customHQL: ScOption[String] = ScNone
    var customJQL: ScOption[String] = ScNone
    var customSQL: ScOption[String] = ScNone
    var customValueFields: ScOption[IscArray[String]] = ScNone
    var dataFormat: ScOption[DSDataFormat] = ScNone
    var dataProtocol: ScOption[DSProtocol] = ScNone
    var dataTransport: ScOption[RPCTransport] = ScNone
    var dataURL: ScOption[URL] = ScNone
    var defaultParams: ScOption[JSObject] = ScNone
    var excludeCriteriaFields: ScOption[IscArray[String]] = ScNone
    var exportAs: ScOption[ExportFormat] = ScNone
    var exportFields: ScOption[IscArray[String]] = ScNone
    var exportFilename: ScOption[String] = ScNone
    var exportResults: ScOption[Boolean] = ScNone
    var groupBy: ScOption[IscArray[String]] = ScNone
    var groupClause: ScOption[String] = ScNone
    var guestUserId: ScOption[String] = ScNone
    var invalidateCache: ScOption[Boolean] = ScNone
    var lineBreakStyle: ScOption[String] = ScNone
    var mail: ScOption[Mail] = ScNone
    var methodArguments: ScOption[String] = ScNone
    var namedQuery: ScOption[String] = ScNone
    var operationId: ScOption[String] = ScNone
    var operationType: ScOption[DSOperationType] = ScNone
    var orderClause: ScOption[String] = ScNone
    var outputs: ScOption[String] = ScNone
    var ownerIdField: ScOption[String] = ScNone
    var preventHTTPCaching: ScOption[Boolean] = ScNone
    var progressiveLoading: ScOption[Boolean] = ScNone
    var providesMissingKeys: ScOption[Boolean] = ScNone
    var qualifyColumnNames: ScOption[Boolean] = ScNone
    var recordName: ScOption[String] = ScNone
    var recordXPath: ScOption[XPathExpression] = ScNone
    var requestProperties: ScOption[DSRequest] = ScNone
    var requires: ScOption[VelocityExpression] = ScNone
    var requiresAuthentication: ScOption[Boolean] = ScNone
    var requiresRole: ScOption[String] = ScNone
    var responseDataSchema: ScOption[DataSource] = ScNone
    var script: ScOption[String] = ScNone
    var selectClause: ScOption[String] = ScNone
    var serverMethod: ScOption[String] = ScNone
    var serverObject: ScOption[ServerObject] = ScNone
    var skipAudit: ScOption[Boolean] = ScNone
    var skipRowCount: ScOption[Boolean] = ScNone
    var spoofResponses: ScOption[Boolean] = ScNone
    var sqlPaging: ScOption[SQLPagingStrategy] = ScNone
    var sqlType: ScOption[SQLType] = ScNone
    var sqlUsePagingHint: ScOption[Boolean] = ScNone
    var summaryFunctions: ScOption[JSObject] = ScNone
    var tableClause: ScOption[String] = ScNone
    var transformMultipleFields: ScOption[Boolean] = ScNone
    var useFlatFields: ScOption[Boolean] = ScNone
    var useForCacheSync: ScOption[Boolean] = ScNone
    var useHttpProxy: ScOption[Boolean] = ScNone
    var useSpringTransaction: ScOption[Boolean] = ScNone
    var useSubselectForRowCount: ScOption[Boolean] = ScNone
    var values: ScOption[IscArray[DSRequestModifier]] = ScNone
    var valuesClause: ScOption[String] = ScNone
    var whereClause: ScOption[String] = ScNone
    var wsOperation: ScOption[String] = ScNone
    var xmlNamespaces: ScOption[JSObject] = ScNone

}
