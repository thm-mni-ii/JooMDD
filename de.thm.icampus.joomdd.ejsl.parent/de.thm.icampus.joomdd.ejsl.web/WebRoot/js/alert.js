define('alert',['jquery',"jstree"], function(jQuery, jstree) {
	var alert = {};
	alert.showSuccess = function(text){
        $("#alert").toggleClass('alert-success', true)
        $("#alert").toggleClass('alert-danger', false)
		$("#alert").hide().show();
        $("#alert #alertState").text("Success! ");
		$("#alert #alertText").text(text);
	};
	
	alert.showError = function(text){
        $("#alert").toggleClass('alert-danger', true)
        $("#alert").toggleClass('alert-success', false)
        $("#alert").hide().show();
        $("#alert #alertState").text("Error! ");
        $("#alert #alertText").text(text);
    };

	alert.showloadmodal = function(text){
		$(".loader").css("display","inline-flex");
	};
	alert.closeloadmodal = function(text){
		$(".loader").css("display","none");
	};
	
	return alert;
})
