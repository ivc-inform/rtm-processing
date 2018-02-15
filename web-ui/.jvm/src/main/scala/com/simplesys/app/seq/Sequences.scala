package com.simplesys.app.seq

import java.math.{BigDecimal ⇒ JBigDecimal}

import akka.actor.{Actor, ActorSystem, Props}
import com.simplesys.akka.event.Logging
import com.simplesys.akka.pattern.AskSupport
import com.simplesys.config.Config
import com.simplesys.db.pool.PoolDataSource
import com.simplesys.jdbc.exception.NoDataFoundException
import com.simplesys.tuple.TupleSS2
import ru.simplesys.defs.bo.systemservice.SeqGeneratorBo

import scala.concurrent.{Await, Future}
import scalaz.{Failure, Success}

class IDGen(val nodeID: Int) extends Actor with Logging {
    def this() = this(0)

    val ids: scala.collection.mutable.Map[String, Long] = scala.collection.mutable.Map()

    protected def constructID(x: Long, y: Int): BigDecimal = {
        val t1 = new java.math.BigDecimal(y)
        val res: BigDecimal = t1.movePointLeft(t1.precision).add(new JBigDecimal(x))
        res
    }

    protected def constructID(x: Long): Long = x

    def receive = {
        case x: String => {
            val res = ids.get(x)
            val ret: Long = res match {
                case Some(y) => {
                    ids(x) += 1;
                    y + 1
                }
                case None => {
                    ids += ((x, 1));
                    1
                }
            }

            sender ! constructID(ret, nodeID)
        }
        case _ => logger.debug("received unknown message")
    }
}

object Sequences {
    def apply(oraclePool: PoolDataSource)(implicit actorSystem: ActorSystem) = new Sequences(oraclePool)
}

class Sequences(oraclePool: PoolDataSource)(implicit val actorSystem: ActorSystem) extends AskSupport with Config {
    private val nodeId = getInt(s"topology.node-id")

    val actorBigDecimalSeq = actorSystem.actorOf(Props(new IDSSequence(nodeId)))

    def nextBigDecimal(implicit triggerName: String): Future[BigDecimal] = {
        if (triggerName.isEmpty)
            throw new RuntimeException(s"TriggerName is Empty")

        (actorBigDecimalSeq ?(oraclePool, triggerName)).mapTo[BigDecimal]
    }

    def nextBigDecimal1(implicit triggerName: String): BigDecimal = {
        if (triggerName.isEmpty)
            throw new RuntimeException(s"TriggerName is Empty")

        Await.result(actorBigDecimalSeq ?(oraclePool, triggerName), timeout.duration).asInstanceOf[BigDecimal]
    }

    val actorLongSeq = actorSystem.actorOf(Props(new IDSeuenceGetLong(oraclePool)))

    def nextLong(triggerName: String): Future[Long] = {
        if (triggerName.isEmpty)
            throw new RuntimeException(s"TriggerName is Empty")

        (actorLongSeq ? triggerName).mapTo[Long]
    }

    def nextLong1(triggerName: String): Long = {
        if (triggerName.isEmpty)
            throw new RuntimeException(s"TriggerName is Empty")

        Await.result(actorLongSeq ? triggerName, timeout.duration).asInstanceOf[Long]
    }
}

class IDSeuenceGetLong(val oraclePool: PoolDataSource) extends IDGen {

    import com.simplesys.jdbc.control.classBO._

    override def receive = {

        case nameSeq: String =>
            val seqGenerator = SeqGeneratorBo(oraclePool)

            seqGenerator.selectPOne(where = Where(seqGenerator.nameSeq === nameSeq)).result match {
                case Success(trigger) =>
                    seqGenerator.update(TupleSS2(trigger.maxValue + 1, nameSeq), Where(seqGenerator.nameSeq === nameSeq)).result match {
                        case Success(_) =>
                            sender ! constructID(trigger.maxValue)
                        case Failure(e) =>
                            throw e
                    }
                case Failure(e) => e match {
                    case e: NoDataFoundException =>
                        seqGenerator.insert(TupleSS2(2, nameSeq)).result match {
                            case Success(_) =>
                                sender ! constructID(1)
                            case Failure(e) =>
                                throw e
                        }
                    case e: Throwable => throw e
                }
            }
        case x =>
            throw new RuntimeException(s"Received unknown message: ${x}")
    }
}

class IDSSequence(override val nodeID: Int) extends IDGen(nodeID) with Logging {

    import com.simplesys.jdbc.control.classBO._

    override def receive = {

        case (oraclePool: PoolDataSource, nameSeq: String) =>
            val seqGenerator = SeqGeneratorBo(oraclePool)

            seqGenerator.selectPOne(where = Where(seqGenerator.nameSeq === nameSeq)).result match {
                case Success(trigger) =>
                    seqGenerator.update(TupleSS2(trigger.maxValue + 1, nameSeq), Where(seqGenerator.nameSeq === nameSeq)).result match {
                        case Success(_) =>
                            sender ! constructID(trigger.maxValue, nodeID)
                        case Failure(e) =>
                            throw e
                    }
                case Failure(e) => e match {
                    case e: NoDataFoundException =>
                        seqGenerator.insert(TupleSS2(2, nameSeq)).result match {
                            case Success(_) =>
                                sender ! constructID(1, nodeID)
                            case Failure(e) =>
                                throw e
                        }
                    case e: Throwable => throw e
                }
            }
        case x =>
            logger warning s"Received unknown message: ${x}"
    }
}
