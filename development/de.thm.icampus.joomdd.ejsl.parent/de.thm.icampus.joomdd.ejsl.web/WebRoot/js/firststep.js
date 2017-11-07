require(["jquery"], function($) {
	require([ "cookie","jstree","treeloader","editorhandler"],function(Cookies, jstree,treeloader,editorhandler){
	
	var cb = function(e, data)
	{
		var resourceID = "main.eJSL";
		var editor = editorhandler.loadEditor(resourceID);
		editor.renderer.on('afterRender', function() {
		    editor.renderer.removeAllListeners('afterRender');
		    treeloader.reload();
		});
	};
	treeloader.writeTree(cb);
})})
