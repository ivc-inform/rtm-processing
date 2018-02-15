package com.simplesys.SmartClient.Foundation

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait JoinJSCanvas extends Canvas {
    var suffix: String
    var joinJSFunc: js.Function1[String, _] | String
    def setJoinJSFunc(func: js.Function1[String, _]): JoinJSCanvas
}


