package com.simplesys.SmartClient.Layout.props

import com.simplesys.SmartClient.Control.ImgButton
import com.simplesys.SmartClient.Foundation.{Canvas, Img, Label}
import com.simplesys.SmartClient.Layout.{HLayout, Window}
import com.simplesys.System.JSObject
import com.simplesys.System.Types.AnimationAcceleration.AnimationAcceleration
import com.simplesys.System.Types.ContentLayoutPolicy.ContentLayoutPolicy
import com.simplesys.System.Types.PanelPlacement.PanelPlacement
import com.simplesys.System.Types.{CSSColor, CSSStyleName, HTMLString, SCImgURL}
import com.simplesys.option.{ScNone, ScOption}

import scala.scalajs.js

class WindowProps extends LayoutProps {
    type classHandler <: Window

    var animateMinimize: ScOption[Boolean] = ScNone
    var autoCenter: ScOption[Boolean] = ScNone
    var autoSize: ScOption[Boolean] = ScNone
    var body: ScOption[Canvas] = ScNone
    var bodyColor: ScOption[CSSColor] = ScNone
    var bodyConstructor: ScOption[String] = ScNone
    var bodyDefaults: ScOption[JSObject] = ScNone
    var bodyStyle: ScOption[CSSStyleName] = ScNone
    var bringToFrontOnMouseUp: ScOption[Boolean] = ScNone
    var canFocusInHeaderButtons: ScOption[Boolean] = ScNone
    var closeButton: ScOption[ImgButton] = ScNone
    var closeClick: ScOption[js.ThisFunction0[classHandler, Boolean]] = ScNone
    var contentLayout: ScOption[ContentLayoutPolicy] = ScNone
    var contentsType: ScOption[String] = ScNone
    var defaultMinimizeHeight: ScOption[Int] = ScNone
    var dismissOnEscape: ScOption[Boolean] = ScNone
    var dismissOnOutsideClick: ScOption[Boolean] = ScNone
    var fillSpaceStyleName: ScOption[CSSStyleName] = ScNone
    var footer: ScOption[HLayout] = ScNone
    var footerControls: ScOption[Seq[String]] = ScNone
    var footerHeight: ScOption[Int] = ScNone
    var header: ScOption[HLayout] = ScNone
    var headerBackground: ScOption[Img] = ScNone
    var headerControls: ScOption[Seq[String]] = ScNone
    var headerIcon: ScOption[Img] = ScNone
    var headerIconDefaults: ScOption[JSObject] = ScNone
    var headerLabel: ScOption[Label] = ScNone
    var headerLabelDefaults: ScOption[JSObject] = ScNone
    var headerSrc: ScOption[SCImgURL] = ScNone
    var headerStyle: ScOption[CSSStyleName] = ScNone
    var hiliteBodyColor: ScOption[CSSColor] = ScNone
    var hiliteHeaderSrc: ScOption[SCImgURL] = ScNone
    var hiliteHeaderStyle: ScOption[CSSStyleName] = ScNone
    var isModal: ScOption[Boolean] = ScNone
    var items: ScOption[Seq[Canvas]] = ScNone
    var maximizeButton: ScOption[ImgButton] = ScNone
    var maximized: ScOption[Boolean] = ScNone
    var minimizeAcceleration: ScOption[AnimationAcceleration] = ScNone
    var minimizeButton: ScOption[ImgButton] = ScNone
    var minimized: ScOption[Boolean] = ScNone
    var minimizeHeight: ScOption[Int] = ScNone
    var minimizeTime: ScOption[Int] = ScNone
    var modalMask: ScOption[Canvas] = ScNone
    var modalMaskOpacity: ScOption[Int] = ScNone
    var modalMaskStyle: ScOption[String] = ScNone
    var placement: ScOption[PanelPlacement] = ScNone
    var printBodyStyle: ScOption[CSSStyleName] = ScNone
    var printHeaderStyle: ScOption[CSSStyleName] = ScNone
    var resizer: ScOption[ImgButton] = ScNone
    var restoreButton: ScOption[ImgButton] = ScNone
    var showBody: ScOption[Boolean] = ScNone
    var showCloseButton: ScOption[Boolean] = ScNone
    var showFooter: ScOption[Boolean] = ScNone
    var showHeader: ScOption[Boolean] = ScNone
    var showHeaderBackground: ScOption[Boolean] = ScNone
    var showHeaderIcon: ScOption[Boolean] = ScNone
    var showMaximizeButton: ScOption[Boolean] = ScNone
    var showMinimizeButton: ScOption[Boolean] = ScNone
    var showModalMask: ScOption[Boolean] = ScNone
    var showResizer: ScOption[Boolean] = ScNone
    var showStatusBar: ScOption[Boolean] = ScNone
    var showTitle: ScOption[Boolean] = ScNone
    var src: ScOption[String] = ScNone
    var status: ScOption[String] = ScNone
    var statusBar: ScOption[Canvas] = ScNone
    var title: ScOption[HTMLString] = ScNone
}
