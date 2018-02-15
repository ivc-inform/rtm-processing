package com.simplesys.SmartClient.DataBinding.props

import com.simplesys.SmartClient.Layout.props.VLayoutProps
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class DataViewProps extends VLayoutProps with DataBoundComponentProps{
    var dataViewLoaded: ScOption[js.Function0[_]] = ScNone
}
