package com.simplesys.advisa

case class SimilarTerminalFindResult(
    sTerminal: String,
    sCategoryCode: Option[String],
    sCategoryName: Option[String],
    merchantName: Option[String],
    similarity: Double
) {
  override def toString: String =
    s"sTerminal: $sTerminal, sCategoryCode: ${sCategoryCode.getOrElse("None")}, sCategoryName: ${sCategoryName
      .getOrElse("None")}, merchantName: ${merchantName.getOrElse("None")}, similarity: $similarity"
}
