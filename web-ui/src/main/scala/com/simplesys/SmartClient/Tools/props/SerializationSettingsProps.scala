package com.simplesys.SmartClient.Tools.props

import scala.scalajs.js._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class SerializationSettingsProps extends AbstractClassProps {
    var indent: ScOption[Boolean] = ScNone
    var outputComponentsIndividually: ScOption[Boolean] = ScNone
    var serverless: ScOption[Boolean] = ScNone
}
