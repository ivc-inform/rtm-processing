package com.simplesys.js.components.props

import com.simplesys.SmartClient.App.LoggedGroup
import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Control.menu.MenuSSItem
import com.simplesys.SmartClient.Control.props.menu.MenuSSItemProps
import com.simplesys.SmartClient.Control.props.{MenuSSProps, SliderProps}
import com.simplesys.SmartClient.DataBinding.props.{AdvancedCriteriaProps, CriterionProps}
import com.simplesys.SmartClient.DataBinding.{DSRequest, _}
import com.simplesys.SmartClient.Drawing.drawItem.DrawLinePathSS
import com.simplesys.SmartClient.Drawing.drawItem.props.DrawLabelProps
import com.simplesys.SmartClient.Drawing.gradient.props.SimpleGradientProps
import com.simplesys.SmartClient.Drawing.props._
import com.simplesys.SmartClient.Drawing.{DrawItemCommons, _}
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.{CanvasProps, RichTextEditorProps}
import com.simplesys.SmartClient.Grids.props.detailViewer.DetailViewerFieldProps
import com.simplesys.SmartClient.Grids.props.tileGrid.DrawItemTileProps
import com.simplesys.SmartClient.Grids.tileGrid.{DrawItemTile, TileRecord}
import com.simplesys.SmartClient.Layout.props.sectionStack.SectionStackSectionProps
import com.simplesys.SmartClient.Layout.props.{HLayoutSSProps, SectionStackSSProps, WindowSSDialogProps, WindowSSProps}
import com.simplesys.SmartClient.Layout.{SectionStackSS, WindowSS}
import com.simplesys.SmartClient.RPC.RPCResponseStatic
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.editProxy.DrawPaneEditProxy
import com.simplesys.SmartClient.Tools.palette.TilePalette
import com.simplesys.SmartClient.Tools.palette.props.TilePaletteProps
import com.simplesys.SmartClient.Tools.props.editProxy.DrawPaneEditProxyProps
import com.simplesys.SmartClient.Tools.props.{EditContextSSProps, EditProxyProps, PaletteNodeProps}
import com.simplesys.SmartClient.Tools.{EditContext, EditContextSS, EditProxy, PaletteNode, _}
import com.simplesys.System.Types.HoopSelectionStyle.{HoopSelectionStyle ⇒ _}
import com.simplesys.System.Types.VisibilityMode.{VisibilityMode ⇒ _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.{ListAnything, _}
import com.simplesys.appCommon
import com.simplesys.function._
import com.simplesys.jdbc.control.clob._
import com.simplesys.js.components._
import com.simplesys.js.components.drawing.drawItems.props._
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen.{DataSourcesJS, scenarios_Scr_Graph_CopiesJsonStorage_scenario_id_NameStrong, scenarios_Scr_Graph_Copies_id_scenario_ref_NameStrong, scenarios_Scr_ScenarioJsonStorage_scenario_id_scenario_NameStrong}
import ru.simplesys.defs.app.scala.container.Scr_ScenarioJsonStorage_scenarioDataRecord

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.UndefOr._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.{ThisFunction0, ThisFunction2, UndefOr}


@ScalaJSDefined
trait ErrorStuct extends js.Object {
    val message: JSUndefined[String]
    val stackTrace: JSUndefined[String]
}

@ScalaJSDefined
trait Scr_ScenarioJsonStorage_scenarioDataRecordExt extends Scr_ScenarioJsonStorage_scenarioDataRecord {
    val error: JSUndefined[ErrorStuct]
}

@ScalaJSDefined
trait Position extends JSObject {
    val rectTop: Double
    val rectLeft: Double
    val rectWidth: Double
    val rectHeight: Double
    val xc: Double
    val yc: Double
    val width: Double
    val height: Double
}

trait ConstructorFormBase extends SCComponent[ConstructorForm] with DrawItemCommons {
    self =>

    private lazy val itemContextMenu: MenuSS =
        MenuSS.create(
            new MenuSSProps {
                unserialize = true.opt
                items = Seq(
                    new MenuSSItemProps {
                        title = "Свойства".ellipsis.opt
                        identifier = "properties".opt
                        icon = Common.properties.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                propertyEditorRef.foreach(_.markForDestroy())
                                isc.Timer.setTimeout(() ⇒ propertyEditorRef = getPropertyEditor(), 10)
                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                canvasEditContext.getSelectedEditNodes().length == 1
                        }.toFunc.opt
                    },
                    new MenuSSItemProps {
                        title = "Копировать свойства".opt
                        identifier = "copyProps".opt
                        icon = Common.copy_icon.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                copiedProperties = isc.shallowClone(canvasEditContext.getSelectedEditNodes().head).asInstanceOf[DrawItem].defaults
                                //isc debugTrap copiedProperties
                                false
                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                canvasEditContext.getSelectedEditNodes().length == 1
                        }.toFunc.opt
                    },
                    new MenuSSItemProps {
                        title = "Вставить свойства".opt
                        identifier = "pastProps".opt
                        icon = Common.insert.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                // TODO: Отладить каждый пункт присвоения свойств
                                //setPropertyOnSelection("serializeID", copiedProperties.get.serializeID.undefAny)
                                setPropertyOnSelection("shadow", copiedProperties.get.shadow.undefAny)
                                setPropertyOnSelection("canDrag", copiedProperties.get.canDrag.undefAny)
                                setPropertyOnSelection("cursor", copiedProperties.get.cursor.undefAny)
                                setPropertyOnSelection("endArrow", copiedProperties.get.endArrow.undefAny)
                                setPropertyOnSelection("fillColor", copiedProperties.get.fillColor.undefAny)
                                setPropertyOnSelection("fillGradient", copiedProperties.get.fillGradient.undefAny)
                                setPropertyOnSelection("fillOpacity", copiedProperties.get.fillOpacity.undefAny)
                                setPropertyOnSelection("lineCap", copiedProperties.get.lineCap.undefAny)
                                setPropertyOnSelection("lineColor", copiedProperties.get.lineColor.undefAny)
                                setPropertyOnSelection("lineOpacity", copiedProperties.get.lineOpacity.undefAny)
                                setPropertyOnSelection("linePattern", copiedProperties.get.linePattern.undefAny)
                                setPropertyOnSelection("lineWidth", copiedProperties.get.lineWidth.undefAny)
                                setPropertyOnSelection("criteria", copiedProperties.get.criteria.undefAny)
                                setPropertyOnSelection("startArrow", copiedProperties.get.startArrow.undefAny)
                                setPropertyOnSelection("titleRotationMode", copiedProperties.get.titleRotationMode.undefAny)
                                setPropertyOnSelection("title", copiedProperties.get.title.undefAny)
                                setPropertyOnSelection("rounding", copiedProperties.get.rounding.undefAny)
                                setPropertyOnSelection("keepInParentRect", copiedProperties.get.keepInParentRect.undefAny)
                                setPropertyOnSelection("multiline", copiedProperties.get.multiline.undefAny)
                                setPropertyOnSelection("listRefs", copiedProperties.get.listRefs.undefAny)
                                setPropertyOnSelection("titleLabelProperties", copiedProperties.get.titleLabelProperties.undefAny)
                                setPropertyOnSelection("messageSMS", copiedProperties.get.messageSMS.undefAny)
                                setPropertyOnSelection("timerProps", copiedProperties.get.timerProps.undefAny)

                                getDrawPane.foreach(_.refreshNow())
                                false
                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                canvasEditContext.getSelectedEditNodes().length == 1 && copiedProperties.isDefined && canvasEditContext.getSelectedEditNodes().head.asInstanceOf[DrawItem].defaults._constructor == copiedProperties.get._constructor
                        }.toFunc.opt
                    },
                    new MenuSSItemProps {
                        title = "Удалить".opt
                        identifier = "remove".opt
                        icon = Common.delete_Column.opt
                        click = {
                            (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                                isc.ask(simpleSyS.config.confirmDeleting, {
                                    (value: Boolean) =>
                                        if (value)
                                            removeSelectedItems()
                                })

                                false
                        }.toFunc.opt
                        enableIf = {
                            (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                                canvasEditContext.getSelectedEditNodes().length > 0
                        }.toFunc.opt
                    }).opt
            })


    lazy val editCanvas: Canvas =
        Canvas.create(
            new CanvasProps {
                autoDraw = false.opt
                width = "100%"
                height = "100%"
                border = "1px solid black".opt
                canFocus = true.opt
                contextMenu = jsonContextMenu.opt
                //showResizeBar = true.opt
                keyPress = {
                    (thiz: Canvas) =>
                        if (isc.EventHandler.getKey() == "Delete") {
                            isc.ask(simpleSyS.config.confirmDeleting, {
                                (value: Boolean) =>
                                    if (value)
                                        removeSelectedItems()
                            })
                        }
                        false
                }.toThisFunc.opt
            }
        )

    protected val saveControl: JSUndefined[Canvas] = jSUndefined
    private var saveState = false

    def onEditedAction(disabled: Boolean = false) {
        saveControl.foreach(_ setDisabled disabled)
        saveState = !disabled
    }

    def getSaveState() = saveState

    protected val identifier: String

    protected val jsonStorage: JSUndefined[JSAny] = jSUndefined

    protected val dataSource = DataSourcesJS.scenarios_Scr_ScenarioJsonStorage_scenario_DS
    protected val dataSourceHistory = DataSourcesJS.scenarios_Scr_Graph_CopiesJsonStorage_scenario_DS

    protected val idScenario: JSUndefined[Double]

    protected val codeCmpgn: JSUndefined[String] = jSUndefined
    protected val codeScenario: JSUndefined[String] = jSUndefined
    protected val captionScenario: JSUndefined[String] = jSUndefined

    protected val tileSize = 80

    protected val topPadding: Double = 3

    private val canDragScroll = true

    private val components = mutable.HashMap.empty[String, DrawItem]

    private val drawPaneEditProxy: DrawPaneEditProxy = DrawPaneEditProxy.create(
        new DrawPaneEditProxyProps {
            dropOut = (self.dropOut _).toThisFunc.opt
            drop = (self.drop _).toThisFunc.opt
        }
    )

    def refresh(): Unit = {
        recoverGraph(_getJSONGraph())
    }

    private val emptyDrawPanePaletteNode: DrawPane = {
        val res = DrawPane.create(
            new DrawPaneProps {
                editProxyProperties = drawPaneEditProxy.opt
                defaults = DrawPaneDefaults(
                    new DrawPaneDefaultsProps {
                        canFocus = true.opt
                        width = "100%"
                        height = "100%"
                    }).opt
            }
        )
        res
    }

    private val shd = Shadow(
        new ShadowProps {
            color = "#333333".opt
            blur = 2.opt
            offset = Point(1, 1).opt
        })

    private val editProxyElements: EditProxy = EditProxy(
        new EditProxyProps {
            inlineEditEvent = InlineEditEvent.dblOrKeypress.opt
            startInlineEditing = {
                (thiz: classHandler, appendChar: JSUndefined[String]) =>
                    propertyEditorRef.foreach(_.markForDestroy())
                    isc.Timer.setTimeout(() ⇒ propertyEditorRef = getPropertyEditor(), 10)
            }.toThisFunc.opt
        })

    private lazy val jsonContextMenu = MenuSS.create(
        new MenuSSProps {
            items = Seq(
                new MenuSSItemProps {
                    title = "Просмотреть JSON графа сценария".ellipsis.opt
                    icon = Common.structure.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            getJSONWindow(isc.js_beautify _)
                    }.toFunc.opt
                    visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                },
                new MenuSSItemProps {
                    title = "Редактировать JSON графа сценария".ellipsis.opt
                    icon = Common.structure.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            editJSONDirectInBase(idScenario)
                    }.toFunc.opt
                    visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                },
                new MenuSSItemProps {
                    isSeparator = true.opt
                    visibilityIf = (() ⇒ LoggedGroup.isDevsGroup()).toFunc.opt
                },
                new MenuSSItemProps {
                    title = "Копировать граф".ellipsis.opt
                    icon = Common.copy_icon.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            isc.copiedJoson = _getJSONGraph()
                    }.toFunc.opt

                },
                new MenuSSItemProps {
                    title = "Вставить граф".ellipsis.opt
                    icon = Common.insert.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            isc.copiedJoson.foreach(recoverGraph(_))
                            onEditedAction()
                    }.toFunc.opt
                    enableIf = {
                        (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                            isc.copiedJoson.isDefined
                    }.toFunc.opt

                },
                new MenuSSItemProps {
                    isSeparator = true.opt
                },
                new MenuSSItemProps {
                    title = "История".ellipsis.opt
                    icon = appCommon.history.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            historyEditorRef.foreach(_.markForDestroy())
                            isc.Timer.setTimeout(
                                () ⇒
                                    historyEditorRef = WindowSS.create(
                                        new WindowSSProps {
                                            identifier = "062E2BB7-C2AA-89BB-4D22-0FA96C1227F8".opt
                                            title = item.title.ellipsis.opt
                                            headerIconPath = item.icon.opt
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
                                            items = Seq(HistoryList.create(
                                                new HistoryListProps {
                                                    recoverGraphFromHistory = (self.recoverGraphFromHistory _).toFunc.opt
                                                    initialCriteria = js.Dictionary(scenarios_Scr_Graph_Copies_id_scenario_ref_NameStrong.name → idScenario.getOrElse(0.0)).asInstanceOf[Criteria].opt
                                                }
                                            )).opt
                                        }
                                    ),
                                200
                            )

                    }.toFunc.opt
                    enableIf = {
                        (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                            true
                    }.toFunc.opt
                },
                new MenuSSItemProps {
                    isSeparator = true.opt
                },
                new MenuSSItemProps {
                    title = "Сохранить".ellipsis.opt
                    icon = appCommon.iconSave.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            updateInBase(_callback = (
                              (response: DSResponse) => {
                                  saveState = false
                                  isc ok "Запись выполнена"
                              }).toFunc)
                    }.toFunc.opt
                    enableIf = {
                        (target: Canvas, menu: MenuSS, item: MenuSSItem) =>
                            saveState
                    }.toFunc.opt
                },
                new MenuSSItemProps {
                    isSeparator = true.opt
                },
                new MenuSSItemProps {
                    title = "Очистить".ellipsis.opt
                    icon = appCommon.delete_Column.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>
                            canvasEditContext.removeAll()
                            canvasEditContext addFromPaletteNode emptyDrawPanePaletteNode
                            onEditedAction()
                    }.toFunc.opt

                },
                new MenuSSItemProps {
                    isSeparator = true.opt
                },
                new MenuSSItemProps {
                    title = "Обновить".ellipsis.opt
                    icon = appCommon.iconRefresh.opt
                    click = {
                        (target: Canvas, item: MenuSSItem, menu: MenuSS, colNum: JSUndefined[Int]) =>

                            idScenario.foreach(id ⇒ recoverGraphFromBase(id))

                    }.toFunc.opt

                }
            ).opt
        })

    private val canvasEditContext: EditContextSS = EditContextSS.create(
        new EditContextSSProps {
            //defaultPalette = actionTilePalette.opt
            selectionType = SelectionStyle.single.opt
            enableInlineEdit = true.opt
            canSelectEditNodes = true.opt
            hoopSelectionMode = HoopSelectionStyle.intersects.opt

            rootComponent = PaletteNode(
                new PaletteNodeProps {
                    liveObject = editCanvas.opt
                    `type` = "Canvas".opt
                }
            ).opt
            selectedEditNodesUpdated = {
                (thiz: JSObject, firstSelectedNode: JSUndefined[EditNode], allSelectedNodes: IscArray[EditNode]) =>
                    firstSelectedNode.foreach {
                        editNode =>
                            if (isc.isA.DrawLinePathSS(editNode.liveObject)) {
                                val drawItem = editNode.liveObject.asInstanceOf[DrawItem]
                                refreshPropertyEditor()
                                changeZ(drawItem)
                            } else if (isc.isA.DrawPane(editNode.liveObject))
                                propertyEditorRef.foreach(_.markForDestroy())
                            else
                                refreshPropertyEditor()
                    }

            }.toThisFunc.opt
            editNodeUpdated = {
                (editNode: JSUndefined[EditNode], editContext: JSUndefined[EditContext], modifiedProperties: IscArray[String]) =>
                    editNode.foreach {
                        editNode =>
                            onEditedAction()
                    }

            }.toFunc.opt
        }
    )
    var jsonWindow: JSUndefined[WindowSS] = jSUndefined

    private var copiedProperties: JSUndefined[DrawItemDefaults] = jSUndefined

    private var propertyEditorRef: JSUndefined[PropertyEditorWindow] = jSUndefined
    private var historyEditorRef: JSUndefined[WindowSS] = jSUndefined

    private def paletteDrawItems(): IscArray[DrawItem] = {
        val positions: Position = {
            val leftPadding = topPadding
            val rightPadding = topPadding
            val bottomPadding = topPadding

            val tileHeight = tileSize - topPadding - bottomPadding
            val tileWidth = tileSize - leftPadding - rightPadding

            val _xc = leftPadding + tileWidth / 2
            val _yc = topPadding + tileHeight / 2

            val _width = tileWidth - leftPadding - rightPadding
            val _height = tileHeight - topPadding - bottomPadding

            val smallAngle = Math.PI / 5
            val rectPoints = DrawPaneSatic.getPolygonPoints(_width, _height, _xc, _yc, IscArray(smallAngle, Math.PI - smallAngle, Math.PI + smallAngle, -smallAngle))

            new Position {
                val rectTop = rectPoints(1)(1)
                val rectLeft = rectPoints(1)(0)
                val rectWidth = rectPoints(3)(0) - rectLeft
                val rectHeight = rectPoints(3)(1) - rectTop
                val xc = _xc
                val yc = _yc
                val width = _width
                val height = _height
            }
        }

        val curveDefaults = DrawPathDefaults(
            new DrawPathDefaultsProps {
                startPoint = Point(Math.round(positions.xc - positions.width / 2), Math.round(positions.yc - (positions.height - 10) / 2)).opt
                endPoint = Point(Math.round(positions.xc + positions.width / 2), Math.round(positions.yc + (positions.height - 20) / 2)).opt
                titleLabelProperties = DrawLabel(new DrawLabelProps {
                    lineColor = "#000000".opt
                }).opt
                keepInParentRect = false.opt
                lineWidth = 1.opt
                shadow = shd.opt
            }
        )

        val defPurchase = isc clone curveDefaults
        defPurchase.title = "Внешнее\nСообщение"

        val defTimerFired = isc clone curveDefaults
        defTimerFired.title = "Срабатывание\n таймера"

        IscArray(
            StartState(
                new StartStateProps {
                    title = "Начало".opt
                    defaults = DrawOvalDefaults(
                        new DrawOvalDefaultsProps {
                            title = "Начало".opt
                            top = positions.rectTop.opt
                            left = positions.rectLeft.opt
                            width = positions.rectHeight.opt
                            height = positions.rectHeight.opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#99ccff".opt
                                    startColor = "red".opt
                                    direction = 90.0.opt
                                }).opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            enable4Glue = true.opt
                            enable4Connect = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.states.opt
                }
            ),
            StopState(
                new StopStateProps {
                    title = "Конец".opt
                    defaults = DrawOvalDefaults(
                        new DrawOvalDefaultsProps {
                            title = "Конец".opt
                            top = positions.rectTop.opt
                            left = positions.rectLeft.opt
                            width = positions.rectHeight.opt
                            height = positions.rectHeight.opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    startColor = "#99ccff".opt
                                    endColor = "red".opt
                                    direction = 90.0.opt
                                }).opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            enable4Connect = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.states.opt
                }
            ),
            StateUnified(
                new StateUnifiedProps {
                    title = "Состояние".opt
                    defaults = DrawOvalDefaults(
                        new DrawOvalDefaultsProps {
                            title = "Состояние".opt
                            top = positions.rectTop.opt
                            left = positions.rectLeft.opt
                            width = positions.rectWidth.opt
                            height = positions.rectHeight.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#99ccff".opt
                                    startColor = "#ffffff".opt
                                    direction = 90.0.opt
                                }).opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            enable4Connect = true.opt
                            enable4Glue = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.states.opt
                }),
            IncomingMessage(
                new IncomingMessageProps {
                    title = "Внешнее\nСообщение".opt
                    defaults = defPurchase.opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.events.opt
                }
            ),
            TimerFired(
                new TimerFiredProps {
                    defaults = defTimerFired.opt
                    title = "Срабатывание\n таймера".opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.events.opt
                }
            ),
            Transition(
                new TransitionProps {
                    title = "Переход".opt
                    defaults = DrawPathDefaults(
                        new DrawPathDefaultsProps {
                            startPoint = Point(Math.round(positions.xc - positions.width / 2), Math.round(positions.yc - (positions.height - 10) / 2)).opt
                            endPoint = Point(Math.round(positions.xc + positions.width / 2), Math.round(positions.yc + (positions.height - 20) / 2)).opt
                            title = "Переход".opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            shadow = shd.opt
                        }
                    ).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.events.opt
                }
            ),
            TimerUnified(
                new TimerUnifiedProps {
                    title = "Таймер".opt
                    defaults = DrawRectDefaults(
                        new DrawRectDefaultsProps {
                            title = "Таймер".opt
                            top = positions.rectTop.opt
                            left = positions.rectLeft.opt
                            width = positions.rectWidth.opt
                            height = positions.rectHeight.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#808000".opt
                                    startColor = "#FFFF00".opt
                                    direction = 47.0.opt
                                }).opt
                            rounding = 0.25.opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            enable4Connect = true.opt
                            enable2Glue = true.opt
                            enable4Glue = true.opt
                            shadow = shd.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.timers.opt
                }),
            /*SubProgram(
                new SubProgramProps {
                    title = "Подпрограмма".opt
                    defaults = DrawRectDefaults(
                        new DrawRectDefaultsProps {
                            top = positions.rectTop.opt
                            title = "Подпрограмма".opt
                            left = positions.rectLeft.opt
                            width = positions.rectWidth.opt
                            height = positions.rectHeight.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#FFFF00".opt
                                    startColor = "#00CCFF".opt
                                    direction = 70.0.opt
                                }).opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            rounding = 0.8.opt
                            enable2Glue = true.opt
                            enable4Glue = true.opt
                            enable4Connect = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.subPrograms.opt
                }
            ),*/
            SendMessage(
                new SendMessageProps {
                    title = "Отправка\nсообщения".opt
                    defaults = DrawRectDefaults(
                        new DrawRectDefaultsProps {
                            top = positions.rectTop.opt
                            title = "Отправка\nсообщения".opt
                            left = positions.rectLeft.opt
                            width = positions.rectWidth.opt
                            height = positions.rectHeight.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#FFFF00".opt
                                    startColor = "#00CCFF".opt
                                    direction = 70.0.opt
                                }).opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            rounding = 0.8.opt
                            enable2Glue = true.opt
                            enable4Glue = true.opt
                            enable4Connect = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.subPrograms.opt
                }
            ),
            CalcGroup(
                new CalcGroupProps {
                    title = "Вычисление\n группы".opt
                    defaults = DrawRectDefaults(
                        new DrawRectDefaultsProps {
                            top = positions.rectTop.opt
                            title = "Вычисление\n группы".opt
                            left = positions.rectLeft.opt
                            width = positions.rectWidth.opt
                            height = positions.rectHeight.opt
                            fillGradient = SimpleGradient(
                                new SimpleGradientProps {
                                    endColor = "#FFFF00".opt
                                    startColor = "#00CCFF".opt
                                    direction = 70.0.opt
                                }).opt
                            keepInParentRect = false.opt
                            lineWidth = 1.opt
                            titleLabelProperties = DrawLabel(new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                            shadow = shd.opt
                            rounding = 0.8.opt
                            enable2Glue = true.opt
                            enable4Glue = true.opt
                            enable4Connect = true.opt
                        }).opt
                    editProxyProperties = editProxyElements.opt
                    palette = ConstructorPalette.subPrograms.opt
                }
            ),
            /*Variable(new VariableProps {
                title = "Переменная".opt
                defaults = DrawRectDefaults(
                    new DrawRectDefaultsProps {
                        top = positions.rectTop.opt
                        title = "Переменная".opt
                        left = positions.rectLeft.opt
                        width = positions.rectWidth.opt
                        height = positions.rectHeight.opt
                        fillGradient = SimpleGradient(
                            new SimpleGradientProps {
                                endColor = "#FFFF00".opt
                                startColor = "#800000".opt
                                direction = 45.0.opt
                            }).opt
                        keepInParentRect = false.opt
                        lineWidth = 1.opt
                        titleLabelProperties = DrawLabel(
                            new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                        shadow = shd.opt
                        rounding = 0.0.opt
                        enable2Glue = true.opt
                        enable4Glue = true.opt
                        enable4Connect = false.opt
                    }).opt
                editProxyProperties = editProxyElements.opt
                palette = ConstructorPalette.variables.opt
            }),*/
            /*CounterVariable(new CounterVariableProps {
                title = "Счетчик -\nаккумулятор".opt
                defaults = DrawRectDefaults(
                    new DrawRectDefaultsProps {
                        top = positions.rectTop.opt
                        title = "Счетчик -\nаккумулятор".opt
                        left = positions.rectLeft.opt
                        width = positions.rectWidth.opt
                        height = positions.rectHeight.opt
                        fillGradient = SimpleGradient(
                            new SimpleGradientProps {
                                endColor = "#FFFF00".opt
                                startColor = "#800000".opt
                                direction = 45.0.opt
                            }).opt
                        keepInParentRect = false.opt
                        lineWidth = 1.opt
                        titleLabelProperties = DrawLabel(
                            new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                        shadow = shd.opt
                        rounding = 0.0.opt
                        enable2Glue = true.opt
                        enable4Glue = true.opt
                        enable4Connect = false.opt
                    }).opt
                editProxyProperties = editProxyElements.opt
                palette = ConstructorPalette.variables.opt
            }),*/
            ListAnything(new ListAnythingProps {
                title = "Список".opt
                defaults = DrawRectDefaults(
                    new DrawRectDefaultsProps {
                        title = "Список".opt
                        top = positions.rectTop.opt
                        left = positions.rectLeft.opt
                        width = positions.rectWidth.opt
                        height = positions.rectHeight.opt
                        fillGradient = SimpleGradient(
                            new SimpleGradientProps {
                                endColor = "#FF6600".opt
                                startColor = "#00CCFF".opt
                                direction = 45.0.opt
                            }).opt
                        keepInParentRect = false.opt
                        lineWidth = 1.opt
                        titleLabelProperties = DrawLabel(
                            new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                        shadow = shd.opt
                        rounding = 0.10.opt
                        enable2Glue = false.opt
                        enable4Glue = false.opt
                        enable4Connect = false.opt
                    }).opt
                editProxyProperties = editProxyElements.opt
                palette = ConstructorPalette.miscs.opt
            }),
            Group(new GroupProps {
                title = "Группа".opt
                defaults = DrawRectDefaults(
                    new DrawRectDefaultsProps {
                        title = "Группа".opt
                        top = positions.rectTop.opt
                        left = positions.rectLeft.opt
                        width = positions.rectWidth.opt
                        height = positions.rectHeight.opt
                        fillColor = "#D54500".opt
                        fillGradient = SimpleGradient(
                            new SimpleGradientProps {
                                endColor = "#D54500".opt
                                startColor = "#D54500".opt
                                direction = 45.0.opt
                            }).opt
                        keepInParentRect = false.opt
                        lineColor = "#0000FF".opt
                        lineWidth = 3.opt
                        titleLabelProperties = DrawLabel(
                            new DrawLabelProps {
                                lineColor = "#000000".opt
                            }).opt
                        shadow = shd.opt
                        rounding = 0.99.opt
                    }).opt
                editProxyProperties = editProxyElements.opt
                palette = ConstructorPalette.miscs.opt
            })
        )
    }

    //todo Не работает открытие TABSET В РЕЖИМЕ VisibilityMode.multiple

    private lazy val slider = Slider.create(
        new SliderProps {
            //width = 50
            animateThumbInit = true.opt
            //visibility = Visibility.hidden.opt
            minValue = 0.50.opt
            //showRange = false.opt
            //showValue = false.opt
            maxValue = 2.00.opt
            labelWidth = 20.opt
            labelSpacing = 0.opt
            numValues = 200.opt
            roundValues = false.opt
            roundPrecision = 2.opt
            title = "Масштаб".opt
            showTitle = false.opt
            drawPane = getDrawPane.opt
            valueChanged = {
                (thiz: classHandler, value: Double) =>
                    //isc debugTrap (thiz.drawPane, value)
                    thiz.drawPane.foreach {
                        drawPane =>
                            drawPane.zoom(value)
                            drawPane.zoomLevel = value

                    }
            }.toThisFunc.opt
        }
    )

    def setScrollingGraph(): Unit = {
        getDrawPane.foreach(item => item.canDragScroll = !item.canDragScroll)
    }

    def getDrawPane: JSUndefined[DrawPane] = {
        val drawPaneNode = canvasEditContext.getDrawPaneEditNode()
        if (drawPaneNode.isDefined)
            drawPaneNode.get.liveObject.asInstanceOf[DrawPane]
        else
            jSUndefined
    }

    def refreshPropertyEditor(): Unit = {
        propertyEditorRef.foreach {
            pe ⇒
                pe.markForDestroy()
                isc.Timer.setTimeout(() ⇒ propertyEditorRef = getPropertyEditor(), 10)
        }
    }

    private def getPropertyEditor(): PropertyEditorWindow = {
        val selected = self.canvasEditContext.getSelectedEditNodes()

        val _selected: JSUndefined[DrawItem] = if (selected.length != 1)
            jSUndefined
        else
            selected(0).asInstanceOf[DrawItem].undef

        PropertyEditorWindow.create(
            new PropertyEditorWindowProps {
                identifier = s"${self.identifier}${if (selected.isEmpty) "" else _selected.get._constructor}".opt
                canvasEditContext = self.canvasEditContext.opt
                propertyEditorDestroyRef = (self.propertyEditorDestroyRef _).toFunc.opt
                codeCmpgn = self.codeCmpgn.opt
                components = self.components.map(_._1).toSeq.opt
            }
        )
    }

    private def propertyEditorDestroyRef(): Unit = propertyEditorRef = jSUndefined

    def setPropertyOnSelection[T <: JSAny](property: String, _value: JSUndefined[T]): void = {
        val selected = canvasEditContext.getSelectedEditNodes()

        //isc debugTrap _value
        selected.foreach {
            item =>
                if (_value.isDefined) {
                    if (_value == null)
                        canvasEditContext.setNodeProperties(item, js.Dictionary(property -> null))
                    else
                        canvasEditContext.setNodeProperties(item, js.Dictionary(property -> _value))
                }
                else {
                    //canvasEditContext.setNodeProperties(item, js.Dictionary(property -> null))
                    canvasEditContext.removeNodeProperties(item, IscArray(property))
                }
        }
    }

    def removeSelectedItems(): void = {
        canvasEditContext.getSelectedEditNodes().foreach {
            item =>
                val drawItem = item.liveObject.asInstanceOf[DrawItem]
                removeOutDrawItem(drawItem)
                removeInDrawItem(drawItem)
                val sourceItem = drawItem.sourceGlue
                drawItem.deleteGlueItems()
                sourceItem.foreach(_.setGluedDrawItem())
                canvasEditContext removeNode item
                item.liveObject.destroy()
        }
    }

    def recoverGraphFromBase(idScenario: Double, _callback: JSUndefined[Callback] = jSUndefined): Unit = {

        val criteria = AdvancedCriteria(
            new AdvancedCriteriaProps {
                operator = OperatorId.and.opt
                criteria = Seq(
                    Criterion(
                        new CriterionProps {
                            fieldName = scenarios_Scr_ScenarioJsonStorage_scenario_id_scenario_NameStrong.name.opt
                            operator = OperatorId.equals.opt
                            value = idScenario.asInstanceOf[JSAny].opt
                        })
                ).opt
            }
        )

        fetchFromBase(dataSource, criteria, (json: String) ⇒ if (json != "") recoverGraph(json, _callback))
    }

    def recoverGraphFromHistory(idHistory: Double, _callback: JSUndefined[Callback] = jSUndefined): Unit = {

        //isc debugTrap idHistory

        val criteria = AdvancedCriteria(
            new AdvancedCriteriaProps {
                operator = OperatorId.and.opt
                criteria = Seq(
                    Criterion(
                        new CriterionProps {
                            fieldName = scenarios_Scr_Graph_CopiesJsonStorage_scenario_id_NameStrong.name.opt
                            operator = OperatorId.equals.opt
                            value = idHistory.asInstanceOf[JSAny].opt
                        })
                ).opt
            }
        )

        fetchFromBase(dataSourceHistory, criteria, (json: String) ⇒ if (json != "") recoverGraph(json, _callback))
    }

    def fetchFromBase(dataSource: RestDataSourceSS, criteria: AdvancedCriteria, _callback: js.Function1[String, _]): Unit =
        dataSource.fetchData(
            criteria = criteria,
            callback = {
                (resp: DSResponse, data: JSObject, req: DSRequest) ⇒
                    val errorStruct = resp.errorStruct

                    //isc debugTrap resp

                    if (errorStruct.isEmpty && resp.status == RPCResponseStatic.STATUS_SUCCESS) {
                        if (isc.isA.Array(data)) {
                            val res = data.asInstanceOf[IscArray[Scr_ScenarioJsonStorage_scenarioDataRecordExt]]

                            //isc debugTrap res

                            if (res.length > 0) {
                                if (res.head.error.isDefined) {
                                    if (res.head.error.get.stackTrace.isDefined)
                                        isc.errorDetail(res.head.error.get.message.getOrElse("Ошибка не определена."), res.head.error.get.stackTrace.get)
                                    else
                                        isc error res.head.error.get.message.getOrElse("Ошибка не определена.")
                                }
                                else if (res.head.jsonStorage_scenario.isDefined)
                                    _callback(res.head.jsonStorage_scenario.get)
                                else
                                    _callback("")
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

    //</editor-fold>

    def recoverGraph(json: JSAny, _callback: JSUndefined[Callback] = jSUndefined): Unit = {
        //isc debugTrap json
        if (json.toString != "") {
            canvasEditContext.destroyAll()
            canvasEditContext.addPaletteNodesFromJSON1(
                components = self.components.toMap,
                jsonString = json,
                addedProps = new AddedProps {
                    override val drawItemEditProxyProperties: EditProxy = self.editProxyElements
                    override val contextMenu: MenuSS = self.itemContextMenu
                    override val drawPaneEditProxyProperties: DrawPaneEditProxy = self.drawPaneEditProxy
                    override val canvasEditContext: EditContext = self.canvasEditContext
                    override val onDragStop: ThisFunction2[DrawItem, Int, Int, _] = self.onDragStop _
                    override val resized: ThisFunction0[DrawItem, _] = self.onResized _
                },
                callback = {
                    () =>

                        getDrawPane.foreach {
                            drawPane =>
                                drawPane.canDragScroll = self.canDragScroll
                                slider.drawPane = drawPane
                                slider setValue drawPane.zoomLevel
                                drawPane.drawItems.foreach(_._updateTitleLabelAndBackground())
                        }

                        onEditedAction(true)
                        _callback.foreach(canvasEditContext.fireCallback(_))
                }.toFunc
            )
            //onEditedAction()
        }
    }

    private def onDragStop(drawItem: DrawItem, x: Int, y: Int): Unit = {
        onEditedAction()
    }

    private def onResized(drawItem: DrawItem): Unit = {
        onEditedAction()
    }

    def editJSONDirectInBase(idScenario: JSUndefined[Double], format: JSUndefined[Boolean] = jSUndefined): Unit = {
        val criteria = AdvancedCriteria(
            new AdvancedCriteriaProps {
                operator = OperatorId.and.opt
                criteria = Seq(
                    Criterion(
                        new CriterionProps {
                            fieldName = scenarios_Scr_ScenarioJsonStorage_scenario_id_scenario_NameStrong.name.opt
                            operator = OperatorId.equals.opt
                            value = idScenario.asInstanceOf[JSAny].opt
                        })
                ).opt
            }
        )

        fetchFromBase(dataSource, criteria, (json: String) ⇒ {

            //replaceAll("<div.*</div>", "")
            val _json: String = if (format.getOrElse(true)) s"<plaintext>${isc js_beautify json}" else json

            val editor = RichTextEditor.create(
                new RichTextEditorProps {
                    autoDraw = false.opt
                    height = "100%"
                    width = "100%"
                    controlGroups = Seq().opt
                    value = _json.opt
                }
            )

            WindowSSDialog.create(
                new WindowSSDialogProps {
                    height = 800
                    width = 800
                    showMaximizeButton = true.opt
                    showMinimizeButton = true.opt
                    title = "JSON Viewer".opt
                    headerIconPath = Common.Actions_document_edit_icon.opt
                    identifier = "DC3493B6-F896-800E-CE71-B6AD0559B6ED".opt
                    isModal = true.opt
                    okFunction = {
                        (thiz: classHandler) ⇒
                            updateInBase(
                                new Scr_ScenarioJsonStorage_scenarioDataRecord {
                                    override val id_scenario: UndefOr[Double] = idScenario
                                    override val jsonStorage_scenario: UndefOr[Clob] = editor.getValue().asInstanceOf[String].replace("<plaintext>", "").replace("</plaintext>", "").replaceAll("<div.*</div>", "")
                                }, {
                                    () ⇒
                                        thiz.markForDestroy()
                                        isc ok "Сохранение выполнено."
                                }.toFunc
                            )

                    }.toThisFunc.opt

                    wrapCanvas = editor.opt
                }
            )
        })
    }

    def updateInBase(_updateRecord: JSUndefined[Scr_ScenarioJsonStorage_scenarioDataRecord] = jSUndefined, _callback: JSUndefined[Callback] = jSUndefined): Unit =
        getJSONGraph({
            (jsonGraph: String) =>
                dataSource.updateData(
                    updatedRecord = if (_updateRecord.isDefined)
                        _updateRecord.get
                    else
                        new Scr_ScenarioJsonStorage_scenarioDataRecord {
                            override val id_scenario: UndefOr[Double] = idScenario
                            override val jsonStorage_scenario: UndefOr[Clob] = jsonGraph
                        },
                    callback = {
                        (resp: DSResponse, data: JSObject, req: DSRequest) ⇒
                            val errorStruct = resp.errorStruct
                            //isc debugTrap(resp, errorStruct, resp.status == RPCResponseStatic.STATUS_SUCCESS)

                            if (errorStruct.isDefined && errorStruct.get.status == RPCResponseStatic.STATUS_SUCCESS)
                                _callback.foreach(canvasEditContext.fireCallback(_))
                            else if (resp.status == RPCResponseStatic.STATUS_SUCCESS)
                                _callback.foreach(canvasEditContext.fireCallback(_, "resp", IscArray(resp)))
                    }
                )
        }.toFunc)


    def insertInBase(_callback: JSUndefined[Callback] = jSUndefined): Unit =
        getJSONGraph({
            (jsonGraph: String) =>
                dataSource.addData(
                    newRecord = new Scr_ScenarioJsonStorage_scenarioDataRecord {
                        override val id_scenario: UndefOr[Double] = idScenario
                        override val jsonStorage_scenario: UndefOr[Clob] = jsonGraph
                    },
                    callback = {
                        (resp: DSResponse, data: JSObject, req: DSRequest) ⇒
                            val errorStruct = resp.errorStruct

                            if (errorStruct.isDefined && errorStruct.get.status == RPCResponseStatic.STATUS_SUCCESS)
                                _callback.foreach(canvasEditContext.fireCallback(_))

                    }
                )
        }.toFunc)


    def getPaletes(): IscArray[TilePalette] = {
        val drawItems = paletteDrawItems().filter(_.palette.isDefined)
        //isc debugTrap drawItems
        val palettes = drawItems.map(_.palette.get).map(_.asInstanceOf[ConstructorPaletteItem]).distinct
        //isc debugTrap palettes

        val res = IscArray(palettes.map {
            palette ⇒
                val res = getTilePelette(palette.name, palette.title)
                res setData IscArray(drawItems.filter(item ⇒ item.palette.isDefined && item.palette.get.asInstanceOf[ConstructorPaletteItem].name == palette.name).map {
                    item =>
                        item.defaults.onDragStop = (self.onDragStop _).toThisFunc
                        item.defaults.resized = (self.onResized _).toThisFunc
                        item.defaults.fieldDataSource = item.fieldDataSource
                        components += item.`type`.get → item
                        item
                }: _*)
                res setDefaultEditContext canvasEditContext
                res
        }.toSeq: _*)

        //isc debugTrap res
        res
    }


    def getJSONGraph(callback: JSUndefined[js.Function1[String, _]] = jSUndefined): void = {
        //val json = _getJSONGraph(iscStatic.js_beautify _)
        val json = _getJSONGraph().replaceAll("<div.*</div>", "")

        //isc debugTrap json
        callback.foreach(_ (json))
    }
    //</editor-fold>

    private def _getJSONGraph(butifiler: JSUndefined[String => String] = jSUndefined): String =

        if (butifiler.isDefined)
            butifiler.get(canvasEditContext.serializeAllEditNodesAsJSON())
        else
            canvasEditContext.serializeAllEditNodesAsJSON()

    canvasEditContext.observe(canvasEditContext, "selectSingleComponent", {
        (thiz: EditContextSS, editNode: EditNode) =>
            val _editNode = editNode.asInstanceOf[DrawItem]
            _editNode.drawPane.foreach {
                drawPane =>
                    _editNode.clickedPoint = drawPane.getDrawingPoint()

                    //isc debugTrap (isc.EventHandler.ctrlKeyDown(), editNode)
                    if (isc.EventHandler.shiftKeyDown() && isc.isA.DrawLinePathSS(editNode))
                        editNode.asInstanceOf[DrawLinePathSS].insertControlPointKnob()
            }
    }.toThisFunc)

    canvasEditContext.observe(canvasEditContext, "addFromPaletteNode", {
        (thiz: EditContextSS, editNode: EditNode, parentNode: JSUndefined[EditNode]) =>
            val _editNode = editNode.asInstanceOf[DrawItem]
            //isc debugTrap _editNode
            println(s"////////////////////////////////// addFromPaletteNode //////////////////////////////////////////////////////////////")
    }.toThisFunc)

    //</editor-fold>
    private def dropOut(thiz: DrawPaneEditProxy): Boolean = {

        val trg = isc.EventHandler.getDragTarget().asInstanceOf[TilePalette].getSelection().asInstanceOf[IscArray[DrawItem]]

        def checkFinStates(name: String): Boolean = {
            if (trg.exists { item => if (item.`type`.isEmpty) false else item.`type`.get == name }) {
                val drawItems = thiz.creator.asInstanceOf[DrawPane].drawItems.filter { item => if (item._constructor.isEmpty) false else item._constructor.get == name }
                if (drawItems.length > 0) {
                    isc.error(s"Компонент '${trg(0).title}' может присутствовать на схеме в единственном экземпляре.", s"34CE6614-D80F-BE98-754B-C2FED5F65224$name")
                    false
                } else
                    true
            } else
                true
        }

        import com.simplesys.js.components.drawing.drawItems.{Group, StartState, StopState}
        //isc debugTrap trg
        checkFinStates(StartState.typeName) &&
          checkFinStates(StopState.typeName) &&
          checkFinStates(Group.typeName)
    }

    private def drop(thiz: DrawPaneEditProxy, arguments: IscArray[JSAny]): Boolean = {
        val liveObject = thiz.creator.asInstanceOf[Canvas]

        val source = isc.EventHandler.getDragTarget().asInstanceOf[TilePalette]

        if (!isc.isA.Palette(source))
            liveObject.asInstanceOf[js.Dynamic].drop.apply(arguments).asInstanceOf[Boolean]
        else {
            val data = source.transferDragData()

            val paletteNode: JSUndefined[Object] = if (data.length > 0) data(0) else jSUndefined

            if (paletteNode.isEmpty)
                false
            else {
                if (liveObject.editContext.isEmpty)
                    false
                else {
                    val editContext = liveObject.editContext.get

                    val pn = paletteNode.asInstanceOf[PaletteNode]

                    isc.addProperties(pn.defaults, js.Dictionary("contextMenu" -> itemContextMenu.merge(pn.contextMenu)))

                    val editNode = editContext.makeEditNode(pn)
                    val editProxy = thiz

                    var res = true
                    editContext.requestLiveObject(editNode, {
                        (editNode: JSUndefined[EditNode]) =>
                            if (editNode.isDefined) {
                                var node: JSUndefined[EditNode] = jSUndefined

                                if (isc.isA.DrawPane(liveObject)) {
                                    node = editContext.addNode(editNode.get, liveObject.editNode.get)


                                    val drawItem = node.get.liveObject.asInstanceOf[DrawItem]
                                    drawItem.moveTo(liveObject.getOffsetX(), liveObject.getOffsetY())

                                    liveObject.editContext.get selectSingleComponent node.get.liveObject
                                    editProxy.creator.asInstanceOf[Canvas] focus()
                                } else
                                    res = false
                            }
                    }, source)
                    res
                }
            }
        }
    }

    private def getTilePelette(_identifier: ID, _title: String, _width: String = "100%"): TilePalette =
        TilePalette.create(
            new TilePaletteProps {
                title = _title.opt
                identifier = _identifier.opt
                width = _width
                tileWidth = self.tileSize.opt
                tileHeight = self.tileSize.opt
                canDragTilesOut = true.opt
                createTile = (createDrawItemTile _).toThisFunc.opt
                fields = Seq(
                    new DetailViewerFieldProps {
                        name = "type".opt
                    },
                    new DetailViewerFieldProps {
                        name = "title".opt
                        title = "Component".opt
                    }
                ).opt
            }
        )

    private def createDrawItemTile(tilePalette: TilePalette, record: TileRecord, pos: Int): DrawItemTile =
        DrawItemTile.create(
            new DrawItemTileProps {
                initWidget = {
                    (thiz: classHandler, arguments: IscArray[JSAny]) =>

                        thiz.Super("initWidget", arguments)
                        thiz.record = record

                        thiz.tileGrid = tilePalette
                        thiz.drawPane = DrawPane.create(
                            new DrawPaneProps {
                                autoDraw = false.opt
                                width = "100%"
                                height = "100%"
                            }
                        )

                        thiz addChild thiz.drawPane
                        thiz.setLogPriority(thiz.getClassName(), LogPriority.DEBUG)

                }.toThisFunc.opt

                getInnerHTML = { () => "&nbsp;" }.toFunc.opt

                private def drawRecord(thiz: classHandler, record: TileRecord) = {

                    val tilePalette = thiz.tileGrid.asInstanceOf[TilePalette]
                    val drawItem = tilePalette.makeEditNode1(record).liveObject.asInstanceOf[DrawItem]
                    thiz.drawPane addDrawItem drawItem
                }

                draw = {
                    (thiz: classHandler, arguments: JSUndefined[IscArray[JSAny]]) =>

                        val ret = thiz.Super1("draw", arguments)
                        val record = thiz.getRecord()

                        drawRecord(thiz, record)
                        ret.asInstanceOf[Canvas]
                }.toThisFunc.opt

                redraw = {
                    (thiz: classHandler, arguments: IscArray[JSAny]) =>

                        val drawPane = thiz.drawPane
                        val record = thiz.getRecord()

                        if (record != thiz.record) {
                            drawPane.erase()
                            drawRecord(thiz, record)
                            thiz.record = record
                        }

                        thiz.Super("redraw", arguments)

                }.toThisFunc.opt
            }
        )

    private def getJSONWindow(butifiler: JSUndefined[String => String] = jSUndefined): Unit = {
        jsonWindow.foreach(_.markForDestroy())

        isc.Timer.setTimeout({ () ⇒
            jsonWindow = WindowSS.create(
                new WindowSSProps {
                    identifier = "062E2BB7-C2AA-89BB-4DEE-0FA96C1227F8".opt
                    title = s"JSON ($codeCmpgn) $codeScenario: $captionScenario".ellipsis.opt
                    headerIconPath = Common.structure.opt
                    height = 500
                    width = 600
                    canDragResize = true.opt
                    canDragReposition = true.opt
                    keepInParentRect = true.opt
                    autoCenter = false.opt
                    autoSize = false.opt
                    isModal = true.opt
                    showMinimizeButton = false.opt
                    showMaximizeButton = true.opt
                    items = Seq(
                        RichTextEditor.create(
                            new RichTextEditorProps {
                                autoDraw = false.opt
                                height = "100%"
                                width = "100%"
                                controlGroups = Seq().opt
                                value = s"<plaintext>${_getJSONGraph(butifiler)}".opt
                            }
                        )

                    ).opt
                }
            )
        }.toFunc, 5)
    }

    getDrawPane.foreach {
        drawPane =>
            slider setValue drawPane.zoomLevel
            drawPane.canDragScroll = self.canDragScroll
    }

    private lazy val sectionStack: SectionStackSS = {
        val sectionStack = SectionStackSS.create(
            new SectionStackSSProps {
                showResizeBar = true.opt
                visibilityMode = VisibilityMode.multiple.opt
            }
        )

        val palettes = getPaletes()
        //isc debugTrap palettes

        palettes.foreach {
            palette ⇒ sectionStack addSection SectionStackSection(
                new SectionStackSectionProps {
                    expanded = true.opt
                    name = palette.identifier.opt
                    title = palette.title.opt
                    items = Seq(
                        palette
                    ).opt
                }
            )
        }

        canvasEditContext enableEditing canvasEditContext.getRootEditNode()
        canvasEditContext addFromPaletteNode emptyDrawPanePaletteNode
        sectionStack
    }

    override def get: ConstructorForm = {

        onEditedAction(true)

        jsonStorage.foreach(json => recoverGraph(json))

        val res = ConstructorForm.create(
            new ConstructorFormProps {
                width = "100%"
                height = "100%"
                getJSONGraph = (self.getJSONGraph _).toFunc.opt
                identifier = self.identifier.opt
                editContext = canvasEditContext.opt
                updateInBase = (self.updateInBase _).toFunc.opt
                recoverGraphFromBase = (self.recoverGraphFromBase _).toFunc.opt
                refresh = (self.refresh _).toFunc.opt
                getSaveState = (self.getSaveState _).toFunc.opt
                codeScenario = self.codeScenario.opt
                captionScenario = self.captionScenario.opt
                funcMenu = self.jsonContextMenu.opt
                propertyEditorRef = {
                    () ⇒ self.propertyEditorRef
                }.toFunc.opt
                historyEditorRef = {
                    () ⇒ self.historyEditorRef
                }.toFunc.opt
                members = Seq(
                    sectionStack,
                    HLayoutSS.create(
                        new HLayoutSSProps {
                            members = Seq(
                                editCanvas,
                                slider
                            ).opt
                        }
                    )
                ).opt
            }
        )

        res.getViewState()

        editCanvas.observe(canvasEditContext, "addNode", () ⇒ onEditedAction())
        editCanvas.observe(canvasEditContext, "removeNode", () ⇒ onEditedAction())

        res
    }
}
