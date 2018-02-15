package com.simplesys.js.components

import com.simplesys.SmartClient.App.{AbstractCommonTreeListGridEditorComponentCompanion, CommonTreeListGridEditorComponent}
import com.simplesys.SmartClient.Layout.IconMenuButtonSS
import com.simplesys.System.JSUndefined

import scala.scalajs.js

@js.native
trait ScenarioCompain extends CommonTreeListGridEditorComponent {
    val functionButton: JSUndefined[IconMenuButtonSS]
}

@js.native
abstract trait AbstractScenarioCompainCompanion extends AbstractCommonTreeListGridEditorComponentCompanion {
}


