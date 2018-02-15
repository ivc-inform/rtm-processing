package com.simplesys.http

import java.net.URLEncoder

abstract class httpProtocolAbstract

case object httpProtocol extends httpProtocolAbstract {
  override val toString: String = "http://"
}

case object httpsProtocol extends httpProtocolAbstract {
  override val toString: String = "https://"
}

abstract class SenderParamAbs

case class SenderParam(key: String, value: String) extends SenderParamAbs {
  override def toString: String =
    s"${key}=${URLEncoder.encode(value, "UTF-8")}"
}

case class SenderParam0(key: String, value: String) extends SenderParamAbs {
  override def toString: String = s"${key}=${value}"
}

object MessageURI {
  def apply(protocol: httpProtocolAbstract,
            address: String,
            params: Seq[SenderParamAbs]): String =
    s"$protocol$address?${params.mkString("&")}"
}

case class MessageResponse(codeResponse: String,
                           yourID: String,
                           theirsID: String)

object MessageResponse {
  def unapply(string: String): Option[MessageResponse] = {
    val parts = string split ";"
    if (parts.length != 3)
      None
    else
      Some(MessageResponse(parts(0), parts(1), parts(2)))
  }
}
