package com.simplesys.container

import com.simplesys.annotation.RSTransfer
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.actor.BaseLoaderSchemas
import com.simplesys.servlet.http.{HttpServletRequest, HttpServletResponse}

@RSTransfer(urlPattern = "/isomorphic/LoadSchemas")
class LoaderSchemas(override val request: HttpServletRequest, override val response: HttpServletResponse, override val servletContext: ServletContext) extends BaseLoaderSchemas(request, response, servletContext)
