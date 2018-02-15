package com.simplesys.components.props

import com.simplesys.SmartClient.App.LoggedGroup
import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.{CommonTreeListGridEditorComponentProps, NewDSRequestData}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{IButtonSSProps, MenuSSProps}
import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse, JSON}
import com.simplesys.SmartClient.DataBinding.props.DSRequestProps
import com.simplesys.SmartClient.Foundation.props.HTMLPaneSSProps
import com.simplesys.SmartClient.Foundation.{Canvas, HTMLPaneSS}
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.treeGrid.TreeNode
import com.simplesys.SmartClient.Grids.{GridEditor, TreeGridEditor}
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.Layout.props.WindowSSProps
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse, RPCResponseStatic}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.App.ScenarioTestsGroups
import com.simplesys.function._
import com.simplesys.components.{ScenarioTests, StatusScenario, StatusTestScenario}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import com.simplesys.appCommon.common._
import ru.simplesys.defs.app.scala.container._
import ru.simplesys.defs.app.scala.container.scenarios.ScenarioTesterContainerShared

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

trait RefreshTestGroupGridResult extends JSObject {
    val value: JSUndefined[Int]
    val idGroupTest: JSUndefined[Double]
    val codeGroup: JSUndefined[String]
    val captionGroup: JSUndefined[String]
    val data: JSUndefined[String]
}

trait ScenarioTestData extends JSObject {
    val idsGroupTest: JSArray[Double]
    val mode: JSUndefined[Int]
}

trait ScenarioErrorData extends JSObject {
    val message: JSUndefined[String]
    val stackTrace: JSUndefined[String]
}

class LoggingViewer extends JSObject {
    private val windows = IscArray[WindowSS]()
    private val logs = js.Dictionary[IscArray[String]]()

    def getWindowIdentifier(idGroupTest: Double) = s"${loggingWindowGroupID}_$idGroupTest"

    def closeWindow(idGroupTest: Double): Unit = {
        windows.find(_.identifier.getOrElse("") == getWindowIdentifier(idGroupTest)).foreach {
            window ⇒
                window.close()
                windows remove(obj = window, comparator = (w: WindowSS, w1: WindowSS) ⇒ w.identifier == window.identifier)
        }
    }

    def closeAllWindow(): Unit = windows.foreach(window ⇒ closeWindow(window.identifier1.getOrElse("").toDouble))

    def clearLog(idGroupTest: Double): LoggingViewer = {
        logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.foreach {
            case (key, logData) ⇒
                logData.length = 0
                findWindow(idGroupTest) match {
                    case None ⇒
                    case Some(window) ⇒
                        window.items.foreach {
                            items ⇒
                                val hTMLPaneSS = items(0).asInstanceOf[HTMLPaneSS]
                                hTMLPaneSS.clearContents()
                                println(s"hTMLPaneSS.clearContents()")
                        }
                }
        }
        this
    }

    def subscribe4Update(idGroupTest: Double): Unit = {
        if (logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.isEmpty)
            logs(idGroupTest.toLong.toString) = IscArray[String]()

        isc.MessagingSS.subscribe(updateContentLogWindowMessage(idGroupTest), {
            (messageJS: MessageJS) ⇒
                messageJS.data.foreach {
                    data ⇒
                        //isc debugTrap data
                        updateLog(idGroupTest, data.toString)
                }
        })

    }

    def unSubscribe4Update(idGroupTest: Double): Unit = {
        logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.foreach {
            case (key, _) ⇒
                isc.MessagingSS.unsubscribe(updateContentLogWindowMessage(idGroupTest))
        }
    }

    def updateLog(idGroupTest: Double, logStr: String): LoggingViewer = {
        println(s"updateLog($idGroupTest, $logStr)")
        if (logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.isEmpty) {
            logs(idGroupTest.toLong.toString) = IscArray[String]()
            println(s"logs(idGroupTest.toLong.toString) = ${IscArray[String]()}")
        }

        logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.foreach {
            case (key, logData) ⇒
                logData append logStr
                findWindow(idGroupTest) match {
                    case None ⇒
                    case Some(window) ⇒
                        window.items.foreach {
                            items ⇒
                                val richEditor = items(0).asInstanceOf[HTMLPaneSS]
                                richEditor addContents logStr
                                println(s"richEditor addContents $logStr")
                        }
                }
        }
        this
    }

    def findWindow(idGroupTest: Double): Option[WindowSS] = {
        val res = windows.find(_.identifier.getOrElse("") == getWindowIdentifier(idGroupTest))
        println(s"findWindow($idGroupTest) = $res")
        res
    }

    def getWindow(idGroupTest: Double, codeGroup: String, captionGroup: String): Unit = {
        println(s"getWindow($idGroupTest, $codeGroup, $captionGroup)")
        findWindow(idGroupTest) match {
            case None ⇒
                val richEditor = HTMLPaneSS.create(
                    new HTMLPaneSSProps {
                        autoDraw = false.opt
                        height = "100%"
                        width = "100%"
                        //controlGroups = Seq().opt
                        //value = s"<plaintext>$error".opt
                    }
                )

                //isc debugTrap logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }
                logs.find { case (key, _) ⇒ key == idGroupTest.toLong.toString }.foreach {
                    case (_, logData) ⇒
                        //isc debugTrap logData
                        logData.foreach(richEditor addContents _)
                }

                windows add WindowSS.create(
                    new WindowSSProps {
                        identifier = getWindowIdentifier(idGroupTest).opt
                        identifier1 = idGroupTest.toLong.toString.opt
                        title = s"Протокол выполнения: $captionGroup: ($codeGroup)".ellipsis.opt
                        headerIconPath = Common.lists.opt
                        height = 500
                        width = 600
                        canDragResize = true.opt
                        canDragReposition = true.opt
                        keepInParentRect = true.opt
                        autoCenter = false.opt
                        autoSize = false.opt
                        isModal = false.opt
                        showMinimizeButton = true.opt
                        showMaximizeButton = true.opt
                        items = Seq(richEditor).opt
                        autoDestroy = true.opt
                        initWidget = {
                            (thiz: classHandler, args: IscArray[JSAny]) ⇒
                                thiz.Super("initWidget", args)
                                thiz.autoDestroy.foreach {
                                    autoDestroy ⇒
                                        if (autoDestroy)
                                            thiz.observe(thiz, "close",
                                                () ⇒ {
                                                    windows remove(obj = thiz, comparator = (w: WindowSS, w1: WindowSS) ⇒ w.identifier == thiz.identifier)
                                                    thiz.markForDestroy()
                                                }
                                            )
                                }
                        }.toThisFunc.opt
                    }
                )
            case Some(wnd) ⇒
                if (!wnd.destroyed.getOrElse(false)) {
                    wnd.show()
                    println(s"wnd.show()")
                    wnd.bringToFront()
                    println(s"wnd.bringToFront()")
                }

        }
    }

    def getViewWindows(selectedRecords: ScenariosTestGroupDataRecord*): Unit = selectedRecords.foreach {
        selectedRecord ⇒
            (for (id ← selectedRecord.id; codeGroup ← selectedRecord.codeGroup; captionGroup ← selectedRecord.captionGroup) yield (id, codeGroup, captionGroup)).foreach {
                case (id, codeGroup, captionGroup) ⇒
                    getWindow(id, codeGroup, captionGroup)
                    println(s"getWindow($id, $codeGroup, $captionGroup)")
            }
    }
}

class ScenarioTestsProps extends CommonTreeListGridEditorComponentProps {
    type classHandler <: ScenarioTests

    identifier = "D8C4BF69-A1A9-230D-AFBE-747A804E9F9B".opt

    captionMenuTree = "Группы тестов".opt
    captionMenuList = "Элементы тестирования".opt

    val _loggingViewer = new LoggingViewer
    val loggingViewer: ScOption[LoggingViewer] = _loggingViewer.opt

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

    //selectionTypeTree = SelectionStyle.multiple.opt
    //selectionAppearanceTree = SelectionAppearance.checkbox.opt

    dataSourceList = DataSourcesJS.scenarios_Test_DS.opt
    dataSourceTree = DataSourcesJS.scenarios_TestGroup_DS.opt

    fieldsTree = ListGridFiledsJS.scenarios_TestGroup_FLDS.opt
    editingTreeFields = FormItemsJS.scenarios_TestGroup_FRMITM.opt

    fieldsList = ListGridFiledsJS.scenarios_Test_FLDS.opt
    editingListFields = FormItemsJS.scenarios_Test_FRMITM.opt

    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = scenarios_Test_codeGroup_Group_NameStrong.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = ScenarioTestsGroups.create(new ScenarioTestsGroupsProps).opt
                }).opt
        }
    ).opt

    showTreeRecordComponents = true.opt
    showTreeRecordComponentsByCell = true.opt
    recordTreeComponentPoolingMode = RecordComponentPoolingMode.recycle.opt

    def seqMenuControlsItemProps(owner: TreeGridEditor) = Seq(
        new MenuSSItemProps {
            title = "Запустить".ellipsis.opt
            identifier = "start".opt
            icon = start.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor
                    makeTest(editor, StatusTestScenario.play)
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner

                    owner.getSelectedRecords().length == 1 && StatusTestScenario.enable2Start(owner.getSelectedRecords().map(_.status)) && owner.getSelectedRecord().active.getOrElse(false)
            }.toFunc.opt

        },
        new MenuSSItemProps {
            title = "Запустить как Prod".ellipsis.opt
            identifier = "startAsPros".opt
            icon = start.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor
                    makeTest(editor, StatusTestScenario.playAsProd)
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner

                    owner.getSelectedRecords().length == 1 && StatusTestScenario.enable2Start(owner.getSelectedRecords().map(_.status)) && owner.getSelectedRecord().active.getOrElse(false)
            }.toFunc.opt
            visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
        },
        new MenuSSItemProps {
            isSeparator = true.opt
        },
        new MenuSSItemProps {
            title = "Остановить".ellipsis.opt
            identifier = "stop".opt
            icon = stoped.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor: GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState] = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor
                    makeTest(editor, StatusTestScenario.stoped)
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length > 0 && StatusTestScenario.enable2Stop(owner.getSelectedRecords().map(_.status))
            }.toFunc.opt

        }
    ).map { item ⇒ item.owner = owner.opt; item }

    dataArrivedTree = {
        (thiz: classHandler, startRecord: Int, endRecord: Int) ⇒
            isc.ok("dataArrived")
    }.toThisFunc.opt

    initWidget = {
        (thisTop: classHandler, arguments: IscArray[JSAny]) =>
            //isc debugTrap thisTop
            //thisTop.listGrid.observe(thisTop.listGrid, "dataArrived", () ⇒ isc.ok("Data Arrived"))

            thisTop.customMenuTree = IscArray(
                seqMenuControlsItemProps(thisTop.treeGrid).map(MenuSSItem(_)) ++
                  IscArray(
                      MenuSSItem(new MenuSSItemProps {
                          isSeparator = true.opt
                      }),
                      MenuSSItem(
                          new MenuSSItemProps {
                              title = "Протокол выполнения теста".ellipsis.opt
                              identifier = "logView".opt
                              icon = login.opt
                              click = {
                                  (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                      val editor = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                                      simpleSyS checkOwner editor
                                      _loggingViewer.getViewWindows(editor.getSelectedRecords(): _*)

                              }.toFunc.opt
                              enableIf = {
                                  (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                      val owner = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                                      simpleSyS checkOwner owner
                                      owner.getSelectedRecords().length > 0
                              }.toFunc.opt

                          }),
                      MenuSSItem(
                          new MenuSSItemProps {
                              title = "Закрыть все окна протокола выполнения теста".ellipsis.opt
                              identifier = "logView".opt
                              icon = iconDiscard.opt
                              click = {
                                  (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                      _loggingViewer.closeAllWindow()

                              }.toFunc.opt
                              enableIf = {
                                  (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                      val owner = item.owner.asInstanceOf[GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState]]
                                      simpleSyS checkOwner owner
                                      owner.getSelectedRecords().length > 0
                              }.toFunc.opt

                          })
                  ): _*
            )

            thisTop.Super("initWidget", arguments)

            thisTop.observe(thisTop, "destroy", () ⇒ _loggingViewer.closeAllWindow())

           /* def transformResponse(response: DSResponse, request: DSRequest, data: JSON): DSResponse = {
                isc debugTrap data
                thisTop.treeGrid.invalidateRecordComponents()
                response
            }

            DataSourcesJS.scenarios_TestGroup_DS.transformResponse = transformResponse _*/

    }.toThisFunc.opt

    def getButtonProps(record: ScenariosTestGroupDataRecord, listGridEditor: TreeGridEditor) = {
        val isDisabled: Boolean = !record.active.getOrElse(false)

        new IButtonSSProps {
            height = 20
            width = 130
            layoutAlign = Alignment.center
            title = StatusTestScenario.getStatus(record.status).title.opt
            prompt = StatusTestScenario.getStatus(record.status).prompt.opt
            icon = StatusTestScenario.getStatus(record.status).icon.opt
            showDisabledIcon = false.opt
            //disabled = isDisabled.opt
            click = {
                (thiz: classHandler) ⇒
                    listGridEditor.deselectAllRecords()
                    listGridEditor selectRecord record.asInstanceOf[TreeNode]

                    MenuSS.create(
                        new MenuSSProps {
                            items = seqMenuControlsItemProps(listGridEditor).opt
                        }
                    ).showContextMenu()
                    true
            }.toThisFunc.opt
        }
    }

    createTreeRecordComponent = {
        (thiz: TreeGridEditor, record: ScenariosTestGroupDataRecord, colNum: Int) =>

            val fieldName = thiz.getFieldName(colNum)
            fieldName match {
                case x if x == scenarios_TestGroup_status_NameStrong.name ⇒
                    //isc debugTrap (thiz, fieldName, record.status)
                    IButtonSS.create(getButtonProps(record, thiz)).asInstanceOf[Canvas].undef
                case _ ⇒
                    jSUndefined
            }
    }.toThisFunc.opt

    updateTreeRecordComponent = {
        (thiz: TreeGridEditor, record: ScenariosTestGroupDataRecord, colNum: Int, component: Canvas, recordChanged: Boolean) ⇒
            isc debugTrap(thiz, record, colNum, component, recordChanged)

            val fieldName = thiz.getFieldName(colNum)
            fieldName match {
                case x if x == scenarios_TestGroup_status_NameStrong.name ⇒
                    component.addProperties(IButtonSS(getButtonProps(record, thiz))).undef
                case _ ⇒
                    jSUndefined
            }
    }.toThisFunc.opt

    def makeTest(editor: GridEditor[ListGridField, ScenariosTestGroupDataRecord, ListGridSelectedState], _mode: StatusScenario): Unit = {
        //isc debugTrap editor
        val _idsGroupTest: JSArray[Double] = editor.getSelectedRecords().map(_.id.get)

        isc.MessagingSS.subscribe(errorMessage, {
            (messageJS: MessageJS) ⇒
                messageJS.data.foreach {
                    item ⇒
                        val data = item.asInstanceOf[ScenarioErrorData]
                        isc errorDetail(data.message.getOrElse("None"), data.stackTrace.getOrElse("None"), "EC6F3AE1-7667-A81F-11DE-DD15015E42BB", "39AD9472-22FB-31BA-01D9-F94E34D8BF05")
                }
        })

        isc.MessagingSS.subscribe(openLogWindowMessage, {
            (messageJS: MessageJS) ⇒
                messageJS.data.foreach {
                    item ⇒
                        val data = item.asInstanceOf[RefreshTestGroupGridResult]

                        (for (idGroupTest ← data.idGroupTest; codeGroup ← data.codeGroup; captionGroup <- data.captionGroup) yield (idGroupTest, codeGroup, captionGroup)).foreach {
                            case (idGroupTest, codeGroup, captionGroup) ⇒
                                _loggingViewer.getWindow(idGroupTest, codeGroup, captionGroup)
                        }
                }
        })

        isc.MessagingSS.subscribe(refteshTestGroupGridMessage,
            (messageJS: MessageJS) ⇒
                messageJS.data.foreach {
                    item ⇒
                        val data = item.asInstanceOf[RefreshTestGroupGridResult]
                        (for (idGroupTest ← data.idGroupTest; value ← data.value) yield (idGroupTest, value)).foreach {
                            case (idGroupTest, value) ⇒
                                value match {
                                    case value if value == StatusTestScenario.stoped.value ⇒
                                        isc.MessagingSS unsubscribe IscArray(openLogWindowMessage, errorMessage, refteshTestGroupGridMessage)
                                        _loggingViewer unSubscribe4Update idGroupTest

                                    case value if value == StatusTestScenario.play.value ⇒
                                }
                        }

                        if (isc.isA.TreeGridEditor(editor)) {
                            val selectedRec = editor.treeGrid.findByKey(data.idGroupTest.get)
                            selectedRec.asInstanceOf[JSDynamic].updateDynamic("status")(data.value)
                            editor.treeGrid.applyRecordData(IscArray(selectedRec))
                            editor.treeGrid.invalidateRecordComponents()

                        }
                        else if (isc.isA.TreeGrid(editor)) {
                            val selectedRec = editor.findByKey(data.idGroupTest.get)
                            selectedRec.asInstanceOf[JSDynamic].updateDynamic("status")(data.value)
                            selectedRec.foreach(rec ⇒ editor.applyRecordData(IscArray(rec)))
                            editor.invalidateRecordComponents()
                        }
                }
        )

        _idsGroupTest.foreach {
            idGroupTest ⇒
                _loggingViewer clearLog idGroupTest
                _loggingViewer subscribe4Update idGroupTest
        }


        RPCManagerSS.sendRequest(
            RPCRequest(
                new RPCRequestProps {
                    ignoreTimeout = true.opt
                    sendNoQueue = true.opt
                    data = new ScenarioTestData {
                        override val idsGroupTest: JSArray[Double] = _idsGroupTest
                        override val mode: JSUndefined[Int] = _mode.value
                    }.opt
                    actionURL = (simpleSyS.simpleSysContextPath + ScenarioTesterContainerShared.scenarios_Scr_Scenario_ScenarioTest).opt
                    callback = {
                        (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                            resp.results.foreach {
                                _.response.foreach {
                                    response ⇒
                                        if (response.status != RPCResponseStatic.STATUS_SUCCESS)
                                            response.data.foreach {
                                                error ⇒ isc error(error.toString, "163B7F9E-576B-7EFA-8F3C-E536055508B4")
                                            }
                                }
                            }
                    }.toFunc.opt
                }
            )
        )
    }
}
