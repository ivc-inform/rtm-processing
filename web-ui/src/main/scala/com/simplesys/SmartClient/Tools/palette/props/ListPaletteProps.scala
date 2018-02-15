package com.simplesys.SmartClient.Tools.palette.props

import com.simplesys.SmartClient.Grids.props.ListGridProps
import com.simplesys.SmartClient.Tools.palette.ListPalette
import com.simplesys.SmartClient.Tools.props.PaletteProps

class ListPaletteProps extends ListGridProps with PaletteProps{
    type classHandler <: ListPalette
}
