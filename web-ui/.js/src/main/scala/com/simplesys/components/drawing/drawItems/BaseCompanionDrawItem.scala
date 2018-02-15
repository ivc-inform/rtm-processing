package com.simplesys.components.drawing.drawItems

trait BaseCompanionDrawItem {
    lazy val typeName = this.getClass.getSimpleName.replace("$", "")
}




