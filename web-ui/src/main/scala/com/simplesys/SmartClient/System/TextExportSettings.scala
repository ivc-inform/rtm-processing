package com.simplesys.SmartClient.System

import com.simplesys.System.Types.DateDisplayFormat.DateDisplayFormat
import com.simplesys.System.Types.ForceTextApproach.ForceTextApproach
import com.simplesys.System.Types.TimeDisplayFormat.TimeDisplayFormat

import scala.scalajs.js

@js.native
trait TextExportSettings extends TextSettings {
    var dateFormat: DateDisplayFormat
    var dateTimeFormat: DateDisplayFormat
    var forceText: ForceTextApproach
    var nullValueText: String
    var quoteValues: Boolean
    var timeFormat: TimeDisplayFormat
    var useDisplayValue: Boolean
}
