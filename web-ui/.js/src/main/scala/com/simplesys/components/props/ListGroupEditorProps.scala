package com.simplesys.components.props

import com.simplesys.SmartClient.App.props.CommonTreeGridEditorComponentProps
import com.simplesys.components.ListGroupEditor
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class ListGroupEditorProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: ListGroupEditor

    identifier = "C4C555D1-7823-F001-BD54-8456A2238DCC".opt

    dataSource = DataSourcesJS.scenarios_ListGroups_DS.opt
    fields = ListGridFiledsJS.scenarios_ListGroups_FLDS.opt
    editingFields = FormItemsJS.scenarios_ListGroups_FRMITM.opt
}
