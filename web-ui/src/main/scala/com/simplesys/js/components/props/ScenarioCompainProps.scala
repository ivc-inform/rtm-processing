package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.LoggedGroup
import com.simplesys.SmartClient.App.formItems.props.LookupTreeGridEditorItemProps
import com.simplesys.SmartClient.App.props.CommonTreeListGridEditorComponentProps
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{IButtonSSProps, MenuSSProps}
import com.simplesys.SmartClient.DataBinding.props.{AdvancedCriteriaProps, CriterionProps, DSRequestProps}
import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, DSRequest, DSResponse, RestDataSourceSS}
import com.simplesys.SmartClient.Foundation.props.RichTextEditorProps
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Grids.listGrid.{ListGridField, ListGridRecord}
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.{GridEditor, ListGridEditor}
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.{TabSetSSProps, WindowSSProps}
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{IconMenuButtonSS, TabSetSS, WindowSS}
import com.simplesys.SmartClient.Messaging.MessageJS
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse, RPCResponseStatic}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Alignment.{Alignment ⇒ _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.{Compain, ScenarioStatistics, TabRTM}
import com.simplesys.appCommon
import com.simplesys.container.FullHandContainer._
import com.simplesys.function._
import com.simplesys.js.components._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.{Scr_ScenarioClobConfigDataRecord, Scr_ScenarioDataRecord}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Scr_ScenarioClobConfigDataRecordExt extends Scr_ScenarioClobConfigDataRecord {
    val error: JSUndefined[ErrorStuct]
}

@ScalaJSDefined
trait ScenarioUpdateData extends JSObject {
    val id_scenario: JSUndefined[Double]
    val mode: JSUndefined[String]
    val scenarioTestMode: JSUndefined[Boolean]
    val scenarioProdTestMode: JSUndefined[Boolean]
}

@ScalaJSDefined
trait RefreshScenarioGridResult extends JSObject {
    val error: JSUndefined[String]
}

class ScenarioCompainProps extends CommonTreeListGridEditorComponentProps {

    type classHandler <: ScenarioCompain

    val windows = IscArray[WindowSS]()

    captionMenuTree = "Кампании".opt
    captionMenuList = "Сценарии".opt

    /*selectionTypeList = SelectionStyle.multiple.opt
    selectionAppearanceList = SelectionAppearance.checkbox.opt*/

    identifier = "D123BF69-A1A9-230D-AFBE-747A804E9F9B".opt
    var functionButton: ScOption[IconMenuButtonSS] = ScNone

    newTreeRequestProperties = {
        (thiz: classHandler) =>
            DSRequest(
                new DSRequestProps {
                    data = (new NewCompainRequestData {
                        override val active_cmpgn: Boolean = true
                    }).opt
                }
            )

    }.toThisFunc.opt

    dataSourceList = DataSourcesJS.scenarios_Scr_Scenario_DS.opt
    dataSourceTree = DataSourcesJS.scenarios_Scr_Cmpgn_DS.opt

    fieldsTree = ListGridFiledsJS.scenarios_Scr_Cmpgn_FLDS.opt
    editingTreeFields = FormItemsJS.scenarios_Scr_Cmpgn_FRMITM.opt

    fieldsList = ListGridFiledsJS.scenarios_Scr_Scenario_FLDS.opt
    editingListFields = FormItemsJS.scenarios_Scr_Scenario_FRMITM.opt

    replacingFieldsList = Seq(
        new ListGridFieldProps {
            nameStrong = scenarios_Scr_Scenario_status_NameStrong.opt
            `type` = ListGridFieldType.nInt_SimpleType.opt
            align = Alignment.center.opt
        },
        new ListGridFieldProps {
            nameStrong = scenarios_Scr_Scenario_caption_cmpgn_NameStrong.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Compain.create(new CompainProps).opt
                }).opt
        },
        new ListGridFieldProps {
            nameStrong = scenarios_Scr_Scenario_code_cmpgn_NameStrong.opt
            editorType = FormItemComponentType.LookupTreeGridEditorItem
            editorProperties = LookupTreeGridEditorItem(
                new LookupTreeGridEditorItemProps {
                    treeGridEditor = Compain.create(new CompainProps).opt
                }).opt
        }
    ).opt

    def makeOperation(idScenario: JSUndefined[Double], _mode: StatusScenario, testMode: JSUndefined[Boolean] = false, prodTestMode: JSUndefined[Boolean] = false): Unit = {

        val info: Option[WindowSS] = _mode match {
            case mode: Ready ⇒ Some(isc info("Подготовка".ellipsis, "6C2A513E-CEE6-E6B9-5F7F-BE8484657D17"))
            case mode: Play ⇒ Some(isc info("Запуск".ellipsis, "6C2A513E-CEE6-E6B9-5F7F-BE8484657D17"))
            case mode: Stoping ⇒ Some(isc info("Останов".ellipsis, "6C2A513E-CEE6-E6B9-5F7F-BE8484657D17"))
            case mode: Stoped ⇒ Some(isc info("Полный останов".ellipsis, "6C2A513E-CEE6-E6B9-5F7F-BE8484657D17"))
            case _ ⇒ None
        }

        isc.MessagingSS.subscribe(appCommon.makeOperationMessage, {
            (messageJS: MessageJS) ⇒
                info.foreach(_.markForDestroy())
            //isc.MessagingSS.unsubscribe(appCommon.makeOperationMessage)
        })

        RPCManagerSS.sendRequest(
            RPCRequest(
                new RPCRequestProps {
                    timeout = 0.opt
                    data = new ScenarioUpdateData {
                        override val id_scenario: JSUndefined[Double] = idScenario
                        override val mode: JSUndefined[String] = _mode.mode
                        override val scenarioTestMode: JSUndefined[Boolean] = testMode
                        override val scenarioProdTestMode: JSUndefined[Boolean] = prodTestMode
                    }.opt
                    actionURL = (simpleSyS.simpleSysContextPath + ScenarioUpdateStatusContainer.scenarios_Scr_Scenario_UpdateStatus).opt
                    callback = {
                        (resp: RPCResponse, data: JSObject, req: RPCRequest) ⇒
                            resp.results.foreach {
                                _.response.foreach {
                                    response ⇒
                                        if (response.status != RPCResponseStatic.STATUS_SUCCESS)
                                            response.data.foreach {
                                                error ⇒ isc error(error.toString, "063B7F9E-576B-7EFA-8F3C-E536055508B4")
                                            }
                                }
                            }
                    }.toFunc.opt
                }
            )
        )
    }

    def seqMenuControlsItemProps(owner: ListGridEditor) = Seq(
        new MenuSSItemProps {
            title = "Подготовить".ellipsis.opt
            identifier = "prepare".opt
            icon = appCommon.ready.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor

                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.ready)

            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1 && StatusScenario.enable2Prepare(owner.getSelectedRecord().status)
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Подготовить в тестовом режиме".ellipsis.opt
            identifier = "prepareTest".opt
            icon = appCommon.ready.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor

                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.readyTest, true)

            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1 && StatusScenario.enable2Prepare(owner.getSelectedRecord().status)
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Подготовить в тестовом режиме как Prod".ellipsis.opt
            identifier = "prepareTestAsProd".opt
            icon = appCommon.ready.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor

                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.readyTestAsProd, true, true)

            }.toFunc.opt
            visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1 && StatusScenario.enable2Prepare(owner.getSelectedRecord().status)
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Запустить".ellipsis.opt
            identifier = "play".opt
            icon = appCommon.start.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor
                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.play)

            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1 && StatusScenario.enable2Start(owner.getSelectedRecord().status)
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Мягкий останов".ellipsis.opt
            identifier = "softStop".opt
            icon = appCommon.closeProgram.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor
                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.stoping)
            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    //owner.getSelectedRecords().length == 1 && StatusScenario.enable2SoftStop(owner.getSelectedRecord().status)
                    false
            }.toFunc.opt
        },
        new MenuSSItemProps {
            title = "Жесткий останов".ellipsis.opt
            identifier = "hardStop".opt
            icon = appCommon.stoped.opt
            click = {
                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner editor

                    makeOperation(editor.getSelectedRecord().id_scenario, StatusScenario.stoped)

            }.toFunc.opt
            enableIf = {
                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                    simpleSyS checkOwner owner
                    owner.getSelectedRecords().length == 1 && StatusScenario.enable2Stop(owner.getSelectedRecord().status)
                //false
            }.toFunc.opt
        }
    ).map { item ⇒ item.owner = owner.opt; item }

    def getCfgCodeViewer(idScenario: JSUndefined[Double], callback: js.Function2[String, String, _]): Unit = {
        idScenario.foreach {
            idScenario ⇒
                val criteria4CfgCode = AdvancedCriteria(
                    new AdvancedCriteriaProps {
                        operator = OperatorId.and.opt
                        criteria = Seq(
                            Criterion(
                                new CriterionProps {
                                    fieldName = scenarios_Scr_ScenarioClobConfig_id_scenario_NameStrong.name.opt
                                    operator = OperatorId.equals.opt
                                    value = idScenario.asInstanceOf[JSAny].opt
                                })
                        ).opt
                    }
                )

                val criteria4CfgError = AdvancedCriteria(
                    new AdvancedCriteriaProps {
                        operator = OperatorId.and.opt
                        criteria = Seq(
                            Criterion(
                                new CriterionProps {
                                    fieldName = scenarios_Scr_ScenarioClobConfigError_id_scenario_NameStrong.name.opt
                                    operator = OperatorId.equals.opt
                                    value = idScenario.asInstanceOf[JSAny].opt
                                })
                        ).opt
                    }
                )

                fetchFromBase(DataSourcesJS.scenarios_Scr_ScenarioClobConfig_DS, criteria4CfgCode, scenarios_Scr_ScenarioClobConfig_clobConfig_NameStrong.name, {
                    (cfgCode: String) ⇒
                        fetchFromBase(DataSourcesJS.scenarios_Scr_ScenarioClobConfigError_DS, criteria4CfgError, scenarios_Scr_ScenarioClobConfigError_clobConfigError_NameStrong.name, {
                            (cfgError: String) ⇒
                                callback(cfgCode, cfgError)
                        })
                })
        }
    }

    def getExeCodeViewer(idScenario: JSUndefined[Double], callback: js.Function2[String, String, _]): Unit = {
        idScenario.foreach {
            idScenario ⇒
                val criteria4CfgCode = AdvancedCriteria(
                    new AdvancedCriteriaProps {
                        operator = OperatorId.and.opt
                        criteria = Seq(
                            Criterion(
                                new CriterionProps {
                                    fieldName = scenarios_Scr_ScenarioExeCode_id_scenario_NameStrong.name.opt
                                    operator = OperatorId.equals.opt
                                    value = idScenario.asInstanceOf[JSAny].opt
                                })
                        ).opt
                    }
                )

                val criteria4CfgError = AdvancedCriteria(
                    new AdvancedCriteriaProps {
                        operator = OperatorId.and.opt
                        criteria = Seq(
                            Criterion(
                                new CriterionProps {
                                    fieldName = scenarios_Scr_ScenarioExeCodeError_id_scenario_NameStrong.name.opt
                                    operator = OperatorId.equals.opt
                                    value = idScenario.asInstanceOf[JSAny].opt
                                })
                        ).opt
                    }
                )

                fetchFromBase(DataSourcesJS.scenarios_Scr_ScenarioExeCode_DS, criteria4CfgCode, scenarios_Scr_ScenarioExeCode_exeCode_NameStrong.name, {
                    (cfgCode: String) ⇒
                        fetchFromBase(DataSourcesJS.scenarios_Scr_ScenarioExeCodeError_DS, criteria4CfgError, scenarios_Scr_ScenarioExeCodeError_exeCodeError_NameStrong.name, {
                            (cfgError: String) ⇒
                                callback(cfgCode, cfgError)
                        })
                })
        }
    }

    def fetchFromBase(dataSource: RestDataSourceSS, criteria: AdvancedCriteria, lobFieldName: String, _callback: js.Function1[String, _]): Unit =
        dataSource.fetchData(
            criteria = criteria,
            callback = {
                (resp: DSResponse, data: JSObject, req: DSRequest) ⇒
                    val errorStruct = resp.errorStruct

                    //isc debugTrap resp

                    if (errorStruct.isEmpty && resp.status == RPCResponseStatic.STATUS_SUCCESS) {
                        if (isc.isA.Array(data)) {
                            val res = data.asInstanceOf[IscArray[Scr_ScenarioClobConfigDataRecordExt]]

                            //isc debugTrap res

                            if (res.length > 0) {
                                if (res.head.error.isDefined) {
                                    if (res.head.error.get.stackTrace.isDefined)
                                        isc.errorDetail(res.head.error.get.message.getOrElse("Ошибка не определена."), res.head.error.get.stackTrace.get)
                                    else
                                        isc error res.head.error.get.message.getOrElse("Ошибка не определена.")
                                }
                                //isc debugTrap (res, lobFieldName)
                                _callback(res.head.asInstanceOf[JSDynamic].selectDynamic(lobFieldName).toString)
                            }
                            else
                                _callback("")
                        }
                    } else {
                        if (errorStruct.get.stackTrace.isDefined)
                            isc.errorDetail(errorStruct.get.errorMessage.getOrElse("Ошибка не определена."), errorStruct.get.stackTrace.get)
                        else
                            isc error errorStruct.get.errorMessage.getOrElse("Ошибка не определена.")
                    }
            }
        )

    def getViewWindow(_title: String, selectedRecord: Scr_ScenarioDataRecord, icon: SCImgURL, code: String, error: String): Unit = {
        val _identifier = s"D529567A-C367-3198-E36E-CAEA02368AE9_${_title}"
        val _identifier1 = s"D529567A-C367-3198-E36E-CAEA02368AE9_${selectedRecord.id_scenario}_${_title}"

        windows.find(_.identifier == _identifier1).foreach(_.close())

        windows add WindowSS.create(
            new WindowSSProps {
                identifier = _identifier.opt
                identifier1 = _identifier.opt
                title = s"${_title}: (${selectedRecord.caption_cmpgn}) ${selectedRecord.code_scenario}: ${selectedRecord.caption_scenario}".ellipsis.opt
                headerIconPath = icon.opt
                height = 500
                width = 600
                canDragResize = true.opt
                canDragReposition = true.opt
                keepInParentRect = true.opt
                autoCenter = false.opt
                autoSize = false.opt
                isModal = false.opt
                autoDestroy = false.opt
                showMinimizeButton = true.opt
                showMaximizeButton = true.opt
                items = Seq(
                    TabSetSS.create(
                        new TabSetSSProps {
                            identifier = s"${_identifier}TabSet".opt
                            canCloseTabs = false.opt
                            tabs = Seq(
                                Tab(
                                    new TabProps {
                                        title = "Код".ellipsis.opt
                                        icon = appCommon.cards.opt
                                        pane = RichTextEditor.create(
                                            new RichTextEditorProps {
                                                autoDraw = false.opt
                                                height = "100%"
                                                width = "100%"
                                                controlGroups = Seq().opt
                                                value = s"<plaintext>$code".opt
                                            }
                                        ).opt
                                    }
                                ),
                                Tab(
                                    new TabProps {
                                        title = "Выдача компилятора".ellipsis.opt
                                        icon = appCommon.docitem.opt
                                        pane = RichTextEditor.create(
                                            new RichTextEditorProps {
                                                autoDraw = false.opt
                                                height = "100%"
                                                width = "100%"
                                                controlGroups = Seq().opt
                                                value = s"<plaintext>$error".opt
                                            }
                                        ).opt
                                    }
                                )
                            ).opt
                        }
                    )
                ).opt
                initWidget = {
                    (thiz: classHandler, args: IscArray[JSAny]) ⇒
                        thiz.Super("initWidget", args)
                        thiz.autoDestroy.foreach {
                            autoDestroy ⇒
                                if (autoDestroy)
                                    thiz.observe(thiz, "close", () ⇒ thiz.markForDestroy())
                        }
                }.toThisFunc.opt
            }
        )
    }


    initWidget = {
        (thisTop: classHandler, arguments: IscArray[JSAny]) =>
            val staticticVisible = false

            thisTop.customMenuList = IscArray(
                IscArray(
                    MenuSSItem(
                        new MenuSSItemProps {
                            title = "Конструктор".ellipsis.opt
                            identifier = "constructor".opt
                            icon = appCommon.iconConstructor.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                    simpleSyS checkOwner editor

                                    if (editor.owner.isEmpty || editor.owner.get.tabSet.isEmpty)
                                        isc error s"Не найден TabSet у ${editor.getIdentifier()}"
                                    else {
                                        //isc debugTrap editor.owner
                                        val tabSet = editor.owner.get.tabSet.get
                                        val selectedRecord = editor.getSelectedRecord()

                                        val tabName = s"Tab_${selectedRecord.id_scenario}"

                                        tabSet.findTab(tabName).toOption match {
                                            case None ⇒
                                            case Some(tab) ⇒
                                                val _tab = tab.asInstanceOf[TabRTM]
                                                if (_tab.tabType.toString == TabTypes.WithOutGraph.toString)
                                                    tabSet removeTab tab
                                        }

                                        tabSet.findTab(tabName).toOption match {
                                            case None ⇒
                                                val _title = s"${CanvasStatic.imgHTML(item.icon, 16, 14)} ${s"${selectedRecord.caption_scenario} (${selectedRecord.code_scenario})"}"

                                                val constructorFormBase: ConstructorFormBase = new ConstructorFormBase {
                                                    override protected val identifier: String = s"013B9E03-FACE-0E4E-F790-AEE3EEA6B6F3_${selectedRecord.id_scenario}"
                                                    override protected val idScenario: JSUndefined[Double] = selectedRecord.id_scenario
                                                    override protected val codeCmpgn: JSUndefined[String] = selectedRecord.code_cmpgn
                                                    override protected val codeScenario: JSUndefined[String] = selectedRecord.code_scenario
                                                    override protected val captionScenario: JSUndefined[String] = selectedRecord.caption_scenario
                                                }

                                                val tab = TabRTM(
                                                    new TabRTMProps {
                                                        tabType = TabTypes.WithGraph.opt
                                                        pane = constructorFormBase.get.opt
                                                        name = tabName.opt
                                                        title = _title.opt
                                                        tabSelected = {

                                                            (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].refresh())
                                                                //isc debugTrap (tab.pane, thiz.functionButton)
                                                                tab.pane.foreach(_.funcMenu.foreach(menu ⇒ thisTop.functionButton.foreach(button ⇒ button.menu = menu)))
                                                        }.toFunc.opt
                                                        tabDeselected = {
                                                            (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, newTab: Tab, name: JSUndefined[String]) ⇒
                                                                //isc debugTrap (tab.pane, tab.pane.foreach(_.asInstanceOf[ConstructorForm].propertyEditorRef()))
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].propertyEditorRef().foreach(_.markForDestroy()))
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].historyEditorRef().foreach(_.markForDestroy()))
                                                                true
                                                        }.toFunc.opt
                                                    }
                                                )

                                                tabSet addTab tab
                                                tabSet selectTab tab
                                                tabSet.getSelectedTab().foreach {
                                                    _.pane.foreach {
                                                        pane ⇒
                                                            selectedRecord.id_scenario.foreach(pane.asInstanceOf[ConstructorForm].recoverGraphFromBase(_))
                                                    }
                                                }

                                            case Some(tab) ⇒
                                                tabSet selectTab tab
                                        }
                                    }
                            }.toFunc.opt
                            enableIf = {
                                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                    simpleSyS checkOwner owner
                                    owner.getSelectedRecords().length == 1
                            }.toFunc.opt
                        }
                    ),
                    MenuSSItem(
                        new MenuSSItemProps {
                            title = "Конструктор без загрузки графа".ellipsis.opt
                            identifier = "constructor_without_graph".opt
                            icon = appCommon.iconConstructor.opt
                            click = {
                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                    val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                    simpleSyS checkOwner editor

                                    if (editor.owner.isEmpty || editor.owner.get.tabSet.isEmpty)
                                        isc error s"Не найден TabSet у ${editor.getIdentifier()}"
                                    else {
                                        //isc debugTrap editor.owner
                                        val tabSet = editor.owner.get.tabSet.get
                                        val selectedRecord = editor.getSelectedRecord()

                                        val tabName = s"Tab_${selectedRecord.id_scenario}"

                                        tabSet.findTab(tabName).toOption match {
                                            case None ⇒
                                            case Some(tab) ⇒
                                                val _tab = tab.asInstanceOf[TabRTM]
                                                if (_tab.tabType.toString == TabTypes.WithGraph.toString)
                                                    tabSet removeTab tab
                                        }

                                        tabSet.findTab(tabName).toOption match {
                                            case None ⇒
                                                val _title = s"${CanvasStatic.imgHTML(item.icon, 16, 14)} ${s"${selectedRecord.caption_scenario} (${selectedRecord.code_scenario})"} (*)"

                                                val constructorFormBase: ConstructorFormBase = new ConstructorFormBase {
                                                    override protected val identifier: String = s"013B9E03-FACE-0E4E-F790-AEE3EEA6B6F3_${selectedRecord.id_scenario}"
                                                    override protected val idScenario: JSUndefined[Double] = selectedRecord.id_scenario
                                                    override protected val codeCmpgn: JSUndefined[String] = selectedRecord.code_cmpgn
                                                    override protected val codeScenario: JSUndefined[String] = selectedRecord.code_scenario
                                                    override protected val captionScenario: JSUndefined[String] = selectedRecord.caption_scenario
                                                }

                                                val tab = TabRTM(
                                                    new TabRTMProps {
                                                        tabType = TabTypes.WithOutGraph.opt
                                                        pane = constructorFormBase.get.opt
                                                        name = tabName.opt
                                                        title = _title.opt
                                                        tabSelected = {

                                                            (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].refresh())
                                                                //isc debugTrap (tab.pane, thiz.functionButton)
                                                                tab.pane.foreach(_.funcMenu.foreach(menu ⇒ thisTop.functionButton.foreach(button ⇒ button.menu = menu)))
                                                        }.toFunc.opt
                                                        tabDeselected = {
                                                            (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, newTab: Tab, name: JSUndefined[String]) ⇒
                                                                //isc debugTrap (tab.pane, tab.pane.foreach(_.asInstanceOf[ConstructorForm].propertyEditorRef()))
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].propertyEditorRef().foreach(_.markForDestroy()))
                                                                tab.pane.foreach(_.asInstanceOf[ConstructorForm].historyEditorRef().foreach(_.markForDestroy()))
                                                                true
                                                        }.toFunc.opt
                                                    }
                                                )

                                                tabSet addTab tab
                                                tabSet selectTab tab

                                                tabSet.getSelectedTab().foreach(_.pane.foreach(_.asInstanceOf[ConstructorForm].refresh()))

                                            case Some(tab) ⇒
                                                tabSet selectTab tab
                                        }
                                    }

                            }.toFunc.opt
                            enableIf = {
                                (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                    val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                    simpleSyS checkOwner owner
                                    owner.getSelectedRecords().length == 1
                            }.toFunc.opt
                            visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                        }),
                    MenuSSItem(new MenuSSItemProps {
                        isSeparator = true.opt
                    }),
                    MenuSSItem(new MenuSSItemProps {
                        title = "Статистика".ellipsis.opt
                        identifier = "statistic".opt
                        icon = appCommon.iconStatistic.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner editor

                                if (editor.owner.isEmpty || editor.owner.get.tabSet.isEmpty)
                                    isc error s"Не найден TabSet у ${editor.getIdentifier()}"
                                else {
                                    //isc debugTrap editor.owner
                                    val tabSet = editor.owner.get.tabSet.get
                                    val selectedRecord = editor.getSelectedRecord()

                                    val tabName = s"Tab_St${selectedRecord.id_scenario}"
                                    tabSet.findTab(tabName).toOption match {
                                        case None ⇒
                                            val _title = s"${CanvasStatic.imgHTML(item.icon, 16, 14)} ${s"Статистика: ${selectedRecord.caption_scenario} (${selectedRecord.code_scenario})"}"


                                            val tab = Tab(
                                                new TabProps {
                                                    pane = ScenarioStatistics.create(
                                                        new ScenarioStatisticsProps {

                                                        }
                                                    ).opt
                                                    name = tabName.opt
                                                    title = _title.opt
                                                    tabSelected = {
                                                        (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                                                            //isc debugTrap (tab.pane, thiz.functionButton)
                                                            tab.pane.foreach(_.funcMenu.foreach(menu ⇒ thisTop.functionButton.foreach(button ⇒ button.menu = menu)))
                                                    }.toFunc.opt
                                                    tabDeselected = {
                                                        (tabSet: TabSetSS, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, newTab: Tab, name: JSUndefined[String]) ⇒
                                                            //isc debugTrap (tab.pane, tab.pane.foreach(_.asInstanceOf[ConstructorForm].propertyEditorRef()))
                                                            true
                                                    }.toFunc.opt
                                                }
                                            )

                                            tabSet addTab tab
                                            tabSet selectTab tab
                                            tabSet.getSelectedTab().foreach {
                                                _.pane.foreach {
                                                    pane ⇒
                                                }
                                            }

                                        case Some(tab) ⇒
                                            tabSet selectTab tab
                                    }
                                }
                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner owner
                                owner.getSelectedRecords().length == 1
                        }.toFunc.opt
                        visibilityIf = {
                            () ⇒ staticticVisible
                        }.toFunc.opt
                    }),
                    MenuSSItem(new MenuSSItemProps {
                        isSeparator = true.opt
                        visibilityIf = {
                            () ⇒ staticticVisible
                        }.toFunc.opt
                    }),
                    MenuSSItem(new MenuSSItemProps {
                        title = "Просмотреть конфигурационный код".ellipsis.opt
                        identifier = "view_cfg".opt
                        icon = appCommon.card.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner editor
                                val selectedRecord: Scr_ScenarioDataRecord = editor.getSelectedRecord()
                                getCfgCodeViewer(selectedRecord.id_scenario, {
                                    (code: String, error: String) ⇒
                                        getViewWindow("Config", selectedRecord, item.icon, code, error)
                                })

                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner owner
                                owner.getSelectedRecords().length == 1
                        }.toFunc.opt
                        visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                    }),
                    MenuSSItem(new MenuSSItemProps {
                        title = "Просмотреть исполняемый код".ellipsis.opt
                        identifier = "view_exe".opt
                        icon = appCommon.data.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                val editor = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner editor

                                val selectedRecord: Scr_ScenarioDataRecord = editor.getSelectedRecord()
                                getExeCodeViewer(selectedRecord.id_scenario, {
                                    (code: String, error: String) ⇒
                                        getViewWindow("Exe", selectedRecord, item.icon, code, error)
                                })

                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                val owner = item.owner.asInstanceOf[GridEditor[ListGridField, Scr_ScenarioDataRecord, ListGridSelectedState]]
                                simpleSyS checkOwner owner
                                owner.getSelectedRecords().length == 1
                        }.toFunc.opt
                        visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                    }),
                    MenuSSItem(
                        new MenuSSItemProps {
                            isSeparator = true.opt
                            visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                        })
                ) ++ seqMenuControlsItemProps(thisTop.listGrid).map(MenuSSItem(_)): _*
            )

            thisTop.Super("initWidget", arguments)

            isc.MessagingSS.subscribe(appCommon.refteshScenarioGridMessage, {
                (messageJS: MessageJS) ⇒
                    //isc debugTrap messageJS
                    messageJS.data.foreach(_.asInstanceOf[RefreshScenarioGridResult].error.foreach {
                        error ⇒
                            if (!error.isEmpty)
                                isc.error(error)
                    })
                    thisTop.refreshDataList()
            })

            thisTop.observe(thisTop, "destroy", {
                () ⇒
                    windows.foreach(_.close())
                    isc.MessagingSS unsubscribe appCommon.refteshScenarioGridMessage

            })

    }.toThisFunc.opt

    showListRecordComponents = true.opt
    showListRecordComponentsByCell = true.opt
    recordListComponentPoolingMode = RecordComponentPoolingMode.recycle.opt

    def getButtonProps(record: Scr_ScenarioDataRecord, listGridEditor: ListGridEditor) = new IButtonSSProps {
        height = 20
        width = 130
        layoutAlign = Alignment.center
        title = StatusScenario.getStatus(record.status).title.opt
        prompt = StatusScenario.getStatus(record.status).prompt.opt
        icon = StatusScenario.getStatus(record.status).icon.opt
        click = {
            (thiz: classHandler) ⇒
                listGridEditor.deselectAllRecords()
                listGridEditor selectRecord record.asInstanceOf[ListGridRecord]

                MenuSS.create(
                    new MenuSSProps {
                        items = seqMenuControlsItemProps(listGridEditor).opt
                    }
                ).showContextMenu()
                true
        }.toThisFunc.opt
    }

    createListRecordComponent = {
        (thiz: ListGridEditor, record: ListGridRecord, colNum: Int) =>
            //isc debugTrap thiz.listGrid
            val fieldName = thiz.listGrid.getFieldName(colNum)
            fieldName match {
                case x if x == scenarios_Scr_Scenario_status_NameStrong.name ⇒
                    val _record = record.asInstanceOf[Scr_ScenarioDataRecord]
                    IButtonSS.create(getButtonProps(_record, thiz)).asInstanceOf[Canvas].undef
                case _ ⇒
                    jSUndefined
            }
    }.toThisFunc.opt

    updateListRecordComponent = {
        (thiz: ListGridEditor, record: ListGridRecord, colNum: Int, component: Canvas, recordChanged: Boolean) ⇒
            val fieldName = thiz.listGrid.getFieldName(colNum)
            fieldName match {
                case x if x == scenarios_Scr_Scenario_status_NameStrong.name ⇒
                    val _record = record.asInstanceOf[Scr_ScenarioDataRecord]
                    component.addProperties(IButtonSS(getButtonProps(_record, thiz))).undef
                case _ ⇒
                    jSUndefined
            }
    }.toThisFunc.opt
}
