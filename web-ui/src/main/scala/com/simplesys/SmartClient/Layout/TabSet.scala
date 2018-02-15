package com.simplesys.SmartClient.Layout

import com.simplesys.SmartClient.Control.{ImgButton, StretchImgButton}
import com.simplesys.SmartClient.Forms.formsItems.TextItem
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Grids.TableView
import com.simplesys.SmartClient.Layout.tabSet.Tab
import com.simplesys.SmartClient.System.{Class, IscArray}
import com.simplesys.System.{JSObject, JSUndefined}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.Side.Side
import com.simplesys.System.Types.TabTitleEditEvent.TabTitleEditEvent
import com.simplesys.System.Types._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, |}

@js.native
trait TabSet extends Canvas {
    def addTabs(tabs: Tab | IscArray[Tab], position: Int): void
    var addTabButton: ImgButton with AutoChild
    var animateTabScrolling: Boolean
    var addTabButtonIcon: SCImgURL
    var addTabs: js.Function2[Tab | IscArray[Tab], Int, _]
    var ariaCloseableSuffix: String
    var bottomEdgeOffsets: EdgeSizes
    var bottomEdgeSizes: EdgeSizes
    def cancelTabTitleEditing(): void
    //var canAddTabs: Boolean Не работает
    var canCloseTabs: Boolean
    var canEditTabTitles: Boolean
    var canReorderTabs: Boolean
    var locateTabsBy: String
    def setCanCloseTabs(canCloseTabs: Boolean): void
    var showTabScroller: Boolean
    var tabs: IscArray[Tab]
    var tabsReordered: js.Function0[_]
    var useIOSTabs: Boolean
    var useSimpleTabs: Boolean
    var closeClick: js.Function1[Tab, _]
    var closeTabIcon: SCImgURL
    var closeTabIconSize: Int
    var defaultTabHeight: Int
    var defaultTabWidth: Int
    var destroyPanes: Boolean
    def disableTab(tab: Tab | Int | ID): void
    def editTabTitle(tab: Tab | Int | ID): void
    def enableTab(tab: Tab | Int | ID): void
    var getPaneContainerEdges: js.Function1[void, IscArray[JSObject]]
    def getSelectedTab(): JSUndefined[Tab]
    def getSelectedTabNumber(): Int
    def getTab(tab: Int | ID): Tab
    def getTabNumber(tab: Int | ID): Int
    def getTabObject(tab: Int | ID): JSObject
    def getTabPane(tab: Int | ID): Canvas
    var leftEdgeOffsets: EdgeSizes
    var leftEdgeSizes: EdgeSizes
    var moreTab: Tab with AutoChild
    var moreTabCount: Int
    var moreTabImage: SCImgURL
    var moreTabPane: VLayout with AutoChild
    var moreTabPaneDefaults: Canvas
    var moreTabPaneNavBar: NavigationBar with AutoChild
    var moreTabPaneProperties: Canvas
    var moreTabPaneTable: TableView with AutoChild
    var moreTabProperties: Tab
    var moreTabTitle: String
    var paneContainer: VLayout with AutoChild
    var paneContainerClassName: CSSClassName
    var paneContainerOverflow: Overflow
    var paneMargin: Int
    var pickerButtonHSrc: SCImgURL
    var pickerButtonSize: Int
    var pickerButtonSrc: SCImgURL
    var pickerButtonVSrc: SCImgURL
    def removeLastTab(): void
    def removeTab(tabs: Tab | ID | Int): void
    def removeTabs(tabs: Tab | ID | Int | IscArray[Tab]): void
    def reorderTab(tab: Tab | ID | Int, moveToPosition: Int = js.native): void
    def revealChild(child: Canvas): void
    var rightEdgeOffsets: EdgeSizes
    var rightEdgeSizes: EdgeSizes
    def saveTabTitle(): void
    def scrollBack(): void
    var scroller: StretchImgButton with AutoChild
    var scrollerButtonSize: Int
    var scrollerHSrc: SCImgURL
    var scrollerSrc: SCImgURL
    var scrollerVSrc: SCImgURL
    def scrollForward(): void
    var selectedTab: Int
    def selectTab(tab: Tab | ID | Int): void
    def setCanCloseTab(tab: Tab | ID | Int, canClose: Boolean): void
    def setPaneContainerOverflow(newOverflow: Overflow): void
    def setTabIcon(tab: Tab | ID | Int, icon: SCImgURL): void
    def setTabPane(tab: Tab | ID | Int, pane: Canvas): void
    def setTabProperties(tab: Tab | ID | Int, properties: JSObject): void
    var showMoreTab: Boolean
    var showPaneContainerEdges: Boolean
    var showPartialEdges: Boolean
    var showTabContextMenu: js.Function2[TabSet, Tab, Boolean]
    var showTabPicker: Boolean
    var simpleTabBaseStyle: CSSClassName
    var simpleTabButtonConstructor: Class
    var symmetricEdges: Boolean
    var symmetricPickerButton: Boolean
    var symmetricScroller: Boolean
    val tabBar: TabBar with AutoChild
    var tabBarAlign: Alignment | Side
    var tabBarControls: IscArray[JSObject]
    var tabBarPosition: Side
    var tabBarProperties: TabBar
    var tabBarThickness: Int
    var tabDeselected: js.Function5[Int, Canvas, UndefOr[ID], Tab, Tab, Boolean]
    var tabSelected: js.Function5[Int, Canvas, UndefOr[ID], Tab, String, _]
    def tabForPane(pane: Canvas): Tab
    var tabIconClick: js.Function1[Tab, _]
    var tabPicker: ImgButton with AutoChild
    var tabProperties: Tab
    var titleChanged: js.Function3[String, String, Tab, Boolean]
    var titleEditEvent: TabTitleEditEvent
    var titleEditor: TextItem with AutoChild
    var titleEditorLeftOffset: Int
    var titleEditorProperties: TextItem
    var titleEditorRightOffset: Int
    var titleEditorTopOffset: Int
    var topEdgeOffsets: EdgeSizes
    var topEdgeSizes: EdgeSizes
    var touchPickerButtonSize: Int
    def updateTab(tab: Tab | ID | Int, pane: Canvas | ID): void
}



