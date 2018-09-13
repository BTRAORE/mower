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
public class Duration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long durationId;
	private String durationName;

	/**
	 * @return the durationId
	 */
	public Long getDurationId() {
		return durationId;
	}

	/**
	 * @param durationId
	 *            the durationId to set
	 */
	public void setDurationId(Long durationId) {
		this.durationId = durationId;
	}

	/**
	 * @return the durationName
	 */
	public String getDurationName() {
		return durationName;
	}

	/**
	 * @param durationName
	 *            the durationName to set
	 */
	public void setDurationName(String durationName) {
		this.durationName = durationName;
	}

}
