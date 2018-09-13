/**
 * 
 */
package com.mom.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Brehima
 *
 */
@Entity
public class ItemState {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	 private Long itemStateId;
	 private String stateName;
	/**
	 * @return the itemStateId
	 */
	public Long getItemStateId() {
		return itemStateId;
	}
	/**
	 * @param itemStateId the itemStateId to set
	 */
	public void setItemStateId(Long itemStateId) {
		this.itemStateId = itemStateId;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	 
}
