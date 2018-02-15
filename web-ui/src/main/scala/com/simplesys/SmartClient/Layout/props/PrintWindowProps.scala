package com.simplesys.SmartClient.Layout.props

import com.simplesys.option.{ScNone, ScOption}

class PrintWindowProps extends WindowProps {
    var externalStylesheet: ScOption[String] = ScNone
    var printButtonTitle: ScOption[String] = ScNone

}
