package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.cjsl.Application
import de.thm.icampus.cjsl.cjsl.Content
import de.thm.icampus.cjsl.cjsl.ContentAttributeOptions
import de.thm.icampus.cjsl.cjsl.ContentImageOptions
import de.thm.icampus.cjsl.cjsl.ContentMetaDataOptions
import de.thm.icampus.cjsl.cjsl.ContentUrlSettingOptions
import de.thm.icampus.cjsl.cjsl.cJSL_Content
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

abstract class ContentGenerator extends ApplicationLibrary {
	
	
	public def CharSequence generate();

}
