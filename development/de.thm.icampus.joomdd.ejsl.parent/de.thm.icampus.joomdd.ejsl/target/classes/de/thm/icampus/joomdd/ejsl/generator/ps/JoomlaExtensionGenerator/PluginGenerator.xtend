/**
 */
package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaExtensionGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.eJSL.Language
import java.util.Calendar
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.ProtectedRegion
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.KVPairGeneratorClient

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eJSLGenerator.GeneratorTemplatePackage#getPluginGenerator()
 * @model
 * @generated
 */
public class PluginGenerator extends AbstractExtensionGenerator {
	
	private String slug;
	private Plugin plugin;
	
	new(Plugin plugin, IFileSystemAccess fsa, String path)
	{
		this.fsa = fsa;
		this.slug = Slug.slugify(plugin.name);
		this.name = "plg_" + this.slug
		//this.name = plugin.name;
		this.plugin = plugin;
		this.path = path
	}
	
	def static PluginGenerator getGenerator(Plugin plugin, IFileSystemAccess fsa, String path) {
		return new PluginGenerator(plugin, fsa,path)
	}
	
	
	/**
	 * @override
	 */
	override generate() 
	{
		generateJoomlaDirectory("")
		generateFile(plugin.name + ".xml", plugin.xmlContent)
		generateFile(plugin.name + ".php", plugin.phpContent)
		
		if (plugin.languages.size > 0) {
		
		generateJoomlaDirectory("language")
		
			for (lang : plugin.languages) {
				generateJoomlaDirectory("language/" + lang.name)
				generateFile("language/" + lang.name + "/" + lang.name + "." + "plg_" + plugin.type + "_" + plugin.name + ".ini", lang.iniContent)
				generateFile("language/" + lang.name + "/" + lang.name + "." + "plg_" + plugin.type + "_" + plugin.name + ".sys.ini", lang.iniContent)
			}
		}
		return ""
	}
	
	def CharSequence iniContent(Language language) '''
	«FOR e : language.keyvaluepairs»
		«e.name»="«e.value»"
	«ENDFOR»
	'''
	
	def CharSequence phpContent(Plugin plugin) '''
	<?php
	/**
	* @version $Id: «plugin.name» version date author
	* @copyright Copyright
	* @license License, for example GNU/GPL
	* All other information you would like to add
	*/

	// don't allow other scripts to grab and execute this file
	defined('_JEXEC') or die('Direct Access to this location is not allowed');
	jimport('joomla.plugin.plugin');

	class Plg«plugin.type.toString.toFirstUpper»«plugin.name.toString.toFirstUpper» extends JPlugin
	{
		public function plg«plugin.type.toString.toFirstUpper»«plugin.name.toString.toFirstUpper»(&$subject, $params)
		{
			parent::__construct($subject, $params);
			$this->loadLanguage();
		}
		
		«plugin.events»
	}
	'''
	
	def CharSequence events(Plugin plugin)
	{
		switch (plugin.type) {
			case AUTHENTICATE:
			return plugin.authenticateEvents
			case CAPTCHA:
			return plugin.captchaEvents
			case CONTENT:
			return plugin.contentEvents
			case CONTACT:
			return plugin.contactEvents
			case EDITORS:
			return plugin.editorsEvents
			case EXTENSIONS:
			return plugin.extensionsEvents
			case FINDER:
			return plugin.finderEvents
			case QUICK_ICONS:
			return plugin.quickiconsEvents
			case SEARCH:
			return plugin.searchEvents
			case SYSTEM:
			return plugin.systemEvents
			case USER:
			return plugin.userEvents
			case XML_RPC:
			return plugin.xmlrpcEvents
		}
	}
	
	def CharSequence authenticateEvents(Plugin plugin) '''
	public function onUserAuthenticate($credentials, $options, &$response)
	{
		$response->type = '«plugin.name.toString.toFirstUpper»';
		
		// TODO: place code here
	}
	'''
	
	def CharSequence captchaEvents(Plugin plugin) '''
	//TODO: place code here
	'''
	
	def CharSequence contentEvents(Plugin plugin) '''
	public function onContentPrepare($context, &$article, &$params, $page = 0)
	{
		// TODO: place code here
	}
	'''
	
	def CharSequence contactEvents(Plugin plugin) '''
	//TODO: place code here
	'''
	
	def CharSequence editorsEvents(Plugin plugin) '''
	/**
	 * Initialises the Editor.
	 *
	 * @return  string  JavaScript Initialization string.
	 */
	public function onInit()
	{
		//TODO: place code here
	}
	
	public function onSave()
	{
		//always return
		return;
	}
	
	/**
	 * Get the editor content
	 */
	public function onGetContent()
	{
		return '/*place code here*/';
	}
	
	/**
	 * Set the editor content.
	 */
	public function onSetContent()
	{
		return '/*place code here*/';
	}
	
	/**
	 * Adds the editor specific insert method.
	 */
	public function onGetInsertMethod()
	{
		//TODO: place code here
	}
	
	/**
	 * Display the editor area.
	 *
	 * @param   string   $name     The control name.
	 * @param   string   $content  The contents of the text area.
	 * @param   string   $width    The width of the text area (px or %).
	 * @param   string   $height   The height of the text area (px or %).
	 * @param   integer  $col      The number of columns for the textarea.
	 * @param   integer  $row      The number of rows for the textarea.
	 * @param   boolean  $buttons  True and the editor buttons will be displayed.
	 * @param   string   $id       An optional ID for the textarea (note: since 1.6). If not supplied the name is used.
	 * @param   string   $asset    Unused
	 * @param   object   $author   Unused
	 * @param   array    $params   Associative array of editor parameters.
	 *
	 * @return  string  HTML Output
	 */
	public function onDisplay($name, $content, $width, $height, $col, $row, $buttons = true,
		$id = null, $asset = null, $author = null, $params = array())
	{
		if (empty($id))
		{
			$id = $name;
		}

		// Only add "px" to width and height if they are not given as a percentage
		if (is_numeric($width))
		{
			$width .= 'px';
		}

		if (is_numeric($height))
		{
			$height .= 'px';
		}
		
		//TODO: add some code here
	} 
	
	/**
	 * Displays the editor buttons.
	 *
	 * @param   string  $name     The control name.
	 * @param   mixed   $buttons  [array with button objects | boolean true to display buttons]
	 * @param   string  $asset    The object asset
	 * @param   object  $author   The author.
	 *
	 * @return  string HTML
	 */
	public function _displayButtons($name, $buttons, $asset, $author)
	{
		$return = '';

		$args = array(
			'name'  => $name,
			'event' => 'onGetInsertMethod'
		);

		$results = (array) $this->update($args);

		if ($results)
		{
			foreach ($results as $result)
			{
				if (is_string($result) && trim($result))
				{
					$return .= $result;
				}
			}
		}

		if (is_array($buttons) || (is_bool($buttons) && $buttons))
		{
			$buttons = $this->_subject->getButtons($name, $buttons, $asset, $author);

			$return .= JLayoutHelper::render('joomla.editors.buttons', $buttons);
		}

		return $return;
	}
	'''
	
	def CharSequence extensionsEvents(Plugin plugin) '''
	/**
	 * Load the language file on instantiation.
	 *
	 * @var    boolean
	 * @since  3.1
	 */
	protected $autoloadLanguage = true;
	
	//TODO: place code here
	'''
	
	def CharSequence finderEvents(Plugin plugin) '''
	/**
	 * The plugin identifier.
	 *
	 * @var    string
	 * @since  2.5
	 */
	protected $context = '«plugin.name.toString.toFirstUpper»';

	/**
	 * The extension name.
	 *
	 * @var    string
	 * @since  2.5
	 */
	protected $extension = 'com_«plugin.name»';

	/**
	 * The sublayout to use when rendering the results.
	 *
	 * @var    string
	 * @since  2.5
	 */
	protected $layout = '';

	/**
	 * The type of content that the adapter indexes.
	 *
	 * @var    string
	 * @since  2.5
	 */
	protected $type_title = '';

	/**
	 * The table name.
	 *
	 * @var    string
	 * @since  2.5
	 */
	protected $table = '#__«plugin.name»';

	/**
	 * The field the published state is stored in.
	 *
	 * @var    string
	 * @since  2.5
	 * @not always an option
	 */
	protected $state_field = 'published';

	/**
	 * Load the language file on instantiation.
	 *
	 * @var    boolean
	 * @since  3.1
	 */
	protected $autoloadLanguage = true;
	
	/**
	 * Method to update the item link information when the item category is
	 * changed. This is fired when the item category is published or unpublished
	 * from the list view.
	 *
	 * @param   string   $extension  The extension whose category has been updated.
	 * @param   array    $pks        An array of primary key ids of the content that has changed state.
	 * @param   integer  $value      The value of the state that the content has been changed to.
	 *
	 * @return  void
	 *
	 * @since   2.5
	 */
	public function onFinderCategoryChangeState($extension, $pks, $value)
	{
		// Make sure we're handling com_names categories.
		if ($extension == 'com_«plugin.name»')
		{
			$this->categoryStateChange($pks, $value);
		}
	}
	
	/**
	 * Method to remove the link information for items that have been deleted.
	 *
	 * @param   string  $context  The context of the action being performed.
	 * @param   JTable  $table    A JTable object containing the record to be deleted.
	 *
	 * @return  boolean  True on success.
	 *
	 * @since   2.5
	 * @throws  Exception on database error.
	 */
	public function onFinderAfterDelete($context, $table)
	{
		if ($context == 'com_«plugin.name».article')
		{
			$id = $table->id;
		}
		elseif ($context == 'com_finder.index')
		{
			$id = $table->link_id;
		}
		else
		{
			return true;
		}

		// Remove the item from the index.
		return $this->remove($id);
	}
	
	/**
	 * Smart Search after content save method.
	 * Reindexes the link information for a weblink that has been saved.
	 * It also makes adjustments if the access level of a weblink item or
	 * the category to which it belongs has been changed.
	 *
	 * @param   string   $context  The context of the content passed to the plugin.
	 * @param   JTable   $row      A JTable object.
	 * @param   boolean  $isNew    True if the content has just been created.
	 *
	 * @return  boolean  True on success.
	 *
	 * @since   2.5
	 * @throws  Exception on database error.
	 */
	public function onFinderAfterSave($context, $row, $isNew)
	{
		// We only want to handle «plugin.name» here. We need to handle front end and back end editing.
		if ($context == 'com_«plugin.name».article' || $context == 'com_«plugin.name».form' )
		{
			// Check if the access levels are different.
			if (!$isNew && $this->old_access != $row->access)
			{
				// Process the change.
				$this->itemAccessChange($row);
			}

			// Reindex the item.
			$this->reindex($row->id);
		}

		// Check for access changes in the category.
		if ($context == 'com_categories.category')
		{
			// Check if the access levels are different.
			if (!$isNew && $this->old_cataccess != $row->access)
			{
				$this->categoryAccessChange($row);
			}
		}

		return true;
	}
	
	/**
	 * Smart Search before content save method.
	 * This event is fired before the data is actually saved.
	 *
	 * @param   string   $context  The context of the content passed to the plugin.
	 * @param   JTable   $row      A JTable object.
	 * @param   boolean  $isNew    True if the content is just about to be created.
	 *
	 * @return  boolean  True on success.
	 *
	 * @since   2.5
	 * @throws  Exception on database error.
	 */
	public function onFinderBeforeSave($context, $row, $isNew)
	{
		// We only want to handle «plugin.name» here.
		if ($context == 'com_«plugin.name».article' || $context == 'com_«plugin.name».form')
		{
			// Query the database for the old access level if the item isn't new.
			if (!$isNew)
			{
				$this->checkItemAccess($row);
			}
		}

		// Check for access levels from the category.
		if ($context == 'com_categories.category')
		{
			// Query the database for the old access level if the item isn't new.
			if (!$isNew)
			{
				$this->checkCategoryAccess($row);
			}
		}

		return true;
	}
	
	/**
	 * Method to update the link information for items that have been changed
	 * from outside the edit screen. This is fired when the item is published,
	 * unpublished, archived, or unarchived from the list view.
	 *
	 * @param   string   $context  The context for the content passed to the plugin.
	 * @param   array    $pks      An array of primary key ids of the content that has changed state.
	 * @param   integer  $value    The value of the state that the content has been changed to.
	 *
	 * @return  void
	 *
	 * @since   2.5
	 */
	public function onFinderChangeState($context, $pks, $value)
	{
		// We only want to handle «plugin.name» here.
		if ($context == 'com_«plugin.name».article' || $context == 'com_«plugin.name».form')
		{
			$this->itemStateChange($pks, $value);
		}

		// Handle when the plugin is disabled.
		if ($context == 'com_plugins.plugin' && $value === 0)
		{
			$this->pluginDisable($pks);
		}
	}
	
	/**
	 * Method to index an item. The item must be a FinderIndexerResult object.
	 *
	 * @param   FinderIndexerResult  $item    The item to index as an FinderIndexerResult object.
	 * @param   string               $format  The item format.  Not used.
	 *
	 * @return  void
	 *
	 * @since   2.5
	 * @throws  Exception on database error.
	 */
	protected function index(FinderIndexerResult $item, $format = 'html')
	{
		// Check if the extension is enabled
		if (JComponentHelper::isEnabled($this->extension) == false)
		{
			return;
		}

		$item->setLanguage();
		
		// Initialise the item parameters.
		$registry = new JRegistry;
		$registry->loadString($item->params);
		$item->params = $registry;

		$registry = new JRegistry;
		$registry->loadString($item->metadata);
		$item->metadata = $registry;
		
		// Build the necessary route and path information.
		$item->url = $this->getURL($item->id, $this->extension, $this->layout);
		$item->route = «plugin.name.toString.toFirstUpper»HelperRoute::getArticleRoute($item->slug, $item->catslug, $item->language);
		$item->path = FinderIndexerHelper::getContentPath($item->route);
		
		/*
		 * Add the meta-data processing instructions based on the newsfeeds
		 * configuration parameters.
		 */
		// Add the meta-author.
		$item->metaauthor = $item->metadata->get('author');

		// Handle the link to the meta-data.
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'link');
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'metakey');
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'metadesc');
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'metaauthor');
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'author');
		$item->addInstruction(FinderIndexer::META_CONTEXT, 'created_by_alias');
		
		// Add the type taxonomy data.
		$item->addTaxonomy('Type', 'Web Link');

		// Add the category taxonomy data.
		$item->addTaxonomy('Category', $item->category, $item->cat_state, $item->cat_access);

		// Add the language taxonomy data.
		$item->addTaxonomy('Language', $item->language);

		// Get content extras.
		FinderIndexerHelper::getContentExtras($item);

		// Index the item.
		$this->indexer->index($item);
	}
	
	/**
	 * Method to setup the indexer to be run.
	 *
	 * @return  boolean  True on success.
	 *
	 * @since   2.5
	 */
	protected function setup()
	{
		// Load dependent classes.
		require_once JPATH_SITE . '/components/com_«plugin.name»/helpers/route.php';

		return true;
	}
	
	/**
	 * Method to get the SQL query used to retrieve the list of content items.
	 *
	 * @param   mixed  $query  A JDatabaseQuery object or null.
	 *
	 * @return  JDatabaseQuery  A database object.
	 *
	 * @since   2.5
	 */
	protected function getListQuery($query = null)
	{
		$db = JFactory::getDbo();
		
		// Check if we can use the supplied SQL query.
		$query = $query instanceof JDatabaseQuery ? $query : $db->getQuery(true)
			->select('a.id, a.catid, a.title, a.alias, a.url AS link, a.description AS summary')
			->select('a.metakey, a.metadesc, a.metadata, a.language, a.access, a.ordering')
			->select('a.created_by_alias, a.modified, a.modified_by')
			->select('a.publish_up AS publish_start_date, a.publish_down AS publish_end_date')
			->select('a.state AS state, a.created AS start_date, a.params')
			->select('c.title AS category, c.published AS cat_state, c.access AS cat_access');

		// Handle the alias CASE WHEN portion of the query.
		$case_when_item_alias = ' CASE WHEN ';
		$case_when_item_alias .= $query->charLength('a.alias', '!=', '0');
		$case_when_item_alias .= ' THEN ';
		$a_id = $query->castAsChar('a.id');
		$case_when_item_alias .= $query->concatenate(array($a_id, 'a.alias'), ':');
		$case_when_item_alias .= ' ELSE ';
		$case_when_item_alias .= $a_id . ' END as slug';
		$query->select($case_when_item_alias);

		$case_when_category_alias = ' CASE WHEN ';
		$case_when_category_alias .= $query->charLength('c.alias', '!=', '0');
		$case_when_category_alias .= ' THEN ';
		$c_id = $query->castAsChar('c.id');
		$case_when_category_alias .= $query->concatenate(array($c_id, 'c.alias'), ':');
		$case_when_category_alias .= ' ELSE ';
		$case_when_category_alias .= $c_id . ' END as catslug';
		$query->select($case_when_category_alias)

			->from('#__«plugin.name» AS a')
			->join('LEFT', '#__categories AS c ON c.id = a.catid');

		return $query;
	}
	'''
	
	def CharSequence quickiconsEvents(Plugin plugin)'''
	/**
	 * Load the language file on instantiation.
	 *
	 * @var    boolean
	 * @since  3.1
	 */
	protected $autoloadLanguage = true;
	
	public function onGetIcons($context)
	{
		if ($context != $this->params->get('context', 'mod_quickicon') || !JFactory::getUser()->authorise('core.manage', 'com_installer'))
		{
			return;
		}

		JHtml::_('jquery.framework');
		
		//TODO: some code here
		
		$ajax_url = JUri::base() . 'index.php?option=com_installer&view=update&task=update.ajax';
		
		//TODO: some code here
		
		JHtml::_('script', 'plg_quickicon_«plugin.name»/«plugin.name»check.js', false, true);
		
		return array(
			array(
				'link' => '',
				'image' => '',
				'icon' => '',
				'text' => JText::_('PLG_QUICKICON_«plugin.name.toString.toUpperCase»_CHECKING'),
				'id' => 'plg_quickicon_«plugin.name»',
				'group' => 'MOD_QUICKICON_MAINTENANCE'
			)
		);
	}
	'''
	
	def CharSequence searchEvents(Plugin plugin) '''
	/**
	 * Load the language file on instantiation.
	 *
	 * @var    boolean
	 * @since  3.1
	 */
	protected $autoloadLanguage = true;
	
	/**
	 * Determine areas searchable by this plugin.
	 *
	 * @return  array  An array of search areas.
	 *
	 * @since   1.6
	 */
	public function onContentSearchAreas()
	{
		static $areas = array(
			'«plugin.name»' => 'PLG_«plugin.type.toString.toUpperCase»_«plugin.name.toString.toUpperCase»_«plugin.name.toString.toUpperCase»'
		);

		return $areas;
	}
	
	/**
	 * Search content («plugin.name»).
	 *
	 * The SQL must return the following fields that are used in a common display
	 * routine: href, title, section, created, text, browsernav
	 *
	 * @param   string  $text      Target search string.
	 * @param   string  $phrase    Matching option (possible values: exact|any|all).  Default is "any".
	 * @param   string  $ordering  Ordering option (possible values: newest|oldest|popular|alpha|category).  Default is "newest".
	 * @param   mixed   $areas     An array if the search it to be restricted to areas or null to search all areas.
	 *
	 * @return  array  Search results.
	 *
	 * @since   1.6
	 */
	public function onContentSearch($text, $phrase = '', $ordering = '', $areas = null)
	{
		$db = JFactory::getDbo();
		$app = JFactory::getApplication();
		$user = JFactory::getUser();
		$groups = implode(',', $user->getAuthorisedViewLevels());
		
		//not always used
		$searchText = $text;

		if (is_array($areas))
		{
			if (!array_intersect($areas, array_keys($this->onContentSearchAreas())))
			{
				return array();
			}
		}
		
		$sContent = $this->params->get('search_content', 1);
		$sArchived = $this->params->get('search_archived', 1);
		$limit = $this->params->def('search_limit', 50);
		$state = array();
		
		if ($sContent)
		{
			$state[] = 1;
		}

		if ($sArchived)
		{
			$state[] = 2;
		}

		if (empty($state))
		{
			return array();
		}

		$text = trim($text);

		if ($text == '')
		{
			return array();
		}
		
		switch ($phrase)
		{
			case 'exact':
				//TODO: place code here
				break;

			case 'all':
			case 'any':
			default:
				//TODO: place code here
				break;
		}
		
		switch ($ordering)
		{
			case 'alpha':
				//TODO: place code here
				break;

			case 'category':
				//TODO: place code here
				break;

			case 'oldest':
				//TODO: place code here
				break;
			case 'popular':
				//TODO: place code here
				break;
			case 'newest':
				//TODO: place code here
				break;
			default:
				//TODO: place code here
		}
		
		$query = $db->getQuery(true);
		
		// SQLSRV changes.
		$case_when = ' CASE WHEN ';
		$case_when .= $query->charLength('a.alias', '!=', '0');
		$case_when .= ' THEN ';
		$a_id = $query->castAsChar('a.id');
		$case_when .= $query->concatenate(array($a_id, 'a.alias'), ':');
		$case_when .= ' ELSE ';
		$case_when .= $a_id . ' END as slug';

		$case_when1 = ' CASE WHEN ';
		$case_when1 .= $query->charLength('c.alias', '!=', '0');
		$case_when1 .= ' THEN ';
		$c_id = $query->castAsChar('c.id');
		$case_when1 .= $query->concatenate(array($c_id, 'c.alias'), ':');
		$case_when1 .= ' ELSE ';
		$case_when1 .= $c_id . ' END as catslug';
		
		
		$query->select('/*some code here*/')
		->from('#__«plugin.name» AS a')
		//join is not always used
		->join('INNER', '#__categories as c ON c.id = a.catid')
		->where('/*some code here*/')
		->order($order);
		
		// Filter by language.
		if ($app->isSite() && JLanguageMultilang::isEnabled())
		{
			$tag = JFactory::getLanguage()->getTag();
			$query->where('a.language in (' . $db->quote($tag) . ',' . $db->quote('*') . ')')
				->where('c.language in (' . $db->quote($tag) . ',' . $db->quote('*') . ')');
		}
		
		$db->setQuery($query, 0, $limit);
		$rows = $db->loadObjectList();
		
		if ($rows)
		{
			foreach ($rows as $key => $row)
			{
				//TODO: place code here
			}
		}

		return $rows;
	}
	'''
	
	def CharSequence systemEvents(Plugin plugin) '''
	//TODO: place code here
	'''
	
	def CharSequence userEvents(Plugin plugin) '''
	/**
	 * Load the language file on instantiation.
	 *
	 * @var    boolean
	 * @since  3.1
	 */
	protected $autoloadLanguage = true;
	
	//TODO: place code here
	'''
	
	def CharSequence xmlrpcEvents(Plugin plugin) '''
	//TODO: place code here
	'''
	
	def CharSequence xmlContent(Plugin plugin) '''
	<?xml version="1.0" encoding="utf-8"?>
	<extension version="3.1" type="plugin" group="«plugin.type»">
		<name>«plugin.name»</name>
		«plugin.manifest.authors.generate»
		<creationDate>«if (plugin.manifest.creationdate != null) {plugin.manifest.creationdate} 
		else {Calendar::instance.get(Calendar::YEAR)}»</creationDate>		
		<copyright>«if (plugin.manifest.copyright != null) {plugin.manifest.copyright}
			else {"Copyright (C) 2005 - 2014 Open Source Matters. All rights reserved."}»</copyright>
		<license>«if (plugin.manifest.license != null) {plugin.manifest.license}
			else {"GPL 2.0"}»</license>
		<version>«if (plugin.manifest.version != null) {plugin.manifest.version}
			else {"3.0.0"}»</version>
		<description>«if (plugin.manifest.description != null) {plugin.manifest.description}
			else {"Place Description here"}»</description>

		<files>
			<filename plugin="«plugin.name»">«plugin.name».php</filename>
			<filename>index.html</filename>
		</files>

		<languages folder="language">
			«FOR lang:plugin.languages»
				<language tag="«lang.name»">«lang.name».«plugin.name».ini</language>
				<language tag="«lang.name»">«lang.name».plg_«plugin.type»_«plugin.name».sys.ini</language>
			«ENDFOR»
		</languages>
	
		<config>
		<!-- Config Section-->
		</config>
	</extension>
	'''
	
	
} // PluginGenerator
