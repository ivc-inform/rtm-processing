package com.simplesys.components

import com.saxonica.bytecode.util.Callback
import com.simplesys.SmartClient.Layout.{AbstractChainMasterDetailCompanion, ChainMasterDetail, WindowSS}
import com.simplesys.System.Types.void
import com.simplesys.System._
import ru.simplesys.defs.app.scala.container.ScenariosScr_ScenarioJsonStorage_scenarioDataRecord

import scala.scalajs.js

@js.native
trait ConstructorForm extends ChainMasterDetail {
    def getJSONGraph(callback: JSUndefined[js.Function1[String, _]] = jSUndefined): void
    def updateInBase(_updateRecord: JSUndefined[ScenariosScr_ScenarioJsonStorage_scenarioDataRecord] = jSUndefined, _callback: JSUndefined[Callback] = jSUndefined): void
    def recoverGraphFromBase(idScenario:Double, _callback: JSUndefined[Callback] = jSUndefined): void
    def refresh(): void
    def getSaveState(): Boolean
    val codeScenario: JSUndefined[String]
    val captionScenario: JSUndefined[String]
    def propertyEditorRef(): JSUndefined[PropertyEditorWindow]
    def historyEditorRef(): JSUndefined[WindowSS]
}

@js.native
abstract trait AbstractSettingFormCompanion extends AbstractChainMasterDetailCompanion {
}


