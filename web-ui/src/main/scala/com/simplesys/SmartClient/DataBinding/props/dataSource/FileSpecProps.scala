package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class FileSpecProps extends AbstractClassProps {
    var fileFormat: ScOption[String] = ScNone
    var fileName: ScOption[String] = ScNone
    var fileType: ScOption[String] = ScNone
}
