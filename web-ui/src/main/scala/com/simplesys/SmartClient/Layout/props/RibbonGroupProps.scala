package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Layout.IconButton
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.JSAny
import com.simplesys.option.{ScNone, ScOption}

class RibbonGroupProps extends ToolStripGroupProps {
    var newControlConstructor: ScOption[Class] = ScNone
    var newControlDefaults: ScOption[IconButton] = ScNone
    var colWidths: ScOption[Seq[JSAny]] = ScNone
}
