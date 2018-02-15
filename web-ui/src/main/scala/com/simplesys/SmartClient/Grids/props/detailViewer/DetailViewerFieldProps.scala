package com.simplesys.SmartClient.Grids.props.detailViewer

import com.simplesys.SmartClient.DataBinding.DetailViewer
import com.simplesys.SmartClient.Grids.detailViewer.DetailViewerRecord
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSObject
import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.HiliteIconPosition.HiliteIconPosition
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat
import com.simplesys.System.Types.{CSSClassName, FormatString, HTMLString}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js

class DetailViewerFieldProps extends AbstractClassProps {
    var canExport: ScOption[Boolean] = ScNone
    var canHilite: ScOption[Boolean] = ScNone
    var cellStyle: ScOption[CSSClassName] = ScNone
    var dataPath: ScOption[String] = ScNone
    var dateFormatter: ScOption[DateDisplayFormat] = ScNone
    var decimalPad: ScOption[Int] = ScNone
    var decimalPrecision: ScOption[Int] = ScNone
    var displayField: ScOption[String] = ScNone
    var emptyCellValue: ScOption[HTMLString] = ScNone
    var escapeHTML: ScOption[Boolean] = ScNone
    var exportFormat: ScOption[FormatString] = ScNone
    var format: ScOption[FormatString] = ScNone
    var height: ScOption[Int] = ScNone
    var hiliteIconHeight: ScOption[Int] = ScNone
    var hiliteIconLeftPadding: ScOption[Int] = ScNone
    var hiliteIconPosition: ScOption[HiliteIconPosition] = ScNone
    var hiliteIconRightPadding: ScOption[Int] = ScNone
    var hiliteIconSize: ScOption[Int] = ScNone
    var hiliteIconWidth: ScOption[Int] = ScNone
    var imageHeight: ScOption[Int] = ScNone
    var imageSize: ScOption[Int] = ScNone
    var imageURLPrefix: ScOption[String] = ScNone
    var imageWidth: ScOption[Int] = ScNone
    var includeFrom: ScOption[String] = ScNone
    var linkText: ScOption[String] = ScNone
    var linkTextProperty: ScOption[String] = ScNone
    var linkURLSuffix: ScOption[String] = ScNone
    var linkURLPrefix: ScOption[String] = ScNone
    var name: ScOption[String] = ScNone
    var printCellStyle: ScOption[CSSClassName] = ScNone
    var showFileInline: ScOption[Boolean] = ScNone
    var showIf: ScOption[js.Function2[DetailViewer, IscArray[DetailViewerRecord], Boolean]] = ScNone
    var target: ScOption[String] = ScNone
    var timeFormatter: ScOption[TimeDisplayFormat] = ScNone
    var title: ScOption[HTMLString] = ScNone
    var value: ScOption[HTMLString] = ScNone
    var valueMap: ScOption[JSObject] = ScNone
}
