package com.simplesys.components

import com.simplesys.appCommon.common._

import scala.scalajs.js

abstract class Common4Singltones extends js.Object {
    val unknown = new Unknown
    val statuses: Seq[StatusScenario]

    def getStatus(value: js.UndefOr[Double]): StatusScenario =
        if (value.isEmpty)
            unknown
        else
            statuses.find(_.value == value.get) match {
                case None ⇒ unknown
                case Some(status) ⇒ status
            }                                                                     

    def getStatus(value: Int): StatusScenario =
        statuses.find(_.value == value) match {
            case None ⇒ unknown
            case Some(status) ⇒ status
        }
}

object StatusScenario extends Common4Singltones {

    val notReady = new NotReady(false)
    val ready = new Ready(false)
    val play = new Play(false)
    val notPlay = new NotPlay(false)
    val stoped = new Stoped(false)
    val stoping = new Stoping(false)

    val readyTest = new Ready(true)
    val readyTestAsProd = new Ready(true, true)
    val playTest = new Play(true)
    val playTestAsProd = new Play(true, true)
    val notPlayTest = new NotPlay(true)
    val notPlayTestAsProd = new NotPlay(true, true)
    val stopedTest = new Stoped(true)
    val stopedTestAsProd = new Stoped(true, true)
    val stopingTest = new Stoping(true)
    val stopingTestAsProd = new Stoping(true, true)

    val statuses: Seq[StatusScenario] = Seq(unknown, notReady, ready, play, notPlay, stoped, stoping, readyTest, playTest, notPlayTest, stopedTest, stopingTest, readyTestAsProd, playTestAsProd, notPlayTestAsProd, stopedTestAsProd, stopingTestAsProd)

    def enable2Prepare(value: js.UndefOr[Double]): Boolean = {
        if (value.isEmpty || value.get == unknown.value)
            false
        else
            Seq(notReady, ready, readyTest, readyTestAsProd, stoped, stopedTest, stopedTestAsProd).map(_.value).contains(value)
    }

    def isTest(value: Double): Boolean =
        Seq(readyTest, playTest, notPlayTest, stopedTest, stopingTest).map(_.value).contains(value)

    def enable2Start(value: js.UndefOr[Double]): Boolean = {
        if (value.isEmpty || value.get == unknown.value)
            false
        else
            Seq(ready, readyTest, readyTestAsProd, stoped, stopedTest, stopedTestAsProd, stoping, stopingTest, stopedTestAsProd).map(_.value).contains(value)
    }

    def enable2Stop(value: js.UndefOr[Double]): Boolean = {
        if (value.isEmpty || value.get == unknown.value)
            false
        else
            Seq(play, playTest, playTestAsProd, notPlay, notPlayTest, notPlayTestAsProd).map(_.value).contains(value)
    }

    def enable2SoftStop(value: js.UndefOr[Double]): Boolean = {
        if (value.isEmpty || value.get == unknown.value)
            false
        else
            Seq(play, playTest, playTestAsProd).map(_.value).contains(value)
    }
}

object StatusTestScenario extends Common4Singltones {
    val play = new Play(false)
    val playAsProd = new Play(false, true)
    val stoped = new Stoped(false)

    val statuses = Seq(unknown, play, stoped)

    def enable2Start(values: Seq[js.UndefOr[Double]]): Boolean = {
        val _values = values.filter(_.isDefined).map(_.get)
        if (values.length != _values.length)
            false
        else
            _values.forall(Seq(stoped.value).contains(_))
    }

    def enable2Stop(values: Seq[js.UndefOr[Double]]): Boolean = {
        val _values = values.filter(_.isDefined).map(_.get)
        if (values.length != _values.length)
            false
        else
            _values.forall(Seq(play.value, playAsProd.value).contains(_))
    }
}

abstract class StatusScenario extends js.Object {
    val value: Int
    val title: String
    val prompt: String
    val icon: String
    val mode: String              
    val test: Boolean
    val prod: Boolean
    protected def makePrompt(prompt: String): String = {
        val res = s"$prompt${if (test) " - тест" else ""}"
        s"${if (prod) res + " (Prod)" else res}"
    }
    protected def makeTitle(title: String): String = {
        val res = s"$title${if (test) " - тест" else ""}"
        s"${if (prod) res + " (Prod)" else res}"
    }
    protected def makeValue(value: Int): Int = {
        val res = if (test) value + 10 else value
        if (prod) res + 10 else res
    }
}

class Unknown extends StatusScenario {
    override val value: Int = -1
    override val title: String = "не определен"
    override val prompt: String = "Не определен"
    override val icon: String = status
    override val mode: String = "unknown"
    override val test: Boolean = false
    override val prod: Boolean = false
}

class NotReady(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(0)
    override val title: String = makeTitle("не готов")
    override val prompt: String = makePrompt("Не готов, необходимо выполнить операцию подготовки")
    override val icon: String = notready
    override val mode: String = ""
}

class Ready(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(1)
    override val title: String = makeTitle("готов")
    override val prompt: String = makePrompt("Готов, можно запускать")
    override val icon: String = ready
    override val mode: String = "prepare"
}

class Play(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(2)
    override val title: String = makeTitle("выполняется")
    override val prompt: String = makePrompt("Сенарий выполняется, и имеет вхождения")
    override val icon: String = start
    override val mode: String = "play"
}

class NotPlay(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(3)
    override val title: String = makeTitle("простаивает")
    override val prompt: String = makePrompt("Сенарий запущен, но не имеет вхождений")
    override val icon: String = load
    override val mode: String = ""
}

class Stoped(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(4)
    override val title: String = makeTitle("остановлен")
    override val prompt: String = makePrompt("Сенарий остановлен полностью")
    override val icon: String = softStop
    override val mode: String = "stoped"
}

class Stoping(val test: Boolean, val prod: Boolean = false) extends StatusScenario {
    override val value: Int = makeValue(5)
    override val title: String = makeTitle("останавливается")
    override val prompt: String = makePrompt("Сенарий остановлен, но имеет вхождения")
    override val icon: String = stoped
    override val mode: String = "stoping"
}
