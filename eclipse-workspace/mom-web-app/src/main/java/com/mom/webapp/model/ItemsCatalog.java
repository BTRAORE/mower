/**
 * 
 */
package com.mom.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Brehima
 *
 */
@Entity
public class ItemsCatalog {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	 private Long itemsCatalogId;
	 private String itemName;
	 @ManyToOne
	 @JoinColumn(name = "sub_category")
	 private SubCategory subCategory;
	/**
	 * @return the itemsCatalogId
	 */
	public Long getItemsCatalogId() {
		return itemsCatalogId;
	}
	/**
	 * @param itemsCatalogId the itemsCatalogId to set
	 */
	public void setItemsCatalogId(Long itemsCatalogId) {
		this.itemsCatalogId = itemsCatalogId;
	}
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
	/**
	 * @return the subCategory
	 */
	public SubCategory getSubCategory() {
		return subCategory;
	}
	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	 
	 
	 
 
}
