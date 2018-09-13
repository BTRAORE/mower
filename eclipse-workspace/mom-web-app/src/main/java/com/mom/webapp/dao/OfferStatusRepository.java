/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.OfferStatus;

/**
 * @author Brehima
 *
 */
public interface OfferStatusRepository extends JpaRepository<OfferStatus, Long> {

	OfferStatus findByStatusName(String string);

}
