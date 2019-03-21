package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import org.eclipse.xtext.generator.IFileSystemAccess
import java.io.File

/**
 * This class contains the templates to generate the fiel cardinalities.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class FieldsCardinalityGenerator extends FieldsGenerator {
	
	Reference foreignReference
	
	new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from) {
		super(ref, component, from)
		setForeignElemente()
	}
	
	private def setForeignElemente(){
		foreignReference = (mainRef.entity.references.filter[t | t.entity.name != entFrom.instance.name]).get(0)
	}
	
	public override CharSequence genRefrenceField()'''
		<?php
		«Slug.generateFileDoc(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("Text", "Uri", "FormField", "Factory"))»
		
		class JFormField«nameField.toFirstUpper» extends FormField
		{
		    protected $referenceStruct = array("table" => "«Slug.databaseName(com.name, entFrom.name)»",
		        "mappingTable"=> "«Slug.databaseName(com.name,mainRef.entity.name)»",
		        "foreignTable"=> "«Slug.databaseName(com.name,foreignReference.entity.name)»");
		    protected $keysAndForeignKeys= array( "table" => array(
		        «FOR attr : mainRef.extendedAttributes»
		        «IF attr != mainRef.extendedAttributes.last»
		        "«attr.name»" => "«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name»",
		        «ELSE»
		        "«attr.name»" => "«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name»"
		        «ENDIF»
		        «ENDFOR»
		    ),"foreignTable" => array(
		        «FOR attr : foreignReference.attribute»
		        «IF attr != foreignReference.attribute.last»
		        "«attr.name»" => "«foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(attr)).name»",
		        «ELSE»
		        "«attr.name»" => "«foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(attr)).name»"
                «ENDIF»
                «ENDFOR»
		    ));
		
		    «genGetInput»
		
		    «genGetAllData»
		
		    «genAttributValue»
		
		    «genGetData_item»
		
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
	def private genGetAllData()'''
		protected function getAllData()
		{
		    $db = Factory::getDbo();
		    $queryALL = $db->getQuery(true);
		    $queryALL->select("
		    «FOR foreignAttr : foreignReference.attribute»
		    «IF foreignAttr != foreignReference.attribute.last»
		    b.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» as «foreignAttr.name» , 
		    «ELSE»
		    b.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» as «foreignAttr.name»
		    «ENDIF»
		    «ENDFOR»")
		        ->from($this->referenceStruct["foreignTable"] . ' as b')
		        ->where("state = 1")
		        ->order(" «foreignReference.attributerefereced.get(0).name»  ASC");
		    $db->setQuery($queryALL);
		    return $db->loadObjectList();
		}
	'''
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
	def private getAllReferenceData()'''
		protected function getAllReferenceData($item)
		{
		    $db = Factory::getDbo();
		    $query = $db->getQuery(true);
		
		    $query->select("B.«Slug.getPrimaryKeys(mainRef.destinationEntity).name»,«FOR foreignAttr : foreignReference.attribute»A.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» as «foreignAttr.name» ,«ENDFOR»
		    (case when B.«Slug.getPrimaryKeys(mainRef.destinationEntity).name» <> 0   then 'selected' else ' ' end) as selected ")
		        ->from($this->referenceStruct["foreignTable"] . " as A")
		        ->leftJoin("(select * from " . $this->referenceStruct["mappingTable"] . " as C where 
		            «FOR attr : mainRef.extendedAttributes»
		            «IF attr != mainRef.extendedAttributes.last»
		            C.«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name» = '$item->«attr.name»' AND 
		            «ELSE»
		            C.«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name» = '$item->«attr.name»'
		            «ENDIF»
		            «ENDFOR»
		            ) as B on 
		            «FOR foreignAttr : foreignReference.attribute»
		            «IF foreignAttr != foreignReference.attribute.last»
		            A.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» = B.«foreignAttr.name» AND 
		            «ELSE»
		            A.« foreignReference.attributerefereced.get(foreignReference.attribute.indexOf(foreignAttr)).name» = B.«foreignAttr.name»")
		            «ENDIF»
		            «ENDFOR»
		        ->where("A.state = 1")
		        ->order("A.«foreignReference.attributerefereced.get(0).name»");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}
	'''
	def private genGetData_item()'''
		protected function getDataItem($«entFrom.primaryKey.name»)
		{
		    $db = Factory::getDbo();
		    $query = $db->getQuery(true);
		    $query->select("*")->from($this->referenceStruct["table"])
		        ->where("«entFrom.primaryKey.name» = " . $«entFrom.primaryKey.name»);
		    $db->setQuery($query);
		    return $db->loadObject();
		}
	'''
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