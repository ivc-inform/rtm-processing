package com.simplesys.SmartClient.Forms.props

import com.simplesys.SmartClient.Control.IButton
import com.simplesys.SmartClient.Forms.formsItems.DateRangeItem
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.option.{ScNone, ScOption}

class DateRangeDialogProps extends WindowProps {
    var cancelButton: ScOption[IButton] = ScNone
    var cancelButtonTitle: ScOption[String] = ScNone
    var clearButton: ScOption[IButton] = ScNone
    var clearButtonTitle: ScOption[String] = ScNone
    var headerTitle: ScOption[String] = ScNone
    var okButton: ScOption[IButton] = ScNone
    var okButtonTitle: ScOption[String] = ScNone
    var rangeItem: ScOption[DateRangeItem] = ScNone
}
