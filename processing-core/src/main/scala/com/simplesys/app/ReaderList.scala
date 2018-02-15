package com.simplesys.app

import com.simplesys.actors.AppConfig
import doobie.imports._

object ReaderList {
    case class ListItem(value: String)

    def getList(id: Int): IndexedSeq[String] = {
       val proc: Query0[ListItem] = sql"""SELECT
            SELEMENT_LIST
        FROM
            RTM_LISTELEMENTS
        WHERE
            IDID_LIST = $id
        ORDER BY
            SELEMENT_LIST ASC""".query[ListItem]

        val queue: List[ListItem] = proc.list.transact(AppConfig.dsLoggerTransactor).run
        queue.map(_.value).toIndexedSeq
    }

}
