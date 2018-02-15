package com.simplesys.SmartClient.System

import com.simplesys.System.{JSDictionaryAny, JSObject}
import com.simplesys.json.JSON._
import com.simplesys.macros.PropsToDictionary
import com.simplesys.props.AbstractClassProps

import scala.reflect.ClassTag
import scala.scalajs.js

class SCApply[T <: Class, P <: AbstractClassProps](implicit classTag_T: ClassTag[T], propsToDictionary: PropsToDictionary[P]) {
    def create(propsClass: P): T = js.Dynamic.global.isc.selectDynamic(classTag_T.runtimeClass.getSimpleName).create(propsToDictionary.getDictionary(propsClass)).asInstanceOf[T]
    def create(): T = js.Dynamic.global.isc.selectDynamic(classTag_T.runtimeClass.getSimpleName).create().asInstanceOf[T]

    def apply(propsClass: P): T = propsToDictionary.getDictionary(propsClass).asInstanceOf[T]
    def jsonString(propsClass: P): String = propsToDictionary.getMap(propsClass).toMap.toJsonString()
    def GetClass: T = js.Dynamic.global.isc.selectDynamic(classTag_T.runtimeClass.getSimpleName).`getClass()`.asInstanceOf[T]
}

//class SCApplyDate[T <: Date, P <: AbstractClassProps](propsToDictionary: PropsToDictionary[P])(implicit classTag_T: ClassTag[T]) {
//    def create(propsClass: P): T = js.Dynamic.global.isc.selectDynamic(classTag_T.runtimeClass.getSimpleName).create(propsToDictionary.getDictionary(propsClass)).asInstanceOf[T]
//}

class SCApply4Object[T <: JSObject, P <: AbstractClassProps](implicit classTag_T: ClassTag[T], propsToDictionary: PropsToDictionary[P]) {
    def apply(propsClass: P): T = propsToDictionary.getDictionary(propsClass).asInstanceOf[T]
}

class SCApply4Props[P <: AbstractClassProps](implicit propsToDictionary: PropsToDictionary[P]) {
    def apply(propsClass: P): JSDictionaryAny = propsToDictionary.getDictionary(propsClass)
}

//class SCDefine[S <: Class, T <: Class, P <: AbstractClassProps](implicit classTag_S: ClassTag[S], classTag_T: ClassTag[T], propsToDictionary: PropsToDictionary[P]) {
//    def defineClass(propsClass: P): Unit = js.Dynamic.global.isc.applyDynamic("defineClass")(classTag_T.runtimeClass.getSimpleName.dblQuoted, s"isc.${classTag_S.runtimeClass.getSimpleName}").addProperties(propsToDictionary.getDictionary(propsClass))
//    def apply(propsClass: P): T = propsToDictionary.getDictionary(propsClass).asInstanceOf[T]
//}
