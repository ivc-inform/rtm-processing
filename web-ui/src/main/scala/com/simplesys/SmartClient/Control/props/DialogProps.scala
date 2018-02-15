package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Foundation.{Img, Label}
import com.simplesys.SmartClient.Layout.props.WindowProps
import com.simplesys.SmartClient.Layout.{Layout, Toolbar}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{CSSStyleName, HTMLString, SCImgURL, void}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class DialogProps extends WindowProps {
    var askIcon: ScOption[SCImgURL] = ScNone
    var autoFocus: ScOption[Boolean] = ScNone
    var buttonClick: ScOption[js.Function2[Button, Int, _]] = ScNone
    var buttons: ScOption[IscArray[Button]] = ScNone
    var cancelClick: ScOption[js.Function1[void, _]] = ScNone
    var confirmIcon: ScOption[SCImgURL] = ScNone
    var doneClick: ScOption[js.Function1[void, _]] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var iconSize: ScOption[Int] = ScNone
    var message: ScOption[HTMLString] = ScNone
    var messageIcon: ScOption[Img] = ScNone
    var messageLabel: ScOption[Label] = ScNone
    var messageStack: ScOption[Layout] = ScNone
    var messageStyle: ScOption[CSSStyleName] = ScNone
    var noClick: ScOption[js.Function1[void, _]] = ScNone
    var saveData: ScOption[js.Function1[void, _]] = ScNone
    var sayIcon: ScOption[SCImgURL] = ScNone
    var showToolbar: ScOption[Boolean] = ScNone
    var toolbar: ScOption[Toolbar] = ScNone
    var toolbarButtons: ScOption[IscArray[Button]] = ScNone
    var warnIcon: ScOption[SCImgURL] = ScNone
    var yesClick: ScOption[js.Function1[void, _]] = ScNone
}
