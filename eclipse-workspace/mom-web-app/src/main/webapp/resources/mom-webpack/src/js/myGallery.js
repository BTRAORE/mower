let modalId = $('#image-gallery');
var imgFiles = [], prevImgs = [{id:1, loaded: false}, {id:2, loaded: false}, {id:3,loaded: false}];
var preview, prevImgCount=3;
$(document)
  .ready(function () {
	  preview = $("#freeImgRow");
	  for (var i = 0; i < prevImgs.length; i++) {
		  addNewPreviewPlaceholder(prevImgs[i].id);  
	  }
    loadGallery(true, 'a.thumbnail');

    //This function disables buttons when needed
    function disableButtons(counter_max, counter_current) {
      $('#show-previous-image, #show-next-image')
        .show();
      if (counter_max === counter_current) {
        $('#show-next-image')
          .hide();
      } else if (counter_current === 1) {
        $('#show-previous-image')
          .hide();
      }
    }

    /**
     *
     * @param setIDs        Sets IDs when DOM is loaded. If using a PHP counter, set to false.
     * @param setClickAttr  Sets the attribute for the click handler.
     */

    function loadGallery(setIDs, setClickAttr) {
      let current_image,
        selector,
        counter = 0;

      $('#show-next-image, #show-previous-image')
        .click(function () {
          if ($(this)
            .attr('id') === 'show-previous-image') {
            current_image--;
          } else {
            current_image++;
          }

          selector = $('[data-image-id="' + current_image + '"]');
          updateGallery(selector);
        });

      function updateGallery(selector) {
        let $sel = selector;
        current_image = $sel.data('image-id');
        $('#image-gallery-title')
          .text($sel.data('title'));
        $('#image-gallery-image')
        .attr('src', $sel.find("img").attr('src'));
        disableButtons(counter, $sel.data('image-id'));
      }

      if (setIDs == true) {
        $('[data-image-id]')
          .each(function () {
            counter++;
            $(this)
              .attr('data-image-id', counter);
          });
      }
      $(setClickAttr)
        .on('click', function () {
          updateGallery($(this));
        });
    }
  });
function handleFileSelect(event) {
    //Check File API support
    if (window.File && window.FileList && window.FileReader) {

        var files = event.target.files; //FileList object
//        var preview = document.getElementById("freeImgRow");

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            //Only pics
            if (!file.type.match('image')) continue;

            var picReader = new FileReader();
            picReader.addEventListener("load", function (event) {
                var picFile = event.target;
//                var div = document.createElement("div");
//                div.className += "col-lg-4 col-md-4 col-xs-6 thumb";
//                div.innerHTML = "<a class='thumbnail' href='#' data-image-id='' data-toggle='modal' data-title='' data-image='" + picFile.result +"' data-target='#image-gallery'><img class='img-thumbnail' src='" + picFile.result + "'" + "title='" + file.name + "' width='1100' height='650' /></a>";
                for (var i = 0; i < prevImgs.length; i++) {
                	var imgProp = prevImgs[i];
                	if(imgProp.loaded==false){
                		var prev = preview.find("#prevImg"+imgProp.id);
                		var iii = prev.find("img");
                		prev.find("img").attr('src', picFile.result);
                		imgProp.loaded=true;
                		break;
                	}
                	
                }
            });
            imgFiles.push(file);
            //Read the image
            picReader.readAsDataURL(file);
        }
    } else {
        console.log("Your browser does not support File API");
    }
}
function addNewPreviewPlaceholder(imgId){
	 var div = document.createElement("div");
     div.className += "col-lg-4 col-md-4 col-xs-6 thumb";
     div.id="prevImg"+imgId;
     div.innerHTML = "<a class='thumbnail' href='#' data-image-id='' data-toggle='modal' data-title='' data-image='' data-target='#image-gallery'><img src='' style='border: 2px dashed #1c7178;'width='260' height='180' /></a>";
     preview.append(div);
}
document.getElementById('imgFiles').addEventListener('change', handleFileSelect, false);
$("form#offerCreation").submit(function(e) {
    e.preventDefault();
    
    var formData = new FormData(this);
    var imgFilesLength = imgFiles.length;
    for (var i = 0; i < imgFilesLength; i++) {
    	formData.append("files", imgFiles[i], imgFiles[i].name);
    }
//    var urrl = $(this).attr("action");
    $.ajax({
        type: 'POST',
        method:'POST',
        url: $(this).attr("action"),
        cache: false,
        contentType: false,
        processData: false,
        data : formData,
        success: function(response){
        	window.location.href = 'userOffers';
        },
        error: function(err){
            console.log(err);
        }
    })
//    $.post($(this).attr("action").val(), formData, function(data) {
//        alert(data);
//    });
});
// build key actions
$(document)
  .keydown(function (e) {
    switch (e.which) {
      case 37: // left
        if ((modalId.data('bs.modal') || {})._isShown && $('#show-previous-image').is(":visible")) {
          $('#show-previous-image')
            .click();
        }
        break;

      case 39: // right
        if ((modalId.data('bs.modal') || {})._isShown && $('#show-next-image').is(":visible")) {
          $('#show-next-image')
            .click();
        }
        break;

      default:
        return; // exit this handler for other keys
    }
    e.preventDefault(); // prevent the default action (scroll / move caret)
  });
