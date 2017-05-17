require(["jquery"], function($) {
	require([ "cookie"],function(Cookies){
	
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
				  loadEditor(name, resourceID);
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
				Cookies.set('resourceid',resourceID);
				loadEditor(username, resourceID);
			
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
				Cookies.set('resourceid',resourceID);
				loadEditor(username, resourceID);
			
			}else{
				$("#firstStepModalfailur").html("<h3>Username and Email cannot be matched, please try again or create new user!</h3>");
			}
			  });
		});
	
})})
function loadEditor(username, resourceID){
	 require(["webjars/ace/1.2.0/src/ace"], function() {
			require(["xtext/xtext-ace"], function(xtext) {
				var baseUrl = window.location.pathname;
				xtext.createEditor({
					baseUrl: baseUrl ,
					syntaxDefinition: "xtext-resources/generated/mode-eJSL",
					theme: "ace/theme/chrome",
					resourceId: username +"/src/" + resourceID
					
				});
	
					
				});
			});
}