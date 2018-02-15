package com.simplesys.SmartClient.Grids.props

import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.System.Types.NavigationMode.NavigationMode
import com.simplesys.System.Types.RecordLayout.RecordLayout
import com.simplesys.System.Types.{HTMLString, CSSStyleName, SCImgURL}
import com.simplesys.System.Types.TableMode.TableMode
import com.simplesys.option.{ScOption, ScNone}

import scala.scalajs.js
import scala.scalajs.js._

class TableViewProps extends GridProps {
    var formatRecord: ScOption[js.Function1[ListGridRecord, HTMLString]] = ScNone
    var imageClick: ScOption[js.Function1[ListGridRecord, HTMLString]] = ScNone
    var navIcon: ScOption[SCImgURL] = ScNone
    var navigationMode: ScOption[NavigationMode] = ScNone
    var recordDataStyle: ScOption[CSSStyleName] = ScNone
    var recordDescriptionStyle: ScOption[CSSStyleName] = ScNone
    var recordInfoStyle: ScOption[CSSStyleName] = ScNone
    var recordLayout: ScOption[RecordLayout] = ScNone
    var recordNavigationClick: ScOption[js.Function1[ListGridRecord, HTMLString]] = ScNone
    var recordNavigationProperty: ScOption[String] = ScNone
    var recordTitleStyle: ScOption[CSSStyleName] = ScNone
    var showIconField: ScOption[Boolean] = ScNone
    var showNavigation: ScOption[Boolean] = ScNone
    var tableMode: ScOption[TableMode] = ScNone
    var wholeRecordNavIcon: ScOption[SCImgURL] = ScNone
}
