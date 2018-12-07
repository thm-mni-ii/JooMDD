package de.thm.icampus.mdd.model.oo

/**
 * Created by alexheinz1110 on 12.08.15.
 */
case class Folder(name: String, folders: List[Folder] = List(), structs: List[Struct] = List()) {}
