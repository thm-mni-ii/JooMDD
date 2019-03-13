package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil

import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaExtensionGenerator.AbstractExtensionGenerator
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import org.eclipse.emf.common.util.EList
import org.eclipse.xtext.generator.IFileSystemAccess2
import de.thm.icampus.joomdd.ejsl.eJSL.KeyValuePair
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedModule
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedExtensionPackage

class LanguageGenerator extends AbstractExtensionGenerator {
	new(IFileSystemAccess2 fileAccess) {
		fsa = fileAccess
	}

	def genComponentLanguage(ExtendedComponent component, String root) {
	   for (language : component.languages) {
			val ldir = language.name
			var componentHasFrontend = component.frontEndExtendedPagerefence.empty === false
			var componentHasBackend = component.backEndExtendedPagerefence.empty === false
			
			if (componentHasFrontend === true) {
			    var pathArray = newArrayList(
                                    root,
                                    "components",
                                    component.extensionName,
                                    "language",
                                    ldir
                                )
                                
                if (language.sys === true) {
                    pathArray.add('''«ldir».«Slug.nameExtensionBind("com", component.name).toLowerCase».sys.ini''')
                }
                else
                {
                    pathArray.add('''«ldir».«Slug.nameExtensionBind("com", component.name).toLowerCase».ini''')
                }
                
                fsa.generateFile(pathArray.join("/"), fileLangGen(language.keyvaluepairs))         
			}
			
			if (componentHasBackend === true) {
			    var pathArray = newArrayList(
                                    root,
                                    "administrator",
                                    "components",
                                    component.extensionName,
                                    "language",
                                    ldir
                                )
                                
                if (language.sys === true) {
                    pathArray.add('''«ldir».«Slug.nameExtensionBind("com", component.name).toLowerCase».sys.ini''')
                }
                else
                {
                    pathArray.add('''«ldir».«Slug.nameExtensionBind("com", component.name).toLowerCase».ini''')
                }
                
                fsa.generateFile(pathArray.join("/"), fileLangGen(language.keyvaluepairs)) 
			}
		}
	}
	
	def void addsLanguageKeys(EList<KVPairLanguage> list, KVPairLanguage language){
		for(KVPairLanguage g : list){
			if(g.kv.name.equals(language.kv.name)){
				return;
			}
		}
		list.add(language);
	}

	override generate() {
	    throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	def genModuleLanguage(ExtendedModule module, String root) {
		var String name = Slug.nameExtensionBind("mod", module.name).toLowerCase
		for (language : module.languages) {
			val ldir = language.name
			var path = root + "/language/" + ldir + "/" + ldir + "." + name + ".ini"
			
			if(language.sys === true) {
                path = root + "/language/" + ldir + "/" + ldir + "." + name + ".sys.ini"
			}
			
            fsa.generateFile(path, fileLangGen(language.keyvaluepairs))
		}
	}
	
    def genPackageLanguage(ExtendedExtensionPackage extensionPackage, String root) {
        var String name = Slug.nameExtensionBind("pkg", extensionPackage.name).toLowerCase
        for (language : extensionPackage.languages) {
            val ldir = language.name
            var path =root + "/language/" + ldir + "/" + ldir + "." + name + ".ini"
            
            if(language.sys === true) {
                path = root + "/language/" + ldir + "/" + ldir + "." + name + ".sys.ini"
            }
            
            fsa.generateFile(path, fileLangGen(language.keyvaluepairs))
        }
    }
	
	def CharSequence fileLangGen( EList<KeyValuePair> keyValueLanguagePair) {
	    // Sort the key in alphabetical order
	    var languagePairSortedByKey = keyValueLanguagePair.sortBy[ name ]
	    
	    return '''
            «FOR KeyValuePair kv: languagePairSortedByKey»
                «kv.name»="«kv.value»"
            «ENDFOR»
        '''
	}
}
