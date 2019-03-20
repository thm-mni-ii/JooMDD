package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.impl

import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.impl.DynamicPageImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedEntityImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDetailPageField
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterGroupImpl
import de.thm.icampus.joomdd.ejsl.generator.pi.util.impl.ExtendedParameterImpl
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity
import java.util.stream.IntStream
import de.thm.icampus.joomdd.ejsl.generator.pi.util.TableColumn

class ExtendedDynamicPageImpl extends DynamicPageImpl implements ExtendedDynamicPage {

    DynamicPage instance
    EList<ExtendedAttribute> extendedTableColumnList
    EList<ExtendedAttribute> extendedFiltersList
    EList<ExtendedDetailPageField> extendedEditFieldsList
    EList<ExtendedParameterGroup> extendedParameterGroupList
    EList<ExtendedParameter> extendedGlobalParameterList
    EList<ExtendedParameter> extendedLocalParameterList
    EList<ExtendedAttribute> allAttributeOfFilterAndColum
    boolean haveFile = false
    EList<ExtendedEntity> extendedEntity
    Boolean isDetailsPage = false

    new(DynamicPage page) {
        instance = page
        this.entities = page.entities
        this.name = page.name
        this.parametergroups = page.parametergroups
        this.globalparameters = page.globalparameters
        this.localparameters = page.localparameters
        this.tablecolumns = page.tablecolumns
        this.filters = page.filters
        this.links = page.links
        this.preserve = page.preserve
        initList()
        setHaveFile()
    }

    def setHaveFile() {
        for (ExtendedDetailPageField t : extendedEditFieldsList) {
            if (t.type.equalsIgnoreCase("Imagepicker") || t.type.equalsIgnoreCase("Filepicker"))
                haveFile = true
        }
    }

    def initList() {
        extendedTableColumnList = new BasicEList<ExtendedAttribute>
        extendedFiltersList = new BasicEList<ExtendedAttribute>
        extendedEditFieldsList = new BasicEList<ExtendedDetailPageField>
        allAttributeOfFilterAndColum = new BasicEList<ExtendedAttribute>
        extendedEntity = new BasicEList<ExtendedEntity>

        extendedEntity.addAll(this.entities.map [ t |
            new ExtendedEntityImpl(t)
        ])

        // TODO: There might be more than one entity.
        var extEntity = extendedEntity.get(0)
        for (attribute : this.tablecolumns) {
            var extendedAttribute = extEntity.allExtendedAttributes.findFirst [ extAttr |
                extAttr.instance === attribute
            ]

            if (extendedAttribute === null) {
                extendedAttribute = new ExtendedAttributeImpl(attribute)
            }
            extendedTableColumnList.add(extendedAttribute)
        }

        for (attribute : this.filters) {
            var extendedAttribute = extEntity.allExtendedAttributes.findFirst [ extAttr |
                extAttr.instance === attribute
            ]

            if (extendedAttribute === null) {
                extendedAttribute = new ExtendedAttributeImpl(attribute)
            }
            extendedFiltersList.add(extendedAttribute)
        }

        if (instance instanceof DetailsPage) {
            this.isDetailsPage = true
            var DetailsPage dpg = instance as DetailsPage
            extendedEditFieldsList.addAll(dpg.editfields.map [ t |
                new ExtendedDetailPageFieldImpl(t)
            ])
        }
        extendedGlobalParameterList = new BasicEList<ExtendedParameter>
        extendedGlobalParameterList.addAll(this.globalparameters.map [ t |
            new ExtendedParameterImpl(t)
        ])
        extendedLocalParameterList = new BasicEList<ExtendedParameter>
        extendedLocalParameterList.addAll(this.localparameters.map [ t |
            new ExtendedParameterImpl(t)
        ])
        extendedParameterGroupList = new BasicEList<ExtendedParameterGroup>
        extendedParameterGroupList.addAll(this.parametergroups.map [ t |
            new ExtendedParameterGroupImpl(t)
        ])

        for (ExtendedAttribute colum : extendedTableColumnList) {
            var boolean isInList = false
            for (ExtendedAttribute filter : extendedFiltersList) {
                if (colum.name.equalsIgnoreCase(filter.name)) {
                    isInList = true
                }
            }
            if (!isInList)
                allAttributeOfFilterAndColum.add(colum)
        }
        allAttributeOfFilterAndColum.addAll(extendedFiltersList)
    }

    override getExtendedTableColumnList() {
        return this.extendedTableColumnList
    }

    override getExtendFiltersList() {
        return this.extendedFiltersList
    }

    override getExtendedEditedFieldsList() {
        return this.extendedEditFieldsList
    }

    override getExtendedEntityList() {
        return extendedEntity
    }

    override getInstance() {
        return instance
    }

    override isDetailsPage() {
        return this.isDetailsPage
    }

    override getExtendedParametersGroupsListe() {
        return this.extendedParameterGroupList
    }

    override getExtendedGlobalParametersListe() {
        return this.extendedGlobalParameterList
    }

    override getExtendedLocalParametersListe() {
        return this.extendedLocalParameterList
    }

    override getAllAttributeOfFilterAndColum() {
        return allAttributeOfFilterAndColum
    }

    override haveFiletoLoad() {
        return haveFile
    }

    override containsParamertergroup(String paramenterGroupName) {
        for (ExtendedParameterGroup d : extendedParameterGroupList) {
            if (d.name.equalsIgnoreCase(paramenterGroupName))
                return true
        }
        return false
    }

    override getTextColumn(ExtendedAttribute attribute, EList<ExtendedEntity> extendedEntityList) {
        var extendedEntity = extendedEntityList.findFirst [ extendedEntity |
            extendedEntity.instance === attribute.entity
        ]

        if (extendedEntity !== null) {
            val reference = extendedEntity.allRefactoryReference.findFirst [ reference |
                reference.attribute.exists [ referenceAttribute |
                    referenceAttribute.name.equals(attribute.name)
                ]
            ]

            if (reference !== null) {
                // An attribute with a reference
                var indexOpt = IntStream.range(0, reference.attribute.size()).filter [ i |
                    reference.attribute.get(i).name.equals(attribute.name)
                ].findFirst();

                if (indexOpt.present === true) {
                    var referencedAttribute = reference.attributerefereced.get(indexOpt.asInt)
                    var type = attribute.entity.name
                    
                    return new TableColumn(reference.entity.name, referencedAttribute.name, type)
                } else {
                    throw new UnsupportedOperationException
                }
            } else {
                // An attribute with no reference
                return new TableColumn(attribute.entity.name, attribute.name)
            }
        } else {
            throw new UnsupportedOperationException
        }
    }

    override getValueColumn(ExtendedAttribute attribute, EList<ExtendedEntity> extendedEntityList) {
        this.getTextColumn(attribute, extendedEntityList)
    }
}
