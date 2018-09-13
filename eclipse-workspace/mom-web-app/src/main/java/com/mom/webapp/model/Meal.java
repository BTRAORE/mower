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
public class Meal {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer mealId;
	private String mealName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
