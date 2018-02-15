package com.simplesys.SmartClient.Tools.props

import com.simplesys.SmartClient.Tools.{EditNode, EditProxy}
import com.simplesys.System.{JSDictionaryAny, JSObject}
import com.simplesys.System.Types._

import scala.scalajs.js._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class PaletteNodeProps extends AbstractClassProps {
    var defaults: ScOption[JSDictionaryAny] = ScNone
    var editNodeProperties: ScOption[EditNode] = ScNone
    var editProxyProperties: ScOption[EditProxy] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var idPrefix: ScOption[String] = ScNone
    var liveObject: ScOption[JSObject] = ScNone
    var title: ScOption[String] = ScNone
    var `type`: ScOption[SCClassName] = ScNone
}

