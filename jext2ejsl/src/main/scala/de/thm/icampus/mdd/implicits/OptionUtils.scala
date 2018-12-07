package de.thm.icampus.mdd.implicits


object OptionUtils {
  implicit class OptionSaveGet[A](val op: Option[A]) extends AnyVal {
    def ?[B](f1: A => B)(e: B): B = if (this.op.isDefined) f1(op.get) else e
  }

  implicit class OptionSaveDo[A](val op: Option[A]) extends  AnyVal {
    def ?=>(f1: A => Unit): Unit = {
      if (op.isDefined)
        f1(op.get)
    }
  }

  implicit class EmptyStringOption(val str: String) extends AnyVal {
    def asOpt: Option[String] = if (str.isEmpty) None else Option(str)
  }

}
