package com.simplesys.app

import com.simplesys.SmartClient.System.{SCApply, SCApply4Object, _}
import com.simplesys.components._
import com.simplesys.components.drawing.drawItems._
import com.simplesys.components.drawing.drawItems.props._
import com.simplesys.components.formItems._
import com.simplesys.components.formItems.props._
import com.simplesys.components.props._

//import com.simplesys.macros.PropsToDictionary
import com.simplesys.macros.PropsToDictionary

object App {
    import com.simplesys.components.formItems.TimerItem

        object TimerItem extends SCApply4Object[TimerItem, TimerItemProps]
        object GroupItem extends SCApply4Object[GroupItem, GroupItemProps]

        object StartState extends SCApply[StartState, StartStateProps]
        object StopState extends SCApply[StopState, StopStateProps]
        object StateUnified extends SCApply[StateUnified, StateUnifiedProps]
        object TimerUnified extends SCApply[TimerUnified, TimerUnifiedProps]
        object IncomingMessage extends SCApply[IncomingMessage, IncomingMessageProps]
        object TimerFired extends SCApply[TimerFired, TimerFiredProps]
        object Transition extends SCApply[Transition, TransitionProps]
        object ListAnything extends SCApply[ListAnything, ListAnythingProps]
        object Group extends SCApply[Group, GroupProps]

        object SubProgram extends SCApply[SubProgram, SubProgramProps]
        object SendMessage extends SCApply[SendMessage, SendMessageProps]
        object CalcGroup extends SCApply[CalcGroup, CalcGroupProps]
        object Variable extends SCApply[Variable, VariableProps]
        object CounterVariable extends SCApply[CounterVariable, CounterVariableProps]

        object ScenarioTests extends SCApply[ScenarioTests, ScenarioTestsProps]
        object ScenarioTestsGroups extends SCApply[ScenarioTestsGroups, ScenarioTestsGroupsProps]
        object ScenarioCompain extends SCApply[ScenarioCompain, ScenarioCompainProps]
        object Compain extends SCApply[Compain, CompainProps]
        object ScenarioTrace extends SCApply[ScenarioTrace, ScenarioTraceProps]
        object ScenarioTraceTest extends SCApply[ScenarioTraceTest, ScenarioTraceTestProps]
        object PersistenceJournalView extends SCApply[PersistenceJournalView, PersistenceJournalViewProps]
        object PersistenceJournalViewTest extends SCApply[PersistenceJournalViewTest, PersistenceJournalViewTestProps]
        object ComData extends SCApply[ComData, ComDataProps]
        object ScenarioStatistics extends  SCApply[ScenarioStatistics, ScenarioStatisticsProps]
        object ConstructorForm extends SCApply[ConstructorForm, ConstructorFormProps]
        object PropertyEditorDynamicForm extends SCApply[PropertyEditorDynamicForm, PropertyEditorDynamicFormProps]
        object PropertyEditorWindow extends SCApply[PropertyEditorWindow, PropertyEditorWindowProps]
        object ListGroupEditor extends SCApply[ListGroupEditor, ListGroupEditorProps]
        object ListEditor extends SCApply[ListEditor, ListEditorProps]
        object FormItemList extends SCApply[FormItemList, FormItemListProps]
        object HistoryList extends SCApply[HistoryList, HistoryListProps]

        object SubProgramItem extends SCApply[SubProgramItem, SubProgramItemProps]
        object SendMessageItem extends SCApply[SendMessageItem, SendMessageItemProps]
        object CalcGroupItem extends SCApply[CalcGroupItem, CalcGroupItemProps]
        object VariableItem extends SCApply[VariableItem, VariableItemProps]
        object CounterAccumItem extends SCApply[CounterAccumItem, CounterAccumItemProps]
        object LoggingItem extends SCApply[LoggingItem, LoggingItemProps]

        object WindowListEditor extends SCApply[WindowListEditor, WindowListEditorProps]
        object TimerU extends SCApply4Object[TimerU, TimerUProps]
        object TabRTM extends SCApply4Object[TabRTM, TabRTMProps]
}
