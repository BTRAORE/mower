/**
 * 
 */
package com.mom.webapp.geocode;

/**
 * @author Brehima
 *
 */
public class LatLng {
	
	private double lat;
	private double lng;
	
	public LatLng(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}
	public boolean isValid() {
		return (lat != 0 && lng != 0);
	}
	

}
