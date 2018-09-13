//$(document).ready(function($) {
// 
//        $('#myCarousel').carousel({
//                interval: 5000
//        });
// 
//        $('#carousel-text').html($('#slide-content-0').html());
// 
//        //Handles the carousel thumbnails
//       $('[id^=carousel-selector-]').click( function(){
//            var id = this.id.substr(this.id.lastIndexOf("-") + 1);
//            var id = parseInt(id);
//            $('#myCarousel').carousel(id);
//        });
// 
// 
//        // When the carousel slides, auto update the text
//        $('#myCarousel').on('slid.bs.carousel', function (e) {
//                 var id = $('.item.active').data('slide-number');
//                $('#carousel-text').html($('#slide-content-'+id).html());
//        });
//});
function initMap() {
        var map = new google.maps.Map(document.getElementById('side-map'), {
          zoom: 8,
          center: {lat: -34.397, lng: 150.644}
        });
        var geocoder = new google.maps.Geocoder();

        document.getElementById('submit').addEventListener('click', function() {
          geocodeAddress(geocoder, map);
        });
      }

      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }