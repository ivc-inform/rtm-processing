package com.simplesys.SmartClient.Grids.props.listGrid

import com.simplesys.SmartClient.DataBinding.AdvancedCriteria
import com.simplesys.System.Types.{Criteria, HTMLString, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.props.AbstractClassProps

import scala.scalajs.js._

class HiliteProps extends AbstractClassProps {
    var backgroundColor: ScOption[String] = ScNone
    var canEdit: ScOption[Boolean] = ScNone
    var criteria: ScOption[Criteria | AdvancedCriteria] = ScNone
    var cssText: ScOption[String] = ScNone
    var disabled: ScOption[Boolean] = ScNone
    var fieldName: ScOption[String] = ScNone
    var htmlAfter: ScOption[HTMLString] = ScNone
    var htmlBefore: ScOption[HTMLString] = ScNone
    var icon: ScOption[SCImgURL] = ScNone
    var id: ScOption[String] = ScNone
    var replacementValue: ScOption[HTMLString] = ScNone
    var textColor: ScOption[String] = ScNone
    var title: ScOption[String] = ScNone
}
