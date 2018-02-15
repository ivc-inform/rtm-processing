package com.simplesys.SmartClient.Layout.props.portalLayout

import com.simplesys.SmartClient.Control.Dialog
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.option.{IntString, ScOption, ScNone}

import scala.scalajs.js._

class PortletProps extends WindowProps {
    var closeConfirmationDialogProperties: ScOption[Dialog] = ScNone
    var closeConfirmationMessage: ScOption[String] = ScNone
    var destroyOnClose: ScOption[Boolean] = ScNone
    var rowHeight: ScOption[IntString[Int, String]] = ScNone
    var showCloseConfirmationMessage: ScOption[Boolean] = ScNone
}
