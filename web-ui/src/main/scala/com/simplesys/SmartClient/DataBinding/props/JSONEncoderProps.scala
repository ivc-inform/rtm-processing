package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.System.Types.JSONCircularReferenceMode.JSONCircularReferenceMode
import com.simplesys.System.Types.JSONDateFormat.JSONDateFormat
import com.simplesys.System.Types.JSONInstanceSerializationMode.JSONInstanceSerializationMode
import com.simplesys.option.{ScNone, ScOption}

class JSONEncoderProps extends ClassProps {
    var circularReferenceMarker: ScOption[String] = ScNone
    var circularReferenceMode: ScOption[JSONCircularReferenceMode] = ScNone
    var dateFormat: ScOption[JSONDateFormat] = ScNone
    var prettyPrint: ScOption[Boolean] = ScNone
    var serializeInstances: ScOption[JSONInstanceSerializationMode] = ScNone
    var showDebugOutput: ScOption[Boolean] = ScNone
    var skipInternalProperties: ScOption[Boolean] = ScNone
    var strictQuoting: ScOption[Boolean] = ScNone

}
