package com.simplesys.js.components.formItems.props

import com.simplesys.SmartClient.Control.Button
import com.simplesys.SmartClient.Control.props.IButtonSSProps
import com.simplesys.SmartClient.Forms.formsItems.props.FormItemWithButtonsProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.app.WindowListEditor
import com.simplesys.function._
import com.simplesys.js.components.formItems.FormItemList
import com.simplesys.js.components.props.WindowListEditorProps
import com.simplesys.js.components.{ListEditor, PropertyEditorDynamicForm}
import com.simplesys.option.DoubleType._
import com.simplesys.option.ScOption._
import ru.simplesys.defs.app.gen.scala.ScalaJSGen._
import ru.simplesys.defs.app.scala.container.ListsDataRecord

import scala.scalajs.js

class FormItemListProps extends FormItemWithButtonsProps {
    type classHandler <: FormItemList

    typeEditorField = FormItemComponentType.TextAreaItem

    nameStrong = "listRefs".nameStrongOpt
    title = "Список".opt
    showTitle = false.opt
    colSpan = 2.opt

    clearValue = {
        (thiz: classHandler) ⇒
            thiz.Super("clearValue")

            thiz.innerForm.foreach {
                innerForm ⇒
                    innerForm.setValue(scenarios_Lists_caption_list_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_caption_listGroup_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_code_list_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_description_list_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_id_list_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_id_listGroup_ref_NameStrong.name, null)
                    innerForm.setValue(scenarios_Lists_type_list_NameStrong.name, null)
            }
    }.toThisFunc.opt

    setValue = {
        (thiz: classHandler, value: JSUndefined[ListsDataRecord]) ⇒
            value.foreach {
                value ⇒
                    if (value == null)
                        thiz.clearValue()
                    else {
                        thiz.innerForm.foreach {
                            innerForm ⇒
                                //isc debugTrap value
                                innerForm.setValue(scenarios_Lists_caption_list_NameStrong.name, value.caption_list)
                                innerForm.setValue(scenarios_Lists_caption_listGroup_NameStrong.name, value.caption_listGroup)
                                innerForm.setValue(scenarios_Lists_code_list_NameStrong.name, value.code_list)
                                innerForm.setValue(scenarios_Lists_description_list_NameStrong.name, value.description_list)
                                innerForm.setValue(scenarios_Lists_id_list_NameStrong.name, value.id_list)
                                innerForm.setValue(scenarios_Lists_id_listGroup_ref_NameStrong.name, value.id_listGroup_ref)
                                innerForm.setValue(scenarios_Lists_type_list_NameStrong.name, value.type_list)
                        }
                    }
            }
            thiz.Super("setValue", IscArray(value))

    }.toThisFunc.opt

    init = {
        (thisTop: classHandler, args: IscArray[JSAny]) ⇒
            import com.simplesys.SmartClient.Forms.formsItems.props.CancelButtonProps
            import com.simplesys.SmartClient.Forms.props.DynamicFormSSProps

            thisTop.innerForm = DynamicFormSS.create(
                new DynamicFormSSProps {

                    import com.simplesys.SmartClient.Forms.formsItems.props.{FormItemProps, TextAreaItemSSProps, TextItemSSProps}
                    import ru.simplesys.defs.app.gen.scala.ScalaJSGen._

                    width = "100%"
                    isGroup = true.opt
                    groupTitle = "Список".opt
                    fields = Seq(
                        FormItem(
                            new FormItemProps {
                                nameStrong = scenarios_Lists_id_list_NameStrong.opt
                                `type` = FormItemType.id_SimpleType
                                title = "Идентификатор списка".opt
                                hidden = true.opt
                            }),
                        TextItemSS(
                            new TextItemSSProps {
                                nameStrong = scenarios_Lists_code_list_NameStrong.opt
                                title = "Код".opt
                                readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt
                            }),
                        TextItemSS(
                            new TextItemSSProps {
                                nameStrong = scenarios_Lists_type_list_NameStrong.opt
                                title = "Тип элемента".opt
                                readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt
                            }),
                        TextItemSS(
                            new TextItemSSProps {
                                nameStrong = scenarios_Lists_caption_list_NameStrong.opt
                                title = "Наименование".opt
                                readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt
                            }),
                        TextAreaItemSS(
                            new TextAreaItemSSProps {
                                nameStrong = scenarios_Lists_description_list_NameStrong.opt
                                title = "Описание".opt
                                readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt
                            }),
                        FormItem(
                            new FormItemProps {
                                nameStrong = scenarios_Lists_id_listGroup_ref_NameStrong.opt
                                title = "Идентификатор группы".opt
                                `type` = FormItemType.id_SimpleType
                                hidden = true.opt
                            }),
                        TextItemSS(
                            new TextItemSSProps {
                                title = "Наименование группы".opt
                                nameStrong = scenarios_Lists_caption_listGroup_NameStrong.opt
                                readOnlyDisplay = ReadOnlyDisplayAppearance.readOnly.opt

                            })).opt
                }
            )

            thisTop.buttons =
              IscArray[Button](
                  IButtonSS.create(
                      new IButtonSSProps {
                          width = 20
                          icon = Common.accounts.opt
                          click = {
                              (thiz: classHandler) =>
                                  val windowListEditor = WindowListEditor.create(
                                      new WindowListEditorProps {
                                          height = 500
                                          width = 300
                                          identifier = "DAF9E379-E39F-89DD-79EA-577AC820D06A".opt
                                          okFunction = {
                                              (thiz: classHandler) ⇒
                                                  thiz.wrapCanvas.foreach {
                                                      listEditor ⇒
                                                          val _listEditor = listEditor.asInstanceOf[ListEditor]
                                                          if (_listEditor.listGrid.getSelectedRecords().length == 0)
                                                              isc.error(s"Не сделан выбор.")
                                                          else if (_listEditor.listGrid.getSelectedRecords().length > 1)
                                                              isc.error(s"Множественный выбор недопустим.")
                                                          else {

                                                              val _form = thisTop.form.asInstanceOf[PropertyEditorDynamicForm]

                                                              //isc debugTrap _form
                                                              val res = isc.deletePrivateProps(_listEditor.listGrid.getSelectedRecord)
                                                              _form.setPropertyOnSelection("listRefs", res)
                                                              //isc debugTrap thisTop.editingItem

                                                              thisTop setValue res
                                                              thiz.markForDestroy()
                                                          }
                                                  }

                                          }.toThisFunc.opt
                                      }
                                  )

                                  windowListEditor.wrapCanvas.foreach {
                                      wrapperCanvas ⇒

                                          val listPhoneEditor = wrapperCanvas.asInstanceOf[ListEditor]
                                          listPhoneEditor.fetchTreeData(
                                              callback = {
                                                  import com.simplesys.SmartClient.DataBinding.{DSRequest, DSResponse}
                                                  (resp: DSResponse, data: JSObject, req: DSRequest) ⇒

                                                      import ru.simplesys.defs.app.gen.scala.ScalaJSGen.scenarios_Lists_id_listGroup_ref_NameStrong
                                                      import ru.simplesys.defs.app.scala.container.ListsDataRecord

                                                      val listsDataRecord = thisTop.getValue().asInstanceOf[ListsDataRecord]
                                                      //isc debugTrap listsDataRecord

                                                      if (listsDataRecord != null) {
                                                          listPhoneEditor.selectTreeSingleRecordByKey(listsDataRecord.id_listGroup_ref)

                                                          listPhoneEditor.fetchListData(
                                                              criteria = js.Dictionary(scenarios_Lists_id_listGroup_ref_NameStrong.name → listsDataRecord.id_listGroup_ref),
                                                              callback = {
                                                                  (resp: DSResponse, data: JSObject, req: DSRequest) ⇒
                                                                      listPhoneEditor.selectListSingleRecordByKey(js.Dictionary("id_list" → listsDataRecord.id_list.toString.toLong))
                                                              }
                                                          )
                                                      }
                                              }
                                          )
                                  }
                                  true
                          }.toThisFunc.opt
                      }),
                  CancelButton.create(
                      new CancelButtonProps {
                          width = 20
                          click = {
                              (thiz: classHandler) =>
                                  thisTop.form.foreach {
                                      form ⇒
                                          thisTop.nameStrong.foreach {
                                              nameStrong ⇒
                                                  form.setPropertyOnSelection(nameStrong.name, jSUndefined)
                                                  form.setValue(nameStrong.name, null)
                                          }
                                  }
                                  true
                          }.toThisFunc.opt
                      })
              ).undef


            thisTop.Super("init", args)

    }.toThisFunc.opt

    `type` = FormItemComponentType.FormItemList
}
