package com.simplesys.components.props

import com.simplesys.SmartClient.DataBinding.dataSource.DataSourceField
import com.simplesys.SmartClient.DataBinding.props.dataSource.{DataSourceFieldProps, OperatorProps}
import com.simplesys.SmartClient.DataBinding.{AdvancedCriteria, Criterion, DataSource, OfflineSS}
import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.Drawing.drawItem.DrawLabel
import com.simplesys.SmartClient.Drawing.gradient.SimpleGradient
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{ColorItem, FontItem, FormItem, GradientItem}
import com.simplesys.SmartClient.Forms.props.FilterBuilderSSProps
import com.simplesys.SmartClient.Foundation.{Canvas, CanvasStatic}
import com.simplesys.SmartClient.Layout.TabSetSS
import com.simplesys.SmartClient.Layout.props.TabSetSSProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{Class, Operator, _}
import com.simplesys.SmartClient.Tools.{EditContext, EditNode}
import com.simplesys.System.Types.ArrowStyle.{ArrowStyle ⇒ _}
import com.simplesys.System.Types.FormItemType.{FormItemType ⇒ _}
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.App.{CalcGroupItem, CounterAccumItem, FormItemList, GroupItem, LoggingItem, PropertyEditorDynamicForm, SendMessageItem, SubProgramItem, TimerItem, VariableItem}
import com.simplesys.function._
import com.simplesys.components.drawing.drawItems._
import com.simplesys.components.formItems.ScalaTypes
import com.simplesys.components.formItems.props._
import com.simplesys.components.validators.Validators
import com.simplesys.components.{MultiElementsValue, PropEditorLiveObject, TemplateObject}
import com.simplesys.option.DoubleType.{Int2IntString, Stringt2IntString}
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}
import ru.simplesys.defs.app.scala.container.ScenariosListsDataRecord

import scala.scalajs.js
import scala.scalajs.js.UndefOr._
import scala.scalajs.js.WrappedArray

// TODO: Реализовать функцию обратной связи заполнения редактра свойств, для вызова в случае когода свойства изменеются не из редактора , при этом редактор запущен
trait PropertyEditorBase {

    self =>

    import com.simplesys.components.PropertyEditorDynamicForm

    protected val canvasEditContext: EditContext
    protected val identifier: String
    protected val codeCmpgn: String
    protected val components: IscArray[String]

    private def getDrawItems(): IscArray[DrawItem] = {
        canvasEditContext.serializeAllEditNodesAsJSON()
        val data = canvasEditContext.getEditNodeTree();
        data.getChildren(data.root).toOption match {
            case None ⇒ IscArray[DrawItem]()
            case Some(seq) ⇒
                val a: WrappedArray[DrawItem] = seq.map(_.children).filter(_.isDefined).map(_.get).flatten.map(_.liveObject).filter(_.isDefined).map(_.get)
                IscArray(a: _*)
        }
    }

    private def selectedEditNodesUpdated4Sys(propertyEditorDynamicForm: PropertyEditorDynamicForm): void = {

        val selected = propertyEditorDynamicForm.getSelectedNodes()
        //isc debugTrap selected

        if (selected.length == 0 || selected.length > 1) {
            if (propertyEditorDynamicForm.getItems().isDefined) {
                propertyEditorDynamicForm.clearValues()
                propertyEditorDynamicForm clearErrors true
            }
        } else {

            val item: DrawItem = selected(0).liveObject
            val itemClass = item.GetClass()
            val itemValues = item.asInstanceOf[PropEditorLiveObject]

            val arrow = itemClass.isMethodSupported("setEndArrow") || itemClass.isMethodSupported("setStartArrow")

            propertyEditorDynamicForm.getField("_constructor").foreach(_.setValueMapString(IscArray(components.sortWith(_ < _): _*)))
            itemValues._constructor.foreach(propertyEditorDynamicForm.setValue("_constructor", _))

            //<editor-fold desc="canDrag">
            if (itemClass.isMethodSupported("setCanDrag")) {
                itemValues.canDrag.foreach(propertyEditorDynamicForm.setValue("canDrag", _))
                propertyEditorDynamicForm.getField("canDrag").foreach(_.show())
            } else
                propertyEditorDynamicForm.getField("canDrag").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="endArrow">
            if (itemClass.isMethodSupported("setEndArrow")) {
                itemValues.endArrow.foreach(item => propertyEditorDynamicForm.setValue("endArrow", item))
                propertyEditorDynamicForm.getField("endArrow").foreach(_.setValueMapString(IscArray(ArrowStyle.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*)))
                propertyEditorDynamicForm.getField("endArrow").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("endArrow").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="fillColor">
            if (itemClass.isMethodSupported("setFillColor") && !arrow) {
                itemValues.fillColor.foreach(propertyEditorDynamicForm.setValue("fillColor", _))
                propertyEditorDynamicForm.getField("fillColor").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("fillColor").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="fillGradient">
            if (itemClass.isMethodSupported("setFillGradient") && !arrow) {
                itemValues.fillGradient.foreach(propertyEditorDynamicForm.setValue("fillGradient", _))
                propertyEditorDynamicForm.getField("fillGradient").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("fillGradient").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="titleLabelProperties">
            if (item.titleLabel.isDefined && item.titleLabel.get.GetClass().isMethodSupported("setFontSize")) {
                itemValues.titleLabelProperties.foreach(propertyEditorDynamicForm.setValue("titleLabelProperties", _))
                propertyEditorDynamicForm.getField("titleLabelProperties").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("titleLabelProperties").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="lineColor">
            if (itemClass.isMethodSupported("setLineColor")) {
                itemValues.lineColor.foreach(propertyEditorDynamicForm.setValue("lineColor", _))
                propertyEditorDynamicForm.getField("lineColor").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("lineColor").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="setLinePattern">
            if (isc.isA.DrawLinePathSS(item)) {
                itemValues.linePattern.foreach(item => propertyEditorDynamicForm.setValue("linePattern", item.toString))
                propertyEditorDynamicForm.getField("linePattern").foreach(_.setValueMapString(IscArray(LinePattern.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*)))
                propertyEditorDynamicForm.getField("linePattern").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("linePattern").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="lineWidth">
            if (itemClass.isMethodSupported("setLineWidth")) {
                itemValues.lineWidth.foreach(propertyEditorDynamicForm.setValue("lineWidth", _))
                propertyEditorDynamicForm.getField("lineWidth").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("lineWidth").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="shadow">
            //isc debugTrap itemClass.isMethodSupported("setShadow")
            if (itemClass.isMethodSupported("setShadow")) {
                //isc debugTrap itemValues.shadow
                itemValues.shadow.foreach(propertyEditorDynamicForm.setValue("shadow", _))
                propertyEditorDynamicForm.getField("shadow").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("shadow").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="startArrow">
            if (itemClass.isMethodSupported("setStartArrow")) {
                itemValues.startArrow.foreach(propertyEditorDynamicForm.setValue("startArrow", _))
                propertyEditorDynamicForm.getField("startArrow").foreach(_.setValueMapString(IscArray(ArrowStyle.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*)))
                propertyEditorDynamicForm.getField("startArrow").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("startArrow").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="titleRotationMode">
            if (itemClass.isMethodSupported("setStartArrow")) {
                propertyEditorDynamicForm.setValue("titleRotationMode", itemValues.titleRotationMode.toString)
                propertyEditorDynamicForm.getField("titleRotationMode").foreach(_.setValueMapString(IscArray(TitleRotationMode.values.filter(!_.toString.contains("$")).map(_.toString).toSeq: _*)))
            } else
                propertyEditorDynamicForm.getField("titleRotationMode").foreach(_.hide())
            //</editor-fold>

            //<editor-fold desc="ID">
            itemValues.ID.foreach(propertyEditorDynamicForm.setValue("ID", _))
            //</editor-fold>

            //<editor-fold desc="rounding">
            if (itemClass.isMethodSupported("setRounding")) {
                itemValues.rounding.foreach(propertyEditorDynamicForm.setValue("rounding", _))
                propertyEditorDynamicForm.getField("rounding").foreach(_.show())
            }
            else
                propertyEditorDynamicForm.getField("rounding").foreach(_.hide())
            //</editor-fold>

            itemValues.keepInParentRect.foreach(propertyEditorDynamicForm.setValue("keepInParentRect", _))
        }

    }

    private def selectedEditNodesUpdated4App(propertyEditorDynamicForm: PropertyEditorDynamicForm): void = {
        val selected = propertyEditorDynamicForm.getSelectedNodes()

        if (selected.length == 0 || selected.length > 1) {
            if (propertyEditorDynamicForm.getItems().isDefined) {
                propertyEditorDynamicForm.clearValues()
                propertyEditorDynamicForm clearErrors true
            }
        } else {

            //isc debugTrap 0

            val item: Class = selected(0).liveObject
            //val itemClass = item.GetClass()
            val itemValues = item.asInstanceOf[PropEditorLiveObject]

            //isc debugTrap itemValues

            itemValues.title.foreach(propertyEditorDynamicForm.setValue("title", _))
            itemValues.description.foreach(propertyEditorDynamicForm.setValue("description", _))

            //itemValues.serializeID.foreach(propertyEditorDynamicForm.setValue("serializeID", _))

            itemValues.listRefs.foreach(propertyEditorDynamicForm.setValue("listRefs", _))

            itemValues.timerProps.foreach(propertyEditorDynamicForm.setValue("timerProps", _))
            itemValues.groupProps.foreach(propertyEditorDynamicForm.setValue("groupProps", _))

            itemValues.messageSMS.foreach(propertyEditorDynamicForm.setValue("messageSMS", _))
            itemValues.variableProps.foreach(propertyEditorDynamicForm.setValue("variableProps", _))
            itemValues.subProgramProps.foreach(propertyEditorDynamicForm.setValue("subProgramProps", _))
            itemValues.multiElementsProps.foreach(propertyEditorDynamicForm.setValue("multiElementsProps", _))

            //isc debugTrap itemValues.loggingProps
            itemValues.loggingProps.foreach(propertyEditorDynamicForm.setValue("loggingProps", _))
        }
    }

    private def getSysFields: Seq[FormItem] = Seq(
        SelectItem(
            new SelectItemProps {
                width = "100%"
                nameStrong = "_constructor".nameStrongOpt
                title = "Тип элемента".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        //isc debugTrap (value, item.nameStrong.get.name)
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                }.toFunc.opt
            }),
        CheckboxItem(
            new CheckboxItemProps {
                width = "100%"
                nameStrong = "canDrag".nameStrongOpt
                title = "Перемещаемый".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                }.toFunc.opt
            }),
        SelectItem(
            new SelectItemProps {
                width = "100%"
                nameStrong = "endArrow".nameStrongOpt
                title = "Конечная стрелка".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)
                }.toFunc.opt
            }),
        ColorItem(
            new ColorItemProps {
                width = "100%"
                nameStrong = "fillColor".nameStrongOpt
                defaultPickerMode = ColorPickerMode.complex.opt
                title = "Цвет заливки".opt
                supportsTransparency = true.opt
                pickerColorSelected = {
                    (thiz: ColorItem, color: CSSColor, opacity: JSUndefined[Int]) =>

                        val form = thiz.form.asInstanceOf[PropertyEditorDynamicForm]
                        form.setPropertyOnSelection("fillGradient", jSUndefined)
                        form.setPropertyOnSelection(thiz.nameStrong.get.name, color)

                }.toThisFunc.opt
            }),
        GradientItem(
            new GradientItemProps {
                changed = {
                    (form: PropertyEditorDynamicForm, item: GradientItem, value: JSUndefined[SimpleGradient]) ⇒
                        //isc debugTrap(form, value)
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                }.toFunc.opt
            }
        ),
        ShadowItem(new ShadowItemProps),
        ColorItem(
            new ColorItemProps {
                width = "100%"
                nameStrong = "lineColor".nameStrongOpt
                title = "Цвет линии".opt
                supportsTransparency = true.opt
                defaultPickerMode = ColorPickerMode.complex.opt
                pickerColorSelected = {
                    (thiz: ColorItem, color: CSSColor, opacity: JSUndefined[Int]) =>
                        val form = thiz.form.asInstanceOf[PropertyEditorDynamicForm]
                        form.setPropertyOnSelection(thiz.nameStrong.get.name, color)

                }.toThisFunc.opt
            }),
        SelectItem(
            new SelectItemProps {
                width = "100%"
                nameStrong = "linePattern".nameStrongOpt
                title = "Тип линии".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                }.toFunc.opt
            }),
        SpinnerItem(
            new SpinnerItemProps {
                width = "100%"
                defaultValue = 1.asInstanceOf[JSAny].opt
                min = 1.0.opt
                max = 5.0.opt
                step = 1.0.opt
                nameStrong = "lineWidth".nameStrongOpt
                title = "Толщина линии".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value.asInstanceOf[Int])
                }.toFunc.opt
            }),
        FontItem(
            new FontItemProps {
                changed = {
                    (form: PropertyEditorDynamicForm, item: FontItem, value: JSUndefined[DrawLabel]) ⇒

                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                        //isc debugTrap(form, form.getDrawItem(), formItem, value)
                        form.getDrawItem().foreach {
                            drawItem ⇒

                                val _drawItem = drawItem.liveObject.asInstanceOf[DrawItem]
                                _drawItem.titleLabel.foreach(item ⇒ _drawItem.titleLabel = isc.addProperties(item, _drawItem.titleLabelProperties))

                                _drawItem.moveBy(1, 0)
                                _drawItem.moveBy(-1, 0)
                        }

                }.toFunc.opt
            }
        ),
        SelectItem(
            new SelectItemProps {
                width = "100%"
                nameStrong = "startArrow".nameStrongOpt
                title = "Начальная стрелка".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)
                }.toFunc.opt
            }),
        SelectItem(
            new SelectItemProps {
                width = "100%"
                nameStrong = "titleRotationMode".nameStrongOpt
                title = "Режим поворота надписи".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)
                        canvasEditContext.getSelectedEditNodes().foreach {
                            formItem =>
                                //isc debugTrap (formItem, formItem.liveObject.asInstanceOf[DrawLinePathSS].moveStartPointTo _)
                                formItem.liveObject._updateTitleLabelAndBackground()
                        }
                }.toFunc.opt
            }),
        TextItemSS(
            new TextItemSSProps {
                width = "100%"
                nameStrong = "ID".nameStrongOpt
                title = "Уникальный код".opt
                change = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[ID], oldValue: JSUndefined[ID]) =>
                        val res = if (value.isEmpty || value.get.isEmpty) {
                            isc error s"Поле: ${item.nameStrong} не может быть пустым."
                            false
                        } else if (Seq("_", " ", "+", "_", "(", ")", "[", "]", ".", ",", ";", ":").filter(item ⇒ value.get.contains(item)).length > 0) {
                            isc error s"Недопустимый символ."
                            false
                        } else
                            getDrawItems().find(_.ID == value) match {
                                case None ⇒ true
                                case Some(x) ⇒
                                    isc error s"Элемент с ID: $value уже существует."
                                    false
                            }

                        //isc debugTrap getDrawItems()

                        //@tailrec
                        def renameFiledName(templateObject: TemplateObject, criteria: JSUndefined[IscArray[Criterion]], value: ID): Unit = {
                            if (criteria.isDefined)
                                criteria.get.foreach {
                                    criteria ⇒
                                        //isc debugTrap criteria
                                        if (criteria.criteria.isDefined)
                                            renameFiledName(templateObject, criteria.criteria, value)
                                        else
                                            criteria.fieldName.foreach {
                                                fieldName ⇒
                                                    oldValue.foreach {
                                                        oldValue ⇒
                                                            val undPos = fieldName.indexOf("_")
                                                            val _fieldName = if (undPos != -1) fieldName.substring(0, undPos) else fieldName

                                                            //isc debugTrap(_fieldName, oldValue)
                                                            if (_fieldName == oldValue) {
                                                                val _value = if (undPos != -1) value + "_" + fieldName.substring(undPos + 1) else value
                                                                //isc debugTrap(_value, 0)
                                                                criteria.fieldName = _value
                                                            }
                                                    }
                                            }
                                }
                        }


                        if (res) {
                            val templateObject: TemplateObject = TemplateObject(getDrawItems())

                            //Изменение в критериях
                            value.foreach(value ⇒ getDrawItems().foreach(_.criteria.foreach(criteria ⇒ renameFiledName(templateObject, IscArray(criteria.asInstanceOf[Criterion]).undef, value))))

                            //Изменение в составных компонентах
                            value.foreach(value ⇒ getDrawItems().filter(_.multiElementsProps.isDefined).foreach {
                                drawItem ⇒
                                    val _drawItem = drawItem.multiElementsProps.get.asInstanceOf[MultiElementsValue]
                                    //isc debugTrap _drawItem.variables
                                    _drawItem.variables.foreach {
                                        variables ⇒ variables.toSeq.foreach {
                                            case (key, variableValue) ⇒
                                                oldValue.foreach {
                                                    oldValue ⇒
                                                        //isc debugTrap(variableValue.routineCode, oldValue, value)
                                                        variableValue.routineCode.foreach {
                                                            _ ⇒
                                                                variableValue.asInstanceOf[JSDynamic].updateDynamic("routineCode")(variableValue.routineCode.get.replace(s"${oldValue}_", s"${value}_"))
                                                        }
                                                    //isc debugTrap variableValue.routineCode
                                                }
                                        }
                                    }

                                    _drawItem.subPrograms.foreach {
                                        subPrograms ⇒ subPrograms.toSeq.foreach {
                                            case (key, subProgramValue) ⇒
                                                oldValue.foreach {
                                                    oldValue ⇒
                                                        //isc debugTrap(variableValue.routineCode, oldValue, value)
                                                        subProgramValue.routineCode.foreach {
                                                            _ ⇒
                                                                subProgramValue.asInstanceOf[JSDynamic].updateDynamic("routineCode")(subProgramValue.routineCode.get.replace(s"${oldValue}_", s"${value}_"))
                                                        }
                                                    //isc debugTrap variableValue.routineCode
                                                }
                                        }
                                    }
                            })
                        }
                        res
                }.toFunc.opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[ID]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)
                }.toFunc.opt
            }),
        CheckboxItem(
            new CheckboxItemProps {
                width = "100%"
                nameStrong = "keepInParentRect".nameStrongOpt
                title = "Нахождение только в видимой зоне".opt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value)

                }.toFunc.opt
            }),
        FloatItem(
            new FloatItemProps {
                decimalPrecision = 2.opt
                width = "100%"
                title = "Скругление".opt
                nameStrong = "rounding".nameStrongOpt
                changed = {
                    (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                        form.setPropertyOnSelection(item.nameStrong.get.name, value.toString.toDouble)

                }.toFunc.opt
            })
    )

    private def getAppFields: Seq[FormItem] = {
        val res = Seq(
            TextAreaItemSS(
                new TextAreaItemSSProps {
                    width = "100%"
                    height = 50
                    nameStrong = "title".nameStrongOpt
                    title = "Текст".opt
                    changed = {
                        (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[JSAny]) =>
                            form.setPropertyOnSelection(item.nameStrong.get.name, value)
                    }.toFunc.opt
                }),
            TextAreaItemSS(
                new TextAreaItemSSProps {
                    width = "100%"
                    height = 50
                    nameStrong = "description".nameStrongOpt
                    title = "Описание".opt
                    height = 100
                    changed = {
                        (form: PropertyEditorDynamicForm, item: FormItem, value: JSUndefined[String]) =>
                            form.setPropertyOnSelection(item.nameStrong.get.name, value)
                    }.toFunc.opt
                }) /*,
            IntegerItem(
                new IntegerItemProps {
                    width = "100%"
                    validators = Seq(
                        Validator(
                            new ValidatorProps {
                                `type` = ValidatorType.isInteger.opt
                            }
                        ),
                        Validator(
                            new ValidatorProps {
                                `type` = ValidatorType.required.opt
                            }
                        )
                    ).opt
                    nameStrong = "serializeID".nameStrongOpt
                    title = "ID сериализации".opt
                    validateOnExit = true.opt
                    changed = {
                        (form: PropertyEditorDynamicForm, item: FormItem, value: JSAny) =>
                            form.setPropertyOnSelection(item.nameStrong.get.name, value)
                    }.toFunc.opt
                })*/
        )

        val selectesItem = getSelectedItem()
        def addLogiingItem(templateObject: TemplateObject, gluedElements: TemplateObject) = {
            import TemplateObject._

            Seq(LoggingItem(
                new LoggingItemProps {
                    loggingValueMap4String = {
                        {
                            val strTypes = Seq(ScalaTypes.Int.toString, ScalaTypes.String.toString, ScalaTypes.Long.toString, ScalaTypes.Double.toString, ScalaTypes.AtomicInteger.toString, ScalaTypes.Boolean.toString, ScalaTypes.AtomicDouble.toString, ScalaTypes.AtomicLong.toString)

                            templateObject.getTransitionVariablesOfTypes(strTypes).getIDandTitle() ++
                              gluedElements.getVariablesOfReturnTypes(strTypes).getIDandTitle() ++
                              templateObject.getTransitionSubProgramsOfTypes(strTypes).getIDandTitle() ++
                              gluedElements.getSubProgramsOfReturnTypes(strTypes).getIDandTitle()

                        }.distinct.sortWith(_.title < _.title).foldLeft(js.Dictionary.empty[String]) {
                            (dict, item) ⇒ dict(item.ID) = item.title; dict
                        }
                    }.opt

                    loggingValueMap4Double = {
                        {
                            val doubleTypes = Seq(ScalaTypes.Double.toString, ScalaTypes.AtomicDouble.toString, ScalaTypes.Int.toString, ScalaTypes.AtomicInteger.toString, ScalaTypes.Long.toString, ScalaTypes.AtomicLong.toString)

                            templateObject.getTransitionVariablesOfTypes(doubleTypes).getIDandTitle() ++
                              gluedElements.getVariablesOfReturnTypes(doubleTypes).getIDandTitle() ++
                              templateObject.getTransitionSubProgramsOfTypes(doubleTypes).getIDandTitle() ++
                              gluedElements.getSubProgramsOfReturnTypes(doubleTypes).getIDandTitle()

                        }.distinct.sortWith(_.title < _.title).foldLeft(js.Dictionary.empty[String]) {
                            (dict, item) ⇒ dict(item.ID) = item.title; dict
                        }
                    }.opt

                    loggingValueMap4Long = {
                        {
                            val longTypes = Seq(ScalaTypes.Int.toString, ScalaTypes.AtomicInteger.toString, ScalaTypes.Long.toString, ScalaTypes.AtomicLong.toString)

                            templateObject.getTransitionVariablesOfTypes(longTypes).getIDandTitle() ++
                              gluedElements.getVariablesOfReturnTypes(longTypes).getIDandTitle() ++
                              templateObject.getTransitionSubProgramsOfTypes(longTypes).getIDandTitle() ++
                              gluedElements.getSubProgramsOfReturnTypes(longTypes).getIDandTitle()

                        }.distinct.sortWith(_.title < _.title).foldLeft(js.Dictionary.empty[String]) {
                            (dict, item) ⇒ dict(item.ID) = item.title; dict
                        }
                    }.opt

                    //isc debugTrap(loggingValueMap4Double, loggingValueMap4Long, loggingValueMap4String)
                }
            ))
        }

        val res1 = getSelectedTypeDrawItem match {
            case ListAnything.typeName ⇒
                res ++ Seq(FormItemList(new FormItemListProps))
            case SubProgram.typeName ⇒
                res ++ Seq(SubProgramItem(new SubProgramItemProps))
            case SendMessage.typeName ⇒
                res ++ Seq(SendMessageItem(new SendMessageItemProps))
            case CalcGroup.typeName ⇒
                res ++ Seq(CalcGroupItem(new CalcGroupItemProps {
                    identifier = selectesItem.getID().opt
                }))
            case Variable.typeName ⇒
                res ++ Seq(VariableItem(new VariableItemProps))
            case CounterVariable.typeName ⇒
                res ++ Seq(CounterAccumItem(new CounterAccumItemProps))
            case StartState.typeName ⇒

                val templateObject: TemplateObject = TemplateObject(getDrawItems())
                val gluedElements: TemplateObject = TemplateObject(templateObject.getGluedElements(getSelectedItem()))

                res ++ addLogiingItem(templateObject, gluedElements)
            case StopState.typeName ⇒

                val templateObject: TemplateObject = TemplateObject(getDrawItems())
                val gluedElements: TemplateObject = TemplateObject(templateObject.getGluedElements(getSelectedItem()))

                res ++ addLogiingItem(templateObject, gluedElements)
            case StateUnified.typeName ⇒

                val templateObject: TemplateObject = TemplateObject(getDrawItems())
                val gluedElements: TemplateObject = TemplateObject(templateObject.getGluedElements(getSelectedItem()))

                res ++ addLogiingItem(templateObject, gluedElements)
            case TimerUnified.typeName ⇒
                res ++ Seq(TimerItem(new TimerItemProps))
            case Group.typeName ⇒
                res ++ Seq(GroupItem(new GroupItemProps {
                    nameValueMap = IscArray(codeCmpgn).opt
                }))
            case _ ⇒
                res
        }

        //isc debugTrap 1
        res1
    }

    private def getSelectedNodes(propertyEditorDynamicForm: PropertyEditorDynamicForm): IscArray[EditNode] = {
        if (propertyEditorDynamicForm.editContext.isDefined)
            propertyEditorDynamicForm.editContext.get.getSelectedEditNodes()
        else
            IscArray[EditNode]()
    }

    private def setPropertyOnSelection(propertyEditorDynamicForm: PropertyEditorDynamicForm, property: String, _value: JSUndefined[JSAny]): void = {

        val selected = propertyEditorDynamicForm.getSelectedNodes()
        //isc debugTrap selected
        selected.foreach {
            item =>
                //isc debugTrap item
                propertyEditorDynamicForm.editContext.foreach(_.setNodeProperties(item, js.Dictionary(property -> _value.getOrElse(null))))
                if (_value.isEmpty) {
                    propertyEditorDynamicForm.editContext.foreach(_.setNodeProperties(item, js.Dictionary(property -> null)))
                    propertyEditorDynamicForm.editContext.foreach(_.removeNodeProperties(item, IscArray(property)))
                }
        }
    }

    def getOptionSelectedDrawItem(): JSUndefined[DrawItem] = {

        val selected = self.canvasEditContext.getSelectedEditNodes()
        if (selected.length != 1)
            jSUndefined
        else
            selected(0).asInstanceOf[DrawItem]
    }

    def getSelectedItem(): DrawItem =
        getOptionSelectedDrawItem().toOption match {
            case None ⇒ throw new RuntimeException("Не выбран элемент.")
            case Some(x) ⇒ x.liveObject.asInstanceOf[DrawItem]
        }

    def getSelectedTypeDrawItem: String = {
        val item: JSUndefined[DrawItem] = getOptionSelectedDrawItem()
        if (item.isEmpty)
            ""
        else
            item.get._constructor.getOrElse("")
    }

    def getSelectedTypeDrawItemTitle: String = {
        val item = getOptionSelectedDrawItem()
        //isc debugTrap item.get.defaults
        if (item.isEmpty)
            ""
        else
            item.get.defaults.title.get
    }

    private lazy val sysPropertyEditorDynamicForm = PropertyEditorDynamicForm.create(
        new PropertyEditorDynamicFormProps {
            editContext = self.canvasEditContext.opt
            stopOnError = true.opt
            validateOnExit = true.opt
            validateOnChange = true.opt
            numCols = 2.opt
            height = "*"
            colWidths = Seq[JSAny](100, "*").opt
            getSelectedNodes = (self.getSelectedNodes _).toThisFunc.opt
            setPropertyOnSelection = (self.setPropertyOnSelection _).toThisFunc.opt
            selectedEditNodesUpdated = (self.selectedEditNodesUpdated4Sys _).toThisFunc.opt
            getDrawItem = (self.getOptionSelectedDrawItem _).toFunc.opt
            items = getSysFields.opt
        }
    )

    private lazy val appPropertyEditorDynamicForm = PropertyEditorDynamicForm.create(
        new PropertyEditorDynamicFormProps {
            editContext = self.canvasEditContext.opt
            stopOnError = true.opt
            validateOnExit = true.opt
            validateOnChange = true.opt
            numCols = 2.opt
            height = "100"
            colWidths = Seq[JSAny](100, "*").opt
            getSelectedNodes = (self.getSelectedNodes _).toThisFunc.opt
            setPropertyOnSelection = (self.setPropertyOnSelection _).toThisFunc.opt
            selectedEditNodesUpdated = (self.selectedEditNodesUpdated4App _).toThisFunc.opt
            getDrawItem = (self.getOptionSelectedDrawItem _).toFunc.opt
            items = getAppFields.opt
        }
    )

    private lazy val tabSetID = s"${identifier}TabSet"

    private lazy val keyTabSet = s"${tabSetID}_${getSelectedTypeDrawItem}_selectedTab"
    def getFieldDataSourse(): ScOption[DataSource] = {
        val drawItem: JSUndefined[DrawItem] = getOptionSelectedDrawItem()
        if (drawItem.isEmpty)
            ScNone
        else {
            if (drawItem.get.liveObject.fieldDataSource.isEmpty)
                ScNone
            else
                drawItem.get.liveObject.fieldDataSource.get.opt
        }
    }

    private lazy val filterBuilder = FilterBuilderSS.create(
        new FilterBuilderSSProps {
            filterChanged = {
                (thiz: classHandler) =>
                    val selected = sysPropertyEditorDynamicForm.getSelectedNodes()
                    if (thiz.validate()) {
                        selected.foreach {
                            item =>
                                self.canvasEditContext.setNodeProperties(item, js.Dictionary("criteria" -> thiz.getCriteria()))
                                if (isc.isA.emptyObject(thiz.getCriteria()))
                                    self.canvasEditContext.removeNodeProperties(item, IscArray("criteria"))
                        }
                        sysPropertyEditorDynamicForm.setValue("criteria", isc.JSON.encode(thiz.getCriteria()))
                    } else
                        isc error "Ошибка заполнения."
            }.toThisFunc.opt
            fieldDataSource = getFieldDataSourse()
            allowEmpty = true.opt
        }
    )

    lazy val tabSet: TabSetSS = {
        val tab = TabSetSS.create(
            new TabSetSSProps {
                identifier = tabSetID.opt
                selectedTab = OfflineSS.getNumber(keyTabSet, 0).toInt.opt
                canReorderTabs = true.opt
                canCloseTabs = false.opt
                tabSelected = {
                    (thiz: classHandler, tabNum: Int, tabPane: Canvas, ID: JSUndefined[ID], tab: Tab, name: JSUndefined[String]) =>
                        if (thiz.isDrawn())
                            OfflineSS.putNumber(keyTabSet, tabNum)

                        name.foreach(name =>
                            name match {
                                case "filterBuilder" =>
                                    self.canvasEditContext.getSelectedEditNodes().foreach(
                                        item => {
                                            val _item = item.liveObject.asInstanceOf[PropEditorLiveObject]

                                            val cacheData = IscArray[Record]()
                                            filterBuilder.fieldDataSource.cacheData1.foreach(cacheData add _)

                                            val addedOperator = Seq(
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "likeWithOutPunct".opt
                                                        title = "Соответствие шаблону без знаков пунктуации".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    }),
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "likeSQL".opt
                                                        title = "Соответствие шаблону SQL запроса (с учетом регистра символов) (LIKE)".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    }),
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "iLikeSQL".opt
                                                        title = "Соответствие шаблону SQL запроса (без учета регистра символов) (UPPERCASE(LIKE))".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    }),
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "notLikeSQL".opt
                                                        title = "Исключение соответствия шаблону SQL запроса (с учетом регистра символов) (NOT LIKE)".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    }),
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "iNotLikeSQL".opt
                                                        title = "Исключение соответствия шаблону SQL запроса (без учета регистра символов) (UPPERCASE(NOT LIKE))".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    }),
                                                Operator(
                                                    new OperatorProps {
                                                        ID = "regexp".opt
                                                        title = "Соответствие шаблону RegExp".opt
                                                        editorType = FormItemComponentType.TextItem
                                                        valueType = OperatorValueType.fieldType.opt
                                                    })
                                            )

                                            val incomingMessageItem: JSUndefined[DrawItem] = getOptionSelectedDrawItem()

                                            //isc debugTrap (incomingMessageItem)
                                            if (incomingMessageItem.isEmpty)
                                                addedOperator.foreach(filterBuilder.fieldDataSource removeSearchOperator _)
                                            else {
                                                if (incomingMessageItem.get._constructor == IncomingMessage.typeName)
                                                    addedOperator.foreach(filterBuilder.fieldDataSource addSearchOperator _)
                                                else
                                                    addedOperator.foreach(filterBuilder.fieldDataSource removeSearchOperator _)
                                            }

                                            val templateObject = TemplateObject(getDrawItems())
                                            val gluedElements = TemplateObject(templateObject.getGluedElements(getSelectedItem().sourceConnect))

                                            templateObject.getLists.foreach {
                                                drawItem ⇒
                                                    drawItem.listRefs.foreach {
                                                        listRefs ⇒
                                                            val _listRefs = listRefs.asInstanceOf[ScenariosListsDataRecord]

                                                            cacheData add DataSourceField(
                                                                new DataSourceFieldProps {
                                                                    name = _listRefs.code_list.opt
                                                                    `type` = FieldType.enum
                                                                    title = s"${_listRefs.caption_list} Группа: ${_listRefs.caption_list}".opt
                                                                    validOperators = Seq(OperatorId.iNotContains, OperatorId.iContains).opt
                                                                    valueMap = js.Dictionary("address" → "Номер телефона".asInstanceOf[JSAny]).opt
                                                                })
                                                    }
                                            }
                                            def addSubProgram(drawItems: IscArray[DrawItem], gluedList: Option[IscArray[DataSourceField]] = None): IscArray[DataSourceField] =
                                                IscArray(
                                                    drawItems.map {
                                                        drawItem ⇒
                                                            drawItem.subProgramProps.flatMap {
                                                                routineProps ⇒
                                                                    routineProps.asInstanceOf[SubProgramValue].returnType.flatMap {
                                                                        returnType ⇒
                                                                            if (gluedList.isEmpty || gluedList.get.find(_.identifier == drawItem.ID).isEmpty) {
                                                                                val res = DataSourceField(
                                                                                    new DataSourceFieldProps {
                                                                                        identifier = drawItem.ID.opt
                                                                                        name = (if (gluedList.isDefined) s"data.${drawItem.getID}" else drawItem.getID).opt
                                                                                        `type` = {
                                                                                            returnType match {
                                                                                                case x if x == ScalaTypes.String.toString ⇒ FieldType.text
                                                                                                case x if x == ScalaTypes.Double.toString ⇒ FieldType.float
                                                                                                case x if x == ScalaTypes.Long.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.Int.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.Boolean.toString ⇒ FieldType.boolean
                                                                                                /*case x if x == ScalaTypes.AtomicInteger.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.AtomicDouble.toString ⇒ FieldType.float*/
                                                                                                case _ ⇒ FieldType.text
                                                                                            }
                                                                                        }
                                                                                        title = s"${if (gluedList.isDefined) "->" else ""} ${drawItem.title.getOrElse("Unknown").replace("\n", " ")}".opt
                                                                                        validators = {
                                                                                            returnType match {
                                                                                                case x if x == ScalaTypes.String.toString ⇒ ScNone
                                                                                                case x if x == ScalaTypes.Boolean.toString ⇒ ScNone
                                                                                                case x if x == ScalaTypes.Double.toString ⇒ Seq(Validators.floatValidator).opt
                                                                                                /*case x if x == ScalaTypes.AtomicDouble.toString ⇒ Seq(Validators.floatSummaValidator).opt
                                                                                                case x if x == ScalaTypes.AtomicInteger.toString ⇒ Seq(Validators.intSummaValidator).opt*/
                                                                                                case x if x == ScalaTypes.Int.toString ⇒ Seq(Validators.intValidator).opt
                                                                                                case x if x == ScalaTypes.Long.toString ⇒ Seq(Validators.intValidator).opt
                                                                                                case _ ⇒ Seq(Validators.noneValidator).opt
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                )
                                                                                cacheData add res
                                                                                js.UndefOr.any2undefOrA(res)
                                                                            }
                                                                            else
                                                                                jSUndefined
                                                                    }
                                                            }
                                                    }.filter(_.isDefined).map(_.get): _*
                                                )

                                            addSubProgram(templateObject.getTransitionSubPrograms, Some(addSubProgram(gluedElements.getTransitionSubPrograms)))

                                            def addVariable(drawItems: IscArray[DrawItem], gluedList: Option[IscArray[DataSourceField]] = None): IscArray[DataSourceField] =
                                                IscArray(
                                                    drawItems.map {
                                                        drawItem ⇒
                                                            drawItem.variableProps.flatMap {
                                                                routineProps ⇒
                                                                    routineProps.asInstanceOf[VariableValue].returnType.flatMap {
                                                                        returnType ⇒
                                                                            if (gluedList.isEmpty || gluedList.get.find(_.identifier == drawItem.ID).isEmpty) {
                                                                                val res = DataSourceField(
                                                                                    new DataSourceFieldProps {
                                                                                        identifier = drawItem.ID.opt
                                                                                        name = (if (gluedList.isDefined) s"data.${drawItem.getID()}" else drawItem.getID).opt
                                                                                        `type` = {
                                                                                            returnType match {
                                                                                                case x if x == ScalaTypes.String.toString ⇒ FieldType.text
                                                                                                case x if x == ScalaTypes.Double.toString ⇒ FieldType.float
                                                                                                case x if x == ScalaTypes.Long.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.Int.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.Boolean.toString ⇒ FieldType.boolean
                                                                                                case x if x == ScalaTypes.AtomicInteger.toString ⇒ FieldType.integer
                                                                                                case x if x == ScalaTypes.AtomicDouble.toString ⇒ FieldType.float
                                                                                                case _ ⇒ FieldType.text
                                                                                            }
                                                                                        }
                                                                                        title = s"${if (gluedList.isDefined) "->" else ""} ${drawItem.title.getOrElse("Unknown").replace("\n", " ")}".opt
                                                                                        validators = {
                                                                                            returnType match {
                                                                                                case x if x == ScalaTypes.String.toString ⇒ ScNone
                                                                                                case x if x == ScalaTypes.Boolean.toString ⇒ ScNone
                                                                                                case x if x == ScalaTypes.Double.toString ⇒ Seq(Validators.floatValidator).opt
                                                                                                case x if x == ScalaTypes.AtomicDouble.toString ⇒ Seq(Validators.floatValidator).opt
                                                                                                case x if x == ScalaTypes.AtomicInteger.toString ⇒ Seq(Validators.intValidator).opt
                                                                                                case x if x == ScalaTypes.Int.toString ⇒ Seq(Validators.intValidator).opt
                                                                                                case x if x == ScalaTypes.Long.toString ⇒ Seq(Validators.intValidator).opt
                                                                                                case _ ⇒ Seq(Validators.noneValidator).opt
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                )
                                                                                cacheData add res
                                                                                js.UndefOr.any2undefOrA(res)
                                                                            }
                                                                            else
                                                                                jSUndefined
                                                                    }
                                                            }
                                                    }.filter(_.isDefined).map(_.get): _*
                                                )

                                            addVariable(templateObject.getTransitionVariables, Some(addVariable(gluedElements.getTransitionVariables)))

                                            filterBuilder.fieldDataSource setCacheData IscArray(cacheData.map(_.asInstanceOf[DataSourceField]).sortWith(_.title < _.title): _*)
                                            filterBuilder setCriteria _item.criteria.asInstanceOf[AdvancedCriteria]
                                        }
                                    )
                                case "appProps" =>
                                    appPropertyEditorDynamicForm.selectedEditNodesUpdated()
                                case "systemProps" =>
                                    sysPropertyEditorDynamicForm.selectedEditNodesUpdated()
                            }
                        )
                        false
                }.toThisFunc.opt
            })


        if (getFieldDataSourse.isDefined)
            tab addTabs
              Tab(
                  new TabProps {
                      pane = filterBuilder.opt
                      name = "filterBuilder".opt
                      title = s"${
                          CanvasStatic.imgHTML(Common.filter, 16, 14)
                      } Фильтры".opt
                  })

        tab addTabs
          IscArray(
              Tab(
                  new TabProps {
                      pane = appPropertyEditorDynamicForm.opt
                      name = "appProps".opt
                      title = s"${
                          CanvasStatic.imgHTML(Common.accounts, 16, 14)
                      } Прикладные".opt
                  }),
              Tab(
                  new TabProps {
                      pane = sysPropertyEditorDynamicForm.opt
                      name = "systemProps".opt
                      title = s"${CanvasStatic.imgHTML(Common.systemservice, 16, 14)} Системные".opt

                  })
          )

        tab selectTab OfflineSS.getNumber(keyTabSet, 0).toInt
        tab
    }
}
