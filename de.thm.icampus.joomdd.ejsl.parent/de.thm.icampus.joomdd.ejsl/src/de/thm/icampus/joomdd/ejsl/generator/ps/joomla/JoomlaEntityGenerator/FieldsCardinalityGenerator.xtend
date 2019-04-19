package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Query
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Select
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Column
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Table
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedReferenceImpl

/**
 * This class contains the templates to generate the fiel cardinalities.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class FieldsCardinalityGenerator extends FieldsGenerator {
    
	ExtendedReference foreignReference
	
	new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from) {
		super(ref, component, from)
		setForeignElemente()
	}
	
	private def setForeignElemente(){
		foreignReference = new ExtendedReferenceImpl((mainRef.entity.references.filter[t | t.entity.name != entFrom.instance.name]).get(0), mainRef.entity)
	}
	
	public override CharSequence genRefrenceField()'''
		<?php
		«Slug.generateFileDocAdmin(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text", "Uri", "FormField", "Factory"))»
		
		class JFormField«nameField.toFirstUpper» extends FormField
		{
		    protected $referenceStruct = array("table" => "«Slug.databaseName(com.name, entFrom.name)»",
		        "mappingTable"=> "«Slug.databaseName(com.name,mainRef.entity.name)»",
		        "foreignTable"=> "«Slug.databaseName(com.name,foreignReference.entity.name)»");
		    protected $keysAndForeignKeys= array( "table" => array(
		        "«mainRef.referenceAttribute»" => "«mainRef.referencedAttribute»",
		        "«mainRef.referenceIDAttribute»" => "«mainRef.referencedIDAttribute»"
		    ),"foreignTable" => array(
		        "«foreignReference.referenceAttribute»" => "«foreignReference.referencedAttribute»",
		        "«foreignReference.referenceIDAttribute»" => "«foreignReference.referencedIDAttribute»"
		    ));
		
		    «genGetInput»
		
		    «genGetAllData»
		
		    «genAttributValue»
		
		    «genGetDataItem»
		
		    «getAllReferenceData»
		
		    «genGenerateJsonValue»
		
		    «gengenerateStringValue»
		}
	'''
	
	def private genGetInput()'''
		protected function getInput()
		{
		    $html = array();
		    $document = Factory::getDocument();
		    $document->addScript(Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/setmultipleforeignkeys.js');
		    $input = Factory::getApplication()->input;
		    $«entFrom.primaryKey.name» = intval($input->get('«entFrom.primaryKey.name»'));
		    if (empty($«entFrom.primaryKey.name»)) {
		        $alldata = $this->getAllData();
		        $html[] = "<select
		                       onchange='setMultipleValueForeignKeys(this)'
		                       generated='true'
		                       multiple
		                       id='" . $this->id . "select'
		                       class='form-control'
		                   >";
		        foreach ($alldata as $data) {
		            $html[] = "<option value='". $this->generateJsonValue($data) ."'>"
		            . $this->generateStringValue($data) ."</option>";
		        }
		        $html[]="</select>";
		        $html[]="<input type='hidden' value='' name='" . $this->name. "' id='" . $this->id. "'/>";
		        return implode($html);
		    }
		    $data_item = $this->getDataItem($«entFrom.primaryKey.name»);
		    $referenceData = $this->getAllReferenceData($data_item);
		    $html[] = "<select
		                   multiple='true'
		                   onchange='setMultipleValueForeignKeys(this)'
		                   generated='true'
		                   id='" . $this->id . "select'
		                   class='form-control'
		               >";
		
		    foreach ($referenceData as $reference) {
		        $html[] = "<option
		                       $reference->selected
		                       value='". $this->generateJsonValue($reference)."'>" .
		                       $this->generateStringValue($reference) .
		                   "</option>";
		    }
		    $html[]="</select>";
		    $html[]="<input
		                 type='hidden'
		                 value='" . $this->attributValue($referenceData). "'
		                 name='" . $this->name. "'
		                 id='" . $this->id. "'
		             />";
		    return implode($html);
		}
	'''
	def private genGetAllData() {
	    var query = new Query
	    query.mainTable = new Table('''$foreignTable''', '''b''')
	    for(attribute : foreignReference.attribute) {
	        var column = new Column(query.mainTable.alias, foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(attribute)).name)
	        var select = new Select(column, attribute.name)
            query.addToMainSelect(select)
	    }
	    
	    var whereColumn = new Column(query.mainTable.alias, '''state''')
	    var whereStatement = '''«whereColumn» = 1'''
	    
    	return '''
    		protected function getAllData()
    		{
    		    $foreignTable = $this->referenceStruct['foreignTable'];
    		    $db = Factory::getDbo();
    		    $queryALL = $db->getQuery(true);
    		    $queryALL->select("«query.mainSelect»")
    		        ->from("«query.mainTable»")
    		        ->where("«whereStatement»")
    		        ->order("«foreignReference.attributerefereced.get(0).name»  ASC");
    		    $db->setQuery($queryALL);
    		    return $db->loadObjectList();
    		}
    	'''
	}
	def private genAttributValue()'''
		protected function attributValue($referenceData)
		{
		    $values = array();
		    foreach ($referenceData as $reference) {
		        if (!empty($reference->selected) && !in_array($reference->id, $values)) {
		            array_push($values, $reference->id);
		        }
		    }
		    return json_encode($values);
		}
	'''
	
	// @todo: Use query classes from DatabaseQuery
	def private getAllReferenceData()'''
		protected function getAllReferenceData($item)
		{
		    $db = Factory::getDbo();
		    $query = $db->getQuery(true);
		
		    $query->select("B.«Slug.getPrimaryKey(mainRef.destinationEntity).name»,«FOR foreignAttr : foreignReference.attribute»A.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» as «foreignAttr.name» ,«ENDFOR»
		    (case when B.«Slug.getPrimaryKey(mainRef.destinationEntity).name» <> 0 then 'selected' else ' ' end) as selected ")
		        ->from($this->referenceStruct['foreignTable'] . " as A")
		        ->leftJoin("(select * from " . $this->referenceStruct['mappingTable'] . " as C 
		            where C.«mainRef.referencedIDAttribute» = '$item->«mainRef.referenceIDAttribute»') as B 
		            on A.« foreignReference.referencedIDAttribute» = B.«foreignReference.referenceIDAttribute»")
		        ->where("A.state = 1")
		        ->order("A.«foreignReference.attributerefereced.get(0).name»");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}
	'''
	def private genGetDataItem() {
	    var query = new Query
	    var selectColumn = new Column('''*''')
	    query.addToMainSelect(new Select(selectColumn))
        query.mainTable = new Table('''$table''')
        
        var whereColumn = new Column(entFrom.primaryKey.name)
        var whereStatement = '''«whereColumn» = $«entFrom.primaryKey.name»'''
        
    	return '''
    		protected function getDataItem($«entFrom.primaryKey.name»)
    		{
    		    $db = Factory::getDbo();
    		    $table = $this->referenceStruct['table'];
    		    $query = $db->getQuery(true);
    		    $query->select("«query.mainSelect»")
    		           ->from("«query.mainTable»")
    		           ->where("«whereStatement»");
    		    $db->setQuery($query);
    		    return $db->loadObject();
    		}
    	'''
	}
	
	def private genGenerateJsonValue()'''
		public function generateJsonValue($data)
		{
		    $result  = array();
		    foreach ($this->keysAndForeignKeys["foreignTable"] as $key => $value) {
		        if (!array_key_exists("jform_$key", $result)) {
		            $result["jform_$key"] = array();
		        }
		        array_push($result["jform_$key"], $data->{$key});
		    }
		    return json_encode($result);
		}
	'''
	def private gengenerateStringValue()'''
		public function generateStringValue($data)
		{
		    $result = array();
		    $result[] = $data->{array_keys($this->keysAndForeignKeys["foreignTable"])[0]} . " ";

		    return implode($result);
		}
	'''
	override dogenerate(String path, IFileSystemAccess access) {
		if(this.mainRef !== null) {
            access.generateFile(newArrayList(path, getnameField.toLowerCase + ".php").join("/"), genRefrenceField)
		}
	}
}