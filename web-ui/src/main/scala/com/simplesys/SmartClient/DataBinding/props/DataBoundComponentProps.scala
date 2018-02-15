package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.{DSRequest, DataSource}
import com.simplesys.SmartClient.Grids.listGrid.{Hilite, HiliteEditor}
import com.simplesys.SmartClient.Layout.props.WindowProgressDialogProps
import com.simplesys.SmartClient.Layout.{Window, WindowProgressDialog}
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.System.{Common, IscArray, WindowProgressDialog, isc, _}
import com.simplesys.System.Types.DragDataAction.DragDataAction
import com.simplesys.System.Types.FetchMode.FetchMode
import com.simplesys.System.Types.FieldNamingStrategy.FieldNamingStrategy
import com.simplesys.System.Types.HiliteIconPosition.HiliteIconPosition
import com.simplesys.System.Types.RecategorizeMode.RecategorizeMode
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction4, |}

@js.native
trait Operation extends JSObject {
    val ID: ID
    val dataSource: DataSource | String
    val filterType: String
    val loadDataOnDemand: Boolean
    val source: String
    val `type`: String
}

trait DataBoundComponentProps extends ClassProps {

    var addDropValues: ScOption[Boolean] = ScNone
    var addFormulaFieldText: ScOption[String] = ScNone
    var addOperation: ScOption[String] = ScNone
    var addSummaryFieldText: ScOption[String] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var badFormulaResultValue: ScOption[String] = ScNone
    var canAddFormulaFields: ScOption[Boolean] = ScNone
    var canAddSummaryFields: ScOption[Boolean] = ScNone
    var canEditFieldAttribute: ScOption[String] = ScNone
    var canEditHilites: ScOption[Boolean] = ScNone
    var dataArity: ScOption[String] = ScNone
    var dataFetchMode: ScOption[FetchMode] = ScNone
    var dataField: ScOption[String] = ScNone
    var dataPageSize: ScOption[Int] = ScNone
    var dataSource: ScOption[DataSource] = ScNone
    var deepCloneOnEdit: ScOption[Boolean] = ScNone
    var descriptionField: ScOption[String] = ScNone
    var deleteRecords: ScOption[ThisFunction4[classHandler, IscArray[Record], Operation, DSRequest, DataSource, _]] = {
        (thiz: classHandler, records: IscArray[Record], deleteOperation: Operation, context: DSRequest, dataSource: DataSource) ⇒

            //isc debugTrap(records, deleteOperation, context, dataSource)
            isc.addProperties(context, js.Dictionary("prompt" → context.prompt.getOrElse(isc.RPCManager.removeDataPrompt)))

            val keyFieldNames: IscArray[String] = dataSource.getPrimaryKeyFieldNames()
            val fieldNames: IscArray[String] = dataSource.getFieldNames()

            var progressBar: JSUndefined[WindowProgressDialog] = jSUndefined

            if (records.length > 1)
                progressBar = WindowProgressDialog.create(
                    new WindowProgressDialogProps {
                        maxValue = records.length.toDouble.opt
                        title = "Удаление данных".ellipsis.opt
                        headerIconPath = Common.insert.opt
                    }
                )


            val wasAlreadyQueuing = isc.RPCManager.startQueue()

            //isc debugTrap(keyFieldNames, fieldNames, wasAlreadyQueuing)
            records.foreach {
                record ⇒

                    if (record.asInstanceOf[js.Dynamic]._isGroup.undef.isEmpty) {

                        val recordKeys = isc.applyMask[JSObject](record, keyFieldNames)
                        context.oldValues = isc.applyMask[JSObject](record, fieldNames)
                        dataSource.performDSOperation(deleteOperation.`type`, recordKeys, jSUndefined, context)
                    }
            }

            val transactionID = isc.RPCManager.getQueueTransactionId()
            val _channelMessage4RemoveOperation = s"ListElements_Remove_$transactionID"
            val _channelMessage4RemoveAddOperation = s"ListElements_EndRemove_$transactionID"

            transactionID.foreach {
                transactionID ⇒

                    isc.MessagingSS.subscribe(_channelMessage4RemoveOperation, {
                        (e: MessageJS) ⇒
                            //println(s"remove data: ${e.data.toString}")
                            progressBar.foreach(_.nextStep())
                    }
                    )

                    isc.MessagingSS.subscribe(
                        _channelMessage4RemoveAddOperation, {
                            (e: MessageJS) ⇒
                                isc.MessagingSS unsubscribe _channelMessage4RemoveOperation
                                isc.MessagingSS unsubscribe _channelMessage4RemoveAddOperation

                                progressBar.foreach(_.markForDestroy())
                        }
                    )

                    if (!wasAlreadyQueuing)
                        isc.RPCManager.sendQueue(delay = 100)
            }

    }.toThisFunc.opt
    var dragDataAction: ScOption[DragDataAction] = ScNone
    var dragRecategorize: ScOption[RecategorizeMode] = ScNone
    var dragTrackerStyle: ScOption[CSSStyleName] = ScNone
    var dropValues: ScOption[JSObject] = ScNone
    var duplicateDragMessage: ScOption[String] = ScNone
    var editFormulaFieldText: ScOption[String] = ScNone
    var editHilitesDialogTitle: ScOption[String] = ScNone
    var editHilitesText: ScOption[String] = ScNone
    var editSummaryFieldText: ScOption[String] = ScNone
    var emptyExportMessage: ScOption[String] = ScNone
    var exportAll: ScOption[Boolean] = ScNone
    var exportFields: ScOption[Seq[String]] = ScNone
    var exportIncludeSummaries: ScOption[Boolean] = ScNone
    var fetchOperation: ScOption[String] = ScNone
    var fieldEditorWindow: ScOption[Window] = ScNone
    var fieldEditorWindowTitle: ScOption[HTMLString] = ScNone
    var fieldNamingStrategy: ScOption[FieldNamingStrategy] = ScNone
    var hiliteEditor: ScOption[HiliteEditor] = ScNone
    var hiliteIconHeight: ScOption[Int] = ScNone
    var hiliteIconLeftPadding: ScOption[Int] = ScNone
    var hiliteIconPosition: ScOption[HiliteIconPosition] = ScNone
    var hiliteIconRightPadding: ScOption[Int] = ScNone
    var hiliteIcons: ScOption[Seq[String]] = ScNone
    var hiliteIconSize: ScOption[Int] = ScNone
    var hiliteIconWidth: ScOption[Int] = ScNone
    var hiliteProperty: ScOption[String] = ScNone
    var hilites: ScOption[Seq[Hilite]] = ScNone
    var hiliteState: ScOption[String] = ScNone
    var iconField: ScOption[String] = ScNone
    var includeHilitesInSummaryFields: ScOption[Boolean] = ScNone
    var infoField: ScOption[String] = ScNone
    var initialCriteria: ScOption[Criteria] = ScNone
    var missingSummaryFieldValue: ScOption[String] = ScNone
    var offlineMessage: ScOption[String] = ScNone
    var offlineSaveMessage: ScOption[String] = ScNone
    var preventDuplicates: ScOption[Boolean] = ScNone
    var progressiveLoading: ScOption[Boolean] = ScNone
    var removeFormulaFieldText: ScOption[String] = ScNone
    var removeOperation: ScOption[String] = ScNone
    var removeSummaryFieldText: ScOption[String] = ScNone
    var showComplexFields: ScOption[Boolean] = ScNone
    var showDetailFields: ScOption[Boolean] = ScNone
    var showHiddenFields: ScOption[Boolean] = ScNone
    var showOfflineMessage: ScOption[Boolean] = ScNone
    var titleField: ScOption[String] = ScNone
    var unknownErrorMessage: ScOption[HTMLString] = ScNone
    var updateOperation: ScOption[String] = ScNone
    var useAllDataSourceFields: ScOption[Boolean] = ScNone
    var useFlatFields: ScOption[Boolean] = ScNone
}
