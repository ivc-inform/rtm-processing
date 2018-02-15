package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.DataBinding.DataSource
import com.simplesys.SmartClient.Foundation.StatefulCanvas
import com.simplesys.System.Types.{SCClassName, HTMLString, AutoChild}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait MultiFileItem extends RelationItem {
    val dataSource:DataSource | String
    val editButton:StatefulCanvas with AutoChild
    val editButtonPrompt:String
    val emptyMessage:String
    val pickerAddAnotherFileButtonTitle:HTMLString
    val pickerCancelButtonTitle:String
    val pickerUploadButtonInitialTitle:String
    val pickerUploadButtonTitle:String
    val pickerUploadProgressLabel:HTMLString
    val removeButton:StatefulCanvas with AutoChild
    val removeButtonPrompt:String
}


