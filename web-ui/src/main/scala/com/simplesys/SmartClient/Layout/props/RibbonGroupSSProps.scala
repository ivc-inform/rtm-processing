package com.simplesys.SmartClient.Layout.props


import com.simplesys.System.JSAny
import com.simplesys.System.Types.Alignment
import com.simplesys.option.ScOption._

class RibbonGroupSSProps extends RibbonGroupProps {
    numRows = 3.opt
    rowHeight = 26.opt
    colWidths  = Seq[JSAny](40, "*").opt
    defaultLayoutAlign = Alignment.center
}
