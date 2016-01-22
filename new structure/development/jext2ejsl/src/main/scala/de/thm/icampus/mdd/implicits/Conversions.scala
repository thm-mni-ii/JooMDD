package de.thm.icampus.mdd.implicits

import java.nio.file.{Paths, Path}

/**
 * Created by andrej on 13/08/15.
 */
object Conversions {
  implicit def pathToUri(p: Path): java.net.URI = p.toUri

  implicit class PathUtil(val p: Path) extends AnyVal {
    def + (e: String): Path = if (e.trim.isEmpty || e.trim.equals("/")) p else Paths.get(s"${p.toString}/$e")
  }

  implicit class StringEQ(val s1: String) extends AnyVal {
    def == (s2: String): Boolean = s1.equals(s2)
  }
}
