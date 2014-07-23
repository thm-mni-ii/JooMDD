package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.Editor
import de.thm.icampus.cjsl.cjsl.Language
import de.thm.icampus.cjsl.cjsl.TimeZone
import de.thm.icampus.cjsl.cjsl.cJSL_Configuration
import de.thm.icampus.cjsl.cjsl.db_Conf
import de.thm.icampus.cjsl.cjsl.ftp_Conf
import de.thm.icampus.cjsl.cjsl.mailer_Conf
import de.thm.icampus.cjsl.cjsl.smtp_Conf
import de.thm.icampus.cjsl.cjsl.system_Conf
import de.thm.icampus.cjsl.cjsl.website_Conf
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.cjsl.cjsl.Application

class ConfigGenerator extends ApplicationLibrary {
	
	IFileSystemAccess fpa;
	cJSL_Configuration config;
	Application app;
	
	new (IFileSystemAccess acc, Application apps){
		
		this.fpa = acc;
		this.config = apps.cjsl_configuration;
		this.app = apps;		
		}
	
	def generateConfig() {
		fpa.generateFile("configuration.php", config.contentGen)
	}
	
	def CharSequence contentGen(cJSL_Configuration configuration)'''
	<?php
	class JConfig {
	
	«genWebsite_Conf(configuration.website_conf)»
	«genDB_Conf(configuration.db_conf)»
	«if(configuration.system_conf != null)genSystem_Conf(configuration.system_conf) else genDefaultSystem_conf()»
	« if(configuration.ftp_conf != null) genFTP_Conf(configuration.ftp_conf)else genDefaultFTP_Conf()»
	« if(configuration.mailer_conf != null)  genMailer_Conf(configuration.mailer_conf) else genDefaultMailer_Conf() »
	}
	'''
	
	def CharSequence genDefaultSystem_conf() '''
	public $secret = 'FBVtggIk5lAzEU9H'; 		// Change this to something more secure
	public $gzip = '0';
	public $error_reporting = 'default';
	public $helpurl = 'http://help.joomla.org/proxy/index.php?option=com_help&amp;keyref=Help{major}{minor}:{keyref}';
	public $tmp_path = '/tmp';
	public $log_path = '/var/logs';
	public $live_site = ''; 					
	public $force_ssl = 0;
	public $lifetime = '15';					// Session time
	public $session_handler = 'database';						
	public $offset = 'UTC';
	public $caching = '0';
	public $cachetime = '15';
	public $cache_handler = 'file';
	public $debug = '0';
	public $debug_lang = '0';
	'''
	
	def CharSequence genDefaultFTP_Conf()'''
	public $ftp_host = '';
	public $ftp_port = '';
	public $ftp_user = '';
	public $ftp_pass = '';
	public $ftp_root = '';
	public $ftp_enable = '';
	'''
	def CharSequence genDefaultMailer_Conf()'''
	public $mailer = 'mail';
	public $mailfrom = '';
	public $fromname = '';
	public $sendmail = '/usr/sbin/sendmail';
	public $smtpauth = '0';
	public $smtpuser = '';
	public $smtppass = '';
	public $smtphost = 'localhost';
	public $smtpsecure = 'ssl';
	public $smtpport = '25';
	'''
	
	def CharSequence paramConfig(String name, String value)'''
	public $«name» = '«value»';
	'''
	def CharSequence paramConfig(String name, int value)'''
	public $«name» = '«value»';
	'''
	
	def CharSequence genWebsite_Conf(website_Conf website)'''
	«paramConfig("sitename", website.pagetitle)»
	«paramConfig("offline",isEmpty(valueParser(website.offline+""),"0"))»
	«paramConfig("offline_message",isEmpty(website.offline_message,"Diese Website ist zurzeit im Wartungsmodus.<br />Bitte später wiederkommen."))»
	«paramConfig("offline_image", website.offline_image)»
	«paramConfig("captcha", "0")»
	«paramConfig("editor", selectedEdit(website.editor))»
	«paramConfig("display_offline_message", isEmpty(website.offline_message))»
	«paramConfig("list_limit",0)»
	«paramConfig("access", isEmpty(selectedSiteacess(website.site_access, app.cjsl_user.viewlevel),"1"))»
	«paramConfig("feed_email", "site")»
	«paramConfig("cookie_domain", "")»
	«paramConfig("cookie_path", "")»
	«paramConfig("MetaDesc", website.description)»
	«paramConfig("MetaKeys",website.keyword.toString)»
	public $MetaTitle = '1';
	«paramConfig("MetaAuthor",valueParser(isEmpty(website.show_author_meta_tag, "yes").toString))»
	«paramConfig("MetaVersion", valueParser(isEmpty(website.show_joomla_version, "no").toString))»
	«paramConfig("robots",valueParser(isEmpty(website.robot.toString,"index, follow").toString))»
	«paramConfig("sef", isEmpty(website.use_sef,"1"))»
	«paramConfig("sef_rewrite", valueParser(isEmpty(website.url_rewrite, "0")))»
	«paramConfig("sef_suffix", "0")»
	«paramConfig("unicodeslugs", "0")»
	«paramConfig("feed_limit", 10)»
	«paramConfig("MetaRights", "")»
	«paramConfig("sitename_pagetitles", valueParser(isEmpty(website.include_site_name_in_page_titles.toString,'no').toString))»
	'''
	
	def CharSequence genDB_Conf(db_Conf dbconf)'''
	«paramConfig("dbtype", dbconf.dbtype.toString)»
	«paramConfig("host", isEmpty(dbconf.host.toString,"localhost"))»
	«paramConfig("user",isEmpty(dbconf.user, "root"))»
	«paramConfig("password",dbconf.password.toString)»
	«paramConfig("db", dbconf.database)»
	«paramConfig("dbprefix", dbconf.prefix+"_")»
	'''
	
	def CharSequence genFTP_Conf(ftp_Conf ftpconf)'''
	«paramConfig("ftp_host", ftpconf.host.toString)»
	«paramConfig("ftp_port", ftpconf.port)»
	«paramConfig("ftp_user", ftpconf.ftp_user)»
	«paramConfig("ftp_pass",ftpconf.ftp_pass)»
	«paramConfig("ftp_root",isEmpty(ftpconf.root_path,"root"))»
	«paramConfig("ftp_enable",valueParser(isEmpty(ftpconf.host)))»
	'''
	
	def CharSequence genMailer_Conf(mailer_Conf mailerconf)'''
	«paramConfig("mailer", valueParser(isEmpty(mailerconf.mailer.getName(),"php mail")))»
	«paramConfig("mailfrom", mailerconf.mail_from)»
	«paramConfig("fromname", mailerconf.name_from)»
	«paramConfig("sendmail", isEmpty(mailerconf.sendmail_path, "/usr/sbin/sendmail"))»
	«if(mailerconf.smtpconfig != null) {genSMTP_conf(mailerconf.smtpconfig)}
	else
	genDefaultSMTP_conf()
	»
	'''
	
	
	def CharSequence genDefaultSMTP_conf() '''
	public $smtpauth = '0';
	public $smtpuser = '';
	public $smtppass = '';
	public $smtphost = 'localhost';
	public $smtpsecure = 'ssl';
	public $smtpport = '25';
	'''
	def CharSequence genSMTP_conf(smtp_Conf conf) '''
	«paramConfig("smtpauth",valueParser(isEmpty(conf.smtp_auth, "no")))»
	«paramConfig("smtpuser", conf.smtp_username)»
	«paramConfig("smtppass", conf.smtp_password)»
	«paramConfig("smtphost", isEmpty(conf.smtp_host.toString,"localhost"))»
	«paramConfig("smtpport", isEmpty(conf.smtp_port,25))»
	«paramConfig("smtpsecure",isEmpty(conf.smtp_security.getName(),"ssl"))»
	'''
	
	def CharSequence genSystem_Conf(system_Conf systemconf)'''
	«paramConfig("debug", valueParser(isEmpty(systemconf.debug_system,"no")))»
	«paramConfig("debug_lang", valueParser(isEmpty(systemconf.debug_language,"no")))»
	«paramConfig("caching", valueParser(isEmpty(systemconf.caching.toString,"off-caching disabled")))»
	«paramConfig("cache_handler", valueParser(isEmpty(systemconf.cache_handler.toString,"file")))»
	«paramConfig("cachetime", isEmpty(systemconf.cache_time, 150))»
	«paramConfig("lifetime", isEmpty(systemconf.session_lifetime, 150))»
	«paramConfig("session_handler","database")»
	«paramConfig("log_path","/var/logs")»
	«paramConfig("helpurl", "http://help.joomla.org/proxy/index.php?option=com_help&keyref=Help{major}{minor}:{keyref}")»
	«paramConfig("force_ssl",valueParser(isEmpty(systemconf.force_ssl.toString, "none")))»
	«paramConfig("offset", isEmpty(searchattribut(systemconf.server_time_zone," "),"UTC"))»
	«paramConfig("tmp_path","/tmp")»
	«paramConfig("gzip", valueParser("no"))»
	«paramConfig("error_reporting", valueParser(isEmpty(systemconf.error_reporting_type.toString, "default")))»
	public $live_site = '';
	public $secret = 'DZk2H9y3q3eYDYob';
	'''
	
	def CharSequence genEditor(Editor editorconf)'''
	'''
	
	def CharSequence genLanguage(Language langconf)'''
	'''
	
	def CharSequence genTimeZone (TimeZone time)'''
	'''
	
	
	
}