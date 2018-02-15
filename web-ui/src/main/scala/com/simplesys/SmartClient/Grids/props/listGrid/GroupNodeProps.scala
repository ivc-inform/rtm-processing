package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSAny
import com.simplesys.System.Types.HTMLString
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class GroupNodeProps extends AbstractClassProps {
    var groupMembers: ScOption[IscArray[ListGridRecord]] = ScNone
    var groupTitle: ScOption[HTMLString] = ScNone
    var groupValue: ScOption[JSAny] = ScNone
}
