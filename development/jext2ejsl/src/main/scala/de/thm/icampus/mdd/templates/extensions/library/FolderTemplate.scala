package de.thm.icampus.mdd.templates.extensions.library

import de.thm.icampus.mdd.model.oo.{Clazz, Folder}
import de.thm.icampus.mdd.templates.basic.BasicTemplate

/**
  * Created by tobias on 26.05.17.
  */
trait FolderTemplate extends BasicTemplate with ClazzTemplate {

  def folderPartial(folder: Folder, newline: Boolean = true, indent: Int = 0) : String = {
    val classesOpt = ?(folder.structs.nonEmpty,
      s"""
         |classes ${rep(folder.structs.filter(_.isInstanceOf[Clazz]).map(_.asInstanceOf[Clazz]), clazzPartial, sep="\n")}""""
    )

    val foldersOpt = ?(folder.folders.nonEmpty,
      s"""
         |packages ${rep(folder.folders, folderPartial,  sep="\n")}"""
    )

    toTemplate(
      s"""
         |Package ${folder.name} {
         |   $classesOpt
         |   $foldersOpt
         |}""", newline, indent)
  }

}
