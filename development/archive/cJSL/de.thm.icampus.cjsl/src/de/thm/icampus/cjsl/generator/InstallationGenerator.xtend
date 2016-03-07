package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.User
import de.thm.icampus.cjsl.cjsl.db_Conf

class Installationgen  {
	def CharSequence defineIndex(db_Conf db, User firstuser)'''
<?php

require_once 'definemdd.php';
require_once 'databasemdd.php';

if (!file_exists(JPATH_MDD.'/com/application.sql')) {
   header('Location:'.substr($_SERVER['REQUEST_URI'], 0, strpos($_SERVER['REQUEST_URI'], 'mddinstallation/index.php')).'index.php');
		exit();
}


$classData = new DatabaseMdd();

$options =  array(
		'db_type' =>'«db.dbtype.toString»',
		'db_host' => '«db.host»',
		'db_name' => '«db.database»',
		'db_prefix' => '«db.prefix»_',
		'db_created' => null,
		'db_user' => '«db.user»',
		'db_pass' => '«db.password»',
		'schema' => 'com/application.sql',
		'user_schema' => 'com/user.sql'
);



if(!empty($_GET['username'] ) && !empty($_GET['pass'])){

	$userpass = $_GET['pass'];
	$salt = sha1(32);
	$passmD5 = md5($userpass.$salt);

$pass = $passmD5.':'.$salt;

$classData->initialise($options);


$newdb = DatabaseMdd::getDbo($options->db_type, $options->db_host, $options->db_user, $options->db_pass, null, $options->db_prefix, false);
$newdb->setQuery("update  #__users set password='$pass'where username= '«firstuser.name»'" );
$re = $newdb->execute();

echo '<h3 style="color:green;"> Der neue Passwort wurde gespeichert</h3>';
sleep(10);
while(JFolder::delete(JPATH_MDD."/com")){};
header('Location:'.substr($_SERVER['REQUEST_URI'], 0, strpos($_SERVER['REQUEST_URI'], 'mddinstallation/index.php')).'index.php');
exit();
}else{
	header('Location: '.substr($_SERVER['REQUEST_URI'], 0, strpos($_SERVER['REQUEST_URI'], 'index.php')).'login.php');
	exit();

}
'''
	
def CharSequence overWriteFramework()'''
	<?php
/**
 * @package		Joomla.Site
 * @subpackage	Application
 * @copyright	Copyright (C) 2005 - 2013 Open Source Matters, Inc. All rights reserved.
 * @license		GNU General Public License version 2 or later; see LICENSE.txt
 */

// No direct access.
defined('_JEXEC') or die;

//
// Joomla system checks.
//

@ini_set('magic_quotes_runtime', 0);
@ini_set('zend.ze1_compatibility_mode', '0');

//
// Installation check, and check on removal of the install directory.
//
define('JPATH_MDD',	JPATH_ROOT.'/mddinstallation');


  if (file_exists(JPATH_MDD.'/com/application.sql')) {
    header('Location: '.substr($_SERVER['REQUEST_URI'], 0, strpos($_SERVER['REQUEST_URI'], 'index.php')).'mddinstallation/index.php');
    exit();
  }

//
// Joomla system startup.
//

// System includes.
require_once JPATH_LIBRARIES.'/import.php';

// Force library to be in JError legacy mode
JError::$legacy = true;
JError::setErrorHandling(E_NOTICE, 'message');
JError::setErrorHandling(E_WARNING, 'message');
JError::setErrorHandling(E_ERROR, 'callback', array('JError', 'customErrorPage'));

// Botstrap the CMS libraries.
require_once JPATH_LIBRARIES.'/cms.php';

// Pre-Load configuration.
ob_start();
require_once JPATH_CONFIGURATION.'/configuration.php';
ob_end_clean();

// System configuration.
$config = new JConfig();

// Set the error_reporting
switch ($config->error_reporting)
{
  case 'default':
  case '-1':
    break;

  case 'none':
  case '0':
    error_reporting(0);
    break;

  case 'simple':
    error_reporting(E_ERROR | E_WARNING | E_PARSE);
    ini_set('display_errors', 1);
    break;

  case 'maximum':
    error_reporting(E_ALL);
    ini_set('display_errors', 1);
    break;

  case 'development':
    error_reporting(-1);
    ini_set('display_errors', 1);
    break;

  default:
    error_reporting($config->error_reporting);
    ini_set('display_errors', 1);
    break;
}

define('JDEBUG', $config->debug);

unset($config);

//
// Joomla framework loading.
//

// System profiler.
if (JDEBUG) {
  jimport('joomla.error.profiler');
  $_PROFILER = JProfiler::getInstance('Application');
}

//
// Joomla library imports.
//

jimport('joomla.application.menu');
jimport('joomla.environment.uri');
jimport('joomla.utilities.utility');
jimport('joomla.event.dispatcher');
jimport('joomla.utilities.arrayhelper');
'''


def CharSequence defineDefine()'''
<?php

// PHP 5 check
if (version_compare(PHP_VERSION, '5.2.4', '<')) {
	die('Your host needs to use PHP 5.2.4 or higher to run this version of Joomla!');
}

/**
 * Constant that is checked in included files to prevent direct access.
 */
define('_JEXEC', 1);

/**
 * Constant that defines the base path of the installed Joomla site.
 */
define('JPATH_BASE', dirname(__FILE__));

// Set path constants.
$parts = explode(DIRECTORY_SEPARATOR, JPATH_BASE);
array_pop($parts);

define('JPATH_ROOT',			implode(DIRECTORY_SEPARATOR, $parts));
define('JPATH_SITE',			JPATH_ROOT);
define('JPATH_CONFIGURATION',	JPATH_ROOT);
define('JPATH_ADMINISTRATOR',	JPATH_ROOT . '/administrator');
define('JPATH_LIBRARIES',		JPATH_ROOT . '/libraries');
define('JPATH_PLUGINS',			JPATH_ROOT . '/plugins');
define('JPATH_INSTALLATION',	JPATH_ROOT . '/installation');
define('JPATH_THEMES',			JPATH_BASE);
define('JPATH_CACHE',			JPATH_ROOT . '/cache');
define('JPATH_MANIFESTS',		JPATH_ADMINISTRATOR . '/manifests');
define('JPATH_MDD',	JPATH_ROOT.'/mddinstallation');
/*
 * Joomla system checks.
*/
error_reporting(E_ALL);
@ini_set('magic_quotes_runtime', 0);
@ini_set('zend.ze1_compatibility_mode', '0');

/*
 * Check for existing configuration file.
*/
if (!file_exists(JPATH_BASE.'/index.php')) {
	header('Location: ../index.php');
	exit();
}

//
// Joomla system startup.
//

// Bootstrap the Joomla Framework.
require_once JPATH_LIBRARIES.'/import.php';

// Botstrap the CMS libraries.
require_once JPATH_LIBRARIES.'/cms.php';



// Joomla library imports.
jimport('joomla.database.table');
jimport('joomla.environment.uri');
jimport('joomla.utilities.arrayhelper');
jimport('joomla.filesystem.file');
'''
def CharSequence defineDatabaseMDD()'''
<?php

require_once 'definemdd.php';

class DatabaseMdd{

	public function initialise($options)
	{
		// Get the options as a JObject for easier handling.
		$options = JArrayHelper::toObject($options, 'JObject');



		// Ensure a database type was selected.
		if (empty($options->db_type)) {
			throw new Exception('INSTL_DATABASE_INVALID_TYPE');
			return false;
		}

		// Ensure that a valid hostname and user name were input.
		if (empty($options->db_host) || empty($options->db_user)) {
			throw new Exception('INSTL_DATABASE_INVALID_DB_DETAILS');
			return false;
		}

		// Ensure that a database name was input.
		if (empty($options->db_name)) {
			throw new Exception('INSTL_DATABASE_EMPTY_NAME');
			return false;
		}

		// Validate database table prefix.
		if (!preg_match('#^[a-zA-Z]+[a-zA-Z0-9_]*$#', $options->db_prefix)) {
			throw new Exception('INSTL_DATABASE_PREFIX_INVALID_CHARS');
			return false;
		}

		// Validate length of database table prefix.
		if (strlen($options->db_prefix) > 15) {
			throw new Exception('INSTL_DATABASE_FIX_TOO_LONG');
			return false;
		}

		// Validate length of database name.
		if (strlen($options->db_name) > 64) {
			throw new Exception('INSTL_DATABASE_NAME_TOO_LONG');
			return false;
		}

		// If the database is not yet created, create it.
		if (empty($options->db_created)) {
			// Get a database object.
			try
			{
				$db = DatabaseMdd::getDbo($options->db_type, $options->db_host, $options->db_user, $options->db_pass, null, $options->db_prefix, false);

				// Check database version.
				$db_version = $db->getVersion();
				$type = $options->db_type;
			}
			catch (RuntimeException $e)
			{
				throw new Exception('INSTL_DATABASE_COULD_NOT_CONNECT');
				return false;
			}

			if (!$db->isMinimumVersion()) {
				throw new Exception('INSTL_DATABASE_INVALID_'.strtoupper($type).'_VERSION');
				return false;
			}

			if ($type == ('mysql' || 'mysqli')) {
				// @internal MySQL versions pre 5.1.6 forbid . / or \ or NULL
				if ((preg_match('#[\\\/\.\0]#', $options->db_name)) && (!version_compare($db_version, '5.1.6', '>='))) {
					throw new Exception('INSTL_DATABASE_INVALID_NAME', $db_version);
					return false;
				}
			}

			// @internal Check for spaces in beginning or end of name
			if (strlen(trim($options->db_name)) <> strlen($options->db_name)) {
				throw new Exception('INSTL_DATABASE_NAME_INVALID_SPACES');
				return false;
			}

			// @internal Check for asc(00) Null in name
			if (strpos($options->db_name, chr(00)) !== false) {
				throw new Exception('INSTL_DATABASE_NAME_INVALID_CHAR');
				return false;
			}

			// Try to select the database
			try
			{
				$db->select($options->db_name);
			}
			catch (RuntimeException $e)
			{
				// If the database could not be selected, attempt to create it and then select it.
				if ($this->createDatabase($db, $options->db_name)) {
					$db->select($options->db_name);
				} else {
					throw new Exception('INSTL_DATABASE_ERROR_CREATE');
					return false;
				}
			}

			// Set the character set to UTF-8 for pre-existing databases.
			$this->setDatabaseCharset($db, $options->db_name);

            $schema = $options->schema;
			// Check if the schema is a valid file
			if (!JFile::exists($schema)) {
				throw new Exception('INSTL_ERROR_DB'.'INSTL_DATABASE_NO_SCHEMA');
				return false;
			}

			// Attempt to import the database schema.
			if (!$this->populateDatabase($db, $schema)) {
				throw new Exception('INSTL_ERROR_DB');
				return false;
			}
			$user_schema = $options->user_schema;
			// Attempt to import the database schema of MDD for User.
			if (!$this->populateDatabase($db, $user_schema)) {
				throw new Exception('INSTL_ERROR_DB_USER');
				return false;
			}

			// Attempt to update the table #__schema.
			$files = JFolder::files(JPATH_ADMINISTRATOR . '/components/com_admin/sql/updates/mysql/', '\.sql$');
			if (empty($files)) {
				throw new Exception('INSTL_ERROR_INITIALISE_SCHEMA');
				return false;
			}
			$version = '';
			foreach ($files as $file) {
				if (version_compare($version, JFile::stripExt($file)) <0) {
					$version = JFile::stripExt($file);
				}
			}
			$query = $db->getQuery(true);
			$query->insert('#__schemas');
			$query->columns(
					array(
							$db->quoteName('extension_id'),
							$db->quoteName('version_id')));
			$query->values('700, '. $db->quote($version)) ;
			$db->setQuery($query);

			try
			{
				$db->execute();
			}
			catch (RuntimeException $e)
			{
				$this->setError($e->getMessage());
				return false;
			}

			// Attempt to refresh manifest caches
			$query = $db->getQuery(true);
			$query->select('*');
			$query->from('#__extensions');
			$db->setQuery($query);

			try
			{
				$extensions = $db->loadObjectList();
			}
			catch (RuntimeException $e)
			{
				throw new Exception($e->getMessage());
				$return = false;
			}

		}

		return true;
	}


	public function installSampleData($options)
	{
		// Get the options as a JObject for easier handling.
		$options = JArrayHelper::toObject($options, 'JObject');

		// Get a database object.
		try
		{
			$db = JInstallationHelperDatabase::getDBO($options->db_type, $options->db_host, $options->db_user, $options->db_pass, $options->db_name, $options->db_prefix);
		}
		catch (RuntimeException $e)
		{
			throw new Exception('INSTL_DATABASE_COULD_NOT_CONNECT'. $e->getMessage());
			return false;
		}

		// Build the path to the sample data file.
		$type = $options->db_type;

		$data =  $options->sample_file;

		// Attempt to import the database schema.
		if (!file_exists($data)) {
			throw new Exception('INSTL_DATABASE_FILE_DOES_NOT_EXIST'.$data);
			return false;
		}
		elseif (!$this->populateDatabase($db, $data)) {
			throw new Exception('INSTL_ERROR_DB');
			return false;
		}

		$this->postInstallSampleData($db);

		return true;
	}

	public function postInstallSampleData($db) {
		// Create the ID for the root user
		$userId = self::getUserId();

		// update all created_by field of the tables with the random user id
		// categories (created_user_id), contact_details, content, newsfeeds, weblinks
		$updates_array = array(
				'categories' => 'created_user_id',
				'contact_details' => 'created_by',
				'content' => 'created_by',
				'newsfeeds' => 'created_by',
				'weblinks' => 'created_by',
		);
		foreach ($updates_array as $table => $field) {
			$db->setQuery(
					'UPDATE '.$db->quoteName('#__' . $table) .
					' SET '.$db->quoteName($field).' = '.$db->Quote($userId)
			);
			$db->query();
		}

	}

public	function populateDatabase($db, $schema)
	{
		// Initialise variables.
		$return = true;

		// Get the contents of the schema file.
		if (!($buffer = file_get_contents($schema))) {
			throw new Exception($db->getErrorMsg());
			return false;
		}

		// Get an array of queries from the schema and process them.
		$queries = $this ->_splitQueries($buffer);
		foreach ($queries as $query)
		{
			// Trim any whitespace.
			$query = trim($query);

			// If the query isn't empty and is not a comment, execute it.
			if (!empty($query) && ($query{0} != '#')) {
				// Execute the query.
				$db->setQuery($query);

				try
				{
					$db->execute();
				}
				catch (RuntimeException $e)
				{
					$return = false;
				}
			}
		}

		return $return;
	}



	function setDatabaseCharset($db, $name)
	{
		// Run the create database query.
		$db->setQuery(
				'ALTER DATABASE '.$db->quoteName($name).' CHARACTER' .
				' SET `utf8`'
		);

		try
		{
			$db->execute();
		}
		catch (RuntimeException $e)
		{
			return false;
		}

		return true;
	}




	/**
	 * Method to split up queries from a schema file into an array.
	 *
	 * @param	string	$sql SQL schema.
	 *
	 * @return	array	Queries to perform.
	 * @since	1.0
	 * @access	protected
	 */
	public function _splitQueries($sql)
	{
		// Initialise variables.
		$buffer		= array();
		$queries	= array();
		$in_string	= false;

		// Trim any whitespace.
		$sql = trim($sql);

		// Remove comment lines.
		$sql = preg_replace("/\n\#[^\n]*/", '', "\n".$sql);

		// Parse the schema file to break up queries.
		for ($i = 0; $i < strlen($sql) - 1; $i ++)
		{
			if ($sql[$i] == ";" && !$in_string) {
				$queries[] = substr($sql, 0, $i);
				$sql = substr($sql, $i +1);
				$i = 0;
			}

			if ($in_string && ($sql[$i] == $in_string) && $buffer[1] != "\\") {
				$in_string = false;
			}
			elseif (!$in_string && ($sql[$i] == '"' || $sql[$i] == "'") && (!isset ($buffer[0]) || $buffer[0] != "\\")) {
				$in_string = $sql[$i];
			}
			if (isset ($buffer[1])) {
				$buffer[0] = $buffer[1];
			}
			$buffer[1] = $sql[$i];
		}

		// If the is anything left over, add it to the queries.
		if (!empty($sql)) {
			$queries[] = $sql;
		}

		return $queries;
	}

public	function createDatabase(& $db, $name)
	{
		// Build the create database query.
		$query = 'CREATE DATABASE '.$db->quoteName($name).' CHARACTER SET `utf8`';

		// Run the create database query.
		$db->setQuery($query);

		try
		{
			$db->execute();
		}
		catch (RuntimeException $e)
		{
			// If an error occurred return false.
			return false;
		}

		return true;
	}

	public static function & getDBO($driver, $host, $user, $password, $database, $prefix, $select = true)
	{
		static  $db;

		if (!$db) {
			// Build the connection options array.
			$options = array (
					'driver' => $driver,
					'host' => $host,
					'user' => $user,
					'password' => $password,
					'database' => $database,
					'prefix' => $prefix,
					'select' => $select
			);

			// Get a database object.
			$db = JDatabase::getInstance($options);
		}

		return $db;
	}


}
'''

def CharSequence defineFormular()'''
<?php
?>
<!DOCTYPE html
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Joomla! Installation</title>
    <link rel="stylesheet" type="text/css" href="login.css"/>
    <style type="text/css">
    
    body {
  background-color: #FFFFFF;
  margin:           0px ;
}

h1 {
  font:             bold 1.5em sans-serif;
  margin:           0px;
  padding-top:      4px;
  padding-bottom:   4px;
}

p {
  margin:           0px;
  padding:          0px;
}

#header  {
   height:auto;
  background-color: #FFFFFF;
}

#mainarticle{
background-color: #BDBDBD;
max-width: 500px
}
    
    </style>
  </head>

<body vlink="#0000CC" text="#000000" link="#0000CC" bgcolor="#FFFFFF" alink="#0000CC">


  <div id="header">
    <center><img width="541" height="180" src="https://webmail.thm.de/images/THM_Logo_4c-transparent.png" alt="THM" /></center>
  </div>

  <div id="mainwrapper">

    <div id="contentwrapper">

       <center> <div id="mainarticle">
       <h1>MDD Joomla! Installation</h1>
       bitte geben Sie den Name und das neue Passwort des Super Administrator an!
        <form action="<?php echo "index.php";?>" method="get" >
       <table>
       <tr><td><h1 >Benutzername:  </h1></td><td><input type="text"  autocomplete="on" title="Benutzer Name" name="username" min="155"></input></td></tr>
       <tr><td><h1 >Passwort:  </h1></td><td><input type="password" name="pass" title="Benutzer Password" min="155"></td></tr>
    </table>
      <input type="submit" value="absenden" name="submit" >
      </form>
           </div> </center>
      </div>
    </div>

  <div id="footer">

  </div>


</body>

</html>

'''

		
	
}