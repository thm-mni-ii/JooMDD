define('alert',['jquery',"jstree"], function(jQuery, jstree) {
	var alert = {};
	alert.showSuccess = function(text){
        $("#alert").toggleClass('alert-success', true)
        $("#alert").toggleClass('alert-danger', false)
		$("#alert").hide().show();
        $("#alertState").text("Success! ");
		$("#alertText").text(text);
	};
	alert.showError = function(text){
        $("#alert").toggleClass('alert-danger', true)
        $("#alert").toggleClass('alert-success', false)
        $("#alert").hide().show();
        $("#alertState").text("Error! ");
        $("#alertText").text(text);
    };


	alert.showloadmodal = function(text){
		$(".loader").css("display","inline-block");
	};
	alert.closeloadmodal = function(text){
		$(".loader").css("display","none");
	};
		 
	/*
	// Get the modal
	var modal = document.getElementById('infoModal');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	*/
	return alert;
})
