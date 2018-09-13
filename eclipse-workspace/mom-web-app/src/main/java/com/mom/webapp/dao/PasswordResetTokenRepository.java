/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.PasswordResetToken;

/**
 * @author Brehima
 *
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

}
