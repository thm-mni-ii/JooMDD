package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.User
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Configuration
import de.thm.icampus.cjsl.cjsl.UserGroup
import de.thm.icampus.cjsl.cjsl.ViewLevel
import java.util.logging.Handler
import java.util.HashMap

class UserGenerator extends ApplicationLibrary {
	/**
	 * Index of User begin of 200
	 * 
	 * Index of Group begin of 9
	 */
	
	EList<User> allusers
	cJSL_Configuration conf
	Application app
	EList<UserGroup> groups
	EList<ViewLevel> views
	 AccessGenerator access 
  int usersStartid
  int groupsStartid
  int viewLevelid
new( Application apps, int userStartindex, int groupStartindex, int viewLevelMaxindex) {
		
		app = apps
		allusers = apps.cjsl_user.user
		conf= apps.cjsl_configuration
		groups = apps.cjsl_user.usergroups
		views= apps.cjsl_user.viewlevel
		access = new AccessGenerator(app);
		allusers.add(0,conf.website_conf.superadmin)
		usersStartid = userStartindex
		groupsStartid = groupStartindex
		viewLevelid = viewLevelMaxindex
		
		
	}
	 
	

  public def CharSequence generateAllUser ()'''
  INSERT INTO `#__users` (`id`, `name`, `username`, `email`, `password`, `usertype`, `block`, `sendEmail`, `registerDate`, `lastvisitDate`, `activation`, `params`, `lastResetTime`, `resetCount`) VALUES
  «FOR user : allusers»
  «IF(user.equals(allusers.get(allusers.size-1)))»
  (« indexOf(user,allusers,usersStartid,0)»,'«user.fullname»', '«user.name»', '«user.email»', '«genaratePass»', ' ', '«valueParser(isEmpty(user.blocked,'no'))»', '«valueParser(isEmpty(user.receiveSystemMail,'no'))
  	»', '«searchDateTime()»', '0000-00-00 00:00:00','0','{"admin_style":"«getTemplateid(user.backendTemplateStyle, conf.template)»","admin_language":"«selectedLanguage(user.backendLanguage)»","language":"«selectedLanguage(user.frontendLanguage)»","editor":"«selectedEdit(user.editor)»","helpsite":"http:\/\/help.joomla.org\/proxy\/index.php?option=com_help&keyref=Help{major}{minor}:{keyref}","timezone":"«isEmpty(searchattribut(user.time_zone,"continent"),"de")»"\/"«isEmpty(searchattribut(user.time_zone,"country"),"DE")»"}','0000-00-00 00:00:00','0');
  «ELSE»
   («indexOf(user,allusers,usersStartid,0)»,'«user.fullname»', '«user.name»', '«user.email»', '«genaratePass»', ' ', '«valueParser(isEmpty(user.blocked,'no'))»', '«valueParser(isEmpty(user.receiveSystemMail,'no'))
  	»', '«searchDateTime()»', '0000-00-00 00:00:00','0','{"admin_style":"«getTemplateid(user.backendTemplateStyle, conf.template)»","admin_language":"«selectedLanguage(user.backendLanguage)»","language":"«selectedLanguage(user.frontendLanguage)»","editor":"«selectedEdit(user.editor)»","helpsite":"http:\/\/help.joomla.org\/proxy\/index.php?option=com_help&keyref=Help{major}{minor}:{keyref}","timezone":"«isEmpty(searchattribut(user.time_zone,"continent"),"de")»"\/"«isEmpty(searchattribut(user.time_zone,"country"),"DE")»"}','0000-00-00 00:00:00','0'),
  «ENDIF»
  «ENDFOR»'''
  
  public def CharSequence generateUserProfile()'''
  
  INSERT INTO `#__user_profiles` (`user_id`, `profile_key`, `profile_value`, `ordering`) VALUES
   «FOR user : allusers»
    «var int counter =0»
    «IF (user.userProfile != null)»
   «FOR attribut: user.userProfile.userAttribute»
   «IF(attribut.equals(allusers.get(allusers.size-1).userProfile.userAttribute.get(allusers.get(allusers.size-1).userProfile.userAttribute.size -1)))»
    ('«indexOf(user,allusers,usersStartid,0)»', 'profile.«attribut.name»', '«attribut.value»', '«counter=counter+1»');
   «ELSE»
    ('«indexOf(user,allusers,usersStartid,0)»', 'profile.«attribut.name»', '«attribut.value»', '«counter=counter+1»'),
   «ENDIF»
   «ENDFOR»
   «ENDIF»
   «ENDFOR»'''
   
   public def CharSequence generateGroups(EList<TreeElement> baum)'''
   
   UPDATE `#__usergroups` SET `rgt` = «searchElem(baum, -1).parent» WHERE `id` =1;
   
   INSERT INTO `#__usergroups` ( `parent_id`, `lft`, `rgt`, `title`) VALUES 
   «FOR group: groups»
   «IF(group.equals(groups.get(groups.size-1)))»
   ('«indexOf(group.parent,groups,groupsStartid,1)»', '«searchElem(baum, groups.indexOf(group)).lft»', '«searchElem(baum, groups.indexOf(group)).rgt»', '«group.name»');
   «ELSE»
   ('«indexOf(group.parent,groups,groupsStartid,1)»', '«searchElem(baum, groups.indexOf(group)).lft»', '«searchElem(baum, groups.indexOf(group)).rgt»', '«group.name»'),
   «ENDIF»
   «ENDFOR»'''
   
   public def CharSequence generateUserGroupsMap()'''
   
   INSERT INTO `#__user_usergroup_map` (`user_id`, `group_id`) VALUES 
   ('« indexOf(allusers.get(0), allusers,usersStartid,0)»','8'),
    «FOR user : allusers»
    «FOR group: groups»
    «IF user.usergroup!= null && user.usergroup.contains(group)»
    «IF(user.equals(allusers.get(allusers.size-1)))»
    ('«indexOf(user,allusers,usersStartid,0)»', '«indexOf(group,groups,groupsStartid,1)»');
  	«ELSE»
    ('«indexOf(user,allusers,usersStartid,0)»', '«indexOf(group,groups,groupsStartid,1)»'),
  	«ENDIF»
   «ENDIF»
   «ENDFOR»
   «ENDFOR»'''
   
   public def CharSequence generateViewLevel()'''
   «var int count = 3»
   INSERT INTO `#__viewlevels` (`title`, `ordering`, `rules`) VALUES 
   «FOR ViewLevel view: views»
   «IF(view.equals(views.get(views.size-1)))»
   ('«view.name»', '«count=count+1»', '[«searchGroupsIDForViewLevel(view)»]');
   «ELSE»
   ('«view.name»', '«count=count+1»', '[«searchGroupsIDForViewLevel(view)»]'),
   «ENDIF»
   «ENDFOR»'''
   
   public def CharSequence generateGroupsCoreAcess()'''
   UPDATE #__assets SET 
   rules = 
   '{
   	«FOR aktion: access.aktion»
   	«IF(access.aktion.get(access.aktion.length-1)==aktion)»
   	"«access.nameOfCoreaccess(aktion)»":{«access.getAcessForComUser(aktion, groups,groupsStartid)»}
   	«ELSE»
   	"«access.nameOfCoreaccess(aktion)»":{«access.getAcessForComUser(aktion, groups,groupsStartid)»},
   	«ENDIF»
   	«ENDFOR»
   }' WHERE id =24;
   '''
   
   public def String searchGroupsIDForViewLevel(ViewLevel level){
   	
   	var String result = new String()
   	var int counter =0;
   	for(UserGroup group: groups){
   		var int in
   		if(level.usergroup.contains(group)){
   			counter = counter +1 
   			in  = indexOf(group,groups,groupsStartid,1);
   			if(counter >= level.usergroup.size ){
   			  result = result + in;
   			  }else{
   			  	 result = result + in+",";
   			  }
   		}
   	}
   
   	return result
   }
   
   public def int getMaxUserIndex(){
   	return usersStartid + allusers.size
   }
   
    public def int getMaxGroupIndex(){
   	return groupsStartid + groups.size
   	
   }
	
}