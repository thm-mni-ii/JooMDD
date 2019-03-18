package de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage

import de.thm.icampus.joomdd.ejsl.eJSL.DynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameterGroup
import de.thm.icampus.joomdd.ejsl.generator.pi.util.ExtendedParameter

interface ExtendedDynamicPage extends DynamicPage {
	

	/**
	 * Transform the attributes of entities in tablecolumn to Extentedattribute
	 * for the plattform speciefied generator
	 */
	def EList<ExtendedAttribute> getExtendedTableColumnList()
	/**
	 * Transform the attributes of entities in filter to Extentedattribute
	 * for the plattform speciefied generator
	 */
	def EList<ExtendedAttribute> getExtendFiltersList()
	/**
	 * Transform the EditedFields of a detailspage  to ExtentedEditedField
	 * for the plattform speciefied generator
	 */
	def EList<ExtendedDetailPageField> getExtendedEditedFieldsList()
	/**
	 * Transform the eintities in entitylist to ExtentedEntity
	 * for the plattform speciefied generator
	 */
	def EList<ExtendedEntity> getExtendedEntityList()
	/**
	 * Transform the paramterGroup of a page  to ExtentedParametergroup
	 * for the plattform speciefied generator
	 */
	def EList<ExtendedParameterGroup> getExtendedParametersGroupsListe()
	/**
	 * Transform the parameter in the globalparamterlist of a page  to ExtentedParameter
	 * for the plattform speciefied generator 
	 */
	def EList<ExtendedParameter> getExtendedGlobalParametersListe()
	/**
	 * Transform the parameter in the localparamterlist of a page  to ExtentedParameter
	 * for the plattform speciefied generator 
	 */
	def EList<ExtendedParameter> getExtendedLocalParametersListe()
	/**
	 * Return all attributes of the entities in the entitylist and referenced entities
	 * 
	 */
	def EList<ExtendedAttribute> getAllAttributeOfFilterAndColum()
	/**
	 * Check if the details page have a attribute with a type file 
	 */
	def boolean haveFiletoLoad()
	/**
	 * Check if the dynamic page are a details page
	 */
	def Boolean isDetailsPage()
	/**
	 * return the Dynamicpage instance
	 */
	def DynamicPage getInstance()
	/**
	 * return the true if the parameter group exist in Page
	 */
	def Boolean containsParamertergroup(String paramenterGroupName)
	
	/**
	 * Returns the <table name>.<field name> representation in the database for the given attribute.
	 */
	def String getTextColumn(ExtendedAttribute attribute, EList<ExtendedEntity> extendedEntityList)
	
    /**
     * Returns the <table name>.<field name> representation in the database for the given attribute.
     */
	def String getValueColumn(ExtendedAttribute attribute, EList<ExtendedEntity> extendedEntityList)
}