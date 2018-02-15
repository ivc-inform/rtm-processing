package com.simplesys.SmartClient

import com.simplesys.SmartClient.App._
import com.simplesys.SmartClient.App.formItems.props.{LookupListGridEditorItemProps, LookupTreeGridEditorItemProps}
import com.simplesys.SmartClient.App.formItems.{LookupListGridEditorItem, LookupTreeGridEditorItem}
import com.simplesys.SmartClient.App.props._
import com.simplesys.SmartClient.Control._
import com.simplesys.SmartClient.Control.menu.{MenuItem, MenuSSItem}
import com.simplesys.SmartClient.Control.props._
import com.simplesys.SmartClient.Control.props.menu.{MenuItemProps, MenuSSItemProps}
import com.simplesys.SmartClient.DataBinding._
import com.simplesys.SmartClient.DataBinding.dataSource.{DataSourceField, Operator}
import com.simplesys.SmartClient.DataBinding.props._
import com.simplesys.SmartClient.DataBinding.props.dataSource.{DataSourceFieldProps, OperatorProps}
import com.simplesys.SmartClient.Drawing._
import com.simplesys.SmartClient.Drawing.drawItem._
import com.simplesys.SmartClient.Drawing.drawItem.props._
import com.simplesys.SmartClient.Drawing.gradient._
import com.simplesys.SmartClient.Drawing.gradient.props._
import com.simplesys.SmartClient.Drawing.props._
import com.simplesys.SmartClient.Forms._
import com.simplesys.SmartClient.Forms.formsItems.props._
import com.simplesys.SmartClient.Forms.formsItems.{FormItemWithButtons, _}
import com.simplesys.SmartClient.Forms.props._
import com.simplesys.SmartClient.Foundation._
import com.simplesys.SmartClient.Foundation.canvas.ImgProperties
import com.simplesys.SmartClient.Foundation.props._
import com.simplesys.SmartClient.Foundation.props.canvas.ImgPropertiesProps
import com.simplesys.SmartClient.Grids._
import com.simplesys.SmartClient.Grids.listGrid.ListGridField
import com.simplesys.SmartClient.Grids.props._
import com.simplesys.SmartClient.Grids.props.listGrid.ListGridFieldProps
import com.simplesys.SmartClient.Grids.props.tileGrid.{DrawItemTileProps, SimpleTileProps}
import com.simplesys.SmartClient.Grids.props.treeGrid.TreeGridFieldProps
import com.simplesys.SmartClient.Grids.tileGrid.{DrawItemTile, SimpleTile}
import com.simplesys.SmartClient.Grids.treeGrid.{Tree, TreeGridField}
import com.simplesys.SmartClient.Layout._
import com.simplesys.SmartClient.Layout.props._
import com.simplesys.SmartClient.Layout.props.sectionStack.SectionStackSectionProps
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.props.toolStrip.{ToolStripButtonProps, ToolStripMenuButtonProps, ToolStripSeparatorProps}
import com.simplesys.SmartClient.Layout.sectionStack.SectionStackSection
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.toolStrip.{ToolStripButton, ToolStripMenuButton, ToolStripSeparator}
import com.simplesys.SmartClient.RPC.props.{RPCRequestProps, RequestParamsProps}
import com.simplesys.SmartClient.RPC.{RPCRequest, RequestParams}
import com.simplesys.SmartClient.System.date.Date
import com.simplesys.SmartClient.System.date.props.DateProps
import com.simplesys.SmartClient.System.props.TreeProps
import com.simplesys.SmartClient.Tools.editProxy.props.DrawItemEditProxyProps
import com.simplesys.SmartClient.Tools.editProxy.{DrawItemEditProxy, DrawPaneEditProxy}
import com.simplesys.SmartClient.Tools.palette._
import com.simplesys.SmartClient.Tools.palette.props._
import com.simplesys.SmartClient.Tools.props._
import com.simplesys.SmartClient.Tools.props.editProxy.DrawPaneEditProxyProps
import com.simplesys.SmartClient.Tools.{EditContext, EditContextSS, EditProxy, PaletteNode}


//Должны быть
//import com.simplesys.macros.PropsToDictionary
import com.simplesys.macros.PropsToDictionary

//

package object System {

    type Point = IscArray[Double]
    type Rectangle = IscArray[Point]

    implicit class String1Opts(x: String) {
        def ellipsis = s"$x..."
        def fromSkinPath = s"[SKIN]$x"
        def dblQuoted: String = "\"" + x.replace("\"", "\\\"") + "\""
    }

    object Date extends SCApply4Object[Date, DateProps]

    //<editor-fold desc="Control">
    object IButtonSS extends SCApply[IButtonSS, IButtonSSProps]
    object CancelButton extends SCApply[CancelButton, CancelButtonProps]
    object IMenuButton extends SCApply[IMenuButton, IMenuButtonProps]
    object Slider extends SCApply[Slider, SliderProps]
    object Menu extends SCApply[Menu, MenuProps]
    object MenuSS extends SCApply[MenuSS, MenuSSProps]
    object MenuSSItem extends SCApply4Object[MenuSSItem, MenuSSItemProps]
    //</editor-fold>

    object JSONEncoder extends SCApply4Object[JSONEncoder, JSONEncoderProps]

    //<editor-fold desc="DataBinding">
    object DataView extends SCApply[DataView, DataViewProps]
    object DataSource extends SCApply[DataSource, DataSourceProps]
    object DataSourceSS extends SCApply[DataSourceSS, DataSourceSSProps]
    object DataBoundComponent extends SCApply4Object[DataBoundComponent, DataBoundComponentProps]
    object RestDataSourceSS extends SCApply[RestDataSourceSS, RestDataSourceSSProps]
    object RestDataSource extends SCApply[RestDataSource, RestDataSourceProps]
    object SimpleType extends SCApply[SimpleType, SimpleTypeProps]
    object SimpleTile extends SCApply[SimpleTile, SimpleTileProps]
    object AdvancedCriteria extends SCApply4Object[AdvancedCriteria, AdvancedCriteriaProps]
    object Criterion extends SCApply4Object[Criterion, CriterionProps]
    object DataSourceField extends SCApply4Object[DataSourceField, DataSourceFieldProps]
    //</editor-fold>

    //<editor-fold desc="Forms">
    object DynamicForm extends SCApply[DynamicForm, DynamicFormProps]
    object DynamicFormSS extends SCApply[DynamicFormSS, DynamicFormSSProps]
    object FilterBuilder extends SCApply[FilterBuilder, FilterBuilderProps]
    object FilterBuilderSS extends SCApply[FilterBuilderSS, FilterBuilderSSProps]
    //</editor-fold>

    //<editor-fold desc="FormsItems">
    object Operator extends SCApply4Object[Operator, OperatorProps]
    object FormItem extends SCApply4Object[FormItem, FormItemProps]
    object ComboBoxItem extends SCApply4Object[ComboBoxItem, ComboBoxItemProps]
    object CanvasItem extends SCApply4Object[CanvasItem, CanvasItemProps]
    object CheckboxItem extends SCApply4Object[CheckboxItem, CheckboxItemProps]
    object ColorItem extends SCApply4Object[ColorItem, ColorItemProps]
    object SkinBoxItem extends SCApply4Object[SkinBoxItem, SkinBoxItemProps]
    object DateItem extends SCApply4Object[DateItem, DateItemProps]
    object DateRangeItem extends SCApply4Object[DateRangeItem, DateRangeItemProps]
    object DateTimeItem extends SCApply4Object[DateTimeItem, DateTimeItemProps]
    object DateTimeItemSS extends SCApply4Object[DateTimeItemSS, DateTimeItemSSProps]
    object DoubleItem extends SCApply4Object[DoubleItem, DoubleItemProps]
    object IntegerItem extends SCApply4Object[IntegerItem, IntegerItemProps]
    object FloatItem extends SCApply4Object[FloatItem, FloatItemProps]
    object PickList extends SCApply4Object[PickList, PickListProps]
    object SelectItem extends SCApply4Object[SelectItem, SelectItemProps]
    object SpinnerItem extends SCApply4Object[SpinnerItem, SpinnerItemProps]
    object TextAreaItem extends SCApply4Object[TextAreaItem, TextAreaItemProps]
    object TextItem extends SCApply4Object[TextItem, TextItemProps]
    object TextItemSS extends SCApply4Object[TextItemSS, TextItemSSProps]
    object TextAreaItemSS extends SCApply4Object[TextAreaItemSS, TextAreaItemSSProps]
    object StaticTextItem extends SCApply4Object[StaticTextItem, StaticTextItemProps]
    object TimeItem extends SCApply4Object[TimeItem, TimeItemProps]
    object Tab extends SCApply4Object[Tab, TabProps]
    object DateChooser extends SCApply4Object[DateChooser, DateChooserProps]
    object BooleanItem extends SCApply4Object[BooleanItem, BooleanItemProps]
    object PasswordItem extends SCApply4Object[PasswordItem, PasswordItemProps]
    object SubmitItem extends SCApply4Object[SubmitItem, SubmitItemProps]
    object ButtonItem extends SCApply4Object[ButtonItem, ButtonItemProps]

    object FormItemWithButtons extends SCApply4Object[FormItemWithButtons, FormItemWithButtonsProps]
    object ComboboxItemWithButtonsJS extends SCApply4Object[ComboboxItemWithButtonsJS, ComboboxItemWithButtonsJSProps]

    object GradientItem extends SCApply4Object[GradientItem, GradientItemProps]
    object ShadowItem extends SCApply4Object[ShadowItem, ShadowItemProps]
    object FontItem extends SCApply4Object[FontItem, FontItemProps]
    object PointItem extends SCApply4Object[PointItem, PointItemProps]
    object DurationItem extends SCApply4Object[DurationItem, DurationItemProps]
    object ComboboxItemWithButtons extends SCApply4Object[ComboboxItemWithButtons, ComboboxItemWithButtonsProps]
    object ComboboxItemWithClearButton extends SCApply4Object[ComboboxItemWithClearButton, ComboboxItemWithClearButtonProps]
    object LookupListGridEditorItem extends SCApply4Object[LookupListGridEditorItem, LookupListGridEditorItemProps]
    object LookupTreeGridEditorItem extends SCApply4Object[LookupTreeGridEditorItem, LookupTreeGridEditorItemProps]
    object Validator extends SCApply4Object[Validator, ValidatorProps]
    //</editor-fold>

    //<editor-fold desc="Foundation">
    object Canvas extends SCApply[Canvas, CanvasProps]
    object Label extends SCApply[Label, LabelProps]
    object HTMLFlow extends SCApply[HTMLFlow, HTMLFlowProps]
    object HTMLPane extends SCApply[HTMLPane, HTMLPaneProps]
    object HTMLPaneSS extends SCApply[HTMLPaneSS, HTMLPaneSSProps]
    object Progressbar extends SCApply[Progressbar, ProgressbarProps]
    object ImgProperties extends SCApply4Object[ImgProperties, ImgPropertiesProps]
    //</editor-fold>

    //<editor-fold desc="RPC">
    object RPCRequest extends SCApply[RPCRequest, RPCRequestProps]
    object DSRequest extends SCApply[DSRequest, DSRequestProps]
    //</editor-fold>

    //<editor-fold desc="Grids">
    object ListGrid extends SCApply[ListGrid, ListGridProps]
    object ListGridEditor extends SCApply[ListGridEditor, ListGridEditorProps]
    object TreeGridEditor extends SCApply[TreeGridEditor, TreeGridEditorProps]
    object Tree extends SCApply[Tree, TreeProps]
    object ResultTree extends SCApply[ResultTree, ResultTreeProps]
    //</editor-fold>

    //<editor-fold desc="Layout">
    object LayoutSS extends SCApply[LayoutSS, LayoutSSProps]
    object HLayoutSS extends SCApply[HLayoutSS, HLayoutSSProps]
    object VLayoutSS extends SCApply[VLayoutSS, VLayoutSSProps]
    object IconButton extends SCApply[IconButton, IconButtonProps]
    object IconButtonSS extends SCApply[IconButtonSS, IconButtonSSProps]
    object IconMenuButton extends SCApply[IconMenuButton, IconMenuButtonProps]
    object IconMenuButtonSS extends SCApply[IconMenuButtonSS, IconMenuButtonSSProps]
    object OkCancelPanel extends SCApply[OkCancelPanel, OkCancelPanelProps]
    object OkPanel extends SCApply[OkPanel, OkPanelProps]
    object ChainMasterDetail extends SCApply[ChainMasterDetail, ChainMasterDetailProps]
    object TabSetSS extends SCApply[TabSetSS, TabSetSSProps]
    object WindowSS extends SCApply[WindowSS, WindowSSProps]
    object WindowSSDialog extends SCApply[WindowSSDialog, WindowSSDialogProps]
    object WindowProgressDialog extends SCApply[WindowProgressDialog, WindowProgressDialogProps]
    object ToolStrip extends SCApply[ToolStrip, ToolStripProps]
    object WindowListGridEditorToolStrip extends SCApply[WindowListGridEditorToolStrip, WindowListGridEditorToolStripProps]
    object ToolStripMenuButton extends SCApply[ToolStripMenuButton, ToolStripMenuButtonProps]
    object ToolStripSeparator extends SCApply[ToolStripSeparator, ToolStripSeparatorProps]
    object ToolStripButton extends SCApply[ToolStripButton, ToolStripButtonProps]
    object SectionStack extends SCApply[SectionStack, SectionStackProps]
    object SectionStackSS extends SCApply[SectionStackSS, SectionStackSSProps]
    object SectionStackSection  extends SCApply4Object[SectionStackSection , SectionStackSectionProps]
    object LayoutSpacer extends SCApply[LayoutSpacer, LayoutSpacerProps]
    object RichTextEditor extends SCApply[RichTextEditor, RichTextEditorProps]
    //</editor-fold>

    //<editor-fold desc="drawing">
    object DrawPane extends SCApply[DrawPane, DrawPaneProps]
    object EditProxy extends SCApply[EditProxy, EditProxyProps]
    object DrawPaneEditProxy extends SCApply[DrawPaneEditProxy, DrawPaneEditProxyProps]
    object DrawItemEditProxy extends SCApply[DrawItemEditProxy, DrawItemEditProxyProps]
    object EditContext extends SCApply[EditContext, EditContextProps]
    object PaletteNode extends SCApply4Object[PaletteNode, PaletteNodeProps]

    //<editor-fold desc="gradient">
    object SimpleGradient extends SCApply4Object[SimpleGradient, SimpleGradientProps]
    //</editor-fold>

    //<editor-fold desc="Palette">
    object TilePalette extends SCApply[TilePalette, TilePaletteProps]
    //</editor-fold>

    //<editor-fold desc="DrawItem">
    object DrawItem extends SCApply[DrawItem, DrawItemProps]
    object DrawGroup extends SCApply[DrawGroup, DrawGroupProps]
    object DrawLine extends SCApply[DrawLine, DrawLineProps]
    object DrawRect extends SCApply[DrawRect, DrawRectProps]
    object DrawOval extends SCApply[DrawOval, DrawOvalProps]
    object DrawLabel extends SCApply[DrawLabel, DrawLabelProps]
    object DrawCurve extends SCApply[DrawCurve, DrawCurveProps]
    object DrawPath extends SCApply[DrawPath, DrawPathProps]
    object DrawLinePath extends SCApply[DrawLinePath, DrawLinePathProps]
    object DrawLinePathSS extends SCApply[DrawLinePathSS, DrawLinePathSSProps]
    object DrawKnob extends SCApply[DrawKnob, DrawKnobProps]
    object Shadow extends SCApply4Object[Shadow, ShadowProps]
    object SortSpecifier extends SCApply4Object[SortSpecifier, SortSpecifierProps]
    object RequestParams extends SCApply4Object[RequestParams, RequestParamsProps]
    object DrawItemTile extends SCApply[DrawItemTile, DrawItemTileProps]
    object EditContextSS extends SCApply[EditContextSS, EditContextSSProps]

    object DrawGroupDefaults extends SCApply4Object[DrawGroupDefaults, DrawGroupDefaultsProps]
    object DrawRectDefaults extends SCApply4Object[DrawRectDefaults, DrawRectDefaultsProps]
    object DrawLineDefaults extends SCApply4Object[DrawLineDefaults, DrawLineDefaultsProps]
    object DrawPathDefaults extends SCApply4Object[DrawPathDefaults, DrawPathDefaultsProps]
    object DrawLinePathDefaults extends SCApply4Object[DrawLinePathDefaults, DrawLinePathDefaultsProps]
    object DrawOvalDefaults extends SCApply4Object[DrawOvalDefaults, DrawOvalDefaultsProps]
    object DrawPaneDefaults extends SCApply4Object[DrawPaneDefaults, DrawPaneDefaultsProps]
    object DrawCurveDefaults extends SCApply4Object[DrawCurveDefaults, DrawCurveDefaultsProps]

    object RibbonBar extends SCApply[RibbonBar, RibbonBarProps]
    object RibbonGroup extends SCApply[RibbonGroup, RibbonGroupProps]
    object RibbonGroupSS extends SCApply[RibbonGroupSS, RibbonGroupSSProps]

    //</editor-fold>

    object SettingsEditor extends SCApply[SettingsEditor, SettingsEditorProps]
    object ListGridContextMenu extends SCApply[ListGridContextMenu, ListGridContextMenuProps]
    object ListGridContextMenuWithForm extends SCApply[ListGridContextMenu, ListGridContextMenuWithFormProps]
    object TreeGridContextMenu extends SCApply[TreeGridContextMenu, TreeGridContextMenuProps]
    object TreeListGridContextMenu extends SCApply[TreeListGridContextMenu, TreeListGridContextMenuProps]
    object TreeListGridEditor extends SCApply[TreeListGridEditor, TreeListGridEditorProps]

    object MenuItem extends SCApply4Object[MenuItem, MenuItemProps]
    object TreeGridField extends SCApply4Object[TreeGridField, TreeGridFieldProps]
    object ListGridField extends SCApply4Object[ListGridField, ListGridFieldProps]

    object CommonListGridEditorComponent extends SCApply[CommonListGridEditorComponent, CommonListGridEditorComponentProps]
    object CommonTreeGridEditorComponent extends SCApply[CommonTreeGridEditorComponent, CommonTreeGridEditorComponentProps]
    object CommonTreeListGridEditorComponent extends SCApply[CommonTreeListGridEditorComponent, CommonTreeListGridEditorComponentProps]

    object EditorUsers extends SCApply[EditorUsers, EditorUsersProps]
    object EditorUserGroups extends SCApply[EditorUserGroups, EditorUserGroupsProps]

    val nbsp = """&nbsp;"""
}

