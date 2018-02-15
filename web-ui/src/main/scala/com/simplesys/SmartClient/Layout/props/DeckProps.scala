package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.Deck
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.option.{ScNone, ScOption}

class DeckProps extends LayoutProps {
    type classHandler <: Deck
    var currentPane: ScOption[Canvas] = ScNone
    var panes: ScOption[IscArray[Canvas]] = ScNone
}
