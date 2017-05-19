/**
 * 
 */
require(["webjars/ace/1.2.0/src/ace"], function() {
require(["xtext/xtext-ace","cookie","jstree","treeloader"], function(xtext,Cookies, jstree,treeloader) {
	jQuery("#ejslGenerator").click(function(){
		var editor = jQuery("#xtext-editor");
	
		var generatePromise = editor[0].env.editor.xtextServices.generate({"artifactId":"status"});
		var name = Cookies.get('joomddusername');
		if(generatePromise !=null){
		treeloader.writeTree(name)
		}
		//generatePromise.then
	});
				
});});
