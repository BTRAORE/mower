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
public class Toilet {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer toiletId;
	private String toiletName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getToiletId() {
		return toiletId;
	}

	public void setToiletId(Integer toiletId) {
		this.toiletId = toiletId;
	}

	public String getToiletName() {
		return toiletName;
	}

	public void setToiletName(String toiletName) {
		this.toiletName = toiletName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
