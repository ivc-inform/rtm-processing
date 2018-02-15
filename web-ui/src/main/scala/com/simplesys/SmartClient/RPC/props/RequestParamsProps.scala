package com.simplesys.SmartClient.RPC.props

import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class RequestParamsProps extends AbstractClassProps {
    var isc_dataFormat: ScOption[String] = ScNone
}
