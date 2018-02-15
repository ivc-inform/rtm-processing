package com.simplesys.js.components.props

import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.Layout.props.ChainMasterDetailProps
import com.simplesys.System.Types.Callback
import com.simplesys.System._
import com.simplesys.js.components.{ConstructorForm, PropertyEditorWindow}
import com.simplesys.option.{ScNone, ScOption}
import ru.simplesys.defs.app.scala.container.Scr_ScenarioJsonStorage_scenarioDataRecord

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.Function0


class ConstructorFormProps extends ChainMasterDetailProps {
    type classHandler <: ConstructorForm

    var getJSONGraph: ScOption[js.Function1[JSUndefined[js.Function1[String, _]], _]] = ScNone
    var recoverGraphFromBase: ScOption[js.Function2[Double, JSUndefined[Callback], _]] = ScNone
    var updateInBase: ScOption[js.Function2[JSUndefined[Scr_ScenarioJsonStorage_scenarioDataRecord], JSUndefined[Callback], _]] = ScNone
    var refresh: ScOption[js.Function0[_]] = ScNone
    var getSaveState: ScOption[Function0[Boolean]] = ScNone
    var codeScenario: ScOption[String] = ScNone
    var captionScenario: ScOption[String] = ScNone
    var propertyEditorRef: ScOption[js.Function0[JSUndefined[PropertyEditorWindow]]] = ScNone
    var historyEditorRef: ScOption[js.Function0[JSUndefined[WindowSS]]] = ScNone
}
