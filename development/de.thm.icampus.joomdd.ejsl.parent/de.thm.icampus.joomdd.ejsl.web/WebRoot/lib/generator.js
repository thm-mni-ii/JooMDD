/**
 * 
 */
require(["webjars/ace/1.2.0/src/ace"], function() {
require(["xtext/xtext-ace","cookie","jstree","treeloader"], function(xtext,Cookies, jstree,treeloader) {
	jQuery("#ejslGenerator").click(function(){
		var editor = jQuery("#xtext-editor");
	
		var generatePromise = editor[0].env.editor.xtextServices.generate({"artifactId":"status"});
		var name = Cookies.get('joomddusername');
		
		treeloader.writeTree(name)
		//generatePromise.then
	});
	jQuery("#ejslGeneratordownload").click(function(){
	
		var name = Cookies.get('joomddusername');
		var index 
		var data = $('#folder_tree').jstree(true).get_selected()
		for ( index = 0; index < data.length; ++index) {
		    var value = data[index];
		    window.open(value)
		}	 
			  
	   })

});});

