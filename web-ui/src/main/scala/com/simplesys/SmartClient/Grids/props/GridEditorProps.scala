package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.Callbacks.DSCallback
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.{ListGridFieldProps, ListGridRecordProps}
import com.simplesys.SmartClient.Grids.{ListGrid, ListGridEditor}
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.Layout.props.VLayoutSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.AutoFitWidthApproach.AutoFitWidthApproach
import com.simplesys.System.Types.DateDisplayFormat._
import com.simplesys.System.Types.DragDataAction._
import com.simplesys.System.Types.DragTrackerMode.DragTrackerMode
import com.simplesys.System.Types.ExportFormat.{apply ⇒ _}
import com.simplesys.System.Types.FetchMode.{apply ⇒ _, _}
import com.simplesys.System.Types.ListGridEditEvent.ListGridEditEvent
import com.simplesys.System.Types.RecordComponentPoolingMode.RecordComponentPoolingMode
import com.simplesys.System.Types.SelectionAppearance.SelectionAppearance
import com.simplesys.System.Types.SelectionStyle._
import com.simplesys.System.Types.TextMatchStyle.TextMatchStyle
import com.simplesys.System.Types.{Criteria, DateDisplayFormat, ExportFormat}
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined

class GridEditorProps[T <: ListGridFieldProps, R <: ListGridRecordProps] extends VLayoutSSProps {

    var closedIconSuffix: ScOption[String] = ScNone
    var dropIconSuffix: ScOption[String] = ScNone
    var openIconSuffix: ScOption[String] = ScNone
    var canDragSelectText: ScOption[Boolean] = ScNone
    var canAcceptDroppedRecords: ScOption[Boolean] = ScNone
    var autoFitFieldWidth: ScOption[Boolean] = ScNone
    var canResizeFields: ScOption[Boolean] = ScNone
    var showAllRecords: ScOption[Boolean] = ScNone
    var showRecordComponentsByCell: ScOption[Boolean] = ScNone
    var showRecordComponents: ScOption[Boolean] = ScNone
    var filterOnKeypress: ScOption[Boolean] = ScNone
    var drawAheadRatio: ScOption[Double] = ScNone
    var autoSaveEdits: ScOption[Boolean] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var showRollOve: ScOption[Boolean] = ScNone
    var autoFetchData: ScOption[Boolean] = ScNone
    var showRowNumbers: ScOption[Boolean] = ScNone
    var autoSaveConfig: ScOption[Boolean] = ScNone
    var showAdvancedFilter: ScOption[Boolean] = ScNone
    var emptyMessage: ScOption[String] = ScNone
    var wrapCells: ScOption[Boolean] = ScNone
    var autoFetchTextMatchStyle: ScOption[TextMatchStyle] = ScNone
    var fixedRecordHeights: ScOption[Boolean] = ScNone
    var dragTrackerMode: ScOption[DragTrackerMode] = ScNone
    var autoFitWidthApproach: ScOption[AutoFitWidthApproach] = ScNone
    var modalEditing: ScOption[Boolean] = ScNone
    var canSelectCells: ScOption[Boolean] = ScNone
    var useClientFilteringSorting: ScOption[Boolean] = ScNone
    var selectionAppearance: ScOption[SelectionAppearance] = ScNone
    var cancelEditingConfirmationMessage: ScOption[String] = ScNone
    var fetchDelay: ScOption[Int] = ScNone
    var editEvent: ScOption[ListGridEditEvent] = ScNone
    var canSelectTextExpandedField: ScOption[Boolean] = ScNone
    var showFilterEditor: ScOption[Boolean] = ScNone
    var dataPageSize: ScOption[Int] = ScNone
    var dataSource: ScOption[DataSource] = ScNone
    var saveItems: ScOption[Seq[MenuSSItemProps]] = ScNone
    var selectionType: ScOption[SelectionStyle] = ScNone
    var masterGrid: ScOption[ListGrid] = ScNone
    var canDragRecordsOut: ScOption[Boolean] = ScNone
    var canReorderRecords: ScOption[Boolean] = ScNone
    var dragDataAction: ScOption[DragDataAction] = ScNone
    var startEditingNewInForm: ScOption[Function4[JSObject, Seq[FormItem], DSCallback, DSRequest, _]] = ScNone
    var startEditingInForm: ScOption[Function4[JSObject, Seq[FormItem], DSCallback, DSRequest, _]] = ScNone
    var newRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editRequestProperties: ScOption[ThisFunction0[classHandler, DSRequest]] = ScNone
    var editingFields: ScOption[Seq[FormItem]] = ScNone
    var saveByCell: ScOption[Boolean] = ScNone
    var dataFetchMode: ScOption[FetchMode] = ScNone
    var editWindowProperties: ScOption[WindowSS] = ScNone
    var recordComponentPoolingMode: ScOption[RecordComponentPoolingMode] = ScNone
    var initialSort: ScOption[Seq[SortSpecifier]] = ScNone
    var initialCriteria: ScOption[Criteria] = ScNone

    var createRecordComponent: ScOption[ThisFunction2[classHandler, ListGridRecord, Int, JSUndefined[Canvas]]] = ScNone
    var updateRecordComponent: ScOption[ThisFunction4[classHandler, ListGridRecord, Int, Canvas, Boolean, JSUndefined[Canvas]]] = ScNone

    var selectFirstRecordAfterFetch: ScOption[Boolean] = ScNone
    var replacingFields: ScOption[Seq[ListGridFieldProps]] = ScNone

    var fields: ScOption[Seq[ListGridFieldProps]] = ScNone
    var defaultFields: ScOption[Seq[ListGridFieldProps]] = ScNone

    var datetimeFormatter: ScOption[DateDisplayFormat] = DateDisplayFormat.toEuropeanDatetime.opt
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone

}

@ScalaJSDefined
trait EmptyCriteria extends JSObject {
    val ts: Double
}

class ListGridEditorProps extends GridEditorProps[ListGridFieldProps, ListGridRecordProps] {
    type classHandler <: ListGridEditor
    var data: ScOption[Seq[ListGridRecord]] = ScNone

    var exportData: ScOption[ThisFunction3[classHandler, JSUndefined[AdvancedCriteria], JSUndefined[DSRequest], JSUndefined[DSCallback], _]] = {
        (thiz: classHandler, criteria: JSUndefined[AdvancedCriteria], requestProperties: JSUndefined[DSRequest], callback: JSUndefined[DSCallback]) ⇒

            val timestamProp = new EmptyCriteria {
                override val ts: Double = isc.timeStamp()
            }

            def getGriteria(): Criteria = if (criteria.isEmpty) thiz.getCriteria() else criteria.get

            //isc debugTrap 0

            thiz.fetchData(
                isc.addProperties(getGriteria(), timestamProp), {
                    (resp: DSResponse, data: IscArray[JSObject], req: DSRequest) ⇒
                        callback.foreach(thiz.fireCallback(_, "resp, data, req", IscArray(resp, data, req)))

                        //isc debugTrap(resp, data, req)

                        if (data.length > 0 && data(0).asInstanceOf[ErrorStructOld].error.isDefined)
                            isc.errorDetail(
                                data(0).asInstanceOf[ErrorStructOld].error.get.message.getOrElse("Unknown message"),
                                data(0).asInstanceOf[ErrorStructOld].error.get.stackTrace.getOrElse("Unknown message"),
                                "BE1379E4-354C-FFD1-C791-A417430252B7",
                                "1DB22FC7-9191-FB5E-AED7-83D3B40A8575"
                            )
                        else
                            resp.urlExportFile.foreach(window.location assign _)
                },
                DSRequest(
                    new DSRequestProps {
                        exportAs = ExportFormat.ooxml.opt
                        exportToFilesystem = true.opt
                    }
                )
            )
    }.toThisFunc.opt
}
