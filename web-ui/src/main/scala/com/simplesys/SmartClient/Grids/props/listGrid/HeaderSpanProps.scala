package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.Grids.listGrid.HeaderSpan
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.SCClassName
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class HeaderSpanProps extends AbstractClassProps {
    var align: ScOption[Alignment] = ScNone
    var fields: ScOption[IscArray[String]] = ScNone
    var headerBaseStyle: ScOption[SCClassName] = ScNone
    var headerTitle: ScOption[String] = ScNone
    var headerTitleStyle: ScOption[SCClassName] = ScNone
    var height: ScOption[Int] = ScNone
    var name: ScOption[String] = ScNone
    var spans: ScOption[IscArray[HeaderSpan]] = ScNone
    var title: ScOption[String] = ScNone
    var valign: ScOption[VerticalAlignment] = ScNone
}
