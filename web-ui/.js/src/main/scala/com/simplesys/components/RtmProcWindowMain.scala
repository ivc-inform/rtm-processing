package com.simplesys.components

import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.App.{SettingsEditor, WebTabSetApp}
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.MenuSSProps
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.DataBinding.RestDataSourceSS
import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Forms.formsItems.props.{SpinnerItemProps, TextAreaItemSSProps}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.HTMLPaneProps
import com.simplesys.SmartClient.Grids.listGrid.ListGridRecord
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{RibbonGroupSS, TabSetSS}
import com.simplesys.SmartClient.RPC.props.RPCRequestProps
import com.simplesys.SmartClient.RPC.{RPCManagerSS, RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.App._
import com.simplesys.appCommon._
import com.simplesys.appCommon.common._
import com.simplesys.components.props._
import com.simplesys.function._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.scenariosShared.{PersistenceJournalMessageContainer_Shared, PersistenceJournal_DebugMessageContainer_Shared}

import scala.scalajs.js.ThisFunction1
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel, ScalaJSDefined}

trait pkKey extends JSObject {
    val persistence_id: String
    val sequence_number: Long
}

@JSExportTopLevel("RtmProcWindowMain")
class RtmProcWindowMain extends WebTabSetApp {
    self ⇒


    override protected val loadSchemas = com.simplesys.appCommon.common.loadSchemas

    override protected val identifier = "5814FE1C-252A-01C4-11A1-557FA9992D3F"
    override protected val appImageDir = "images/"

    override protected val dataSourcesJS_admin_UserGroup_DS: RestDataSourceSS = DataSourcesJS.admin_UserGroup_DS
    override protected val dataSourcesJS_admin_User_DS: RestDataSourceSS = DataSourcesJS.admin_User_DS

    override protected val listGridFiledsJS_admin_UserGroup_FLDS: Seq[ListGridFieldProps] = ListGridFiledsJS.admin_UserGroup_FLDS
    override protected val listGridFiledsJS_admin_User_FLDS: Seq[ListGridFieldProps] = ListGridFiledsJS.admin_User_FLDS

    override protected val formItemsJS_admin_UserGroup_FRMITM: Seq[FormItem] = FormItemsJS.admin_UserGroup_FRMITM
    override protected val formItemsJS_admin_User_FRMITM: Seq[FormItem] = FormItemsJS.admin_User_FRMITM

    override protected val admin_User_codeGroup_NameStrong: NameStrong = admin_User_codeGroup_Group_NameStrong
    override protected val admin_User_captionGroup_NameStrong: NameStrong = admin_User_captionGroup_Group_NameStrong

    override protected def getSettingsEditor: SettingsEditor = SettingsEditor.create(
        new SettingsEditorProps {
            identifier = self.identifier.opt
            action = s"${simpleSyS.simpleSysContextPath}logic/ChangePassword".opt
            customSettingItems = Seq(
                SpinnerItem(
                    new SpinnerItemProps {
                        disabled = true.opt
                        title = "Количество копий графа состояний".opt
                        defaultValue = (if (simpleSyS.qtyGraphCopies.isEmpty) 100 else simpleSyS.qtyGraphCopies get).asInstanceOf[JSAny].opt
                        min = 0.0.opt
                        step = 1.0.opt
                        changed = {
                            import com.simplesys.SmartClient.Forms.DynamicFormSS
                            (form: DynamicFormSS, formItem: FormItem, value: JSUndefined[Int]) ⇒
                                simpleSyS.qtyGraphCopies = value
                        }.toFunc.opt
                    }
                )
            ).opt
        }
    )

    def beforeRemoveConstructorFormTabs(thiz: TabSetSS, tab: Tab): void = {
        tab.pane.foreach {
            pane ⇒
                if (pane.getClassName() == ConstructorForm.getClass.getSimpleName) {
                    val constructorForm = pane.asInstanceOf[ConstructorForm]
                    if (constructorForm.getSaveState())
                        isc.ask(s"Не сохраненный граф: ${constructorForm.captionScenario.getOrElse("Unknown")} (${constructorForm.codeScenario.getOrElse("Unknown")}) Сохранить ?",
                            (v: Boolean) ⇒
                                if (v) constructorForm.updateInBase()
                        )
                }
        }
    }

    simpleSyS.scenarioTestMode = isc.OfflineSS.getBoolean(s"ScenarioTestMode$identifier", false)

    override val beforeRemoveTabs: ScOption[ThisFunction1[TabSetSS, Tab, _]] = (beforeRemoveConstructorFormTabs _).toThisFunc.opt

    override protected val managedUsersGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Настройка".ellipsis.opt
                            icon = Common.Workflow.opt
                            identifier = "32571839-8D4D-FFA0-E491-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "campaign".opt
                                            icon = doccats.opt
                                            title = "Кампании".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ScenarioCompain.create(new ScenarioCompainProps {
                                                        functionButton = self.functionButton.opt
                                                    }), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "lists".opt
                                            icon = lists.opt
                                            title = "Списки".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                                                    val listEditor = ListEditor.create(
                                                        new ListEditorProps {
                                                            showResizeBar = true.opt
                                                            width = "80%"
                                                            selectFirstRecordAfterFetchList = true.opt
                                                        }
                                                    )

                                                    val listItems = CommonListGridEditorComponent.create(
                                                        new CommonListGridEditorComponentProps {

                                                            selectionType = SelectionStyle.multiple.opt
                                                            width = "20%"
                                                            dataSource = DataSourcesJS.scenarios_ListElements_DS.opt
                                                            selectionAppearance = SelectionAppearance.checkbox.opt
                                                            autoFetchData = false.opt
                                                            fields = ListGridFiledsJS.scenarios_ListElements_FLDS.opt
                                                            editingFields = FormItemsJS.scenarios_ListElements_FRMITM.opt
                                                            masterGrid = listEditor.listGrid.listGrid.opt
                                                            itemsType = Seq(miDelete(), miEdit(), miRefresh()).opt
                                                            customMenu = Seq(
                                                                MenuSSItem(
                                                                    new MenuSSItemProps {
                                                                        title = "Новые".ellipsis.opt
                                                                        identifier = "loadlines".opt
                                                                        icon = Common.insert.opt
                                                                        click = {
                                                                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                                                import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps

                                                                                val form = DynamicFormSS.create(
                                                                                    new DynamicFormSSProps {

                                                                                        import com.simplesys.System._

                                                                                        numCols = 1.opt
                                                                                        height = "*"
                                                                                        colWidths = Seq[JSAny]("*").opt
                                                                                        fields = Seq(
                                                                                            TextAreaItemSS(
                                                                                                new TextAreaItemSSProps {
                                                                                                    nameStrong = "data".nameStrongOpt
                                                                                                    showTitle = false.opt
                                                                                                    width = "100%"
                                                                                                    height = "100%"
                                                                                                }
                                                                                            )
                                                                                        ).opt
                                                                                    }
                                                                                )
                                                                                WindowSSDialog.create(
                                                                                    new WindowSSDialogProps {
                                                                                        isModal = true.opt
                                                                                        title = item.title.ellipsis.opt
                                                                                        identifier = "0C833899-DA13-B519-DCBF-7EDB94C5D5DA".opt
                                                                                        headerIconPath = Common.insert.opt
                                                                                        wrapCanvas = form.opt
                                                                                        okFunction = {
                                                                                            (thiz: classHandler) ⇒
                                                                                                val field = form.getField("data")
                                                                                                if (field.isDefined) {
                                                                                                    import com.simplesys.System._

                                                                                                    trait RecordElement extends JSObject {
                                                                                                        val element_list: String
                                                                                                        val id_list: Int
                                                                                                        val code_list: String
                                                                                                    }

                                                                                                    trait RecordListEditor extends JSObject {
                                                                                                        val id_list: Int
                                                                                                        val code_list: String
                                                                                                    }

                                                                                                    val records: IscArray[Record] = IscArray(field.get.getValue().toString.split("\n").map {
                                                                                                        item ⇒
                                                                                                            new RecordElement {
                                                                                                                override val element_list: String = item
                                                                                                                override val id_list: Int = listEditor.getListSelectedRecord().asInstanceOf[RecordElement].id_list
                                                                                                                override val code_list: String = listEditor.getListSelectedRecord().asInstanceOf[RecordElement].code_list
                                                                                                            }.asInstanceOf[Record]
                                                                                                    }.toSet.toSeq: _*)

                                                                                                    //isc debugTrap progressBar
                                                                                                    DataSourcesJS.scenarios_ListElements_DS.addDatas(
                                                                                                        newRecords = records,
                                                                                                        callback = {
                                                                                                            (response: IscArray[RPCResponse]) ⇒

                                                                                                                val res = response.filter {
                                                                                                                    item ⇒
                                                                                                                        import com.simplesys.SmartClient.DataBinding.ResponseData
                                                                                                                        if (item.data.isDefined) {
                                                                                                                            val status = item.data.get.asInstanceOf[ResponseData].status
                                                                                                                            status.isDefined && status.get < 0
                                                                                                                        }
                                                                                                                        else
                                                                                                                            false
                                                                                                                }

                                                                                                                //isc debugTrap res
                                                                                                                if (res.length == 0)
                                                                                                                    thiz.markForDestroy()
                                                                                                        })
                                                                                                } else
                                                                                                    thiz.owner.foreach(_.markForDestroy())


                                                                                        }.toThisFunc.opt
                                                                                    }
                                                                                )
                                                                        }.toFunc.opt
                                                                        enableIf = {
                                                                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                                                                listEditor.listGrid.getSelectedRecords().length > 0
                                                                        }.toFunc.opt
                                                                    })
                                                            ).opt
                                                            replacingFields = Seq(
                                                                new ListGridFieldProps {

                                                                    import ru.simplesys.defs.app.gen.scala.ScalaJSGen.scenarios_ListElements_element_list_NameStrong

                                                                    nameStrong = scenarios_ListElements_element_list_NameStrong.opt
                                                                    editorType = FormItemComponentType.TextItem
                                                                    formatCellValue = {
                                                                        import com.simplesys.SmartClient.Grids.ListGrid
                                                                        import com.simplesys.System.JSAny
                                                                        (value: JSAny, record: ListGridRecord, rowNum: Int, colNum: Int, grid: ListGrid) ⇒
                                                                            //todo Доделать Формат телеофна +7 (ххх) ххх-ххх-хх
                                                                            value.toString
                                                                    }.toFunc.opt
                                                                },
                                                                new ListGridFieldProps {
                                                                    nameStrong = scenarios_ListElements_code_list_Id_list_NameStrong.opt
                                                                    hidden = true.opt
                                                                }
                                                            ).opt
                                                        }
                                                    )

                                                    //isc debugTrap listEditor

                                                    val res = ChainMasterDetail.create(
                                                        new ChainMasterDetailProps {
                                                            identifier = "F9B26DDD-F880-3609-9875-6BE2E4568BBF".opt
                                                            width = "100%"
                                                            members = Seq(
                                                                listEditor,
                                                                listItems
                                                            ).opt
                                                        }
                                                    )

                                                    listEditor.funcMenu.foreach {
                                                        listEditorFuncMenu ⇒
                                                            listItems.funcMenu.foreach {
                                                                listItemsFuncMenu ⇒
                                                                    listEditorFuncMenu addItem MenuSSItem(
                                                                        new MenuSSItemProps {
                                                                            submenu = listItemsFuncMenu.opt
                                                                            title = "Элементы справочника".ellipsis.opt
                                                                            icon = Common.ellipsis.opt
                                                                        }
                                                                    )
                                                            }

                                                            res setFuncMenu listEditorFuncMenu
                                                    }
                                                    res.getViewState()

                                                    addTab(res, item)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Тестирование".ellipsis.opt
                            icon = scenarioTets.opt
                            identifier = "33EE1839-8D4D-FFA0-E491-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "tests".opt
                                            icon = zapros.opt
                                            title = "Тесты".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    val scenarioTests = ScenarioTests.create(new ScenarioTestsProps)
                                                    addTab(scenarioTests, item, {
                                                        () ⇒ scenarioTests.loggingViewer.foreach(_.closeAllWindow())
                                                            true
                                                    }.toFunc)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        ),
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Пользователи".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Журналы результатов".ellipsis.opt
                            icon = scenarioTrace.opt
                            identifier = "33EE1839-8D4D-FFA0-2291-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "log".opt
                                            icon = docizv.opt
                                            title = "Движение сценариев".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ScenarioTrace.create(new ScenarioTraceProps), item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "log-tests".opt
                                            icon = docizv.opt
                                            title = "Движение сценариев (test)".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ScenarioTraceTest.create(new ScenarioTraceTestProps), item)
                                            }.toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }

    override protected val progectManagedDevsGroups: Seq[RibbonGroupSS] = Seq(
        RibbonGroupSS.create(
            new RibbonGroupSSProps {
                title = "Разработчики".ellipsis.opt
                controls = Seq(
                    IconMenuButtonSS.create(
                        new IconMenuButtonSSProps {
                            title = "Журналы результатов".ellipsis.opt
                            icon = lists.opt
                            identifier = "33EE1839-8D4D-FFA0-2291-22B54F55772A".opt
                            menu = MenuSS.create(
                                new MenuSSProps {
                                    items = Seq(
                                        new MenuSSItemProps {
                                            name = "persistance_event".opt
                                            icon = common.state.opt
                                            title = "Событийная информация".ellipsis.opt
                                            //visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    val jsonField = HTMLPane.create(
                                                        new HTMLPaneProps {
                                                            width = "20%"
                                                            padding = 2.opt
                                                            overflow = Overflow.auto.opt
                                                            showEdges = true.opt
                                                        }
                                                    )
                                                    val persistenceJournalEditor = PersistenceJournalView.create(
                                                        new PersistenceJournalViewProps {
                                                            showResizeBar = true.opt
                                                            width = "80%"
                                                        }
                                                    )

                                                    persistenceJournalEditor.setSelectionUpdated(
                                                        {
                                                            (selectedRecord: ListGridRecord, selectedRecords: IscArray[ListGridRecord]) =>
                                                                if (selectedRecords.length != 1)
                                                                    jsonField.clean()
                                                                else {
                                                                    RPCManagerSS.sendRequest(
                                                                        RPCRequest(
                                                                            new RPCRequestProps {
                                                                                sendNoQueue = true.opt
                                                                                timeout = 60000.opt
                                                                                actionURL = s"${simpleSyS.simpleSysContextPath}${PersistenceJournalMessageContainer_Shared.scenarios_PersistenceJournalMessage_Fetch}".opt
                                                                                data = selectedRecord.opt
                                                                                callback = {
                                                                                    (response: RPCResponse, data: JSUndefined[JSObject], request: RPCRequest) =>
                                                                                        data.foreach {
                                                                                            data ⇒
                                                                                                val _data = isc.JSON.decode(data.toString).asInstanceOf[JSDynamic]
                                                                                                jsonField setContents s"<pre><code>${_data.response.data.toString}</code></pre>"
                                                                                        }
                                                                                }.toFunc.opt
                                                                            }
                                                                        ),
                                                                        false
                                                                    )
                                                                }
                                                        }
                                                    )

                                                    val res = ChainMasterDetail.create(
                                                        new ChainMasterDetailProps {
                                                            identifier = persistenceJournalEditor.identifier.opt
                                                            width = "100%"
                                                            members = Seq(
                                                                persistenceJournalEditor,
                                                                jsonField
                                                            ).opt
                                                        }
                                                    )

                                                    persistenceJournalEditor.funcMenu.foreach(res setFuncMenu _)
                                                    res.getViewState()

                                                    addTab(res, item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "persistance_event_tests".opt
                                            icon = common.state.opt
                                            title = "Событийная информация (test)".ellipsis.opt
                                            //visibilityIf = (() ⇒ LoggedGroup.isDevsGroup.toFunc.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    val jsonField = HTMLPane.create(
                                                        new HTMLPaneProps {
                                                            width = "20%"
                                                            padding = 2.opt
                                                            overflow = Overflow.auto.opt
                                                            showEdges = true.opt
                                                        }
                                                    )
                                                    val persistenceJournalEditor = PersistenceJournalViewTest.create(
                                                        new PersistenceJournalViewTestProps {
                                                            showResizeBar = true.opt
                                                            width = "80%"
                                                        }
                                                    )

                                                    persistenceJournalEditor.setSelectionUpdated(
                                                        {
                                                            (selectedRecord: ListGridRecord, selectedRecords: IscArray[ListGridRecord]) =>
                                                                if (selectedRecords.length != 1)
                                                                    jsonField.clean()
                                                                else {
                                                                    RPCManagerSS.sendRequest(
                                                                        RPCRequest(
                                                                            new RPCRequestProps {
                                                                                sendNoQueue = true.opt
                                                                                timeout = 60000.opt
                                                                                actionURL = s"${simpleSyS.simpleSysContextPath}${PersistenceJournal_DebugMessageContainer_Shared.scenarios_PersistenceJournal_DebugMessage_Fetch}".opt
                                                                                data = selectedRecord.opt
                                                                                callback = {
                                                                                    (response: RPCResponse, data: JSUndefined[JSObject], request: RPCRequest) =>
                                                                                        data.foreach {
                                                                                            data ⇒
                                                                                                val _data = isc.JSON.decode(data.toString).asInstanceOf[JSDynamic]
                                                                                                jsonField setContents s"<pre><code>${_data.response.data.toString}</code></pre>"
                                                                                        }

                                                                                }.toFunc.opt
                                                                            }
                                                                        ),
                                                                        false
                                                                    )
                                                                }
                                                        }
                                                    )

                                                    val res = ChainMasterDetail.create(
                                                        new ChainMasterDetailProps {
                                                            identifier = persistenceJournalEditor.identifier.opt
                                                            width = "100%"
                                                            members = Seq(
                                                                persistenceJournalEditor,
                                                                jsonField
                                                            ).opt
                                                        }
                                                    )

                                                    persistenceJournalEditor.funcMenu.foreach(res setFuncMenu _)
                                                    res.getViewState()

                                                    addTab(res, item)
                                            }.toFunc.opt
                                        },
                                        new MenuSSItemProps {
                                            name = "comData".opt
                                            icon = docizv.opt
                                            title = "Входящие СМС".ellipsis.opt
                                            click = {
                                                (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                                    addTab(ComData.create(new ComDataProps), item)
                                            }.toFunc.opt
                                            //visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                                        }
                                    ).opt
                                }
                            ).opt
                        }
                    )
                ).opt
            }
        )
    ).map {
        item =>
            item.hide()
            item
    }
}
