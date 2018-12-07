package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess

/**
 * This class contains the templates to generate the tables.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class TableGeneratorTemplate {
	
	ExtendedComponent com
	String tName
	ExtendedEntity ent
	new(ExtendedComponent component, ExtendedEntity entity) {
		com = component
		tName = entity.name
		ent = entity
	}
	
	public def CharSequence genClassTable() '''
		<?php
		«Slug.generateFileDoc(com)»
		
		«Slug.generateNamespace(com.name, "Administrator", "Table")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Table", "Registry", "Factory"))»
		
		/**
		 * «tName.toFirstUpper» Table class
		 */
		class «tName.toFirstUpper»Table extends Table
		{
		    public $foreigntableOption = array();
		
		    «genContructor»
		
		    «genBind»
		
		    «genCheck»
		
		    «genReset»
		
		    «genGetAssetName»
		
		    «genGetAssetParentID»
		
		    «genInitTheForeignTableOption»
		
		    «genLoadAllPrimaryKeys»
		
		    «genPublish»
		
		    «IF ent.getAllExtendedReferencesToEntity.size > 0»
		    «genDelete»
		    «ENDIF»
		}
	'''
	
 	public def CharSequence genContructor()'''
 		/**
 		* Constructor
 		*
 		* @param object Database connector object
 		*/
 		public function __construct(&$db) 
 		{
 		    parent::__construct('#__«com.name.toLowerCase»_«ent.name.toLowerCase»', '«ent.primaryKey.name»', $db);
 		    $this->initTheForeignTableOption();
 		}
 	'''
 	
 	public def CharSequence genInitTheForeignTableOption() '''
 		public function initTheForeignTableOption()
 		{
 		    «FOR ExtendedReference ref : ent.getAllExtendedReferencesToEntity»
 		    $temp_«ent.getAllExtendedReferencesToEntity.indexOf(ref)» = array(
 		        "type" => "«ref.sourceEntity.name.toString.toFirstUpper»",
 		        "prefix" => "«com.name.toFirstUpper»Table",
 		        "foreignkey" => array(«Slug.transformAttributeListInString('''"''',"",ref.attribute, ', ')»),
 		        "refkey" => array(«Slug.transformAttributeListInString('''"''',"",ref.attributerefereced, ', ')»),
 		        "name" => "#__«com.name.toLowerCase»_«ref.sourceEntity.name.toLowerCase»",
 		        "foreignPrimaryKeys" => '«Slug.getPrimaryKeys(ref.destinationEntity).name.toLowerCase»'
 		    );
 		    array_push($this->foreigntableOption, $temp_«ent.getAllExtendedReferencesToEntity.indexOf(ref)»);
 		    «ENDFOR»
 		}
 	'''
 	
	public def genBind()'''
		/**
		 * Overloaded bind function to pre-process the params.
		 *
		 * @param    array        Named array
		 *
		 * @return   null|string  null is operation was satisfactory, otherwise returns an error
		 * @see      JTable:bind
		 * @since    1.5
		 */
		public function bind($array, $ignore = '')
		{
		    $input = Factory::getApplication()->input;
		    $task = $input->getString('task', '');
		    if (($task == 'save' || $task == 'apply') && (!Factory::getUser()->authorise('core.edit.state','«Slug.nameExtensionBind("com", com.name).toLowerCase».«tName.toLowerCase».'.$array['id']) && $array['state'] == 1)) {
		        $array['state'] = 0;
		    }
		    if ($array['«ent.primaryKey.name»'] == 0) {
		        $array['created_by'] = Factory::getUser()->id;
		    }
		
		    //Support for file field: file
		    $input = Factory::getApplication()->input;
		
		    if (isset($array['params']) && is_array($array['params'])) {
		        $registry = new Registry();
		        $registry->loadArray($array['params']);
		        $array['params'] = (string) $registry;
		    }
		
		    if (isset($array['metadata']) && is_array($array['metadata'])) {
		        $registry = new Registry();
		        $registry->loadArray($array['metadata']);
		        $array['metadata'] = (string) $registry;
		    }
		
		    //Bind the rules for ACL where supported.
		    if (isset($array['rules']) && is_array($array['rules'])) {
		        $this->setRules($array['rules']);
		    }

			return parent::bind($array, $ignore);
		}
	'''
 
	public def genCheck()'''
		/**
		* Rewrite check function
		*/
		public function check()
		{
		    if (property_exists($this, 'ordering') && $this->«ent.primaryKey.name» == 0) {
		        $this->ordering = self::getNextOrder();
		    }
		
		    return parent::check();
		}
	'''
 
	public def CharSequence genGetAssetName()'''
		/**
		 * Define a namespaced asset name for inclusion in the #__assets table
		 * @return string The asset name
		 *
		 * @see Table::_getAssetName
		 */
		protected function _getAssetName()
		{
		    $k = $this->_tbl_key;
		    return '«Slug.nameExtensionBind("com",com.name).toLowerCase».«tName.toLowerCase».' . (int) $this->$k;
		}
	'''
 
	public def CharSequence genGetAssetParentID()'''
		/**
		 * Returns the parent asset's id. If you have a tree structure, retrieve the parent's id using the external key field
		 *
		 * @see Table::_getAssetParentId
		 */
		protected function _getAssetParentId(Table $table = null, $id = null)
		{
		    // We will retrieve the parent-asset from the Asset-table
		    $assetParent = Table::getInstance('Asset');
		    // Default: if no asset-parent can be found we take the global asset
		    $assetParentId = $assetParent->getRootId();
		    // The item has the component as asset-parent
		    $assetParent->loadByName('«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		    // Return the found asset-parent-id
		    if ($assetParent->id) {
		        $assetParentId = $assetParent->id;
		    }
		    return $assetParentId;
		}
	'''
	
	public def CharSequence genDelete()'''
		/**
		* @param  mixed   $pk  An optional primary key value to delete.  If not set the instance property value is used.
		* @return bool
		*/
		public function delete($pk = null)
		{
		    $item = $this->load($pk);
		    if (isset($this->foreigntableOption)) {
		        foreach ($this->foreigntableOptio as $key => $dbtable) {
		            $instance_table = Table::getInstance($dbtable['type'], $dbtable['prefix']);
		            $allForeignKeys = $this->loadAllPrimaryKeyofRef($pk, $dbtable['refkey']
		                , $dbtable['name'], $dbtable['foreignkey'],$dbtable["foreignId"]);
		            foreach ($allForeignKeys as $keyOf) {
		                $result = $instance_table->delete($keyOf);
		            }
		        }
		    }
		
		    $result = parent::delete($pk);
		    return $result;
		}
	'''
	
	public def CharSequence genReset()'''
		public function reset()
		{
		    $this->«ent.primaryKey.name» = 0;
		    parent::reset();
		}
	'''
	
	public def CharSequence genLoadAllPrimaryKeys()'''
		public function loadAllPrimaryKeyofRef($pk, $keylist, $foreigntable, $foreignkeys,$foreignId)
		{
		    $this->load($pk);
		    $query = $this->_db->getQuery(true);
		    $query->select($foreignId)
		        ->from("#__" . $foreigntable);
		    foreach ($keylist as $index=>$value) {
		        $query->where($this->_db->quoteName($foreignkeys[$index]) . "=" .
		        $this->_db->quoteName($this->$value));
		    }
		    $this->_db->setQuery($query);
		    $result = $this->_db->loadObjectList();
		    return $result;
		}
	'''
 
	public def CharSequence genPublish()'''
		public function publish($pks = null, $state = 1, $userId = 0)
		{
		    $k = $this->_tbl_keys;
		    if (!is_null($pks)) {
		        foreach ($pks AS $key => $pk)
		        {
		            if (!is_array($pk)) {
		                $pks[$key] = array($this->_tbl_key => $pk);
		            }
		        }
		    }

		    $userId = (int) $userId;
		    $state  = (int) $state;
		
		    // If there are no primary keys set check to see if the instance key is set.
		    if (empty($pks)) {
		        $pk = array();
		        foreach ($this->_tbl_keys AS $key) {
		            if ($this->$key) {
		                $pk[$this->$key] = $this->$key;
		            } else {
		                return false;
		            }
		        }
		        $pks = array($pk);
		    }
		
		    foreach ($pks AS $pk) {
		        // Update the state state for rows with the given primary keys.
		        $query = $this->_db->getQuery(true)
		            ->update($this->_tbl)
		            ->set('state = ' . (int)$state);
		        $this->appendPrimaryKeys($query, $pk);
		
		        $this->_db->setQuery($query);
		        $this->_db->execute();
		    }
		    return parent::publish($pks, $state, $userId);
		}
	'''
	
	def dogenerate(String path, IFileSystemAccess access) {
		access.generateFile(path + "/" + this.ent.name.toFirstUpper + "Table.php", genClassTable)
	}
}