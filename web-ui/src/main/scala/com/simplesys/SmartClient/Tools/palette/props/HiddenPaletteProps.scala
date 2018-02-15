package com.simplesys.SmartClient.Tools.palette.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.PaletteNode
import com.simplesys.SmartClient.Tools.props.PaletteProps
import com.simplesys.option.{ScNone, ScOption}

class HiddenPaletteProps extends PaletteProps {
    val data: ScOption[IscArray[PaletteNode]] = ScNone
}
