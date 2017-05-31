define('editorhandler',[ 'jquery',"jstree", "cookie","webjars/ace/1.2.0/src/ace","xtext/xtext-ace","treeloader"], function(jQuery,jstree,Cookies,ace,xtext_ace,treeloader) {
	 var exports = {};
	 exports.loadEditor = function(username, resourceID){
		 var baseUrl = window.location.pathname;
		 Cookies.set('resourceid',resourceID);
		var t = xtext_ace.createEditor({
				baseUrl: baseUrl ,
				syntaxDefinition: "xtext-resources/generated/mode-eJSL",
				theme: "ace/theme/twilight",
				resourceId: username +"/src/" + resourceID
				
			});
		 $("#modelname").text(resourceID)

	 };
	 return exports;
})
