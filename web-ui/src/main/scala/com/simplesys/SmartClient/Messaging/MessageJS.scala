package com.simplesys.SmartClient.Messaging

import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System._

import scala.scalajs.js

@js.native
trait MessageJS extends JSObject {
    val id: JSUndefined[String]
    val data: JSUndefined[JSObject]
    val channels: IscArray[String]
    val event: JSUndefined[String]
//    override def toString: String = {
//        s"MESSAGE::: nextID: $id data: ${data.toString} channels: ${channels.mkString("[", ",", "]")} event: $event"
//    }
}
