package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import java.io.File
import org.eclipse.xtext.generator.IFileSystemAccess
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair

/**
 * This class contains the templates to generate the view fields.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class FieldsGenerator {

	public ExtendedReference mainRef
	public ExtendedComponent com
	public String nameField
	public ExtendedEntity entFrom
	private ExtendedDetailPageField field

	public new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from) {
		mainRef = ref
		com = component
		entFrom = from
		nameField = from.name + "to" + ref.entity.name
	}

	public new(ExtendedComponent component, ExtendedEntity from) {
		com = component
		entFrom = from
	}
	public new(ExtendedComponent component) {
		com = component
		
	}
	public new(ExtendedDetailPageField field, ExtendedComponent component) {
		com = component
		nameField = field.type
		this.field = field
		
	}

	public def String getnameField() {
		return nameField
	}

	public def CharSequence genRefrenceField() '''
		<?php
		«Slug.generateFileDoc(com)»

		«Slug.generateRestrictedAccess()»

		«Slug.generateUses(newArrayList("Text", "FormField", "Factory", "Uri"))»

		class JFormField«nameField.toFirstUpper» extends FormField
		{
		    protected $referenceStruct = array("table" => "«Slug.databaseName(com.name, entFrom.name)»",
		        "foreignTable"=> "«Slug.databaseName(com.name,mainRef.entity.name)»");
		    protected $keysAndForeignKeys= array(
		        «FOR attr : mainRef.extendedAttributes»
		        «IF attr != mainRef.extendedAttributes.last»
		        "«attr.name.toLowerCase»" => "«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name.toLowerCase»",
		        «ELSE»
		        "«attr.name.toLowerCase»" => "«mainRef.referencedExtendedAttributes.get(mainRef.extendedAttributes.indexOf(attr)).name.toLowerCase»"
                «ENDIF»
		        «ENDFOR»);

		    «genGetInput»

		    «genGetAllData»

		    «genGetReferencedata»
		
		    «genGetData_item»
		
		    «genGenerateJsonValue»
		
		    «gengenerateStringValue»
		}
	'''
	public def CharSequence genEmptyField()'''
	<?php
	«Slug.generateFileDoc(com)»
	
	«Slug.generateRestrictedAccess()»
	
	«Slug.generateUses(newArrayList("FormField"))»
	
	class JFormField«nameField.toFirstUpper» extends JFormField
	{
	    protected function getInput()
	    {
	        $html[]="<input type='text' value='" . $this->value. "' name='" . $this->name. "' id='" . $this->«field.attribute.name.toLowerCase». "'
	        «FOR KeyValuePair kv: field.attributes»
	        «kv.name» = '«kv.value»'
	        «ENDFOR»
	        />";
	        return implode($html);
	    }
	}
	'''
    def private genGetData_item()'''
	protected function getData_item($«entFrom.primaryKey.name»)
	{
	    $db = Factory::getDbo();
	    $query = $db->getQuery(true);
	    $query->select("*")->from($this->referenceStruct["table"])
	        ->where("«entFrom.primaryKey.name» = " . $«entFrom.primaryKey.name»);
	    $db->setQuery($query);
	    return $db->loadObject();
	}
	
	'''
	private def CharSequence genGetInput() '''
		/**
		 * Method to get the field input markup.
		 *
		 * @return	string	The field input markup.
		 * @since	1.6
		 */
		protected function getInput()
		{
		    $html = array();
		    $document = Factory::getDocument();
		    $document->addScript( Uri::root() . '/media/«Slug.nameExtensionBind("com",com.name).toLowerCase»/js/setForeignKeys.js');
		    $input = Factory::getApplication()->input;
		    $«entFrom.primaryKey.name» = intval($input->get('«entFrom.primaryKey.name»'));
		    if (empty($«entFrom.primaryKey.name»)) {
		        $alldata = $this->getAllData();
		        $html[] = "<select required onchange='setValueForeignKeys(this)' id='" . $this->«entFrom.primaryKey.name» . "select'  class='form-control' >";
		        $html[] = "<option>". Text::_("JOPTION_SELECT_«mainRef.extendedAttributes.get(0).name.toUpperCase»"). "</option>";
		        foreach ($alldata as $data) {
		            $html[] = "<option  value='". $this->generateJsonValue($data) ."'>"
		            . $this->generateStringValue($data) ."</option>";
		        }
		        $html[]="</select>";
		        $html[]="<input type='hidden' value='' name='" . $this->name. "' id='" . $this->id . "'/>";
		        return implode($html);
		    }
		    $data = $this->getData_item($«entFrom.primaryKey.name»);
		    $selectData = $this->getReferencedata($data);
		    $html[] = "<select required onchange='setValueForeignKeys(this)' id='" . $this->«entFrom.primaryKey.name» . "select' class='form-control' name='" . $this->name. "select'>";
		    $html[] = "<option>". Text::_("JOPTION_SELECT_«mainRef.extendedAttributes.get(0).name.toUpperCase»"). "</option>";
		    foreach ($selectData as $selected) {
		        $html[] = "<option $selected->selected value='". $this->generateJsonValue($selected) ."'>"
		        . $this->generateStringValue($selected) ."</option>";
		    }

		    $html[]="</select>";
		    $html[]="<input type='hidden' value='" . $this->value. "' name='" . $this->name. "' id='" . $this->id. "'/>";
		    return implode($html);
		}
	'''

	private def CharSequence genGetReferencedata() '''
	/**
	*Read Selected  Items
	*
	*/
	protected function getReferencedata($data)
	{
	    $db = Factory::getDbo();
	    $query = $db->getQuery(true);
	    $query->select("distinct (case when 
	        «FOR ExtendedAttribute attr: mainRef.referencedExtendedAttributes»
	        «IF attr != mainRef.referencedExtendedAttributes.last»
	        b.«attr.name» = '$data->«mainRef.extendedAttributes.get(mainRef.referencedExtendedAttributes.indexOf(attr)).name»' and 
	        «ELSE»
	        b.«attr.name» = '$data->«mainRef.extendedAttributes.get(mainRef.referencedExtendedAttributes.indexOf(attr)).name»'
	        «ENDIF»
	        «ENDFOR»
	        then 'selected'
	        else ' ' end) as selected");
	    foreach ($this->keysAndForeignKeys as $key =>$value) {
	        $query->select(" b.$value");
	    }

	    $query->from( $this->referenceStruct["foreignTable"] . " as b ")
	        ->where("b.state = 1")
	        ->order("b.«mainRef.attributerefereced.get(0).name.toLowerCase»" . " ASC");
	    $db->setQuery($query);
	    return $db->loadObjectList();
	}
	'''

	private def CharSequence genGetAllData() '''
		protected function getAllData()
		{
		    $db = Factory::getDbo();
		    $query = $db->getQuery(true);
		    $query->select("«Slug.transformAttributeListInString("b.",mainRef.attributerefereced,',')»")
		        ->from($this->referenceStruct["foreignTable"] . " as b")
		        ->where("b.state = 1")
		        ->order("b.«mainRef.attributerefereced.get(0).name.toLowerCase»" . " ASC");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}
	'''

	private def CharSequence genGenerateJsonValue() '''
		public function generateJsonValue($data)
		{
		    $result  = array();
		    foreach ($this->keysAndForeignKeys as $key=>$value) {
		        $result["jform_$key"] = $data->{$value};
		    }
		    return json_encode($result);
		}
	'''

	private def CharSequence gengenerateStringValue() '''
		public function generateStringValue($data)
		{
		    $result = array();
		
		    $result[] = $data->{array_values($this->keysAndForeignKeys)[0]} . " ";
		
		    return implode($result);
		}
	'''
	
	public def CharSequence genFieldsForEntity()'''
		<?php
		«Slug.generateFileDoc(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("FormHelper", "Factory"))»
		
		FormHelper::loadFieldClass('list');
		
		class JFormField«entFrom.name.toFirstLower» extends JFormFieldList{
		    protected $table = "«Slug.databaseName(com.name, entFrom.name)»";
		    
		    «genGetOptionsForEntity»
		    
		    «genGetAllDataForEntity»
		}
	'''
	
	private def genGetOptionsForEntity() '''
		protected function getOptions()
		{
		    $valueColumn = $this->getAttribute('valueColumn');
		    $textColumn = $this->getAttribute('textColumn');
		    return  array_merge(parent::getOptions(),$this->getAllData($valueColumn, $textColumn));
		}
	'''
	
	private def genGetAllDataForEntity() '''
	protected function getAllData($valueColumn, $textColumn)
	{
	    $dbo = Factory::getDbo();
	    $query = $dbo->getQuery(true);
	    $query->select("DISTINCT $valueColumn as value, $textColumn as text")
	        ->from("$this->table AS «entFrom.name.toLowerCase»")
	        «FOR ExtendedReference ref:entFrom.allExtendedReferences »
	        ->join('LEFT', "«Slug.databaseName(com.name,ref.destinationEntity.name)» as  «ref.destinationEntity.name.toLowerCase» ON
	            «FOR ExtendedAttribute attr: ref.extendedAttributes»
	            «IF ref.extendedAttributes.last != attr»
	            «entFrom.name.toLowerCase».«attr.name.toLowerCase» = «ref.destinationEntity.name.toLowerCase».«ref.referencedExtendedAttributes.get(ref.extendedAttributes.indexOf((attr))).name.toLowerCase» AND
	            «ELSE»
	            «entFrom.name.toLowerCase».«attr.name.toLowerCase» = «ref.destinationEntity.name.toLowerCase».«ref.referencedExtendedAttributes.get(ref.extendedAttributes.indexOf((attr))).name.toLowerCase»
	            «ENDIF»
	            «ENDFOR»
	        ")
	        «ENDFOR»
	        ->order("$textColumn ASC");
	    $dbo->setQuery($query);
	    $result = $dbo->loadObjectList();
	    return $result;
	}
	'''
	
// private def CharSequence genAllSelectedDataForEntity() '''
//     protected function getAllSelectedData($«entFrom.primaryKey.name», $valueColumn, $textColumn)
//     {
//         $dbo = Factory::getDbo();
//         $query = $dbo->getQuery(true);
//         $query->select(" $valueColumn AS value, $textColumn AS text,
//         CASE WHEN «entFrom.primaryKey.name» = $«entFrom.primaryKey.name» THEN 1
//         ELSE 0 AS checked
//         ")->from("$this->table")->order("text AS ASC");
//         $dbo->setQuery($query);
//         $result = $dbo->loadObjectList();
//         return $result;
//     }
// '''
	
	static def CharSequence genFieldsForUserView(ExtendedComponent component)'''
		<?php
		«Slug.generateFileDoc(component)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("FormHelper", "Factory"))»
		
		FormHelper::loadFieldClass('list');
		
		class JFormFieldComponentuser extends JFormFieldList{
		    
		    protected function getOptions()
		    {
		        $entity = $this->getAttribute('entity');
		        $table = "#__«component.name.toLowerCase»_" . $entity;
		        $dbo = Factory::getDbo();
		        $query = $dbo->getQuery(true);
		        $query->select("DISTINCT a.created_by AS value, b.name AS text")
		            ->from("$table AS a ")
		            ->leftJoin("#__users AS b ON a.created_by = b.id")
		            ->order("b.name ASC");
		        $dbo->setQuery($query);
		        $dataList = $dbo->loadObjectList();
		        return  array_merge(parent::getOptions(),$dataList);
		    }
		}
	'''
	def public dogenerate(String path, IFileSystemAccess access) {
		if(this.mainRef !== null)
			access.generateFile(path+ "/"+getnameField +".php", genRefrenceField)
		var File fieldEntity = new File (path+ "/"+entFrom.name +".php")
		if(!fieldEntity.exists){
			access.generateFile(path+ "/"+entFrom.name +".php", genFieldsForEntity)
		}
		var File fieldUser = new File (path+ "/componentuser.php")
		if(!fieldUser.exists){
			access.generateFile(path+ "/componentuser.php", FieldsGenerator.genFieldsForUserView(com))
		}
	}

}
