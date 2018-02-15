package com.simplesys.SmartClient.System

import com.simplesys.SmartClient.App.{AbstractCommonTreeListGridEditorComponentCompanion, AbstractSettingsEditorCompanion}
import com.simplesys.SmartClient.Control._
import com.simplesys.SmartClient.DataBinding.{AbstractDataBoundComponentCompanion, AbstractDataSourceSSCompanion, AbstractJSONCompanion, AbstractOfflineSSCompanion}
import com.simplesys.SmartClient.Drawing.drawItem.{AbstractDrawLineCompanion, AbstractDrawRectCompanion}
import com.simplesys.SmartClient.Drawing.{AbstractDrawItemCompanion, AbstractDrawPaneCompanion}
import com.simplesys.SmartClient.Forms.formsItems._
import com.simplesys.SmartClient.Forms.{AbstractDateChooserCompanion, AbstractDynamicFormCompanion}
import com.simplesys.SmartClient.Foundation.{AbstractCanvasCompanion, AbstractRichTextEditorCompanion}
import com.simplesys.SmartClient.Grids._
import com.simplesys.SmartClient.Layout.{AbstractWindowSSCompanion, WindowSS}
import com.simplesys.SmartClient.Messaging.AbstractMessagingSSCompanion
import com.simplesys.SmartClient.RPC.AbstractRPCManagerCompanion
import com.simplesys.SmartClient.System.date.{AbstractDateCompanion, AbstractTimeCompanion}
import com.simplesys.SmartClient.System.{ListGrid ⇒ _, ListGridEditor ⇒ _}
import com.simplesys.SmartClient.Tools.EditContextCompanion
import com.simplesys.SmartClient.Tools.editProxy.{AbstractDrawItemEditProxyCompanion, AbstractDrawPaneEditProxyCompanion}
import com.simplesys.SmartClient.math.AbstractAffineTransformCompanion
import com.simplesys.System.Types.{Callback, ID, void}
import com.simplesys.System._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSBracketAccess, JSName}
import scala.scalajs.js.|

@js.native
object isc extends JSObject {

    import com.simplesys.SmartClient.App.AbstractCommonListGridEditorComponentCompanion
    import com.simplesys.SmartClient.DataBinding.{AbstractDataSourceCompanion, AbstractOfflineCompanion}

    def applyMask[T](input: JSObject, mask: IscArray[String]): T = js.native
    def addDefaults(destination: JSObject, source: JSObject): JSObject = js.native
    def addProperties[T](destination: JSObject, propsArray: JSAny*): T = js.native
    def addMethods[T](destination: JSObject, metods: JSObject): T = js.native
    @JSName("addProperties")
    def addPropertiesDyn(destination: JSObject, propsArray: JSAny*): js.Dynamic = js.native
    def ask(message: String, callback: js.Function1[Boolean, _] = js.native, properties: Dialog = js.native): void = js.native
    def askForValue(message: String, callback: js.Function1[JSAny, _] = js.native, properties: Dialog = js.native): void = js.native
    def clearPrompt(): void = js.native
    def clone[T](obj: T): T = js.native
    def shallowClone[T](obj: T): T = js.native
    def confirm(message: String, callback: Callback = js.native, properties: Dialog = js.native): void = js.native
    def defineClass[T <: Class](className: String, superClass: String): T = js.native
    def dismissCurrentDialog(): void = js.native
    def echo(value: JSAny): String = js.native
    def echoAll(value: JSAny): String = js.native
    def echoLeaf(value: JSAny): String = js.native
    def eval(expression: String): JSAny = js.native
    def firstKey(obj: JSObject): String = js.native
    def getIconLeft(icon: JSObject): Int = js.native
    def getIconPageRect(icon: JSObject): IscArray[Int] = js.native
    def getIconRect(icon: JSObject): IscArray[Int] = js.native
    def getIconTop(icon: JSObject): Int = js.native
    def getKeyForValue(value: Int, valueMap: JSDictionary[JSObject], defaultKey: JSAny): JSAny = js.native
    def getKeys(obj: JSObject): IscArray[String] = js.native
    def getValueForKey(value: Int, valueMap: JSDictionary[JSObject], defaultKey: JSAny): JSAny = js.native
    def getValues(obj: JSObject): IscArray[JSAny] = js.native
    def logEcho(value: JSAny, message: String): void = js.native
    def logEchoAll(value: JSAny, message: String): void = js.native
    def logWarn(message: String, category: String = js.native): void = js.native
    def makeReverseMap(valueMap: JSDictionary[JSObject]): JSDictionary[JSObject] = js.native
    def overwriteClass(): void = js.native
    def propertyDefined(obj: JSObject, propertyName: String): Boolean = js.native
    def say(message: String, callback: Callback = js.native, properties: Dialog = js.native): void = js.native
    def setAutoDraw(enable: Boolean = js.native): void = js.native
    def setScreenReaderMode(newState: Boolean): void = js.native
    def shallowClone(obj: JSObject | IscArray[JSObject]): JSObject | IscArray[JSObject] = js.native
    def showFadingPrompt(message: String, duration: Int, callback: Callback = js.native, properties: Dialog = js.native): void = js.native
    def showLoginDialog(loginFunc: js.Function2[JSDictionary[String], js.Function1[Boolean, void], void], properties: LoginDialog = js.native): void = js.native
    def showPrompt(message: String, properties: Dialog = js.native): void = js.native
    def sortObject(obj: JSObject, comparator: js.Function): JSObject = js.native
    def sortObjectByProperties(obj: JSObject, comparator: js.Function): JSObject = js.native
    def timeStamp(): Double = js.native
    def warn(message: String, callback: Callback = js.native, properties: Dialog = js.native): void = js.native
    def createClass[T](className: String, args: IscArray[JSAny] = js.native): T = js.native

    val params: com.simplesys.SmartClient.System.params.type = js.native
    val Log: AbstractLogCompanion = js.native
    val Class: AbstractClassCompanion = js.native
    val PickList: AbstractPickListCompanion = js.native
    val Canvas: AbstractCanvasCompanion = js.native
    val EventHandler: AbstractEventHandlerCompanion = js.native
    val EditContext: EditContextCompanion = js.native
    val isA: isAStatic = js.native
    var captureDefaults: JSUndefined[JSAny] = js.native
    val ClassFactory: ClassFactoryTrt = js.native
    val DrawItem: AbstractDrawItemCompanion = js.native
    val DataSource: AbstractDataSourceCompanion = js.native
    val Time: AbstractTimeCompanion = js.native
    val Timer: AbstractTimerCompanion = js.native
    val DrawLine: AbstractDrawLineCompanion = js.native
    val DrawRect: AbstractDrawRectCompanion = js.native
    val DrawPane: AbstractDrawPaneCompanion = js.native
    val RichTextEditor: AbstractRichTextEditorCompanion = js.native
    val DateChooser: AbstractDateChooserCompanion = js.native
    val AffineTransform: AbstractAffineTransformCompanion = js.native
    val OfflineSS: AbstractOfflineSSCompanion = js.native
    val Offline: AbstractOfflineCompanion = js.native
    val RPCManager: AbstractRPCManagerCompanion = js.native
    val DataSourceSS: AbstractDataSourceSSCompanion = js.native
    val DateTimeItem: AbstractDateTimeItemCompanion = js.native
    val WindowSS: AbstractWindowSSCompanion = js.native
    val Date: AbstractDateCompanion = js.native
    val DateUtil: AbstractDateUtilCompanion = js.native
    val DynamicForm: AbstractDynamicFormCompanion = js.native
    val SettingsEditor: AbstractSettingsEditorCompanion = js.native
    val _traceMarkers: JSUndefined[Boolean] = js.native
    val MessagingSS: AbstractMessagingSSCompanion = js.native
    val DataBoundComponent: AbstractDataBoundComponentCompanion = js.native
    val ListGrid: AbstractListGridCompanion = js.native
    val FormItem: AbstractFormItemCompanion = js.native
    val DrawPaneEditProxy: AbstractDrawPaneEditProxyCompanion = js.native
    val DrawItemEditProxy: AbstractDrawItemEditProxyCompanion = js.native
    val CommonTreeListGridEditorComponent: AbstractCommonTreeListGridEditorComponentCompanion = js.native
    val FormItemWithButtons: AbstractFormItemWithButtonsCompanion = js.native
    val CommonListGridEditorComponent: AbstractCommonListGridEditorComponentCompanion = js.native


    def error(message: String, identifier: ID = js.native, callback: Callback = js.native): WindowSS = js.native
    def errorDetail(message: String , detailMessage: String , identifier: ID = js.native, detailIdentifier: ID = js.native): WindowSS = js.native
    def info(message: String, identifier: ID = js.native, callback: Callback = js.native): WindowSS = js.native
    def ok(message: String, identifier: ID = js.native, callback: Callback = js.native): WindowSS = js.native
    def infos(gridProperties: ListGrid | ListGridEditor, identifier: ID = js.native, callback: Callback = js.native): WindowSS = js.native
    def errors(gridproperties: ListGrid | ListGridEditor, identifier: ID = js.native, callback: Callback = js.native): WindowSS = js.native
    val JSON: AbstractJSONCompanion = js.native
    def debugTrac[T](obj: T*): T = js.native
    def debugTrap[T](obj: T*): T = js.native
    def deletePrivateProps[T](obj: T*): T = js.native
    def getPropValue[T](obj: JSObject, name: String): JSUndefined[T] = js.native
    def setPropValue[T](obj: JSObject, name: String, value: T): JSUndefined[T] = js.native
    def getWindowObject[T](name: String): JSUndefined[T] = js.native
    def setArrayItem[A](array: IscArray[A], index: Int, value: JSAny): void = js.native
    def deleteProp(obj: JSObject | JSDynamic, propName: String): void = js.native

    @JSBracketAccess
    def apply(name: String): JSUndefined[AbstractClassCompanion] = js.native

    @JSBracketAccess
    def update(name: String, value: Class | JSObject): Unit = js.native

    def js_beautify(str: String): String = js.native
    var copiedJoson: JSUndefined[String] = js.native
}


