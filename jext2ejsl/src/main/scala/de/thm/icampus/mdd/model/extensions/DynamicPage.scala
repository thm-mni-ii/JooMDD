package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.sql.Entity

abstract class DynamicPage extends Page{
  var modelPath: String = "";
  var viewPath: String = "";
  var viewLayoutPath: String="";
  def setEntityOb(entities:List[Entity])


}