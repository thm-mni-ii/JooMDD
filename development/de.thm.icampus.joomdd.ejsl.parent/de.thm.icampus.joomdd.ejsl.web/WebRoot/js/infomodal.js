define('infomodal',[ 'jquery',"jstree", "cookie"], function(jQuery,jstree,Cookies) {
	var exports = {};
	 exports.showmodal = function(text){
		$("#infoModalText").text(text);
		$("#infoModal").css("display","block");
	 };
	 exports.showloadmodal = function(text){
			$("#loadModal").css("display","block");
		 };
	 exports.closeloadmodal = function(text){
			$("#loadModal").css("display","none");
		 };
		 
	// Get the modal
	var modal = document.getElementById('infoModal');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
		 
	 return exports;
})
