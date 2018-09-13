/**
 * 
 */
package com.mom.webapp.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mom.webapp.model.Address_;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.Offer;

/**
 * @author Brehima
 *
 */
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {
//	Page<BlogPost> findByPublishedIsTrueOrderByPublishedTimeDesc(Pageable pageable);
//	Page<BlogPost> getAllPublishedPosts(Pageable pageable);
	@Query(
			  value = "SELECT * FROM offer ofr INNER JOIN address adr ON ofr.address_id = adr.address_id INNER JOIN items_catalog icg ON ofr.catalog_item_id=icg.items_catalog_id WHERE adr.lng >= :swLng AND adr.lng <= :neLng AND adr.lat >= :swLat AND adr.lat <= :neLat ORDER BY ofr.offer_last_update",
			  countQuery = "SELECT count(*) FROM offer ofr INNER JOIN address adr ON ofr.address_id = adr.address_id INNER JOIN items_catalog icg ON ofr.catalog_item_id=icg.items_catalog_id WHERE adr.lng >= :swLng AND adr.lng <= :neLng AND adr.lat >= :swLat AND adr.lat <= :neLat",
			  nativeQuery = true)
	Page<Offer> findAllOffersInMapBounds(Pageable pageable, @Param("neLng") Optional<Double> neLng,
			@Param("neLat") Optional<Double> neLat, @Param("swLng") Optional<Double> swLng, @Param("swLat") Optional<Double> swLat);
	
	Page<Offer> findAllByCustomerOrderByOfferLastUpdateDesc(Pageable pageable, Customer customer);

	Offer findAllByOfferId(Long offerId);

	void deleteByOfferId(Long offerId);


}
