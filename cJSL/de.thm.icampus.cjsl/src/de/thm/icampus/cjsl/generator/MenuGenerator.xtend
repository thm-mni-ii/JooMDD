package de.thm.icampus.cjsl.generator

import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Menu
import de.thm.icampus.cjsl.cjsl.Menu
import de.thm.icampus.cjsl.cjsl.MenuItem
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.MenuRepresentation

class MenuGenerator extends ApplicationLibrary {
	/**
	 * Index of Menu begin of 2
	 * 
	 * Index of Menuitem begin of 102
	 */
	
	 Application app
	EList<Menu> allmenus
	EList<MenuRepresentation> allmodul
	int menuStartid
	int menuItemStart
	int modulStartid
	new( Application newapp, int menuStartindex, int menuItemStartIndex, int modulStartIndex) {
		app= newapp
		allmenus = app.cjsl_menu.menu
		allmodul=  app.cjsl_menu.menuRepresentation
		menuStartid = menuStartindex
		menuItemStart = menuItemStartIndex
		modulStartid = modulStartIndex
	}
	
	public def CharSequence  genMenus()'''
	INSERT INTO `#__menu_types`( `menutype`, `title`, `description`) VALUES
	«FOR menu : allmenus»
	«IF(menu == allmenus.get(allmenus.size-1))»
	 («menu.title»,«menu.menuType»,«menu.description»);
	 «ELSE»
	 («menu.title»,«menu.menuType»,«menu.description»),
	 «ENDIF»
	 «ENDFOR»
	'''
	public def CharSequence  genMenuItem()'''
	INSERT INTO `#__menu`(`id`, `menutype`, `title`, `alias`, `note`, `path`, `link`, `type`, `published`, `parent_id`, `level`, `component_id`, `ordering`, `checked_out`, `checked_out_time`, `browserNav`, `access`, `img`, `template_style_id`, `params`, `lft`, `rgt`, `home`, `language`, `client_id`) VALUES 
	()
	'''
	public def CharSequence  genMenuRepresentation()'''
	'''
	
	 public def int getMaxMenuIndex(){
   	return menuStartid + allmenus.size
   }
   
    public def int getMaxModulIndex(){
   	return modulStartid + allmodul.size
   	
   }
}