package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait UploadItem extends TextItem {
    val accept:String
    val multiple:Boolean
    def  setSelectionRange ():void
}


