package com.simplesys

import scalaz.NonEmptyList

package object scalaZ {
    implicit class SeqOpt[A](seq: Seq[A]) {
        def toNonEmptyList: NonEmptyList[A] =
            seq.toList match {
                case Nil ⇒
                    throw new RuntimeException(s"Список не должен быть пустым.")
                case x :: xs ⇒
                    NonEmptyList(x, xs: _*)
            }
    }
}
