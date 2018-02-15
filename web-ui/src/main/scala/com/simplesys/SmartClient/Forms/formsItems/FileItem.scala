package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.SmartClient.Forms.DynamicForm
import com.simplesys.System.Types.{AutoChild, SCImgURL, void}

import scala.scalajs.js

@js.native
trait FileItem extends CanvasItem {
    val accept: String
    val displayForm: DynamicForm with AutoChild
    val displayItem: StaticTextItem with AutoChild
    val downloadIconSrc: SCImgURL
    val editForm: DynamicForm with AutoChild
    def setMultiple(multiple: Boolean): void
    val showFileInline: Boolean
    val uploadItem: UploadItem with AutoChild
    val viewIconSrc: SCImgURL
}

