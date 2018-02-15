package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.option.{ScOption, ScNone}
import com.simplesys.props.AbstractClassProps
import com.simplesys.System.{JSDictionaryAny, JSDictionary, JSAny}

import scala.scalajs.js
import scala.scalajs.js._

class UserFormulaProps extends AbstractClassProps {
    var formulaVars: ScOption[JSDictionaryAny] = ScNone
    var text: ScOption[String] = ScNone
}
