package com.simplesys.SmartClient.Grids.treeGrid

import com.simplesys.SmartClient.Drawing.DrawItem
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.System.Types.{HTMLString, SCImgURL}
import com.simplesys.System.{JSObject, JSUndefined}

import scala.language.dynamics
import scala.scalajs.js

@js.native
trait TreeNode extends JSObject{

    var canAcceptDrop: JSUndefined[Boolean]
    var canDrag:  JSUndefined[Boolean]
    var children:  JSUndefined[IscArray[TreeNode]]
    var enabled:  JSUndefined[Boolean]
    var icon:  JSUndefined[SCImgURL]
    var id: JSUndefined[String]
    var isFolder: JSUndefined[Boolean]
    var name:  JSUndefined[String]
    var parentId: JSUndefined[String]
    var showDropIcon:  JSUndefined[Boolean]
    var showOpenIcon:  JSUndefined[Boolean]
    var title:  JSUndefined[HTMLString]
    var liveObject:  JSUndefined[DrawItem]
}
