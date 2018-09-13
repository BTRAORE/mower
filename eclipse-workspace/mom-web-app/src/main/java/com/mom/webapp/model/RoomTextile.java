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
public class RoomTextile {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer textileId;
	private String textileName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getTextileId() {
		return textileId;
	}

	public void setTextileId(Integer textileId) {
		this.textileId = textileId;
	}

	public String getTextileName() {
		return textileName;
	}

	public void setTextileName(String textileName) {
		this.textileName = textileName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
