package com.simplesys.SmartClient.RPC

import com.simplesys.System.JSUndefined
import com.simplesys.System.Types._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait RPCManagerSS extends RPCManager {
}

@js.native
abstract trait AbstractRPCManagerSSCompanion extends AbstractRPCManagerCompanion {
    def loginRequired (reload: Boolean, loginSuccessProcedure:js.Function2[Boolean, String, _]): void  = js.native
    def loginRequired (loginSuccessProcedure:js.Function3[Boolean, JSUndefined[String], JSUndefined[String], _]): void  = js.native
    def logoutRequired (): void  = js.native
}

@js.native
object RPCManagerSS extends AbstractRPCManagerSSCompanion
