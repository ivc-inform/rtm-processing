package com.simplesys.SmartClient.DataBinding.props.dataSource

import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class WildRecordColumnProps extends AbstractClassProps {
    var name: ScOption[String] = ScNone
    var value: ScOption[Any] = ScNone
}
