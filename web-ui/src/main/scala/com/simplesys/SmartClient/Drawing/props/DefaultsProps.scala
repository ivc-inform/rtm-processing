package com.simplesys.SmartClient.Drawing.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.SmartClient.Drawing.gradient.SimpleGradient
import com.simplesys.SmartClient.Drawing.{DrawItem, Shadow}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.ArrowStyle._
import com.simplesys.System.Types.ConnectorStyle._
import com.simplesys.System.Types.LinePattern._
import com.simplesys.System.Types.{ArrowStyle ⇒ _, LinePattern ⇒ _, _}
import com.simplesys.option.ScOption._
import com.simplesys.option.{IntString, ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js.{ThisFunction0, ThisFunction2}

class DrawItemDefaultsProps extends AbstractClassProps {
    var canDrop: ScOption[Boolean] = false.opt
    var onDragStop: ScOption[ThisFunction2[DrawItem, Int, Int, _]] = ScNone
    var resized: ScOption[ThisFunction0[DrawItem, _]] = ScNone
    var title: ScOption[String] = ScNone
    var keepInParentRect: ScOption[Boolean] = ScNone
    var lineWidth: ScOption[Int] = ScNone
    var titleLabelProperties: ScOption[DrawLabel] = ScNone
    var contextMenu: ScOption[MenuSS] = ScNone
    var linePattern: ScOption[LinePattern] = ScNone
    var lineColor: ScOption[CSSColor] = ScNone
    var enable4Connect: ScOption[Boolean] = false.opt
    //Возможность приклеивания к другому
    var enable2Glue: ScOption[Boolean] = false.opt
    //Возможность приклеивания другого
    var enable4Glue: ScOption[Boolean] = false.opt
}

class DrawGroupDefaultsProps extends DrawItemDefaultsProps {
    var top: ScOption[Double] = ScNone
    var left: ScOption[Double] = ScNone
    var width: ScOption[Double] = ScNone
    var height: ScOption[Double] = ScNone
}

class DrawRectDefaultsProps extends DrawItemDefaultsProps {
    var top: ScOption[Double] = ScNone
    var left: ScOption[Double] = ScNone
    var width: ScOption[Double] = ScNone
    var height: ScOption[Double] = ScNone
    var rounding: ScOption[Double] = ScNone
    var fillGradient: ScOption[SimpleGradient] = ScNone
    var shadow: ScOption[Shadow] = ScNone
}

class DrawLineDefaultsProps extends DrawItemDefaultsProps {
    var startPoint: ScOption[Point] = ScNone
    var endPoint: ScOption[Point] = ScNone
    var endArrow: ScOption[ArrowStyle] = ScNone
    var startArrow: ScOption[ArrowStyle] = ScNone
}

class DrawCurveDefaultsProps extends DrawItemDefaultsProps {
    var endPoint: ScOption[Point] = ScNone
    var startPoint: ScOption[Point] = ScNone
    var endArrow: ScOption[ArrowStyle] = ScNone
    var startArrow: ScOption[ArrowStyle] = ScNone
    var controlPoint1: ScOption[Point] = ScNone
    var controlPoint2: ScOption[Point] = ScNone
}

class DrawLinePathDefaultsProps extends DrawItemDefaultsProps {
    var endPoint: ScOption[Point] = ScNone
    var startPoint: ScOption[Point] = ScNone
    var endArrow: ScOption[ArrowStyle] = ScNone
    var startArrow: ScOption[ArrowStyle] = ScNone
    var controlPoint1: ScOption[Point] = ScNone
    var controlPoint2: ScOption[Point] = ScNone
}

class DrawPathDefaultsProps extends DrawItemDefaultsProps {
    var endPoint: ScOption[Point] = ScNone
    var startPoint: ScOption[Point] = ScNone
    var endArrow: ScOption[ArrowStyle] = ScNone
    var startArrow: ScOption[ArrowStyle] = ScNone
    var controlPoint1: ScOption[Point] = ScNone
    var controlPoint2: ScOption[Point] = ScNone
    var connectorStyle: ScOption[ConnectorStyle] = ScNone
    //var titleRotationMode: ScOption[TitleRotationMode] = TitleRotationMode.withLineAlwaysUp.opt
    var shadow: ScOption[Shadow] = ScNone
}

class DrawOvalDefaultsProps extends DrawItemDefaultsProps {
    var top: ScOption[Double] = ScNone
    var left: ScOption[Double] = ScNone
    var width: ScOption[Double] = ScNone
    var height: ScOption[Double] = ScNone
    var fillGradient: ScOption[SimpleGradient] = ScNone
    var shadow: ScOption[Shadow] = ScNone
}

class DrawDiamondDefaultsProps extends DrawItemDefaultsProps {
    var top: ScOption[Double] = ScNone
    var left: ScOption[Double] = ScNone
    var width: ScOption[Double] = ScNone
    var height: ScOption[Double] = ScNone
    var fillGradient: ScOption[SimpleGradient] = ScNone
    var shadow: ScOption[Shadow] = ScNone
}

class DrawTriangleDefaultsProps extends DrawItemDefaultsProps {
    var points: ScOption[IscArray[Point]] = ScNone
    var fillGradient: ScOption[SimpleGradient] = ScNone
    var shadow: ScOption[Shadow] = ScNone
}

class DrawLabelDefaultsProps extends DrawItemDefaultsProps {
    var top: ScOption[Double] = ScNone
    var left: ScOption[Double] = ScNone
    var contents: ScOption[String] = ScNone
    var alignment: ScOption[Alignment] = ScNone
    var fillGradient: ScOption[SimpleGradient] = ScNone
    var shadow: ScOption[Shadow] = ScNone
}

class DrawPaneDefaultsProps extends AbstractClassProps {
    var canFocus: ScOption[Boolean] = ScNone
    var width: ScOption[IntString[Int, String]] = ScNone
    var height: ScOption[IntString[Int, String]] = ScNone
    var gradients: ScOption[Seq[SimpleGradient]] = ScNone
}
