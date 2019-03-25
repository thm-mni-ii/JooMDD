package de.thm.icampus.mdd.generator.extensions.library

import de.thm.icampus.mdd.model.extensions.LibraryExtension
import de.thm.icampus.mdd.model.oo.Clazz
import de.thm.icampus.mdd.generator.basic.BasicTemplate
import de.thm.icampus.mdd.generator.extensions.ManifestTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait LibraryTemplate extends BasicTemplate with ManifestTemplate with ClazzTemplate with FolderTemplate {

  def libraryPartial(lib: LibraryExtension, newline: Boolean = true, indent: Int = 0) : String = {
    val classesOpt = ?(lib.folder.structs.nonEmpty,
      s"""
         |classes ${rep(lib.folder.structs.filter(_.isInstanceOf[Clazz]).map(_.asInstanceOf[Clazz]), clazzPartial, sep="\n")}""""
    )

    val foldersOpt = ?(lib.folder.folders.nonEmpty,
      s"""
         |packages ${rep(lib.folder.folders, folderPartial, sep="\n")}"""
    )

    toTemplate(
      s"""
         |Library ${lib.name} {
         |    ${manifestPartial(lib.manifest, newline = false, indent = 1)}
         |    $classesOpt
         |    $foldersOpt
         |}""", newline, indent)
  }

}
