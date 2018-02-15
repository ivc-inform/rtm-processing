package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Forms.props.DateChooserProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.FormItemComponentType
import com.simplesys.option.ScOption._

class DateTimeItemSSProps extends DateTimeItemProps {
    pickerProperties = DateChooser(
        new DateChooserProps {
            showSecondItem = true.opt
        }
    ).opt

    `type` = FormItemComponentType.DateTimeItemSS
}
