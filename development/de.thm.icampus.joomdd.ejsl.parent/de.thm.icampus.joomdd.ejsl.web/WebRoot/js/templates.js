
require(["jquery","infomodal"], function($,infomodal) {
	
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
	require([ "cookie","treeloader"],function(Cookies,treeloader){
	
		$("#ejslGeneratorsave").click(function(){
		var editor = $("#xtext-editor");
		var save = editor[0].env.editor.xtextServices.saveResource();
		save.then( value => {
			infomodal.showmodal("Model has been saved successfully.")
		}, reason => {
			infomodal.showmodal("Model cannot be saved.")
		} );
		});
		
		$("#ejslReverseModel").click(function(){
			var data = $('#folder_tree').jstree(true).get_selected();
			var name = Cookies.get('joomddusername');
			var dataArray = data[0].split("/");
			if(dataArray[2] == "reverse" && dataArray[dataArray.length-1].endsWith(".xml")){
				infomodal.showloadmodal();
			var modelName = dataArray[dataArray.length-1].replace(".xml",".eJSL")
				$.ajax({
					  url: '/reverse-loader/',
					  type: 'POST',
					  data: {user:name,manifest:data[0],model:modelName},
					  success: function(data) {
						  infomodal.closeloadmodal();
					    if(data){
					    	var name = Cookies.get('joomddusername');
					    	treeloader.reload()
					    }else{
					    	infomodal.showmodal("The uploaded extension cannot be extracted. Please select a valid manifest file before clicking this button!");
					    }
					  }
					});
			}
			});
		require([ "editorhandler"],function(editorhandler){
		$("#ejslLoadModel").click(function(){
			var data = $('#folder_tree').jstree(true).get_selected();
			if(data.length ==1){
				var nameArray = data[0].split("/");
				var namefile = nameArray[nameArray.length-1]
				
				var response = editorhandler.loadEditor(namefile+"");
			}else{
				
			infomodal.showmodal("You can only load one model.");
			}

		});
		$('#eJSlCreateModel').click(function(){
			var filename = $("#addfile").val()
			var tempArray = filename.split(".")
			if(tempArray[tempArray.length-1] != "eJSL")
				filename= filename+".eJSL"
			if(tempArray.length > 1)
				filename = tempArray[0] + ".eJSL"
			var response = editorhandler.loadEditor(filename+"");
			 location.reload(); 

		});
		$('#eJSlUploadModel').click(function(){
			var name = Cookies.get('joomddusername');
			var input = $("#addRevers")[0].files[0];
			infomodal.showloadmodal();
			$.ajax({ url:"/reverse-loader/?filename=" + input.name,
				    method:"PUT",
					dataType: 'application/zip',
					 contentType: "multipart/form-data",
					 processData: false,
					data:input,
					complete:function(data){
						infomodal.closeloadmodal();
						if(data){
					    	infomodal.showmodal("The extension has been uploaded succesfully.");
					    	treeloader.reload()
						}else{
							infomodal.showmodal("An error occured during the upload. You must use a path to a valid extension package (.zip)");
						}
					}
			    	});
		});
	});
});
});
