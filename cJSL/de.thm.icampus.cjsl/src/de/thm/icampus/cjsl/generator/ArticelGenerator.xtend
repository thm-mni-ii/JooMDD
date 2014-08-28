package de.thm.icampus.cjsl.generator

import de.thm.icampus.cjsl.generator.ContentGenerator
import de.thm.icampus.cjsl.cjsl.ContentMetaDataOptions
import de.thm.icampus.cjsl.cjsl.ContentAttributeOptions
import de.thm.icampus.cjsl.cjsl.ContentUrlSettingOptions
import de.thm.icampus.cjsl.cjsl.ContentImageOptions
import org.eclipse.emf.common.util.EList
import de.thm.icampus.cjsl.cjsl.Content
import de.thm.icampus.cjsl.cjsl.Application
import org.eclipse.emf.ecore.EObject
import de.thm.icampus.cjsl.cjsl.cJSL_Content

class ArticelGenerator extends ContentGenerator {
	Application app
	EList<Content> allarticles
	cJSL_Content contentContainer
	EList<EObject> allcat
	int articleStartid
	int assetAktuellID
	int catAktuellID
	int userStartid
	int viewLevelid

	new(
		Application newapp,
		int articleStartindex,
		int assetStartindex,
		int categorieStartindex,
		int userStartindex,
		int viewLevelMaxindex
	) {

		app = newapp
		contentContainer = app.cjsl_content
		allarticles = contentContainer.content
		articleStartid = articleStartindex
		assetAktuellID = assetStartindex
		catAktuellID = categorieStartindex
		userStartid = userStartindex
		viewLevelid = viewLevelMaxindex
		allcat.addAll(contentContainer.articleCategory)
		

	}

	override generate()  '''
		INSERT INTO `#__content`(`id`, `asset_id`, `title`, `alias`, `title_alias`, `introtext`, `fulltext`, `state`, `sectionid`, `mask`, `catid`, `created`, `created_by`, `created_by_alias`, `modified`, `modified_by`, `checked_out`, `checked_out_time`, `publish_up`, `publish_down`, `images`, `urls`, `attribs`, `version`, `parentid`, `ordering`, `metakey`, `metadesc`, `access`, `hits`, `metadata`, `featured`, `language`, `xreference`) VALUES 
		«FOR Content art : allarticles»
			«IF art.equals(allarticles.get(allarticles.size - 1))»
				(«indexOf(art, allarticles, articleStartid, 0)», «assetAktuellID + allarticles.indexOf(art)», '«art.title»','«art.
			name»','','«art.articleText.value»','','«valueParser(art.status.toString)»','0','0','«indexOf(art.category, allcat,
			catAktuellID, catAktuellID + 1)»','«searchDateTime»','«indexOf(art.author, app.cjsl_user.user, userStartid, 0)»','«art.
			authorAlias»','','','','','«art.startPublishing»','«art.finishPublishing»','«readArticleImg(art.imagesOptions)»','«readArticleURL(
			art.urlOptions)»','«readArticleAttribut(art.attributeOptions)»','0','«indexOf(art.parentArticle, allarticles,
			articleStartid, 0)»','0',«readArticleMeta(art.contentMetadataOptions)»,'«indexOf(art.viewlevel,
			app.cjsl_user.viewlevel, viewLevelid, 0)»','0','','«valueParser(art.featured)»','«selectedLanguageAll(art.language)»','«art.
			extRessourceDescription»');
			«ELSE»
				(«indexOf(art, allarticles, articleStartid, 0)», «assetAktuellID + allarticles.indexOf(art)», '«art.title»','«art.name»','','«art.
			articleText.value»','','«valueParser(art.status.toString)»','0','0','«indexOf(art.category, allcat, catAktuellID,
			catAktuellID + 1)»','«searchDateTime»','«indexOf(art.author, app.cjsl_user.user, userStartid, 0)»','«art.authorAlias»','','','','','«art.
			startPublishing»','«art.finishPublishing»','«readArticleImg(art.imagesOptions)»','«readArticleURL(art.urlOptions)»','«readArticleAttribut(
			art.attributeOptions)»','0','«indexOf(art.parentArticle, allarticles, articleStartid, 0)»','0',«readArticleMeta(
			art.contentMetadataOptions)»,'«indexOf(art.viewlevel, app.cjsl_user.viewlevel, viewLevelid, 0)»','0','','«valueParser(
			art.featured)»','«selectedLanguageAll(art.language)»','«art.extRessourceDescription»'),
			«ENDIF»
		«ENDFOR»
	'''

	def CharSequence readArticleMeta(ContentMetaDataOptions options) '''
		'''

	def CharSequence readArticleAttribut(ContentAttributeOptions options) '''
		'''

	def CharSequence readArticleURL(EList<ContentUrlSettingOptions> list) '''
		'''

	def CharSequence readArticleImg(ContentImageOptions options) '''
		'''

	public def int getassetAktuellID() {
		var int assettemp = assetAktuellID
		setassetAktuellID(assetAktuellID + 1)
		return assettemp
	}

	def setassetAktuellID(int i) {
		this.assetAktuellID = i
	}
}