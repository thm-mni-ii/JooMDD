package de.thm.icampus.mdd.templates.extensions

import de.thm.icampus.mdd.model.extensions.PluginExtension
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait PluginTemplate extends BasicTemplate {

  def pluginPartial(datatype: PluginExtension, newline: Boolean = true, indent: Int = 0) = {
    toTemplate(
      s"""
         |Datatype
         |}""", newline, indent)
  }

}
