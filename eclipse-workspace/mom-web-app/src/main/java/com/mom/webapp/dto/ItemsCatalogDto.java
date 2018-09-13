/**
 * 
 */
package com.mom.webapp.dto;

/**
 * @author Brehima
 *
 */
public class ItemsCatalogDto {
	
	private String itemName;

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemsCatalogDto [itemName=" + itemName + "]";
	}
	
}
