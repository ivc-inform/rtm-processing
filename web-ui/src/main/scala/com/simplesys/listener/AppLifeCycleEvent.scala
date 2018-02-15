package com.simplesys.listener

import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.bonecp.BoneCPDataSource
import com.simplesys.rtm.app.{InitData, InitDataWeb, RtmProcessingApp}
import com.simplesys.servlet.ServletContextEvent
import com.simplesys.actors.AppConfig._

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    override val loadSchemas = com.simplesys.app.loadSchemas
    val initDataName = "InitData"
    val initDataWebName = "InitDataWeb"

    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        val ds: BoneCPDataSource = getString("dbPool.default") match {
            case x@"oracleMFMS" => cpStack OracleDataSource x
            case any => throw new RuntimeException(s"Bad: ${any}")
        }

        sce.ServletContext.Attribute("ds", Some(ds))

        try {
            ds.Connection.close()
            logger trace "ds checked"
            sce.ServletContext.Attribute("ds", Some(ds))
        }
        catch {
            case ex: SQLException => throw new RuntimeException(s"Not database conection ${ds.getUsername}")
            case ex: Throwable => throw ex
        }

        if (!getBoolean("app.onlyClientDebug")) {
            sce.ServletContext.Attribute(initDataName, Some(RtmProcessingApp.init()))
            sce.ServletContext.Attribute(initDataWebName, Some(RtmProcessingApp.init4Web()))
        }

        logger.trace(s"DriverClass: ${ds.getDriverClass}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {
        sce.ServletContext.Attribute(initDataName).foreach{case (x: InitData) ⇒ RtmProcessingApp destroy x}
        sce.ServletContext.Attribute(initDataWebName).foreach{case (x: InitDataWeb) ⇒ RtmProcessingApp destroyWeb x}
        cpStack.Close()
    }
}
