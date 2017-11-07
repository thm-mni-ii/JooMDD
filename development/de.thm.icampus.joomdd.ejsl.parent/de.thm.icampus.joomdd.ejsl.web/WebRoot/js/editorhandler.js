define('editorhandler',[ 'jquery',"jstree", "cookie","ace/ace","xtext/xtext-ace","treeloader"], function(jQuery, jstree, Cookies, ace, xtext_ace, treeloader) {
	 var exports = {};
	 exports.loadEditor = function(username, resourceID){
         var baseUrl = window.location.pathname;
         var fileIndex = baseUrl.indexOf("editor.html");
         if (fileIndex > 0)
             baseUrl = baseUrl.slice(0, fileIndex);
		 Cookies.set('resourceid',resourceID);
		var t = xtext_ace.createEditor({
				baseUrl: baseUrl ,
				syntaxDefinition: "xtext-resources/generated/mode-eJSL",
				theme: "ace/theme/github",
				resourceId: username +"/src/" + resourceID
				
			});
		 $("#modelname").text("- " + resourceID)
		 jQuery("#xtext-editor")[0].env.editor.setOptions({
			 fontSize: "14px"
         });
	 };
	 
	 return exports;
})
