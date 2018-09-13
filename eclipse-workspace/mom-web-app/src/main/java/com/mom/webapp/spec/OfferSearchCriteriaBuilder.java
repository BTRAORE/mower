/**
 * 
 */
package com.mom.webapp.spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mom.webapp.dao.BrandRepository;
import com.mom.webapp.dao.CategoryRepository;
import com.mom.webapp.dao.ItemStateRepository;
import com.mom.webapp.dao.ItemsCatalogRepository;
import com.mom.webapp.dao.OfferStatusRepository;
import com.mom.webapp.dao.SubCategoryRepository;
import com.mom.webapp.dao.TopicRepository;
import com.mom.webapp.dto.OfferProperties;
import com.mom.webapp.dto.PriceFilter;
import com.mom.webapp.dto.SearchCriteria;
import com.mom.webapp.geocode.LatLng;
import com.mom.webapp.model.Address;
import com.mom.webapp.model.Address_;
import com.mom.webapp.model.Brand;
import com.mom.webapp.model.Brand_;
import com.mom.webapp.model.Category;
import com.mom.webapp.model.Category_;
import com.mom.webapp.model.ItemState;
import com.mom.webapp.model.ItemState_;
import com.mom.webapp.model.ItemsCatalog;
import com.mom.webapp.model.ItemsCatalog_;
import com.mom.webapp.model.Offer;
import com.mom.webapp.model.OfferStatus;
import com.mom.webapp.model.OfferStatus_;
import com.mom.webapp.model.Offer_;
import com.mom.webapp.model.SubCategory;
import com.mom.webapp.model.SubCategory_;
import com.mom.webapp.model.Topic;
import com.mom.webapp.model.Topic_;
import com.mom.webapp.utils.ArraysUtil;
import com.mom.webapp.utils.OfferStatusEnum;

/**
 * @author Brehima
 *
 */
@Service
public class OfferSearchCriteriaBuilder {
	
@Autowired
private SubCategoryRepository subCategoryRepo;

private LatLng neCorner;
private LatLng swCorner;
private OfferProperties offerProperties;
private SearchCriteria searchCriteria;
 public OfferSearchCriteriaBuilder() {
}
 public OfferSearchCriteriaBuilder init() {
	 this.neCorner = null;
	 this.swCorner = null;
	 this.offerProperties = null;
	 this.searchCriteria = null;
	 return this;
 }
public OfferSearchCriteriaBuilder inMapBound(LatLng neCorner, LatLng swCorner) {
	this.neCorner = neCorner;
	this.swCorner = swCorner;
	return this;
}
public OfferSearchCriteriaBuilder withProperties(OfferProperties offerProperties) {
	this.offerProperties = offerProperties;
	return this;
}
public OfferSearchCriteriaBuilder withSearchCriteria(SearchCriteria searchCriteria) {
	this.searchCriteria =  searchCriteria;
	return this;
}

private Specification<Offer> northEastContriesSpec(){
	if(neCorner == null || swCorner == null) {
		return null;
	}
	  return (root, query, cb) -> {
		  Join <Offer, Address> address = root.join(Offer_.address);
		  Join<Offer, OfferStatus> offerStatus = root.join(Offer_.offerStatus);
		  return cb.and(
				  cb.equal(offerStatus.get(OfferStatus_.statusName), OfferStatusEnum.VALIDATED.name()),
				  cb.greaterThan(address.get(Address_.lng), swCorner.getLng()), 
				  cb.lessThan(address.get(Address_.lng), neCorner.getLng()),
				  cb.greaterThan(address.get(Address_.lat), swCorner.getLat()),
				  cb.lessThan(address.get(Address_.lat), neCorner.getLat()));
	  };
  }
	
public  Specification<Offer> build() {
	return onlyValidatedOffers()
	.and(northEastContriesSpec())
	.and(offerPropertiesSpec());
}
private Specification<Offer> onlyValidatedOffers() {
	 return (root, query, cb) -> {
		  Join<Offer, OfferStatus> offerStatus = root.join(Offer_.offerStatus);
		  return cb.and(cb.equal(offerStatus.get(OfferStatus_.statusName), OfferStatusEnum.VALIDATED.name()));
	 };
}
private Specification<Offer> offerPropertiesSpec(){
	  return (root, query, cb) -> {
		  List<Predicate> predicates = new ArrayList<Predicate>();
		  String topicFilterName = offerProperties.getTopic();
		  if(topicFilterName != null) {
			  Join<Offer, Topic> topic = root.join(Offer_.topic);
			  predicates.add(cb.equal(topic.get(Topic_.topicName), topicFilterName));
		  }
		  String catFilterName = offerProperties.getCategory();
		  if(catFilterName != null) {
			  Join<Offer, Category> category = root.join(Offer_.category);
			 predicates.add(cb.equal(category.get(Category_.categoryName), catFilterName));
		  }
		  String subCatNameFilter = offerProperties.getSubCategory();
		  String itemNameFilter = offerProperties.getItemName();
		  if(itemNameFilter == null && subCatNameFilter != null ) {
			  SubCategory subCat = subCategoryRepo.findByScName(subCatNameFilter);
			  if(subCat != null) {
				  Join<Offer, ItemsCatalog> item = root.join(Offer_.item);
				  predicates.add(cb.equal(item.get(ItemsCatalog_.subCategory), subCat));
			  }
		  }
		  if(itemNameFilter != null && subCatNameFilter != null) {
			  Join<Offer, ItemsCatalog> item = root.join(Offer_.item);
			  Join<ItemsCatalog, SubCategory> subCategory = item.join(ItemsCatalog_.subCategory);
			  predicates.add(cb.equal(subCategory.get(SubCategory_.scName), subCatNameFilter));
			  predicates.add(cb.equal(item.get(ItemsCatalog_.itemName), itemNameFilter));
		  }
		  PriceFilter priceFilter = searchCriteria.getPrice();
		  if(priceFilter != null && priceFilter.getMinPrice() != null && priceFilter.getMaxPrice() != null) {
			  predicates.add(cb.and(cb.between(root.get(Offer_.offerPrice), priceFilter.getMinPrice(), priceFilter.getMaxPrice())));
		  }
		  String[] brands = searchCriteria.getBrand();
		  if(ArraysUtil.isNotEmpty(brands)) {
			 Join<Offer, Brand> brand = root.join(Offer_.brand);
			 predicates.add(cb.and(brand.get(Brand_.brandName).in(Arrays.asList(brands))));
		  }
		  String [] states = searchCriteria.getState();
		  if(ArraysUtil.isNotEmpty(states)) {
			  Join<Offer, ItemState> itemState = root.join(Offer_.state);
			  predicates.add(cb.and(itemState.get(ItemState_.stateName).in(Arrays.asList(states))));
		  }
		  Predicate[] predicatesArray = new Predicate[predicates.size()];
		  return cb.and(predicates.toArray(predicatesArray));
	  };
}
}
