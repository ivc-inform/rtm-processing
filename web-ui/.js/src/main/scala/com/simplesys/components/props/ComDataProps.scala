package com.simplesys.components.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.Control.props.ListGridContextMenuProps
import com.simplesys.SmartClient.System.{IscArray, ListGridContextMenu}
import com.simplesys.System.Types.{ListGridEditEvent, SelectionStyle}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.ComData
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class ComDataProps extends CommonListGridEditorComponentProps {
    type classHandler <: ComData

    wrapCells = true.opt
    //selectionAppearance = SelectionAppearance.checkbox.opt
    showAdvancedFilter = true.opt
    //showFilterEditor = false.opt
    //dataFetchMode = FetchMode.basic.opt
    editEvent = ListGridEditEvent.none.opt
    selectionType = SelectionStyle.multiple.opt
    autoSaveEdits = false.opt
    identifier = "C15515D1-7823-F191-BD54-88A362238DCC".opt

    dataSource = DataSourcesJS.data_COM_Data_DM_DS.opt
    fields = ListGridFiledsJS.data_COM_Data_DM_FLDS.opt
    editingFields = FormItemsJS.data_COM_Data_DM_FRMITM.opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>

            thiz.Super("initWidget", arguments)

            val contextMenu = ListGridContextMenu.create(new ListGridContextMenuProps {
                owner = thiz.opt
            })

            thiz setFuncMenu contextMenu
            thiz setContextMenu contextMenu

    }.toThisFunc.opt
}
