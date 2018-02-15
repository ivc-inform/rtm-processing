package com.simplesys.rtm.common

import java.time.LocalDateTime

import ru.mfms.mfmd.integration.tokenizer.TokenIs
import ru.simplesys.dmprocess.dictionary.MixedDictMutable
import ru.simplesys.dmprocess.templates.WordSwissTool
import ru.simplesys.dmprocess.text.ParserContextVal
import ru.simplesys.dmprocess.text.grammar.ParboiledTokenizer
import ru.simplesys.dmprocess.text.transliteration.ImmTransTable

object Helpers {
  def transform[Input, Output](orig: Input,
                               text: String,
                               refDate: LocalDateTime)(
      f: (Input, Either[Throwable, IndexedSeq[TokenIs[_]]]) => Output): Output = {
    val ctx = ParserContextVal(refDate)
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

  val jodaFormatter = LocalDateTime.parse("yyyy.MM.dd HH.mm.ss")

}
