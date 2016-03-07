package de.thm.icampus.mdd.model.extensions


case class JEntity(name: String, attributes: List[Attribute])

object SQL_HTMLTypeMap {
  def apply(sqlType: String): String = sqlType.toLowerCase match {
    case text: String if text.contains("int") ⇒ "int"
    case text: String if text.contains("bit") || text.contains("TINYINT(1)") ⇒ "int"
    case text: String if text.contains("date") || text.contains("time") ⇒ "text"
    case _: String ⇒ "text"
  }
}

object Attribute {
  def apply(name: String, dbtype: String, isPrimary: Boolean): Attribute = new Attribute(name, dbtype, isPrimary)
}

case class Attribute(name: String, var dbtype: String, var htmltype: String, var isPrimary: Boolean) {
  def this(name: String, dbtype: String, isPrimary: Boolean) = this(name, dbtype, SQL_HTMLTypeMap(dbtype), isPrimary)
}
