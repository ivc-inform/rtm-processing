package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Layout.{AbstractWindowSSCompanion, OkCancelPanel, WindowSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined
import com.simplesys.System.Types.URL

import scala.scalajs.js

@js.native
trait SettingsEditor extends WindowSS {
    var customSettingItems: JSUndefined[IscArray[FormItem]]
    var action: JSUndefined[URL]
    var okCancelPanel: JSUndefined[OkCancelPanel]
}

@js.native
abstract trait AbstractSettingsEditorCompanion extends AbstractWindowSSCompanion {
}

