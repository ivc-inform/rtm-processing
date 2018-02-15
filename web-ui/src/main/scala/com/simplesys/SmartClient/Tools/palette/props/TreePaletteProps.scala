package com.simplesys.SmartClient.Tools.palette.props

import com.simplesys.SmartClient.Grids.props.TreeGridProps
import com.simplesys.SmartClient.Tools.palette.TreePalette
import com.simplesys.SmartClient.Tools.props.PaletteProps

class TreePaletteProps extends TreeGridProps with PaletteProps{
    type classHandler <: TreePalette
}
