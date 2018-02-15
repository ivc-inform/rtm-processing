package com.simplesys.components

import com.simplesys.SmartClient.App.{AbstractCommonTreeListGridEditorComponentCompanion, CommonTreeListGridEditorComponent}
import com.simplesys.System.JSUndefined
import com.simplesys.components.props.LoggingViewer

import scala.scalajs.js

@js.native
trait ScenarioTests extends CommonTreeListGridEditorComponent {
    val loggingViewer: JSUndefined[LoggingViewer]
}

@js.native
abstract trait AbstractScenatioTestsCompanion extends AbstractCommonTreeListGridEditorComponentCompanion {
}


