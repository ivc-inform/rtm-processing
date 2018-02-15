package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.ListGridEditor
import com.simplesys.SmartClient.Layout.WindowListGridEditorToolStrip
import com.simplesys.SmartClient.Layout.props.toolStrip.ToolStripButtonProps
import com.simplesys.SmartClient.System._
import com.simplesys.System.Types.Visibility.Visibility
import com.simplesys.System._
import com.simplesys.function._
import com.simplesys.option.ScOption._
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js.ThisFunction0
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait VisibleButtons extends JSObject {
    val newButton: Visibility
    val editButton: Visibility
    val deleteButton: Visibility
    val copyButton: Visibility
}

class WindowListGridEditorToolStripProps extends ToolStripProps {
    type classHandler <: WindowListGridEditorToolStrip

    var visibleButtons: ScOption[VisibleButtons] = ScNone

    members = Seq(
        ToolStripButton.create(
            new ToolStripButtonProps {
                identifier = "new".opt
                icon = Common.iconAdd.opt
                prompt = "Новый".ellipsis.opt
                click = {
                    (thiz: classHandler) =>
                        false
                }.toThisFunc.opt
            }
        ),
        ToolStripButton.create(
            new ToolStripButtonProps {
                identifier = "edit".opt
                icon = Common.Actions_document_edit_icon.opt
                prompt = "Изменить".ellipsis.opt
                click = {
                    (thiz: classHandler) =>
                        false
                }.toThisFunc.opt
            }
        ),
        ToolStripButton.create(
            new ToolStripButtonProps {
                identifier = "copy".opt
                icon = Common.copy_icon.opt
                prompt = "Копировать".ellipsis.opt
                click = {
                    (thiz: classHandler) =>
                        val owner = thiz.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.getSelectedRecords().foreach(owner.dataSource.addData(_))

                        false
                }.toThisFunc.opt
                enableIf = {
                    (thiz: classHandler) =>
                        if (thiz.owner.isEmpty)
                            false
                        else {
                            val owner = thiz.owner.asInstanceOf[ListGridEditor]
                            simpleSyS checkOwner owner
                            owner.getSelectedRecords().length > 0
                        }
                }.toThisFunc.opt
            }
        ),
        ToolStripButton.create(
            new ToolStripButtonProps {
                identifier = "delete".opt
                icon = Common.delete_icon.opt
                prompt = "Удалить".ellipsis.opt
                click = {
                    (thiz: classHandler) =>
                        val owner = thiz.owner.asInstanceOf[classHandler].owner.asInstanceOf[ListGridEditor]
                        isc.ask(simpleSyS.config.confirmDeleting, {
                            (value: Boolean) =>
                                if (value)
                                    owner.removeSelectedData()
                        })

                        false
                }.toThisFunc.opt
                enableIf = {
                    (thiz: classHandler) =>
                        if (thiz.owner.isEmpty)
                            false
                        else {
                            val owner = thiz.owner.asInstanceOf[ListGridEditor]
                            simpleSyS checkOwner owner
                            owner.getSelectedRecords().length > 0
                        }
                }.toThisFunc.opt
            }
        ),
        ToolStripSeparator.create(),
        ToolStripButton.create(
            new ToolStripButtonProps {
                icon = Common.iconSave.opt
                prompt = "Сохранить изменения".opt
                click = {
                    (thiz: classHandler) =>
                        false
                }.toThisFunc.opt
                enableIf = {
                    (thiz: classHandler) =>
                        val owner = thiz.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.hasChanges() && !owner.hasErrors()
                }.toThisFunc.opt
            }
        ), ToolStripButton.create(
            new ToolStripButtonProps {
                icon = Common.cancel.opt
                prompt = "Отменить изменения".opt
                true.opt
                click = {
                    (thiz: classHandler) =>
                        false
                }.toThisFunc.opt
                enableIf = {
                    (thiz: classHandler) =>
                        val owner = thiz.owner.asInstanceOf[ListGridEditor]
                        simpleSyS checkOwner owner
                        owner.hasChanges()
                }.toThisFunc.opt
            }
        )
    ).opt

    var redrawButtons: ScOption[ThisFunction0[classHandler, _]] = {
        (thiz: classHandler) =>
            thiz.members.foreach {
                member =>
                    member.owner = thiz
                    member.enableIf.foreach(member setDisabled !_ (thiz))
                    member.identifier.foreach {
                        _ match {
                            case "new" => thiz.visibleButtons.foreach(visibleButtons => member.visibility = visibleButtons.newButton)
                            case "edit" => thiz.visibleButtons.foreach(visibleButtons => member.visibility = visibleButtons.editButton)
                            case "copy" => thiz.visibleButtons.foreach(visibleButtons => member.visibility = visibleButtons.copyButton)
                            case "delete" => thiz.visibleButtons.foreach(visibleButtons => member.visibility = visibleButtons.deleteButton)
                            case _ =>
                        }
                    }
            }

    }.toThisFunc.opt

    draw = {
        (thiz: classHandler, arguments: JSUndefined[IscArray[JSAny]]) =>
            val res = thiz.Super("draw", arguments.getOrElse(IscArray()))

            thiz.owner.foreach {
                owner =>

                    if (isc.isA.ListGridEditor(owner)) {
                        owner.observe(owner.asInstanceOf[ListGridEditor].listGrid, "selectionChanged", () => thiz.redrawButtons())
                        owner.observe(owner.asInstanceOf[ListGridEditor].listGrid, "removeSelectedData", () => thiz.redrawButtons())
                    }
            }

            thiz.redrawButtons()

            //isc debugTrac(thiz.getClassName(), thiz.getIdentifier())
            res.asInstanceOf[classHandler]
    }.toThisFunc.opt

}
