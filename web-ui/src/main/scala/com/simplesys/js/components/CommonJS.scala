package com.simplesys.js.components

import com.simplesys.SmartClient.Foundation.CanvasStatic
import com.simplesys.System.JSObject
import com.simplesys.isc.system.typesDyn._

import scala.scalajs.js

object CommonJS {

    implicit class DynOpt[C <: JSObject, R](in: C) {
        def select(str: String): R = in.asInstanceOf[js.Dynamic].selectDynamic(str).asInstanceOf[R]
    }

    def getHTMLTille(text: String, icon: SCImgURL): String = s"""<span>${CanvasStatic.imgHTML(icon)} $text</span>"""
}
