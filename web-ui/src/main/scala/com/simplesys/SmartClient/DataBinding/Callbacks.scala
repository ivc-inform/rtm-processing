package com.simplesys.SmartClient.DataBinding

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.Layout.SectionStack
import com.simplesys.SmartClient.RPC.{RPCRequest, RPCResponse}
import com.simplesys.SmartClient.System.IscArray
import com.simplesys.SmartClient.Tools.PaletteNode
import com.simplesys.System.{JSObject, JSUndefined}

import scala.scalajs.js
import scala.scalajs.js.{ThisFunction0, ThisFunction1, ThisFunction2}

object Callbacks {
    type AnimationCallback = ThisFunction1[Canvas#classHandler, Boolean, _]

    type CollapseSectionCallback = ThisFunction0[SectionStack#classHandler, _]

    type ExpandSectionCallback = ThisFunction0[SectionStack#classHandler, _]

    type HideSectionCallback = ThisFunction0[SectionStack#classHandler, _]

    type ShowSectionCallback = ThisFunction0[SectionStack#classHandler, _]

    type ClientOnlyDataSourceCallback = ThisFunction0[DataSource, _]

    type GetFileCallback = ThisFunction2[DSResponse, String, DSRequest, _]

    type GetFileVersionCallback = ThisFunction2[DSResponse, String, DSRequest, _]

    type HasFileCallback = ThisFunction2[DSResponse, String, DSRequest, _]

    type DSCallback = js.Function3[DSResponse, _ <: JSObject, DSRequest, _]

    type RPCQueueCallback = js.Function1[IscArray[RPCResponse], _]

    type RPCCallback = js.Function3[RPCResponse, _ <: JSUndefined[JSObject], RPCRequest, _]

    type DataURLCallback = js.Function1[String, _]

    type PaletteNodeCallback = js.Function1[IscArray[PaletteNode], _]
}


