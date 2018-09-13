/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Credentials;

/**
 * @author Brehima
 *
 */
public interface CredentialsRepository extends JpaRepository<Credentials, Integer>{

	Credentials findByUsername(String email);

}
