require(["ace/ace","xtext/xtext-ace"], function(ace, exportd) {
	define('setting',['xtext/services/XtextService', 'jquery'], function(XtextService, jQuery) {
     var exports = {}; exports.a = new XtextService();  return exports;
	});
});