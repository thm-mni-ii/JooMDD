(function ( $ ) {
 
    $.fn.imagePicker = function( options ) {
        
        // Define plugin options
        var settings = $.extend({
            // Classes for styling the input
            class: "btn btn-success"

        }, options );
        
        // Create an input inside each matched element
        return this.each(function() {

            $(this).children('#add').html(create_btn(this, settings));
        });
 
    };
 
    // Private function for creating the input element
    function create_btn(that, settings) {

       // var add = $(that).children('#add');
        var preview = $(that).children('#preview');

        if(settings.file != ''){
            var preview = create_preview(that, settings.file, '', settings);
            $(that).children('#add').attr('class','hidden');
            $(that).children('#preview').html(preview);
            return;   

        }
        // The input icon element
        var picker_btn_icon = $('<span class="">'+settings.showLabel+'</span>');
        
        // The actual file input which stays hidden
        var picker_btn_input = $('<input type="file" accept="'+ settings.accept +'" name="'+settings.name+'" />');
        
        // The actual element displayed
        var picker_btn = $('<div class="'+settings.class+' img-upload-btn"></div>')
            .append(picker_btn_icon)
            .append(picker_btn_input);
            
        // File load listener
        picker_btn_input.change(function(event) {
            if ($(this).prop('files')[0]) {
                // Use FileReader to get file
                var reader = new FileReader();
                
                // Create a preview once image has loaded
                reader.onload = function(e) {
                    var input = event.target;
                    var file = input.files[0];
                    var preview = create_preview(that, e.target.result,file.name , settings);
                    $(that).children('#add').attr('class','hidden');
                    $(that).children('#preview').removeAttr('class');
                    $(that).children('#preview').html(preview);
                }
                
                // Load image
                reader.readAsDataURL(picker_btn_input.prop('files')[0]);
            }                
        });

        return picker_btn
    };
    
    // Private function for creating a preview element
    function create_preview(that, src, filename, settings) {
        var picker_preview_image ;
        // The preview image
            if(settings.fieldtype == "image"){
                 picker_preview_image = $('<img src="'+src+'" class="img-responsive img-rounded" /><br/>');
            }else{
                var format = src.split(".").getLast() ;
                picker_preview_image = $('<div class="imgOutline thumbnail height-80 width-80 center"style="margin-left: 40px;margin-bottom:10px;" >'+
                    '<div class="height-50">'+
                    '<a style="display: block; width: 100%; height: 100%">'+
                    '<img src="'+settings.iconpath+ format + ".png" +
                '" class="img-responsive img-rounded" style="float: right; margin: 20px"/><br/></a>'+'</div> </div>');
            }


            
            // The remove image button
            var picker_preview_remove = $('<button class="btn btn-danger"><span >'+settings.deleteLabel + '</span></button>');

            // The preview element
            var picker_preview = $('<div class="text-center"></div>')
                .append(picker_preview_image)
                .append(picker_preview_remove);

            // Remove image listener
            picker_preview_remove.click(function() {
                settings.file="";
                var btn = create_btn(that, settings);
                $(that).children('#preview').html('');
                $(that).children('#preview').attr('class','hidden');
                $(that).children('#add').removeAttr('class','');
                $(that).children('#add').html(btn);
            });
            
            return picker_preview;
    };
    
}( jQuery ));

jQuery(document).ready(function() {
    jQuery('.img-picker').each(function(){
       jQuery(this).imagePicker({name: jQuery(this).attr("name"), file:jQuery(this).attr("file"), 
           deleteLabel:jQuery(this).attr("deleteLabel"), showLabel:jQuery(this).attr("showLabel"),accept:jQuery(this).attr("accept"),
           fieldtype:jQuery(this).attr("fieldtype"), iconpath:jQuery(this).attr("iconpath") })});
});