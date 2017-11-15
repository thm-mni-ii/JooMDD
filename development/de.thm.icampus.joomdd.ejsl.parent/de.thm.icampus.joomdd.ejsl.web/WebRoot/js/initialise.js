require(["jquery"], function($) {
	require(["jstree","treeloader","editorhandler"],function(jstree,treeloader,editorhandler){
		var loginMenuBtn = $("#loginMenuBtn");
		var logoutMenuBtn = $("#logoutMenuBtn");
		var loginMenuSpinner = loginMenuBtn.children("i");
		$.ajax({
		  method: "GET",
		  url: "/login"
		})
		.done(function( data, textStatus, jqXHR ) {
			loginMenuBtn.addClass("d-none");
			logoutMenuBtn.removeClass("d-none");
		})
		.fail(function( jqXHR, textStatus, errorThrown ) {
		  	loginMenuSpinner.removeClass('fa-spinner').removeClass('fa-pulse').addClass('fa-user-o');
		})
		.always(function( mixedDataJqXHR, textStatus, mixedJqXHRErrorThrown ) {
			var resourceID = "main.eJSL";
			var editor = editorhandler.loadEditor(resourceID);
		});
	});
});
