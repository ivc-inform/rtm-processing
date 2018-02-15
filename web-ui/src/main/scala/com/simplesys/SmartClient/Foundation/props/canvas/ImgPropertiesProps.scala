package com.simplesys.SmartClient.Foundation.props.canvas

import com.simplesys.System.Types.URL
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

class ImgPropertiesProps extends AbstractClassProps {
    var extraStuff: ScOption[String] = ScNone
    var height: ScOption[Int] = ScNone
    var imgDir: ScOption[URL] = ScNone
    var name: ScOption[String] = ScNone
    var src: ScOption[URL] = ScNone
    var width: ScOption[Int] = ScNone
}
