package com.simplesys.components.props

import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.components.TabRTM
import com.simplesys.components.TabTypes.TabTypes
import com.simplesys.option.{ScNone, ScOption}

class TabRTMProps extends TabProps {
    type classHandler <: TabRTM

    var tabType: ScOption[TabTypes] = ScNone
}
