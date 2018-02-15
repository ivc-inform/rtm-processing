package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.props.{CommonTreeGridEditorComponentProps, NewDSRequestData}
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.function._
import com.simplesys.js.components.ScenarioTestsGroups
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

class ScenarioTestsGroupsProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: ScenarioTestsGroups

    identifier = "C4C555D1-7823-F191-BD54-88A8A2238DCC".opt
    dataSource = DataSourcesJS.scenarios_TestGroup_DS.opt
    fields = ListGridFiledsJS.scenarios_TestGroup_FLDS.opt
    editingFields = FormItemsJS.scenarios_TestGroup_FRMITM.opt

    newRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt

    editWindowProperties = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
