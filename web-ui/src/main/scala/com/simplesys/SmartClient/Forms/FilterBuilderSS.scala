package com.simplesys.SmartClient.Forms

import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait FilterBuilderSS extends FilterBuilder {
    var clause: JSUndefined[DynamicFormSS]
    def getValidatedValues(): JSUndefined[JSObject]
}

@js.native
abstract trait AbstractFilterBuilderSSCompanion extends AbstractFilterBuilderCompanion {
}


