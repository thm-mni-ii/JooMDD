package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Configuration
import de.thm.icampus.cjsl.cjsl.db_Conf
import java.io.File
import org.eclipse.xtext.generator.IFileSystemAccess
import org.antlr.runtime.tree.TreeIterator
import org.eclipse.emf.ecore.EObject

class MoreInstanzGenerator {
	
	public IFileSystemAccess fpa;
	public  TreeIterator app;
	public String pathDestinationRoot;
	public cJSL_Configuration config;
	public db_Conf dbconfig
	public UserGenerator users 
	public Installationgen install = new Installationgen
	public FileUtil fileUtil = new FileUtil 
	public Boolean newInstallation
	new (IFileSystemAccess acc, TreeIterator  app, String appname, Boolean installation){
		
		this.fpa = acc;
		this.app = app;
		dbconfig = config.db_conf
		newInstallation = installation
		}
	 
}