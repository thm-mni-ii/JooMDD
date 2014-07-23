package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.CoreAccessLevel
import de.thm.icampus.cjsl.cjsl.UserGroup
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.accessLevelRight

class AccessGenerator   {
	
	 Application app
	 var String[] aktionCoreArray ;
	new(Application newapp) {
		app = newapp
	aktionCoreArray =#{"site_login","admin_login","offline_access","super_admin","access_administration_interface","create",	"delete","edit","edit_own"}
	}
	
	public def String nameOfCoreaccess(String name){
		
		switch (name){
			case "site_login":
			return "core.login.site"
			
			case "admin_login":
			return "core.login.admin"
			
			case"offline_access":
			return "core.login.offline"
			
			case"super_admin":
			return "core.admin"
			
			case"access_administration_interface":
			return "core.login.admin"
			
			case"create":
			return "core.create"
			
			case"delete":
			return"core.delete"
			
			case"edit":
			return"core.edit"
			
			case"edit_state":
			return"core.edit.state"
			
			case"edit_own":
			return"core.edit.own"
			
			case"manage":
			return"core.manage"
		}
		return "core.admin"
	}
	
	public def String[] getAktion(){
		return aktionCoreArray
	}
	
	public def int valueofCoreGroup(accessLevelRight value){
		
		if(value ==  null)
		return 2
		
		switch(value.toString){
			case"denied":
			return 0
			
			case"accepted":
			return 1
		}
		return 2
	}
	
	public def String getAcessForComUser(String name, EList<UserGroup>groupList, int startIndexOfGroups){
		var String result = new String() 
		if(name.equalsIgnoreCase("super_admin")){
		result = '''"7":1,'''
	
		}
	for (UserGroup group : groupList){
		if(group.globalAccess_level != null){
		var int value = valueOfCoreaccess(name, group.globalAccess_level) 
	     if(value!=2){
	     	var int index = groupList.indexOf(group) + startIndexOfGroups
	     	 result = result + '''"'''+  index.toString + '''"'''+ ":" + value +","
	     
	     }
	  }
	}
	if(!result.empty){
	result = result.substring(0,result.length-1)
	}

	return  result
	
	}
	
	public def int valueOfCoreaccess(String name, CoreAccessLevel core){
		
		switch (name){
			case "site_login":
			return valueofCoreGroup(core.site_login)
			
			case "admin_login":
			return valueofCoreGroup(core.admin_login)
			
			case "offline_access":
			return valueofCoreGroup(core.offline_access)
			
			case "super_admin":
			return valueofCoreGroup(core.super_admin)
			
			case "access_administration_interface":
			return valueofCoreGroup(core.access_administration_interface)
			 
			case "create":
			return valueofCoreGroup(core.create)
			
			case "delete":
			return valueofCoreGroup(core.delete)
			
			case "edit":
			return valueofCoreGroup(core.edit)
			
			case "edit_state":
			return valueofCoreGroup(core.edit_state)
			
			case "edit_own":
			return valueofCoreGroup(core.edit_own)
			
			
		}
		return valueofCoreGroup(core.super_admin)
	}
	
}