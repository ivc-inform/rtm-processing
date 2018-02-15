package com.simplesys.SmartClient.Foundation.props

import com.simplesys.option.{Function1_String, ScNone, ScOption}

import scala.scalajs.js

class JoinJSCanvasProps extends CanvasProps {
    var suffix: ScOption[String] = ScNone
    var joinJSFunc: ScOption[Function1_String[js.ThisFunction1[_, String, _], String]] = ScNone
}
