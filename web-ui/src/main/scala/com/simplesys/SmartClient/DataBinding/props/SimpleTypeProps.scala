package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.Validator
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.Types.OperatorId.OperatorId
import com.simplesys.System.Types.{FormatString, ID, ValueMap}
import com.simplesys.option.{ScNone, ScOption}

class SimpleTypeProps extends ClassProps {
    var defaultGroupingMode: ScOption[String] = ScNone
    var editorType: ScOption[FormItem] = ScNone
    var exportFormat: ScOption[FormatString] = ScNone
    var fieldProperties: ScOption[DataSourceField] = ScNone
    var filterEditorType: ScOption[FormItem] = ScNone
    var format: ScOption[FormatString] = ScNone
    var groupingModes: ScOption[ValueMap] = ScNone
    var inheritsFrom: ScOption[ID] = ScNone
    var name: ScOption[ID] = ScNone
    var readOnlyEditorType: ScOption[FormItem] = ScNone
    var validators: ScOption[Seq[Validator]] = ScNone
    var validOperators: ScOption[Seq[OperatorId]] = ScNone
    var valueMap: ScOption[ValueMap] = ScNone
}
