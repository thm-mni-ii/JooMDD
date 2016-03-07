package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Component;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FieldsGenerator {
  private ExtendedReference mainRef;
  
  private Component com;
  
  private String nameField;
  
  private ExtendedEntity entFrom;
  
  public FieldsGenerator(final ExtendedReference ref, final ExtendedComponent component, final ExtendedEntity from, final int index) {
    this.mainRef = ref;
    this.com = component;
    this.entFrom = from;
    String _name = from.getName();
    String _plus = (_name + "To");
    Entity _entity = ref.getEntity();
    String _name_1 = _entity.getName();
    String _plus_1 = (_plus + _name_1);
    String _plus_2 = (_plus_1 + Integer.valueOf(index));
    this.nameField = _plus_2;
  }
  
  public FieldsGenerator(final ExtendedComponent component, final ExtendedEntity from) {
    this.com = component;
    this.entFrom = from;
  }
  
  public String getnameField() {
    return this.nameField;
  }
  
  public CharSequence genRefrenceField() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    CharSequence _generateFileDoc = Slug.generateFileDoc(this.com, true);
    _builder.append(_generateFileDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("jimport(\'joomla.form.formfield\');");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class JFormField");
    String _firstUpper = StringExtensions.toFirstUpper(this.nameField);
    _builder.append(_firstUpper, "");
    _builder.append(" extends JFormField");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected $referenceStruct = array(\"table\" => \"");
    String _name = this.com.getName();
    String _name_1 = this.entFrom.getName();
    CharSequence _databaseName = Slug.databaseName(_name, _name_1);
    _builder.append(_databaseName, "\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                                      ");
    _builder.append("\"foreignTable\"=> \"");
    String _name_2 = this.com.getName();
    Entity _entity = this.mainRef.getEntity();
    String _name_3 = _entity.getName();
    CharSequence _databaseName_1 = Slug.databaseName(_name_2, _name_3);
    _builder.append(_databaseName_1, "\t                                      ");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t                                      ");
    _builder.append(");");
    _builder.newLine();
    _builder.append("\t   ");
    _builder.append("protected $keysAndForeignKeys= array(");
    _builder.newLine();
    {
      EList<ExtendedAttribute> _extendedAttribute = this.mainRef.getExtendedAttribute();
      for(final ExtendedAttribute attr : _extendedAttribute) {
        {
          EList<ExtendedAttribute> _extendedAttribute_1 = this.mainRef.getExtendedAttribute();
          ExtendedAttribute _last = IterableExtensions.<ExtendedAttribute>last(_extendedAttribute_1);
          boolean _notEquals = (!Objects.equal(attr, _last));
          if (_notEquals) {
            _builder.append("\t     ");
            _builder.append("\"");
            String _name_4 = attr.getName();
            String _lowerCase = _name_4.toLowerCase();
            _builder.append(_lowerCase, "\t     ");
            _builder.append("\" => \"");
            EList<ExtendedAttribute> _extendedAttributeReferenced = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_2 = this.mainRef.getExtendedAttribute();
            int _indexOf = _extendedAttribute_2.indexOf(attr);
            ExtendedAttribute _get = _extendedAttributeReferenced.get(_indexOf);
            String _name_5 = _get.getName();
            String _lowerCase_1 = _name_5.toLowerCase();
            _builder.append(_lowerCase_1, "\t     ");
            _builder.append("\",");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t     ");
            _builder.append("\"");
            String _name_6 = attr.getName();
            String _lowerCase_2 = _name_6.toLowerCase();
            _builder.append(_lowerCase_2, "\t     ");
            _builder.append("\" => \"");
            EList<ExtendedAttribute> _extendedAttributeReferenced_1 = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_3 = this.mainRef.getExtendedAttribute();
            int _indexOf_1 = _extendedAttribute_3.indexOf(attr);
            ExtendedAttribute _get_1 = _extendedAttributeReferenced_1.get(_indexOf_1);
            String _name_7 = _get_1.getName();
            String _lowerCase_3 = _name_7.toLowerCase();
            _builder.append(_lowerCase_3, "\t     ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t   ");
    _builder.append(");");
    _builder.newLine();
    _builder.append("\t      ");
    CharSequence _genGeTInput = this.genGeTInput();
    _builder.append(_genGeTInput, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    _builder.newLine();
    _builder.append("\t      ");
    CharSequence _genGetAllData = this.genGetAllData();
    _builder.append(_genGetAllData, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    _builder.newLine();
    _builder.append("\t      ");
    CharSequence _genGetAllRestData = this.genGetAllRestData();
    _builder.append(_genGetAllRestData, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    _builder.newLine();
    _builder.append("\t      ");
    CharSequence _genGetReferencedata = this.genGetReferencedata();
    _builder.append(_genGetReferencedata, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    CharSequence _genGenerateJsonValue = this.genGenerateJsonValue();
    _builder.append(_genGenerateJsonValue, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    CharSequence _gengenerateStringValue = this.gengenerateStringValue();
    _builder.append(_gengenerateStringValue, "\t      ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGeTInput() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Method to get the field input markup.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return\tstring\tThe field input markup.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @since\t1.6");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected function getInput()");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$html = array();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$document = JFactory::getDocument();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("$document->addScript( JURI::root() . \'/administrator/components/");
    String _name = this.com.getName();
    String _nameExtensionBind = Slug.nameExtensionBind("com", _name);
    String _lowerCase = _nameExtensionBind.toLowerCase();
    _builder.append(_lowerCase, "\t\t");
    _builder.append("/assets/setForeignKeys.js\');");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("$input = JFactory::getApplication()->input;");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$id = intval($input->get(\'id\'));");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("if(empty($id)){");
    _builder.newLine();
    _builder.append("\t\t      \t");
    _builder.append("$alldata = $this->getAllData();");
    _builder.newLine();
    _builder.append("\t\t      \t    ");
    _builder.append("$html[] = \"<select required onchange=\'setValueForeignKeys(this)\' id=\'\" . $this->id . \"select\'  class=\'form-control\' name=\'\" . $this->name. \"\'>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$html[] = \"<option>\". JText::_(\"JOPTION_SELECT_");
    EList<ExtendedAttribute> _extendedAttribute = this.mainRef.getExtendedAttribute();
    ExtendedAttribute _get = _extendedAttribute.get(0);
    String _name_1 = _get.getName();
    String _upperCase = _name_1.toUpperCase();
    _builder.append(_upperCase, "\t\t      ");
    _builder.append("\"). \"</option>\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t      ");
    _builder.append("foreach($alldata as $data){");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append("$html[] = \"<option  value=\'\". $this->generateJsonValue($data) .\"\'>\"");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append(". $this->generateStringValue($data) .\"</option>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t        ");
    _builder.append("$html[]=\"</select>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$html[]=\"<input type=\'hidden\' value=\'\' name=\'\" . $this->name. \"\' id=\'\" . $this->id. \"\'/>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("return implode($html);");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$selectData = $this->getReferencedata($id);");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$restData = $this->getAllRestData($id);");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$html[] = \"<select required onchange=\'setValueForeignKeys(this)\' id=\'\" . $this->id . \"select\' class=\'form-control\' name=\'\" . $this->name. \"select\'>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$html[] = \"<option>\". JText::_(\"JOPTION_SELECT_");
    EList<ExtendedAttribute> _extendedAttribute_1 = this.mainRef.getExtendedAttribute();
    ExtendedAttribute _get_1 = _extendedAttribute_1.get(0);
    String _name_2 = _get_1.getName();
    String _upperCase_1 = _name_2.toUpperCase();
    _builder.append(_upperCase_1, "\t\t      ");
    _builder.append("\"). \"</option>\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t      ");
    _builder.append("foreach($selectData as $selected){");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append("$html[] = \"<option selected=\'selected\' value=\'\". $this->generateJsonValue($selected) .\"\'>\"");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append(". $this->generateStringValue($selected) .\"</option>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("foreach($restData as $rest){");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append("$html[] = \"<option  value=\'\". $this->generateJsonValue($rest).\"\'>\" . $this->generateStringValue($rest) .\"</option>\";");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t      ");
    _builder.append("$html[]=\"</select>\";");
    _builder.newLine();
    _builder.append("\t\t  ");
    _builder.append("$html[]=\"<input type=\'hidden\' value=\'\" . $this->value. \"\' name=\'\" . $this->name. \"\' id=\'\" . $this->id. \"\'/>\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return implode($html);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGetReferencedata() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*Read Selected  Items");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected function getReferencedata($id)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$db = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$query = $db->getQuery(true);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$query->select(\"b.*\")");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("->from( $this->referenceStruct[\"table\"] . \" as a\")");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("->leftJoin($this->referenceStruct[\"foreignTable\"] . \" as b on ");
    _builder.newLine();
    {
      EList<ExtendedAttribute> _extendedAttribute = this.mainRef.getExtendedAttribute();
      for(final ExtendedAttribute attr : _extendedAttribute) {
        {
          EList<ExtendedAttribute> _extendedAttribute_1 = this.mainRef.getExtendedAttribute();
          ExtendedAttribute _last = IterableExtensions.<ExtendedAttribute>last(_extendedAttribute_1);
          boolean _notEquals = (!Objects.equal(attr, _last));
          if (_notEquals) {
            _builder.append("b.");
            EList<ExtendedAttribute> _extendedAttributeReferenced = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_2 = this.mainRef.getExtendedAttribute();
            int _indexOf = _extendedAttribute_2.indexOf(attr);
            ExtendedAttribute _get = _extendedAttributeReferenced.get(_indexOf);
            String _name = _get.getName();
            String _lowerCase = _name.toLowerCase();
            _builder.append(_lowerCase, "");
            _builder.append("= a.");
            String _name_1 = attr.getName();
            String _lowerCase_1 = _name_1.toLowerCase();
            _builder.append(_lowerCase_1, "");
            _builder.append(" AND ");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("b.");
            EList<ExtendedAttribute> _extendedAttributeReferenced_1 = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_3 = this.mainRef.getExtendedAttribute();
            int _indexOf_1 = _extendedAttribute_3.indexOf(attr);
            ExtendedAttribute _get_1 = _extendedAttributeReferenced_1.get(_indexOf_1);
            String _name_2 = _get_1.getName();
            String _lowerCase_2 = _name_2.toLowerCase();
            _builder.append(_lowerCase_2, "");
            _builder.append("= a.");
            String _name_3 = attr.getName();
            String _lowerCase_3 = _name_3.toLowerCase();
            _builder.append(_lowerCase_3, "");
            _builder.append("\"\t       ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("           ");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t         ");
    _builder.append("->where(\"b.state = 1\")");
    _builder.newLine();
    _builder.append("\t         ");
    _builder.append("->where(\"a.id =\" . $id)");
    _builder.newLine();
    _builder.append("\t         ");
    _builder.append("->order(\"a.id\" . \" ASC\");");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("$db->setQuery($query);");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("return $db->loadObjectList();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGetAllRestData() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected function getAllRestData($id)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$db = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$queryALL = $db->getQuery(true);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$query = $db->getQuery(true);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$query->select(\"b.id\")");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("->from( $this->referenceStruct[\"table\"] . \" as a\")");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("->leftJoin($this->referenceStruct[\"foreignTable\"]. \" as b on ");
    _builder.newLine();
    {
      EList<ExtendedAttribute> _extendedAttribute = this.mainRef.getExtendedAttribute();
      for(final ExtendedAttribute attr : _extendedAttribute) {
        {
          EList<ExtendedAttribute> _extendedAttribute_1 = this.mainRef.getExtendedAttribute();
          ExtendedAttribute _last = IterableExtensions.<ExtendedAttribute>last(_extendedAttribute_1);
          boolean _notEquals = (!Objects.equal(attr, _last));
          if (_notEquals) {
            _builder.append("           ");
            _builder.append("b.");
            EList<ExtendedAttribute> _extendedAttributeReferenced = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_2 = this.mainRef.getExtendedAttribute();
            int _indexOf = _extendedAttribute_2.indexOf(attr);
            ExtendedAttribute _get = _extendedAttributeReferenced.get(_indexOf);
            String _name = _get.getName();
            _builder.append(_name, "           ");
            _builder.append("= a.");
            String _name_1 = attr.getName();
            String _lowerCase = _name_1.toLowerCase();
            _builder.append(_lowerCase, "           ");
            _builder.append(" AND ");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("           ");
            _builder.append("b.");
            EList<ExtendedAttribute> _extendedAttributeReferenced_1 = this.mainRef.getExtendedAttributeReferenced();
            EList<ExtendedAttribute> _extendedAttribute_3 = this.mainRef.getExtendedAttribute();
            int _indexOf_1 = _extendedAttribute_3.indexOf(attr);
            ExtendedAttribute _get_1 = _extendedAttributeReferenced_1.get(_indexOf_1);
            String _name_2 = _get_1.getName();
            _builder.append(_name_2, "           ");
            _builder.append("= a.");
            String _name_3 = attr.getName();
            String _lowerCase_1 = _name_3.toLowerCase();
            _builder.append(_lowerCase_1, "           ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("            ");
    _builder.append(") ");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->where(\"b.state = 1\")");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->where(\"a.id =\" . $id);");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("$queryALL->select(\"*\")");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->from($this->referenceStruct[\"foreignTable\"])");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->where(\"state = 1\")");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->where(\"id not in (\" . $query  .\")\")");
    _builder.newLine();
    _builder.append("\t        ");
    _builder.append("->order(\"id\" . \" ASC\");");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("$db->setQuery($queryALL);");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("return $db->loadObjectList();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGetAllData() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("protected function getAllData(){");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$db = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$queryALL = $db->getQuery(true);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$queryALL->select(\"*\")");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("->from($this->referenceStruct[\"foreignTable\"])");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("->where(\"state = 1\")");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("->order(array_values($this->keysAndForeignKeys)[0] . \" ASC\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("$db->setQuery($queryALL);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return $db->loadObjectList();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGenerateJsonValue() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public function generateJsonValue($data){");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("$result  = array();");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("foreach($this->keysAndForeignKeys as $key=>$value){");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("$result[\"jform_$key\"] = $data->{$value};");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("          ");
    _builder.append("return json_encode($result);");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence gengenerateStringValue() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public function generateStringValue($data){");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$result = array();");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("$result[] = $data->{array_values($this->keysAndForeignKeys)[0]} . \" \";");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return implode($result);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence genFieldsForEntity() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    CharSequence _generateFileDoc = Slug.generateFileDoc(this.com, true);
    _builder.append(_generateFileDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("JFormHelper::loadFieldClass(\'list\');");
    _builder.newLine();
    _builder.append("class JFormField");
    String _name = this.entFrom.getName();
    String _firstLower = StringExtensions.toFirstLower(_name);
    _builder.append(_firstLower, "");
    _builder.append(" extends JFormFieldList{");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected $table = \"");
    String _name_1 = this.com.getName();
    String _name_2 = this.entFrom.getName();
    CharSequence _databaseName = Slug.databaseName(_name_1, _name_2);
    _builder.append(_databaseName, "    ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    CharSequence _genGetOptionsForEntity = this.genGetOptionsForEntity();
    _builder.append(_genGetOptionsForEntity, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    CharSequence _genGetAllDataForEntity = this.genGetAllDataForEntity();
    _builder.append(_genGetAllDataForEntity, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGetOptionsForEntity() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected function getOptions()");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$input = JFactory::getApplication()->input;");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$id = intval($input->get(\'id\'));");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$valueColumn = $this->getAttribute(\'valueColumn\');");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$textColumn = $this->getAttribute(\'textColumn\');");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("      ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("return  array_merge(parent::getOptions(),$this->getAllData($valueColumn, $textColumn));");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genGetAllDataForEntity() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected function getAllData($valueColumn, $textColumn){");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$dbo = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$query = $dbo->getQuery(true);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$query->select(\"DISTINCT $valueColumn as value, $textColumn as text\")");
    _builder.newLine();
    _builder.append("             ");
    _builder.append("->from(\"$this->table\")");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("->order(\"$textColumn ASC\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$dbo->setQuery($query);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("$result = $dbo->loadObjectList();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return $result;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence genAllSelectedDataForEntity() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected function getAllSelectedData($id, $valueColumn, $textColumn){");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$dbo = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$query = $dbo->getQuery(true);");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$query->select(\" $valueColumn AS value, $textColumn AS text,");
    _builder.newLine();
    _builder.append("                       ");
    _builder.append("CASE WHEN id = $id THEN 1");
    _builder.newLine();
    _builder.append("                       ");
    _builder.append("ELSE 0 AS checked");
    _builder.newLine();
    _builder.append("                      ");
    _builder.append("\")->from(\"$this->table\")->order(\"text AS ASC\");");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$dbo->setQuery($query);");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("$result = $dbo->loadObjectList();");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("return $result;");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public static CharSequence genFieldsForUserView(final ExtendedComponent component) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?php");
    _builder.newLine();
    CharSequence _generateFileDoc = Slug.generateFileDoc(component, true);
    _builder.append(_generateFileDoc, "");
    _builder.newLineIfNotEmpty();
    _builder.append("JFormHelper::loadFieldClass(\'list\');");
    _builder.newLine();
    _builder.append("class JFormField");
    String _name = component.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("user extends JFormFieldList{");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("protected function getOptions(){");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$entity = $this->getAttribute(\'entity\');");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$table = \"#__");
    String _name_1 = component.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "           ");
    _builder.append("_\" . $entity;");
    _builder.newLineIfNotEmpty();
    _builder.append("           ");
    _builder.append("$dbo = JFactory::getDbo();");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$query = $dbo->getQuery(true);");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$query->select(\"DISTINCT a.created_by AS value, b.name AS text\")");
    _builder.newLine();
    _builder.append("                 ");
    _builder.append("->from(\"$table AS a \")");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("->leftJoin(\"#__users AS b ON a.created_by = b.id\")");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("->order(\"b.name ASC\");");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$dbo->setQuery($query);");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("$dataList = $dbo->loadObjectList();");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("return  array_merge(parent::getOptions(),$dataList);");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
