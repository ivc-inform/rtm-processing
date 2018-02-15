package com.simplesys.SmartClient.Control.props

import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.{MenuSS, TreeGridContextMenu}
import com.simplesys.SmartClient.DataBinding.DSRequest
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.{ListGridEditor, TreeGridEditor}
import com.simplesys.SmartClient.System.{Common, simpleSyS, _}
import com.simplesys.System.Types.Visibility
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class TreeGridContextMenuProps extends MenuSSProps {
    type classHandler <: TreeGridContextMenu

    var customMenu: ScOption[Seq[MenuSSItem]] = ScNone

    def newRootMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Новый корневой узел".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconNew.opt
            visibility = Visibility.hidden.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.deselectAllRecords()

                    if (owner.treeGrid.newRequestProperties.isDefined)
                        owner.startEditingNewInForm(
                            requestProperties = (owner.treeGrid.newRequestProperties.get) ()
                        )
                    else
                        owner.startEditingNewInForm()

            }.toFunc.opt
        })

    def newMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Новый".ellipsis.opt
            identifier = "new".opt
            icon = Common.iconNew.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    //simpleSyS checkOwner owner

                    val parentIdField = owner.treeGrid.data.parentIdField
                    val idField = owner.treeGrid.data.idField

                    //isc debugTrap(parentIdField, idField)

                    val request: DSRequest = if (owner.treeGrid.newRequestProperties.isDefined) (owner.treeGrid.newRequestProperties.get) () else DSRequest(
                        new DSRequestProps{
                            data = js.Object().opt
                        }
                    )
                    val idValue = owner.getSelectedRecord().asInstanceOf[JSDynamic].selectDynamic(idField)

                    request.data.asInstanceOf[JSDynamic].updateDynamic(parentIdField)(idValue)

                    owner.startEditingNewInForm(
                        requestProperties = request
                    )

            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1
            }.toFunc.opt
        })

    def copyMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Копировать".opt
            identifier = "copy".opt
            icon = Common.copy_icon.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().foreach(record => owner.dataSource.addData(ListGridContextMenuProps.deletePKField(owner.dataSource, isc.deletePrivateProps(record))))
                    owner.fullRefresh()
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[ListGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length > 0
            }.toFunc.opt
        })

    def editMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Изменить".ellipsis.opt
            identifier = "edit".opt
            icon = Common.iconEdit.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.startEditing()
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1
            }.toFunc.opt
        })

    def enableReparentMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Разрешить перемещение групп".ellipsis.opt
            identifier = "enableReparent".opt
            checkIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    //simpleSyS checkOwner owner
                    owner.treeGrid.canReparentNodes.getOrElse(false)

            }.toFunc.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    //isc debugTrap owner.canReparentNodes.getOrElse(false)
                    val x: Boolean = !owner.treeGrid.canReparentNodes.getOrElse(false)
                    //isc debugTrap x
                    owner.treeGrid.canReparentNodes = x
            }.toFunc.opt
        })

    def deleteMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Удалить".opt
            identifier = "remove".opt
            icon = Common.delete_Column.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    isc.ask(simpleSyS.config.confirmDeleting, {
                        (value: Boolean) => if (value) owner.removeSelectedData()
                    })

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    simpleSyS _enableDeleteFromTree item
            }.toFunc.opt
        })

    def refreshMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Обновить".opt
            identifier = "refresh".opt
            icon = Common.iconRefresh.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.fullRefresh()
            }.toFunc.opt
        })

    def openFolderMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Развернуть узел".opt
            identifier = "openFolder".opt
            icon = Common.iconOpenFolder.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    simpleSyS _openFolders menu
                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length > 0
            }.toFunc.opt
        })

    def separatorMenuItem = MenuSSItem(
        new MenuSSItemProps {
            isSeparator = true.opt
        })

    def saveMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Сохранить изменения".opt
            identifier = "saveAll".opt
            icon = Common.iconSave.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.saveAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges() && !owner.hasErrors()
            }.toFunc.opt
        })

    def cancelMenuItem = MenuSSItem(
        new MenuSSItemProps {
            title = "Отменить изменения".opt
            identifier = "discardAll".opt
            icon = Common.cancel.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.discardAllEdits()

                    false
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[TreeGridEditor]
                    simpleSyS checkOwner owner
                    owner.hasChanges()
            }.toFunc.opt
        })

    initWidget = {
        (thiz: classHandler, args: IscArray[JSAny]) =>
            //isc debugTrac(thiz.getClassName(), thiz.getIdentifier())
            val items = Seq(
                newRootMenuItem,
                newMenuItem,
                copyMenuItem,
                editMenuItem,
                enableReparentMenuItem,
                deleteMenuItem,
                refreshMenuItem,
                openFolderMenuItem) ++
              ListGridContextMenuProps.getCustomMenuItems(thiz.customMenu) ++
              Seq(separatorMenuItem,
                  saveMenuItem,
                  cancelMenuItem
              )

            thiz.items = IscArray(items: _*)
            thiz.Super("initWidget", args)
    }.toThisFunc.opt


}
