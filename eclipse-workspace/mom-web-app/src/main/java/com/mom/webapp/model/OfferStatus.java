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
public class OfferStatus {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer offerStatusId;
	private String statusName;
	private String statusDescription;
	/**
	 * @return the offerStatusId
	 */
	public Integer getOfferStatusId() {
		return offerStatusId;
	}
	/**
	 * @param offerStatusId the offerStatusId to set
	 */
	public void setOfferStatusId(Integer offerStatusId) {
		this.offerStatusId = offerStatusId;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}
	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	

}
