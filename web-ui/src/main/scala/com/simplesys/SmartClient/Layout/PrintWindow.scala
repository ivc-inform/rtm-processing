package com.simplesys.SmartClient.Layout

import com.simplesys.System.Types.void

import scala.scalajs.js

@js.native
trait PrintWindow extends Window {
    val externalStylesheet: String
    val printButtonTitle: String
    def setPrintButtonTitle (printButtonTitle:String):void
}



