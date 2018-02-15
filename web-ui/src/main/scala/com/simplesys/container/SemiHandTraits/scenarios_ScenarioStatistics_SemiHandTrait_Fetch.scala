// This file is generated automatically (at 31.10.2016 09:52:46), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.DSRequestDyn
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import ru.simplesys.defs.bo.scenarios._

 
trait scenarios_ScenarioStatistics_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {
    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    val requestData = new DSRequestDyn(request)    
    
    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"    
    
    val dataSet = ScenarioStatisticsDS(ds)    
/////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////    
    
     def receiveBase: Option[Actor.Receive] = None    
    
     def wrapperBlobGetter(blob: Blob): String = blob.asString
}