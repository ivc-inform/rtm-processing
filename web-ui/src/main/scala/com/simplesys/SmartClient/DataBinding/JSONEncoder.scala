package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.{AbstractClassCompanion, Class}
import com.simplesys.System.Types.JSONCircularReferenceMode.JSONCircularReferenceMode
import com.simplesys.System.Types.JSONDateFormat.JSONDateFormat
import com.simplesys.System.Types.JSONInstanceSerializationMode.JSONInstanceSerializationMode
import com.simplesys.System.JSAny

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait JSONEncoder extends Class {
    val circularReferenceMarker: String
    val circularReferenceMode: JSONCircularReferenceMode
    val dateFormat: JSONDateFormat
    def encode(obj: JSAny): String
    def encodeDate(theDate: Date): String
    val prettyPrint: Boolean
    val serializeInstances: JSONInstanceSerializationMode
    val showDebugOutput: Boolean
    val skipInternalProperties: Boolean
    val strictQuoting: Boolean
}

@js.native
abstract trait AbstractJSONEncoderCompanion extends AbstractClassCompanion {
}


