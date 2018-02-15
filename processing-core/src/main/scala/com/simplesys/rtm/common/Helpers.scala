package com.simplesys.rtm.common

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import ru.simplesys.dmprocess.dictionary.MixedDictMutable
import ru.simplesys.dmprocess.templates.WordSwissTool
import ru.simplesys.dmprocess.text.grammar.ParboiledTokenizer
import ru.simplesys.dmprocess.text.transliteration.ImmTransTable
import ru.simplesys.dmprocess.text.{ParserContextVal, TokenIs}

import scalaz.Validation

object Helpers {
  def transform[Input, Output](orig: Input,
                               text: String,
                               refDate: LocalDateTime)(
      f: (Input,
          Validation[Throwable, IndexedSeq[TokenIs[_]]]) => Output): Output = {
    val ctx = ParserContextVal(new LocalDateTime(refDate))
    val parsedText = ParboiledTokenizer.parse(text, ctx)
    f(orig, parsedText)
  }

  def getSwissTool: WordSwissTool = {
    val dict = new MixedDictMutable(mutableRus = List.empty,
                                    mutableEng = List.empty,
                                    mutUnkLang = List.empty).getSnapshot
    val transTable = ImmTransTable(None)
    val swissTool = WordSwissTool(dict, transTable)
    swissTool
  }

  val jodaFormatter = DateTimeFormat.forPattern("yyyy.MM.dd HH.mm.ss")

}
