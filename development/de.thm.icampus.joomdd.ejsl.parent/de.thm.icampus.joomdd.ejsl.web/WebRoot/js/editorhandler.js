define('editorhandler',[ 'jquery',"jstree", "cookie","ace/ace","xtext/xtext-ace","treeloader"], function(jQuery,jstree,Cookies,ace,xtext_ace,treeloader) {
	 var exports = {};
	 exports.loadEditor = function(resourceID){
		 var baseUrl = window.location.pathname;
		 var fileIndex = baseUrl.indexOf("index_wolf.html");
	    if (fileIndex > 0)
	    {
	    	baseUrl = baseUrl.slice(0, fileIndex);
	    }		 

		var editor = xtext_ace.createEditor({
				baseUrl: baseUrl ,
				syntaxDefinition: "xtext-resources/generated/mode-eJSL",
				theme: "ace/theme/github",
				resourceId: "/src/" + resourceID
			});
		 $("#modelname").text("- " + resourceID)
		 editor.setOptions({
			 fontSize: "14px"
         });
         
         return editor;
	 };
	 
	 return exports;
})
