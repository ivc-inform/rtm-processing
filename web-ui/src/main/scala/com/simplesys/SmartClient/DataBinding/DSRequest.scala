package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.RPC.{AbstractRPCRequestCompanion, RPCRequest}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.{JSFunction, JSObject, JSUndefined}
import com.simplesys.System.Types.DSOperationType.DSOperationType
import com.simplesys.System.Types.DSProtocol.DSProtocol
import com.simplesys.System.Types.ExportDisplay.ExportDisplay
import com.simplesys.System.Types.ExportFormat.ExportFormat
import com.simplesys.System.Types.ExportImageFormat.ExportImageFormat
import com.simplesys.System.Types.PropertyIdentifier.PropertyIdentifier
import com.simplesys.System.Types.{Callback, Record}
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.ValidationMode.ValidationMode

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
trait InternalClientContext extends JSObject {
  var _callback: JSUndefined[Callback]
}

@js.native
trait DSRequest extends RPCRequest {
    val additionalOutputs: String
    val internalClientContext: InternalClientContext
    val componentId: String
    val channelMessage4AddOperation: JSUndefined[String]
    val channelMessage4EndAddOperation: JSUndefined[String]
    val channelMessage4DeleteOperation: JSUndefined[String]
    val channelMessage4EndDeleteOperation: JSUndefined[String]
    val dataProtocol: DSProtocol
    val dataSource: String
    val endRow: Int
    val exportAs: ExportFormat
    val exportCSS: String
    val exportData: IscArray[Record]
    val exportDatesAsFormattedString: Boolean
    val exportDelimiter: String
    val exportDisplay: ExportDisplay
    val exportFields: IscArray[String]
    val exportFilename: String
    val exportFooter: String
    val exportHeader: String
    val exportHeaderless: Boolean
    val exportImageFormat: ExportImageFormat
    val exportImageQuality: Float
    val exportPath: String
    val exportPropertyIdentifier: PropertyIdentifier
    val exportRawValues: Boolean
    val exportResults: Boolean
    val exportShowHeaderSpanTitles: Boolean
    val exportSpanTitleSeparator: String
    val exportStreaming: Boolean
    val exportTitleSeparatorChar: String
    val exportToClient: Boolean
    val exportToFilesystem: Boolean
    val exportValueFields: Boolean
    val fieldValueExpressions: JSObject
    val generateRelatedUpdates: Boolean
    val groupBy: IscArray[String]
    val headerData: JSObject
    val keepParentsOnFilter: Boolean
    val lineBreakStyle: String
    var oldValues: JSUndefined[Record]
    val operationId: String
    val operationType: JSUndefined[DSOperationType]
    val outputs: String
    val parentNode: TreeNode
    val pendingAdd: Boolean
    val progressiveLoading: Boolean
    val requestId: String
    val resultSet: ResultSet
    val resultTree: ResultTree
    val shouldUseCache: Boolean
    var sortBy: String | IscArray[String] | IscArray[SortSpecifier]
    val startRow: Int
    val streamResults: Boolean
    val summaryFunctions: JSObject
    val textMatchStyle: TextMatchStyle
    val useFlatFields: Boolean
    val useFlatHeaderFields: Boolean
    val useStrictJSON: Boolean
    val validationMode: ValidationMode
}

@js.native
abstract trait AbstractDSRequestCompanion extends AbstractRPCRequestCompanion {
}

@js.native
@JSName("isc.DSRequest")
object DSRequestStatic extends AbstractDSRequestCompanion

