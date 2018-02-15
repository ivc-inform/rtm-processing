package com.simplesys.components.formItems

import language.implicitConversions

object ScalaTypes extends Enumeration {
    type ScalaTypes = Value
    val Int, String, Long, Double, AtomicInteger, Boolean, AtomicDouble, AtomicLong, Unit = Value
}

object ScalaTypes1 extends Enumeration {
    type ScalaTypes = Value
    val Int, String, Double, Boolean = Value
}

object ScopeVisiblity extends Enumeration {
    type ScopeVisiblity = Value
    val scenario, instance, entryCheck, state = Value
}
