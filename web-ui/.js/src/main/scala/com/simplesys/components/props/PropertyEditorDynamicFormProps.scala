package com.simplesys.components.props

import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.EditNode
import com.simplesys.System._
import com.simplesys.components.PropertyEditorDynamicForm
import com.simplesys.option.{ScNone, ScOption}

class PropertyEditorDynamicFormProps extends DynamicFormSSProps {

    import com.simplesys.SmartClient.Drawing.DrawItem

    import scala.scalajs.js
    import scala.scalajs.js.{ThisFunction0, ThisFunction2}

    type classHandler <: PropertyEditorDynamicForm

    var getSelectedNodes: ScOption[ThisFunction0[classHandler, IscArray[EditNode]]] = ScNone
    var selectedEditNodesUpdated: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var getDrawItem :ScOption[js.Function0[JSUndefined[DrawItem]]] = ScNone
}
