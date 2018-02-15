package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js._

class UploadItemProps extends TextItemProps {
    var accept: ScOption[String] = ScNone
    var multiple: ScOption[Boolean] = ScNone
    `type` = FormItemComponentType.UploadItem
}
