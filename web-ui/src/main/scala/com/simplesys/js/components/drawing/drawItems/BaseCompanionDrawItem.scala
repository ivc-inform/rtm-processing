package com.simplesys.js.components.drawing.drawItems

trait BaseCompanionDrawItem {
    lazy val typeName = this.getClass.getSimpleName.replace("$", "")
}




