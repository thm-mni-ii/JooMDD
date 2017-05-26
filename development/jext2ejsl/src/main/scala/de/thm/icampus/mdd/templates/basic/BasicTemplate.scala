package de.thm.icampus.mdd.templates.basic

/**
  * Created by tobias on 26.05.17.
  */
trait BasicTemplate {

  implicit def toIterator[T](iterable: Iterable[T]) :Iterator[T] = iterable.toIterator

  def rep[T](elems: Iterator[T], elemToString: (T, Boolean, Int) => String, indent: Int = 1, sep: String =",\n") : String = {
    if(elems.isEmpty) "{}"
    else {
      val begin = "{"
      val content = elems.map(elemToString(_, true, indent + 1)).mkString(sep)
      val end = tabs(indent) + "}"
      begin + "\n" + content + "\n" + end
    }
  }

  def tabs(amount : Int) : String = {
    (0 until amount).map(_ => "    ").mkString
  }

  private val DELETE = "xXxXx"

  def ?(cond: Boolean, out: => String): String = {
    if(cond) out.stripMargin.drop(1) else DELETE
  }

  def toTemplate(input: String, newline: Boolean = true, indent: Int) : String = {
    if(newline) {
      input.stripMargin.lines.drop(1).filter(!_.contains(DELETE)).map(tabs(indent) + _).mkString("\n")
    } else {
      val all = input.stripMargin.lines.drop(1).filter(!_.contains(DELETE))
      val first =
        if(indent > 0) tabs(indent - 1) + all.take(1).next
        else tabs(indent) + all.take(1).next
      first + "\n" + all.map(tabs(indent) + _).mkString("\n")
    }
  }
}
