package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.DataBinding.DetailViewer
import com.simplesys.SmartClient.DataBinding.fieldPicker.FieldPickerWindow
import com.simplesys.SmartClient.DataBinding.props.DataBoundComponentProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.detailViewer.{DetailViewerField, DetailViewerRecord}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class DetailViewerProps extends CanvasProps with DataBoundComponentProps {
    type classHandler <: Canvas

    var blockSeparator: ScOption[HTMLString] = ScNone
    var blockStyle: ScOption[CSSStyleName] = ScNone
    var canPickFields: ScOption[Boolean] = ScNone
    var cellStyle: ScOption[CSSStyleName] = ScNone
    var configureFieldsText: ScOption[String] = ScNone
    var data: ScOption[IscArray[DetailViewerRecord]] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var datetimeFormatter: ScOption[DateDisplayFormat] = ScNone
    var emptyCellValue: ScOption[HTMLString] = ScNone
    var emptyMessage: ScOption[HTMLString] = ScNone
    var emptyMessageStyle: ScOption[CSSStyleName] = ScNone
    var fieldIdProperty: ScOption[String] = ScNone
    var fieldPickerFieldProperties: ScOption[IscArray[String]] = ScNone
    var fieldPickerWindow: ScOption[FieldPickerWindow] = ScNone
    var fields: ScOption[IscArray[DetailViewerField]] = ScNone
    var formatCellValue: ScOption[js.Function3[String, DetailViewerRecord, DetailViewerField, void]] = ScNone
    var getCellCSSText: ScOption[js.Function4[JSAny, DetailViewerField, DetailViewerRecord, DetailViewer, CSSText]] = ScNone
    var getCellStyle: ScOption[js.Function4[JSAny, DetailViewerField, DetailViewerRecord, DetailViewer, CSSStyleName]] = ScNone
    var headerStyle: ScOption[CSSStyleName] = ScNone
    var labelPrefix: ScOption[HTMLString] = ScNone
    var labelStyle: ScOption[CSSStyleName] = ScNone
    var labelSuffix: ScOption[HTMLString] = ScNone
    var linkTextProperty: ScOption[String] = ScNone
    var loadingMessage: ScOption[HTMLString] = ScNone
    var loadingMessageStyle: ScOption[CSSStyleName] = ScNone
    var printCellStyle: ScOption[CSSStyleName] = ScNone
    var printHeaderStyle: ScOption[CSSStyleName] = ScNone
    var printLabelStyle: ScOption[CSSStyleName] = ScNone
    var recordsPerBlock: ScOption[Int] = ScNone
    var separatorStyle: ScOption[SCClassName] = ScNone
    var showEmptyField: ScOption[Boolean] = ScNone
    var showEmptyMessage: ScOption[Boolean] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var valueAlign: ScOption[Alignment] = ScNone
    var wrapLabel: ScOption[Boolean] = ScNone
    var wrapValues: ScOption[Boolean] = ScNone
}
