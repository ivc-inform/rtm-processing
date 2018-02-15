package com.simplesys.SmartClient.System

import com.simplesys.System.JSObject
import com.simplesys.System.Types.{Callback, void}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
object FileLoader extends JSObject {
    type OnLoad = js.Function0[_]
    var addVersionToLoadTags: Boolean = js.native
    def cacheEdgeImages(baseURLs: IscArray[String], showCenter: Boolean = js.native, edges: IscArray[String] = js.native, colors: IscArray[String] = js.native, onload: OnLoad): void = js.native
    def cacheFiles(URLs: IscArray[String], onload: OnLoad = js.native, `type`: String = js.native): void = js.native
    def cacheImgStates(baseURLs: IscArray[String], states: IscArray[String] = js.native, onload: OnLoad = js.native): void = js.native
    def cacheISC(skin: String = js.native, modules: IscArray[String] = js.native, onload: OnLoad = js.native): void = js.native
    def cacheLocale(locale: String = js.native, onloa: OnLoad = js.native): void = js.native
    def cacheModules(modules: IscArray[String], onload: OnLoad = js.native): void = js.native
    def cacheShadows(baseDir: String, depths: IscArray[String], baseShadowImage: String = js.native, onload: OnLoad = js.native): void = js.native
    def cacheStretchImgStates(baseURLs: IscArray[String], states: IscArray[String] = js.native, pieces: IscArray[String] = js.native, onload: OnLoad = js.native): void = js.native
    var defaultModules: String = js.native
    var defaultSkin: String = js.native
    def ensureLoaded(callback: Callback): void = js.native
    def loadISC(skin: String = js.native, modules: IscArray[String] = js.native, onload: OnLoad = js.native): void = js.native
    def loadSkin(skin: String, onload: OnLoad = js.native): void = js.native
    def loadJSFiles(URLs: IscArray[String] | String, onload: OnLoad = js.native): void = js.native
    def loadLocale(locale: String = js.native, onload: OnLoad = js.native): void = js.native
    def loadModules(modules: IscArray[String] = js.native, onload: OnLoad = js.native): void = js.native
    val modulesDir: String = js.native
    val versionParamName: String = js.native
}
