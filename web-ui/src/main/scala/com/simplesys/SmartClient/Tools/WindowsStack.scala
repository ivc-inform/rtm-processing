package com.simplesys.SmartClient.Tools

import com.simplesys.SmartClient.Foundation.Canvas
import com.simplesys.SmartClient.System.isc

import scala.collection.mutable

class WindowsStack {

    private val elementStack = mutable.Map.empty[String, Canvas]

    def addUniqueElement[T <: Canvas](canvas: T): T = {
        if (canvas.identifier.isEmpty)
            isc.error(s"Компонент ${canvas.getIdentifier()} не имеет постоянного identifier")

        elementStack.get(canvas.getIdentifier()) match {
            case None =>
                elementStack += (canvas.getIdentifier() -> canvas)
            case Some(elem) =>
                elem.markForDestroy()
                elementStack(canvas.getIdentifier()) = canvas
        }
        canvas
    }

    def log(): Unit = {
        println("/////////////////////// Windows Stack //////////////////////////////////")
        elementStack.foreach(item => println(item._1))
        println("/////////////////////// End windows Stack //////////////////////////////")
    }

    def getUniqueElement[T <: Canvas](name: String): Option[T] = {
        //log()

        elementStack.get(name) match {
            case None => None
            case Some(x) => Some(x.asInstanceOf[T])
        }
    }

    def destroy(name: String) {
        //log()

        elementStack.get(name) match {
            case None =>
            //println(s"Element: $name not found")
            case Some(canvas) =>
                elementStack -= name
                canvas.markForDestroy()
            //println(s"Element: $name found and destroyed")
        }
    }

    def destroyAll() {
        elementStack.foreach {
            case (key, canvas) =>
                elementStack -= canvas.getIdentifier()
                canvas.markForDestroy()
        }
    }
}
