package de.thm.icampus.joomdd.ejsl.validation.elements

import de.thm.icampus.joomdd.ejsl.eJSL.BackendSection
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.EJSLPackage
import de.thm.icampus.joomdd.ejsl.eJSL.Section
import java.util.HashSet
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * This class contains custom validation rules about sections.
 *
 */
class SectionValidator extends AbstractDeclarativeValidator {
	
	public static val SECTION_MORE_THAN_ONE_BACKEND = 'moreThanOneBackend'
	public static val SECTION_MORE_THAN_ONE_FRONTEND = 'moreThanOneFrontend'
	public static val SECTION_PAGE_USED_MULTIPLE_TIMES = 'pageUsedMultipleTimes'
	
    public override register(EValidatorRegistrar registrar) {}
    
	/**
	 * Validate that an entity can only used once per section and not multiple times.
	 */
	@Check
	def checkPagesAreUsedOnlyOncePerSection(Section section) {
		var pages = new HashSet<String>

		if (section.pageRef !== null) {
			var i = 0
			for (page : section.pageRef) {
				if (!pages.add(page.page.name)) {
					warning(
						'Page is used multiple times for this section.',
						EJSLPackage.Literals.PAGE_REFERENCE__PAGESCR,
						i,
						de.thm.icampus.joomdd.ejsl.validation.elements.SectionValidator.SECTION_PAGE_USED_MULTIPLE_TIMES
					)
				}
				i++
			}
		}
	}
	
	/**
	 * Checks if the existing component conatains max. one backend and one frontend section
	 */
	@Check
	def checkComponentHasOnlyOneSectionInstancePerClass(Component component) {
		var hasBackend = false
		var hasFrontend = false

		if (component.sections !== null) {
			var i = 0
			for (Section section : component.sections) {
				
				// differentiate between backend and frontend section
				if (section instanceof BackendSection) {
					if (hasBackend) {
						error(
							'Component must not have more than one backend.',
							EJSLPackage.Literals.COMPONENT__SECTIONS,
							i,
							de.thm.icampus.joomdd.ejsl.validation.elements.SectionValidator.SECTION_MORE_THAN_ONE_BACKEND
						)
					}
					hasBackend = true
				} else {
					if (hasFrontend) {
						error(
							'Component must not have more than one frontend.',
							EJSLPackage.Literals.COMPONENT__SECTIONS,
							i,
							de.thm.icampus.joomdd.ejsl.validation.elements.SectionValidator.SECTION_MORE_THAN_ONE_FRONTEND
						)
					}
					hasFrontend = true
				}
				i++;
			}
		}
	}
}
