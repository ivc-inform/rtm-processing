package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.props.CommonTreeGridEditorComponentProps
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.js.components.Compain
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, FormItemsJS, ListGridFiledsJS}

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait NewCompainRequestData extends JSObject {
    val active_cmpgn: Boolean
}

class CompainProps extends CommonTreeGridEditorComponentProps {
    type classHandler <: Compain

    identifier = "C4C555D1-7823-F001-BD54-88A8A2238DCC".opt
    dataSource = DataSourcesJS.scenarios_Scr_Cmpgn_DS.opt
    fields = ListGridFiledsJS.scenarios_Scr_Cmpgn_FLDS.opt
    editingFields = FormItemsJS.scenarios_Scr_Cmpgn_FRMITM.opt

    newRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewCompainRequestData {
                        override val active_cmpgn: Boolean = true
                    }).opt
                }
            )

    }.toThisFunc.opt
}
