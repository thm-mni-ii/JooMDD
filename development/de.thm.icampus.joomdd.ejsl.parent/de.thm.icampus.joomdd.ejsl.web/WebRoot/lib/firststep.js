require(["jquery"], function($) {
	require([ "cookie","jstree","treeloader","editorhandler"],function(Cookies, jstree,treeloader,editorhandler){
	
	var name = Cookies.get('joomddusername');
	var email = Cookies.get('joomddemail');
	if(name==null || email==null ){
		$(document).ready(function(){
			$("#firstStepModal").css("display","block");
		});
	}else{
		$.post( "/exist-user-service/", { name: name, email:email})
		  .done(function( data ) {
			  if(data){
				  var resourceID = Cookies.get('resourceid');
				  editorhandler.loadEditor(name, resourceID);
				  treeloader.writeTree(name)
			  }else{
				  $("#firstStepModal").css("display","block");
				}
		  })
	}
		$("#newuser").click(function(){
			
			var username = $("#username").val();
			var useremail = $("#useremail").val();
			var resourceID=  $("#resourceid").val();
			
			$.post( "/new-user-service/", { name: username, email:useremail})
			  .done(function( data ) {
				  
			if(data){
				$("#firstStepModal").css("display","none");
				editorhandler.loadEditor(username, resourceID);
				treeloader.writeTree(username)
			
			}else{
				$("#firstStepModalfailur").html("<h3>Login failed: the name already exist!</h3>");
			}
			  });
		});
	$("#existuser").click(function(){
			
			var username = $("#username").val();
			var useremail = $("#useremail").val();
			var resourceID=  $("#resourceid").val();
			
			$.post( "/exist-user-service/", { name: username, email:useremail})
			  .done(function( data ) {
				  
			if(data){
				$("#firstStepModal").css("display","none");
				editorhandler.loadEditor(username, resourceID);
				treeloader.writeTree(username)
			}else{
				$("#firstStepModalfailur").html("<h3>Username and Email cannot be matched, please try again or create new user!</h3>");
			}
			  });
		});
	
})})
