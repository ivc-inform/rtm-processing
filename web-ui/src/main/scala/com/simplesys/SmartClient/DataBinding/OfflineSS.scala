package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.DataBinding.Callbacks.RPCCallback
import com.simplesys.SmartClient.System.{AbstractClassCompanion, IscArray}
import com.simplesys.System.Types._
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.|


@js.native
abstract trait AbstractOfflineSSCompanion extends AbstractOfflineCompanion {
    def get[T](key: String, defValue: T): T = js.native
    def getBoolean(key: String, defValue: Boolean): Boolean = js.native
    def putBoolean(key: String, value: Boolean): void = js.native
    def getNumber(key: String, defValue: Double): Double = js.native
    def putNumber(key: String, value: Double): void = js.native
    def putArray(key: String, values: IscArray[JSAny]): void = js.native
    def getArray(key: String): IscArray[JSAny] = js.native
    def removeArray(key: String): IscArray[JSAny] = js.native
    def getFromBase(key: String, defValue: JSAny, _callback: js.Function2[String, IscArray[JSAny], _]): JSAny = js.native
    def putToBase(key:String, value: JSAny, callback: RPCCallback):void = js.native
}


@js.native
object OfflineSS extends AbstractOfflineSSCompanion

