package de.thm.icampus.mdd.templates.extensions

import de.thm.icampus.mdd.model.extensions._
import de.thm.icampus.mdd.templates.basic.BasicTemplate
import de.thm.icampus.mdd.templates.extensions.library.LibraryTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait ExtensionTemplate extends BasicTemplate with ComponentTemplate with ModuleTemplate with LibraryTemplate with PluginTemplate {

  def extensionPartial(extension: Extension, newline: Boolean = true, indent: Int = 0) = extension match {
    case component: ComponentExtension => componentPartial(component, newline, indent)
    case module: ModuleExtension => modulePartial(module, newline, indent)
    case library: LibraryExtension => libraryPartial(library, newline, indent)
    case plugin: PluginExtension => pluginPartial(plugin, newline, indent)
  }

}
