package com.simplesys.js.components

import com.simplesys.SmartClient.App.{AbstractCommonListGridEditorComponentCompanion, CommonListGridEditorComponent}
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait HistoryList extends CommonListGridEditorComponent {
    def recoverGraphFromHistory(idScenario: Double, _callback: JSUndefined[Callback] = jSUndefined): void
}

@js.native
abstract trait AbstractHistoryListCompanion extends AbstractCommonListGridEditorComponentCompanion {
}


