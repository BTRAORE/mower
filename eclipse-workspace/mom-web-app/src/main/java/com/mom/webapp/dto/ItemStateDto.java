/**
 * 
 */
package com.mom.webapp.dto;

/**
 * @author Brehima
 *
 */
public class ItemStateDto {
	
	private String stateName;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemStateDto [stateName=" + stateName + "]";
	}
	
	 
}
