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
public class SubCategory {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long subCategoryId;
	private String scName;
	private String scDescription;
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	/**
	 * @return the subCategoryId
	 */
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	/**
	 * @return the scName
	 */
	public String getScName() {
		return scName;
	}
	/**
	 * @param scName the scName to set
	 */
	public void setScName(String scName) {
		this.scName = scName;
	}
	/**
	 * @return the scDescription
	 */
	public String getScDescription() {
		return scDescription;
	}
	/**
	 * @param scDescription the scDescription to set
	 */
	public void setScDescription(String scDescription) {
		this.scDescription = scDescription;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	

}
