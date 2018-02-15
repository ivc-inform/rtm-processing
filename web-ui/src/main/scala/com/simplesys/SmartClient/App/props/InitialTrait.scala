package com.simplesys.SmartClient.App.props

import com.simplesys.SmartClient.Forms.formsItems.FormItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.{ListGridEditor, TreeGridEditor, TreeListGridEditor}
import com.simplesys.SmartClient.System.{IscArray, isc}
import com.simplesys.System._

import scala.collection.mutable.ArrayBuffer

trait InitialTrait {

    def initWidget(thiz: Canvas, fields: JSUndefined[IscArray[ListGridField]], replacingFields: JSUndefined[IscArray[ListGridField]], editingFields: JSUndefined[IscArray[FormItem]]): (JSUndefined[IscArray[ListGridField]], JSUndefined[IscArray[FormItem]]) = {

//        println(s"InitialTrait.initWidget: thiz: ${thiz.getClassName()}(${thiz.getIdentifier()}) replacingFields: ${replacingFields.map(fields => fields.map(_.nameStrong.map(_.name)).mkString("[", ", ", "]"))}, editingFields: ${editingFields.map(fields => fields.map(_.nameStrong.map(_.name)).mkString("[", ", ", "]"))}")

        fields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #36")))

        val replacingEditingFields = ArrayBuffer.empty[FormItem]

        var _fields = fields
        var _editingFields = editingFields

        //isc debugTrap replacingFields
        replacingFields.foreach {
            _.foreach {
                field =>
                    if (field.nameStrong.isDefined) {
                        field._name = field.nameStrong.get.name
                        field.editorProperties.foreach {
                            formItem =>
                                formItem._name = field._name
                                formItem.nameStrong = new NameStrong {
                                    override val name = field._name
                                }
                                replacingEditingFields += formItem
                        }

                    }
                    else
                        thiz.logError("Field not have nameStrong, error #42")
            }
        }

        editingFields.foreach(_.foreach(field => if (field.nameStrong.isDefined) field._name = field.nameStrong.get.name else thiz.logError("Field not have nameStrong, error #60")))

        val _fieldsListGrid = ArrayBuffer.empty[ListGridField]
        val _fieldsFormItem = ArrayBuffer.empty[FormItem]

        val enableReplacingField = fields.isDefined && replacingFields.isDefined
        val enableReplacingFormItem = editingFields.isDefined && replacingEditingFields.length > 0

        if (enableReplacingField) {
            fields.get.foreach {
                field =>
                    replacingFields.get.find(_._name == field._name) match {
                        case None =>
                            _fieldsListGrid += field
                        case Some(field) =>
                            //isc debugTrac field
                            if (isc.isA.ListGridEditor(thiz))
                                field.filterEditorProperties.foreach(_.filteredGridList = thiz.asInstanceOf[ListGridEditor])
                            else if (isc.isA.TreeGridEditor(thiz))
                                field.filterEditorProperties.foreach(_.filteredGridTree = thiz.asInstanceOf[TreeGridEditor])
                            else if (isc.isA.TreeListGridEditor(thiz)) {
                                val editor = thiz.asInstanceOf[TreeListGridEditor]
                                field.filterEditorProperties.foreach(_.filteredGridList = editor.listGrid)
                                field.filterEditorProperties.foreach(_.filteredGridTree = editor.treeGrid)
                            }
                            else
                                thiz.logError("UnknownEditor, error #62")

                            _fieldsListGrid += field
                    }
            }

            _fields = IscArray(_fieldsListGrid: _*)
        }

        if (enableReplacingFormItem) {
            editingFields.get.foreach {
                field =>
                    replacingEditingFields.find(_._name == field._name) match {
                        case None =>
                            _fieldsFormItem += field
                        case Some(field) =>
                            _fieldsFormItem += field
                    }
            }

            _editingFields = IscArray(_fieldsFormItem: _*)
        }

        (_fields, _editingFields)
    }

}
