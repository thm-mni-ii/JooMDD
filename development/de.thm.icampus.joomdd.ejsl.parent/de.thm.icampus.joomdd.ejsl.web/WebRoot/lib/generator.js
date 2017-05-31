/**
 * 
 */
require(["webjars/ace/1.2.0/src/ace"], function() {
require(["xtext/xtext-ace","cookie","jstree","treeloader","infomodal"], function(xtext,Cookies, jstree,treeloader,infomodal) {
	jQuery("#ejslGenerator").click(function(){
		var editor = jQuery("#xtext-editor");
		infomodal.showloadmodal();
		var generatePromise = editor[0].env.editor.xtextServices.generate({"artifactId":"status"});
		var name = Cookies.get('joomddusername');
		
		generatePromise.then( value => {
			 infomodal.closeloadmodal();
			var name = Cookies.get('joomddusername');
			treeloader.reload(); // Success!
			 //location.reload(); 
		}, reason => {
			 infomodal.closeloadmodal();
			 infomodal.showmodal("Failed! The Model cannot be generate!"); // Error!
		} );
	});
	jQuery("#ejslGeneratordownload").click(function(){
	
		treeloader.downloadAll();	 
			  
	   })

});});

