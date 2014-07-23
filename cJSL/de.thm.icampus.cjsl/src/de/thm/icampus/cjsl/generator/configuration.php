<?php
class JConfig {
//////////////////////////////
	public $offline = '0';
	public $offline_message = 'Diese Website ist zurzeit im Wartungsmodus.<br />Bitte später wiederkommen.';
	public $display_offline_message = '1';
	public $offline_image = '';
	public $sitename = 'joomlabach';
	public $editor = 'tinymce';
	public $captcha = '0';
	public $list_limit = '20';
	public $access = '1';
	public $feed_email = 'site';
	public $cookie_domain = '';
	public $cookie_path = '';
	public $MetaDesc = '';
	public $MetaKeys = '';
	public $MetaTitle = '1';
	public $MetaAuthor = '1';
	public $MetaVersion = '0';
	public $robots = '';
	public $sef = '1';
	public $sef_rewrite = '0';
	public $sef_suffix = '0';
	public $unicodeslugs = '0';
	public $feed_limit = '10';
	public $MetaRights = '';
	public $sitename_pagetitles = '0';
///////////////////////////////////////////////////

	public $dbtype = 'mysqli';
	public $host = 'localhost';
	public $user = 'root';
	public $password = '';
	public $db = 'joomlabach';
	public $dbprefix = 'jos_';

/////////////////////////////////////////////
	
	public $debug = '0';
	public $debug_lang = '0';
	public $caching = '0';
	public $cache_handler = 'file';
	public $cachetime = '150';
	public $lifetime = '150';
	public $session_handler = 'database';
	public $log_path = 'C:\\xampp\\htdocs\\joomlabach/logs';
	public $helpurl = 'http://help.joomla.org/proxy/index.php?option=com_help&keyref=Help{major}{minor}:{keyref}';
	
	
///////////////////////////////////////////////

public $force_ssl = '0';
public $offset = 'UTC';
public $tmp_path = 'C:\\xampp\\htdocs\\joomlabach/tmp';
public $gzip = '0';
public $error_reporting = 'default';

/////////////////////////////////////////////
	
		
	public $ftp_host = '127.0.0.1';
	public $ftp_port = '21';
	public $ftp_user = 'tst';
	public $ftp_pass = '';
	public $ftp_root = 'root';
	public $ftp_enable = '0';
	
////////////////////////////////////////////////
	public $mailer = 'mail';
	public $mailfrom = 'dieudonnetimma@yahoo.fr';
	public $fromname = 'joomlabach';
	public $sendmail = '/usr/sbin/sendmail';
	public $smtpauth = '1';
	public $smtpuser = '';
	public $smtppass = '';
	public $smtphost = 'localhost';
	public $smtpsecure = 'ssl';
	public $smtpport = '25';
	
///////////////////////////////////////////////////
	public $live_site = '';
	public $secret = 'DZk2H9y3q3eYDYob';
	
	
	
	
	
	
	
}