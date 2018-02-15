package com.simplesys.components.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps
import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps.{deleteMenuItem, refreshMenuItem}
import com.simplesys.SmartClient.DataBinding.props.SortSpecifierProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.{ListGridEditEvent, SelectionAppearance, SelectionStyle}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.PersistenceJournalViewTest
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class PersistenceJournalViewTestProps extends CommonListGridEditorComponentProps {
    type classHandler <: PersistenceJournalViewTest

    wrapCells = true.opt
    selectionAppearance = SelectionAppearance.checkbox.opt
    showAdvancedFilter = true.opt
    //showFilterEditor = false.opt
    //dataFetchMode = FetchMode.basic.opt
    editEvent = ListGridEditEvent.none.opt
    selectionType = SelectionStyle.multiple.opt
    autoSaveEdits = false.opt
    identifier = "C4C215D1-7823-F191-BD54-00A362238DCC".opt
    replacingFields = Seq(
        new ListGridFieldProps {
            nameStrong = scenarios_PersistenceJournal_Debug_deleted_NameStrong.opt
            canSort = false.opt
        },
        new ListGridFieldProps {
            nameStrong = scenarios_PersistenceJournal_Debug_created_NameStrong.opt
            canSort = false.opt
        },
        new ListGridFieldProps {
            nameStrong = scenarios_PersistenceJournal_tags_NameStrong.opt
            canSort = false.opt
        }
    ).opt
    initialSort = Seq(
        SortSpecifier(
            new SortSpecifierProps {
                property = scenarios_PersistenceJournal_Debug_ordering_NameStrong.name.opt
            }
        )
    ).opt
    dataSource = DataSourcesJS.scenarios_PersistenceJournal_Debug_DS.opt
    fields = ListGridFiledsJS.scenarios_PersistenceJournal_Debug_FLDS.opt
    editingFields = FormItemsJS.scenarios_PersistenceJournal_Debug_FRMITM.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val contextMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    owner = thiz.opt
                    initWidget = {
                        (thiz: classHandler, args: IscArray[JSAny]) =>
                            //isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

                            val items = Seq(deleteMenuItem, refreshMenuItem) ++ ListGridContextMenuProps.otherItems1

                            thiz.items = IscArray(items: _*)
                            thiz.Super("initWidget", args)
                    }.toThisFunc.opt
                }
            )

            thiz setFuncMenu contextMenu
            thiz setContextMenu contextMenu
    }.toThisFunc.opt
}
