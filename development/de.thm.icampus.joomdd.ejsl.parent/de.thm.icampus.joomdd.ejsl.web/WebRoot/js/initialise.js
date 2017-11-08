require(["jquery"], function($) {
	require(["cookie","jstree","treeloader","editorhandler"],function(Cookies, jstree,treeloader,editorhandler){
		var resourceID = "main.eJSL";
		var editor = editorhandler.loadEditor(resourceID);
	});
});
