package de.thm.icampus.mdd.templates.extensions

import de.thm.icampus.mdd.model.extensions.ModuleExtension
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ModuleTemplate extends BasicTemplate {

  def modulePartial(datatype: ModuleExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }

}
