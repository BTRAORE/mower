/**
 * 
 */
package com.mom.webapp.geocode;

/**
 * @author Brehima
 *
 */
public class GeocodeUtil {
	// 3958.75 in miles, change to 6371 for kilometer output
	public static final int EARTH_RADIUS_IN_KM = 6371;
	//@Override
	//public void onLocationChanged(Location location) {
	//
	// double lat2 = location.getLatitude();
	// double lng2 = location.getLongitude();

	    // lat1 and lng1 are the values of a previously stored location
//	    if (distance(lat1, lng1, lat2, lng2) < 0.1) { // if distance < 0.1 miles we take locations as equal
	       //do what you want to do...
//	    }
	//}

	/**
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static  double distance(double lat1, double lng1, double lat2, double lng2) {
	
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	        * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
	
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	
	    double dist = EARTH_RADIUS_IN_KM * c;

	    return dist; // output distance, in MILES
	}
	public static boolean isValidMapBound(double neLat, double neLng, double swLat, double swLng) {
		return (neLat != 0 && neLng != 0 && swLat != 0 && swLng != 0);
	}
//	public boolean isLongitudesPositive(int lng)
	/**
	 * 

In Google Maps, the LatLngBounds object constructor takes the southwest and northeast corners of the rectangular bounds.

Knowing that one point is the southwest corner and not the southeast corner is crucial to solving the problem.

If you know that one point is the southwest corner and the other the northeast corner, then compare the two corners' longitudes. Your algorithm will work like this:

If the longitude of the southwest corner is less than the longitude of the northeast corner, than a point is within the bounds if it has a longitude between those two longitudes and a latitude between the two latitudes of the corners. (This is probably what you're doing for everything which is why it works in Africa where the condition is true, but not in something that spans the Pacific Ocean.)

If the longitude of the southwest corner is more than the longitude of the northeast corner,
 than your bounds crosses 0 longitude and you need to reverse the condition: A point iswithin the bounds if it has a longitude that is not numerically between the longitude of the southwest corner and the longitude of the northeast corner.

For simplicity and because it is almost certainly true, I am assuming that you will never have a bounds that covers the North Pole or the South Pole. (In Google Maps, I don't even know if you can have such a bounds.)
*/
}
