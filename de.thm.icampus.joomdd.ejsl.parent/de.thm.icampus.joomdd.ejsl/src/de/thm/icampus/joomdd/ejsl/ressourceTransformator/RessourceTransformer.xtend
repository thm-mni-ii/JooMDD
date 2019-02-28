package de.thm.icampus.joomdd.ejsl.ressourceTransformator

import de.thm.icampus.joomdd.ejsl.eJSL.EJSLModel
import de.thm.icampus.joomdd.ejsl.eJSL.CMSExtension
import de.thm.icampus.joomdd.ejsl.eJSL.Feature
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.eJSL.DetailPageField
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.DatatypeReference
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLFactory
import de.thm.icampus.joomdd.ejsl.eJSL.SimpleHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypes
import de.thm.icampus.joomdd.ejsl.eJSL.Page
import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.BasicEList
import de.thm.icampus.joomdd.ejsl.eJSL.Reference
import de.thm.icampus.joomdd.ejsl.eJSL.Type
import de.thm.icampus.joomdd.ejsl.eJSL.InternalLink
import de.thm.icampus.joomdd.ejsl.eJSL.StandardTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.eJSL.StaticPage
import de.thm.icampus.joomdd.ejsl.eJSL.Link
import de.thm.icampus.joomdd.ejsl.eJSL.ContextLink
import de.thm.icampus.joomdd.ejsl.eJSL.LinkParameter
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypeKinds
import de.thm.icampus.joomdd.ejsl.eJSL.ComplexHTMLTypes
import de.thm.icampus.joomdd.ejsl.eJSL.ExtensionPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.Module
import de.thm.icampus.joomdd.ejsl.eJSL.Plugin
import de.thm.icampus.joomdd.ejsl.eJSL.Library
import de.thm.icampus.joomdd.ejsl.eJSL.Template
import de.thm.icampus.joomdd.ejsl.eJSL.Language
import org.eclipse.xtext.EcoreUtil2
import java.util.ArrayList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * this class transforme and complete the ejsl model for the generator.
 * the big points are: 
 * -Solving of the mutilple reference between two entities 
 * -the copy from  attribute of a entity to the tablecolumn an filters of a page.
 * -Parsing of attribute type
 */
class RessourceTransformer {
	EJSLModel modelInstance
	CMSExtension cmsextension
	Feature feature
	new (EJSLModel model){
		modelInstance = model
		
		cmsextension = model.ejslPart as CMSExtension
		feature = cmsextension.feature
		
	}
	def dotransformation(){
		feature.entities.forEach[t | t.name = Util.slugify(t.name)]
		feature.pages.forEach[t | t.name = Util.slugify(t.name)]
		for(Entity ent: feature.entities){
			this.completeAttributeOfEntity(ent)
		}
		for(Entity ent: feature.entities){
    		completeReferenceOfEntity(ent)
    		setReferenceAttribute(ent)
    	}
    	
    	createDefaultLanguageIfNoLanguageIsPresent("en-GB")
        createNtoMLanguage()
    	createSimple1ToNReference(feature.entities)
		createMappingsTable(feature.entities)
		formatEntitiesAttribute(feature.entities)
		completeDetailsPage();
		completeIndexPage();
		
		var JoomlaTranformator jt = new JoomlaTranformator(modelInstance)
		jt.completeCMSExtension
		completePage(feature.pages)
		
	}
	
	def createDefaultLanguageIfNoLanguageIsPresent(String defaultLanguage) {
        cmsextension.extensions.forEach[ cmsextension | 
            switch cmsextension {
                ExtensionPackage: {
                    //TODO                    
                }   
                
                Component: {
                    var component = cmsextension
                    if (component.languages.empty) {
                        addLanguage(component.languages, defaultLanguage)
                    }
                }
                
                Module: {
                    var module = cmsextension
                    if (module.languages.empty) {
                        addLanguage(module.languages, defaultLanguage)
                    }
                }
                
                Plugin: {
                    //TODO                    
                }
                
                Library: {
                    //TODO
                }
                
                Template: {
                    //TODO
                } 
            }
        ]
    }
    
    private def addLanguage(EList<Language> languages, String language) {
        var Language lang = EcoreUtil2.create(EJSLPackage.Literals.LANGUAGE) as Language
        lang.name = language
        lang.sys = false
        languages.add(EcoreUtil2.copy(lang))
        lang.sys = true
        languages.add(EcoreUtil2.copy(lang)) 
    }
    
    private def createNtoMLanguage() {
        cmsextension.extensions.forEach[ cmsextension | 
            switch cmsextension {
                ExtensionPackage: {
                    //TODO                    
                }   
                
                Component: {
                    var component = cmsextension
                    
                    for (section : component.sections){
                        for (pageRef : section.pageRef){
                            var page = pageRef.page 
                            var ArrayList<KeyValuePair> keyValuePairList = getNtoMLanguageForPage(page, Slug.nameExtensionBind("com", component.name))
                            
                            for (language : component.languages){
                                language.keyvaluepairs.addUniqueKeyValuePair(keyValuePairList)
                            }
                        }
                    }
                }
                
                Module: {
                    var module = cmsextension
                    var page = module.pageRef.page
                    var keyValuePairList = getNtoMLanguageForPage(page, Slug.nameExtensionBind("mod", module.name))
                    
                    for (language : module.languages){
                        language.keyvaluepairs.addUniqueKeyValuePair(keyValuePairList)
                    }
                }
                
                Plugin: {
                    //TODO                    
                }
                
                Library: {
                    //TODO
                }
                
                Template: {
                    //TODO
                } 
            }
        ]
    }
    
    private def addUniqueKeyValuePair(EList<KeyValuePair> keyValuePairList, ArrayList<KeyValuePair> additionKeyValuePairList){
        var keyValuePairElementListToAdd = additionKeyValuePairList.filter[ addKeyValuePair |
            keyValuePairList.exists[ keyValuePair | 
                keyValuePair.name.equals(addKeyValuePair.name)
            ] === false
        ]
        
        for ( keyValuePair : keyValuePairElementListToAdd)
        {
            keyValuePairList.add(EcoreUtil2.copy(keyValuePair))
        }
    }
    
    private def ArrayList<KeyValuePair> getNtoMLanguageForPage(Page page, String extensionName) {
        var ArrayList<KeyValuePair> keyValuePairList = newArrayList()
        
        if (page instanceof DynamicPage){
            for (entity : page.entities){
                if (entity.references !== null) {
                    for (reference : entity.references){ 
                        if (reference.upper.equals("-1")){
                            // TODO: Multiple attributes are possible but right now we only consider the first.
                            var key = newArrayList(
                                extensionName,
                                "FORM",
                                "LBL",
                                entity.name,
                                reference.entity.name,
                                reference.attributerefereced.get(0).name
                            ).join("_")
                            
                            key = Slug.slugify(key).toUpperCase
                            
                            var value = reference.attribute.get(0).name
                            
                            var KeyValuePair keyValuePair = EcoreUtil2.create(EJSLPackage.Literals.KEY_VALUE_PAIR) as KeyValuePair
                            keyValuePair.name = key
                            keyValuePair.value = value
                            
                            keyValuePairList.add(EcoreUtil2.copy(keyValuePair))
                            
                            var filterKey = newArrayList(
                                "JOPTION",
                                "SELECT",
                                reference.entity.name,
                                reference.attributerefereced.get(0).name
                            ).join("_")
                            
                            filterKey = Slug.slugify(filterKey).toUpperCase
                            var KeyValuePair filterKeyValuePair = EcoreUtil2.create(EJSLPackage.Literals.KEY_VALUE_PAIR) as KeyValuePair
                            filterKeyValuePair.name = filterKey
                            filterKeyValuePair.value = '''Select a «value»'''
                            
                            keyValuePairList.add(EcoreUtil2.copy(filterKeyValuePair))
                        }
                    }
                }
            }
        }
        
        return keyValuePairList
    }
    
	/**
	 * Create a primary attribute, when it is not exist,
	 * replace the id symbol with the primary keys,
	 * complete the reference with the new generated primary keys or the keys pair
	 * and create many attributes for the joomla specified generator
	 * @param Entity ent contains a entity
	 */
	private def completeAttributeOfEntity(Entity ent) {
		if(!ent.havePrimaryAttribute()){
			
			var Attribute id = EJSLFactory.eINSTANCE.createAttribute
			id.name = "id"
			var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
			typeid.type =  StandardTypeKinds.INTEGER
			typeid.notnull = true
			typeid.autoincrement = true
			id.type = typeid
			id.isprimary = true
			ent.attributes.add(id)
			
		}
		for(Attribute attr: ent.attributes){
			 if(attr.id){
			 	attr.withattribute = ent.searchIdAttribute
			 	attr.id = false;
			 }
		}
		
		
		}
		/**
	 * Search an return if the entity have a primary keys
	 * @param Entity entity contains a entity
	 * 
	 */
	private def Attribute searchIdAttribute(Entity entity){
		for(Attribute e: entity.attributes){
			if(e.name.equalsIgnoreCase("id") || e.name.equalsIgnoreCase("^id") || e.isIsprimary)
			return e
		}
		return null
	}
	

	/**
	 * Check if the entity contains a attribute
	 * @param Entity entity        contains a entity
	 * @param String attributeNam  contains a attribute name
	 */
	private def boolean haveAttribute(Entity entity, String attributeName){
		for(Attribute e: entity.attributes){
			if(e.name.equals(attributeName))
			return true
		}
		return false
	}
	
	/**
	 * replace the id symboile in the unique keys definition with a id object
	 * and create the attribute pair of an referenced attribute in the reference entity
	 * @param Entity ent contains a entity
	 */
	private def void  completeReferenceOfEntity(Entity ent) {
		
		for(Reference ref : ent.references){
			
			for(Attribute refatt : ref.attributerefereced){
				if(!refatt.isprimary && !refatt.isunique && refatt.withattribute === null && !refatt.id){
					var Attribute idref = this.searchIdAttribute(ref.entity);
					refatt.isunique = true;
					refatt.id = true;
					if(idref=== null){
						refatt.id= true;
						
					}else{
						refatt.withattribute = idref
					}
					
				}
			}
			if(ref.id){
				var Attribute id = ref.entity.searchIdAttribute
				if( id !== null){
				ref.attributerefereced.add(id)
				ref.id=false
				if(	ref.attributerefereced.size > 1){
					if(getAttributeReference(ent, ref.entity, id ) === null){
						ref.attribute.add(setNewGenAttribute(ent,ref, id))
					}
				}
				
				}
			}
			
			
		}
	}
	/**
	 * Complete the reference of an entity , when the attribute are unique with an other.
	 * @param Entity ent contains a Entity
	 */
	def void setReferenceAttribute( Entity ent){
		for(Reference ref:ent.references){
			var Entity referenceEntity = ref.entity
			var EList<Attribute> newAttribute = new BasicEList<Attribute>
			for(Attribute attr: ref.attribute){
				var Attribute uniqWith = attr.withattribute
				if(uniqWith !== null && !ref.attribute.contains(uniqWith)){
					newAttribute.add(uniqWith)
				}
			}
			ref.attribute.addAll(newAttribute.filter[t | !containsAttribute(t, ref.attribute)])
			
			
			var EList<Attribute> newReferenceArttibute = new BasicEList<Attribute>
			for(Attribute attrRef: ref.attributerefereced){
				var Attribute uniqWith = attrRef.withattribute
				if(uniqWith !== null ){
					var Attribute attr = getAttributeReference(ent, referenceEntity,uniqWith)
					if(attr === null){
					 var Attribute refAttrEnt = setNewGenAttribute(ent, ref, uniqWith)
					 var Attribute hereAttr = ref.attribute.get(ref.attributerefereced.indexOf(attrRef))
					 //hereAttr.isunique = true;
					 hereAttr.withattribute = refAttrEnt
					 
					newReferenceArttibute.add(uniqWith)
					}else{
						newReferenceArttibute.add(uniqWith)
					}
				}
			}
			ref.attributerefereced.addAll(newReferenceArttibute.filter[t | !this.containsAttribute(t,ref.attributerefereced)])
			
			}
		
	}
	
	def Boolean containsAttribute(Attribute attribute, EList<Attribute> list) {
		
		for(Attribute attr: list){
			if(attr.name.equalsIgnoreCase(attribute.name))
			return true
		}
		return false
	}
	
	/**
	 * Create a new attribute and complete a reference.
	 * @param Entity    ent      contains a entity
	 * @param Reference ref      contains a refrence
	 * @param Attribute attrRef  contains a attribute to clone
	 */
	def Attribute setNewGenAttribute(Entity ent, Reference ref, Attribute attrRef){
		var Entity referenceEntity = ref.entity
		var Attribute newAttribute = EJSLFactory.eINSTANCE.createAttribute
					newAttribute.name = referenceEntity.name.toString.toLowerCase + "_" + attrRef.name.toLowerCase
					newAttribute.type =  Util.copyType(attrRef.type)
					if(newAttribute.type instanceof StandardTypes)
					{
					var StandardTypes typs = newAttribute.type  as StandardTypes
					typs.notnull = false
					}
					ent.attributes.add(newAttribute)
					ref.attribute.add(newAttribute)
										
					return newAttribute
	}
	
	
	/**
	 * check a attribute if the a attribute to clone already exist in the referenced attribute.
	 * @param Entity    ent               contains the origin entity
	 * @param Entity    referencedEntity  contains the referenced entity
	 * @param Attribute referenced        contains to referenced attribute
	 */
	def Attribute getAttributeReference(Entity ent, Entity referencedEntity, Attribute referenced){
		for(Attribute a:ent.attributes ){
			if(a.name.equalsIgnoreCase(referencedEntity.name.toString.toLowerCase +  "_"+ referenced.name))
			return a;
		}
		for(Reference ref:ent.references ){
			
			if(ref.entity.name == referencedEntity.name && ref.attributerefereced.filter[t | t.name.equalsIgnoreCase(referenced.name)].size>0 ){
				var int index = ref.attributerefereced.indexOf(referenced)	
				return ref.attribute.get(index)
			}
		}
		return null
	}
	
	/**
	 * Complete the context Links of a dynamic page, add the id key and refrenced attribute
	 * @param EList<Page> pageList contains a list of page
	 */
	def void completePage(EList<Page> pageList){
		for(Page pg: pageList.filter[t | !(t instanceof StaticPage)]){
			var Entity fromEnt = null
			switch pg{
				DynamicPage:{
					var DynamicPage fromDynPage = pg as DynamicPage
					fromEnt = fromDynPage.entities.get(0)
				for(Link lk : pg.links.filter[t | t instanceof ContextLink]){
				var ContextLink ctLink = lk as ContextLink
				if(ctLink.target instanceof DynamicPage){
					var DynamicPage toDynPage =  ctLink.target as DynamicPage
					var Entity toEnt = toDynPage.entities.get(0) 
					if(ctLink.linkparameters.size != 0)
					completeContextLink(ctLink, fromEnt, toEnt )
				}
				}
			}
				
		  }
		
		}
	}
	
	/**
	 * Complete the the contextlink, when the link have a id, it replace it with the id object of the entity
	 * @param ContextLink link        contains a Link
	 * @param Entity      fromEntity  contains the start entity
	 * @param Entity      toEntity    contain the target entity
	 */
	def void completeContextLink(ContextLink link, Entity fromEntity, Entity toEntity) {
		
		for(LinkParameter lkParam: link.linkparameters){
			if(lkParam.id){
				if(fromEntity.name != toEntity.name){
					lkParam.attvalue = searchReferenceOfID(fromEntity, toEntity)
				}else{
					lkParam.attvalue = searchIdAttribute(fromEntity)
				}
				lkParam.id = false
			}
			
		}
	}
	/**
	 * searchs and returns the attribute, that have a reference to the id.
	 * @param Entity fromEntity  contains the start entity
	 * @param Entity toEntity    contains the target entity
	 */
	def Attribute searchReferenceOfID(Entity fromEntity,Entity toEntity) {
		var Attribute id = searchIdAttribute(toEntity)
		for(Reference ref: fromEntity.references){
			var Attribute result = getAttributeReference(fromEntity, toEntity,id)
			if(result != null)
			return result
		}
		return null
	}
	
	/** 
	 * Check if the entity have a primary keys
	 * @param Entity entity contains a entity
	 */
	private def boolean havePrimaryAttribute(Entity entity){
		for(Attribute e: entity.attributes){
			if(e.isIsprimary)
			return true
		}
		return false
	}
	def formatEntitiesAttribute(EList<Entity> list) {
		for(Entity ent: list){
			ent.name = Util.slugify(ent.name)
			for(Attribute attr: ent.attributes){
			   attr.name = Util.slugify(attr.name)
			   var type = attr.type.getType()
			   if(attr.isIsprimary && type.equalsIgnoreCase("text")){
			   	var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
				typeid.type =  StandardTypeKinds.INTEGER
				typeid.notnull = true
				typeid.autoincrement = true
			   	 attr.type =  typeid
			   }}
		}
	}
	
	def String getType(Type type){
		switch(type){
			DatatypeReference:{
				var DatatypeReference dt = type as DatatypeReference
				return dt.type.name
				
			} 
			 StandardTypes:{
			 	var StandardTypes dt = type as StandardTypes
			 	return dt.type.getName()
			 }
		}
	}
	
	def completeIndexPage() {
			for(Page page: feature.pages.filter[t | t instanceof IndexPage]){
			var IndexPage dp = page as IndexPage
			completeColUmnAndFilterList(dp)
		}
	}
	
	def completeDetailsPage(){
		
		for(Page page: feature.pages.filter[t | t instanceof DetailsPage ]){
			var DetailsPage dp = page as DetailsPage
			completeTableColumnAndEditedFields(dp)
			for(DetailPageField field: dp.editfields){
				if(field.htmltype === null){
				  field.htmltype = parseAttributeType(field.attribute).htmltype
				    
				   println(field.attribute+ " Html Type  "+field.htmltype + " -> " + parseAttributeType(field.attribute).htmltype)
				}
			}
		}
	}
	
	private def completeTableColumnAndEditedFields(DetailsPage page) {

	
			for(Attribute attr: page.entities.get(0).attributes){
				if(!page.tablecolumns.contains(attr)){
					page.tablecolumns.add(attr)
					
				}
				var DetailPageField df = existingAlreadyFieldWithAttr(page.editfields, attr)
				if(df !== null){
					completeDetailageField(df)
				}else{
					page.editfields.add(parseAttributeType(attr))
				}
				
			}
		
	}
	//@todo
	def completeDetailageField(DetailPageField field) {
		
		var Attribute attr = field.attribute
		if( attr !== null ){
			var Entity ent = field.attribute.eContainer as Entity
			var Reference ref = ent.searchRefrenceOfAttribute(field.attribute)
			if(ref !== null){
				field.fieldtype = ref.attributerefereced.get(ref.attribute.indexOf(attr))
			}
			else{
				if(field.fieldtype !== null){
					field.fieldtype = null
					var ComplexHTMLTypes result = EJSLFactory.eINSTANCE.createComplexHTMLTypes
					result.htmltype = ComplexHTMLTypeKinds.get("Hidden")
					field.htmltype =  result
				}
			}
		}
		if(field.fieldtype !== null && field.htmltype === null){
			field.htmltype = this.ParsingHTMlTypeFromAttr( field.attribute)
			
		}
	}
	
	def Reference searchRefrenceOfAttribute(Entity entity, Attribute attribute){
		for(Reference ref : entity.references){
			if(ref.attribute.contains(attribute)){
				return ref
			}
		}
		return null
	}
	
	def DetailPageField existingAlreadyFieldWithAttr(EList<DetailPageField> list, Attribute attribute) {
		for(DetailPageField df: list){
			if(df.attribute.name == attribute.name)
			return df
		}
		return null
	}
	
	private def completeColUmnAndFilterList(IndexPage page) {		
		if(page.filters.empty && !page.tablecolumns.empty){
			for(Attribute attr: page.tablecolumns){
				page.filters.add(attr)
			}
		}
		if(!page.filters.empty && page.tablecolumns.empty){
			
			for(Attribute attr: page.filters){
				page.tablecolumns.add(attr)
			}
		}
		if(page.filters.empty&& page.tablecolumns.empty){
			for(Attribute attr: page.entities.get(0).attributes){
				if((page.entities.get(0).references.filter[t | !t.attribute.contains(attr) && t.upper != 1]).size ==0)
				page.tablecolumns.add(attr)
				page.filters.add(attr)
			}
		}
	}
	
	def void createSimple1ToNReference(EList<Entity> entitiesList){
		for(Entity ent: entitiesList){
			var EList<Reference> toDeleteReference = new BasicEList<Reference>()
			var EList<Reference>toAddReference = new BasicEList<Reference>()
			var EList<Attribute>toDeleteAttribute = new BasicEList<Attribute>()
			for(Reference ref: ent.references){
				var Reference referenced = ref.entity.getReferencedTo(ent,ref.upper)
				   if(ref.upper.equalsIgnoreCase("-1") && referenced !== null && referenced.upper.equalsIgnoreCase("1")){
				   	
				   		for(Attribute attr: ref.attribute){
				   			if(attr.withattribute !== null){
				   				ent.attributes.remove(attr.withattribute)
				   				attr.isunique = false
				   				attr.preserve = true
				   			}
				   		}
				   		var EList<Attribute> ref_attribute_old = new BasicEList<Attribute>()
				   		var EList<Attribute> ref_referenced_attribute_old = new BasicEList<Attribute>()
				   		ref_attribute_old.addAll(ref.attribute)
				   		ref_referenced_attribute_old.addAll(ref.attributerefereced)
				   		ref.attribute.removeAll(ref_attribute_old)
				   		ref.attributerefereced.removeAll(ref_referenced_attribute_old)
				   		ref.attribute.addAll(referenced.attributerefereced)
				   		ref.attributerefereced.addAll(referenced.attribute)
				 		toDeleteReference.add(ref)
				 	}
			 else if(ref.upper.equalsIgnoreCase("-1") && referenced === null){
				 		var Attribute primaryKey = this.searchPKAttribute(ent)
				 		ref.attribute.forEach[t |   t.preserve  = true]
				 		var EList<Attribute> getAttributeList = new BasicEList<Attribute>
				 		if(primaryKey !== null){
				 			getAttributeList.addAll( this.searchUniqueAttribute(ent).filter[t | !ref.attribute.contains(t) && !t.isIsprimary])
				 			getAttributeList.add(primaryKey)
				 			var EList<Attribute> copiedAttribute = copyAttribute(ent, getAttributeList)
				 			var int size = copiedAttribute.size / 2;
				 			for(var i =0; i < copiedAttribute.size; i++){
				 				var int index = i + size
				 				if(index < copiedAttribute.size)
				 				copiedAttribute.get(i).withattribute  = copiedAttribute.get(index)
				 				
				 			}
				 			for(var i =0; i < copiedAttribute.size; i++){
				 				copiedAttribute.get(i).isunique  = false
				 				
				 			}
				 			ref.entity.attributes.addAll(copiedAttribute)
				 			var Reference newRef = EJSLFactory.eINSTANCE.createReference
				 			newRef.attribute.addAll(copiedAttribute)
				 			newRef.entity = ent
				 			newRef.lower = "0"
				 			newRef.upper =  "1"
				 			newRef.attributerefereced.addAll(getAttributeList)
				 			ref.entity.references.add(newRef);
				 			for(Attribute attr: ref.attribute){
				   			if(attr.withattribute !== null){
				   				ent.attributes.remove(attr.withattribute)
				   				attr.isunique = false
				   			}
				   			}
				   		var EList<Attribute> ref_attribute_old = new BasicEList<Attribute>()
				   		var EList<Attribute> ref_referenced_attribute_old = new BasicEList<Attribute>()
				   		ref_attribute_old.addAll(ref.attribute)
				   		ref_referenced_attribute_old.addAll(ref.attributerefereced)
				   		ref.attribute.removeAll(ref_attribute_old)
				   		ref.attributerefereced.removeAll(ref_referenced_attribute_old)
				   		ref.attribute.addAll(newRef.attributerefereced)
				   		ref.attributerefereced.addAll(newRef.attribute)
				 		toDeleteReference.add(ref);
				 			
				 			
				 		}
				 	}
				 	}
				 	ent.references.removeAll(toDeleteReference)
				 	}
	}
	
   
	def void createMappingsTable(EList<Entity> entitiesList){ 
		
		var EList<Entity> newEntity = new BasicEList<Entity>()
		for(Entity ent: entitiesList){
			var EList<Reference> toDeleteReference = new BasicEList<Reference>()
			var EList<Reference>toAddReference = new BasicEList<Reference>()
			var EList<Attribute>toDeleteAttribute = new BasicEList<Attribute>()
			for(Reference ref: ent.references){
				 if(ref.entity.haveNReferenTo(ent,ref.upper)){
				 	if(!ref.entity.existingReferenceBetweenEntity(ent,newEntity)){
				 		var String mappingEntityName =  ent.name + "" + ref.entity.name
				 	
				 		var Entity mappingEntity = EJSLFactory.eINSTANCE.createEntity
				 		mappingEntity.name = mappingEntityName
				 		var EList<Attribute> attributeFromEntity = new BasicEList<Attribute>
				 		var EList<Attribute> copy_attributeFromEntity = new BasicEList<Attribute>
				 		var EList<Attribute> to_rename_attributeFromEntity = new BasicEList<Attribute>
				 		var EList<Attribute> attributeToEntity = new BasicEList<Attribute>
				 		var EList<Attribute> copy_attributeToEntity = new BasicEList<Attribute>
				 	    var EList<Attribute> to_rename_attributeToEntity = new BasicEList<Attribute>
				 		
				 		attributeFromEntity.addAll(ref.entity.references.filter[t | t.entity.name == ent.name].get(0).attributerefereced)
				 		to_rename_attributeToEntity.addAll(ref.entity.references.filter[t | t.entity.name == ent.name].get(0).attribute)
				 		attributeToEntity.addAll(ref.attributerefereced)
				 		to_rename_attributeFromEntity.addAll(ref.attribute);
				 		copy_attributeFromEntity.addAll(copyAttribute( ent,attributeFromEntity))
				 		mappingEntity.attributes.addAll(copy_attributeFromEntity)
				 		copy_attributeToEntity.addAll(copyAttribute( ref.entity,attributeToEntity))
				 		renameOldReference(to_rename_attributeFromEntity, copy_attributeToEntity)
				 		renameOldReference(to_rename_attributeToEntity, copy_attributeFromEntity)
				 		mappingEntity.attributes.addAll(copy_attributeToEntity)
				 		ent.attributes.removeAll(ref.attribute)
				 		ref.entity.attributes.removeAll(ref.entity.references.filter[t | t.entity.name == ent.name].get(0).attribute)
				 		mappingEntity.references.addAll(solveReference(ref,mappingEntity,ent,ref.entity))
				 		newEntity.add(mappingEntity)
				 		println("generateMappingsTable " + mappingEntityName)
				 		deleteReferenceToEntity(ref.entity, ent,mappingEntity)
				 		toDeleteReference.add(ref)
				 		toAddReference.add(createNewReverseReference(mappingEntity, ent,ref))
				 		completeAttributeOfEntity(mappingEntity);
				 		var IndexPage pageList = createNewExtendedIndexPageForExtensions(mappingEntity)
				 		var DetailsPage pageDetails = createNewExtendedDetailsPageForExtensions(mappingEntity)
				 		createLinktoPage(pageList,pageDetails)
				 		feature.pages.add(pageList)
				 		feature.pages.add(pageDetails)
				 	}
				 }else{
				 	
				 	
				 	
				 }
			}
			if(!toDeleteReference.isEmpty)
			ent.references.removeAll(toDeleteReference) 
			
			if(!toAddReference.isEmpty)
			ent.references.addAll(toAddReference)
			
			
		}
		entitiesList.addAll(newEntity)
		
	}
	
	def EList<Attribute> searchUniqueAttribute(Entity entity) {
		var EList<Attribute> attrList = new BasicEList<Attribute>()
		
		for(Attribute at: entity.attributes){
			if(at.isIsprimary || at.isIsunique || at.withattribute !== null){
				attrList.add(at)
			}
		}
		return attrList
		}
	
	def Attribute searchPKAttribute(Entity entity) {
		for(Attribute attr: entity.attributes){
			if(attr.isIsprimary){
				return attr;
			}
		}
		return null
	}
	
	 def void renameOldReference(EList<Attribute> torenameAttribute, EList<Attribute> newAttribute) {
		
		
		for( Page page: feature.pages.filter[t | t instanceof  DynamicPage]){
			var DynamicPage dynPage = page as DynamicPage
			for(Attribute attr : torenameAttribute){
				if(dynPage.filters.contains(attr)){
					var Attribute newAttr = newAttribute.get(torenameAttribute.indexOf(attr))
					dynPage.filters.remove(attr)
					dynPage.filters.add(newAttr)
				}
				if(dynPage.tablecolumns.contains(attr)){
					var Attribute newAttr = newAttribute.get(torenameAttribute.indexOf(attr))
					dynPage.tablecolumns.remove(attr)
					dynPage.tablecolumns.add(newAttr)
				}
			}
		}
		
	}
	
	private def EList<Reference> solveReference(Reference reference, Entity mappingEntity,Entity fromEntity,Entity toEntity ) {
		var Reference nRef =  EJSLFactory.eINSTANCE.createReference
		nRef.attribute.addAll(mappingEntity.attributes.filter[t | isAttributeOfEntity(t,fromEntity.name)])
		nRef.entity = fromEntity
		nRef.attributerefereced.addAll(toEntity.references.filter[t | t.entity.name == fromEntity.name].get(0).attributerefereced)
		nRef.lower = "1"
		nRef.upper = "1"
		var Reference mRef =  EJSLFactory.eINSTANCE.createReference
		mRef.attribute.addAll(mappingEntity.attributes.filter[t | isAttributeOfEntity(t,toEntity.name)])
		mRef.entity = toEntity
		mRef.attributerefereced.addAll(reference.attributerefereced)
		mRef.lower = "1"
		mRef.upper = "1"
		var EList<Reference> result = new BasicEList<Reference>()
		result.add(nRef)
		result.add(mRef)
		return result
		
	}
	
	private def isAttributeOfEntity(Attribute attribute, String entityName) {
		if(attribute.name.startsWith(entityName.toLowerCase + "_"))
		return true
		
		return false
	}
	
	
	
	private def EList<Attribute> copyAttribute( Entity fromEntity, EList<Attribute> attributeOldList) {
		var EList<Attribute> result = new BasicEList<Attribute>
		for(Attribute attribute: attributeOldList){
		
		var Attribute newAttr = EJSLFactory.eINSTANCE.createAttribute
		newAttr.name = fromEntity.name.toLowerCase + "_" + attribute.name
		newAttr.type = Util.copyType(attribute.type)
		if(newAttr.type instanceof StandardTypes)
		{
			var StandardTypes typ = newAttr.type  as StandardTypes
			typ.notnull = false
		}
		newAttr.id = attribute.id
		//newAttr.isunique = attribute.isunique
		if(attribute.isIsprimary)
		//newAttr.isunique = true
		newAttr.preserve = attribute.preserve
		if(!this.containsAttribute(newAttr,result)){
		result.add(newAttr)

			if(attribute.id && attribute.withattribute === null){
				var Attribute newID = EJSLFactory.eINSTANCE.createAttribute
				newID.name = fromEntity.name.toLowerCase + "_" + "id"
				var StandardTypes typeid = EJSLFactory.eINSTANCE.createStandardTypes
				typeid.type =  StandardTypeKinds.INTEGER
				typeid.notnull = true
				newID.type = typeid
				newAttr.withattribute =  newID;
				if( !this.containsAttribute(newID,result))
				result.add(newID)
				
			}
			if(attribute.withattribute !== null){
				var Attribute newUniq = EJSLFactory.eINSTANCE.createAttribute
				newUniq.name = fromEntity.name.toLowerCase + "_" + attribute.withattribute.name
				newUniq.type = Util.copyType(attribute.withattribute.type)
				if(newUniq.type instanceof StandardTypes)
				{
					var StandardTypes typs = newUniq.type  as StandardTypes
					typs.notnull = false
				}
		
				newAttr.withattribute = newUniq;
				if( !this.containsAttribute(newUniq,result))
				result.add(newUniq)
				
			}
			
		}
        }
        
		return result
	}

	
	
	private def boolean existingReferenceBetweenEntity(Entity to, Entity from,EList<Entity> newEntityList){
		var String mappingNameForward =  to.name + "" + from.name
		var String mappingNameReverse= from.name + "" +  to.name
		if(newEntityList.size ==0 )
		return false
		for(Entity ent: newEntityList){
			if(ent.name.equals(mappingNameForward) || ent.name.equals(mappingNameReverse))
			return true
		}
		return false
		
	}
	private def boolean deleteReferenceToEntity(Entity to, Entity from, Entity newEntity){
		var Reference newref = EJSLFactory.eINSTANCE.createReference
		var Reference oldRef = to.references.filter[t | t.entity.name == from.name].get(0)
		newref.attribute.addAll(from.references.filter[t | t.entity.name == to.name].get(0).attributerefereced)
		newref.attributerefereced.addAll(newEntity.attributes.filter[t | isAttributeOfEntity(t,to.name)])
		newref.entity = newEntity
		newref.lower = "1"
		newref.upper = "-1"
		to.references.remove(oldRef)
		to.references.add(newref)
		return false
		
	}
	private def Reference createNewReverseReference(Entity from, Entity to, Reference oldref){
		var Reference newref = EJSLFactory.eINSTANCE.createReference
		newref.attribute.addAll(to.attributes.filter[t | isContainInMappingEntity(t.name,to.name,from)])
		newref.entity= from
		newref.attributerefereced.addAll(from.attributes.filter[t | isAttributeOfEntity(t, to.name)])
		newref.lower = "1"
		newref.upper = "-1"
		return newref
	}
	
	def boolean isContainInMappingEntity(String attributeName, String toEntityname, Entity mappingEntity) {
		
		if(mappingEntity.attributes.filter[t | t.name.toLowerCase.equalsIgnoreCase(toEntityname.toLowerCase() + "_"+attributeName)].size >0)
		   return true
		return false
	}
	
	private def boolean haveNReferenTo(Entity to, Entity from, String upperValue){
		for(Reference ref : to.references){
			if(ref.entity.name == from.name && !upperValue.equalsIgnoreCase("1") && !ref.upper.equalsIgnoreCase("1"))
			return true
		}
		return false 
	}
	private def Reference getReferencedTo(Entity to, Entity from, String upperValue){
		for(Reference ref : to.references){
			if(ref.entity.name == from.name)
			return ref
		}
		return null 
	}

	
	private def DetailPageField parseAttributeType(Attribute attribute) {
		var Entity ent = attribute.eContainer as Entity
		
		var DetailPageField editField = EJSLFactory.eINSTANCE.createDetailPageField
		switch attribute.type{
			DatatypeReference :{
				var Attribute ref = containsInrefrence(attribute,ent); 
				if(ref !==null && !attribute.isIsprimary){
					editField.fieldtype = ref
				}
				var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
				result.htmltype = SimpleHTMLTypeKinds.get("Text_Field")
				editField.htmltype = result	
				
				editField.attribute = attribute
				return editField
			}
			StandardTypes:{
				return generateDetailsPAgeFields(attribute, ent)
			}
		}
	}
	
	def Attribute containsInrefrence(Attribute attribute, Entity entity) {
		for(Reference r: entity.references){
			if(r.attribute.contains(attribute))
			return r.attributerefereced.get(r.attribute.indexOf(attribute))
		}
		return null
	}
	
	private def DetailPageField  generateDetailsPAgeFields(Attribute attribute, Entity ent) {
		var StandardTypes temptyp = attribute.type as StandardTypes
		if(attribute.isIsprimary){
			var ComplexHTMLTypes result = EJSLFactory.eINSTANCE.createComplexHTMLTypes
			result.htmltype = ComplexHTMLTypeKinds.get("Hidden")
			var DetailPageField editField = EJSLFactory.eINSTANCE.createDetailPageField
			editField.attribute = attribute
			editField.htmltype = result
			return editField
			}
		var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
		var DetailPageField editField = EJSLFactory.eINSTANCE.createDetailPageField
		editField.attribute = attribute
			//	result.htmltype = SimpleHTMLTypeKinds.get(parsingType(temptyp))
		var Attribute ref = containsInrefrence(attribute,ent); 
		if(ref !==null && !attribute.isIsprimary){
			editField.fieldtype = ref
		 }
		 	result =  this.ParsingHTMlTypeFromAttr(attribute)
			switch (temptyp.type.getName()){ 
			case "Time":{
			    var KeyValuePair format = EJSLFactory.eINSTANCE.createKeyValuePair
			    format.name = "format"
			    format.value = "%H:%M:%S"
			  editField.attributes.add(format);
			    
				
			}
			case "Date":{
				 var KeyValuePair format = EJSLFactory.eINSTANCE.createKeyValuePair
			    format.name = "format"
			    format.value = "%d-%m-%Y"
			    editField.attributes.add(format);
			}
			case "Datetime" :{
			   var KeyValuePair format = EJSLFactory.eINSTANCE.createKeyValuePair
			    format.name = "format"
			    format.value = "%y-%m-%d %H:%M:%S"
			    editField.attributes.add(format);
				}
		
			
		}
	
         editField.htmltype =result
         
         
		return editField
		
	}
	def SimpleHTMLTypes ParsingHTMlTypeFromAttr(Attribute attr){
		var SimpleHTMLTypes result = EJSLFactory.eINSTANCE.createSimpleHTMLTypes
		var StandardTypes temptyp = attr.type as StandardTypes
			switch (temptyp.type.getName()){
			case "Integer" :{
				
				result.htmltype = SimpleHTMLTypeKinds.get("Integer")
				
				
			}
			case "Boolean" :{
			result.htmltype = SimpleHTMLTypeKinds.get("Yes_No_Buttons")
				
			}
			case "Text" :{
				result.htmltype = SimpleHTMLTypeKinds.get( "Textarea")
			}
			case "Short_Text" :{
				result.htmltype = SimpleHTMLTypeKinds.get( "Text_Field")
			} 
			case "Time":{
				result.htmltype = SimpleHTMLTypeKinds.get( "Datepicker")
			  
			}
			case "Date":{
				result.htmltype = SimpleHTMLTypeKinds.get( "Datepicker")
				 
			}
			case "Datetime" :{
				result.htmltype = SimpleHTMLTypeKinds.get( "Datepicker")
			    
				}
			case "Link" :{
				result.htmltype = SimpleHTMLTypeKinds.get( "Link")
			}
			case "Image":{
				result.htmltype = SimpleHTMLTypeKinds.get( "Imagepicker")
			}
			case "File" :{
				result.htmltype = SimpleHTMLTypeKinds.get( "Filepicker")
			}
			
		}
		return result
		
	}
	private def DetailsPage createNewExtendedDetailsPageForExtensions(Entity entity) {
		var DetailsPage detailsPage = EJSLFactory.eINSTANCE.createDetailsPage
		detailsPage.name = entity.name + "Details"
		detailsPage.entities.add(entity)
		detailsPage.tablecolumns.addAll(entity.attributes)		
		return detailsPage
	}
	
	
	
	private def IndexPage createNewExtendedIndexPageForExtensions(Entity entity) {
		var IndexPage dynPage = EJSLFactory.eINSTANCE.createIndexPage
		dynPage.name = entity.name + "List"
		dynPage.entities.add(entity)
		dynPage.tablecolumns.addAll(entity.attributes)	
		dynPage.filters.addAll(entity.attributes)	
		return dynPage
	}
	
	private def void createLinktoPage(IndexPage indexPage, DetailsPage detailsPage) {
		var InternalLink linktoDetailsPage = EJSLFactory.eINSTANCE.createInternalLink
		linktoDetailsPage.name = "Details"
		linktoDetailsPage.target = detailsPage
		linktoDetailsPage.linkedAttribute = indexPage.tablecolumns.get(0)
		indexPage.links.add(linktoDetailsPage)
		var InternalLink linktoIndexPage = EJSLFactory.eINSTANCE.createInternalLink
		linktoIndexPage.name = "Liste"
		linktoIndexPage.target = indexPage
		linktoIndexPage.linkedAttribute = detailsPage.tablecolumns.get(0)
		detailsPage.links.add(linktoIndexPage)

	}
	
	
	
}