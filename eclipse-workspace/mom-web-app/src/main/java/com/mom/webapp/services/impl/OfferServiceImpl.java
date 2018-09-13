/**
 * 
 */
package com.mom.webapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mom.webapp.dao.OfferRepository;
import com.mom.webapp.model.Offer;
import com.mom.webapp.services.OfferService;

/**
 * @author Brehima
 *
 */
@Service
public class OfferServiceImpl implements OfferService {
	
	final OfferRepository offerRepository;
	
	@Autowired
	public OfferServiceImpl(OfferRepository offerRepository){
		this.offerRepository = offerRepository;
	}
	@Transactional(readOnly = true)
	@Override
	 public Page<Offer> listOffersByPage(Pageable pageable) {
		return offerRepository.findAll(pageable);
	}
}
