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
public class Security {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer secuId;
	private String secuName;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Integer getSecuId() {
		return secuId;
	}

	public void setSecuId(Integer secuId) {
		this.secuId = secuId;
	}

	public String getSecuName() {
		return secuName;
	}

	public void setSecuName(String secuName) {
		this.secuName = secuName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
