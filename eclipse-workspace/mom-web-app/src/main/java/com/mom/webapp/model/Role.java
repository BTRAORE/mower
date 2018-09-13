/**
 * 
 */
package com.mom.webapp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Brehima
 *
 */
@Entity
public class Role {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer role_id;
	private String authority;
	@ManyToMany(mappedBy = "roles")
	private Set<Credentials> credentials;
	/**
	 * @return the role_id
	 */
	public Integer getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the authoriy
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authoriy the authoriy to set
	 */
	public void setAuthority(String authoriy) {
		this.authority = authoriy;
	}
	/**
	 * @return the credentials
	 */
	public Set<Credentials> getCredentials() {
		return credentials;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(Set<Credentials> credentials) {
		this.credentials = credentials;
	}

}
