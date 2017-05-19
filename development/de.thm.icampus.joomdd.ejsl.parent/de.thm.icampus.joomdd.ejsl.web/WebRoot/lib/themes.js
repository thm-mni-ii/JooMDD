
require(["jquery"], function() {

jQuery.loadScript = function (url, callback) {
    jQuery.ajax({
        url: url,
        dataType: 'script',
        success: callback,
        async: true
    })};
    require(["webjars/ace/1.2.0/src/ace"], function() {
    	require(["xtext/xtext-ace"], function(xtext) {
    		jQuery( document ).ready(function() {
    			// Handler for .ready() called.
    			/**jQuery("#xtext-editor")[0].env.editor.setOptions({
    				fontFamily: "tahoma",
    				fontSize: "10px"
    			});*/
    			//jQuery.loadScript("/theme/twilight.js", function(){
    			 //jQuery("#xtext-editor")[0].env.editor.setTheme("theme/twilight.js");
    			//});
    		    
    		})	
    					
    	});});

	jQuery("#selectedtheme").change( function(){
		var nameTheme = jQuery(this).val();
		console.log(nameTheme);
		jQuery.loadScript("/theme/"+nameTheme+".js", function(){
			 jQuery("#xtext-editor")[0].env.editor.setTheme("/theme/"+nameTheme);
			});
	
});
});