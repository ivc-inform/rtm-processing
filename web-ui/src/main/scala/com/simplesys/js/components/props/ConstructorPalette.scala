package com.simplesys.js.components.props

import com.simplesys.SmartClient.Foundation.CanvasStatic
import com.simplesys.SmartClient.System.{Common, _}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.SCImgURL

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
abstract class ConstructorPaletteItem extends JSObject {
    val title: String
    val name: String
}

@ScalaJSDefined
class StatesPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.Workflow, 16, 14)} Состояния".ellipsis
    override val name: String = "states"
}

@ScalaJSDefined
class EventsPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.send, 16, 14)} События".ellipsis
    override val name: String = "events"
}

@ScalaJSDefined
class VariablesPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.accounts, 16, 14)} Переменные".ellipsis
    override val name: String = "variables"
}

@ScalaJSDefined
class SubProgramsPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.iconFunctions, 16, 14)} Подпрограммы".ellipsis
    override val name: String = "subprograms"
}


@ScalaJSDefined
class TimersPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.iconStatistic, 16, 14)} Таймеры".ellipsis
    override val name: String = "timers"
}

@ScalaJSDefined
class MiscPanelItem extends ConstructorPaletteItem {
    override val title: SCImgURL = s"${CanvasStatic.imgHTML(Common.exportDB, 16, 14)} Разное".ellipsis
    override val name: String = "miscs"
}


@ScalaJSDefined
object ConstructorPalette extends JSObject {
    val states = new StatesPanelItem
    val events = new EventsPanelItem
    val variables = new VariablesPanelItem
    val subPrograms = new SubProgramsPanelItem
    val timers = new TimersPanelItem
    val miscs = new MiscPanelItem
}
