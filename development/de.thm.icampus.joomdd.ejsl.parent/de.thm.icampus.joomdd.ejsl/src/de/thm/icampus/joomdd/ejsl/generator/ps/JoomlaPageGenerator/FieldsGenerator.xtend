package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent

class FieldsGenerator {

	ExtendedReference mainRef
	Component com
	String nameField
	ExtendedEntity entFrom

	public new(ExtendedReference ref, ExtendedComponent component, ExtendedEntity from, int index) {
		mainRef = ref
		com = component
		entFrom = from
		nameField = from.name + "To" + ref.entity.name+index
	}

	public new(ExtendedComponent component, ExtendedEntity from) {
		com = component
		entFrom = from
	}

	public def String getnameField() {
		return nameField
	}

	public def CharSequence genRefrenceField() '''
		<?php
		«Slug.generateFileDoc(com, true)»
		
		jimport('joomla.form.formfield');
		
		class JFormField«nameField.toFirstUpper» extends JFormField
		{
			protected $referenceStruct = array("table" => "«Slug.databaseName(com.name, entFrom.name)»",
			                                      "foreignTable"=> "«Slug.databaseName(com.name,mainRef.entity.name)»",
			                                      );
			   protected $keysAndForeignKeys= array(
			     «FOR attr : mainRef.extendedAttribute»
			     	«IF attr != mainRef.extendedAttribute.last»
			     		"«attr.name.toLowerCase»" => "«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name.toLowerCase»",
			     	«ELSE»
			     		"«attr.name.toLowerCase»" => "«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name.toLowerCase»"
			     	«ENDIF»
			     «ENDFOR»
			   );
			      «genGeTInput»
			      
			      «genGetAllData»
			      
			      «genGetAllRestData»
			      
			      «genGetReferencedata»
			      «genGenerateJsonValue»
			      «gengenerateStringValue»
			
		}
	'''

	private def CharSequence genGeTInput() '''
		/**
		 * Method to get the field input markup.
		 *
		 * @return	string	The field input markup.
		 * @since	1.6
		 */
		protected function getInput()
		{
				$html = array();
				$document = JFactory::getDocument();
				$document->addScript( JURI::root() . '/administrator/components/«Slug.nameExtensionBind("com",com.name).toLowerCase»/assets/setForeignKeys.js');
				$input = JFactory::getApplication()->input;
				      $id = intval($input->get('id'));
				      if(empty($id)){
				      	$alldata = $this->getAllData();
				      	    $html[] = "<select required onchange='setValueForeignKeys(this)' id='" . $this->id . "select'  class='form-control' name='" . $this->name. "'>";
				      $html[] = "<option>". JText::_("JOPTION_SELECT_«mainRef.extendedAttribute.get(0).name.toUpperCase»"). "</option>";
				      foreach($alldata as $data){
				          $html[] = "<option  value='". $this->generateJsonValue($data) ."'>"
				          . $this->generateStringValue($data) ."</option>";
				      }
				        $html[]="</select>";
				      $html[]="<input type='hidden' value='' name='" . $this->name. "' id='" . $this->id. "'/>";
				      return implode($html);
				      }
				      $selectData = $this->getReferencedata($id);
				      $restData = $this->getAllRestData($id);
				      $html[] = "<select required onchange='setValueForeignKeys(this)' id='" . $this->id . "select' class='form-control' name='" . $this->name. "select'>";
				      $html[] = "<option>". JText::_("JOPTION_SELECT_«mainRef.extendedAttribute.get(0).name.toUpperCase»"). "</option>";
				      foreach($selectData as $selected){
				          $html[] = "<option selected='selected' value='". $this->generateJsonValue($selected) ."'>"
				          . $this->generateStringValue($selected) ."</option>";
				      }
				      foreach($restData as $rest){
				          $html[] = "<option  value='". $this->generateJsonValue($rest)."'>" . $this->generateStringValue($rest) ."</option>";
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
	protected function getReferencedata($id)
	{
	    $db = JFactory::getDbo();
	    $query = $db->getQuery(true);
	    $query->select("b.*")
	          ->from( $this->referenceStruct["table"] . " as a")
	          ->leftJoin($this->referenceStruct["foreignTable"] . " as b on 
	          «FOR attr : mainRef.extendedAttribute»
          	«IF attr != mainRef.extendedAttribute.last»
             b.«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name.toLowerCase»= a.«attr.name.toLowerCase» AND 
  		  «ELSE»
  		     b.«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name.toLowerCase»= a.«attr.name.toLowerCase»"	       
          «ENDIF»
          «ENDFOR»
           )
		         ->where("b.state = 1")
		         ->where("a.id =" . $id)
		         ->order("a.id" . " ASC");
		    $db->setQuery($query);
		    return $db->loadObjectList();
		}

	'''

	private def CharSequence genGetAllRestData() '''
	protected function getAllRestData($id)
	{
    $db = JFactory::getDbo();
    $queryALL = $db->getQuery(true);
    $query = $db->getQuery(true);
    $query->select("b.id")
        ->from( $this->referenceStruct["table"] . " as a")
        ->leftJoin($this->referenceStruct["foreignTable"]. " as b on 
           «FOR attr : mainRef.extendedAttribute»
           «IF attr != mainRef.extendedAttribute.last»
              b.«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name»= a.«attr.name.toLowerCase» AND 
           «ELSE»
              b.«mainRef.extendedAttributeReferenced.get(mainRef.extendedAttribute.indexOf(attr)).name»= a.«attr.name.toLowerCase»"
           «ENDIF»
            «ENDFOR»
            ) 
		        ->where("b.state = 1")
		        ->where("a.id =" . $id);
		    $queryALL->select("*")
		        ->from($this->referenceStruct["foreignTable"])
		        ->where("state = 1")
		        ->where("id not in (" . $query  .")")
		        ->order("id" . " ASC");
		    $db->setQuery($queryALL);
		    return $db->loadObjectList();
		}
			'''

	private def CharSequence genGetAllData() '''
	 protected function getAllData(){
	    $db = JFactory::getDbo();
	    $queryALL = $db->getQuery(true);
	    $queryALL->select("*")
	        ->from($this->referenceStruct["foreignTable"])
	        ->where("state = 1")
	        ->order(array_values($this->keysAndForeignKeys)[0] . " ASC");
	    $db->setQuery($queryALL);
	    return $db->loadObjectList();
	}
	'''

	private def CharSequence genGenerateJsonValue() '''
		public function generateJsonValue($data){
		          $result  = array();
		          foreach($this->keysAndForeignKeys as $key=>$value){
		              $result["jform_$key"] = $data->{$value};
		          }
		          return json_encode($result);
		      }
	'''

	private def CharSequence gengenerateStringValue() '''
	public function generateStringValue($data){
	        $result = array();
	        
	            $result[] = $data->{array_values($this->keysAndForeignKeys)[0]} . " ";
	        
	        return implode($result);
	    }
	'''
	
	public def CharSequence genFieldsForEntity()'''
	<?php
	«Slug.generateFileDoc(com, true)»
	
	JFormHelper::loadFieldClass('list');
	class JFormField«entFrom.name.toFirstLower» extends JFormFieldList{
	
	    protected $table = "«Slug.databaseName(com.name, entFrom.name)»";
	    
	    «genGetOptionsForEntity»
	    «genGetAllDataForEntity»
	 }
	'''
	
	private def genGetOptionsForEntity() '''
	 protected function getOptions()
	    {
	
	        $input = JFactory::getApplication()->input;
	        $id = intval($input->get('id'));
	        $valueColumn = $this->getAttribute('valueColumn');
	        $textColumn = $this->getAttribute('textColumn');
	
	       
	       return  array_merge(parent::getOptions(),$this->getAllData($valueColumn, $textColumn));
	    }
	'''
	
	private def genGetAllDataForEntity() '''
	protected function getAllData($valueColumn, $textColumn){
	        $dbo = JFactory::getDbo();
	        $query = $dbo->getQuery(true);
	        $query->select("DISTINCT $valueColumn as value, $textColumn as text")
	             ->from("$this->table")
	            ->order("$textColumn ASC");
	        $dbo->setQuery($query);
	        $result = $dbo->loadObjectList();
	        return $result;
	    }
	'''
	
	private def CharSequence genAllSelectedDataForEntity() '''
	 protected function getAllSelectedData($id, $valueColumn, $textColumn){
	        $dbo = JFactory::getDbo();
	        $query = $dbo->getQuery(true);
	        $query->select(" $valueColumn AS value, $textColumn AS text,
	                        CASE WHEN id = $id THEN 1
	                        ELSE 0 AS checked
	                       ")->from("$this->table")->order("text AS ASC");
	        $dbo->setQuery($query);
	        $result = $dbo->loadObjectList();
	        return $result;
	    }
	'''
	static def CharSequence genFieldsForUserView(ExtendedComponent component)'''
	<?php
	«Slug.generateFileDoc(component, true)»
	JFormHelper::loadFieldClass('list');
	class JFormField«component.name.toFirstUpper»user extends JFormFieldList{
	    
	      protected function getOptions(){
	           $entity = $this->getAttribute('entity');
	           $table = "#__«component.name.toLowerCase»_" . $entity;
	           $dbo = JFactory::getDbo();
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

}
