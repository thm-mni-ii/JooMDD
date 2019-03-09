define('saveActionHandler',["ace/ace", "alert"], function(ace, alert) {
	return function() {
		var editor = ace.edit("xtext-editor");
	    var save = editor.xtextServices.saveResource();
	    save.then( value => {
	        window.onbeforeunload = null;
	        alert.showSuccess("Model has been saved successfully.")
	    }, reason => {
	        alert.showError("Model cannot be saved.")
	    });
	}
})