package com.simplesys.components.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.SmartClient.Tools.EditContextSS
import com.simplesys.components.PropertyEditorWindow
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class PropertyEditorWindowProps extends WindowSSProps {

    type classHandler <: PropertyEditorWindow

    var canvasEditContext: ScOption[EditContextSS] = ScNone
    var components: ScOption[Seq[String]] = ScNone

    headerIconPath = Common.properties.opt
    height = 400
    width = 300
    canDragResize = true.opt
    canDragReposition = true.opt
    keepInParentRect = true.opt
    autoCenter = false.opt
    autoSize = false.opt
    showMinimizeButton = false.opt
    showMaximizeButton = false.opt

    var propertyEditorDestroyRef: ScOption[js.Function0[_]] = ScNone
    var codeCmpgn: ScOption[String] = ScNone

    onDestroy = {
        (thiz: classHandler, typeButton: JSUndefined[Canvas]) ⇒
            thiz.propertyEditorDestroyRef()
    }.toThisFunc.opt

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) ⇒
            thiz.Super("initWidget", args)

            val editor = new PropertyEditorBase {
                override protected val canvasEditContext = thiz.canvasEditContext
                override protected val identifier: String = s"${thiz.getIdentifier()}PropertyEditor"
                override protected val codeCmpgn: String = thiz.codeCmpgn
                override protected val components: IscArray[String] = thiz.components
            }

            thiz setTitle s"Свойства (${editor.getSelectedTypeDrawItemTitle}: ${editor.getSelectedTypeDrawItem})".ellipsis
            thiz addItem editor.tabSet


    }.toThisFunc.opt
}
