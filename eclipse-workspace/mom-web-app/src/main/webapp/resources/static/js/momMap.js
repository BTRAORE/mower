/**
 * 
 */
var map;
var markers = [];
var markerAddrMap = {};
var addrOfferMap = {};
var geocoder, placesAdrs;
var geocodeRetryCount = 0;
var isMapFirstLoad = true;
//address autocomplete
var placeSearch, addressAutocomplete;
var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name',
  country: 'long_name',
  postal_code: 'short_name'
};
var isUserMoveIdleSet = false, idleListener;
var mapCenterLatLng, offerMapCenterLatLng;
function initMap() 
{
	autocompleteCities();
	initAutocomplete();
	var parisLatLng = {lat: 48.8566, lng: 2.3522};
	//offer details map
	var offerDetails = document.getElementsByClassName("details-box");
	var ofDet = offerDetails[0];
	if(ofDet){
		var lat = $(ofDet).find("input.lat").val();
		var lng = $(ofDet).find("input.lng").val();
		offerMapCenterLatLng = {"lat":parseFloat(lat), "lng":parseFloat(lng)};
		var offerDetailsOptions = 
	    {
	        zoom: 13,
	        mapTypeId: google.maps.MapTypeId.ROADMAP,
	        center:offerMapCenterLatLng
	    };
		offerDetailsMap = new google.maps.Map(document.getElementById("details-side-map"), offerDetailsOptions);
		var priceValue = parseFloat($(ofDet).find("input.offer-price").val());
		var marker = new RichMarker({
	        position: new google.maps.LatLng(offerMapCenterLatLng.lat, offerMapCenterLatLng.lng),
	        map: offerDetailsMap,
	        anchor: RichMarkerPosition.MIDDLE,
	        shadow:'none',
	        content: '<div class="map-marker text-center">'+priceValue+'€</div>',
	        });
	}
	// offer details map end
	$("input.autocomplete-address").focus(function() {geolocate();});
//	mapCenterLatLng = parisLatLng;
	var offerList = document.getElementsByClassName("offer-list-item");
	var firstItem = offerList[0];
	if(firstItem){
		var lat = $(firstItem).find("input.lat").val();
		var lng = $(firstItem).find("input.lng").val();
		mapCenterLatLng = {"lat":parseFloat(lat), "lng":parseFloat(lng)};
	}else{
		mapCenterLatLng = parisLatLng;
	}
    var myOptions = 
    {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center:mapCenterLatLng
    };
    map = new google.maps.Map(document.getElementById("side-map"), myOptions);
    geocoder = new google.maps.Geocoder();
	    idleListener = google.maps.event.addListener(map, 'idle', function() {
	    	showVisibleMarkers();
		});
//	    idleListener = google.maps.event.addListener(map, 'idle', function() {
//	    	sendMapQuery();
//	    	showVisibleMarkers();
//	    });
    
//    createOffersMarkers(buildOffersInfos());
}
$( document ).ready(function() {
	$("#cities-autocomplete").on("blur", function(){
	})
});

function createOffersMarkers(offersInfos){
//	map.setCenter(offersInfos[0].latLng);
	for(var i=0; i < offersInfos.length; i++){
	    var offerInfos = offersInfos[i];
		var marker = new RichMarker({
	          position: new google.maps.LatLng(offerInfos.latLng.lat, offerInfos.latLng.lng),
	          map: map,
	          draggable: true,
	          animation: google.maps.Animation.DROP,
	          anchor: RichMarkerPosition.MIDDLE,
	          shadow:'none',
	          content: '<div class="map-marker text-center">'+offerInfos.price+'€</div>',
	        //custom attributes
	          offerId : offerInfos.offerId
	          });
	    var infoWindow = createInfowindow();
	    google.maps.event.addListener(marker, 'click', function () {
	        infoWindow.open(map, marker);
	    });
	    google.maps.event.addListener(marker, 'mouseover', function () {
	        infoWindow.open(map, marker);
	    });
	    google.maps.event.addListener(marker, 'mouseout', function () {
	    	infoWindow.close();
	    });
	    markers.push(marker);
	}
}
function buildOffersInfos(){
	
	var offerList = document.getElementsByClassName("offer-list-item");
	var offersInfos = [];
	for(var i=0; i < offerList.length; i++){
		var offerItem = offerList[i];
		var offerIdValue = offerItem.getAttribute('id');
		var latValue = $(offerItem).find("input.lat").val();
		var lngValue = $(offerItem).find("input.lng").val();
		var priceValue = $(offerItem).find(".offer-price")[0].innerHTML;
		var offerInfos = {"offerId": offerIdValue, "latLng":{"lat":parseFloat(latValue), "lng": parseFloat(lngValue)}, "price": priceValue};
		offersInfos[i] = offerInfos;
	}
	return offersInfos;
}
function processAjaxData(response, urlPath){
	var resp = $(response).find("#deals-container");
    $("#deals-container").replaceWith($(response).find("#deals-container")[0]);
//    document.title = response.pageTitle;
//    window.history.pushState("le corps","", urlPath);
}
function sendMapQuery(){
	var bounds = map.getBounds(),
    count = 0;
	var boundsNeLatLng = bounds.getNorthEast();
	var boundsSwLatLng = bounds.getSouthWest();
	var neLat = boundsNeLatLng.lat();
	var swLat = boundsSwLatLng.lat();
	var neLng = boundsNeLatLng.lng();
	var swLng = boundsSwLatLng.lng();
//	var formData = new FormData(this);
//    var imgFilesLength = imgFiles.length;
//    for (var i = 0; i < imgFilesLength; i++) {
//    	formData.append("files", imgFiles[i], imgFiles[i].name);
//    }
	 $.ajax({
     	type: 'GET',
     	url: "/deals/dealsHomeMapTrig?ne_lat="+neLat+"&ne_lng="+neLng+"&sw_lat="+swLat+"&sw_lng="+swLng,
     	success: function(result){
     		processAjaxData(result, "/deals/dealsHomeMapTrig?ne_lat="+neLat+"&ne_lng="+neLng+"&sw_lat="+swLat+"&sw_lng="+swLng)
     		window.history.pushState("ssf","", "/deals/dealsHomeMapTrig?ne_lat="+neLat+"&ne_lng="+neLng+"&sw_lat="+swLat+"&sw_lng="+swLng);
     		showVisibleMarkers();
//     		$("#div1").html(result);
         },
         error: function (xhr, ajaxOptions, thrownError) {
         alert(xhr.status+"  : "+xhr.responseText);
         alert(thrownError);
       }
     });
}
function showVisibleMarkers() {
    var bounds = map.getBounds(),
    count = 0;
    createOffersMarkers(buildOffersInfos());
    for (var i = 0; i < markers.length; i++) {
        var marker = markers[i];
        var offerId = '#'+marker.offerId;
            infoPanel = $('.info-' + (i+1) ); // array indexes start at zero, but not our class names :)
                     var testd= offerId;                      
        if(bounds.contains(marker.getPosition())==true) {
        	marker.setMap(map);
        	if(marker.getVisible()==false){
        		marker.setVisible(true);
        	}
        	var objj = $(offerId);
        	$(offerId).show();
            count++;
        }
        else {
        	$(offerId).hide();
        	marker.setMap(null);
        	
        }
    }
    if(isUserMoveIdleSet == false){
	    google.maps.event.removeListener(idleListener);
	    google.maps.event.addListener(map, 'idle', function() {
			sendMapQuery();
		});
	    isUserMoveIdleSet = true;
    }
}
function createInfowindow(){
	// InfoWindow content
	  var infowContent = '<div class="card" style="width: 10rem;">'+
		  				'<img class="card-img-top" src="../../../resources/static/images/poussette.jpg" height="100" width="50" alt="Card image cap">'+
		  				'<h5 class="card-title">Card title</h5>'+
		  			'</div>';
	 var infoWindow = new google.maps.InfoWindow({
	        content: infowContent
	    });
return infoWindow;
}
function addPlacesAdrsMarkers(map, geocoder, placesAddrs) {
	markers=[];
	for (var i=0; i < placesAddrs.length; i++){
		var address = placesAddrs[i].innerHTML;
		geocodeRetryCount = 0;
		geocodeAddress(geocoder, map, address);
	}
}
/*********************** richmarker *****************/

function log(msg) {
  var log = document.getElementById('log');
  log.innerHTML = msg;
}
function setMarkerContent() {
  var content = document.getElementById('marker-content').value;
  marker.setContent(content);
}
function toggleMap() {
  if (marker.getMap() == map) {
    marker.setMap(map2);
  } else {
    marker.setMap(map);
  }
}
function toggleFlat() {
  marker.setFlat(!marker.getFlat());
}
function toggleVisible() {
  marker.setVisible(!marker.getVisible());
}
function toggleAnchor() {
  var anchor = marker.getAnchor();
  if (anchor == 9) {
    anchor = 1;
  } else {
    anchor++;
  }
  marker.setAnchor(anchor);
}
// Register an event listener to fire when the page finishes loading.
google.maps.event.addDomListener(window, 'load', initMap);
//***** map autocomplete
//var places, infoWindow;
var markers = [];
var autocomplete;
var countryRestrict = {'country': 'fr'};
//var MARKER_PATH = 'https://developers.google.com/maps/documentation/javascript/images/marker_green';
//var hostnameRegexp = new RegExp('^https?://.+?/');

var countries = {
  'au': {
    center: {lat: -25.3, lng: 133.8},
    zoom: 4
  },
  'br': {
    center: {lat: -14.2, lng: -51.9},
    zoom: 3
  },
  'ca': {
    center: {lat: 62, lng: -110.0},
    zoom: 3
  },
  'fr': {
    center: {lat: 46.2, lng: 2.2},
    zoom: 5
  },
  'de': {
    center: {lat: 51.2, lng: 10.4},
    zoom: 5
  },
  'mx': {
    center: {lat: 23.6, lng: -102.5},
    zoom: 4
  },
  'nz': {
    center: {lat: -40.9, lng: 174.9},
    zoom: 5
  },
  'it': {
    center: {lat: 41.9, lng: 12.6},
    zoom: 5
  },
  'za': {
    center: {lat: -30.6, lng: 22.9},
    zoom: 5
  },
  'es': {
    center: {lat: 40.5, lng: -3.7},
    zoom: 5
  },
  'pt': {
    center: {lat: 39.4, lng: -8.2},
    zoom: 6
  },
  'us': {
    center: {lat: 37.1, lng: -95.7},
    zoom: 3
  },
  'uk': {
    center: {lat: 54.8, lng: -4.6},
    zoom: 5
  }
};

//function initMap() {
//  map = new google.maps.Map(document.getElementById('map'), {
//    zoom: countries['us'].zoom,
//    center: countries['us'].center,
//    mapTypeControl: false,
//    panControl: false,
//    zoomControl: false,
//    streetViewControl: false
//  });

//  infoWindow = new google.maps.InfoWindow({
//    content: document.getElementById('info-content')
//  });

  // Create the autocomplete object and associate it with the UI input control.
  // Restrict the search to the default country, and to place type "cities".
function autocompleteCities(){
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {!HTMLInputElement} */ (
          document.getElementById('cities-autocomplete')), {
        types: ['(cities)'],
        componentRestrictions: countryRestrict
  });
//  places = new google.maps.places.PlacesService(map);
//
  autocomplete.addListener('place_changed', onPlaceChanged);

  // Add a DOM event listener to react when the user selects a country.
//  document.getElementById('country').addEventListener(
//      'change', setAutocompleteCountry);
}

// When the user selects a city, get the place details for the city and
// zoom the map in on the city.
function onPlaceChanged() {
  var place = autocomplete.getPlace();
  if (place.geometry) {
    map.panTo(place.geometry.location);
//    map.setZoom(15);
//    search();
  } else {
    document.getElementById('cities-autocomplete').placeholder = 'Enter a city';
  }
}

function clearMarkers() {
  for (var i = 0; i < markers.length; i++) {
    if (markers[i]) {
      markers[i].setMap(null);
    }
  }
  markers = [];
}

// Set the country restriction based on user input.
// Also center and zoom the map on the given country.
function setAutocompleteCountry() {
//  var country = document.getElementById('country').value;
//  if (country == 'all') {
//    autocomplete.setComponentRestrictions({'country': []});
//    map.setCenter({lat: 15, lng: 0});
//    map.setZoom(2);
//  } else {
//    autocomplete.setComponentRestrictions({'country': country});
//    map.setCenter(countries[country].center);
//    map.setZoom(countries[country].zoom);
//  }
  clearResults();
  clearMarkers();
}

function dropMarker(i) {
  return function() {
    markers[i].setMap(map);
  };
}

function addResult(result, i) {
  var results = document.getElementById('results');
  var markerLetter = String.fromCharCode('A'.charCodeAt(0) + (i % 26));
  var markerIcon = MARKER_PATH + markerLetter + '.png';

  var tr = document.createElement('tr');
  tr.style.backgroundColor = (i % 2 === 0 ? '#F0F0F0' : '#FFFFFF');
  tr.onclick = function() {
    google.maps.event.trigger(markers[i], 'click');
  };
}
//***** address autocomplete
//This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">



function initAutocomplete() {
  // Create the autocomplete object, restricting the search to geographical
  // location types.
	addressAutocomplete = new google.maps.places.Autocomplete(
      /** @type {!HTMLInputElement} */(document.getElementById('route')),
      {types: ['geocode']});

  // When the user selects an address from the dropdown, populate the address
  // fields in the form.
	addressAutocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
  // Get the place details from the autocomplete object.
  var place = addressAutocomplete.getPlace();

  for (var component in componentForm) {
    var elt = document.getElementById(component);
    if(elt){
    	elt.value = '';
    	elt.disabled = false;
    }
  }

  // Get each component of the address from the place details
  // and fill the corresponding field on the form.
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (componentForm[addressType]) {
      var val = place.address_components[i][componentForm[addressType]];
      var elt = document.getElementById(addressType);
      if(elt)
    	  elt.value = val;
    }
  }
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var geolocation = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      var latField = document.getElementById('lat');
      if(latField)
    	  latField.value = geolocation.lat;
      var lngField = document.getElementById('lng');
      if(lng)
    	  lngField.value = geolocation.lng;
      var circle = new google.maps.Circle({
        center: geolocation,
        radius: position.coords.accuracy
      });
      addressAutocomplete.setBounds(circle.getBounds());
    });
  }
}