package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.System.Types.{FormItemComponentType, HTMLString}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class MultiFileItemProps extends RelationItemProps {
    var editButton: ScOption[StatefulCanvas] = ScNone
    var editButtonPrompt: ScOption[String] = ScNone
    var emptyMessage: ScOption[String] = ScNone
    var pickerAddAnotherFileButtonTitle: ScOption[HTMLString] = ScNone
    var pickerCancelButtonTitle: ScOption[String] = ScNone
    var pickerUploadButtonInitialTitle: ScOption[String] = ScNone
    var pickerUploadButtonTitle: ScOption[String] = ScNone
    var pickerUploadProgressLabel: ScOption[HTMLString] = ScNone
    var removeButton: ScOption[StatefulCanvas] = ScNone
    var removeButtonPrompt: ScOption[String] = ScNone
    `type` = FormItemComponentType.MultiFileItem
}
