
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
});