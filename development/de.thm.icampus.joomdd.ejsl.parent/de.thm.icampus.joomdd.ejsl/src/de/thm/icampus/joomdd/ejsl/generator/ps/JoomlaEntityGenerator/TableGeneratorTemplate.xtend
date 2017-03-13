package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess

class TableGeneratorTemplate {
	
	ExtendedComponent com
	String tName
	ExtendedEntity ent
	new(ExtendedComponent component, ExtendedEntity entity){
		com = component
		tName = entity.name
		ent = entity
	}
	
	public def CharSequence genClassTable() '''
	<?php
	«Slug.generateFileDoc(com, true)»
	
	  // import Joomla table library
	jimport('joomla.database.table');
	use Joomla\Registry\Registry;
	
	/**
	* «tName.toFirstUpper» Table class
	*/
	class «com.name.toFirstUpper»Table«tName.toFirstUpper» extends JTable
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
		«IF ent.getallReferenceToEntity.size > 0»
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
			function __construct(&$db) 
			{
				parent::__construct('#__«com.name.toLowerCase»_«ent.name.toLowerCase»', '«ent.primaryKey.name»', $db);
				$this->initTheForeignTableOption();
			}
 		'''
 public def CharSequence genInitTheForeignTableOption() '''
   public function  initTheForeignTableOption(){
   	«FOR ExtendedReference ref : ent.getallReferenceToEntity»
   	$temp_«ent.getallReferenceToEntity.indexOf(ref)» = array(
   	"type" => "«ref.extendedFromEntity.name.toString.toFirstUpper»",
   	"prefix" => "«com.name.toFirstUpper»Table",
   	"foreignkey" => array(«Slug.transformAttributeListInString('''"''',"",ref.attribute, ', ')»),
   	"refkey" => array(«Slug.transformAttributeListInString('''"''',"",ref.attributerefereced, ', ')»),
   	"name" => "#__«com.name.toLowerCase»_«ref.extendedFromEntity.name.toLowerCase»",
   	"foreignPrimaryKeys" => '«Slug.getPrimaryKeys(ref.extendedToEntity).name.toLowerCase»'
   	);
   	array_push($this->foreigntableOption, $temp_«ent.getallReferenceToEntity.indexOf(ref)»);
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
        $task = JFactory::getApplication()->getString('task', '');
        if(($task == 'save' || $task == 'apply') && (!JFactory::getUser()->authorise('core.edit.state','«Slug.nameExtensionBind("com", com.name).toLowerCase».«tName.toLowerCase».'.$array['id']) && $array['state'] == 1)){
            $array['state'] = 0;
        }
        if($array['«ent.primaryKey.name»'] == 0){
            $array['created_by'] = JFactory::getUser()->id;
        }
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
        if (property_exists($this, 'ordering') && $this->«ent.primaryKey.name» == 0)
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
                $allForeignKeys = $this->loadAllPrimaryKeyofRef($pk, $dbtable['refkey']
                    , $dbtable['name'], $dbtable['foreignkey'],$dbtable["foreignId"]);
                foreach($allForeignKeys as $keyOf){
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
   public function loadAllPrimaryKeyofRef($pk, $keylist, $foreigntable, $foreignkeys,$foreignId){
   	
        $this->load($pk);
        $query = $this->_db->getQuery(true);
       	$query->select($foreignId)
      	         ->from("#__" . $foreigntable);
      	     foreach($keylist as $index=>$value){
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
	
	def dogenerate(String path, IFileSystemAccess access) {
		access.generateFile(path + "/" + this.ent.name + ".php", genClassTable)
	}
 
 
 
 
 
 
 
 
 
 
 
 
}