package com.simplesys.js.components

import com.simplesys.SmartClient.System.Class

trait SCComponent[C <: Class] {
    def get: C
}

