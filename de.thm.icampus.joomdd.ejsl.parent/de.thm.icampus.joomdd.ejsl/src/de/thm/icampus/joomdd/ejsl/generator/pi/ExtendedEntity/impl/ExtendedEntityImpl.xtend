package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl

import de.thm.icampus.joomdd.ejsl.eJSL.impl.EntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.generator.pi.util.PlattformUtil
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity
import org.eclipse.xtend.lib.annotations.Accessors

class ExtendedEntityImpl extends EntityImpl implements ExtendedEntity {

    Entity instance
    EList<ExtendedAttribute> ownExtendedAttributes
    EList<ExtendedAttribute> parentExtendedAttributes
    EList<ExtendedAttribute> allExtendedAttributes
    EList<ExtendedReference> allExtendedReferences
    EList<ExtendedReference> allReferencesToEntity
    EList<ExtendedAttribute> allRefactoryAttribute
    EList<ExtendedReference> allRefactoryReference
    ExtendedAttribute primaryAttribute
    boolean isMappingEntity = false

    new(Entity entity) {
        setParentProperties(entity)
        instance = entity
        
        initLists()

        if (entity instanceof MappingEntity) {
            isMappingEntity = true
        }
    }
    
    override setParentProperties(Entity entity) {
        this.name = PlattformUtil.slugify(entity.name)
        this.supertype = entity.supertype
        this.attributes = entity.attributes
        this.references = entity.references
        this.preserve = entity.preserve
    }

    override getOwnExtendedAttributes() {
        return ownExtendedAttributes

    }

    override getParentExtendedAttributes() {
        return parentExtendedAttributes
    }

    override getInstance() {
        return instance

    }

    override getAllExtendedAttributes() {
        return allExtendedAttributes
    }

    def void initLists() {
        ownExtendedAttributes = new BasicEList<ExtendedAttribute>
        allExtendedAttributes = new BasicEList<ExtendedAttribute>
        allReferencesToEntity = new BasicEList<ExtendedReference>
        allExtendedReferences = new BasicEList<ExtendedReference>
                
        ownExtendedAttributes.addAll(this.attributes.map [ attr |
            if (attr instanceof ExtendedAttributeImpl) {
                attr
            } else {
                new ExtendedAttributeImpl(attr)
            }
        ])
        parentExtendedAttributes = searchAttributeParent()

        allExtendedAttributes.addAll(ownExtendedAttributes)
        allExtendedAttributes.addAll(parentExtendedAttributes)
        allExtendedReferences.addAll(references.map [ t |
            if (t instanceof ExtendedReferenceImpl) {
                t
            } else {
                new ExtendedReferenceImpl(t, this.instance)
            }
        ])
        var EList<Entity> allEntity = (instance.eContainer as Feature).entities
        for (Entity ent : allEntity) {
            if (ent.references != null) {
                var listRef = ent.references.filter [ t |
                    t.entity.name == instance.name
                ].toList

                for (Reference ref : listRef) {
                    if (ref.upper.equals("1")) {
                        if (ref instanceof ExtendedReferenceImpl) {
                            allReferencesToEntity.add(ref)
                        } else {
                            allReferencesToEntity.add(new ExtendedReferenceImpl(ref, ent))
                        }
                    }
                }
            }
        }

        allRefactoryAttribute = new BasicEList<ExtendedAttribute>
        allRefactoryReference = new BasicEList<ExtendedReference>
        allRefactoryAttribute.addAll(ownExtendedAttributes.filter[t|!t.preserve])
        allRefactoryReference.addAll(allExtendedReferences.filter[t|!t.preserve])

        for (ExtendedAttribute attr : ownExtendedAttributes) {
            if (attr.isIsprimary)
                primaryAttribute = attr;
        }

    }

    def EList<ExtendedAttribute> searchAttributeParent() {
        var EList<ExtendedAttribute> result = new BasicEList<ExtendedAttribute>
        var Entity parent = this.supertype

        while (parent != null) {
            result.addAll(parent.attributes.map [ attr |
                if (attr instanceof ExtendedAttributeImpl) {
                    attr
                } else {
                    new ExtendedAttributeImpl(attr)
                }
            ])
            parent = parent.supertype

        }

        return result
    }

    override boolean hasIdAttribute() {
        for (attr : ownExtendedAttributes) {
            if (attr.name.equalsIgnoreCase("id")) {
                attr.name = "id"
                return true
            }
        }
        return false
    }

    override getAllExtendedReferences() {
        return allExtendedReferences
    }

    override getAllExtendedReferencesToEntity() {
        return allReferencesToEntity
    }

    override getExtendedAttributeByName(String name) {
        for (ExtendedAttribute attr : ownExtendedAttributes) {
            if (attr.name.equalsIgnoreCase(name))
                return attr;
        }
        return null
    }

    override getAllRefactoryAttribute() {
        return allRefactoryAttribute
    }

    override getAllRefactoryReference() {
        return allRefactoryReference
    }

    override isGenerated() {
        if (name.startsWith("mappingMDD") && references.size == 2) {
            return true
        }
        return false
    }

    override getPrimaryKey() {
        return primaryAttribute
    }

    override getFirstUniqueKey() {
        return ownExtendedAttributes.filter[t|t.isIsunique].get(0);
    }

    override searchRefWithAttr(Attribute attribute, Entity entity) {
        
       for (ExtendedReference ref : this.allExtendedReferences) {
            if (ref.attribute.contains(attribute) && ref.entity === entity) {
                return ref
            }
        }  

        return null
    }

    override searchListRefWithAttr(Attribute attribute) {
        var EList<ExtendedReference> result = new BasicEList<ExtendedReference>()

        for (ExtendedReference ref : this.allExtendedReferences) {
            if (ref.attribute.contains(attribute)) {
                result.add(ref)
            }
        }

        return result
    }

    override getIsMappingEntity() {
        this.isMappingEntity
    }
}
