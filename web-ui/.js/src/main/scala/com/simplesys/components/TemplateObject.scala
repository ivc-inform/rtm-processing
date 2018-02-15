package com.simplesys.components

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System.{DrawItem, IscArray, isc}
import com.simplesys.System.Types.ID
import com.simplesys.System._
import ru.simplesys.defs.app.scala.container.ScenariosListsDataRecord

import scala.annotation.tailrec
import scala.scalajs.js.annotation.ScalaJSDefined

trait IDandTitle extends JSObject {
    val ID: ID
    val title: String
}

object ScopeVisivlity extends Enumeration {
    type ScopeVisivlity = Value
    val scenario, entryCheck, instance, state = Value
}

object TemplateObject {
    implicit class DrawItemProps(val drawItem: DrawItem) {
    }

    implicit class DrawItemSeqProps(val drawItems: IscArray[DrawItem]) {
        def filterOfTypes(types: String*): IscArray[DrawItem] = IscArray(drawItems.filter(item ⇒ Seq(types: _*).contains(item._constructor)): _*)
        def getIDs(): IscArray[ID] = IscArray(drawItems.map(_.getID()): _*)
        def getIDandTitle(): IscArray[IDandTitle with Object {val ID: ID; val title: String}] = IscArray(drawItems.map(item ⇒ new IDandTitle {
            override val ID: ID = item.getID()
            override val title: String = item.title.getOrElse(item.getID()).replace("\n", " ")
        }): _*)
    }

    implicit class StrProps(val str: String) {
        def simpleID: String = str.trim.replace("ref:", "")
        def simpleCode: String = str.trim.replace("$", "")
    }

    implicit class DrawItemOptSeqProps(val drawItems: JSUndefined[IscArray[DrawItem]]) {
        def toSeq: IscArray[DrawItem] = drawItems.toOption match {
            case None ⇒ IscArray[DrawItem]()
            case Some(seq) ⇒ seq
        }
    }
}

case class TemplateObject(drawItems: IscArray[DrawItem]) {

    import ScopeVisivlity._
    import TemplateObject._
    import com.simplesys.components.drawing.drawItems._

    def getElementOfType(_constructor: String): DrawItem = {
        drawItems.find(_._constructor.getOrElse("") == _constructor) match {
            case Some(item) ⇒ item
            case None ⇒ throw new RuntimeException(s"Не найден элемент ${_constructor}.")
        }
    }

    def getMultiID(parentID: ID, keyMultyProps: String): String = s"${parentID}_$keyMultyProps"

    def getElementOfID(ID: String): DrawItem = {
        drawItems.find(_.ID == ID.simpleID) match {
            case Some(item) ⇒ item
            case None ⇒ throw new RuntimeException(s"Не найден элемент ${ID}")
        }
    }

    def getSubProgramsFromMultiElements: IscArray[DrawItem] = IscArray(drawItems.filter(_.multiElementsProps.isDefined).filter(_.multiElementsProps.get.asInstanceOf[MultiElementsValue].subPrograms.isDefined).flatMap(drawItem ⇒ drawItem.multiElementsProps.get.asInstanceOf[MultiElementsValue].subPrograms.get.map {
        item ⇒
            import com.simplesys.SmartClient.Drawing.props.DrawItemProps

            val res = isc.addProperties[DrawItem](DrawItem.create(new DrawItemProps), drawItem)
            res.ID = getMultiID(drawItem.ID, item._1)
            res.subProgramProps = item._2
            //isc debugTrap(drawItem, res)
            res
    }): _*)

    def getSubPrograms: IscArray[DrawItem] = IscArray(drawItems.filter(_.subProgramProps.isDefined) ++ getSubProgramsFromMultiElements: _*)
    def getTransitionSubPrograms: IscArray[DrawItem] = IscArray(getSubPrograms.filter(_.subProgramProps.get.asInstanceOf[SubProgramValue].transition2Data.getOrElse(false)): _*)
    def getTransitionSubProgramsOfTypes(returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(getTransitionSubPrograms.filter(item ⇒ returnTypes.contains(item.subProgramProps.get.asInstanceOf[SubProgramValue].returnType)): _*)
    def getSubProgramsOfReturnTypes(returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(drawItems.filter(_.subProgramProps.isDefined).filter(item ⇒ returnTypes.contains(item.subProgramProps.get.asInstanceOf[SubProgramValue].returnType)): _*)
    def getSubProgramsCompanion: IscArray[DrawItem] = IscArray(getSubPrograms.filter(_.subProgramProps.get.asInstanceOf[SubProgramValue].scopeVisivlity.getOrElse("") == scenario.toString): _*)
    def getSubProgramsEntryCheck: IscArray[DrawItem] = IscArray(getSubPrograms.filter(_.subProgramProps.get.asInstanceOf[SubProgramValue].scopeVisivlity.getOrElse("") == entryCheck.toString): _*)
    def getSubProgramsInstance: IscArray[DrawItem] = IscArray(getSubPrograms.filter(_.subProgramProps.get.asInstanceOf[SubProgramValue].scopeVisivlity.getOrElse("") == instance.toString): _*)
    def getSubProgramsState: IscArray[DrawItem] = IscArray(getSubPrograms.filter(_.subProgramProps.get.asInstanceOf[SubProgramValue].scopeVisivlity.getOrElse("") == state.toString): _*)


    def getVariablesFromMultiElements: IscArray[DrawItem] = IscArray(drawItems.filter(_.multiElementsProps.isDefined).filter(_.multiElementsProps.get.asInstanceOf[MultiElementsValue].variables.isDefined).flatMap(drawItem ⇒ drawItem.multiElementsProps.get.asInstanceOf[MultiElementsValue].variables.get.map {
        item ⇒
            import com.simplesys.SmartClient.Drawing.props.DrawItemProps

            val res = isc.addProperties[DrawItem](DrawItem.create(new DrawItemProps), drawItem)
            res.ID = getMultiID(drawItem.ID, item._1)
            res.variableProps = item._2
            //isc debugTrap(drawItem, res)
            res
    }): _*)
    def getVariables: IscArray[DrawItem] = IscArray(drawItems.filter(_.variableProps.isDefined) ++ getVariablesFromMultiElements: _*)
    def getTransitionVariables: IscArray[DrawItem] = IscArray(getVariables.filter(_.variableProps.get.asInstanceOf[VariableValue].transition2Data.getOrElse(false)): _*)
    def getTransitionVariablesOfTypes(returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(getTransitionVariables.filter(item ⇒ returnTypes.contains(item.variableProps.get.asInstanceOf[VariableValue].returnType)): _*)
    def getVariablesOfReturnTypes(returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(drawItems.filter(_.variableProps.isDefined).filter(item ⇒ returnTypes.contains(item.variableProps.get.asInstanceOf[VariableValue].returnType)): _*)
    def getVariablesOfTypes(types: Seq[String]): IscArray[DrawItem] = IscArray(drawItems.filter(item ⇒ types.contains(item._constructor)): _*)
    def getVariablesCompanion: IscArray[DrawItem] = IscArray(getVariables.filter(_.variableProps.get.asInstanceOf[VariableValue].scopeVisivlity.getOrElse("") == scenario.toString): _*)
    def getVariablesEntryCheck: IscArray[DrawItem] = IscArray(getVariables.filter(_.variableProps.get.asInstanceOf[VariableValue].scopeVisivlity.getOrElse("") == entryCheck.toString): _*)
    def getVariablesInstance: IscArray[DrawItem] = IscArray(getVariables.filter(_.variableProps.get.asInstanceOf[VariableValue].scopeVisivlity.getOrElse("") == instance.toString): _*)
    def getVariablesState: IscArray[DrawItem] = IscArray(getVariables.filter(_.variableProps.get.asInstanceOf[VariableValue].scopeVisivlity.getOrElse("") == state.toString): _*)

    def getLists: IscArray[DrawItem] = IscArray(drawItems.filter(_.listRefs.isDefined): _*)
    def isList(codeList: String): Boolean = drawItems.filter(_.listRefs.isDefined).find(item ⇒ if (item.listRefs.isEmpty) false else item.listRefs.get.asInstanceOf[ScenariosListsDataRecord].code_list.getOrElse("") == codeList).isDefined
    def getList(codeList: String): DrawItem = drawItems.filter(_.listRefs.isDefined).find(item ⇒ if (item.listRefs.isEmpty) false else item.listRefs.get.asInstanceOf[ScenariosListsDataRecord].code_list.getOrElse("") == codeList).get

    def getTimers: IscArray[DrawItem] = IscArray(drawItems.filter(_.timerProps.isDefined): _*)
    def getGroup: IscArray[DrawItem] = IscArray(drawItems.filter(_.groupProps.isDefined): _*)

    def getStartState: DrawItem = drawItems.filter(_._constructor.getOrElse("") == StartState.typeName).head

    def getIncomingMessage: DrawItem = {
        val startItem: DrawItem = getElementOfType(StartState.typeName)

        startItem.outConnectedItems.toOption match {
            case None ⇒ throw new RuntimeException(s"У элемента CheckEntry нет связанного элемента IncomingMessage.")
            case Some(seq) ⇒
                if (seq.length == 0)
                    throw new RuntimeException(s"У элемента CheckEntry нет связанного элемента IncomingMessage.")
                else if (seq.length > 1)
                    throw new RuntimeException(s"У элемента CheckEntry может быть только один связанный элемент")
                else getElementOfID(seq.head.ID.simpleID)
        }
    }

    import TemplateObject._
    import com.simplesys.components.drawing.drawItems._

    @tailrec
    final def getGluedElements(item: JSUndefined[DrawItem], seq: IscArray[DrawItem] = IscArray[DrawItem]()): IscArray[DrawItem] = {
        val res: JSUndefined[DrawItem] = if (item.isEmpty) item else item.get.targetGlue.map(item ⇒ getElementOfID(item.ID))

        if (res.isEmpty)
            seq
        else {
            val drawItems: Seq[DrawItem] = seq.toSeq ++ IscArray(res.get).toSeq
            getGluedElements(res.get, IscArray(drawItems: _*))
        }
    }

    def getIncomingEvents(item: DrawItem): JSUndefined[IscArray[DrawItem]] = item.inConnectedItems.map(item ⇒ IscArray(item.map(item ⇒ getElementOfID(item.ID)): _*).filterOfTypes(IncomingMessage.typeName, Transition.typeName, TimerFired.typeName))

    def getOutTransitions(item: DrawItem): JSUndefined[IscArray[DrawItem]] = item.outConnectedItems.map(item ⇒ IscArray(item.map(item ⇒ getElementOfID(item.ID)): _*).filterOfTypes(IncomingMessage.typeName, Transition.typeName, TimerFired.typeName))

    def getTransitionFromPreviosState(item: DrawItem, previosState: DrawItem): DrawItem = item.inConnectedItems.toOption match {
        case None ⇒ throw new RuntimeException(s"Не найдена входящая связь.")
        case Some(seq) ⇒
            seq.map(item ⇒ getElementOfID(item.ID)).filter(_.sourceConnect.isDefined).filter(_.sourceConnect.get.ID.simpleID == previosState.ID).head
    }

    def gluedElements(item: DrawItem): IscArray[DrawItem] = {
        val gluedEllements = TemplateObject(getGluedElements(item))
        IscArray(gluedEllements.getSubPrograms ++ gluedEllements.getVariables: _*)
    }
    def gluedSubPrograms(item: DrawItem): IscArray[DrawItem] = IscArray(getGluedElements(item).filter(_.subProgramProps.isDefined): _*)
    def gluedVariables(item: DrawItem): IscArray[DrawItem] = IscArray(getGluedElements(item).filter(_.variableProps.isDefined): _*)

    def gluedVariablesOfTypes(item: DrawItem, returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(gluedVariables(item).filter(item ⇒ returnTypes.contains(item.variableProps.get.asInstanceOf[VariableValue].returnType)): _*)
    def gluedSubProgramsOfTypes(item: DrawItem, returnTypes: Seq[String]): IscArray[DrawItem] = IscArray(gluedSubPrograms(item).filter(item ⇒ returnTypes.contains(item.subProgramProps.get.asInstanceOf[SubProgramValue].returnType)): _*)

    def getTimers(item: DrawItem): IscArray[DrawItem] = IscArray(getGluedElements(item).filter(_._constructor.getOrElse("") == TimerUnified.typeName).filter(timer ⇒ timer.timerProps.isDefined && timer.outConnectedItems.isDefined && timer.outConnectedItems.get.length > 0): _*)

    def transitions4NextStates(item: DrawItem): IscArray[DrawItem] = IscArray({
        val transitions: IscArray[DrawItem] = getOutTransitions(item).toSeq

        val timerFiredTransitions: IscArray[DrawItem] = IscArray(getTimers.filter(_.outConnectedItems.isDefined).map(_.ID).map(getElementOfID): _*)

        if (transitions.length == 0)
            timerFiredTransitions
        else
            transitions

    }.filter(_.targetConnect.isDefined): _*)

    def nextStates(item: DrawItem): IscArray[DrawItem] = IscArray(transitions4NextStates(item).map(_.targetConnect.get).map(item ⇒ getElementOfID(item.ID)): _*)
}
