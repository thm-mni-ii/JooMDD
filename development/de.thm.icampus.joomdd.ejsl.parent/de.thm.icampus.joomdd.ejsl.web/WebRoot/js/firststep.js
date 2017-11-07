require(["jquery"], function($) {
	require([ "cookie","jstree","treeloader","editorhandler"],function(Cookies, jstree,treeloader,editorhandler){
	
	var cb = function(e, data)
	{
		var resourceID = "main.eJSL";
		var editor = editorhandler.loadEditor(resourceID);
		var changeAnnotationCB = function(){
			 editor.getSession().off('changeAnnotation', changeAnnotationCB);
			 treeloader.reload();
		};
		editor.getSession().on('changeAnnotation', changeAnnotationCB);
	};
	treeloader.writeTree(cb);
})})
