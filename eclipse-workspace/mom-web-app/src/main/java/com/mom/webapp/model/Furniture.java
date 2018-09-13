/**
 * 
 */
package com.mom.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Brehima
 *
 */
@Entity
public class Furniture {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer furnitureId;
	private String furnitureName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getFurnitureId() {
		return furnitureId;
	}

	public void setFurnitureId(Integer furnitureId) {
		this.furnitureId = furnitureId;
	}

	public String getFurnitureName() {
		return furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


}
