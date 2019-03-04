require(["jquery", "bootstrap", "jquery-easing", "scrolling-nav"], function($) {
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
		  	loginMenuSpinner.removeClass('fa-spinner').removeClass('fa-pulse').removeClass('fas fa-fw').addClass('far fa-user');
		})
		.always(function( mixedDataJqXHR, textStatus, mixedJqXHRErrorThrown ) {
			var resourceID = "main.eJSL";
			var editor = editorhandler.loadEditor(resourceID);
		});
		
		// Select the node that will be observed for mutations
		var htmlCollection = document.getElementsByTagName("body");
		var body = htmlCollection.item(0);
		
		// Options for the observer (which mutations to observe)
		var config = { attributes: false, childList: true, subtree: false };
		
		// Callback function to execute when mutations are observed
		var callback = function(mutationsList, observer) {
		    for(var mutation of mutationsList) {
		        if (mutation.type == 'childList' && mutation.addedNodes.length > 0) {
		            var autocompleteDiv = mutation.addedNodes.item(0);
		            
		            if (autocompleteDiv.classList.contains("ace_autocomplete")){
		            	var editor = ace.edit("xtext-editor");
		            	editor.container.appendChild(editor.completer.popup.container);
		            }
		        }
		    }
		};
		
		// Create an observer instance linked to the callback function
		var observer = new MutationObserver(callback);
		
		// Start observing the target node for configured mutations
		observer.observe(body, config);
	});
	
	// Every time a modal is shown, if it has an autofocus element, focus on it.
	$('.modal').on('shown.bs.modal', function() {
	  $(this).find('[autofocus]').focus();
	});
});
