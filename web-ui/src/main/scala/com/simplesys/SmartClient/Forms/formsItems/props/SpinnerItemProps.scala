package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.formsItems.{SpinnerItem, TextItem}
import com.simplesys.SmartClient.Forms.formsItems.formItem.FormItemIcon
import com.simplesys.System.Types.{FormItemBaseStyle, FormItemComponentType}
import com.simplesys.System.{JSAny, JSDictionaryAny}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class SpinnerItemProps extends TextItemProps {
    type classHandler <: SpinnerItem

    var decreaseIcon: ScOption[FormItemIcon] = ScNone
    var decreaseIconProperties: ScOption[JSDictionaryAny] = ScNone
    var getNextValue: ScOption[js.Function2[Int, Int, Int]] = ScNone
    var getPreviousValue: ScOption[js.Function2[Int, Int, Int]] = ScNone
    var increaseIcon: ScOption[FormItemIcon] = ScNone
    var increaseIconProperties: ScOption[JSDictionaryAny] = ScNone
    var max: ScOption[Double] = ScNone
    var min: ScOption[Double] = ScNone
    var step: ScOption[Double] = ScNone
    var unstackedDecreaseIcon: ScOption[FormItemIcon] = ScNone
    var unstackedIncreaseIcon: ScOption[FormItemIcon] = ScNone
    var unstackedPrintTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var unstackedReadOnlyTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var unstackedTextBoxStyle: ScOption[FormItemBaseStyle] = ScNone
    var writeStackedIcons: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.SpinnerItem
}
