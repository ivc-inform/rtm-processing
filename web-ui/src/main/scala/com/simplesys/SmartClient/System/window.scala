package com.simplesys.SmartClient.System

import com.simplesys.System.Types.{URL, void}
import com.simplesys.System.{JSAny, JSObject}

import scala.scalajs.js

@js.native
object window extends JSObject {
    def setTimeout(function: js.Function, delay: Long, args: JSAny*): Int = js.native
    def clearTimeout(timerId: Int): void = js.native

    def setInterval(function: js.Function, delay: Long, args: JSAny*): Int = js.native
    def clearInterval(timerId: Int): void = js.native

    val location: Location = js.native
}

@js.native
trait Location extends JSObject {
    def assign (url: URL): void
}
