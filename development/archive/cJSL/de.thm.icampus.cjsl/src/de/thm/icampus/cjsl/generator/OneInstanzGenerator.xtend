package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.cJSL_Configuration
import de.thm.icampus.cjsl.cjsl.db_Conf
import java.io.File
import org.eclipse.xtext.generator.IFileSystemAccess

class OneInstanzGenerator extends ApplicationLibrary {
	
	public IFileSystemAccess fpa;
	public  Application app;
	public String pathDestinationRoot;
	public cJSL_Configuration config;
	public db_Conf dbconfig
	public UserGenerator users 
	public Installationgen install = new Installationgen
	public FileUtil fileUtil = new FileUtil 
	public Boolean newInstallation
	new (IFileSystemAccess acc, Application app, String appname, Boolean installation){
		
		this.fpa = acc;
		this.app = app;
		this.config = app.cjsl_configuration
		dbconfig = config.db_conf
		pathDestinationRoot = app.applicationPath + "/" +appname
		users = new UserGenerator(app,200,9,4)
		newInstallation = installation
		}


	
	def generateInstanz() {
		if(newInstallation){
			fpa.generateFile("mddinstallation/com/application.sql", contentGen())
			deleteFolder(new File(pathDestinationRoot+"/installation"))
		}
		
		fpa.generateFile("mddinstallation/com/user.sql", usercontengen())
		fpa.generateFile("includes/framework.php", install.overWriteFramework)
		fpa.generateFile("mddinstallation/definemdd.php", install.defineDefine)
		fpa.generateFile("mddinstallation/databasemdd.php",install.defineDatabaseMDD)
		fpa.generateFile("mddinstallation/login.php",install.defineFormular)
		fpa.generateFile("mddinstallation/index.php", install.defineIndex(dbconfig, app.cjsl_user.user.get(0)) )
		//fpa.generateFile("mddsql/test.sql", testgen() )
	}
//	def CharSequence testgen(){
//	
//	
//	return users.generateGroupsCoreAcess
//	}

def CharSequence usercontengen()'''
#--------------------------------------------------------------User-----------------------------------------------------------
	« users.generateAllUser»
	
	«users.generateUserProfile»
	
	«users.generateGroups(transformArtefact(app.cjsl_user.usergroups))»
	
	«users.generateUserGroupsMap»
	
	«users.generateViewLevel»
	
	«users.generateGroupsCoreAcess»
'''
	
	
	def CharSequence contentGen()'''
	«if (dbconfig.dbtype.toString.equals("mysql") || dbconfig.dbtype.toString.equals("mysqli")) 
	fileUtil.readFiletoString(pathDestinationRoot+"/installation/sql/mysql/joomla.sql") »
	«if (dbconfig.dbtype.toString.equals("sqlsrv")) 
	fileUtil.readFiletoString(pathDestinationRoot+"/installation/sql/sqlazure/joomla.sql") »
	«IF (config.website_conf.exampledata.equalsIgnoreCase("yes"))»
	#--------------------------------------------------------------Sample Data ---------------------------------------------------
	«fileUtil.readFiletoString(pathDestinationRoot+"/installation/sql/mysql/sample_data.sql")»
	«ENDIF»
	'''
	
	


	
}