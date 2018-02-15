package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject
import com.simplesys.System.Types.LogPriority.LogPriority
import com.simplesys.System.Types.void

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Log extends Class {
}

@js.native
abstract trait AbstractLogCompanion extends AbstractClassCompanion {
    def applyLogPriorities(settings: JSObject): void = js.native
    def clear(): void = js.native
    def clearPriority(category: String, obj: Class | JSObject): void = js.native
    var defaultPriority: LogPriority = js.native
    def getDefaultPriority(obj: Class | JSObject): LogPriority = js.native
    def getLogPriorities(obj: Class | JSObject, overridesOnly: Boolean = js.native): JSObject = js.native
    def getMessages(): IscArray[String] = js.native
    def getPriority(category: String, obj: Class | JSObject): LogPriority = js.native
    def isEnabledFor(category: String, priority: LogPriority): void = js.native
    var messageCount: Int = js.native
    def setDefaultPriority(priority: String, obj: Class | JSObject): void = js.native
    def setPriority(category: String, priority: LogPriority, obj: Class | JSObject): void = js.native
    def show(): void = js.native
    var stackTracePriority: LogPriority = js.native
    def timeMethod(obj: JSObject, methodName: String): void = js.native
    def traceLogMessage(messagePattern: String, prefix: String = js.native): void = js.native
    def traceMethod(obj: JSObject, methodName: String): void = js.native
}

@js.native
object Log extends AbstractLogCompanion
