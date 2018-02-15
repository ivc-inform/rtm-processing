package com.simplesys.SmartClient.Tools.props

import com.simplesys.SmartClient.Drawing.drawItem.DrawPath
import com.simplesys.SmartClient.Drawing.{DrawItem, DrawPane}
import com.simplesys.SmartClient.RPC.RPCManager
import com.simplesys.SmartClient.System.{IscArray, Point, isc}
import com.simplesys.SmartClient.Tools._
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._

import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined

class EditContextSSProps extends EditContextProps {
    type classHandler <: EditContextSS

    val props4Object = IscArray("liveObject", "defaults", "children")

    var _replaceRefs: ScOption[ThisFunction2[classHandler, DrawItem, IscArray[String], _]] = {
        (thiz: classHandler, drawItem: JSObject, globals: IscArray[String]) =>
            val keepAllGlobals = globals(0) == RPCManager.ALL_GLOBALS

            //isc debugTrap drawItem
            js.Object.getOwnPropertyNames(drawItem).foreach {
                propertyName =>
                    val value: JSUndefined[JSObject] = isc.getPropValue(drawItem, propertyName)
                    if (value.isEmpty)
                        isc.deleteProp(drawItem, propertyName)
                    else {
                        if (isc.isA.Array(value.get)) {
                            val array = value.get.asInstanceOf[IscArray[JSObject]]
                            array.zipWithIndex.foreach {
                                case (item, index) =>
                                    if (isc.isA.String(item)) {
                                        val _item = item.asInstanceOf[String]
                                        if (_item.startsWith("ref:")) {
                                            var ref = _item.replace("ref:", "");
                                            if (!keepAllGlobals && !globals.contains(ref))
                                                ref = s"_$ref"
                                            //isc debugTrap ref
                                            val res: JSUndefined[DrawItem] = isc.getWindowObject(ref)
                                            if (res.isDefined)
                                                array(index) = res.get
                                        }
                                    } else if (isc.isA.Object(item) && props4Object.contains(propertyName))
                                        thiz._replaceRefs(item, globals)
                            }
                        }
                        else if (isc.isA.Object(value.get) && props4Object.contains(propertyName))
                            thiz._replaceRefs(value.get, globals)
                        else if (isc.isA.String(value.get)) {
                            val _value = value.get.asInstanceOf[String]
                            if (_value.startsWith("ref:")) {
                                var ref = _value.replace("ref:", "");
                                if (!keepAllGlobals && !globals.contains(ref))
                                    ref = s"_$ref"
                                //isc debugTrap ref
                                val res = isc.getWindowObject(ref)
                                if (res.isDefined)
                                    isc.setPropValue(drawItem, propertyName, res)
                            }
                        }
                    }
            }

    }.toThisFunc.opt

    var addPaletteNodesFromJSON1: ScOption[ThisFunction6[classHandler, Map[String, DrawItem], JSAny, JSUndefined[EditNode], JSUndefined[IscArray[String]], JSUndefined[JSFunction], JSUndefined[JSObject], _]] = {
        (thiz: classHandler, components: Map[String, DrawItem], jsonString: JSAny, parentNode: JSUndefined[EditNode], globals: JSUndefined[IscArray[String]], callback: JSUndefined[JSFunction], addedProps: JSUndefined[JSObject]) =>

            isc.captureDefaults = true.asInstanceOf[JSAny]

            val jsClassDefs = isc.JSON.decode(jsonString.asInstanceOf[String].replaceAll("<div.*</div>", "")/*.replace("\n", "\\n")*/).asInstanceOf[IscArray[JSUndefined[DrawItem]]]
            val _globals = globals.getOrElse(IscArray(RPCManager.ALL_GLOBALS))

            def newInstance(item: DrawItem): Unit = {

                item._constructor foreach {
                    className =>
                        className match {
                            case "DrawPane" =>
                                thiz._replaceRefs(item, _globals)

                                isc.ClassFactory.newInstance(className, item, if (addedProps.isDefined)
                                    js.Dictionary[JSAny](
                                        "editProxyProperties" -> addedProps.asInstanceOf[AddedProps].drawPaneEditProxyProperties
                                    )
                                else
                                    js.Dictionary[JSAny]())

                            case _ =>
                                val props = components.get(className) match {
                                    case None ⇒
                                        js.Dictionary()
                                    case Some(component) ⇒
                                        js.Dictionary("fieldDataSource" → component.asInstanceOf[JSDynamic].selectDynamic("fieldDataSource").undef)
                                }

                                //isc debugTrap item
                                isc.ClassFactory.newInstance(className, item, if (addedProps.isDefined)
                                    js.Dictionary[JSAny](
                                        "contextMenu" -> addedProps.asInstanceOf[AddedProps].contextMenu,
                                        "editProxyProperties" -> addedProps.asInstanceOf[AddedProps].drawItemEditProxyProperties,
                                        "onDragStop" -> addedProps.asInstanceOf[AddedProps].onDragStop,
                                        "resized" -> addedProps.asInstanceOf[AddedProps].resized,
                                        "sourceConnect" -> item.sourceConnect,
                                        "targetConnect" -> item.targetConnect,
                                        "inConnectedItems" -> item.inConnectedItems,
                                        "outConnectedItems" -> item.outConnectedItems,
                                        "sourceGlue" -> item.sourceGlue,
                                        "targetGlue" -> item.targetGlue,
                                        "canDrag" → item.canDrag
                                    )
                                else
                                    js.Dictionary.empty[JSAny], props)
                        }
                }
            }

            val classesDefs: JSArray[DrawItem] = jsClassDefs.filter(_.isDefined).map(_.get)

            classesDefs foreach newInstance

            isc.captureDefaults = jSUndefined

            val capturedComponents = thiz.getCapturedComponents()

            val nodes: UndefOr[IscArray[EditContext]] = capturedComponents.map(nodes => thiz.addFromPaletteNodes(nodes, parentNode))
            nodes foreach {
                drawPanes =>
                    if (drawPanes.length > 0) {
                        val editContext: EditContext = drawPanes(0)
                        //isc debugTrap drawPane
                        thiz._replaceRefs(editContext, _globals)

                        //isc debugTrap editContext
                        if (isc.isA.DrawPane(editContext.liveObject)) {
                            val drawPane = editContext.liveObject.asInstanceOf[DrawPane]

                            drawPane.drawItems.foreach {
                                drawItem =>
                                    //isc debugTrap drawItem
                                    if (drawItem.sourceGlue.isEmpty && drawItem.targetGlue.isDefined)
                                        drawItem.targetGlue.get.setGluedDrawItem()
                            }
                        }
                    }
            }

            callback.foreach {
                callback =>
                    capturedComponents.foreach {
                        capturedComponents =>
                            thiz.fireCallback(callback, IscArray("paletteNodes"), IscArray(capturedComponents))
                    }
            }

    }.toThisFunc.opt

    @ScalaJSDefined
    trait DrawPathProps extends JSObject {
        val points: IscArray[Point]
    }

    @ScalaJSDefined
    trait DrawItemProps extends JSObject {
        val shapeData: JSObject
    }

    var getSerializeableTree: ScOption[ThisFunction3[classHandler, EditNode, JSUndefined[Boolean], JSUndefined[Boolean], _]] = {
        (thiz: classHandler, node: EditNode, dontAddGlobally: JSUndefined[Boolean], topLevel: JSUndefined[Boolean]) =>

            var _node = node

            if (isc.isA.DrawItem(_node.liveObject)) {
                val drawItem = _node.liveObject.asInstanceOf[DrawItem]

                drawItem.editContext.foreach {
                    editContext =>
                        editContext.setNodeProperties1(_node, new DrawItemProps {
                            override val shapeData: JSObject = drawItem.getShapeData()
                        })
                        editContext.removeNodeProperties(_node, IscArray("rotation", "translate", "scale", "xShearFactor", "yShearFactor"))
                }
            }

            if (isc.isA.DrawPath(_node.liveObject)) {
                val drawPath = _node.liveObject.asInstanceOf[DrawPath]
                drawPath.editContext.foreach {
                    editContext =>
                        editContext.setNodeProperties1(_node, new DrawPathProps {
                            override val points: IscArray[Point] = drawPath.points
                        })
                }
            }

            val _type = _node.`type`
            // copy defaults for possible modification
            val defaults = isc.addPropertiesDyn(js.Object(), _node.defaults)

            // if this node is a DataSource (or subclass of DataSource)
            val classObj = isc.ClassFactory.getClass(_type)

            // add autoDraw to non-hidden top-level components
            if (topLevel.getOrElse(false)) {
                _node = isc.addProperties(js.Object(), node, js.Dictionary("defaults" -> defaults))

                if (classObj.isDefined && classObj.get.isA("Canvas") && defaults.visibility != isc.Canvas.HIDDEN && defaults.autoDraw != false)
                    defaults.autoDraw = true
            }

            defaults.parentProperty = jSUndefined

            // A DrawItem can have a fillGradient property. It can either be a reference to a
            // gradient defined in the DrawPane (String) or a Gradient object. During serialization
            // a reference must be serialized as ref="xxx".

            if (isc.isA.DrawItem(_node.liveObject) && defaults.fillGradient != null && isc.isA.String(defaults.fillGradient))
                defaults.fillGradient = "ref:" + defaults.fillGradient

            if (isc.isA.DrawItem(_node.liveObject)) {
                val drawItem = _node.liveObject.asInstanceOf[DrawItem]

                isc.deleteProp(defaults,"sourceConnect")
                drawItem.sourceConnect.foreach(item => defaults.sourceConnect = s"ref:${item.ID}")

                isc.deleteProp(defaults,"targetConnect")
                drawItem.targetConnect.foreach(item => defaults.targetConnect = s"ref:${item.ID}")

                isc.deleteProp(defaults,"inConnectedItems")
                drawItem.inConnectedItems.foreach(item => defaults.inConnectedItems = item.map(item => s"ref:${item.ID}"))

                isc.deleteProp(defaults,"outConnectedItems")
                drawItem.outConnectedItems.foreach(item => defaults.outConnectedItems = item.map(item => s"ref:${item.ID}"))

                isc.deleteProp(defaults,"sourceGlue")
                drawItem.sourceGlue.foreach(item => defaults.sourceGlue = s"ref:${item.ID}")

                isc.deleteProp(defaults,"targetGlue")
                drawItem.targetGlue.foreach(item => defaults.targetGlue = s"ref:${item.ID}")

                isc.deleteProp(defaults,"startLeft2CentrLeft")
                drawItem.startLeft2CentrLeft.foreach(defaults.startLeft2CentrLeft = _)

                isc.deleteProp(defaults,"startTop2CentrTop")
                drawItem.startTop2CentrTop.foreach(defaults.startTop2CentrTop = _)

                isc.deleteProp(defaults,"endLeft2CentrLeft")
                drawItem.endLeft2CentrLeft.foreach(defaults.endLeft2CentrLeft = _)

                isc.deleteProp(defaults,"endTop2CentrTop")
                drawItem.endTop2CentrTop.foreach(defaults.endTop2CentrTop = _)

                isc.deleteProp(defaults,"canDrag")
                drawItem.canDrag.foreach(defaults.canDrag = _)
            }

            // Actions
            // By default these will be defined as simple objects in JS, but for saving in XML
            // we need to enclose them in <Action>...</Action> tags
            // (ensures that any specified mappings are rendered out as an array)
            // Catch these cases and store as a StringMethod object rather than the raw action
            // object - this will serialize correctly.

            isc.EditContext.convertActions(node, defaults, classObj)

            val treeChildren = thiz.getEditNodeTree().getChildren(node)

            if (treeChildren.isEmpty) {
                if (thiz.defaultsBlocks.isDefined) {
                    thiz.defaultsBlocks.get.add(defaults) // add as a top-level node
                }
            } else {

                thiz.serializeChildData(defaults, treeChildren.get)

                if (dontAddGlobally.getOrElse(false)) defaults
                else if (thiz.defaultsBlocks.isDefined)
                    thiz.defaultsBlocks.get.add(defaults)
            }

    }.toThisFunc.opt

    var getDrawPaneEditNode: ScOption[ThisFunction0[classHandler, JSUndefined[EditNode]]] = {
        (thiz: classHandler) =>
            val editTree = thiz.getEditNodeTree()
            val rootNode = thiz.getRootEditNode()
            val childNodes = editTree.getChildren(rootNode)
            if (childNodes.isDefined && childNodes.get.length > 0) childNodes.get(0).asInstanceOf[EditNode].undef else jSUndefined
    }.toThisFunc.opt

    var fireSelectedEditNodesUpdated: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>

            thiz._getSelectionEditProxy().foreach {
                editProxy =>
                    val editNodesList = thiz.getSelectedEditNodes1()

                    editNodesList.foreach {
                        editNodeList =>
                            val editNode: JSUndefined[EditNode] = if (editNodeList.length > 0) editNodeList(0) else jSUndefined
                            editNode.foreach {
                                editNode =>

                                    editNodeList.foreach {
                                        editNode =>
                                            val drawItem = editNode.liveObject.asInstanceOf[DrawItem]
                                            drawItem.moveBy(1, 1)
                                            drawItem.moveBy(-1, -1)
                                    }

                                    editProxy.selectedEditNodesUpdated.foreach(
                                        selectedEditNodesUpdated =>
                                            if (isc.isA.DrawPane(thiz._selectionLiveObject)) {
                                                val editPane = thiz._selectionLiveObject.asInstanceOf[DrawPane]
                                                selectedEditNodesUpdated(editPane.editProxy, editNode, editNodeList)
                                            }
                                    )

                                    thiz.selectedEditNodesUpdated.foreach {
                                        selectedEditNodesUpdated =>
                                            selectedEditNodesUpdated(thiz, editNode, editNodeList)
                                    }
                            }
                    }
            }

    }.toThisFunc.opt
}
