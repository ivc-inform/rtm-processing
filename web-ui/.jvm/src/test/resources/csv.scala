package com.simplesys.utilEval

new EvalInterface {
    override val name: String = "CSV"
    override def orderString (i: String): String = i.splitBy (",").mkString (",")
}
