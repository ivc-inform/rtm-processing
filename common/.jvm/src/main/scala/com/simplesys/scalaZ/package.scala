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

    implicit class OptionOpt[A](item: Option[A]) {
        def toNonEmptyList: NonEmptyList[A] =
            item match {
                case None ⇒
                    throw new RuntimeException(s"Список не должен быть пустым.")
                case Some(x) ⇒
                    NonEmptyList(x)
            }
    }
}
