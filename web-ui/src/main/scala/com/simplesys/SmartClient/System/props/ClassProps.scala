package com.simplesys.SmartClient.System.props

import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.JSAny
import com.simplesys.System.Types.ID
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js.ThisFunction1

class ClassProps extends AbstractClassProps {
    type classHandler <: Class

    var addPropertiesOnCreate: ScOption[Boolean] = ScNone
    var identifier: ScOption[ID] = ScNone
    var identifier1: ScOption[ID] = ScNone
    var init: ScOption[ThisFunction1[classHandler, IscArray[JSAny], _]] = ScNone
    var unserialize: ScOption[Boolean] = ScNone
}
