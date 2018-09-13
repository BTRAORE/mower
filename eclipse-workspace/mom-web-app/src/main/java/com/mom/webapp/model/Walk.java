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
public class Walk {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer walkId;
	private String walkName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getWalkId() {
		return walkId;
	}

	public void setWalkId(Integer walkId) {
		this.walkId = walkId;
	}

	public String getWalkName() {
		return walkName;
	}

	public void setWalkName(String walkName) {
		this.walkName = walkName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
