
require(["jquery"], function() {
	
	jQuery("#templates").change(function(){
	var nameTemplate = jQuery(this).val();
	jQuery.ajax({
		  method: "GET",
		  url: "/instance-loader/",
		  data: { name: nameTemplate }
		})
		  .done(function( msg ) {
			  var editor = jQuery("#xtext-editor");
			  editor[0].env.editor.getSession().setValue(msg);
		  });
});
	require([ "cookie"],function(Cookies){
	
		$("#ejslGeneratorsave").click(function(){
		var name = Cookies.get('joomddusername');
		var resourceid = Cookies.get('resourceid');
		var editor = jQuery("#xtext-editor");
		var save = editor[0].env.editor.xtextServices.saveResource()
		});
	});
});

