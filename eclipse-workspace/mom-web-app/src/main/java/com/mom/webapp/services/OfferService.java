/**
 * 
 */
package com.mom.webapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mom.webapp.model.Offer;

/**
 * @author Brehima
 *
 */
public interface OfferService {

	Page<Offer> listOffersByPage(Pageable pageable);

}
