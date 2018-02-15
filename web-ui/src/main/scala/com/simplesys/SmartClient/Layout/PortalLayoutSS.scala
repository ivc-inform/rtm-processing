package com.simplesys.SmartClient.Layout

import com.simplesys.System.Types.{void, ID}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait PortalLayoutSS extends PortalLayout {
    def existPortlet(identifier: ID, maxQty: Int = js.native) : void
}

