package com.simplesys.js.components.props

import com.simplesys.SmartClient.Layout.props.WindowSSDialogProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.SelectionAppearance
import com.simplesys.app.ListEditor
import com.simplesys.js.components.WindowListEditor
import com.simplesys.option.ScOption._


class WindowListEditorProps extends WindowSSDialogProps {
    type classHandler <: WindowListEditor

    wrapCanvas = ListEditor.create(
        new ListEditorProps {
            selectionAppearanceList = SelectionAppearance.checkbox.opt
            selectionAppearanceTree = SelectionAppearance.checkbox.opt
            autoFetchData = false.opt
        }).opt

    headerIconPath = Common.accounts.opt
    title = "Списки".ellipsis.opt
    isModal = true.opt
}
