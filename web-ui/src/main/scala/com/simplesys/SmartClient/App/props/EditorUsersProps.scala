package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait NewDSRequestData extends JSObject {
    val active: Boolean
}

class EditorUsersProps extends CommonTreeListGridEditorComponentProps {
    identifier = "58125E1C-252A-01C4-11A1-557FA3222D3F".opt

    captionMenuTree = "Группы пользователей".opt
    captionMenuList = "Пользователи".opt

    newTreeRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewDSRequestData {
                        override val active = true
                    }).opt
                }
            )

    }.toThisFunc.opt

    editWindowPropertiesTree = WindowSS(
        new WindowSSProps {
            width = 285
            height = 285
        }
    ).opt
}
