package com.simplesys.SmartClient.Drawing

import com.simplesys.SmartClient.System.{IscArray, Point}

object Point {
    def apply(x: Double, y: Double) : Point = IscArray(x,y)
}


