package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.TreeListGridContextMenu
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{ListGridContextMenuProps, ListGridContextMenuWithFormProps, MenuSSProps, TreeGridContextMenuProps}
import com.simplesys.SmartClient.Grids.TreeListGridEditor
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.{ScNone, ScOption}
import com.simplesys.option.ScOption._

class TreeListGridContextMenuProps extends MenuSSProps {
    type classHandler <: TreeListGridContextMenu

    var simpleTableTree: ScOption[Boolean] = false.opt
    var simpleTableList: ScOption[Boolean] = false.opt

    var captionMenuTree: ScOption[String] = ScNone
    var captionMenuList: ScOption[String] = ScNone

    var customMenuTree: ScOption[Seq[MenuSSItem]] = ScNone
    var customMenuList: ScOption[Seq[MenuSSItem]] = ScNone

    var customSubMenuItem: ScOption[MenuSSItem] = ScNone

    initWidget = {
        (thiz: TreeListGridContextMenu, arguments: IscArray[JSAny]) =>
            //isc debugTrac(thiz.getClassName(), thiz.getIdentifier())

            thiz.Super("initWidget", arguments)

            val topOwner = thiz.owner.asInstanceOf[TreeListGridEditor]

            def listGridEditorMenu = ListGridContextMenu.create(
                new ListGridContextMenuProps {
                    customMenu = if (thiz.customMenuList.isEmpty) ScNone else thiz.customMenuList.get.toSeq.opt
                    owner = topOwner.listGrid.opt
                }
            )

            def listGridEditorMenuWithForm = ListGridContextMenuWithForm.create(
                new ListGridContextMenuWithFormProps {
                    customMenu = if (thiz.customMenuList.isEmpty) ScNone else thiz.customMenuList.get.toSeq.opt
                    owner = topOwner.listGrid.opt
                }
            )

            //isc debugTrap(listGridEditorMenu, listGridEditorMenuWithForm)

            if (thiz.simpleTableList)
                topOwner setContextMenuListGridEditor listGridEditorMenu
            else
                topOwner setContextMenuListGridEditor listGridEditorMenuWithForm

            val treeGridEditorMenu = TreeGridContextMenu.create(
                new TreeGridContextMenuProps {
                    customMenu = if (thiz.customMenuTree.isEmpty) ScNone else thiz.customMenuTree.get.toSeq.opt
                    owner = topOwner.treeGrid.opt
                }
            )

            topOwner setContextMenuTreeGridEditor treeGridEditorMenu

            thiz.addItems(
                IscArray(
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = (if (thiz.simpleTableList) listGridEditorMenu else listGridEditorMenuWithForm).opt
                            title = thiz.captionMenuList.ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    ),
                    MenuSSItem(
                        new MenuSSItemProps {
                            submenu = treeGridEditorMenu.opt
                            title = thiz.captionMenuTree.ellipsis.opt
                            icon = Common.ellipsis.opt
                        }
                    )
                )
            )

            thiz.customSubMenuItem.foreach(thiz addItem _)

    }.toThisFunc.opt
}
