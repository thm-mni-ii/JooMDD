require(["jquery","alert"], function($, alert) {
	// Prevent clicks inside the login form to close it.
	$('#dropdownLogin').on("click.bs.dropdown", function (event) {
		var clickedElement = event.target;
		
		if (clickedElement.id !== "registrationFormBtn")
		{
			event.stopPropagation();
			event.preventDefault();
		} 
	});
	
	$('#loginMenuDropdown').on('shown.bs.dropdown', function () {
		$("#loginAlert").css('visibility','hidden');
	});
		
	// Fullscreen handler
	$('#editorFullscreen').click(function (e) {
  		var editor = ace.edit("xtext-editor");
  		openFullscreen(editor.container);
	});
	
	// Formatter handler
	$('#editorFormatter').click(function (e) {
  		var editor = ace.edit("xtext-editor");
  		editor.xtextServices.format();
	});
    
	function openFullscreen(elem) {
		if (elem.requestFullscreen) {
			elem.requestFullscreen();
		} else if (elem.mozRequestFullScreen) { /* Firefox */
			elem.mozRequestFullScreen();
		} else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
			elem.webkitRequestFullscreen();
		} else if (elem.msRequestFullscreen) { /* IE/Edge */
			elem.msRequestFullscreen();
		}
	}
	
	// Handle the cursor position when exiting the full screen mode.
	document.addEventListener("fullscreenchange", function() {
		if (document.fullscreen === false) {
			setTimeout(function() {
		  		var editor = ace.edit("xtext-editor");
				var cursorPosition = editor.getCursorPosition();
				editor.gotoLine(cursorPosition.row + 1, cursorPosition.column);
			}, 50);
		}
	});
	
	// Load example chosen template in text editor
	$(".templates").click(function(){
	var nameTemplate = $(this).val();
	$.ajax({
		  method: "GET",
		  url: "/instance-loader/",
		  data: { name: nameTemplate }
		})
		  .done(function( msg ) {
			  var editor = $("#xtext-editor");
			  editor[0].env.editor.getSession().setValue(msg);
		  });
	});
	
	$("#registerBtn").click(function(){
		var data = $("#register-form").serialize();
		
		var alert = {};
		alert.showSuccess = function(text){
	        $("#registerAlert").toggleClass('alert-success', true);
	        $("#registerAlert").toggleClass('alert-danger', false);
			$("#registerAlert").hide().show();
	        $("#registerAlert #alertState").text("Success! ");
			$("#registerAlert #alertText").text(text);
		};
		alert.showError = function(text){
	        $("#registerAlert").toggleClass('alert-danger', true);
	        $("#registerAlert").toggleClass('alert-success', false);
	        $("#registerAlert").hide().show();
	        $("#registerAlert #alertState").text("Error! ");
	        $("#registerAlert #alertText").text(text);
	    };
		
		$.ajax({
		  method: "POST",
		  url: "/register",
		  data: data
		})
		.done(function( data, textStatus, jqXHR ) {
			$('#registrationForm').on('hidden.bs.modal', function () {
			    location.reload();
			});
			
			alert.showSuccess("Registered successfully.");
			$("#registerBtn").text("Close");
			$("#registerBtn").removeClass("btn-success");
			$("#registerBtn").attr("data-dismiss", "modal");
		})
		.fail(function( jqXHR, textStatus, errorThrown ) {
			alert.showError("An error occurred.");
		});
	});
	
	$("#loginBtn").click(function(){
		var data = $("#login-form").serialize();
		
		var alert = {};
		alert.showSuccess = function(text){
	        $("#loginAlert").toggleClass('alert-success', true);
	        $("#loginAlert").toggleClass('alert-danger', false);
	        $("#loginAlert").css('visibility','visible');
			$("#loginAlert").hide().show();
	        $("#loginAlert #alertState").text("Success! ");
			$("#loginAlert #alertText").text(text);
		};
		alert.showError = function(text){
	        $("#loginAlert").toggleClass('alert-danger', true);
	        $("#loginAlert").toggleClass('alert-success', false);
	        $("#loginAlert").css('visibility','visible');
	        $("#loginAlert").hide().show();
	        $("#loginAlert #alertState").text("");
	        $("#loginAlert #alertText").text(text);
	    };
		
		$.ajax({
		  method: "POST",
		  url: "/login",
		  data: data
		})
		.done(function( data, textStatus, jqXHR ) {
			location.reload();
		})
		.fail(function( jqXHR, textStatus, errorThrown ) {	 	
		  	alert.showError("Wrong credentials.");
		});
	});
	
	$("#logoutMenuBtn").click(function(){
	$.ajax({
		  method: "GET",
		  url: "/logout"
		})
		.done(function( data, textStatus, jqXHR ) {
			location.reload();
		})
		.fail(function( jqXHR, textStatus, errorThrown ) {
		  	// Something went wrong.
		});
	});
	
	// Save the current model
	$("#saveModel").click(() => {
		var editor = ace.edit("xtext-editor");
		editor.commands.exec("xtext-save");
	});

	require(["treeloader"],function(treeloader) {
		$("#platform").change(function(){
			var editor = ace.edit("xtext-editor");
			
		    content = editor.env.document.getValue();
			var contentLength = editor.env.document.getLength();
			var lastLineContent = editor.env.editor.session.getLine(contentLength-1);
			if (lastLineContent.trim() === "")
			{
				editor.env.document.setValue(content.trim());
			}
			else
			{
		    	editor.env.document.setValue(content + "\n");
		    }
		});

		// Generate Code, based on the model in the editor
        $("#generateCode").click(function(){
            var editor = $("#xtext-editor");
            alert.showloadmodal();
            var platform = $("#platform").val();
            var generatePromise = editor[0].env.editor.xtextServices.generate({"artifactId":"status", "platform":platform});
            generatePromise.then( (value) => {
                alert.closeloadmodal();
                treeloader.reload(); // Success!
                //location.reload();
                alert.showSuccess("Code successful generated.")
            }, (reason) => {
                alert.closeloadmodal();
                alert.showError("Code generation failed. The model cannot be read."); // Error!
            });
        });
        
        $("#download").click(function(){
            treeloader.downloadAll();
        });

		// Extract a model from a valid manifest file
		$("#extractModel").click(function(){
			var data = $('#folder_tree').jstree(true).get_selected();
			var dataArray = data[0].split("/");
			if(dataArray[1] == "reverse" && dataArray[dataArray.length-1].endsWith(".xml")){
				alert.showloadmodal();
			var modelName = dataArray[dataArray.length-1].replace(".xml",".eJSL")
				$.ajax({
					  url: '/reverse-loader/',
					  type: 'POST',
					  data: {manifest: encodeURI(data[0]), model:modelName},
					  success: function(data) {
						  alert.closeloadmodal();
					    if(data){
					    	treeloader.reload()
					    }else{
                            alert.showError("The uploaded extension cannot be extracted. " +
								"Please select a valid manifest file before clicking this button!");
					    }
					  }
					});
			}
		});

		require(["editorhandler"],function(editorhandler){
			// Load the selected model into the editor
			$("#loadModel").click(function(){
				var data = $('#folder_tree').jstree(true).get_selected();
				if(data.length ==1){
					var nameArray = data[0].split("/");
					var namefile = nameArray[nameArray.length-1];
					var confirmed = confirmUnsavedChangeToModel(namefile);
					
					if (confirmed === true){
						var editor = editorhandler.loadEditor(namefile+"");
					}
				}else{
					alert.showError("You can only load one model.");
				}
			});
			// Create a new model
			$('#createModel').click(function(){
				var filename = $("#newModelName").val()
				$("#newModelName").val("");
				var tempArray = filename.split(".")
				if(tempArray[tempArray.length-1] != "eJSL")
					filename= filename+".eJSL"
				if(tempArray.length > 1)
					filename = tempArray[0] + ".eJSL"
					
				var confirmed = confirmUnsavedChangeToModel(filename);
				
				if (confirmed === true){
					var editor = editorhandler.loadEditor(filename+"");
				}
			});

			// Upload of an existing extension
			$('#uploadExtension').click(function(){
				var input = $("#uploadExtensionFile")[0].files[0];
				alert.showloadmodal();
				$.ajax({ url:"/reverse-loader/?filename=" + input.name,
						method:"PUT",
						dataType: 'application/zip',
						 contentType: "multipart/form-data",
						 processData: false,
						data:input,
						complete:function(data){
							alert.closeloadmodal();
							if(data){
                                alert.showSuccess("The extension has been uploaded succesfully.");
								treeloader.reload()
							}else{
                                alert.showError("An error occured during the upload. You must use a path to a valid extension package (.zip)");
							}
						}
				});
			});
			
			function confirmUnsavedChangeToModel(modelNameToChange){
				var editor = ace.edit("xtext-editor");
				var modified = editor.xtextServices.editorContext._dirty;
				var confirmed = true;
				
				if (modified) {
					confirmed = confirm('You have unsaved changes in your current model.\nPress OK to load the model "' + modelNameToChange + '" anyway.');
				}
				
				return confirmed;
			}
		});
	});
});

