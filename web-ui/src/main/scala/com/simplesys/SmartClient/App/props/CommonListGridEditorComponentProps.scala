package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonListGridEditorComponent
import com.simplesys.SmartClient.App.props.MenuItemType.MenuItemType
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps}
import com.simplesys.SmartClient.Grids.props.ListGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.TextMatchStyle._
import com.simplesys.System.Types.{FetchMode, SelectionStyle, TextMatchStyle}
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

trait CommonListGridEditorComponentProps extends ListGridEditorProps with InitialTrait {

    type classHandler <: CommonListGridEditorComponent

    var simpleTable: ScOption[Boolean] = false.opt

    canDragSelectText = false.opt
    height = "100%"
    width = "100%"
    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    selectionType = SelectionStyle.single.opt
    selectFirstRecordAfterFetch = false.opt
    saveByCell = true.opt
    fetchDelay = 500.opt
    autoFetchTextMatchStyle = TextMatchStyle.substring.opt

    var customMenu: ScOption[Seq[MenuSSItem]] = ScNone
    var itemsType: ScOption[Seq[MenuItemType]] = Seq(MenuItemType.miCopy, MenuItemType.miDelete, MenuItemType.miEdit, MenuItemType.miRefresh).opt

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

            val res = initWidget(thiz, thiz.fields, thiz.replacingFields, thiz.editingFields)
            thiz.fields = res._1
            thiz.editingFields = res._2

            thiz.Super("initWidget", arguments)

            val _customMenu = if (thiz.customMenu.isEmpty) ScNone else thiz.customMenu.get.toSeq.opt

            val funcMenu = if (thiz.simpleTable.getOrElse(false)) {
                ListGridContextMenu.create(
                    new ListGridContextMenuProps {
                        itemsType = if (thiz.itemsType.isDefined) thiz.itemsType.get.toSeq.opt else ScNone
                        owner = thiz.opt
                        customMenu = _customMenu
                    }
                )
            }
            else {
                ListGridContextMenuWithForm.create(
                    new ListGridContextMenuWithFormProps {
                        itemsType = if (thiz.itemsType.isDefined) thiz.itemsType.get.toSeq.opt else ScNone
                        owner = thiz.opt
                        customMenu = _customMenu
                    }
                )
            }

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
