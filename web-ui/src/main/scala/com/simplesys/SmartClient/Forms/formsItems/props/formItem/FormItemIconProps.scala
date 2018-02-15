package com.simplesys.SmartClient.Forms.formsItems.props.formItem

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.PickListItemIconPlacement.PickListItemIconPlacement
import com.simplesys.System.Types.{CSSStyleName, HTMLString, SCImgURL, void}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js

class FormItemIconProps extends AbstractClassProps {
    var baseStyle: ScOption[CSSStyleName] = ScNone
    var click: ScOption[js.Function3[DynamicForm, FormItem, FormItemIcon, void]] = ScNone
    var disabled: ScOption[Boolean] = ScNone
    var disableOnReadOnly: ScOption[Boolean] = ScNone
    var height: ScOption[Int] = ScNone
    var hspace: ScOption[Int] = ScNone
    var iconPlacement: ScOption[PickListItemIconPlacement] = ScNone
    var inline: ScOption[Boolean] = ScNone
    var inlineIconAlign: ScOption[Alignment] = ScNone
    var keyPress: ScOption[js.Function5[String, Char, DynamicForm, FormItem, FormItemIcon, _]] = ScNone
    var name: ScOption[String] = ScNone
    var neverDisable: ScOption[Boolean] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var showFocused: ScOption[Boolean] = ScNone
    var showFocusedWithItem: ScOption[Boolean] = ScNone
    var showIf: ScOption[js.Function2[DynamicForm, FormItem, Boolean]] = ScNone
    var showOver: ScOption[Boolean] = ScNone
    var showRTL: ScOption[Boolean] = ScNone
    var src: ScOption[SCImgURL] = ScNone
    var tabIndex: ScOption[Int] = ScNone
    var width: ScOption[Int] = ScNone
}
