package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaEntityGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Column
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.LeftJoin
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Query
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Select
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Table
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import java.io.File
import org.eclipse.xtext.generator.IFileSystemAccess

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
	ExtendedDetailPageField field
	String section;

	new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from, String section) {
		this.mainRef = ref
		this.com = component
		this.entFrom = from
		this.nameField = from.name + "to" + ref.entity.name
        this.section = section
	}

	new(ExtendedComponent component, ExtendedEntity from, String section) {
		this.com = component
		this.entFrom = from
        this.section = section
	}
	
	new(ExtendedComponent component, String section) {
		this.com = component
        this.section = section		
	}
	
	new(ExtendedDetailPageField field, ExtendedComponent component, String section) {
		this.com = component
		this.nameField = field.type
		this.field = field
		this.section = section		
	}

	def String getnameField() {
		return nameField
	}

	def CharSequence genRefrenceField() '''
		<?php
		«Slug.generateFileDocAdmin(com)»
		
		«Slug.generateNamespace(com.name, section, "Field")»

		«Slug.generateRestrictedAccess()»

		«Slug.generateUses(newArrayList("Text", "FormField", "Factory", "Uri"))»

		class «com.name.toFirstUpper»ReferenceField extends FormField
		{
		    public $referenceStruct = array();
		    public $keysAndForeignKeys = array();
		    public $primary_key ="id";

		    «genGetInput»

		    «genGetAllData»

		    «genGetReferencedata»
		
		    «genGetDataItem»
		
		    «genGenerateJsonValue»
		
		    «gengenerateStringValue»
		}
	'''
	
	def CharSequence genEmptyField()'''
    	<?php
    	«Slug.generateFileDocAdmin(com)»
    	
    	«Slug.generateNamespace(com.name, section, "Field")»
    	
    	«Slug.generateRestrictedAccess()»
    	
    	«Slug.generateUses(newArrayList("FormField"))»
    	
    	class «nameField.toFirstUpper»Field extends FormField
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
	
    def private genGetDataItem() {
        var query = new Query
        query.mainTable = new Table('''$table''')
        
        var column = new Column('''*''')
        var select = new Select(column)
        query.addToMainSelect(select)
        
        var whereColumn = new Column('''$this->primary_key''')
        var whereStatement = '''«whereColumn» = $id'''
        
        return '''
    	protected function getDataItem($id)
    	{
    	    $table = $this->referenceStruct->table;
    	    $db = Factory::getDbo();
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
		    $tempsTable               = str_replace("'", "\"", $this->getAttribute("tables"));
		    $tempsAttr                = str_replace("'", "\"", $this->getAttribute("referenced_keys"));
		    $this->referenceStruct    = json_decode($tempsTable);
		    $this->keysAndForeignKeys = json_decode($tempsAttr, true);
		    $html                     = array();
		    $app                      = Factory::getApplication();
		    $document                 = $app->getDocument();
		    $input                    = $app->input;
		    $this->primary_key        = $this->getAttribute("primary_key_name");
		    $id                       = intval($input->get($this->primary_key));

		    $document->addScript(Uri::root() . '/media/«Slug.nameExtensionBind("com", com.name).toLowerCase»/js/setforeignkeys.js');

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
		    $html[] = "<option>". Text::_("JSELECT"). "</option>";
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
	    $fk = $this->keysAndForeignKeys["key"];
	    $valueColumn = $this->getAttribute("valueColumn");
	    $db = Factory::getDbo();
	    $query = $db->getQuery(true);
	    if ($data->$fk === null) {
	        $caseSelected = "' '";
	    } else {
	        $caseCheck = "b." . $this->keysAndForeignKeys["ref"] . " = " . $data->$fk;
	        $caseSelected = "(case when $caseCheck then 'selected' else ' ' end)";
	    }
	    $query->select("distinct $caseSelected as selected");
	    $query->select(" b." . $this->keysAndForeignKeys["ref"]);
	    $query->select(" b." . $valueColumn);
	    $query->from($this->referenceStruct->foreignTable . " as b ")
	        ->where("b.state = 1")
	        ->order("b.$valueColumn ASC");
	    $db->setQuery($query);
	    return $db->loadObjectList();
	}
	'''

    // @todo: Use classes in DatabaseQuery 
	private def CharSequence genGetAllData() '''
		protected function getAllData()
		{
		    $valueColumn = $this->getAttribute("valueColumn");
		    $db = Factory::getDbo();
		    $query = $db->getQuery(true);
		    $query->select("b." . $this->keysAndForeignKeys["ref"]);
		    $query->select("b.$valueColumn");
		    $query->from($this->referenceStruct->foreignTable . " as b")
		    ->where("b.state = 1")
		    ->order("b.$valueColumn ASC");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}
	'''

	private def CharSequence genGenerateJsonValue() '''
		public function generateJsonValue($data)
		{
		    $result  = array();
		    $value = $this->keysAndForeignKeys;
		    $result["jform_" .  $value["key"]] = $data->{$value["ref"]};
		    return json_encode($result);
		}
	'''

	private def CharSequence gengenerateStringValue() '''
		public function generateStringValue($data)
		{
		    $result = array();
		    $valueColumn = $this->getAttribute("valueColumn");
		    $result[] = $data->{$valueColumn} . " ";
		    return implode($result);
		}
	'''
	
	def CharSequence genFieldsForEntity(String section)'''
		<?php
		«Slug.generateFileDocAdmin(com)»
		
		«Slug.generateNamespace(com.name, section, "Field")»
		
		«Slug.generateRestrictedAccess()»
		
		«Slug.generateUses(newArrayList("FormHelper", "Factory", "ListField"))»
		
		FormHelper::loadFieldClass('list');
		
		class «entFrom.name.toFirstUpper»Field extends ListField
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
	    
	    var valueColumn = new Column('''$valueColumn''')
        var textColumn = new Column('''$textColumn''')
	    query.addToMainSelect(new Select(valueColumn, '''value'''))
        query.addToMainSelect(new Select(textColumn, '''text'''))
        
        var mainEntityNameAlias = query.getUniqueAlias(entFrom.name)
        query.mainTable = new Table('''$this->table''', mainEntityNameAlias)
    	'''
    	protected function getAllData($valueColumn, $textColumn)
    	{
    	    $dbo = Factory::getDbo();
    	    $query = $dbo->getQuery(true);
    	    $query->select(
    	        "DISTINCT «query.mainSelect»"
    	    )
    	    ->from("«query.mainTable»")
    	    ->where("«textColumn» IS NOT NULL")
    	    ->where("«textColumn» <> ''")
    	    ->order("$textColumn ASC");

    	    «query.createSelectAndJoins(entFrom.allExtendedReferences, entFrom.name)»
    	    $dbo->setQuery($query);
    	    $result = $dbo->loadObjectList();

    	    return $result;
    	}
    	'''
	}
	
	def CharSequence genFieldsForUserView(ExtendedComponent component) {
	    var query = new Query
        query.mainTable = new Table('''$table''', '''a''')
        
        // Select create_by
        var createdByColumn = new Column(query.mainTable.alias, '''created_by''')
        var createdByselect = new Select(createdByColumn, '''value''')
        query.addToMainSelect(createdByselect)
        
        // Join users
        var usersJoinTable = new Table('''#__users''', '''b''')
        var usersJoinFromColumn = new Column(query.mainTable.alias, '''created_by''')
        var usersJoinToColumn = new Column('''b''', '''id''')
        var usersJoin = new LeftJoin(usersJoinTable, usersJoinFromColumn, usersJoinToColumn)
        query.joinList.add(usersJoin)
        
        // Select name
        var nameColumn = new Column(usersJoinTable.alias, '''name''')
        var nameSelect = new Select(nameColumn, '''text''')
        query.addToMainSelect(nameSelect)
        
        var orderColumn = new Column('''«usersJoinTable.alias»''', '''name''')
	   
	    return '''
    		<?php
    		«Slug.generateFileDocAdmin(component)»
    		
    		«Slug.generateNamespace(component.name, section, "Field")»
    		
    		«Slug.generateRestrictedAccess()»
    		
    		«Slug.generateUses(newArrayList("FormHelper", "Factory", "ListField"))»
    		
    		FormHelper::loadFieldClass('list');
    		
    		class «component.name.toFirstUpper»UserField extends ListField
    		{
    		    protected function getOptions()
    		    {
    		        $entity = $this->getAttribute('entity');
    		        $table = "#__«component.name»_" . $entity;
    		        $dbo = Factory::getDbo();
    		        $query = $dbo->getQuery(true);
    		        $query->select(
    		            "DISTINCT «query.mainSelect»"
    		        )
    		        ->from("«query.mainTable»")
    		        ->join(«usersJoin»)
    		        ->order("«orderColumn» ASC");
    		        
    		        $dbo->setQuery($query);
    		        $dataList = $dbo->loadObjectList();

    		        return array_merge(parent::getOptions(), $dataList);
    		    }
    		}
    	'''
	}

	def dogenerate(String path, IFileSystemAccess access) {
		if (this.mainRef !== null) {
		    var referenceFieldFilePath = '''«path»/«com.name.toFirstUpper»ReferenceField.php'''
			var File field = new File(referenceFieldFilePath)
			
			if (!field.exists) {
				access.generateFile(referenceFieldFilePath, genRefrenceField)
			}
		}
		
		var fieldFilePath = '''«path»/«entFrom.name.toFirstUpper»Field.php''';
		var File fieldEntity = new File(fieldFilePath)
		
		if (!fieldEntity.exists) {
            access.generateFile( fieldFilePath, genFieldsForEntity(this.section))
		}
		
		var userFieldFilePath = '''«path»/«com.name.toFirstUpper»UserField.php'''
		var File fieldUser = new File(userFieldFilePath)
		
		if (!fieldUser.exists) {
			access.generateFile(userFieldFilePath, genFieldsForUserView(com))
		}
	}
}
