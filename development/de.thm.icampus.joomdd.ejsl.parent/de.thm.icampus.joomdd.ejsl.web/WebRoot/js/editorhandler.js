define('editorhandler',[ 'jquery',"jstree","ace/ace","xtext/xtext-ace","treeloader"], function(jQuery,jstree,ace,xtext_ace,treeloader) {
	 var exports = {};
	 var editor = null;
	 exports.loadEditor = function(resourceID) {
		var baseUrl = window.location.pathname;
		var fileIndex = baseUrl.indexOf("editor.html");
	    if (fileIndex > 0) {
	    	baseUrl = baseUrl.slice(0, fileIndex);
	    }
	    
	    if (editor != null) {
			editor.destroy();
		}

		editor = xtext_ace.createEditor({
			baseUrl: baseUrl ,
			syntaxDefinition: "js/folding-mode-eJSL",
			theme: "ace/theme/github",
			resourceId: "/src/" + resourceID
		});
				
		$("#modelname").text(resourceID)
		editor.setOptions({
			fontSize: "14px",
			minLines: 25
        });
                 
        // Override the generate function to include custom request parameters.
        var originInitServerData = editor.xtextServices.generatorService._initServerData;
        editor.xtextServices.generatorService._initServerData = function(serverData, editorContext, params) {
																	if (params.platform)
																	{
																		serverData.platform = params.platform;
																	}
																	originInitServerData(serverData, editorContext, params);
																} 
         
        var afterEditorCreation = function() {
        	var jstree = $('#folder_tree').jstree(true);
        	 
        	if(jstree) {
        		treeloader.reload();
        	}
        	else
        	{
        		treeloader.writeTree();
        	}
			editor.xtextServices.successListeners = editor.xtextServices.successListeners.filter(function(obj) {
				return obj.name !== "afterEditorCreation";
			});
		};
		
		editor.xtextServices.successListeners.push(afterEditorCreation);
        return editor;
	 };
	 
	 return exports;
})
