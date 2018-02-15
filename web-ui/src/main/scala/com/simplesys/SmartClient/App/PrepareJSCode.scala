package com.simplesys.SmartClient.App

import com.simplesys.SmartClient.App.props.{CommonListGridEditorComponentProps, CommonTreeListGridEditorComponentProps}
import com.simplesys.SmartClient.DataBinding.props.DataSourceProps
import com.simplesys.SmartClient.Drawing.drawItem.DrawLinePathSS
import com.simplesys.SmartClient.Drawing.drawItem.props.{DrawLinePathSSProps, DrawLineProps, DrawRectProps}
import com.simplesys.SmartClient.Drawing.props.DrawItemProps
import com.simplesys.SmartClient.Forms.formsItems.PickList
import com.simplesys.SmartClient.Forms.formsItems.props.{DateTimeItemProps, FormItemProps, FormItemWithButtonsProps, PickListProps}
import com.simplesys.SmartClient.Forms.props.DateChooserProps
import com.simplesys.SmartClient.Foundation.props.RichTextEditorProps
import com.simplesys.SmartClient.Grids.props.ListGridProps
import com.simplesys.SmartClient.Layout.WindowSSDialog
import com.simplesys.SmartClient.Layout.props.{WindowSSDialogProps, WindowSSProps}
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.System.date.props.DateProps
import com.simplesys.SmartClient.Tools.editProxy.props.DrawItemEditProxyProps
import com.simplesys.System.Types.DateDisplayFormat
import com.simplesys.option.ScOption._

import scala.scalajs.js.annotation.JSExportTopLevel

object PrepareJSCode extends StaticJSCode {

    @JSExportTopLevel("CreateSmartClientJS")
    override def createJS(): Unit = {

        isc.WindowSS.addProperties(WindowSS(new WindowSSProps))

        isc.defineClass(RibbonGroupSS.getClass.getSimpleName, RibbonGroup.getClass.getSimpleName)
        isc.defineClass(SettingsEditor.getClass.getSimpleName, WindowSS.getClass.getSimpleName)
        isc.defineClass(ListGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(ListGridContextMenuWithForm.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(TreeGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)
        isc.defineClass(TreeListGridContextMenu.getClass.getSimpleName, MenuSS.getClass.getSimpleName)

        isc.defineClass(CommonListGridEditorComponent.getClass.getSimpleName, ListGridEditor.getClass.getSimpleName)
        isc.CommonListGridEditorComponent.addClassProperties(CommonListGridEditorComponent(new CommonListGridEditorComponentProps {
            override type classHandler = CommonListGridEditorComponent
        }))

        isc.defineClass(CommonTreeGridEditorComponent.getClass.getSimpleName, TreeGridEditor.getClass.getSimpleName)
        isc.defineClass(CommonTreeListGridEditorComponent.getClass.getSimpleName, TreeListGridEditor.getClass.getSimpleName)
        isc.defineClass(LookupListGridEditorItem.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.defineClass(LookupTreeGridEditorItem.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.defineClass(TextItemSS.getClass.getSimpleName, TextItem.getClass.getSimpleName)
        isc.defineClass(TextAreaItemSS.getClass.getSimpleName, TextAreaItem.getClass.getSimpleName)
        isc.defineClass(DateTimeItemSS.getClass.getSimpleName, DateTimeItem.getClass.getSimpleName)
        isc.defineClass(EditorUsers.getClass.getSimpleName, CommonTreeListGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(EditorUserGroups.getClass.getSimpleName, CommonTreeGridEditorComponent.getClass.getSimpleName)
        isc.defineClass(WindowListGridEditorToolStrip.getClass.getSimpleName, ToolStrip.getClass.getSimpleName)
        isc.defineClass[WindowSSDialog](WindowSSDialog.getClass.getSimpleName, WindowSS.getClass.getSimpleName).addProperties(WindowSSDialog(new WindowSSDialogProps))
        isc.defineClass[DrawLinePathSS](DrawLinePathSS.getClass.getSimpleName, DrawPath.getClass.getSimpleName).addProperties(DrawLinePathSS(new DrawLinePathSSProps))

        isc.defineClass(DrawItemTile.getClass.getSimpleName, SimpleTile.getClass.getSimpleName)
        isc.defineClass(EditContextSS.getClass.getSimpleName, EditContext.getClass.getSimpleName)
        isc.defineClass(SectionStackSS.getClass.getSimpleName, SectionStack.getClass.getSimpleName)
        isc.defineClass(FormItemWithButtons.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.FormItemWithButtons.addProperties(FormItemWithButtons(new FormItemWithButtonsProps))
        isc.defineClass(GradientItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(ComboboxItemWithButtonsJS.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(ShadowItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(FontItem.getClass.getSimpleName, FormItemWithButtons.getClass.getSimpleName)
        isc.defineClass(PointItem.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.defineClass(DurationItem.getClass.getSimpleName, CanvasItem.getClass.getSimpleName)
        isc.defineClass(CancelButton.getClass.getSimpleName, IButtonSS.getClass.getSimpleName)
        isc.defineClass(FilterBuilderSS.getClass.getSimpleName, FilterBuilder.getClass.getSimpleName)

        isc.defineClass(WindowProgressDialog.getClass.getSimpleName, WindowSS.getClass.getSimpleName)

        isc.ListGrid.addProperties(ListGrid(new ListGridProps))
        isc.FormItem.addProperties(FormItem(new FormItemProps))
        isc.DrawItemEditProxy.addProperties(DrawItemEditProxy(new DrawItemEditProxyProps))
        isc.CommonTreeListGridEditorComponent.addProperties(CommonTreeListGridEditorComponent(new CommonTreeListGridEditorComponentProps))

        isc.DataSource.addProperties(DataSource(new DataSourceProps))
        isc.DrawItem.addProperties(DrawItem(new DrawItemProps))
        isc.DrawLine.addProperties(DrawLine(new DrawLineProps))
        isc.DrawRect.addProperties(DrawRect(new DrawRectProps))
        isc.RichTextEditor.addProperties(RichTextEditor(new RichTextEditorProps))

        isc.PickList.addInterfaceMethods(PickList(new PickListProps {
            override type classHandler = PickList
        }))

        isc.addMethods(
            isc.Date.prototype,
            Date(new DateProps)
        )

        isc.DateTimeItem.addProperties(
            DateTimeItem(
                new DateTimeItemProps {
                    dateFormatter = DateDisplayFormat.toEuropeanDatetime.opt
                    pickerProperties = DateChooser(
                        new DateChooserProps {
                            showSecondItem = true.opt
                        }
                    ).opt
                }
            )
        )
    }
}

