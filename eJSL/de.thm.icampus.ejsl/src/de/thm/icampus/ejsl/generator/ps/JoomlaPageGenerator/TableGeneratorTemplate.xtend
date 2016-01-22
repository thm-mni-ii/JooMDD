package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.eJSL.Reference

class TableGeneratorTemplate {
	
	Component com
	String tName
	Entity ent
	new(Component component, String tableName, Entity entity){
		com = component
		tName = tableName
		ent = entity
	}
	
	public def CharSequence genClassTable() '''
	<?php
	«Slug.generateFileDoc(com, true)»
	
	  // import Joomla table library
	jimport('joomla.database.table');
	
	/**
	* «tName.toFirstUpper» Table class
	*/
	class «com.name.toFirstUpper»Table«tName.toFirstUpper» extends JTable
	{
		public $foreigntableOption = array();
		
		«genContructor»	
		«genBind»
		«genCheck»
		«genGetAssetName»
		«genGetAssetParentID»
		«genInitTheForeignTableOption»
		«genLoadAllPrimaryKeys»
		«genPublish»
	}
	'''
	
 public def CharSequence genContructor()'''
			/**
			* Constructor
			*
			* @param object Database connector object
			*/
			function __construct(&$db) 
			{
				parent::__construct('#__«com.name.toLowerCase»_«ent.name.toLowerCase»', 'id', $db);
				$this->initTheForeignTableOption();
			}
 		'''
 public def CharSequence genInitTheForeignTableOption() '''
   public function  initTheForeignTableOption(){
   	«FOR Reference ref : ent.references»
   	$temp_«ent.references.indexOf(ref)» = array(
   	"type" => "«ref.entity.name.toFirstUpper»",
   	"prefix" => "«com.name.toFirstUpper»Table",
   	"refkey" => "«ref.attribute.name»",
   	"foreignkey" => "«ref.attributerefereced.name»",
   	"name" => "#__«com.name.toLowerCase»_«ref.entity.name.toLowerCase»"
   	);
   	array_push($this->foreigntableOption, $temp_«ent.references.indexOf(ref)»);
        «ENDFOR»
  }
 '''
 public def genBind()'''
  /**
     * Overloaded bind function to pre-process the params.
     *
     * @param    array        Named array
     *
     * @return    null|string    null is operation was satisfactory, otherwise returns an error
     * @see        JTable:bind
     * @since      1.5
     */
    public function bind($array, $ignore = '')
    {


        $input = JFactory::getApplication()->input;
        $task = $input->getString('task', '');
        if(($task == 'save' || $task == 'apply') && (!JFactory::getUser()->authorise('core.edit.state','«Slug.nameExtensionBind("com", com.name).toLowerCase».«tName.toLowerCase».'.$array['id']) && $array['state'] == 1)){
            $array['state'] = 0;
        }
        if($array['id'] == 0){
            $array['created_by'] = JFactory::getUser()->id;
        }

        //Support for file field: file
        $input = JFactory::getApplication()->input;


        if (isset($array['params']) && is_array($array['params']))
        {
            $registry = new JRegistry();
            $registry->loadArray($array['params']);
            $array['params'] = (string) $registry;
        }

        if (isset($array['metadata']) && is_array($array['metadata']))
        {
            $registry = new JRegistry();
            $registry->loadArray($array['metadata']);
            $array['metadata'] = (string) $registry;
        }

        //Bind the rules for ACL where supported.
        if (isset($array['rules']) && is_array($array['rules']))
        {
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
        if (property_exists($this, 'ordering') && $this->id == 0)
        {
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
	 * @see JTable::_getAssetName
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
	 * @see JTable::_getAssetParentId
	 */
	protected function _getAssetParentId(JTable $table = null, $id = null)
	{
		// We will retrieve the parent-asset from the Asset-table
		$assetParent = JTable::getInstance('Asset');
		// Default: if no asset-parent can be found we take the global asset
		$assetParentId = $assetParent->getRootId();
		// The item has the component as asset-parent
		$assetParent->loadByName('«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		// Return the found asset-parent-id
		if ($assetParent->id)
		{
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
        if(isset($this->foreigntableOption)){
            foreach($this->foreigntableOptio as $key => $dbtable){
                $instance_table = JTable::getInstance($dbtable['type'], $dbtable['prefix']);
                $field = $this->$dbtable['refkey'];
                $allForeignKeys = $this->loadAllPrimaryKeyofRef($pk, $dbtable['refkey']
                    , $dbtable['name'], $dbtable['foreignkey']);
                foreach($allForeignKeys as $keyOf){
                    $result = $instance_table->delete($keyOf);
                }
            }
        }


        $result = parent::delete($pk);

        return $result;
    }
 '''
 
 public def CharSequence genLoadAllPrimaryKeys()'''
   public function loadAllPrimaryKeyofRef($pk, $key, $foreigntable, $foreignkeys){
        $this->load($pk);

        $fieldvalue =  $this->$key;

        $query = $this->_db->getQuery(true);
        $query->select('id')
            ->from("#__" . $foreigntable)
            ->where($this->_db->quoteName($foreignkeys) . "=" .
                $this->_db->quoteName($fieldvalue));
        $this->_db->setQuery($query);
        $result = $this->_db->loadObjectList();
        return $result;
    }
 '''
 
public def CharSequence genPublish()'''
  public function publish($pks = null, $state = 1, $userId = 0)
    {

        $k = $this->_tbl_keys;

        if (!is_null($pks))
        {
            foreach ($pks AS $key => $pk)
            {
                if (!is_array($pk))
                {
                    $pks[$key] = array($this->_tbl_key => $pk);
                }
            }
        }

        $userId = (int) $userId;
        $state  = (int) $state;

        // If there are no primary keys set check to see if the instance key is set.
        if (empty($pks))
        {
            $pk = array();

            foreach ($this->_tbl_keys AS $key)
            {
                if ($this->$key)
                {
                    $pk[$this->$key] = $this->$key;
                }
                // We don't have a full primary key - return false
                else
                {
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
 
 
 
 
 
 
 
 
 
 
 
 
}