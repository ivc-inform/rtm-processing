package com.simplesys.SmartClient.Layout.props.sectionStack

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.LabelProps
import com.simplesys.SmartClient.Layout.Layout
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.option.{ScNone, ScOption}

class SectionHeaderProps extends LabelProps {
    var clipTitle: ScOption[Boolean] = ScNone
    var controls: ScOption[IscArray[Canvas]] = ScNone
    var controlsLayout: ScOption[Layout] = ScNone
}
