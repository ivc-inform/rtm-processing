package com.simplesys.js.components

import com.simplesys.SmartClient.Forms.{AbstractDynamicFormSSCompanion, DynamicFormSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.EditNode
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait PropertyEditorDynamicForm extends DynamicFormSS {

    import com.simplesys.SmartClient.Drawing.DrawItem

    def selectedEditNodesUpdated(): void
    def getSelectedNodes(): IscArray[EditNode]
    def getDrawItem(): JSUndefined[DrawItem]
}

@js.native
abstract trait AbstractPropertyEditorDynamicFormCompanion extends AbstractDynamicFormSSCompanion {
}


