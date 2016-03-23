package de.thm.icampus.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.eJSL.DynamicPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.ejsl.eJSL.InternalLink
import javax.swing.text.html.HTMLEditorKit.LinkController
import de.thm.icampus.ejsl.eJSL.Reference
import de.thm.icampus.ejsl.eJSL.Entity
import de.thm.icampus.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

class FieldsGenerator {
	
	Reference mainRef
	Component com
	String nameField
	Entity entFrom
	public new ( Reference attribute, Component component, Entity from){
		mainRef = attribute
		com = component
		entFrom = from
		nameField = from.name + "To" +attribute.entity.name
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
	 	protected $referenceStruct = array("table" => "«Slug.databaseName(com.name, entFrom.name)»",
	                                        "foreignTable"=> "«Slug.databaseName(com.name,mainRef.entity.name)»",
	                                        );
	     protected $keysAndForeignKeys= array(
	       «FOR attr : mainRef.attribute»
	       «IF attr != mainRef.attribute.last»
	        "«attr.name.toLowerCase»" => "«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»",
	       «ENDIF»
	       "«attr.name.toLowerCase»" => "«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»"
	       «ENDFOR»
	     )
	        «genGeTInput»
	        
	        «genGetAllData»
	        
	        «genGetAllRestData»
	        
	        «genGetReferencedata»
	        «genGenerateJsonValue»
	        «gengenerateStringValue»
	 	
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
	            $html[] = "<option  value='". $this->generateJsonValue($data) ."'>"
	            . $this->generateStringValue($selected) ."</option>";
	        }
	          $html[]="</select>";
		       return implode($html);
	        }
	        $selectData = $this->getReferencedata($id);
	        $restData = $this->getAllRestData($id);
	        $html[] = "<select required class='form-control' name='" . $this->name. "'>";
	        foreach($selectData as $selected){
	            $html[] = "<option selected='selected' value='". $this->generateJsonValue($selected) ."'>"
	            . $this->generateStringValue($selected) ."</option>";
	        }
	        foreach($restData as $rest){
	            $html[] = "<option  value='". $this->generateJsonValue($rest)."'>" . $this->generateStringValue($rest) ."</option>";
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
          «FOR attr : mainRef.attribute»
          «IF attr != mainRef.attribute.last»
            . " b.«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»". '=' . " a.«attr» AND". 
          «ENDIF»
            . " b.«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»". '=' . " a.«attr»"
           «ENDFOR»
           )
         ->where("b.state = 1")
         ->where("a.id =" . $id)
         ->order("id" . " ASC");
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
           «FOR attr : mainRef.attribute»
           «IF attr != mainRef.attribute.last»
             . " b.«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»". '=' . " a.«attr» AND". 
           «ENDIF»
             . " b.«mainRef.attributerefereced.get(mainRef.attribute.indexOf(attr))»". '=' . " a.«attr»"
            «ENDFOR»
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
public def CharSequence genGenerateJsonValue()'''
  public function generateJsonValue($data){
            $result  = array();
            foreach($this->$keysAndForeignKeys as $key=>$value){
                $result["$key"] = $data->$key;
            }
            return json_encode($result);
        }
'''	
public def CharSequence gengenerateStringValue()'''
public function generateStringValue($data){
        $result = array();
        foreach($this->$keysAndForeignKeys as $key=>$value){
            $result[] = $data->$key . " ";
        }
        return implode($result);
    }

'''
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}