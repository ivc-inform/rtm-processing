package com.simplesys.listener

import java.io.File
import java.sql.SQLException
import javax.servlet.annotation.WebListener

import com.simplesys.file.Path
import com.simplesys.hikari.OracleHikariDataSource
//import com.simplesys.oracle.pool.OraclePoolDataSource
import com.simplesys.servlet.ServletContextEvent

object AppLifeCycleEvent {
    val oraclePoolAttributeName = "oraclePool"
}

@WebListener
class AppLifeCycleEvent extends CommonWebAppListener {

    import AppLifeCycleEvent._

    override val loadSchemas = com.simplesys.appCommon.common.loadSchemas
    override def UserContextInitialized(sce: ServletContextEvent) {

        com.simplesys.messages.ActorConfig.initSingletonActors(system)

        logger trace s"================================= dbPool.default -> ${sys.env.get("dbPool.default")} =================================================="

        val temp = Path(new File("./temp"))
        temp.deleteRecursively(true)
        temp.createDirectory()

        val dbPoolDefault = sys.env.get("dbPool.default").getOrElse(config.getString("dbPool.default"))

        logger trace s"dbPoolDefault: $dbPoolDefault"

        val oraclePool = new OracleHikariDataSource(s"$dbPoolDefault.oraclcePoolDataSource")
        //val oraclePool = new OraclePoolDataSource(s"$dbPoolDefault.oraclcePoolDataSource")

        try {
            //oraclePool.getConnection().close()
            logger trace s"$oraclePoolAttributeName checked"
            sce.ServletContext.Attribute(oraclePoolAttributeName, Some(oraclePool))
        }
        catch {
            case ex: SQLException =>
                throw ex
            case ex: Throwable =>
                throw ex
        }

        logger.trace(s"DriverClass: ${oraclePool.settings.className}")

        super.UserContextInitialized(sce)
    }


    override def ContextDestroyed1(sce: ServletContextEvent) {

    }
}
