
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
		require([ "editorhandler","treeloader"],function(editorhandler,treeloader){
		$("#ejslLoadModel").click(function(){
			var data = $('#folder_tree').jstree(true).get_selected();
			var name = Cookies.get('joomddusername');
			if(data.length ==1){
				var nameArray = data[0].split("/")
				var namefile = nameArray[nameArray.length-1]
				Cookies.set('resourceid',namefile);
				editorhandler.loadEditor(name,namefile);
				location.reload();
			}else{
				alert("Choose only one Model!")
			}

		});
		$('#eJSlCreateModel').click(function(){
			var name = Cookies.get('joomddusername');
			var filename = $("#addfile").val()
			var tempArray = filename.split(".")
			if(tempArray[tempArray.length-1] != "eJSL")
				filename= filename+".eJSL"
			if(tempArray.length > 1)
				filename = tempArray[0] + ".eJSL"
				Cookies.set('resourceid',filename);
			var response = editorhandler.loadEditor(name, filename+"");
			 location.reload();
			treeloader.writeTree(name)

		})
		});
	});
});

