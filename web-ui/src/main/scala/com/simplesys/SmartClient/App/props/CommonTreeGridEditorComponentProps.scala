package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonTreeGridEditorComponent
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.TreeGridContextMenuProps
import com.simplesys.SmartClient.Grids.props.TreeGridEditorProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionStyle
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

trait CommonTreeGridEditorComponentProps extends TreeGridEditorProps with InitialTrait {

    type classHandler <: CommonTreeGridEditorComponent

    canDragSelectText = false.opt
    height = "100%"
    width = "100%"
    drawAheadRatio = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    dataPageSize = simpleSyS.config.dataPageSize.getOrElse(75).opt
    canEdit = true.opt
    filterOnKeypress = true.opt
    autoFetchData = true.opt
    selectionType = SelectionStyle.single.opt
    folderIcon = Common.iconFolder.opt
    nodeIcon = Common.iconTreeNode.opt
    showOpenIcons = false.opt
    openIconSuffix = "".opt
    dropIconSuffix = "".opt
    closedIconSuffix = "".opt
    selectFirstRecordAfterFetch = false.opt
    saveByCell = true.opt

    var customMenu: ScOption[Seq[MenuSSItem]] = ScNone

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

            val res = initWidget(thiz, thiz.fields, thiz.replacingFields, thiz.editingFields)
            thiz.fields = res._1
            thiz.editingFields = res._2

            thiz.Super("initWidget", arguments)

            val funcMenu = TreeGridContextMenu.create(
                new TreeGridContextMenuProps {
                    owner = thiz.opt
                    customMenu = if (thiz.customMenu.isEmpty) ScNone else thiz.customMenu.get.toSeq.opt
                }
            )

            thiz setFuncMenu funcMenu
            thiz setContextMenu funcMenu
    }.toThisFunc.opt
}
