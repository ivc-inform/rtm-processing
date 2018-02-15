package com.simplesys.utilEval

new EvalInterface {
  override val name:String="TSV"
  override def orderString(i: String): String = i.splitBy(",").mkString("\t")
}
