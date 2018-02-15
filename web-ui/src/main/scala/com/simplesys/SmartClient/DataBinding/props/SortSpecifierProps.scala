package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.DataBinding.DataBoundComponent
import com.simplesys.System.Types.SortDirection.SortDirection
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js

class SortSpecifierProps extends AbstractClassProps {
    var context: ScOption[DataBoundComponent] = ScNone
    var direction: ScOption[SortDirection] = ScNone
    var normalizer: ScOption[js.Function] = ScNone
    var property: ScOption[String] = ScNone
}
