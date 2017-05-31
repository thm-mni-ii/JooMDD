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
	 return exports;
})
