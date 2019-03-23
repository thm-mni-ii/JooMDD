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
import java.nio.file.Files
import java.nio.file.Path
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Query
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Select
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Table
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Column
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.DatabaseQuery.Join

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

		class JFormField«com.name.toFirstUpper»reference extends FormField
		{
		    public $referenceStruct = array();
		    public $keysAndForeignKeys = array();
		    public $primary_key ="id";

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
    def private genGetData_item() {
        var query = new Query
        var column = new Column('''*''')
        var select = new Select(column)
        query.addToMainSelect(select)
        
        var whereColumn = new Column('''$this->primary_key''')
        var whereStatement = '''«whereColumn» = $id'''
        
        return '''
    	protected function getDataItem($id)
    	{
    	    $db = JFactory::getDbo();
    	    $query = $db->getQuery(true);
    	    $query->select("«query.mainSelect»")
    	          ->from("«query.mainTable»")
    	          ->where("«whereStatement»");
    	    $db->setQuery($query);
    	    return $db->loadObject();
    	}
    	'''
	}
	
	private def CharSequence genGetInput() '''
		/**
		 * Method to get the field input markup.
		 *
		 * @return  string  The field input markup.
		 * @since 1.6
		 */
		protected function getInput()
		{
		    $tempsTable = str_replace("'", "\"", $this->getAttribute("tables"));
		    $tempsAttr = str_replace("'", "\"", $this->getAttribute("referenced_keys"));
		    $this->referenceStruct = json_decode($tempsTable);
		    $this->keysAndForeignKeys = json_decode($tempsAttr, true);
		    $html = array();
		    $document = Factory::getDocument();
		    $document->addScript(Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/setforeignkeys.js');
		    $input = Factory::getApplication()->input;
		    
		    $this->primary_key = $this->getAttribute("primary_key_name");
		    $id = intval($input->get($this->primary_key));
		    if (empty($id)) {
		        $alldata = $this->getAllData();
		        $html[] = "<select
		                       required
		                       onchange='setValueForeignKeys(this)'
		                       id='" . $this->id . "select'
		                       class='form-control'
		                   >";
		        $html[] = "<option>". Text::_('JSELECT'). "</option>";
		        foreach ($alldata as $data) {
		            $html[] = "<option  value='". $this->generateJsonValue($data) ."'>"
		            . $this->generateStringValue($data) ."</option>";
		        }
		        $html[]="</select>";
		        $html[]="<input type='hidden' value='' name='" . $this->name. "' id='" . $this->id . "'/>";
		        return implode($html);
		    }
		    $data = $this->getDataItem($id);
		    $selectData = $this->getReferencedata($data);
		    $html[] = "<select
		                   required
		                   onchange='setValueForeignKeys(this)'
		                   id='" . $this->id. "select'
		                   class='form-control'
		                   name='" . $this->name. "select'
		               >";
		    $html[] = "<option>". Text::_("JOPTION_SELECT_" . strtoupper($this->fieldname)). "</option>";
		    foreach ($selectData as $selected) {
		        $html[] = "<option $selected->selected value='". $this->generateJsonValue($selected) ."'>"
		        . $this->generateStringValue($selected) ."</option>";
		    }

		    $html[]="</select>";
		    $html[]="<input type='hidden' value='" . $this->value. "' name='" . $this->name. "' id='" . $this->id. "'/>";
		    return implode($html);
		}
	'''

    // @todo: Use classes in DatabaseQuery 
	private def CharSequence genGetReferencedata() '''
	/**
	*Read Selected  Items
	*
	*/
	protected function getReferencedata($data)
	{
	    $caseCheck = "";
	    foreach ($this->keysAndForeignKeys as $k => $v) {
	        $fk =  $v["key"];
	        $caseCheck .= " b." . $v["ref"] . " = '" .$data->$fk . "' and";
	    }
	    $caseCheck = rtrim($caseCheck, "and");
	    $db = JFactory::getDbo();
	    $query = $db->getQuery(true);
	    $query->select("distinct (case when"
	        . $caseCheck .
	        "then 'selected'
	        else ' ' end) as selected");
	    foreach ($this->keysAndForeignKeys as $key => $value) {
	        $query->select(" b." . $value["ref"]);
	    }
	    $query->from($this->referenceStruct->foreignTable . " as b ")
	        ->where("b.state = 1")
	        ->order("b." . $this->keysAndForeignKeys[0]["ref"]. " ASC");
	    $db->setQuery($query);
	    return $db->loadObjectList();
	}
	'''

    // @todo: Use classes in DatabaseQuery 
	private def CharSequence genGetAllData() '''
		protected function getAllData()
		{
		    $db = JFactory::getDbo();
		    $query = $db->getQuery(true);
		    foreach ($this->keysAndForeignKeys as $k => $v) {
		        $query->select("b." . $v["ref"]);
		    }
		    $query->from($this->referenceStruct->foreignTable . " as b")
		    ->where("b.state = 1")
		    ->order("b." . $this->keysAndForeignKeys[0]["ref"] . " ASC");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}
	'''

	private def CharSequence genGenerateJsonValue() '''
		public function generateJsonValue($data)
		{
		    $result  = array();
		    foreach ($this->keysAndForeignKeys as $key => $value) {
		        $result["jform_" .  $value["key"]] = $data->{$value["ref"]};
		    }
		    return json_encode($result);
		}
	'''

	private def CharSequence gengenerateStringValue() '''
		public function generateStringValue($data)
		{
		    $result = array();
		    $result[] = $data->{$this->keysAndForeignKeys[0]["ref"]} . " ";
		    return implode($result);
		}
	'''
	
	public def CharSequence genFieldsForEntity()'''
		<?php
		«Slug.generateFileDoc(com)»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("FormHelper", "Factory"))»
		
		FormHelper::loadFieldClass('list');
		
		class JFormField«entFrom.name.toFirstLower» extends JFormFieldList
		{
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
		    return array_merge(parent::getOptions(), $this->getAllData($valueColumn, $textColumn));
		}
	'''
	
	private def genGetAllDataForEntity() {
	    var query = new Query(com)
	    query.addToMainSelect(new Select('''$valueColumn''', '''value'''))
        query.addToMainSelect(new Select('''$textColumn''', '''text'''))
        
        var mainEntityNameAlias = query.getUniqueAlias(entFrom.name)
        query.mainTable = new Table('''$this->table''', mainEntityNameAlias)
    	'''
    	protected function getAllData($valueColumn, $textColumn)
    	{
    	    $dbo = Factory::getDbo();
    	    $query = $dbo->getQuery(true);
    	    $query->select("DISTINCT «query.mainSelect»")
    	        ->from("«query.mainTable»")
    	        ->order("$textColumn ASC");

    	    «query.createSelectAndJoins(entFrom.allExtendedReferences, entFrom.name)»
    	    $dbo->setQuery($query);
    	    $result = $dbo->loadObjectList();
    	    return $result;
    	}
    	'''
	}
	
	static def CharSequence genFieldsForUserView(ExtendedComponent component) {
        var query = new Query
        query.mainTable = new Table('''$table''', '''a''')
        
        // Select create_by
        var createdByColumn = new Column(query.mainTable.alias, '''created_by''')
        var createdByselect = new Select(createdByColumn, '''value''')
        query.addToMainSelect(createdByselect)
        
        // Select name
        var nameColumn = new Column(query.mainTable.alias, '''name''')
        var nameSelect = new Select(nameColumn, '''text''')
        query.addToMainSelect(nameSelect)
        
        // Join users
        var usersJoinTable = new Table('''#__users''', '''b''')
        var usersJoinFromColumn = new Column(query.mainTable.alias, '''created_by''')
        var usersJoinToColumn = new Column('''b''', '''id''')
        var usersJoin = new Join(usersJoinTable, usersJoinFromColumn, usersJoinToColumn)
        query.joinList.add(usersJoin)
	   
	   return '''
    		<?php
    		«Slug.generateFileDoc(component)»
    		
    		«Slug.generateRestrictedAccess()»
    		
    		«Slug.generateUses(newArrayList("FormHelper", "Factory"))»
    		
    		FormHelper::loadFieldClass('list');
    		
    		class JFormField«component.name.toFirstUpper»user extends JFormFieldList
    		{
    		    protected function getOptions()
    		    {
    		        $entity = $this->getAttribute('entity');
    		        $table = "#__«component.name»_" . $entity;
    		        $dbo = Factory::getDbo();
    		        $query = $dbo->getQuery(true);
    		        $query->select("DISTINCT «query.mainSelect»")
    		               ->from("«query.mainTable»")
    		               ->join(«usersJoin»)
    		               ->order("«nameColumn» ASC");
    		        $dbo->setQuery($query);
    		        $dataList = $dbo->loadObjectList();
    		        return array_merge(parent::getOptions(), $dataList);
    		    }
    		}
    	'''
	}

	def dogenerate(String path, IFileSystemAccess access) {
		if (this.mainRef !== null) {
			var File field = new File(path + '''/«com.name.toLowerCase»reference.php''')
			if (!field.exists) {
				access.generateFile(path + '''/«com.name.toLowerCase»reference.php''', genRefrenceField)

			}
		}
		var File fieldEntity = new File(path + "/" + entFrom.name.toLowerCase + ".php")
		if (!fieldEntity.exists) {
			access.generateFile(path + "/" + entFrom.name.toLowerCase + ".php", genFieldsForEntity)
		}
		var File fieldUser = new File(path + '''/«com.name.toLowerCase»user.php''')
		if (!fieldUser.exists) {
			access.generateFile(path + '''/«com.name.toLowerCase»user.php''', FieldsGenerator.genFieldsForUserView(com))
		}
	}
}
