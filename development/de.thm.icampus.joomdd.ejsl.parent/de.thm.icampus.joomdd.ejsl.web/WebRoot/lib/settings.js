require(["webjars/ace/1.2.0/src/ace"], function() {
require(["xtext/xtext-ace"], function(exportd) {
	
	define('setting',['xtext/services/XtextService', 'jquery'], function(XtextService, jQuery) {
     var exports = {}; exports.a = new XtextService();  return exports;
	});
				
}); });
