package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.System.AbstractClassCompanion
import com.simplesys.SmartClient.System.Class
import com.simplesys.System.Types.void
import com.simplesys.System.{JSAny, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Offline extends Class {
}

@js.native
abstract trait AbstractOfflineCompanion extends AbstractClassCompanion {
    def get[T](key: String): JSUndefined[T] = js.native
    def goOffline(): void = js.native
    def goOnline(): void = js.native
    def isOffline(): Boolean = js.native
    def put[T](key: String, value: T, recycleEntries: Boolean = js.native): void = js.native
    def remove(key: String): void = js.native
    def useNativeOfflineDetection(): void = js.native
}

@js.native
object Offline extends AbstractOfflineCompanion

