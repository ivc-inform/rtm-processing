package com.simplesys.SmartClient.Foundation

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.DataBinding.Callbacks._
import com.simplesys.SmartClient.Forms.formsItems.CanvasItem
import com.simplesys.SmartClient.Forms.ValuesManager
import com.simplesys.SmartClient.Layout.{PrintWindow, TabSetSS, WindowSS}
import com.simplesys.SmartClient.System._
import com.simplesys.SmartClient.Tools.{EditContext, EditNode}
import com.simplesys.SmartClient.math.AffineTransform
import com.simplesys.System.Types.Alignment._
import com.simplesys.System.Types.AnimationAcceleration.AnimationAcceleration
import com.simplesys.System.Types.BackgroundRepeat._
import com.simplesys.System.Types.ClickMaskMode._
import com.simplesys.System.Types.Cursor._
import com.simplesys.System.Types.DragAppearance._
import com.simplesys.System.Types.DragIntersectStyle._
import com.simplesys.System.Types.DragMaskType._
import com.simplesys.System.Types.DrawPosition._
import com.simplesys.System.Types.EdgeName._
import com.simplesys.System.Types.LocatorStrategy._
import com.simplesys.System.Types.LocatorTypeStrategy._
import com.simplesys.System.Types.Overflow._
import com.simplesys.System.Types.PercentBoxModel._
import com.simplesys.System.Types.Positioning._
import com.simplesys.System.Types.ProportionalResizeMode._
import com.simplesys.System.Types.VerticalAlignment._
import com.simplesys.System.Types.Visibility._
import com.simplesys.System.Types._
import com.simplesys.System.{JSAny, JSObject, JSUndefined}
import org.scalajs.dom.DocumentEvent
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSName

@js.native
trait Canvas extends com.simplesys.SmartClient.System.Class {
    type classHandler <: this.type

    def _getGlobalTransform(): AffineTransform
    def _getInverseGlobalTransform(): AffineTransform
    val accessKey: String
    def addChild(newChild: Canvas, name: String = js.native, autoDraw: Boolean = js.native): Canvas
    def addPeer(newChild: Canvas, name: String = js.native, autoDraw: Boolean = js.native, preDraw: Boolean = js.native): Canvas
    def addSnapAlignCandidate(newCandidate: Canvas): void
    def adjustForContent(immediate: Boolean): void
    val alwaysShowScrollbars: Boolean
    var animateAcceleration: AnimationAcceleration
    def animateFade(opacity: Int, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateFadeTime: Int
    def animateHide(effect: AnimateShowEffectId | AnimateShowEffect = js.native, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateHideAcceleration: AnimationAcceleration
    var animateHideEffect: AnimateShowEffectId
    var animateHideTime: Int
    def animateMove(left: Int, top: Int, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateMoveAcceleration: AnimationAcceleration
    var animateMoveTime: Int
    def animateRect(left: Int, top: Int, width: Int, height: Int, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateRectAcceleration: AnimationAcceleration
    var animateRectTime: Int
    def animateResize(width: Int, height: Int, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateResizeAcceleration: AnimationAcceleration
    var animateResizeTime: Int
    def animateScroll(scrollLeft: Int, scrollTop: Int, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateScrollAcceleration: AnimationAcceleration
    var animateScrollTime: Int
    def animateShow(effect: AnimateShowEffectId | AnimateShowEffect = js.native, callback: AnimationCallback = js.native, duration: Int = js.native, acceleration: AnimationAcceleration = js.native): void
    var animateShowAcceleration: AnimationAcceleration
    var animateShowEffect: AnimateShowEffectId
    var animateShowTime: Int
    var animateTime: Int
    var appImgDir: URL
    val ariaRole: String
    val ariaState: String
    val autoDraw: Boolean
    var autoShowParent: Boolean
    val backgroundColor: CSSColor
    val backgroundImage: SCImgURL
    val backgroundPosition: String
    val backgroundRepeat: BackgroundRepeat
    def blur(): void
    val border: String
    def bringToFront(): void
    var canAcceptDrop: Boolean
    var beforeDestroy: js.ThisFunction0[classHandler, _]
    var canDrag: Boolean
    var canDragReposition: Boolean
    var canDragResize: Boolean
    var canDragScroll: Boolean
    var canDrop: Boolean
    var canDropBefore: Boolean
    val canFocus: Boolean
    var canHover: Boolean
    var canSelectText: Boolean
    val canvasItem: CanvasItem
    val children: IscArray[Canvas]
    var childrenResizeSnapAlign: Boolean
    var childrenSnapAlign: Boolean
    var childrenSnapCenterAlign: Boolean
    var childrenSnapEdgeAlign: Boolean
    var childrenSnapResizeToGrid: Boolean
    var childrenSnapToGrid: Boolean
    def clear(): void
    var click: js.ThisFunction0[classHandler, Boolean] | String
    @JSName("click")
    def clickProc(): Boolean
    def clickMaskUp(ID: String = js.native): Boolean
    val componentMask: Canvas with AutoChild
    val componentMaskDefaults: Canvas
    def contains(canvas: Canvas, testSelf: Boolean = js.native): Boolean
    def containsEvent(): Boolean
    def containsFocus(): Boolean
    def containsPoint(x: Int, y: Int, withinViewport: Boolean = js.native): Boolean
    var contents: HTMLString
    var contextMenu: MenuSS
    val cursor: Cursor
    val customEdges: IscArray[String]
    val dataPath: DataPath
    var defaultHeight: Int
    var defaultWidth: Int
    def deparent(): void
    def depeer(): void
    val destroyed: JSUndefined[Boolean]
    val destroying: JSUndefined[Boolean]
    def disable(): void
    val disabled: Boolean
    var disabledCursor: Cursor
    val disableTouchScrollingForDrag: Boolean
    var doubleClick: js.ThisFunction0[classHandler, Boolean]
    var doubleClickDelay: Int
    var dragAppearance: DragAppearance
    var dragIntersectStyle: DragIntersectStyle
    var dragMaskType: DragMaskType
    var dragMove: js.ThisFunction0[classHandler, Boolean]
    var dragOpacity: Int
    var dragRepositionAppearance: DragAppearance
    var dragRepositionCursor: Cursor
    var dragRepositionMove: js.ThisFunction0[classHandler, Boolean]
    var dragRepositionStart: js.ThisFunction0[classHandler, Boolean]
    var dragRepositionStop: js.ThisFunction0[classHandler, Boolean]
    var dragResizeAppearance: DragAppearance
    var dragResizeMove: js.ThisFunction0[classHandler, Boolean]
    var dragResizeStart: js.ThisFunction0[classHandler, Boolean]
    var dragResizeStop: js.ThisFunction0[classHandler, Boolean]
    var dragScrollDelay: Int
    var dragStart: js.ThisFunction0[classHandler, Boolean]
    var dragStartDistance: Int
    var dragStop: js.ThisFunction0[classHandler, Boolean]
    var dragTarget: Canvas
    var dragType: String
    def draw(): Canvas
    var drop: js.ThisFunction0[classHandler, Boolean]
    var dropMove: js.ThisFunction0[classHandler, Boolean]
    var dropOut: js.ThisFunction0[classHandler, Boolean]
    var dropOver: js.ThisFunction0[classHandler, Boolean]
    var dropTypes: IscArray[String] | String
    var dynamicContents: Boolean
    var dynamicContentsVars: ValueMap
    val edgeBackgroundColor: Color
    val edgeCenterBackgroundColor: Color
    var edgeCursorMap: JSObject
    val edgeImage: SCImgURL
    var edgeMarginSize: Int
    val edgeOffset: Int
    val edgeOpacity: Int
    val edgeShowCenter: Boolean
    val edgeSize: Int
    var editContext: JSUndefined[EditContext]
    val editNode: JSUndefined[EditNode]
    var editProxy: EditNode
    val editProxyConstructor: SCClassName
    def enable(): void
    def encloses(other: Canvas): Boolean
    def enclosesRect(left: Int | IscArray[Int], top: Int, width: Int, height: Int): Boolean
    val extraSpace: Int
    def focus(): void
    var focusChanged: js.ThisFunction1[classHandler, Boolean, void]
    def focusInNextTabElement(): void
    def focusInPreviousTabElement(): void
    def getBottom(): Int
    def getByLocalId(localId: String): Canvas
    def getContentElement(): Element
    def getContents(): HTMLElement
    def getFullDataPath(): DataPath
    def getHeight(): Int
    def getHoverComponent(): Canvas
    def getHoverHTML(): HTMLString
    def getHSnapOrigin(snapChild: Canvas = js.native): Int
    def getHSnapPosition(coordinate: Int, direction: String = js.native): Int
    def getImage(identifier: String): DocumentEvent
    def getImgURL(URL: String, imgDir: String = js.native): String
    def getInnerContentHeight(): Int
    def getInnerContentWidth(): Int
    def getInnerHeight(): Int
    def getInnerHTML(): HTMLString
    def getInnerWidth(): Int
    def getLeft(): Int
    def getMasterCanvas(): Canvas
    def getOffsetX(): Int
    def getOffsetY(): Int
    def getOuterElement(): DocumentEvent
    def getPageBottom(): Int
    def getPageLeft(): Int
    def getPageRight(): Int
    def getPageTop(): Int
    def getPanelContainer(): Canvas
    def getParentCanvas(): Canvas
    def getParentElements(): IscArray[Canvas]
    def getPrintHTML(printProperties: PrintProperties = js.native, callback: Callback): HTMLString
    def getRight(): Int
    def getScrollbarSize(): Int
    def getScrollBottom(): Int
    def getScrollHeight(): Int
    def getScrollLeft(): Int
    def getScrollRight(): Int
    def getScrollTop(): Int
    def getScrollWidth(): Int
    def getSnapEdge(): String
    def getSnapTo(): String
    def getTop(): Int
    def getViewportHeight(): Int
    def getViewportWidth(): Int
    def getVisibleHeight(): Int
    def getVisibleWidth(): Int
    def getVSnapOrigin(snapChild: Canvas): Int
    def getVSnapPosition(coordinate: Int, direction: String = js.native): Int
    def getWidth(): Int
    def getZIndex(resolveToNumber: Boolean): Int
    val groupBorderCSS: String
    val groupLabelBackgroundColor: CSSColor
    val groupLabelStyleName: CSSStyleName
    val groupTitle: HTMLString
    def handleHover(): void
    val height: String | Int
    def hide(): void
    def hide1[T](): T
    def hideClickMask(ID: String = js.native): void
    def hideComponentMask(): void
    def hideContextMenu(): void
    val hideUsingDisplayNone: Boolean
    var hover: js.ThisFunction0[classHandler, Boolean]
    var hoverAlign: Alignment
    var hoverAutoDestroy: Boolean
    var hoverDelay: Int
    var hoverHeight: Int
    def hoverHidden(): void
    var hoverMoveWithMouse: Boolean
    var hoverOpacity: Int
    var hoverStyle: CSSStyleName
    var hoverVAlign: VerticalAlignment
    var hoverWidth: Int
    var hoverWrap: Boolean
    val htmlElement: HTMLElement
    val htmlPosition: DrawPosition
    val ID: String
    var funcMenu: JSUndefined[MenuSS]
    def imgHTML(src: SCImgURL, width: Int = js.native, height: Int = js.native, name: String = js.native, extraStuff: String = js.native, imgDir: String = js.native): String
    var initWidget: js.ThisFunction1[classHandler, IscArray[JSAny], _]
    def intersects(other: Canvas): Boolean
    def intersectsRect(left: Int | IscArray[Int], top: Int, width: Int, height: Int): Boolean
    def isDirty(): Boolean
    def isDisabled(): Boolean
    def isDrawn(): Boolean
    def isFocused(): Boolean
    val isGroup: Boolean
    val isPrinting: Boolean
    var isSnapAlignCandidate: Boolean
    def isVisible(): Boolean
    var keepInParentRect: Boolean | IscArray[Int]
    var keyDown: js.ThisFunction0[classHandler, Boolean]
    var keyPress: js.ThisFunction0[classHandler, Boolean]
    var keyUp: js.ThisFunction0[classHandler, Boolean]
    var layoutAlign: Alignment | VerticalAlignment
    def layoutChildren(reason: String): void
    val leavePageSpace: Int
    var left:  Int
    def linkHTML(href: String, text: HTMLString = js.native, target: String = js.native, ID: String = js.native, tabIndex: Int = js.native, accessKey: String = js.native): HTMLString
    var locateChildrenBy: LocatorStrategy
    var locateChildrenType: LocatorTypeStrategy
    var locatePeersBy: LocatorStrategy
    var locatePeersType: LocatorTypeStrategy
    val margin: Int
    def markForDestroy(): void
    def markForRedraw(reason: String = js.native): void
    val masterElement: Canvas
    var matchElement: Boolean
    var maxHeight: Int
    var maxWidth: Int
    var menuConstructor: SCClassName
    var minHeight: Int
    var minNonEdgeSize: Int
    var minWidth: Int
    var momentumScrollMinSpeed: Int
    var mouseDown: js.ThisFunction0[classHandler, Boolean]
    var mouseMove: js.ThisFunction0[classHandler, Boolean]
    var mouseOut: js.ThisFunction0[classHandler, Boolean]
    var mouseOver: js.ThisFunction0[classHandler, Boolean]
    var mouseStillDown: js.ThisFunction0[classHandler, Boolean]
    var mouseStillDownDelay: Int
    var mouseStillDownInitialDelay: Int
    var mouseUp: js.ThisFunction0[classHandler, Boolean]
    var mouseWheel: js.ThisFunction0[classHandler, Boolean]
    def moveAbove(canvas: Canvas): void
    def moveBelow(canvas: Canvas): void
    def moveBy(deltaX: Int, deltaY: Int): Boolean
    var moved: js.ThisFunction2[classHandler, Int, Int, void]
    def moveTo(left: Int = js.native, top: Int = js.native): Boolean
    var noDoubleClicks: Boolean
    val opacity: Int
    val overflow: Overflow
    var owner: JSUndefined[Canvas]
    val padding: Int
    def pageScrollDown(): void
    def pageScrollUp(): void
    val parentCanvas: Canvas
    var parentMoved: js.ThisFunction2[classHandler, Int, Int, void]
    def parentResized()
    val peers: IscArray[Canvas]
    val percentBox: PercentBoxModel
    val percentSource: Canvas
    def placeNear(left: Int = js.native, top: Int = js.native): void
    var position: Positioning
    var printChildrenAbsolutelyPositioned: Boolean
    var prompt: HTMLString
    val proportionalResizeModifiers: IscArray[KeyName]
    val proportionalResizing: ProportionalResizeMode
    def redraw(reason: String = js.native): void
    var redrawOnResize: Boolean
    def removeChild(child: Canvas, name: String = js.native): void
    def removePeer(peer: Canvas, name: String = js.native): void
    def removeSnapAlignCandidate(candidate: Canvas): void
    val resizeBarTarget: String
    def resizeBy(deltaX: Int = js.native, deltaY: Int = js.native): Boolean
    var resized: js.ThisFunction0[classHandler, Boolean]
    var resized1: JSUndefined[js.ThisFunction2[classHandler, Double, Double, Boolean]]
    def _resized(): Boolean
    var resizeFrom: IscArray[EdgeName]
    def resizeTo(width: Int = js.native, height: Int = js.native): Boolean
    def revealChild(child: Canvas | String): void
    var rightMouseDown: js.ThisFunction0[classHandler, Boolean]
    val scrollbarConstructor: String
    val scrollbarSize: Int
    def scrollBy(dX: Int, dY: Int): void
    def scrollByPercent(dX: Int, dY: Int): void
    var scrolled: js.ThisFunction0[classHandler, Boolean]
    val scrollLeft: Double
    val scrollTop: Double
    def scrollTo(left: Int = js.native, top: Int = js.native): Int
    def scrollToBottom(): void
    def scrollToLeft(): void
    def scrollToPercent(left: Int, top: Int): void
    def scrollToRight(): void
    def scrollToTop(): void
    def sendToBack(): void
    def setAccessKey(accessKey: Char): void
    def setAriaState(stateName: String, stateValue: String | Boolean | Int | Double): void
    def setBackgroundColor(newColor: CSSColor): void
    def setBackgroundImage(newImage: URL): void
    def setBorder(newBorder: String): void
    def setBottom(bottom: Int): void
    def setCanFocus(canFocus: Boolean): void
    def setContents(newContents: HTMLString): void
    def setCursor(newCursor: Cursor): void
    def setDataPath(dataPath: DataPath): void
    def setDisabled(disabled: Boolean): void
    var setDragTracker: js.ThisFunction0[classHandler, Boolean]
    def setEdgeOpacity(newOpacity: Int): void
    def setEditMode(editingOn: Boolean, editContext: EditContext = js.native, editNode: EditNode = js.native): void
    def setGroupLabelBackgroundColor(groupLabelBackgroundColor: CSSColor): void
    def setGroupTitle(newTitle: HTMLString): void
    def setHeight(height: Int | String): void
    def setHtmlElement(element: DocumentEvent): void
    def setHtmlPosition(element: DrawPosition): void
    def setImage(identifier: String, URL: SCImgURL = js.native, imgDir: String = js.native): void
    def setLeavePageSpace(newPageSpace: Int): void
    def setLeft(left: Int): void
    def setMargin(margin: Int): void
    def setOpacity(newOpacity: Int): void
    def setOverflow(newOverflow: Overflow): void
    def setPadding(newPadding: Int): void
    def setPageLeft(left: Int): void
    def setPageTop(top: Int): void
    def setPercentSource(sourceWidget: Canvas = js.native): void
    def setRect(left: Int = js.native, top: Int = js.native, width: Int = js.native, height: Int = js.native): void
    def setRight(right: Int): void
    def setShowResizeBar(show: Boolean): void
    def setShowResizeBar1(show: Boolean): Canvas
    def setShowShadow(showShadow: Boolean): void
    def setShowSnapGrid(show: Boolean): void
    def setSnapEdge(snapEdge: String): void
    def setSnapOffsetLeft(snapOffsetLeft: Int): void
    def setSnapOffsetTop(snapOffsetTop: Int): void
    def setSnapTo(snapTo: String): void
    def setStyleName(newStyle: CSSStyleName): void
    def setTabIndex(tabIndex: Int): void
    def setTop(top: Int): void
    def setValuesManager(dataPath: DataPath): void
    def setWidth(width: Int | String): void
    val shadowDepth: Int
    val shadowImage: Int
    var shadowOffset: Int
    var shadowSoftness: Int
    def shouldDragScroll(): void
    var shouldPrint: Boolean
    def shouldSnapOnDrop(dragTarget: Canvas): Boolean
    def show(animationEffect: JSUndefined[String] = js.native): void
    def showClickMask(clickAction: Callback, mode: ClickMaskMode, unmaskedTargets: Canvas | IscArray[Canvas]): String
    def showComponentMask(unmaskedChildren: IscArray[Canvas] = js.native): void
    def showContextMenu(): Boolean
    val showCustomScrollbars: Boolean
    var showDragShadow: Boolean
    val showEdges: Boolean
    var showHover: Boolean
    var showHoverComponents: Boolean
    def showNextTo(otherWidget: Canvas, side: String = js.native, canOcclude: Boolean = js.native, skipAnimation: Boolean = js.native): void
    def showRecursively(): void
    var showResizeBar: Boolean
    var showShadow: Boolean
    var showSnapGrid: Boolean
    var shrinkElementOnHide: Boolean
    var skinImgDir: URL
    var snapAlignCandidates: IscArray[Canvas]
    val snapAlignCenterLineStyle: String
    val snapAlignEdgeLineStyle: String
    var snapAxis: String
    var snapEdge: String
    val snapHDirection: String
    var snapHGap: Int
    var snapOffsetLeft: Int
    var snapOffsetTop: Int
    var snapOnDrop: Int
    var snapResizeToAlign: Int
    var snapResizeToGrid: Int
    var snapTo: String
    var snapToAlign: Boolean
    var snapToCenterAlign: Boolean
    var snapToEdgeAlign: Boolean
    var snapToGrid: Boolean
    var snapVDirection: String
    var snapVGap: Int
    var styleName: CSSStyleName
    var tabSet: JSUndefined[TabSetSS]
    var tabIndex: Int
    val top: Int
    val topElement: Canvas
    val unserialize: JSUndefined[Boolean]
    val updateEditNode: JSUndefined[js.Function2[EditContext, EditNode, _]]
    def updateHover(hoverHTML: HTMLString = js.native): void
    var useBackMask: Boolean
    var useDragMask: Boolean
    var useNativeDrag: Boolean
    val useOpacityFilter: Boolean
    val useTouchScrolling: Boolean
    var valuesManager: ValuesManager
    var visibility: Visibility
    var visibilityChanged: js.ThisFunction1[classHandler, Boolean, void]
    def visibleAtPoint(x: Int, y: Int, withinViewport: Boolean = js.native, ignoreWidgets: Canvas = js.native, upToParent: Canvas = js.native): Boolean
    var width: Int | String
    var willAcceptDrop: js.ThisFunction0[classHandler, Boolean]
}

@js.native
abstract class AbstractCanvasCompanion extends AbstractClassCompanion {
    var allowExternalFilters: Boolean = js.native
    var defaultPageSpace: Int = js.native
    def ariaEnabled(): Boolean = js.native
    def getById(ID: String): Canvas = js.native
    def getEventEdge(edgeMask: EdgeName = js.native): EdgeName = js.native
    def getPrintHTML(components: IscArray[Canvas], printProperties: PrintProperties, callback: Callback, separator: String = js.native): HTMLString = js.native
    def getPrintPreview(components: IscArray[Canvas], printProperties: PrintProperties = js.native, previewProperties: Canvas = js.native, callback: Callback = js.native, separator: String = js.native): HTMLString = js.native
    def getSnapPosition(target: Canvas | IscArray[Int], snapTo: String, snapper: Canvas | IscArray[Int], snapEdge: String = js.native): Point = js.native
    def hiliteCharacter(string: String, character: Char, hilitePrefix: String = js.native, hiliteSuffix: String = js.native): String = js.native
    def imgHTML(src: SCImgURL, width: Int = js.native, height: Int = js.native, name: String = js.native, extraStuff: String = js.native, imgDir: String = js.native): String = js.native
    var loadingImageSize: Int = js.native
    var loadingImageSrc: SCImgURL = js.native
    val neverUseFilters: Boolean = js.native
    val neverUsePNGWorkaround: Boolean = js.native
    def printComponents(components: IscArray[Canvas], printProperties: PrintProperties = js.native): void = js.native
    def setAllowExternalFilters(allExternalFilters: Boolean): void = js.native
    def setDefaultPageSpace(newDefaultPageSpace: Int): void = js.native
    def setNeverUseFilters(neverUseFilters: Boolean): void = js.native
    def showPrintPreview(components: IscArray[Canvas], printProperties: PrintProperties = js.native, printWindowProperties: PrintWindow = js.native, callback: Callback = js.native, separator: String): void = js.native
    val TAB_INDEX_FLOOR: Int = js.native
    val AUTO: String = js.native
    val ABSOLUTE: String = js.native
    val RELATIVE: String = js.native
    val INHERIT: String = js.native
    val VISIBLE: String = js.native
    val HIDDEN: String = js.native
    val COMPLETE: String = js.native
    val DRAWN: String = js.native
    val DRAWING_HANDLE: String = js.native
    val HANDLE_DRAWN: String = js.native
    val UNDRAWN: String = js.native
    def _getEditProxyPassThruProperties(editContext: JSObject): JSObject = js.native
}

@js.native
@JSName("Canvas")
object CanvasStatic extends AbstractCanvasCompanion

