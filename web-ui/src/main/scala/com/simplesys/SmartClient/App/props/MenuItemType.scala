package com.simplesys.SmartClient.App.props

import language.implicitConversions

object MenuItemType extends Enumeration {
    type MenuItemType = Value
    val miCopy, miEdit, miDelete, miRefresh = Value
}
