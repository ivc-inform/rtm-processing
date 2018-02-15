package com.simplesys.SmartClient.Messaging

import com.simplesys.SmartClient.System.{AbstractClassCompanion, IscArray}
import com.simplesys.System.Types.void
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait MessagingSS extends com.simplesys.SmartClient.System.Class {
}

@js.native
abstract trait AbstractMessagingSSCompanion extends AbstractClassCompanion {
    def subscribe(channels: IscArray[String] | String, callback: js.Function1[MessageJS, _], subscribeCallback: js.Function = js.native, target: JSAny = js.native, event: String = js.native): void = js.native
    def unsubscribe(channels: IscArray[String] | String, unSubscribeCallback: js.Function1[MessageJS, _] = js.native, event: String = js.native): void = js.native
}

@js.native
object MessagingSS extends AbstractMessagingSSCompanion
