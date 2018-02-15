package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Foundation.props.StatefulCanvasProps
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.System.Types.Cursor.Cursor
import com.simplesys.System.Types.void

import scala.scalajs.js

class ButtonProps extends StatefulCanvasProps {
    type classHandler <: Button

    var action: ScOption[js.ThisFunction0[classHandler, void]] = ScNone
    var disabledIconCursor: ScOption[Cursor] = ScNone
    var hiliteAccessKey: ScOption[Boolean] = ScNone
    var iconAlign: ScOption[String] = ScNone
    var iconClick: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var iconCursor: ScOption[Cursor] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var showDisabledIcon: ScOption[Boolean] = ScNone
    var titleClipped: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var titleHover: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var wrap: ScOption[Boolean] = ScNone
}
