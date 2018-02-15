package com.simplesys.SmartClient.DataBinding.dataSource

import com.simplesys.System.JSObject
import com.simplesys.System.Types.{URL, VelocityExpression}

import scala.scalajs.js

@js.native
trait Mail extends JSObject {
    val bcc: String
    val cc: String
    val contentType: String
    val encoding: String
    val from: String
    val messageData: VelocityExpression
    val messageTemplate: String
    val multiple: String
    val replyTo: String
    val subject: String
    val templateFile: URL
    val to : String
}


