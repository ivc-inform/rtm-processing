package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}

class SectionItemProps extends CanvasItemProps {
    var canCollapse: ScOption[Boolean] = ScNone
    var canTabToHeader: ScOption[Boolean] = ScNone
    var itemIds: ScOption[IscArray[String]] = ScNone
    var sectionExpanded: ScOption[Boolean] = ScNone
    var sectionHeaderClass: ScOption[String] = ScNone
    `type` = FormItemComponentType.SectionItem
}
