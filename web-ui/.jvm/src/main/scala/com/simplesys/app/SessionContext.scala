package com.simplesys.app

import com.simplesys.common.Strings._
import com.simplesys.db.pool.PoolDataSource
import com.simplesys.listener.AppLifeCycleEvent
import com.simplesys.log.Logging
import com.simplesys.servlet.ServletContext
import com.simplesys.servlet.http.HttpSession
import com.simplesys.servlet.isc.ServletActor

trait SessionContextSupport {
    this: ServletActor =>

    lazy val sessionContext = new SessionContext(request.Session)

    def getLoginedUser = sessionContext.getLoginedUser
    def getCaptionUser = sessionContext.getCaptionUser
    def getUserId = sessionContext.getUserId

    implicit val oraclePool: PoolDataSource = sessionContext.getOraclePool //Не убирать !!!
}

object SessionContext {
    val loggedAttributeName = "logged"
    val userIdAttributeName = "userId"
    val loginedUserAttributeName = "loginedUser"
    val captionUserAttributeName = "captionUser"
    val loginedGroupAttributeName = "loginedGroup"
}

class SessionContext(protected val session: Option[HttpSession]) extends Logging {

    import AppLifeCycleEvent._
    import SessionContext._

    private[this] var logged = false
    def getLoged = logged

    private[this] var userId: BigDecimal = 0.0
    def getUserId = userId

    private[this] var loginedUser = strEmpty
    def getLoginedUser = loginedUser

    private[this] var captionUser = strEmpty
    def getCaptionUser = captionUser

    private[this] var loginedGroup = strEmpty
    def getLoginedGroup = loginedGroup

    private[this] var oraclePool: PoolDataSource = null
    def getOraclePool = oraclePool

    def getSQLDialect: com.simplesys.sql.SQLDialect = oraclePool.sqlDialect

    /*private[this] var birtEngine: IReportEngine = null
    def getBirtEngine = birtEngine*/

    private[this] var servletContext: ServletContext = null
    //def getServletContext = servletContext

    for (_session <- session) {
        _session.LogSession
        servletContext = _session.ServletContext

        logged = _session.Attribute(loggedAttributeName) match {
            case Some(value: Boolean) => value
            case _ => false
        }

        userId = _session.Attribute(userIdAttributeName) match {
            case Some(value: BigDecimal) => value
            case _ => 0.0
        }

        loginedUser = _session.Attribute(loginedUserAttributeName) match {
            case Some(str: String) => str
            case _ => strEmpty
        }

        captionUser = _session.Attribute(captionUserAttributeName) match {
            case Some(str: String) => str
            case _ => strEmpty
        }

        loginedGroup = _session.Attribute(loginedGroupAttributeName) match {
            case Some(str: String) => str
            case _ => strEmpty
        }

        oraclePool = servletContext.Attribute(oraclePoolAttributeName) match {
            case Some(value: PoolDataSource) => value
            case _ => throw new RuntimeException(s"Не найден $oraclePoolAttributeName")
        }
    }

    def Invalidate() {
        for (_session <- session)
            _session.Invalidate()
    }

    def sessionIsValidate: Boolean = {
        val res = getLoginedUser != strEmpty
        logger.trace(s"SessionIsValidate: ${res}")
        res
    }
}
