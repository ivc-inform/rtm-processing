package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.props.SortSpecifierProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.{app, appCommon}
import com.simplesys.function._
import com.simplesys.js.components.HistoryList
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS, scenarios_Scr_Graph_Copies_version_NameStrong}
import ru.simplesys.defs.app.scala.container.Scr_Graph_CopiesDataRecord

import scala.scalajs.js

class HistoryListProps extends CommonListGridEditorComponentProps {
    type classHandler <: HistoryList

    var recoverGraphFromHistory: ScOption[js.Function2[Double, JSUndefined[Callback], _]] = ScNone

    initialSort = Seq(
        SortSpecifier(
            new SortSpecifierProps {
                property = scenarios_Scr_Graph_Copies_version_NameStrong.name.opt
                direction = SortDirection.descending.opt
            }
        )
    ).opt
    selectionAppearance = SelectionAppearance.checkbox.opt
    selectionType = SelectionStyle.multiple.opt
    identifier = "C4C555D1-7823-F191-BD54-88A222238DCC".opt
    dataSource = DataSourcesJS.scenarios_Scr_Graph_Copies_DS.opt
    fields = ListGridFiledsJS.scenarios_Scr_Graph_Copies_FLDS.opt
    editingFields = FormItemsJS.scenarios_Scr_Graph_Copies_FRMITM.opt
    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val contextMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thiz.opt
                    customMenu = Seq(
                        MenuSSItem(
                            new MenuSSItemProps {
                                title = "Загрузить".ellipsis.opt
                                identifier = "load".opt
                                icon = appCommon.load.opt
                                click = {
                                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                        val record = thiz.getSelectedRecord().asInstanceOf[Scr_Graph_CopiesDataRecord]
                                        //isc debugTrap (record, thiz.recoverGraphFromHistory _)
                                        record.id.foreach(thiz.recoverGraphFromHistory(_, (() ⇒ isc ok "Загрузка выполнена.").toFunc))
                                }.toFunc.opt
                                enableIf = {
                                    (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                        val owner = item.owner.asInstanceOf[HistoryList]
                                        simpleSyS checkOwner owner
                                        owner.getSelectedRecords().length == 1
                                }.toFunc.opt
                            })).opt
                }
            )
            contextMenu.removeItems(IscArray("new", "copy"))

            thiz setContextMenu contextMenu


    }.toThisFunc.opt
}
