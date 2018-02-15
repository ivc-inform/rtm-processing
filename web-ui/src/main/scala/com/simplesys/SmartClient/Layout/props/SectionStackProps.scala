package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.props.sectionStack.SectionStackSectionProps
import com.simplesys.SmartClient.Layout.sectionStack.SectionStackSection
import com.simplesys.System.Types.LocatorStrategy.LocatorStrategy
import com.simplesys.System.Types.LocatorTypeStrategy.LocatorTypeStrategy
import com.simplesys.System.Types.VisibilityMode.VisibilityMode
import com.simplesys.System.Types.void
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class SectionStackProps extends VLayoutProps {
    var canCollapseAll: ScOption[Boolean] = ScNone
    var canReorderSections: ScOption[Boolean] = ScNone
    var canResizeSections: ScOption[Boolean] = ScNone
    var canTabToHeaders: ScOption[Boolean] = ScNone
    var headerHeight: ScOption[Int] = ScNone
    var itemEndIndent: ScOption[Int] = ScNone
    var itemIndent: ScOption[Int] = ScNone
    var itemStartIndent: ScOption[Int] = ScNone
    var locateSectionsBy: ScOption[LocatorStrategy] = ScNone
    var locateSectionsType: ScOption[LocatorTypeStrategy] = ScNone
    var sectionHeaderClass: ScOption[String] = ScNone
    var sectionHeaderClick: ScOption[js.Function1[Canvas, void]] = ScNone
    var sections: ScOption[Seq[SectionStackSectionProps]] = ScNone
    var showExpandControls: ScOption[Boolean] = ScNone
    var useGlobalSectionIDs: ScOption[Boolean] = ScNone
    var visibilityMode: ScOption[VisibilityMode] = ScNone
}
