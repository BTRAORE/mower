/**
 * 
 */
package com.mom.webapp.dto;

/**
 * @author Brehima
 *
 */
public class PriceFilter {
	
 private Double minPrice;
 private Double maxPrice;
/**
 * @return the minPrice
 */
public Double getMinPrice() {
	return minPrice;
}
/**
 * @param minPrice the minPrice to set
 */
public void setMinPrice(Double minPrice) {
	this.minPrice = minPrice;
}
/**
 * @return the maxPrice
 */
public Double getMaxPrice() {
	return maxPrice;
}
/**
 * @param maxPrice the maxPrice to set
 */
public void setMaxPrice(Double maxPrice) {
	this.maxPrice = maxPrice;
}
 
}
