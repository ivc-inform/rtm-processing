package com.simplesys.SmartClient.Layout.props

import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js._

class LoginWindowProps extends WindowSSProps {
    var loginSuccessProcedure: ScOption[Function1[Boolean, _]] = ScNone
}
