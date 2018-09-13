/**
 * 
 */
package com.mom.webapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Brehima
 *
 */
public class AddressDto {
	
	@NotNull
    @NotEmpty
	private Integer streetNum;
	
	@NotNull
    @NotEmpty
	private String streetName;
	
	@NotNull
    @NotEmpty
	private String zipCode;
	
	@NotNull
    @NotEmpty
	private String country;
	
	@NotNull
    @NotEmpty
	private String city;
	
	@NotNull
    @NotEmpty
	private Double lat;
	
	@NotNull
    @NotEmpty
	private Double lng;
	@NotNull
    @NotEmpty
	private String region;

	/**
	 * @return the streetNum
	 */
	public Integer getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetNum the streetNum to set
	 */
	public void setStreetNum(Integer streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public Double getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(Double lng) {
		this.lng = lng;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AddressDto [streetNum=" + streetNum + ", streetName=" + streetName + ", zipCode=" + zipCode
				+ ", country=" + country + ", city=" + city + ", lat=" + lat + ", lng=" + lng + ", region=" + region
				+ "]";
	}
	
	
}
