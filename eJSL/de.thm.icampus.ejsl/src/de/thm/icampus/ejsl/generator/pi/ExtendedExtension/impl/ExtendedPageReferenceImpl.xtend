package de.thm.icampus.ejsl.generator.pi.ExtendedExtension.impl

import de.thm.icampus.ejsl.generator.pi.ExtendedExtension.ExtendedPageReference
import de.thm.icampus.ejsl.eJSL.impl.PageReferenceImpl
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.ExtendedPage
import de.thm.icampus.ejsl.eJSL.PageReference
import de.thm.icampus.ejsl.generator.pi.ExtendedPage.impl.ExtendedPageImpl

class ExtendedPageReferenceImpl extends PageReferenceImpl implements ExtendedPageReference {
	
	ExtendedPage extendedPage
	PageReference instance
	
	new(PageReference pageRef){
		instance = pageRef
		this.page = pageRef.page
		this.pagescr = pageRef.pagescr
		this.sect = pageRef.sect
		extendedPage = new ExtendedPageImpl(page)
	}
	
	
	override getExtendedPage() {
		return extendedPage
	}
	
	
}