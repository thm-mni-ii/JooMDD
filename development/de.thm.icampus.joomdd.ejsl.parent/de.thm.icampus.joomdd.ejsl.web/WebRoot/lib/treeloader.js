
  
		define('treeloader',[ 'jquery',"jstree"], function(jQuery,jstree) {
			   var exports = {};
			   exports.writeTree= function(name){
				   $('#folder_tree').jstree({
						'core' : {
							'data' : {
								"url" : "/tree-loader/?name=" +name,
								"dataType" : "json"
							}
								
				
						}
					}); 
			   }
			   return exports;
			
			});

