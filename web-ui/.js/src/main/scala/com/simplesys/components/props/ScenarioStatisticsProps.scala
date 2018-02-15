package com.simplesys.components.props

import com.simplesys.SmartClient.App.props.CommonListGridEditorComponentProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.ListGridEditEvent
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.components.ScenarioStatistics
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

class ScenarioStatisticsProps extends CommonListGridEditorComponentProps {
    type classHandler <: ScenarioStatistics

    wrapCells = true.opt
    //selectionAppearance = SelectionAppearance.checkbox.opt
    //showAdvancedFilter = true.opt
    //showFilterEditor = false.opt
    editEvent = ListGridEditEvent.none.opt
    //selectionType = SelectionStyle.multiple.opt
    autoSaveEdits = false.opt
    identifier = "C4C215D1-7823-F191-BD54-845662238DCC".opt
    dataSource = DataSourcesJS.scenarios_ScenarioStatistics_DS.opt
    fields = ListGridFiledsJS.scenarios_ScenarioStatistics_FLDS.opt
    editingFields = FormItemsJS.scenarios_ScenarioStatistics_FRMITM.opt
    //initialCriteria

    initWidget = {
        (thiz: classHandler, arguments: IscArray[JSAny]) =>
            thiz.Super("initWidget", arguments)
    }.toThisFunc.opt
}
