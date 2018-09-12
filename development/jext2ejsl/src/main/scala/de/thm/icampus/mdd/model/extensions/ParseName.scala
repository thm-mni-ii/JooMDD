package de.thm.icampus.mdd.model.extensions

object ParseName {

  var excludeWords = List("search","size","version","min","max","type","description",
    "filter","filters","captcha","user","list","detail","details","component","event","events")

  def parse(name:String): String ={
    if(name.size==0)
      return ""
   var resul = name.trim
    if(resul.equals("filters")){
      resul = "^"+resul
    }

    if(excludeWords.contains(resul.toLowerCase.trim))
      resul = "^"+name.trim
    if(resul.contains(".") || resul.contains("(")|| resul.contains(":") || resul.contains(")")|| resul.contains("<")|| resul.contains(">")
      ||resul.charAt(0).toString.matches("[0-9]")||resul.matches("""[\\s\\t]""")||resul.contains("'")||resul.contains("'")){
     return resul.replaceAll("[.\\(\\)<:>[0-9]//\\s\\t,']","_unexc_").trim
    }
    return resul.trim
  }
  def parseValue(valString:String): String={
    var resul = valString.trim
    if(resul.contains("""\""")){
      resul = resul.replaceAll("""\\""","""\\\\""")
    }
    return resul
  }
}
