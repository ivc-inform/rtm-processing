package com.simplesys.SmartClient.Forms.formsItems.props.multiFileItem

import com.simplesys.SmartClient.Layout.props.VStackProps
import com.simplesys.option.{ScNone, ScOption}

class MultiFilePickerProps extends VStackProps {
    var maxUploadFields: ScOption[Int] = ScNone
    var minUploadFields: ScOption[Int] = ScNone
}
