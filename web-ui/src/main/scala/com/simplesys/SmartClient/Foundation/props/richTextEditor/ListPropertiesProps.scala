package com.simplesys.SmartClient.Foundation.props.richTextEditor

import com.simplesys.SmartClient.Foundation.richTextEditor.ListProperties
import com.simplesys.System.Types.SCImgURL
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class ListPropertiesProps extends AbstractClassProps {
    var image: ScOption[SCImgURL] = ScNone
    var startNumber: ScOption[Int] = ScNone
    var style: ScOption[ListProperties] = ScNone
}
