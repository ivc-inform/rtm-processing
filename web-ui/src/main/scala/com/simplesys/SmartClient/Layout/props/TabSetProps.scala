package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.{ImgButton, StretchImgButton}
import com.simplesys.SmartClient.Forms.formsItems.TextItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Foundation.props.CanvasProps
import com.simplesys.SmartClient.Grids.TableView
import com.simplesys.SmartClient.Layout.props.tabSet.TabProps
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.Layout.{NavigationBar, TabBar, TabSet, VLayout}
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.Side.Side
import com.simplesys.System.Types.TabTitleEditEvent.TabTitleEditEvent
import com.simplesys.System.Types._
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js
import scala.scalajs.js.ThisFunction5

class TabSetProps extends CanvasProps {
    type classHandler <: TabSet

    var addTabButton: ScOption[ImgButton] = ScNone
    var animateTabScrolling: ScOption[Boolean] = ScNone
    var addTabButtonIcon: ScOption[SCImgURL] = ScNone
    var addTabs: ScOption[js.Function2[IscArray[Tab], Int, _]] = ScNone
    var ariaCloseableSuffix: ScOption[String] = ScNone
    var bottomEdgeOffsets: ScOption[EdgeSizes] = ScNone
    var bottomEdgeSizes: ScOption[EdgeSizes] = ScNone
    var canAddTabs: ScOption[Boolean] = ScNone
    var canCloseTabs: ScOption[Boolean] = ScNone
    var canEditTabTitles: ScOption[Boolean] = ScNone
    var canReorderTabs: ScOption[Boolean] = ScNone
    var locateTabsBy: ScOption[String] = ScNone
    var showTabScroller: ScOption[Boolean] = ScNone
    var tabs: ScOption[Seq[Tab]] = ScNone
    //var tabsReordered: ScOption[js.Function0[_]] = ScNone  //После доделки макро--подстановщика аннотация JSNameProps
    var useIOSTabs: ScOption[Boolean] = ScNone
    var useSimpleTabs: ScOption[Boolean] = ScNone
    var closeClick: ScOption[js.Function1[Tab, _]] = ScNone
    var closeTabIcon: ScOption[SCImgURL] = ScNone
    var closeTabIconSize: ScOption[Int] = ScNone
    var defaultTabHeight: ScOption[Int] = ScNone
    var defaultTabWidth: ScOption[Int] = ScNone
    var destroyPanes: ScOption[Boolean] = ScNone
    var getPaneContainerEdges: ScOption[js.Function1[void, IscArray[JSObject]]] = ScNone
    var leftEdgeOffsets: ScOption[EdgeSizes] = ScNone
    var leftEdgeSizes: ScOption[EdgeSizes] = ScNone
    var moreTab: ScOption[Tab] = ScNone
    var moreTabCount: ScOption[Int] = ScNone
    var moreTabImage: ScOption[SCImgURL] = ScNone
    var moreTabPane: ScOption[VLayout] = ScNone
    var moreTabPaneDefaults: ScOption[Canvas] = ScNone
    var moreTabPaneNavBar: ScOption[NavigationBar] = ScNone
    var moreTabPaneProperties: ScOption[Canvas] = ScNone
    var moreTabPaneTable: ScOption[TableView] = ScNone
    var moreTabProperties: ScOption[Tab] = ScNone
    var moreTabTitle: ScOption[String] = ScNone
    var paneContainer: ScOption[VLayout] = ScNone
    var paneContainerClassName: ScOption[CSSClassName] = ScNone
    var paneContainerOverflow: ScOption[Overflow] = ScNone
    var paneMargin: ScOption[Int] = ScNone
    var pickerButtonHSrc: ScOption[SCImgURL] = ScNone
    var pickerButtonSize: ScOption[Int] = ScNone
    var pickerButtonSrc: ScOption[SCImgURL] = ScNone
    var pickerButtonVSrc: ScOption[SCImgURL] = ScNone
    var rightEdgeOffsets: ScOption[EdgeSizes] = ScNone
    var rightEdgeSizes: ScOption[EdgeSizes] = ScNone
    var scroller: ScOption[StretchImgButton] = ScNone
    var scrollerButtonSize: ScOption[Int] = ScNone
    var scrollerHSrc: ScOption[SCImgURL] = ScNone
    var scrollerSrc: ScOption[SCImgURL] = ScNone
    var scrollerVSrc: ScOption[SCImgURL] = ScNone
    var selectedTab: ScOption[Int] = ScNone
    var showMoreTab: ScOption[Boolean] = ScNone
    var showPaneContainerEdges: ScOption[Boolean] = ScNone
    var showPartialEdges: ScOption[Boolean] = ScNone
    var showTabContextMenu: ScOption[js.Function2[TabSet, Tab, Boolean]] = ScNone
    var showTabPicker: ScOption[Boolean] = ScNone
    var simpleTabBaseStyle: ScOption[CSSClassName] = ScNone
    var simpleTabButtonConstructor: ScOption[Class] = ScNone
    var symmetricEdges: ScOption[Boolean] = ScNone
    var symmetricPickerButton: ScOption[Boolean] = ScNone
    var symmetricScroller: ScOption[Boolean] = ScNone
    var tabBar: ScOption[TabBar] = ScNone
    var tabBarAlign: ScOption[Alignment] = ScNone
    var tabBarControls: ScOption[IscArray[JSObject]] = ScNone
    var tabBarPosition: ScOption[Side] = ScNone
    var tabBarProperties: ScOption[TabBar] = ScNone
    var tabBarThickness: ScOption[Int] = ScNone
    var tabDeselected: ScOption[js.Function5[Int, Canvas, ID, Tab, Tab, Boolean]] = ScNone
    var tabSelected: ScOption[ThisFunction5[classHandler, Int, Canvas, JSUndefined[ID], Tab, JSUndefined[String], Boolean]] = ScNone
    var tabIconClick: ScOption[js.Function1[Tab, _]] = ScNone
    var tabPicker: ScOption[ImgButton] = ScNone
    var tabProperties: ScOption[Tab] = ScNone
    var titleChanged: ScOption[js.Function3[String, String, Tab, Boolean]] = ScNone
    var titleEditEvent: ScOption[TabTitleEditEvent] = ScNone
    var titleEditor: ScOption[TextItem] = ScNone
    var titleEditorLeftOffset: ScOption[Int] = ScNone
    var titleEditorProperties: ScOption[TextItem] = ScNone
    var titleEditorRightOffset: ScOption[Int] = ScNone
    var titleEditorTopOffset: ScOption[Int] = ScNone
    var topEdgeOffsets: ScOption[EdgeSizes] = ScNone
    var topEdgeSizes: ScOption[EdgeSizes] = ScNone
    var touchPickerButtonSize: ScOption[Int] = ScNone
}
