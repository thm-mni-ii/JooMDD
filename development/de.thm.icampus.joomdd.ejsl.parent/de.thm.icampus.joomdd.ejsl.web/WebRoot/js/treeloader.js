define('treeloader',[ 'jquery',"cookie","jstree","infomodal"], function(jQuery,Cookies,jstree,infomodal) {
	   var exports = {};
	   function context_menu(node){
			var tree = $('#folder_tree').jstree(true);
		 
			// The default set of all items
		    var items = {
		        "Download": {
		            "separator_before": false,
		            "separator_after": true,
		            "label": "Download",
		            "action": function (obj) { 
		            	exports.downloadone(obj)
		            }
		        },                         
		        "Remove": {
		            "separator_before": true,
		            "separator_after": false,
		            "label": "Remove",
		            "action": function (obj) { 
		            	if(confirm('By clicking "OK" the whole sub-tree of the selected node will be deleted. Are you sure?')){
		            		exports.deleteItem(obj,tree);
		            	}
		            }
		        }
		    };
		    return items;
		}
	   exports.writeTree= function(){
		   $('#folder_tree').jstree({
				'core' : {
					'data' : {
						"url" : "/tree-loader",
						"dataType" : "json"
					}
				},
				'plugins': ['contextmenu', "sort", 'state'], 
            	contextmenu: {items: context_menu}
			});
	   }
	   exports.deleteItem = function(node,tree){
		   var name = Cookies.get('joomddusername');
		   var tnode = node.reference[0];
		   	var link= tnode.href;
			$.ajax({
				  url: link,
				  type: 'DELETE',
				  data: {user:name,id:node.reference[0].id},
				  success: function(data) {
				    if(data){
	            		tree.delete_node(node);
				    	exports.reload();
				    }else{
				    	infomodal.showmodal("Sorry, the selected node cannot be found. (Maybe there's a blank in the file name)");
				    }
				  }
				}); 
	   }
	   
	   exports.reload= function(){
		   $('#folder_tree').jstree(true).refresh();
	   }
	   exports.downloadAll = function(){
			var index ;
			var data = $('#folder_tree').jstree(true).get_selected()
			for ( index = 0; index < data.length; ++index) {
			    var value = data[index];
			    window.open(value)
			}
	   }
	   exports.downloadone = function(node){
		   var tnode = node.reference[0];
		   	var link= tnode.href;
		   window.open(link)
	   }
	   return exports;
	
	});

