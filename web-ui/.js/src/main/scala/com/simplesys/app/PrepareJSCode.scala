package com.simplesys.app


import com.simplesys.SmartClient.App.StaticJSCode
import com.simplesys.SmartClient.System.{WindowSSDialog, _}
import com.simplesys.app.App._

import scala.scalajs.js.annotation.JSExportTopLevel

object PrepareJSCode extends StaticJSCode {

    @JSExportTopLevel("CreateAppJS")
    override def createJS(): Unit = {
        isc.defineClass(StartState.getClass.getSimpleName, DrawOval.getClass.getSimpleName)
        isc.defineClass(StopState.getClass.getSimpleName, DrawOval.getClass.getSimpleName)
        isc.defineClass(StateUnified.getClass.getSimpleName, DrawOval.getClass.getSimpleName)
        isc.defineClass(TimerUnified.getClass.getSimpleName, DrawRect.getClass.getSimpleName)

        isc.defineClass(IncomingMessage.getClass.getSimpleName, DrawLinePathSS.getClass.getSimpleName)
        isc.defineClass(TimerFired.getClass.getSimpleName, DrawLinePathSS.getClass.getSimpleName)
        isc.defineClass(Transition.getClass.getSimpleName, DrawLinePathSS.getClass.getSimpleName)
        isc.defineClass(ListAnything.getClass.getSimpleName, DrawRect.getClass.getSimpleName)
        isc.defineClass(Group.getClass.getSimpleName, DrawRect.getClass.getSimpleName)
        isc.defineClass(SubProgram.getClass.getSimpleName, DrawRect.getClass.getSimpleName)
        isc.defineClass(Variable.getClass.getSimpleName, DrawRect.getClass.getSimpleName)
        isc.defineClass(SendMessage.getClass.getSimpleName, Variable.getClass.getSimpleName)
        isc.defineClass(CalcGroup.getClass.getSimpleName, Variable.getClass.getSimpleName)
        isc.defineClass(CounterVariable.getClass.getSimpleName, Variable.getClass.getSimpleName)

        isc.defineClass(ScenarioTests.getClass.getSimpleName, CommonTreeListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ScenarioTestsGroups.getClass.getSimpleName, CommonTreeGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ScenarioCompain.getClass.getSimpleName, CommonTreeListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(Compain.getClass.getSimpleName, CommonTreeGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ScenarioTrace.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ScenarioTraceTest.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(HistoryList.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(PersistenceJournalView.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(PersistenceJournalViewTest.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ComData.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ScenarioStatistics.getClass.getSimpleName, CommonListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ConstructorForm.getClass.getSimpleName, ChainMasterDetail.getClass.getSimpleName)
        isc.defineClass(PropertyEditorDynamicForm.getClass.getSimpleName, DynamicFormSS.getClass.getSimpleName)
        isc.defineClass(PropertyEditorWindow.getClass.getSimpleName, WindowSS.getClass.getSimpleName)
        isc.defineClass(WindowListEditor.getClass.getSimpleName, WindowSSDialog.getClass.getSimpleName)
        isc.defineClass(ListEditor.getClass.getSimpleName, CommonTreeListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(ListGroupEditor.getClass.getSimpleName, CommonTreeGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(TimerU.getClass.getSimpleName, "Class")
        isc.defineClass(FormItemList.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(SubProgramItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(VariableItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(SendMessageItem.getClass.getSimpleName, VariableItem.getClass.getSimpleName)
        isc.defineClass(CounterAccumItem.getClass.getSimpleName, VariableItem.getClass.getSimpleName)
        isc.defineClass(CalcGroupItem.getClass.getSimpleName, VariableItem.getClass.getSimpleName)
        isc.defineClass(TimerItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(GroupItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
    }
}
