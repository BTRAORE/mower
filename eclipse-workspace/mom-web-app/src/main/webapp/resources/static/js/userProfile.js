


(function(removeClass) {
	$(function () {
		  $('[data-toggle="tooltip"]').tooltip();
		});
	jQuery.fn.removeClass = function( value ) {
		if ( value && typeof value.test === "function" ) {
			for ( var i = 0, l = this.length; i < l; i++ ) {
				var elem = this[i];
				if ( elem.nodeType === 1 && elem.className ) {
					var classNames = elem.className.split( /\s+/ );

					for ( var n = classNames.length; n--; ) {
						if ( value.test(classNames[n]) ) {
							classNames.splice(n, 1);
						}
					}
					elem.className = jQuery.trim( classNames.join(" ") );
				}
			}
		} else {
			removeClass.call(this, value);
		}
		return this;
	}

})(jQuery.fn.removeClass);

$(document).ready(function() {
	$('.selectpicker').selectpicker();
    $("#input-44").fileinput({
    	previewFileType: "image",
    	theme: "fa",
//        uploadUrl: '/offer/image/upload',
        showUpload: false, // hide upload button
//        deleteUrl: "/site/file-delete",
//    	dropZoneEnabled=true,
        maxFileCount: 5,
        initialPreviewShowDelete: true,
        overwriteInitial: false,
        language: "fr",
//        showRemove: true,
//        showUpload: false,
        textEncoding: 'UTF-8',
        showCaption: true,
        showPreview: true,
        showRemove: true,
//        showUpload: true,
        showCancel: true,
        showClose: true,
        showUploadedThumbs: true,
        initialPreviewAsData: true,
        maxFilePreviewSize: 10240,
        previewFileType: "image",
        browseClass: "btn mom-default-btn",
        browseLabel: "Choisir une image",
        browseIcon: "<i class=\"fa fa-camera\"></i> ",
        removeClass: "btn btn-danger",
        removeLabel: "Supprimer",
        removeIcon: "<i class=\"fa fa-trash\"></i> "
//        uploadClass: "btn btn-success",
//        uploadLabel: "Valider",
//        uploadIcon: "<i class=\"fa fa-upload\"></i> "
        	
    });
    var formd = new FormData();
    $('#input-44').on('fileloaded', function(event, file, previewId, index, reader) {
    	var mainImgRadio = $("<div class=\"imgPrev\"><label><input type=\"radio\" class=\"mainImgRadio\" name=\"mainImgRadio\" /> Image principale </label><input type=\"hidden\" class=\"mainImgHiddenInput\" value=\""+file.name+"\"/></div>");
    	$("#"+previewId).append(mainImgRadio); 
        $(".imgPrev").find("input.mainImgRadio").on("change", function() {
        	   $("#mainImg").val($(".imgPrev input.mainImgHiddenInput").val()); 
        });
        formd.app
        var filesInp = $("input#input-44[type='file']");
        var files = filesInp.prop("files");
        if(files.length > 1)
        	files[f]=file;
    });
   
    
    
});
/** offer edit checkboxes ***/
$(function () {
    $('.list-group.checked-list-box .list-group-item').each(function () {
        
        // Settings
        var $widget = $(this),
            $checkbox = $('<input type="checkbox" class="hidden" hidden="hidden" />'),
            color = "list-group-item-success",
            settings = {
                on: {
                    icon: 'fa fa-check-square'
                },
                off: {
                    icon: 'fa fa-square'
                }
            };
            
        $widget.css('cursor', 'pointer')
        $widget.append($checkbox);

        // Event Handlers
        $widget.on('click', function () {
            $checkbox.prop('checked', !$checkbox.is(':checked'));
            $checkbox.triggerHandler('change');
            updateDisplay();
        });
        $checkbox.on('change', function () {
            updateDisplay();
        });
          

        // Actions
        function updateDisplay() {
            var isChecked = $checkbox.is(':checked');

            // Set the button's state
            $widget.data('state', (isChecked) ? "on" : "off");

            // Set the button's icon
            $widget.find('.state-icon')
                .removeClass()
                .addClass('state-icon ' + settings[$widget.data('state')].icon);

            // Update the button's color
            if (isChecked) {
                $widget.addClass(color);
            } else {
                $widget.removeClass(color + ' active');
            }
        }

        // Initialization
        function init() {
            
            if ($widget.data('checked') == true) {
                $checkbox.prop('checked', !$checkbox.is(':checked'));
            }
            
            updateDisplay();

            // Inject the icon if applicable
            if ($widget.find('.state-icon').length == 0) {
                $widget.prepend('<span class="state-icon ' + settings[$widget.data('state')].icon + '"></span>');
            }
        }
        init();
    });
    if($('input.autocomplete-address').val()){
        $('div.address-component').show();
    }
    else {
        $('div.address-component').hide();
    }
    $('input.autocomplete-address').blur(function(){
        if( $(this).val() ) {
              $('div.address-component').show('slow');
        }else{
        	$('div.address-component').hide('slow');
        }
    });
    $("#oc-brand-search-input").on("keyup", function() {
		  var value = $(this).val().toLowerCase();
		  $("#oc-brand-list *").filter(function() {
			  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		  });
	  });
//    filterable inputs
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">Aucun resultat trouvé</td></tr>'));
        }
    });
    /** user image profile **/
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.profile-pic').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    

    $(".file-upload").on('change', function(){
        readURL(this);
    });
    
    $(".upload-button").on('click', function() {
       $(".file-upload").click();
    });
    /** End user profile **/
});
