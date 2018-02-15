package com.simplesys.SmartClient.Forms.formsItems

import com.simplesys.System.Types.{void, HTMLString}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait LinkItem extends TextItem {
    var linkTitle: HTMLString
    def setLinkTitle(title: HTMLString): void
    var target: String
}


