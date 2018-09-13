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
public class Decoration {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer decoId;
	private String decoName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getDecoId() {
		return decoId;
	}

	public void setDecoId(Integer decoId) {
		this.decoId = decoId;
	}

	public String getDecoName() {
		return decoName;
	}

	public void setDecoName(String decoName) {
		this.decoName = decoName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


}
