package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.{Canvas, Label}
import com.simplesys.SmartClient.Layout.HLayout
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.SCClassName
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.option.{ScNone, ScOption}

class ToolStripGroupProps extends VLayoutProps {
    var externalStylesheet: ScOption[String] = ScNone
    var autoSizeToTitle: ScOption[Boolean] = ScNone
    var body: ScOption[HLayout] = ScNone
    var bodyConstructor: ScOption[String] = ScNone
    var controls: ScOption[Seq[Canvas]] = ScNone
    var label: ScOption[HLayout] = ScNone
    var labelConstructor: ScOption[String] = ScNone
    var numRows: ScOption[Int] = ScNone
    var rowHeight: ScOption[Int] = ScNone
    var titleAlign: ScOption[Alignment] = ScNone
    var title: ScOption[String] = ScNone
    var titleHeight: ScOption[Int] = ScNone
    var titleOrientation: ScOption[VerticalAlignment] = ScNone
    var titleProperties: ScOption[Label] = ScNone
    var titleStyle: ScOption[SCClassName] = ScNone
}
