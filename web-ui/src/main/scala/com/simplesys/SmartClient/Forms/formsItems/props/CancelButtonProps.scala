package com.simplesys.SmartClient.Forms.formsItems.props

import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.option.ScOption._

class CancelButtonProps extends IButtonSSProps {

    import com.simplesys.SmartClient.Forms.formsItems.CancelButton
    import com.simplesys.SmartClient.System.Common

    type classHandler <: CancelButton

    prompt = "Удалить".opt
    icon = Common.cancel.opt
}
