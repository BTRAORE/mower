/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.EmailVerifToken;

/**
 * @author Brehima
 *
 */
public interface EmailVerifTokenRepository extends JpaRepository<EmailVerifToken, Long> {

	EmailVerifToken findByToken(String verifToken);

}
