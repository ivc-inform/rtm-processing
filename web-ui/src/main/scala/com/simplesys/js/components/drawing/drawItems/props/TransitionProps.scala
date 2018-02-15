package com.simplesys.js.components.drawing.drawItems.props

import com.simplesys.SmartClient.DataBinding.props.DataSourceProps
import com.simplesys.SmartClient.Drawing.drawItem.props.DrawLinePathSSProps
import com.simplesys.SmartClient.System._
import com.simplesys.js.components.drawing.drawItems.Transition
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

class TransitionProps extends DrawLinePathSSProps {
    type classHandler <: Transition

    fieldDataSource = DataSource.create(
            new DataSourceProps {
                clientOnly = true.opt
                unserialize = true.opt
                cacheData1 = Seq().opt
            }
        ).opt

    override val `type`: ScOption[String] = "Transition".opt
}
