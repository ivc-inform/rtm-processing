package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps._
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, MenuSSProps}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{ListGridEditEvent, SelectionAppearance, SelectionStyle}
import com.simplesys.System._
import com.simplesys.appCommon
import com.simplesys.function._
import com.simplesys.js.components.ScenarioTrace
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class ScenarioTraceProps extends CommonListGridEditorComponentProps {
    type classHandler <: ScenarioTrace

    wrapCells = true.opt
    selectionAppearance = SelectionAppearance.checkbox.opt
    showAdvancedFilter = true.opt
    showFilterEditor = false.opt
    editEvent = ListGridEditEvent.none.opt
    selectionType = SelectionStyle.multiple.opt
    autoSaveEdits = false.opt
    identifier = "C4C555D1-7823-F191-BD54-88A362238DCC".opt
    dataSource = DataSourcesJS.scenarios_ScenarioTrace_DS.opt
    fields = ListGridFiledsJS.scenarios_ScenarioTrace_FLDS.opt
    editingFields = FormItemsJS.scenarios_ScenarioTrace_FRMITM.opt
    customMenu = Seq(
        MenuSSItem(new MenuSSItemProps {
            isSeparator = true.opt
        }),
        MenuSSItem(
            new MenuSSItemProps {
                title = "Экспорт".ellipsis.opt
                icon = appCommon.export.opt
                identifier = "exports".opt
                submenu = MenuSS.create(
                    new MenuSSProps {
                        items = Seq(
                            new MenuSSItemProps {
                                name = "exel2007".opt
                                icon = appCommon.exel.opt
                                title = "Формат Exel (.xlsx)".ellipsis.opt
                                click = {
                                    (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                        item.owner.foreach {
                                            owner ⇒
                                                val _owner = owner.asInstanceOf[ScenarioTrace]
                                                _owner.exportData()
                                        }

                                }.toFunc.opt
                            }
                        ).opt
                    }
                ).opt
            })
    ).opt

    initWidget = {
        (thisTop: classHandler, arguments: IscArray[JSAny]) =>

            thisTop.Super("initWidget", arguments)

            val contextMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thisTop.opt
                    initWidget = {
                        (thiz: classHandler, args: IscArray[JSAny]) =>

                            val items = Seq(deleteMenuItem, refreshMenuItem) ++ thisTop.customMenu.getOrElse(IscArray[MenuSSItem]()).map {
                                item ⇒
                                    item setOwner thisTop
                                    item
                            } ++ ListGridContextMenuProps.otherItems1

                            thiz.items = IscArray(items: _*)
                            thiz.Super("initWidget", args)
                    }.toThisFunc.opt
                }
            )

            thisTop setFuncMenu contextMenu
            thisTop setContextMenu contextMenu
    }.toThisFunc.opt
}
