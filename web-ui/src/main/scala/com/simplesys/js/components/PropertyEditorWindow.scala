package com.simplesys.js.components

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Layout.{AbstractWindowSSCompanion, WindowSS}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.JSUndefined

import scala.scalajs.js
import scala.scalajs.js.Function0

@js.native
trait PropertyEditorWindow extends WindowSS {

    import com.simplesys.SmartClient.Tools.EditContextSS

    val canvasEditContext: EditContextSS
    val codeCmpgn: String
    var propertyEditorDestroyRef: Function0[_]
    val components: IscArray[String]
}

@js.native
abstract trait AbstractPropertyEditorWindowCompanion extends AbstractWindowSSCompanion {
}


