package de.thm.icampus.mdd.templates.extensions.library

import de.thm.icampus.mdd.model.extensions.LibraryExtension
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait LibraryTemplate extends BasicTemplate {

  def libraryPartial(datatype: LibraryExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }
}
