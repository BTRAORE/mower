/**
 * 
 */
package com.mom.webapp.dto;

/**
 * @author Brehima
 *
 */
public class SearchCriteria {
	private String[] categoryFilter;
	private PriceFilter price;
	private String[] brand;
	private String[] state;
	
	/**
	 * @return the categoryFilter
	 */
	public String[] getCategoryFilter() {
		return categoryFilter;
	}
	/**
	 * @param categoryFilter the categoryFilter to set
	 */
	public void setCategoryFilter(String[] categoryFilter) {
		this.categoryFilter = categoryFilter;
	}
	/**
	 * @return the price
	 */
	public PriceFilter getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(PriceFilter price) {
		this.price = price;
	}
	/**
	 * @return the brand
	 */
	public String[] getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String[] brand) {
		this.brand = brand;
	}
	/**
	 * @return the state
	 */
	public String[] getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String[] state) {
		this.state = state;
	}
	
}
