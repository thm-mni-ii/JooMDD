package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.DefaultEditor
import de.thm.icampus.cjsl.cjsl.DefaultLanguage
import de.thm.icampus.cjsl.cjsl.DefaultViewLevelReference
import de.thm.icampus.cjsl.cjsl.EditorReference
import de.thm.icampus.cjsl.cjsl.EditorType
import de.thm.icampus.cjsl.cjsl.LanguageReference
import de.thm.icampus.cjsl.cjsl.LanguageType
import de.thm.icampus.cjsl.cjsl.LanguageTypeWithAll
import de.thm.icampus.cjsl.cjsl.Template
import de.thm.icampus.cjsl.cjsl.TimeZone
import de.thm.icampus.cjsl.cjsl.UserGroup
import de.thm.icampus.cjsl.cjsl.ViewLevel
import de.thm.icampus.cjsl.cjsl.ViewLevelRights
import java.io.File
import java.util.Calendar
import java.util.Random
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * This class Content many Function to generate Code
 */
abstract class ApplicationLibrary {
	
var TreeStructur uti = new TreeStructur


	
/**
 * to show, if a value is empty
 * 
 * @param String paramsValue
 * @param String defaultval
 * 
 * @return String 
 */
	def String isEmpty(String paramsValue, String defaultval){
		
		if(paramsValue == null || paramsValue.empty)
		return defaultval
		
		return paramsValue
		
	}
	
	/**
 * to show, if a value is empty
 * 
 * @param int paramsValue
 * @param int defaultval
 * 
 * @return Integer 
 */
	def int isEmpty(int paramsValue, int defaultval){
		
		if(paramsValue == 0)
		return defaultval
		
		return paramsValue
		
	}
	
	/**
 * to show, if a value is empty
 * 
 * @param String paramsValue
 * 
 * 
 * @return String 
 */
	def String  isEmpty(String paramsValue){
		
		if(paramsValue == null)
		return "0"
		
		return "1"
		
	}
	
/**
 * search the id of a Template in List of Template
 * 
 * @param Template temp
 * @param  EList listTemplate
 * 
 * @return Integer 
 */
def  int getTemplateid(Template temp, EList<Template> listTemplate){
	// TODO Auto-generated method stub
	if(listTemplate == null || temp == null)
	return 0
	var int id =0
	for(Template t: listTemplate){
		if(temp.name.equals(t.name)){
			return id
		}
		id= id +1
	}
	return 0
}

 /**
 * retrun the Value of a Params for save in the database
 * 
 * @param String value
 * 
 * @return String 
 */
	def String valueParser(String value){
		
		switch(value.toLowerCase){
			case "yes" :
				  return  1 +""
			case "custom":
				  return  1 + ""
			case "hide":
				return 0 +""
			case "no":
			      return 0 +""
//			case "custom":
//			     return 2 +""
			     
			 case "after":
			      return 2 + ""
			      
			 case "before":
			      return 1 + ""
			     
			case "index, follow":
				return " "
				
			case "index, no follow":
				return value.toLowerCase
				
			case "no index, follow":
				return value.toLowerCase
				
			case "no index, no follow":
				return value.toLowerCase
				
			case "off-caching disabled":
				 return 0+""
				 
			case "on-conservative caching":
				return 1+""
				
			case "on-progressive caching":
					return 2 +""
			case "file":
			   return  value.toLowerCase
			   
			case "cache_lite":
					return "cachelite"
			case "system default":
					return "default"
			case "none":
				 return "none"
				 
			case "simple":
				return "simple"
				
			case "maximum":
				 return "maximum"
				 
			case "development":
				 return "development"
				 
			case"php mail":
				return "mail"
				
			case"sendmail":
				return "sendmail"
			
			case "smtp":
					return "smtp"
			case "inherited":
					return " "
			case "accepted":
		  		return 1 +""
		   case "denied":
		      return 0 +""
		}
		return 0 +""
	}
	/**
 * retrun the Value of a Params for save in the database
 * 
 * @param String value
 * 
 * @return int 
 */
	def int valueParserint(String value){
		
		switch(value.toLowerCase){
			case "none" :
				  return  0
			case "administrator only":
				  return 1
			case "entire site":
				 return 2
					
				  }
				  
		return 0
	}
	
	/**
 * search the Type of a EditorType and return the name
 * 
 * @param EditorType editTyp
 * 
 * @return String 
 */
	def String selectedEdit(EditorType editTyp){
	
	if(editTyp == null)
	 return "tinymce"
		
		if(editTyp.eClass.name.equals("EditorReference")){
			
			var EditorReference te =  editTyp as EditorReference
			return te.reference.name.toLowerCase
		}
		if(editTyp.eClass.name.equals("DefaultEditorReference")){
			
			var DefaultEditor te =  editTyp as DefaultEditor
			return te.reference.getName().toLowerCase
		}	
		
		return "tinymce"
	
	}
	
	/**
 * search the Type of a ViewLevelRights and return the name
 * 
 * @param ViewLevelRights siteacessTyp
 * @params EList  config
 * 
 * @return String 
 */
	def String selectedSiteacess(ViewLevelRights siteacessTyp, EList<ViewLevel> config){
	
	if(siteacessTyp == null)
	 	return "1"
		
		if(siteacessTyp.eClass.name.equals("ViewLevelReference")){
			var int count = 4
			var ViewLevel ref =  siteacessTyp as ViewLevel
			for(acess: config ){
				if(acess.equals(ref)){
					return count + ""
				}
			count = count + 1
			}
			
		}
		if(siteacessTyp.eClass.name.equals("DefaultViewLevelReference")){
			
			
			var DefaultViewLevelReference te =  siteacessTyp as DefaultViewLevelReference
			
		switch(te.ref.getName()){
			case "public":
						return 1 +""
			case "registered":
						return 2 + ""
			case "special":
						return 3 + ""
			}
		}	
		
		return "1"
		
	}
	
 public def String selectedLanguageAll(LanguageTypeWithAll lang){
 	
 	if(lang == null)
 	return "*"
 	
 	if(lang.eClass.name.equals("LanguageType")){
 		var LanguageType l = lang as LanguageType
 	  return selectedLanguage(l)
 	  
 	  }
 	  
 	  return "*"
 }
	
/**
 * search the Type of a LanguageType and return the name
 * 
 * @param LanguageType lang
 * 
 * @return String 
 */
	
	def String selectedLanguage(LanguageType lang ){
		if(lang == null)
		return " "
		
		if(lang.eClass.name.equalsIgnoreCase("LanguageTypeReference")){
			
			var LanguageReference refLang = lang as LanguageReference
			if( refLang.eClass.name.equalsIgnoreCase("LanguageReference")){
				var LanguageReference newlang = refLang as LanguageReference
				return newlang.reference.name
			}
			
			if( refLang.eClass.name.equalsIgnoreCase("DefaultLanguageReference")){
				var DefaultLanguage newlang = refLang as DefaultLanguage
				return newlang.reference.name()
			}
		}
		return " "
	}
	

	    
/**
 *  to delete a File
 * 
 * @param File archive
 * @param File destDir
 * 
 */
	    
public def  deleteFolder(File folder) {
   var  File[] files = folder.listFiles();
    if(files!=null) { 
        for(File f: files) {
            if(f.isDirectory()) {
                deleteFolder(f);
            } else {
                f.delete();
            }
        }
    }
    folder.delete();
}
 
/**
 *  return the aktually Date and the Time
 * 
 * return String
 */
public def String searchDateTime(){
	
	return searchDate() + " " + searchTime();
}

/**
 *  return the aktually Date
 * 
 * return String
 */
public def String searchDate(){
	var Calendar cal = Calendar.getInstance();
	var int m = cal.get(Calendar.MONTH)+1;
	var String monat;
	if(m < 10){
		monat = "0"+ m;
		}
	else{ monat = (m +1) +"";}
	
	m= cal.get(Calendar.DATE) ;
	var String date;
	if(m<10){
		date = "0" + cal.get(Calendar.DATE);
	}
	else{
		date = cal.get(Calendar.DATE) + "";
	}
	var	 String result = cal.get(Calendar.YEAR) + "-" + monat + "-" + date;
	
	return result;
}

/**
 *  return the aktually the Time
 * 
 * return String
 */
public def String searchTime(){
	var Calendar cal = Calendar.getInstance();
	var int i =  cal.get(Calendar.HOUR_OF_DAY);
	var String std;
	var String  min;
	var String  sec;
	
	if(i<10){
		std = "0" + cal.get(Calendar.HOUR_OF_DAY);
	}
	else{
		std=  cal.get(Calendar.HOUR_OF_DAY)+"";
	}
	i = cal.get(Calendar.MINUTE);
	
	if(i<10){
		min = "0"+cal.get(Calendar.MINUTE);
	}
	else{
		min = cal.get(Calendar.MINUTE)+"";
	}
	 i=cal.get(Calendar.SECOND);
	 
	 if(i<10){
	 	sec = "0"+cal.get(Calendar.SECOND);
	 }
	 else{
	 	sec = cal.get(Calendar.SECOND)+"";
	 }
	var	 String date = std+ ":" +min+ ":" +sec;
	return date;
}

/**
 *  Generate a default Password
 * 
 * return String
 */

public def  String genaratePass(){
		var String result = new String() ;
		var Random ran = new Random();
		var String letter = "abcdefghijklmnopqrstuvwxyz0123456789";
		var char[] arr = letter.toCharArray;
		
		for(Integer i: 1..8){
			var int in = ran.nextInt(34);
			result =  arr.get(in) + result;
			
		
		}
		
		return result;
	}
	
/**
 *  search attribut for a Type, it can to be regulary extended
 * the actually Object are Timezone and usergroup
 * 
 * @param EObject element
 * @param String attribut
 * 
 * return String
 */

 def  String searchattribut (EObject element, String attribut){
	
	
	if(element==null)
	return new String;
	
	if (element.eClass.name.toString.toLowerCase.equals("timezone")) {
	 	var TimeZone elem = element as TimeZone;
		
			switch (attribut) {
			case "country":
			  return elem.country
			case "continent":
				return elem.continent
			default  :
				return elem.getName()
				
			}	
		
	}
	if (element.eClass.name.toString.toLowerCase.equals("usergroup")) {
	 	var UserGroup elem = element as UserGroup;
		
			switch (attribut) {
			case "name":
			  return elem.name
			default  :
				return " "
				
			}	
		
	}
	return null;
}

/**
 *  calculate the the value for the topology 
 * it can to be for many object extended 
 * the actually object are Usergroup
 * 
 * @param EList<? extends EObject> artefact
 * 
 * 
 * return EList
 */
public def  EList<TreeElement> transformArtefact(EList<? extends EObject> artefact){
	
	if(artefact == null)
	 return null
	 
	 var EList<TreeElement> result = new BasicEList<TreeElement>()
	 
	 if( artefact.get(0).eClass.name.toString.toLowerCase.equals("usergroup")){
	   var EList<UserGroup> groups = artefact as EList<UserGroup>
	   
		 for(UserGroup g: groups){
		 	if(g.parent != null){
	 	   result.addAll(new TreeElement(groups.indexOf(g),groups.indexOf(g.parent)))
	 	   
	 	   }else{
	 	   	 result.addAll(new TreeElement(groups.indexOf(g),-1))
	 	   	
	 	   }
		 }
		 }
		 var int max = orderGroup(result);
		 var TreeElement maxrgt = new TreeElement(-1,max);
		 result.add(maxrgt);
	 return  result ;
}

/**
 *  calculate for a parent the max right
 * @param EList  groups
 * 
 * 
 * return int
 */
  public def int orderGroup(EList<TreeElement> groups) {
	
	var int maxrgt = 16
	
	for(TreeElement g: groups){
		if(g.parent == -1){
			
			g.setLft(maxrgt)
			maxrgt = uti.buildthegroups(groups, g, uti.searchAllkids(groups, g), maxrgt+1 ,0) 
			g.setRgt(maxrgt);
			maxrgt = maxrgt +1
		
		}
	}
   
   return maxrgt
  
}

/**
 * get the TreeElement at a Index
 * @param EList list
 * @param int id
 * 
 * return TreeElement
 */
public def TreeElement searchElem(EList<TreeElement> list, int id){
	var TreeElement result = new TreeElement()
	for(TreeElement b : list){
		if(b.id == id)
		result = b
	}
	return result
}
 
 /**
 * get the Index of a Object in a List and add the startvalue
 * 
 * @param EObject e
 * @param EList<?> list
 * @param int startValue
 * @param  int defaultValue
 * 
 * return int
 */
 public def int indexOf(EObject e, EList<?> list, int startValue, int defaultValue){
 	if(e == null)
 	return defaultValue
 	
 	if(list.indexOf(e) == -1)
 	return defaultValue
 	
 	return list.indexOf(e)+startValue
 }
 
 

	
}