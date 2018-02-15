package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.App.CommonTreeListGridEditorComponent
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Foundation.props.canvas.ImgPropertiesProps
import com.simplesys.SmartClient.Grids.props.TreeListGridEditorProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FetchMode
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

class CommonTreeListGridEditorComponentProps extends TreeListGridEditorProps with InitialTrait {
    type classHandler <: CommonTreeListGridEditorComponent

    var simpleTableTree: ScOption[Boolean] = false.opt
    var simpleTableList: ScOption[Boolean] = false.opt

    canDragSelectText = false.opt
    autoFetchData = true.opt
    wrapTreeCells = true.opt
    saveByListCell = true.opt
    saveByTreeCell = true.opt
    drawAheadRatioList = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    drawAheadRatioTree = simpleSyS.config.drawAheadRatio.getOrElse(2.0).opt
    canSelectCellsList = false.opt
    showListRecordComponents = false.opt
    folderIconTree = Common.iconFolder.opt

    //dataFetchModeTree = FetchMode.paged.opt
    dataPageSizeTree = simpleSyS.config.dataPageSize.getOrElse(75).opt

    //dataFetchModeList = FetchMode.paged.opt
    dataPageSizeList = simpleSyS.config.dataPageSize.getOrElse(75).opt

    nodeIconTree = Common.iconTreeNode.opt
    canEditList = true.opt
    canEditTree = true.opt
    showListRecordComponentsByCell = false.opt
    wrapListCells = true.opt
    wrapTreeCells = true.opt
    showOpenIconsTree = false.opt
    trackerImageList = ImgProperties(
        new ImgPropertiesProps {
            imgDir = Common.iconTreeNode.opt
            height = 24.opt
            width = 24.opt
        }
    ).opt

    var replacingFieldsList: ScOption[Seq[ListGridFieldProps]] = ScNone
    var replacingFieldsTree: ScOption[Seq[ListGridFieldProps]] = ScNone

    var captionMenuTree: ScOption[String] = ScNone
    var captionMenuList: ScOption[String] = ScNone

    var customMenuTree: ScOption[Seq[MenuSSItem]] = ScNone
    var customMenuList: ScOption[Seq[MenuSSItem]] = ScNone

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            //isc debugTrac (thiz.getClassName(), thiz.getIdentifier())

            val res = initWidget(thiz, thiz.fieldsList, thiz.replacingFieldsList, thiz.editingListFields)
            thiz.fieldsList = res._1
            thiz.editingListFields = res._2

            val res1 = initWidget(thiz, thiz.fieldsTree, thiz.replacingFieldsTree, thiz.editingTreeFields)
            thiz.fieldsTree = res1._1
            thiz.editingTreeFields = res1._2

            thiz.Super("initWidget", arguments)

            val cml = if (thiz.customMenuList.isEmpty) ScNone else thiz.customMenuList.get.toSeq.opt
            val cmt = if (thiz.customMenuTree.isEmpty) ScNone else thiz.customMenuTree.get.toSeq.opt

            //isc debugTrap (cml, cmt)

            thiz setFuncMenu TreeListGridContextMenu.create(
                new TreeListGridContextMenuProps {
                    captionMenuTree = thiz.captionMenuTree.opt
                    captionMenuList = thiz.captionMenuList.opt
                    simpleTableList = thiz.simpleTableList.opt
                    simpleTableTree = thiz.simpleTableTree.opt
                    customMenuList = cml
                    customMenuTree = cmt
                    owner = thiz.opt
                }
            )

            thiz.getViewState()

    }.toThisFunc.opt

}
