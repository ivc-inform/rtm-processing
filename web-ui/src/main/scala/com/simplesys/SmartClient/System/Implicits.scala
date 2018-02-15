package com.simplesys.SmartClient.System

import com.simplesys.System._
import com.simplesys.option.{ScOption, ScSome}

trait Implicits {
    implicit def anyToScOpt1(x: String) = ScSome(x)
    implicit def anyToScOpt2(x: Double) = ScSome(x)
    implicit def anyToScOpt3(x: Boolean) = ScSome(x)
    implicit def anyToScOpt4(x: Int) = ScSome(x)
    implicit def anyToScOpt5(x: String): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt6(x: Double): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt7(x: Boolean): ScOption[JSAny] = ScSome(x)
    implicit def anyToScOpt8(x: Int): ScOption[JSAny] = ScSome(x)

}
