package com.simplesys.SmartClient.System.date

import com.simplesys.System.JSObject

import scala.scalajs.js

@js.native
object FiscalYear extends JSObject{
  type FiscalYear = FiscalYear.type

  var date: Int = js.native
  var month: Int = js.native
  var year: Int = js.native
  var fiscalYear: Int = js.native
}
