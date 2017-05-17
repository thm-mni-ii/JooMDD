/**
 * 
 */
require(["webjars/ace/1.2.0/src/ace"], function() {
require(["xtext/xtext-ace"], function(xtext) {
	jQuery("#ejslGenerator").click(function(){
		var editor = jQuery("#xtext-editor");
	
		var generatePromise = editor[0].env.editor.xtextServices.generate({"artifactId":"status"});
		//generatePromise.then
	});
				
});});



