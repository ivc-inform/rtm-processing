package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonTreeListGridEditorComponentProps
import com.simplesys.SmartClient.Forms.formsItems.props.SelectItemProps
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.app.ListGroupEditor
import com.simplesys.js.components.ListEditor
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class ListEditorProps extends CommonTreeListGridEditorComponentProps {
    type classHandler <: ListEditor

    identifier = "C67EC646-0E43-99B3-3398-2A5BE7DE3DF6".opt

    captionMenuTree = "Группы списков".opt
    captionMenuList = "Списки".opt


    dataSourceList = DataSourcesJS.scenarios_Lists_DS.opt
    dataSourceTree = DataSourcesJS.scenarios_ListGroups_DS.opt

    fieldsTree = ListGridFiledsJS.scenarios_ListGroups_FLDS.opt
    editingTreeFields = FormItemsJS.scenarios_ListGroups_FRMITM.opt

    fieldsList = ListGridFiledsJS.scenarios_Lists_FLDS.opt
    editingListFields = FormItemsJS.scenarios_Lists_FRMITM.opt

    selectFirstRecordAfterFetchList = false.opt
    selectFirstRecordAfterFetchTree = false.opt

    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = scenarios_Lists_caption_listGroup_NameStrong.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = ListGroupEditor.create(new ListGroupEditorProps).opt
                }).opt
        },
        new ListGridFieldProps {
            nameStrong = scenarios_Lists_type_list_NameStrong.opt
            editorType = FormItemComponentType.SelectItem
            editorProperties = SelectItem(
                new SelectItemProps {
                    valueMap = IscArray("String").opt
                }).opt
        }
    ).opt
}
