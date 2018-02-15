package com.simplesys.SmartClient.Foundation.props

import com.simplesys.SmartClient.Control.MenuSS
import com.simplesys.SmartClient.Forms.formsItems.CanvasItem
import com.simplesys.SmartClient.Forms.ValuesManager
import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.WindowSS
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.System.props.ClassProps
import com.simplesys.SmartClient.Tools.{EditContext, EditNode, EditProxy}
import com.simplesys.System.Types.Alignment.Alignment
import com.simplesys.System.Types.AnimationAcceleration.AnimationAcceleration
import com.simplesys.System.Types.BackgroundRepeat._
import com.simplesys.System.Types.Cursor._
import com.simplesys.System.Types.DragAppearance._
import com.simplesys.System.Types.DragIntersectStyle._
import com.simplesys.System.Types.DragMaskType._
import com.simplesys.System.Types.DrawPosition.DrawPosition
import com.simplesys.System.Types.EdgeName.EdgeName
import com.simplesys.System.Types.LocatorStrategy.LocatorStrategy
import com.simplesys.System.Types.LocatorTypeStrategy.LocatorTypeStrategy
import com.simplesys.System.Types.Overflow.Overflow
import com.simplesys.System.Types.PercentBoxModel.PercentBoxModel
import com.simplesys.System.Types.Positioning.Positioning
import com.simplesys.System.Types.ProportionalResizeMode.ProportionalResizeMode
import com.simplesys.System.Types.VerticalAlignment.VerticalAlignment
import com.simplesys.System.Types.Visibility.Visibility
import com.simplesys.System.Types._
import com.simplesys.System._
import com.simplesys.option._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction1, ThisFunction2}

class CanvasProps extends ClassProps {
    type classHandler <: Canvas

    var accessKey: ScOption[String] = ScNone
    var alwaysShowScrollbars: ScOption[Boolean] = ScNone
    var animateAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateFadeTime: ScOption[Int] = ScNone
    var animateHideAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateHideEffect: ScOption[AnimateShowEffectId] = ScNone
    var animateHideTime: ScOption[Int] = ScNone
    var animateMoveAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateMoveTime: ScOption[Int] = ScNone
    var animateRectAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateRectTime: ScOption[Int] = ScNone
    var animateResizeAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateResizeTime: ScOption[Int] = ScNone
    var animateScrollAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateScrollTime: ScOption[Int] = ScNone
    var animateShowAcceleration: ScOption[AnimationAcceleration] = ScNone
    var animateShowEffect: ScOption[AnimateShowEffectId] = ScNone
    var animateShowTime: ScOption[Int] = ScNone
    var animateTime: ScOption[Int] = ScNone
    var appImgDir: ScOption[URL] = ScNone
    var ariaRole: ScOption[String] = ScNone
    var ariaState: ScOption[String] = ScNone
    var autoDraw: ScOption[Boolean] = ScNone
    var autoShowParent: ScOption[Boolean] = ScNone
    var backgroundColor: ScOption[CSSColor] = ScNone
    var backgroundImage: ScOption[SCImgURL] = ScNone
    var backgroundPosition: ScOption[String] = ScNone
    var backgroundRepeat: ScOption[BackgroundRepeat] = ScNone
    var beforeDestroy: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var border: ScOption[String] = ScNone
    var _constructor: ScOption[String] = ScNone
    var canAcceptDrop: ScOption[Boolean] = ScNone
    var canDrag: ScOption[Boolean] = ScNone
    var canDragReposition: ScOption[Boolean] = ScNone
    var canDragResize: ScOption[Boolean] = ScNone
    var canDragScroll: ScOption[Boolean] = ScNone
    var canDrop: ScOption[Boolean] = ScNone
    var canDropBefore: ScOption[Boolean] = ScNone
    var canFocus: ScOption[Boolean] = ScNone
    var canHover: ScOption[Boolean] = ScNone
    var canSelectText: ScOption[Boolean] = ScNone
    var canvasItem: ScOption[CanvasItem] = ScNone
    var children: ScOption[Seq[Canvas]] = ScNone
    var childrenResizeSnapAlign: ScOption[Boolean] = ScNone
    var childrenSnapAlign: ScOption[Boolean] = ScNone
    var childrenSnapCenterAlign: ScOption[Boolean] = ScNone
    var childrenSnapEdgeAlign: ScOption[Boolean] = ScNone
    var childrenSnapResizeToGrid: ScOption[Boolean] = ScNone
    var childrenSnapToGrid: ScOption[Boolean] = ScNone
    var click: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var componentMask: ScOption[Canvas] = ScNone
    var componentMaskDefaults: ScOption[Canvas] = ScNone
    var contents: ScOption[HTMLString] = ScNone
    var contextMenu: ScOption[MenuSS] = ScNone
    var cursor: ScOption[Cursor] = ScNone
    var customEdges: ScOption[Seq[String]] = ScNone
    var dataPath: ScOption[DataPath] = ScNone
    var defaultHeight: ScOption[Int] = ScNone
    var defaultWidth: ScOption[Int] = ScNone
    var destroyed: ScOption[Boolean] = ScNone
    var destroying: ScOption[Boolean] = ScNone
    var disabled: ScOption[Boolean] = ScNone
    var disabledCursor: ScOption[Cursor] = ScNone
    var disableTouchScrollingForDrag: ScOption[Boolean] = ScNone
    var doubleClick: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var doubleClickDelay: ScOption[Int] = ScNone
    var dragAppearance: ScOption[DragAppearance] = ScNone
    var dragIntersectStyle: ScOption[DragIntersectStyle] = ScNone
    var dragMaskType: ScOption[DragMaskType] = ScNone
    var dragMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragOpacity: ScOption[Int] = ScNone
    var dragRepositionAppearance: ScOption[DragAppearance] = ScNone
    var dragRepositionCursor: ScOption[Cursor] = ScNone
    var dragRepositionMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragRepositionStart: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragRepositionStop: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragResizeAppearance: ScOption[DragAppearance] = ScNone
    var dragResizeMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragResizeStart: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragResizeStop: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragScrollDelay: ScOption[Int] = ScNone
    var dragStart: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragStartDistance: ScOption[Int] = ScNone
    var dragStop: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dragTarget: ScOption[Canvas] = ScNone
    var dragType: ScOption[String] = ScNone
    var draw: ScOption[ThisFunction1[classHandler, JSUndefined[IscArray[JSAny]], Canvas]] = ScNone
    var drop: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dropMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dropOut: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dropOver: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var dropTypes: ScOption[String] = ScNone
    var dynamicContents: ScOption[Boolean] = ScNone
    var dynamicContentsVars: ScOption[ValueMap] = ScNone
    var edgeBackgroundColor: ScOption[Color] = ScNone
    var edgeCenterBackgroundColor: ScOption[Color] = ScNone
    var edgeCursorMap: ScOption[JSObject] = ScNone
    var edgeImage: ScOption[SCImgURL] = ScNone
    var edgeMarginSize: ScOption[Int] = ScNone
    var edgeOffset: ScOption[Int] = ScNone
    var edgeOpacity: ScOption[Int] = ScNone
    var edgeShowCenter: ScOption[Boolean] = ScNone
    var edgeSize: ScOption[Int] = ScNone
    var editNode: ScOption[EditNode] = ScNone
    var editContext: ScOption[EditContext] = ScNone
    var editProxyProperties: ScOption[EditProxy] = ScNone //Не менять !!!!
    var editProxyConstructor: ScOption[SCClassName] = ScNone
    var extraSpace: ScOption[Int] = ScNone
    var focusChanged: ScOption[ThisFunction1[classHandler, Boolean, void]] = ScNone
    var getHoverHTML: ScOption[js.Function0[HTMLString]] = ScNone
    var getInnerHTML: ScOption[js.Function0[HTMLString]] = ScNone
    var groupBorderCSS: ScOption[String] = ScNone
    var groupLabelBackgroundColor: ScOption[CSSColor] = ScNone
    var groupLabelStyleName: ScOption[CSSStyleName] = ScNone
    var groupTitle: ScOption[HTMLString] = ScNone
    var height: ScOption[IntString[Int, String]] = ScNone
    var hideUsingDisplayNone: ScOption[Boolean] = ScNone
    var hover: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var hoverAlign: ScOption[Alignment] = ScNone
    var hoverAutoDestroy: ScOption[Boolean] = ScNone
    var hoverDelay: ScOption[Int] = ScNone
    var hoverHeight: ScOption[Int] = ScNone
    var hoverMoveWithMouse: ScOption[Boolean] = ScNone
    var hoverOpacity: ScOption[Int] = ScNone
    var hoverStyle: ScOption[CSSStyleName] = ScNone
    var hoverVAlign: ScOption[VerticalAlignment] = ScNone
    var hoverWidth: ScOption[Int] = ScNone
    var hoverWrap: ScOption[Boolean] = ScNone
    var htmlElement: ScOption[HTMLElement] = ScNone
    var htmlPosition: ScOption[DrawPosition] = ScNone
    var funcMenu: ScOption[MenuSS] = ScNone
    var ID: ScOption[String] = ScNone
    var initWidget: ScOption[ThisFunction1[classHandler, IscArray[JSAny], _]] = ScNone
    var isGroup: ScOption[Boolean] = ScNone
    var isPrinting: ScOption[Boolean] = ScNone
    var isSnapAlignCandidate: ScOption[Boolean] = ScNone
    var keepInParentRect: ScOption[Boolean] = ScNone
    var keyDown: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var keyPress: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var keyUp: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var layoutAlign: ScOption[DoubleAlignment[Alignment, VerticalAlignment]] = ScNone
    var leavePageSpace: ScOption[Int] = ScNone
    var left: ScOption[IntString[Int, String]] = ScNone
    var locateChildrenBy: ScOption[LocatorStrategy] = ScNone
    var locateChildrenType: ScOption[LocatorTypeStrategy] = ScNone
    var locatePeersBy: ScOption[LocatorStrategy] = ScNone
    var locatePeersType: ScOption[LocatorTypeStrategy] = ScNone
    var margin: ScOption[Int] = ScNone
    var masterElement: ScOption[Canvas] = ScNone
    var matchElement: ScOption[Boolean] = ScNone
    var maxHeight: ScOption[Int] = ScNone
    var maxWidth: ScOption[Int] = ScNone
    var menuConstructor: ScOption[SCClassName] = ScNone
    var minHeight: ScOption[Int] = ScNone
    var minNonEdgeSize: ScOption[Int] = ScNone
    var minWidth: ScOption[Int] = ScNone
    var momentumScrollMinSpeed: ScOption[Int] = ScNone
    var mouseDown: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseMove: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseOut: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseOver: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseStillDown: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseStillDownDelay: ScOption[Int] = ScNone
    var mouseStillDownInitialDelay: ScOption[Int] = ScNone
    var mouseUp: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var mouseWheel: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var moved: ScOption[ThisFunction2[classHandler, Int, Int, void]] = ScNone
    var noDoubleClicks: ScOption[Boolean] = ScNone
    var opacity: ScOption[Int] = ScNone
    var overflow: ScOption[Overflow] = ScNone
    var owner: ScOption[Canvas] = ScNone
    var padding: ScOption[Int] = ScNone
    var parentCanvas: ScOption[Canvas] = ScNone
    var parentMoved: ScOption[ThisFunction2[classHandler, Int, Int, void]] = ScNone
    var peers: ScOption[Seq[Canvas]] = ScNone
    var percentBox: ScOption[PercentBoxModel] = ScNone
    var percentSource: ScOption[Canvas] = ScNone
    var position: ScOption[Positioning] = ScNone
    var printChildrenAbsolutelyPositioned: ScOption[Boolean] = ScNone
    var prompt: ScOption[HTMLString] = ScNone
    var proportionalResizeModifiers: ScOption[Seq[KeyName]] = ScNone
    var proportionalResizing: ScOption[ProportionalResizeMode] = ScNone
    var redraw: ScOption[ThisFunction1[classHandler, IscArray[JSAny], _]] = ScNone
    var redrawOnResize: ScOption[Boolean] = ScNone
    var resizeBarTarget: ScOption[String] = ScNone
    var resized: ScOption[ThisFunction0[classHandler, _]] = ScNone
    var resizeFrom: ScOption[Seq[EdgeName]] = ScNone
    var rightMouseDown: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var scrollbarConstructor: ScOption[String] = ScNone
    var scrollbarSize: ScOption[Int] = ScNone
    var scrolled: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var setDragTracker: ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
    var shadowDepth: ScOption[Int] = ScNone
    var shadowImage: ScOption[Int] = ScNone
    var shadowOffset: ScOption[Int] = ScNone
    var shadowSoftness: ScOption[Int] = ScNone
    var shouldPrint: ScOption[Boolean] = ScNone
    var showCustomScrollbars: ScOption[Boolean] = ScNone
    var showDragShadow: ScOption[Boolean] = ScNone
    var showEdges: ScOption[Boolean] = ScNone
    var showHover: ScOption[Boolean] = ScNone
    var showHoverComponents: ScOption[Boolean] = ScNone
    var showResizeBar: ScOption[Boolean] = ScNone
    var showShadow: ScOption[Boolean] = ScNone
    var showSnapGrid: ScOption[Boolean] = ScNone
    var shrinkElementOnHide: ScOption[Boolean] = ScNone
    var skinImgDir: ScOption[URL] = ScNone
    var snapAlignCandidates: ScOption[Seq[Canvas]] = ScNone
    var snapAlignCenterLineStyle: ScOption[String] = ScNone
    var snapAlignEdgeLineStyle: ScOption[String] = ScNone
    var snapAxis: ScOption[String] = ScNone
    var snapEdge: ScOption[String] = ScNone
    var snapHDirection: ScOption[String] = ScNone
    var snapHGap: ScOption[Int] = ScNone
    var snapOffsetLeft: ScOption[Int] = ScNone
    var snapOffsetTop: ScOption[Int] = ScNone
    var snapOnDrop: ScOption[Int] = ScNone
    var snapResizeToAlign: ScOption[Int] = ScNone
    var snapResizeToGrid: ScOption[Int] = ScNone
    var snapTo: ScOption[String] = ScNone
    var snapToAlign: ScOption[Boolean] = ScNone
    var snapToCenterAlign: ScOption[Boolean] = ScNone
    var snapToEdgeAlign: ScOption[Boolean] = ScNone
    var snapToGrid: ScOption[Boolean] = ScNone
    var snapVDirection: ScOption[String] = ScNone
    var snapVGap: ScOption[Int] = ScNone
    var styleName: ScOption[CSSStyleName] = ScNone
    var tabIndex: ScOption[Int] = ScNone
    var top: ScOption[IntString[Int, String]] = ScNone
    var topElement: ScOption[Canvas] = ScNone
    var useBackMask: ScOption[Boolean] = ScNone
    var useDragMask: ScOption[Boolean] = ScNone
    var useNativeDrag: ScOption[Boolean] = ScNone
    var useOpacityFilter: ScOption[Boolean] = ScNone
    var useTouchScrolling: ScOption[Boolean] = ScNone
    var valuesManager: ScOption[ValuesManager] = ScNone
    var visibility: ScOption[Visibility] = ScNone
    var visibilityChanged: ScOption[ThisFunction1[classHandler, Boolean, void]] = ScNone
    var width: ScOption[IntString[Int, String]] = ScNone
    var willAcceptDrop : ScOption[ThisFunction0[classHandler, Boolean]] = ScNone
}
