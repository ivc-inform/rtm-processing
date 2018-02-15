package com.simplesys.SmartClient.Layout.props.sectionStack

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.System.Types.{HTMLString, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js

class SectionStackSectionProps extends AbstractClassProps {
    var canCollapse: ScOption[Boolean] = ScNone
    var canDropBefore: ScOption[Boolean] = ScNone
    var canReorder: ScOption[Boolean] = ScNone
    var canTabToHeader: ScOption[Boolean] = ScNone
    var clipTitle: ScOption[Boolean] = ScNone
    var controls: ScOption[Seq[Canvas]] = ScNone
    var expanded: ScOption[Boolean] = ScNone
    var hidden: ScOption[Boolean] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var ID: ScOption[String] = ScNone
    var items: ScOption[Seq[Canvas]] = ScNone
    var name: ScOption[String] = ScNone
    var resizeable: ScOption[Boolean] = ScNone
    var showClippedTitleOnHover: ScOption[Boolean] = ScNone
    var showHeader: ScOption[Boolean] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
