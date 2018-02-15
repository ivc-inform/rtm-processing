package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Layout.{Layout, ToolStrip}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class ToolStripProps extends LayoutProps {
    type classHandler <: ToolStrip

    var externalStylesheet: ScOption[String] = ScNone
    var formWrapper: ScOption[DynamicForm] = ScNone
    var formWrapperConstructor: ScOption[String] = ScNone
    var formWrapperDefaults: ScOption[JSObject] = ScNone
    var formWrapperProperties: ScOption[JSObject] = ScNone
    var groupTitleAlign: ScOption[Alignment] = ScNone
    var groupTitleOrientation: ScOption[VerticalAlignment] = ScNone
    var separatorClass: ScOption[String] = ScNone
    var separatorSize: ScOption[Int] = ScNone
    var showGroupTitle: ScOption[Boolean] = ScNone
    var verticalStyleName: ScOption[String] = ScNone
}
