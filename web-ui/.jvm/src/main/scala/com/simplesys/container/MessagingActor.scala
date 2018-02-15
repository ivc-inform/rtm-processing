package com.simplesys.container

import com.simplesys.annotation.SseTransfer
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.actor.BaseMessagingActor
import com.simplesys.servlet.http.sse.{SseServletRequest, SseServletResponse}

@SseTransfer(urlPattern = "/Message/Subscribe")
class MessagingActor(override val request: SseServletRequest, override val response: SseServletResponse, override val servletContext: ServletContext) extends BaseMessagingActor(request, response, servletContext)
