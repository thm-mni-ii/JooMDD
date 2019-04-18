package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator

import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug

class IndexPageTemplateHelper {
        
    def CharSequence genModelAttributeReference(EList<ExtendedAttribute>column, ExtendedDynamicPage indexpage, ExtendedComponent component ) {        
        return '''
            «FOR ExtendedAttribute attr : column»
                «IF Slug.isAttributeLinked(attr, indexpage)»
                    «Slug.linkOfAttribut(attr, indexpage,  component.name, "$item->", "$this")»
                «ELSE»
                    <td>
                    «IF attr.entity instanceof MappingEntity»
                    «var listVariableName = '''$«attr.name.toFirstLower»List'''»
                    <?php
                        «listVariableName» = array();
                        foreach ($item->«attr.name» as $value) { ?>
                            <?php «listVariableName»[] = $this->escape($value); ?>
                        <?php
                        }
                        echo implode(" ", «listVariableName»); ?>
                    «ELSE»
                        <?php echo $this->escape($item->«attr.name»); ?>
                    «ENDIF»
                    </td>
                «ENDIF»
            «ENDFOR»
        '''   
    } 
}