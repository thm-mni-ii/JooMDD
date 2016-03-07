package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaEntityGenerator;

import com.google.common.base.Objects;
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.eJSL.Entity;
import de.thm.icampus.joomdd.ejsl.eJSL.Reference;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class JoomlaEntityGenerator {
  private EList<ExtendedEntity> entities;
  
  private String extendsionN;
  
  private boolean update;
  
  public JoomlaEntityGenerator(final EList<ExtendedEntity> entityList, final String extendsionName, final boolean updateScript) {
    this.entities = entityList;
    this.extendsionN = extendsionName;
    this.update = updateScript;
  }
  
  public CharSequence dogenerate() {
    return this.sqlAdminSqlInstallContent(this.extendsionN, this.update);
  }
  
  public boolean setUpdate(final boolean updateScript) {
    return this.update = updateScript;
  }
  
  public void dogenerate(final String path, final IFileSystemAccess fileGen) {
    CharSequence _sqlAdminSqlInstallContent = this.sqlAdminSqlInstallContent(this.extendsionN, this.update);
    fileGen.generateFile((path + "/install.sql"), _sqlAdminSqlInstallContent);
    CharSequence _sqlAdminSqlUninstallContent = this.sqlAdminSqlUninstallContent(this.extendsionN);
    fileGen.generateFile((path + "/uninstal.sql"), _sqlAdminSqlUninstallContent);
  }
  
  public CharSequence sqlAdminSqlInstallContent(final String extensionName, final boolean isupdate) {
    LinkedList<String> visited = new LinkedList<String>();
    StringBuffer result = new StringBuffer();
    while ((visited.size() < this.entities.size())) {
      for (final ExtendedEntity e : this.entities) {
        {
          boolean _and = false;
          EList<ExtendedReference> _extendedReference = e.getExtendedReference();
          boolean _isEmpty = _extendedReference.isEmpty();
          if (!_isEmpty) {
            _and = false;
          } else {
            String _name = e.getName();
            boolean _contains = visited.contains(_name);
            boolean _not = (!_contains);
            _and = _not;
          }
          if (_and) {
            CharSequence _generateSQLTable = this.generateSQLTable(e, isupdate, extensionName);
            result.append(_generateSQLTable);
            String _name_1 = e.getName();
            visited.add(_name_1);
          }
          boolean _and_1 = false;
          boolean _and_2 = false;
          String _name_2 = e.getName();
          boolean _contains_1 = visited.contains(_name_2);
          boolean _not_1 = (!_contains_1);
          if (!_not_1) {
            _and_2 = false;
          } else {
            EList<Reference> _references = e.getReferences();
            boolean _isEmpty_1 = _references.isEmpty();
            boolean _not_2 = (!_isEmpty_1);
            _and_2 = _not_2;
          }
          if (!_and_2) {
            _and_1 = false;
          } else {
            EList<ExtendedReference> _extendedReference_1 = e.getExtendedReference();
            boolean _isAllreferenVisited = this.isAllreferenVisited(_extendedReference_1, visited);
            _and_1 = _isAllreferenVisited;
          }
          if (_and_1) {
            CharSequence _generateSQLTable_1 = this.generateSQLTable(e, isupdate, extensionName);
            result.append(_generateSQLTable_1);
            String _name_3 = e.getName();
            visited.add(_name_3);
          }
        }
      }
    }
    return result.toString();
  }
  
  public boolean isAllreferenVisited(final EList<ExtendedReference> list, final List<String> visited) {
    for (final ExtendedReference r : list) {
      Entity _extendedToEntity = r.getExtendedToEntity();
      String _name = _extendedToEntity.getName();
      boolean _contains = visited.contains(_name);
      boolean _not = (!_contains);
      if (_not) {
        return false;
      }
    }
    return true;
  }
  
  public CharSequence generateSQLTable(final ExtendedEntity table, final boolean isupdate, final String componentName) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((!isupdate)) {
        _builder.append("    ");
        _builder.append("DROP TABLE IF EXISTS `");
        String _lowerCase = componentName.toLowerCase();
        _builder.append(_lowerCase, "    ");
        _builder.append("_");
        String _name = table.getName();
        String _lowerCase_1 = _name.toLowerCase();
        _builder.append(_lowerCase_1, "    ");
        _builder.append("`;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("   ");
    _builder.append("CREATE TABLE ");
    {
      if (isupdate) {
        _builder.append(" IF NOT EXISTS ");
      }
    }
    _builder.append("`");
    String _lowerCase_2 = componentName.toLowerCase();
    _builder.append(_lowerCase_2, "   ");
    _builder.append("_");
    String _name_1 = table.getName();
    String _lowerCase_3 = _name_1.toLowerCase();
    _builder.append(_lowerCase_3, "   ");
    _builder.append("` (");
    _builder.newLineIfNotEmpty();
    {
      EList<ExtendedAttribute> _allattribute = table.getAllattribute();
      for(final ExtendedAttribute a : _allattribute) {
        _builder.append("\t");
        _builder.append("`");
        String _name_2 = a.getName();
        String _lowerCase_4 = _name_2.toLowerCase();
        _builder.append(_lowerCase_4, "\t");
        _builder.append("` ");
        String _generatorType = a.generatorType();
        String _lowerCase_5 = _generatorType.toLowerCase();
        _builder.append(_lowerCase_5, "\t");
        _builder.append(",");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("PRIMARY KEY (`id`)");
    _builder.newLine();
    {
      EList<ExtendedAttribute> _extendedAttributeList = table.getExtendedAttributeList();
      for(final ExtendedAttribute a_1 : _extendedAttributeList) {
        {
          boolean _isIsunique = a_1.isIsunique();
          if (_isIsunique) {
            _builder.append("\t");
            _builder.append(",UNIQUE KEY (");
            String _name_3 = a_1.getName();
            _builder.append(_name_3, "\t");
            CharSequence _xifexpression = null;
            Attribute _withattribute = a_1.getWithattribute();
            boolean _notEquals = (!Objects.equal(_withattribute, null));
            if (_notEquals) {
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append(",");
              Attribute _withattribute_1 = a_1.getWithattribute();
              String _name_4 = _withattribute_1.getName();
              _builder_1.append(_name_4, "");
              _xifexpression = _builder_1;
            }
            _builder.append(_xifexpression, "\t");
            _builder.append(")");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      EList<Reference> _references = table.getReferences();
      for(final Reference ref : _references) {
        _builder.append("\t");
        _builder.append(",INDEX(");
        EList<Attribute> _attribute = ref.getAttribute();
        CharSequence _transformAttributeListInString = Slug.transformAttributeListInString(_attribute, ", ");
        _builder.append(_transformAttributeListInString, "\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Reference> _references_1 = table.getReferences();
      for(final Reference ref_1 : _references_1) {
        _builder.append("\t");
        _builder.append(",CONSTRAINT `");
        String _lowerCase_6 = componentName.toLowerCase();
        _builder.append(_lowerCase_6, "\t");
        _builder.append("_");
        String _name_5 = table.getName();
        String _lowerCase_7 = _name_5.toLowerCase();
        _builder.append(_lowerCase_7, "\t");
        _builder.append("_ibfk_");
        EList<Reference> _references_2 = table.getReferences();
        int _indexOf = _references_2.indexOf(ref_1);
        _builder.append(_indexOf, "\t");
        _builder.append("` FOREIGN KEY(");
        EList<Attribute> _attribute_1 = ref_1.getAttribute();
        CharSequence _transformAttributeListInString_1 = Slug.transformAttributeListInString(_attribute_1, ",");
        _builder.append(_transformAttributeListInString_1, "\t");
        _builder.append(") REFERENCES `");
        String _lowerCase_8 = componentName.toLowerCase();
        _builder.append(_lowerCase_8, "\t");
        _builder.append("_");
        Entity _entity = ref_1.getEntity();
        String _name_6 = _entity.getName();
        String _lowerCase_9 = _name_6.toLowerCase();
        String _slugify = Slug.slugify(_lowerCase_9);
        _builder.append(_slugify, "\t");
        _builder.append("` (");
        EList<Attribute> _attributerefereced = ref_1.getAttributerefereced();
        CharSequence _transformAttributeListInString_2 = Slug.transformAttributeListInString(_attributerefereced, ", ");
        _builder.append(_transformAttributeListInString_2, "\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("    ");
        _builder.append("ON UPDATE CASCADE");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("    ");
        _builder.append("ON DELETE CASCADE");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence sqlAdminSqlUninstallContent(final String extensionName) {
    LinkedList<String> visited = new LinkedList<String>();
    StringBuffer result = new StringBuffer();
    result.append("SET foreign_key_checks = 0;");
    result.append("\n\r");
    while ((visited.size() < this.entities.size())) {
      for (final ExtendedEntity e : this.entities) {
        {
          boolean _and = false;
          EList<ExtendedReference> _extendedReference = e.getExtendedReference();
          boolean _isEmpty = _extendedReference.isEmpty();
          if (!_isEmpty) {
            _and = false;
          } else {
            String _name = e.getName();
            boolean _contains = visited.contains(_name);
            boolean _not = (!_contains);
            _and = _not;
          }
          if (_and) {
            String _name_1 = e.getName();
            visited.add(_name_1);
          }
          boolean _and_1 = false;
          boolean _and_2 = false;
          String _name_2 = e.getName();
          boolean _contains_1 = visited.contains(_name_2);
          boolean _not_1 = (!_contains_1);
          if (!_not_1) {
            _and_2 = false;
          } else {
            EList<Reference> _references = e.getReferences();
            boolean _isEmpty_1 = _references.isEmpty();
            boolean _not_2 = (!_isEmpty_1);
            _and_2 = _not_2;
          }
          if (!_and_2) {
            _and_1 = false;
          } else {
            EList<ExtendedReference> _extendedReference_1 = e.getExtendedReference();
            boolean _isAllreferenVisited = this.isAllreferenVisited(_extendedReference_1, visited);
            _and_1 = _isAllreferenVisited;
          }
          if (_and_1) {
            String _name_3 = e.getName();
            visited.add(_name_3);
          }
        }
      }
    }
    while ((!visited.isEmpty())) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("DROP TABLE IF EXISTS `#__");
        String _lowerCase = extensionName.toLowerCase();
        _builder.append(_lowerCase, "");
        _builder.append("_");
        String _removeLast = visited.removeLast();
        String _lowerCase_1 = _removeLast.toLowerCase();
        _builder.append(_lowerCase_1, "");
        _builder.append("`;");
        result.append(_builder);
        result.append("\n\r");
      }
    }
    return result.toString();
  }
}
