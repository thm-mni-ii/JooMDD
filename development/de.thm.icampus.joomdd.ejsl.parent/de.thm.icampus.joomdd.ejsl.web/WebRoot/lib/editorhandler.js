define('editorhandler',[ 'jquery',"jstree","webjars/ace/1.2.0/src/ace","xtext/xtext-ace"], function(jQuery,jstree,ace,xtext_ace) {
	 var exports = {};
	 exports.loadEditor = function(username, resourceID){
		 var baseUrl = window.location.pathname;
		 xtext_ace.createEditor({
				baseUrl: baseUrl ,
				syntaxDefinition: "xtext-resources/generated/mode-eJSL",
				theme: "ace/theme/chrome",
				resourceId: username +"/src/" + resourceID
				
			});
	 };
	 return exports;
})
