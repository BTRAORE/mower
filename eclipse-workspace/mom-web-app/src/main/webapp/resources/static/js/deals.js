$(document).ready(function() {
	$(function () {
		  $('[data-toggle="tooltip"]').tooltip();
		});
	changePageAndSize();
	  $(".menuItem a").click(function(){
		  var qq = $(".menuItem a");
		    $(".menuItem a").removeClass("active");
		    var kk = $(this);
		    $(this).addClass("active");
		    var kk = $(this);

		});
	  $("#marque-search-input").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#marque-select *").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
	  
	//// slider call
	  var minPrice = $("#min-price").val();
	  var maxPrice = $("#max-price").val();
	  if(maxPrice == 0){
		  maxPrice = 1000;
	  }
	  
	  $('#price-slider').slider({
	  	range: true,
	  	min: 0,
	  	max: 2500,
	  	step:10,
	  	values: [ minPrice, maxPrice ],
	  	slide: function(event, ui) {
	  		$('.ui-slider-handle:eq(0) .price-range-min').html( ui.values[ 0 ]+'€');
	  		$('.ui-slider-handle:eq(1) .price-range-max').html(ui.values[ 1 ]+'€');
	  		$('.price-range-both').html('<i>' + ui.values[ 0 ] + ' - </i>' + ui.values[ 1 ]+'€' );
	  		
	  		//
	  		
	    if ( ui.values[0] == ui.values[1] ) {
	      $('.price-range-both i').css('display', 'none');
	    } else {
	      $('.price-range-both i').css('display', 'inline');
	    }
	  		if (collision($('.price-range-min'), $('.price-range-max')) == true) {
	  			$('.price-range-min, .price-range-max').css('opacity', '0');	
	  			$('.price-range-both').css('display', 'block');		
	  		} else {
	  			$('.price-range-min, .price-range-max').css('opacity', '1');	
	  			$('.price-range-both').css('display', 'none');		
	  		}
	  	},
	  	change: function(event, ui){
	  		$("#min-price").val(ui.values[0]);
	  		$("#max-price").val(ui.values[1]).trigger('change');
	  	}
	  });

	  $('.ui-slider-range').append('<span class="price-range-both value"><i>€' + $('#price-slider').slider('values', 0 ) + ' - </i>' + $('#slider').slider('values', 1 ) + '</span>');

	  $('.ui-slider-handle:eq(0)').append('<span class="price-range-min value">' + $('#price-slider').slider('values', 0 ) + '€</span>');

	  $('.ui-slider-handle:eq(1)').append('<span class="price-range-max value">' + $('#price-slider').slider('values', 1 ) + '€</span>');
	  $("#distance-slider").slider({
          animate: true,
          value:1,
          min: 0,
          max: 100,
          step: 10,
          value: 20,
          slide: function(event, ui) {
  	  		
  	  		$('.ui-slider-handle .distance-value').html( ui.value+'km');
  	  		$(".distance-value").tooltip();
  	  		
  	  	}
  	  });
  	  $('#distance-slider .ui-slider-handle').append('<span class="distance-value value">' + $('#distance-slider').slider('value') + 'km</span>');

//      update();
  	 $(document).on('shown.bs.collapse', '.card-body', function () {
  	  $(this).prev('.card-header').addClass("mcard-collapsed");
  	});
  	$(document).on('hidden.bs.collapse', '.card-body', function () {
    	  $(this).prev('.card-header').removeClass("mcard-collapsed");
    });
  	$('.selectpicker').selectpicker();
  	/* submit if elements of class=filter_auto_submit in the form changes */
  	$(function() {
  	   $(".filter_auto_submit").change(function() {
  	    $(this).parents("form").submit();
  	   });
  	 });
});
$("form#deleteOffer").submit(function(e) {
    e.preventDefault();
    var deleteUrl = $(this).attr("action");
    var token = $(this).find("#csrfToken").val();
    var formData = new FormData(this);
    sendAjaxQuery(deleteUrl, 'PUT', formData, token, '/deals/dealsHome');
});
$("#offer-phone").on("click", function() {
	  var el = $(this);
	  if (el.text() == el.data("phone-num")) {
	    el.html(el.data("text-original"));
	  } else {
	    el.data("text-original", el.html());
	    el.html('<span class="fa fa-phone"></span> '+el.data("phone-num"));
	  }
	});
function sendAjaxQuery(url, method, data, token, targetUrl){
	$.ajax({
        type: method,
        method:method,
        url: url,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        headers: { "X-CSRF-TOKEN": token },
        data : data,
        success: function(response){
        	window.location.href = targetUrl;
        },
        error: function(err){
            console.log(err);
        }
    })	
  }
function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/?pageSize=" + this.value + "&page=1");
	});
}
function collision($div1, $div2) {
    var x1 = $div1.offset().left;
    var w1 = 40;
    var r1 = x1 + w1;
    var x2 = $div2.offset().left;
    var w2 = 40;
    var r2 = x2 + w2;
      
    if (r1 < x2 || x1 > r2) return false;
    return true;
    
  }
  
