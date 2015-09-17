package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.eJSL.InternalLink
import javax.swing.text.html.HTMLEditorKit.LinkController
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Entity

class FieldsGenerator {
	
	Reference ref
	Component com
	String nameField
	Entity entFrom
	public new ( Reference reference, Component component, Entity from){
		ref = reference
		com = component
		entFrom = from
		nameField = from.name + "To" +reference.entity.name
	}
	
	
	public def String getnameField(){
		return nameField 
	}
	
	
	public def CharSequence genClassField()'''
	 <?php
«Slug.generateFileDoc(com, true)»
	 
	 jimport('joomla.form.formfield');
	 
	 class JFormField«nameField.toFirstUpper» extends JFormField
	 {
	 	protected $referenceStruct = array("keys" => "«ref.attribute.name.toLowerCase»",
	                                        "table" => "«Slug.databaseName(com.name, entFrom.name)»",
	                                       "foreignKeys" => "«ref.attributerefereced.name.toLowerCase»",
	                                        "foreignTable"=> "«Slug.databaseName(com.name,ref.entity.name)»",
	                                        );
	        «genGeTInput»
	        
	        «genGetAllData»
	        
	        «genGetAllRestData»
	        
	        «genGetReferencedata»
	 	
	 }
	'''
	
	public def CharSequence genGeTInput() '''
	/**
	 * Method to get the field input markup.
	 *
	 * @return	string	The field input markup.
	 * @since	1.6
	 */
	protected function getInput()
	{
			$html = array();
			$input = JFactory::getApplication()->input;
	        $id = intval($input->get('id'));
	        if(empty($id)){
	        	$alldata = $this->getAllData();
	             $html[] = "<select required class='form-control' name='" . $this->name. "'>";
	        foreach($alldata as $data){
	            $html[] = "<option  value='".$data->«ref.attributerefereced.name.toLowerCase» ."'>"
	            . $data->«ref.attributerefereced.name.toLowerCase» ."</option>";
	        }
	          $html[]="</select>";
		       return implode($html);
	        }
	        $selectData = $this->getReferencedata($id);
	        $restData = $this->getAllRestData($id);
	        $html[] = "<select required class='form-control' name='" . $this->name. "'>";
	        foreach($selectData as $selected){
	            $html[] = "<option selected='selected' value='".$selected->«ref.attributerefereced.name.toLowerCase» ."'>"
	            . $selected->«ref.attributerefereced.name.toLowerCase» ."</option>";
	        }
	        foreach($restData as $rest){
	            $html[] = "<option >" . $rest->«ref.attributerefereced.name.toLowerCase»."</option>";
	        }
	        $html[]="</select>";
		return implode($html);
	}
	'''
	
	public def CharSequence genGetReferencedata()'''
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
          ->leftJoin($this->referenceStruct["foreignTable"] . " as b on "
              . " b." . $this->referenceStruct["foreignKeys"] . '=' . " a." . $this->referenceStruct["keys"])
         ->where("b.state = 1")
         ->where("a.id =" . $id)
         ->order($this->referenceStruct["foreignKeys"] . " ASC");
    $db->setQuery($query);
    return $db->loadObjectList();
}

	'''
	
	
	public def CharSequence genGetAllRestData()'''
	protected function getAllRestData($id)
	{
    $db = JFactory::getDbo();
    $queryALL = $db->getQuery(true);
    $query = $db->getQuery(true);
    $query->select("b.id")
        ->from( $this->referenceStruct["table"] . " as a")
        ->leftJoin($this->referenceStruct["foreignTable"]. " as b on "
            . " b." . $this->referenceStruct["foreignKeys"] . "=  a." . $this->referenceStruct["keys"])
        ->where("b.state = 1")
        ->where("a.id =" . $id);
    $queryALL->select("*")
        ->from($this->referenceStruct["foreignTable"])
        ->where("state = 1")
        ->where("id not in (" . $query  .")")
        ->order($this->referenceStruct["foreignKeys"] . " ASC");
    $db->setQuery($queryALL);
    return $db->loadObjectList();
}
	'''
	
	public def CharSequence genGetAllData()'''
 protected function getAllData(){
    $db = JFactory::getDbo();
    $queryALL = $db->getQuery(true);
    $queryALL->select("*")
        ->from($this->referenceStruct["foreignTable"])
        ->where("state = 1")
        ->order($this->referenceStruct["foreignKeys"] . " ASC");
    $db->setQuery($queryALL);
    return $db->loadObjectList();
}
	'''
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}